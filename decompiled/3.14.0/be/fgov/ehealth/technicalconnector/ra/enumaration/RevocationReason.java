package be.fgov.ehealth.technicalconnector.ra.enumaration;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public enum RevocationReason {
   PASSWORD_COMPROMISED("revocation.reason.password_compromised"),
   PASSWORD_FORGOTTEN("revocation.reason.password_forgotten"),
   AFFILIATION_CHANGED("revocation.reason.affiliation_changed"),
   CA_POLICY_VIOLATED("revocation.reason.ca_policy_violated"),
   OTHER("revocation.reason.other");

   private static Map<Language, ResourceBundle> i18n = new HashMap();
   private String key;

   private static void add(Language lang) {
      i18n.put(lang, ResourceBundle.getBundle("i18n.ra", lang.getLocale()));
   }

   private RevocationReason(String key) {
      this.key = key;
   }

   public String getLabel(Language lang) {
      return ((ResourceBundle)i18n.get(lang)).getString(this.key);
   }

   static {
      add(Language.NL);
      add(Language.FR);
   }
}
