package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TUCO-STEMITYPEvalues"
)
@XmlEnum
public enum CDTUCOSTEMITYPEvalues {
   @XmlEnumValue("stemipci")
   STEMIPCI("stemipci"),
   @XmlEnumValue("stemirescue")
   STEMIRESCUE("stemirescue"),
   @XmlEnumValue("stemilate")
   STEMILATE("stemilate"),
   @XmlEnumValue("nonstemiurgent")
   NONSTEMIURGENT("nonstemiurgent"),
   @XmlEnumValue("nonstemielective")
   NONSTEMIELECTIVE("nonstemielective"),
   @XmlEnumValue("nonstemilate")
   NONSTEMILATE("nonstemilate"),
   @XmlEnumValue("emergentpci")
   EMERGENTPCI("emergentpci"),
   @XmlEnumValue("electivepci")
   ELECTIVEPCI("electivepci"),
   @XmlEnumValue("outofhospitalarrest")
   OUTOFHOSPITALARREST("outofhospitalarrest"),
   @XmlEnumValue("stagedpci")
   STAGEDPCI("stagedpci"),
   @XmlEnumValue("complicationpriorpci")
   COMPLICATIONPRIORPCI("complicationpriorpci"),
   @XmlEnumValue("recurrendischaemia")
   RECURRENDISCHAEMIA("recurrendischaemia");

   private final String value;

   private CDTUCOSTEMITYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTUCOSTEMITYPEvalues fromValue(String v) {
      CDTUCOSTEMITYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTUCOSTEMITYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
