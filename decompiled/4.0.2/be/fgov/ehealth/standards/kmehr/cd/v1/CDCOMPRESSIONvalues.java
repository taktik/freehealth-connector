package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-COMPRESSIONvalues"
)
@XmlEnum
public enum CDCOMPRESSIONvalues {
   DF,
   GZ,
   Z,
   ZL;

   private CDCOMPRESSIONvalues() {
   }

   public String value() {
      return this.name();
   }

   public static CDCOMPRESSIONvalues fromValue(String v) {
      return valueOf(v);
   }
}
