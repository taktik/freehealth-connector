package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ContactData implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final Pattern EMAIL_PATTERN = Pattern.compile("[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?");
   private static final Pattern PHONE_PATTERN = Pattern.compile("[+0-9][0-9 ]*");
   private String phoneGeneral;
   private String phonePrivate;
   private String emailGeneral;
   private String emailPrivate;

   public ContactData() {
   }

   public ContactData(String phonePrivate, String emailPrivate) {
      this((String)null, phonePrivate, (String)null, emailPrivate);
   }

   public ContactData(String phoneGeneral, String phonePrivate, String emailGeneral, String emailPrivate) {
      Validate.notEmpty(phonePrivate);
      Validate.notEmpty(emailPrivate);
      isValidPhone(phoneGeneral);
      isValidPhone(phonePrivate);
      isValidEmail(emailGeneral);
      isValidEmail(emailPrivate);
      this.phoneGeneral = phoneGeneral;
      this.phonePrivate = phonePrivate;
      this.emailGeneral = emailGeneral;
      this.emailPrivate = emailPrivate;
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

   public void setPhoneGeneral(String phoneGeneral) {
      this.phoneGeneral = phoneGeneral;
   }

   public void setPhonePrivate(String phonePrivate) {
      this.phonePrivate = phonePrivate;
   }

   public void setEmailGeneral(String emailGeneral) {
      this.emailGeneral = emailGeneral;
   }

   public void setEmailPrivate(String emailPrivate) {
      this.emailPrivate = emailPrivate;
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

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ContactData that = (ContactData)o;
         return (new EqualsBuilder()).append(this.getPhoneGeneral(), that.getPhoneGeneral()).append(this.getPhonePrivate(), that.getPhonePrivate()).append(this.getEmailGeneral(), that.getEmailGeneral()).append(this.getEmailPrivate(), that.getEmailPrivate()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).append(this.getPhoneGeneral()).append(this.getPhonePrivate()).append(this.getEmailGeneral()).append(this.getEmailPrivate()).toHashCode();
   }
}
