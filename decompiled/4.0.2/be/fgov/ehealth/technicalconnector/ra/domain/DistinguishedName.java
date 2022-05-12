package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.beid.BeIDFactory;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.CertificateParser;
import be.ehealth.technicalconnector.utils.IdentifierType;
import java.io.Serializable;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERPrintableString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DistinguishedName implements Serializable {
   private static final Logger LOG = LoggerFactory.getLogger(DistinguishedName.class);
   private static final long serialVersionUID = 1L;
   private static final Pattern APPLICATIONID_PATTERN = Pattern.compile("[0-9A-Z-_]{1,30}");
   private String id;
   private String name;
   private IdentifierType type;
   private String applicationId;
   private static Identity identity;
   private String basename;

   public DistinguishedName() throws TechnicalConnectorException {
      this(getIdentity().getNationalNumber(), getIdentity().getFirstName() + " " + getIdentity().getName(), (IdentifierType)IdentifierType.SSIN, (String)null);
   }

   public DistinguishedName(String id, String name, String firstName, IdentifierType type) {
      this(id, name + " " + firstName, (IdentifierType)type, (String)null);
   }

   public DistinguishedName(X500Principal principal) throws TechnicalConnectorException {
      CertificateParser parser = new CertificateParser(principal.getName("RFC2253"));
      this.setId(parser.getId());
      this.setType(parser.getIdentifier());
      this.setApplicationId(parser.getApplication());

      try {
         List<Rdn> rdns = (new LdapName(principal.getName("RFC1779"))).getRdns();
         Iterator var4 = rdns.iterator();

         while(var4.hasNext()) {
            Rdn rdn = (Rdn)var4.next();
            if (rdn.getType().equals("OU")) {
               String value = this.getValue(rdn.getValue());
               if (!"eHealth-platform Belgium".equals(value) && !value.contains("=")) {
                  this.setName(this.getValue(rdn.getValue()));
                  break;
               }
            }
         }

      } catch (InvalidNameException var7) {
         throw new IllegalArgumentException("Invalid Principal", var7);
      }
   }

   public DistinguishedName(Organization org) {
      this(org, (String)null);
   }

   public DistinguishedName(Organization org, String applicationId) {
      this(((Organization)notNull(org)).getId(), (String)notNull(org.getName()), (IdentifierType)notNull(org.getType()), applicationId);
   }

   private DistinguishedName(String id, String name, IdentifierType type, String applicationId) {
      this.setId(id);
      this.setName(name);
      this.setApplicationId(applicationId);
      this.setType(type);
   }

   private void setId(String id) {
      Validate.notEmpty(id);
      this.id = id;
   }

   private void setName(String name) {
      Validate.notEmpty(name);
      this.name = normalize(name);
   }

   public void setApplicationId(String applicationId) {
      this.isValidApplicationId(normalize(applicationId));
      this.applicationId = normalize(applicationId);
   }

   private void setType(IdentifierType type) {
      Validate.notNull(type);
      this.type = type;
   }

   private void isValidApplicationId(String applicationId) {
      if (!StringUtils.isEmpty(applicationId)) {
         Validate.isTrue(APPLICATIONID_PATTERN.matcher(applicationId).matches());
      }
   }

   public String getId() {
      return this.id;
   }

   public String getApplicationId() {
      return this.applicationId;
   }

   public String getName() {
      return this.name;
   }

   public IdentifierType getType() {
      return this.type;
   }

   public boolean isNaturalPerson() {
      return this.getType() == IdentifierType.SSIN;
   }

   public Map<String, Object> toOIDMap() {
      Map<String, Object> result = new HashMap();
      result.put("C", "BE");
      result.put("O", "Federal Government");
      Set<String> ou = new LinkedHashSet();
      ou.add("eHealth-platform Belgium");
      ou.add(this.name);
      String ou3 = this.type.getType(48) + "\\=" + this.id;
      ou.add(ou3);
      if (StringUtils.isNotEmpty(this.applicationId)) {
         ou.add(this.applicationId);
         result.put("CN", ou3 + "\\, " + this.applicationId);
      } else {
         result.put("CN", ou3);
      }

      result.put("OU", ou);
      return result;
   }

   public String asNormalizedEhealthDN() {
      Map<String, Object> oidMap = this.toOIDMap();
      StringBuffer sb = new StringBuffer();
      sb.append("CN=").append(oidMap.get("CN")).append(",");
      Set<String> ouSet = (Set)oidMap.get("OU");
      Iterator var4 = ouSet.iterator();

      while(var4.hasNext()) {
         String ou = (String)var4.next();
         sb.append("OU=").append(ou).append(",");
      }

      sb.append("O=").append(oidMap.get("O")).append(",");
      sb.append("C=").append(oidMap.get("C"));
      return sb.toString();
   }

   public String asNormalisedBaseFileName() {
      if (StringUtils.isBlank(this.basename)) {
         StringBuffer sb = new StringBuffer();
         sb.append(this.type.getType(48)).append("=").append(this.id);
         if (!StringUtils.isBlank(this.applicationId)) {
            sb.append(",");
            sb.append(this.applicationId);
         }

         sb.append(" ");
         sb.append(DateTimeFormat.forPattern("yyyyMMdd-HHmmss").print(new DateTime())).append(".p12");
         this.basename = sb.toString();
      }

      return this.basename;
   }

   private static Identity getIdentity() throws TechnicalConnectorException {
      if (identity == null) {
         identity = BeIDFactory.getBeIDInfo("identity", false).getIdentity();
      }

      return identity;
   }

   private static String normalize(String str) {
      return str == null ? null : Normalizer.normalize(str.toUpperCase(), Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
   }

   private static <T> T notNull(T obj) {
      Validate.notNull(obj);
      return obj;
   }

   private String getValue(Object value) {
      if (value instanceof String) {
         return (String)value;
      } else if (value instanceof byte[]) {
         return convertToString((byte[])((byte[])value));
      } else {
         LOG.error("Unsupported value [" + value.getClass() + "]");
         return "";
      }
   }

   private static String convertToString(byte[] value) {
      try {
         ASN1Primitive content = ASN1Primitive.fromByteArray(value);
         if (content instanceof DERPrintableString) {
            return ((DERPrintableString)content).getString();
         }

         LOG.error("Unsupported ASN1Object :" + content.getClass());
      } catch (Exception var2) {
         LOG.error("Error while converting to String", var2);
      }

      return null;
   }
}
