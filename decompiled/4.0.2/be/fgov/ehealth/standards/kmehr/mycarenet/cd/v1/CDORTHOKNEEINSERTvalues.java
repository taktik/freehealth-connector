package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-KNEE-INSERTvalues"
)
@XmlEnum
public enum CDORTHOKNEEINSERTvalues {
   @XmlEnumValue("fixed")
   FIXED("fixed"),
   @XmlEnumValue("mobile")
   MOBILE("mobile"),
   @XmlEnumValue("none")
   NONE("none");

   private final String value;

   private CDORTHOKNEEINSERTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHOKNEEINSERTvalues fromValue(String v) {
      CDORTHOKNEEINSERTvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDORTHOKNEEINSERTvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
