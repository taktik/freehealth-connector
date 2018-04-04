package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.technicalconnector.ra.enumaration.Language;
import be.fgov.ehealth.technicalconnector.ra.enumaration.RevocationReason;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.StringUtils;

public class RevokeCertificateContract extends Contract {
   private static final long serialVersionUID = 1L;
   private String content;
   private X509Certificate cert;

   public RevokeCertificateContract(X509Certificate cert, RevocationReason reason, Language lang) throws TechnicalConnectorException {
      this(cert, reason, (String)null, lang);
   }

   public RevokeCertificateContract(X509Certificate cert, RevocationReason reason, String reasonDetail, Language lang) throws TechnicalConnectorException {
      Validate.notNull(cert);
      Validate.notNull(reason);
      Validate.notNull(lang);
      this.cert = cert;
      if (reason == RevocationReason.OTHER) {
         Validate.notEmpty(reasonDetail);
      } else {
         Validate.isTrue(StringUtils.isEmpty(reasonDetail));
      }

      this.content = generate(new DistinguishedName(cert.getSubjectX500Principal()), reason, reasonDetail, this.getRequestor(), lang);
   }

   protected String getContent() {
      return this.content;
   }

   public X509Certificate getX509Certificate() {
      return this.cert;
   }

   private static String generate(DistinguishedName name, RevocationReason reason, String reasonDetail, Identity identity, Language lang) throws TechnicalConnectorException {
      String template = "/templates/contract.revoke." + lang.getLanguageAbbreviation() + ".html";
      Map<String, Object> context = new HashMap();
      context.put("oids", name.toOIDMap());
      context.put("identity", identity);
      context.put("name", name);
      context.put("reason", reason.getLabel(lang));
      context.put("reasonDetail", reasonDetail);
      return generatedContract(context, template);
   }
}
