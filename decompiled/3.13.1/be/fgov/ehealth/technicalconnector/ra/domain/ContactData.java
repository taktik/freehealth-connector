package be.fgov.ehealth.technicalconnector.ra.domain;

import be.fgov.ehealth.technicalconnector.ra.enumaration.Language;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.commons.lang.Validate;

public final class ContactData implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final Pattern EMAIL_PATTERN = Pattern.compile("[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?");
   private static final Pattern PHONE_PATTERN = Pattern.compile("[+0-9][0-9 ]*");
   private String phoneGeneral;
   private String phonePrivate;
   private String emailGeneral;
   private String emailPrivate;
   private Language lang;

   public ContactData(String phonePrivate, String emailPrivate, Language lang) {
      this((String)null, phonePrivate, (String)null, emailPrivate, lang);
   }

   public ContactData(String phoneGeneral, String phonePrivate, String emailGeneral, String emailPrivate, Language lang) {
      Validate.notEmpty(phonePrivate);
      Validate.notEmpty(emailPrivate);
      Validate.notNull(lang);
      isValidPhone(phoneGeneral);
      isValidPhone(phonePrivate);
      isValidEmail(emailGeneral);
      isValidEmail(emailPrivate);
      this.phoneGeneral = phoneGeneral;
      this.phonePrivate = phonePrivate;
      this.emailGeneral = emailGeneral;
      this.emailPrivate = emailPrivate;
      this.lang = lang;
   }

   private static void isValidEmail(String emailAddress) {
      if (emailAddress != null) {
         if (emailAddress.length() > 320) {
            throw new IllegalArgumentException("emailAddress [" + emailAddress + "] to long.");
         } else {
            Validate.isTrue(EMAIL_PATTERN.matcher(emailAddress).matches());
         }
      }
   }

   private static void isValidPhone(String phone) {
      if (phone != null) {
         Validate.isTrue(PHONE_PATTERN.matcher(phone).matches());
      }
   }

   public String getPhoneGeneral() {
      return this.phoneGeneral;
   }

   public String getPhonePrivate() {
      return this.phonePrivate;
   }

   public String getEmailGeneral() {
      return this.emailGeneral;
   }

   public String getEmailPrivate() {
      return this.emailPrivate;
   }

   public Language getLanguage() {
      return this.lang;
   }
}
