package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-GRAFTvalues"
)
@XmlEnum
public enum CDORTHOGRAFTvalues {
   @XmlEnumValue("allograft")
   ALLOGRAFT("allograft"),
   @XmlEnumValue("homograft")
   HOMOGRAFT("homograft"),
   @XmlEnumValue("autograft")
   AUTOGRAFT("autograft"),
   @XmlEnumValue("alloandautograft")
   ALLOANDAUTOGRAFT("alloandautograft"),
   @XmlEnumValue("none")
   NONE("none");

   private final String value;

   private CDORTHOGRAFTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHOGRAFTvalues fromValue(String v) {
      CDORTHOGRAFTvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDORTHOGRAFTvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
