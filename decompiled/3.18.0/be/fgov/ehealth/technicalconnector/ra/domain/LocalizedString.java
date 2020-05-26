package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class LocalizedString implements Serializable {
   private static final long serialVersionUID = 1L;
   private String value;
   private String lang;

   public void setValue(String value) {
      this.value = value;
   }

   public void setLang(String lang) {
      this.lang = lang;
   }

   public LocalizedString() {
   }

   private LocalizedString(LocalizedString.Builder builder) {
      this.value = builder.value;
      this.lang = builder.lang;
   }

   public static LocalizedString.Builder newBuilder() {
      return new LocalizedString.Builder();
   }

   public String getValue() {
      return this.value;
   }

   public String getLang() {
      return this.lang;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         LocalizedString that = (LocalizedString)o;
         return (new EqualsBuilder()).append(this.getValue(), that.getValue()).append(this.getLang(), that.getLang()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).append(this.getValue()).append(this.getLang()).toHashCode();
   }

   // $FF: synthetic method
   LocalizedString(LocalizedString.Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private String value;
      private String lang;

      private Builder() {
      }

      public LocalizedString.Builder value(String value) {
         this.value = value;
         return this;
      }

      public LocalizedString.Builder lang(String lang) {
         this.lang = lang;
         return this;
      }

      public LocalizedString build() {
         return new LocalizedString(this);
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
