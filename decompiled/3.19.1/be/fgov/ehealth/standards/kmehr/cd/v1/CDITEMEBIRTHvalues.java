package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEM-EBIRTHvalues"
)
@XmlEnum
public enum CDITEMEBIRTHvalues {
   @XmlEnumValue("multiparity")
   MULTIPARITY("multiparity"),
   @XmlEnumValue("samesex")
   SAMESEX("samesex"),
   @XmlEnumValue("stillborn")
   STILLBORN("stillborn"),
   @XmlEnumValue("birthplace")
   BIRTHPLACE("birthplace"),
   @XmlEnumValue("birthrank")
   BIRTHRANK("birthrank"),
   @XmlEnumValue("partusnumber")
   PARTUSNUMBER("partusnumber"),
   @XmlEnumValue("beforepregnancyweight")
   BEFOREPREGNANCYWEIGHT("beforepregnancyweight"),
   @XmlEnumValue("atdeliveryweight")
   ATDELIVERYWEIGHT("atdeliveryweight"),
   @XmlEnumValue("height")
   HEIGHT("height"),
   @XmlEnumValue("previouschildbirth")
   PREVIOUSCHILDBIRTH("previouschildbirth"),
   @XmlEnumValue("previousbornalive")
   PREVIOUSBORNALIVE("previousbornalive"),
   @XmlEnumValue("lastbabybirthdate")
   LASTBABYBIRTHDATE("lastbabybirthdate"),
   @XmlEnumValue("intermediatestillborndelivery")
   INTERMEDIATESTILLBORNDELIVERY("intermediatestillborndelivery"),
   @XmlEnumValue("previouscaesarean")
   PREVIOUSCAESAREAN("previouscaesarean"),
   @XmlEnumValue("parity")
   PARITY("parity"),
   @XmlEnumValue("pregnancyorigin")
   PREGNANCYORIGIN("pregnancyorigin"),
   @XmlEnumValue("hypertensiondiagnose")
   HYPERTENSIONDIAGNOSE("hypertensiondiagnose"),
   @XmlEnumValue("diabetesdiagnose")
   DIABETESDIAGNOSE("diabetesdiagnose"),
   @XmlEnumValue("HIVdiagnose")
   HI_VDIAGNOSE("HIVdiagnose"),
   @XmlEnumValue("pregnancyduration")
   PREGNANCYDURATION("pregnancyduration"),
   @XmlEnumValue("childposition")
   CHILDPOSITION("childposition"),
   @XmlEnumValue("inductiondelivery")
   INDUCTIONDELIVERY("inductiondelivery"),
   @XmlEnumValue("epiduralanalgesia")
   EPIDURALANALGESIA("epiduralanalgesia"),
   @XmlEnumValue("rachianalagesia")
   RACHIANALAGESIA("rachianalagesia"),
   @XmlEnumValue("foetalmonitoring")
   FOETALMONITORING("foetalmonitoring"),
   @XmlEnumValue("streptococcusbcolonization")
   STREPTOCOCCUSBCOLONIZATION("streptococcusbcolonization"),
   @XmlEnumValue("intrapartalsbgprophylaxis")
   INTRAPARTALSBGPROPHYLAXIS("intrapartalsbgprophylaxis"),
   @XmlEnumValue("deliveryway")
   DELIVERYWAY("deliveryway"),
   @XmlEnumValue("episiotomy")
   EPISIOTOMY("episiotomy"),
   @XmlEnumValue("caesareanindication")
   CAESAREANINDICATION("caesareanindication"),
   @XmlEnumValue("breastfeeding")
   BREASTFEEDING("breastfeeding"),
   @XmlEnumValue("atbirthweight")
   ATBIRTHWEIGHT("atbirthweight"),
   @XmlEnumValue("apgarscore1")
   APGARSCORE_1("apgarscore1"),
   @XmlEnumValue("apgarscore5")
   APGARSCORE_5("apgarscore5"),
   @XmlEnumValue("artificialrespiration")
   ARTIFICIALRESPIRATION("artificialrespiration"),
   @XmlEnumValue("neonataldept")
   NEONATALDEPT("neonataldept"),
   @XmlEnumValue("congenitalmalformation")
   CONGENITALMALFORMATION("congenitalmalformation"),
   @XmlEnumValue("othermedicalrisk")
   OTHERMEDICALRISK("othermedicalrisk");

   private final String value;

   private CDITEMEBIRTHvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMEBIRTHvalues fromValue(String v) {
      CDITEMEBIRTHvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDITEMEBIRTHvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
