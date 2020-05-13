package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ENCRYPTION-METHODvalues"
)
@XmlEnum
public enum CDENCRYPTIONMETHODvalues {
   CMS;

   public String value() {
      return this.name();
   }

   public static CDENCRYPTIONMETHODvalues fromValue(String v) {
      return valueOf(v);
   }
}
