package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-CONGENITALMALFORMATIONvalues"
)
@XmlEnum
public enum CDEBIRTHCONGENITALMALFORMATIONvalues {
   @XmlEnumValue("anencephalia")
   ANENCEPHALIA("anencephalia"),
   @XmlEnumValue("spinabifida")
   SPINABIFIDA("spinabifida"),
   @XmlEnumValue("hydrocephalia")
   HYDROCEPHALIA("hydrocephalia"),
   @XmlEnumValue("splitlippalate")
   SPLITLIPPALATE("splitlippalate"),
   @XmlEnumValue("analatresia")
   ANALATRESIA("analatresia"),
   @XmlEnumValue("membersreduction")
   MEMBERSREDUCTION("membersreduction"),
   @XmlEnumValue("diaphragmatichernia")
   DIAPHRAGMATICHERNIA("diaphragmatichernia"),
   @XmlEnumValue("omphalocele")
   OMPHALOCELE("omphalocele"),
   @XmlEnumValue("gastroschisis")
   GASTROSCHISIS("gastroschisis"),
   @XmlEnumValue("transpositiegrotevaten")
   TRANSPOSITIEGROTEVATEN("transpositiegrotevaten"),
   @XmlEnumValue("afwijkinglong")
   AFWIJKINGLONG("afwijkinglong"),
   @XmlEnumValue("atresiedundarm")
   ATRESIEDUNDARM("atresiedundarm"),
   @XmlEnumValue("nieragenese")
   NIERAGENESE("nieragenese"),
   @XmlEnumValue("craniosynostosis")
   CRANIOSYNOSTOSIS("craniosynostosis"),
   @XmlEnumValue("turnersyndrom")
   TURNERSYNDROM("turnersyndrom"),
   @XmlEnumValue("obstructievedefecten")
   OBSTRUCTIEVEDEFECTEN("obstructievedefecten"),
   @XmlEnumValue("tetralogiefallot")
   TETRALOGIEFALLOT("tetralogiefallot"),
   @XmlEnumValue("oesofagaleatresie")
   OESOFAGALEATRESIE("oesofagaleatresie"),
   @XmlEnumValue("atresieanus")
   ATRESIEANUS("atresieanus"),
   @XmlEnumValue("twintotwintransfusionsyndrome")
   TWINTOTWINTRANSFUSIONSYNDROME("twintotwintransfusionsyndrome"),
   @XmlEnumValue("skeletdysplasie")
   SKELETDYSPLASIE("skeletdysplasie"),
   @XmlEnumValue("hydropsfoetalis")
   HYDROPSFOETALIS("hydropsfoetalis"),
   @XmlEnumValue("polymultikystischenierdysplasie")
   POLYMULTIKYSTISCHENIERDYSPLASIE("polymultikystischenierdysplasie"),
   VSD("VSD"),
   @XmlEnumValue("atresiegalwegen")
   ATRESIEGALWEGEN("atresiegalwegen"),
   @XmlEnumValue("hypospadias")
   HYPOSPADIAS("hypospadias"),
   @XmlEnumValue("cystischhygroma")
   CYSTISCHHYGROMA("cystischhygroma"),
   @XmlEnumValue("trisomie21")
   TRISOMIE_21("trisomie21"),
   @XmlEnumValue("trisomie18")
   TRISOMIE_18("trisomie18"),
   @XmlEnumValue("trisomie13")
   TRISOMIE_13("trisomie13"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDEBIRTHCONGENITALMALFORMATIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHCONGENITALMALFORMATIONvalues fromValue(String v) {
      CDEBIRTHCONGENITALMALFORMATIONvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEBIRTHCONGENITALMALFORMATIONvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
