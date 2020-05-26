package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CONTACT-PERSON"
)
@XmlEnum
public enum CDCONTACTPERSON {
   FATHER,
   MOTHER,
   CHILD,
   BROTHER,
   BROTHERINLAW,
   SISTER,
   SISTERINLAW,
   GRANDPARENT,
   HUSBAND,
   SPOUSE,
   PARTNER,
   LAWYER,
   NOTARY,
   TUTOR,
   EMPLOYER,
   GUARDIAN,
   NEPHEW,
   NIECE,
   UNCLE,
   AUNT,
   FRIEND;

   public String value() {
      return this.name();
   }

   public static CDCONTACTPERSON fromValue(String v) {
      return valueOf(v);
   }
}
