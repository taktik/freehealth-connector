package be.fgov.ehealth.technicalconnector.ra.enumaration;

import java.util.Locale;

public enum Language {
   NL("nl", new Locale("nl")),
   FR("fr", new Locale("fr"));

   private String lang;
   private Locale locale;

   private Language(String lang, Locale locale) {
      this.lang = lang;
      this.locale = locale;
   }

   public String getLanguageAbbreviation() {
      return this.lang;
   }

   public Locale getLocale() {
      return this.locale;
   }
}
