package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class CertificateParser {
   private static final Logger LOG = Logger.getLogger(CertificateParser.class);
   private static final String ORGANIZATION_UNIT_NAME_ATTRIBUTE_TYPE = "OU";
   private static final String COMMON_NAME_ATTRIBUTE_TYPE = "CN";
   private static final String REGXP_VALIDATION = "([A-Z(-|_)]+=[0-9]+)";
   private static final String FEDERAL_GOVERMENT = "Federal Government";
   private static final String EHEALTH_PLATFORM = "eHealth-platform Belgium";
   private static final String ESCAPE_CHAR = "=";
   private static final String X500_KEY_VALUE_SEPARATOR = "=";
   private static final String X500_FIELD_SEPARATOR = ",";
   private static final String X500_START_STRING_DELIMITER = "=\"";
   private static final String X500_END_STRING_DELIMITER = "\", ";
   private static final String X500_ESCAPE_CHARACTER = "\\";
   private static final String SAN_ESCAPE_CHAR = "&bksp;";
   private static final String SAN_ESCAPED_QUOTE = "&bkqt;";
   private String type;
   private String value;
   private String application;

   public CertificateParser(X509Certificate cert) throws IntegrationModuleException {
      this(cert.getSubjectX500Principal().getName("RFC1779"));
   }

   public CertificateParser(String subject) throws IntegrationModuleException {
      this.type = "";
      this.value = "";
      this.application = "";
      LOG.info("CertificateParser subject: " + subject);
      HashMap<String, ArrayList<String>> subjects = this.splitCertificateString(subject);
      String commonName = (String)((ArrayList)subjects.get("CN")).get(0);
      LOG.debug("Certificate CN:" + commonName);
      ArrayList<String> ouList = (ArrayList)subjects.get("OU");
      LOG.debug("Certificate OUList loaded");
      if (ouList != null && ouList.size() != 0) {
         Iterator iterator = ouList.iterator();

         while(iterator.hasNext()) {
            String ou = (String)iterator.next();
            LOG.debug("OU:" + ou);
            if (Pattern.matches("([A-Z(-|_)]+=[0-9]+)", ou)) {
               String[] splittedOU = ou.split("=");
               this.type = splittedOU[0];
               this.value = splittedOU[1];
               LOG.debug("Certificat type: " + this.type + " & " + " value: " + this.value);
            } else if (ouList.size() >= 4 && !ou.equals("Federal Government") && !ou.equals("eHealth-platform Belgium") && commonName.indexOf(ou) >= 0) {
               LOG.debug("Certificat application id: " + this.application);
               this.application = ou;
            }
         }

      } else {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"));
      }
   }

   private HashMap<String, ArrayList<String>> splitCertificateString(String x500PrincipalName) {
      HashMap<String, ArrayList<String>> items = new HashMap();
      int indexOfEscapeCharacter = x500PrincipalName.indexOf("\\");
      String workString = null;
      if (indexOfEscapeCharacter > 0) {
         workString = x500PrincipalName.replace("\\\\", "&bksp;");
         workString = workString.replace("\\\"", "&bkqt;");
      } else {
         workString = x500PrincipalName;
      }

      LOG.debug("The certificatestring in splitCertificateString 1: " + workString);
      int indexOfStringDelimiter = x500PrincipalName.indexOf("=\"");
      String[] x500Fields;
      int i;
      if (indexOfStringDelimiter == -1) {
         x500Fields = workString.split(",");

         for(i = 0; i < x500Fields.length; ++i) {
            String x500Field = x500Fields[i];
            if (StringUtils.isNotEmpty(x500Field) && x500Field.split("=").length == 3) {
               LOG.debug("x500Field has several = in his value: " + x500Field + " and we check the next field for the application id");
               if (i + 1 < x500Fields.length) {
                  ++i;
                  String x500Field2 = x500Fields[i];
                  if (StringUtils.isNotEmpty(x500Field2) && x500Field2.split("=").length > 1) {
                     LOG.debug("x500Field has several = in his value: " + x500Field + " and we didn't find the application id");
                     this.addX500Field(x500Field, items);
                     this.addX500Field(x500Field2, items);
                  } else {
                     LOG.debug("x500Field has several = in his value: " + x500Field + " and we found the application id: " + x500Field2);
                     this.addX500Field(x500Field + ", " + x500Field2, items);
                  }
               } else {
                  this.addX500Field(x500Field, items);
               }
            } else {
               this.addX500Field(x500Field, items);
            }
         }
      } else {
         LOG.debug("The workstring had seceral quotes. The method will be used: splitStringWithquotes");
         x500Fields = (String[])this.splitStringWithquotes(workString).toArray(new String[0]);

         for(i = 0; i < x500Fields.length; ++i) {
            this.addX500Field(x500Fields[i], items);
         }
      }

      return items;
   }

   private List<String> splitStringWithquotes(String x500PrincipalName) {
      boolean notFinished = true;
      List<String> tempList = new ArrayList();
      String workString = x500PrincipalName;
      int indexOfStringDelimiter = x500PrincipalName.indexOf("=\"");
      int indexOfFieldSeparator = x500PrincipalName.indexOf(",");

      while(notFinished) {
         if (indexOfStringDelimiter != -1 && indexOfFieldSeparator >= indexOfStringDelimiter) {
            if (workString.indexOf("\", ") != -1) {
               LOG.debug("splitStringWithquotes: if statement two");
               String tempString = workString.substring(0, workString.indexOf("\", ") + 1);
               tempList.add(tempString);
               workString = workString.substring(workString.indexOf("\", ") + 3);
            } else if (workString.indexOf(",") != -1 && workString.indexOf("=") != -1) {
               LOG.debug("splitStringWithquotes: if statement three");
               tempList.add(workString);
               workString = workString.substring(workString.length());
            }
         } else {
            LOG.debug("splitStringWithquotes: if statement one");
            String[] tempStringTable = workString.split(",", 2);
            tempList.add(tempStringTable[0].trim());
            workString = tempStringTable[1].trim();
         }

         indexOfStringDelimiter = workString.indexOf("=\"");
         indexOfFieldSeparator = workString.indexOf(",");
         if (indexOfFieldSeparator == -1) {
            notFinished = false;
            if (StringUtils.isNotEmpty(workString)) {
               LOG.debug("splitStringWithquotes: last field added for pharmacist");
               tempList.add(workString);
            }
         }
      }

      return tempList;
   }

   private void addX500Field(String x500Field, HashMap<String, ArrayList<String>> x500Fields) {
      String[] parts = x500Field.split("=", 2);
      if (parts.length == 2) {
         String key = parts[0].trim();
         String value = parts[1].trim();
         value = value.replace("&bksp;", "\\\\");
         value = value.replace("&bkqt;", "\\\"");
         if (value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
            value = value.substring(1, value.length() - 1);
         }

         LOG.info("X500Field: key [" + key + "], value [" + value + "]");
         ArrayList<String> list = (ArrayList)x500Fields.get(key);
         if (list == null) {
            list = new ArrayList();
            x500Fields.put(key, list);
         }

         list.add(value);
      } else {
         LOG.info("X500Field [" + x500Field + "] has invalid structure. Ignoring ...");
      }

   }

   public String getType() {
      return this.type;
   }

   public String getValue() {
      return this.value;
   }

   public String getApplication() {
      return this.application;
   }
}
