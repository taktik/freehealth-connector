package be.fgov.ehealth.certra.core.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "RevocationReasonType"
)
@XmlEnum
public enum RevocationReasonType {
   @XmlEnumValue("keyCompromise")
   KEY_COMPROMISE("keyCompromise"),
   @XmlEnumValue("affiliationChanged")
   AFFILIATION_CHANGED("affiliationChanged"),
   @XmlEnumValue("superseded")
   SUPERSEDED("superseded"),
   @XmlEnumValue("cessationOfOperation")
   CESSATION_OF_OPERATION("cessationOfOperation");

   private final String value;

   private RevocationReasonType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static RevocationReasonType fromValue(String v) {
      RevocationReasonType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         RevocationReasonType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
