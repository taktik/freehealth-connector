package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TRANSACTIONschemes"
)
@XmlEnum
public enum CDTRANSACTIONschemes {
   @XmlEnumValue("CD-TRANSACTION")
   CD_TRANSACTION("CD-TRANSACTION"),
   @XmlEnumValue("CD-TRANSACTION-CARENET")
   CD_TRANSACTION_CARENET("CD-TRANSACTION-CARENET"),
   @XmlEnumValue("CD-TRANSACTION-MAA")
   CD_TRANSACTION_MAA("CD-TRANSACTION-MAA"),
   @XmlEnumValue("CD-CHAPTER4APPENDIX")
   CD_CHAPTER_4_APPENDIX("CD-CHAPTER4APPENDIX"),
   @XmlEnumValue("CD-TRANSACTION-REG")
   CD_TRANSACTION_REG("CD-TRANSACTION-REG"),
   @XmlEnumValue("CD-TRANSACTION-MYCARENET")
   CD_TRANSACTION_MYCARENET("CD-TRANSACTION-MYCARENET"),
   @XmlEnumValue("CD-TRANSACTION-TYPE")
   CD_TRANSACTION_TYPE("CD-TRANSACTION-TYPE"),
   @XmlEnumValue("CD-HUBSERVICE")
   CD_HUBSERVICE("CD-HUBSERVICE"),
   @XmlEnumValue("CD-DIARY")
   CD_DIARY("CD-DIARY"),
   LOCAL("LOCAL");

   private final String value;

   private CDTRANSACTIONschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTRANSACTIONschemes fromValue(String v) {
      CDTRANSACTIONschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTRANSACTIONschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
