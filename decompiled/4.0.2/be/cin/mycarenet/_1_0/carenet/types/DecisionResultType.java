package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "DecisionResultType"
)
@XmlEnum
public enum DecisionResultType {
   @XmlEnumValue("agreement")
   AGREEMENT("agreement"),
   @XmlEnumValue("partialAgreement")
   PARTIAL_AGREEMENT("partialAgreement"),
   @XmlEnumValue("onlyMedicalAgreement")
   ONLY_MEDICAL_AGREEMENT("onlyMedicalAgreement"),
   @XmlEnumValue("onlyMedicalAgreementWithoutSmallRisks")
   ONLY_MEDICAL_AGREEMENT_WITHOUT_SMALL_RISKS("onlyMedicalAgreementWithoutSmallRisks"),
   @XmlEnumValue("rejection")
   REJECTION("rejection");

   private final String value;

   private DecisionResultType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static DecisionResultType fromValue(String v) {
      DecisionResultType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         DecisionResultType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
