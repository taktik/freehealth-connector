package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ECGvalues"
)
@XmlEnum
public enum CDECGvalues {
   VR("VR"),
   AR("AR"),
   PR("PR"),
   QRS("QRS"),
   QT("QT"),
   @XmlEnumValue("QTc")
   Q_TC("QTc"),
   P("P"),
   R("R"),
   T("T");

   private final String value;

   private CDECGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDECGvalues fromValue(String v) {
      CDECGvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDECGvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
