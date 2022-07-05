package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CONTACT-PERSONvalues"
)
@XmlEnum
public enum CDCONTACTPERSONvalues {
   @XmlEnumValue("mother")
   MOTHER("mother"),
   @XmlEnumValue("father")
   FATHER("father"),
   @XmlEnumValue("child")
   CHILD("child"),
   @XmlEnumValue("familymember")
   FAMILYMEMBER("familymember"),
   @XmlEnumValue("spouse")
   SPOUSE("spouse"),
   @XmlEnumValue("husband")
   HUSBAND("husband"),
   @XmlEnumValue("partner")
   PARTNER("partner"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("brother")
   BROTHER("brother"),
   @XmlEnumValue("sister")
   SISTER("sister"),
   @XmlEnumValue("brotherinlaw")
   BROTHERINLAW("brotherinlaw"),
   @XmlEnumValue("tutor")
   TUTOR("tutor"),
   @XmlEnumValue("notary")
   NOTARY("notary"),
   @XmlEnumValue("lawyer")
   LAWYER("lawyer"),
   @XmlEnumValue("employer")
   EMPLOYER("employer"),
   @XmlEnumValue("grandparent")
   GRANDPARENT("grandparent"),
   @XmlEnumValue("son")
   SON("son"),
   @XmlEnumValue("daughter")
   DAUGHTER("daughter"),
   @XmlEnumValue("grandchild")
   GRANDCHILD("grandchild"),
   @XmlEnumValue("neighbour")
   NEIGHBOUR("neighbour"),
   @XmlEnumValue("stepson")
   STEPSON("stepson"),
   @XmlEnumValue("stepdaughter")
   STEPDAUGHTER("stepdaughter"),
   @XmlEnumValue("stepfather")
   STEPFATHER("stepfather"),
   @XmlEnumValue("stepmother")
   STEPMOTHER("stepmother"),
   @XmlEnumValue("sisterinlaw")
   SISTERINLAW("sisterinlaw");

   private final String value;

   private CDCONTACTPERSONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCONTACTPERSONvalues fromValue(String v) {
      CDCONTACTPERSONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDCONTACTPERSONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
