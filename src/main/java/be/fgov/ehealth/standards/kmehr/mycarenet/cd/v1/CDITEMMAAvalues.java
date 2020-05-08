package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEM-MAAvalues"
)
@XmlEnum
public enum CDITEMMAAvalues {
   @XmlEnumValue("agreementtype")
   AGREEMENTTYPE("agreementtype"),
   @XmlEnumValue("requesttype")
   REQUESTTYPE("requesttype"),
   @XmlEnumValue("responsetype")
   RESPONSETYPE("responsetype"),
   @XmlEnumValue("agreementstartdate")
   AGREEMENTSTARTDATE("agreementstartdate"),
   @XmlEnumValue("agreementenddate")
   AGREEMENTENDDATE("agreementenddate"),
   @XmlEnumValue("consultationstartdate")
   CONSULTATIONSTARTDATE("consultationstartdate"),
   @XmlEnumValue("consultationenddate")
   CONSULTATIONENDDATE("consultationenddate"),
   @XmlEnumValue("careproviderreference")
   CAREPROVIDERREFERENCE("careproviderreference"),
   @XmlEnumValue("iorequestreference")
   IOREQUESTREFERENCE("iorequestreference"),
   @XmlEnumValue("decisionreference")
   DECISIONREFERENCE("decisionreference"),
   @XmlEnumValue("refusaljustification")
   REFUSALJUSTIFICATION("refusaljustification"),
   @XmlEnumValue("chapter4reference")
   CHAPTER_4_REFERENCE("chapter4reference"),
   @XmlEnumValue("chapter4annexreference")
   CHAPTER_4_ANNEXREFERENCE("chapter4annexreference"),
   @XmlEnumValue("unitnumber")
   UNITNUMBER("unitnumber"),
   @XmlEnumValue("strength")
   STRENGTH("strength"),
   @XmlEnumValue("restunitnumber")
   RESTUNITNUMBER("restunitnumber"),
   @XmlEnumValue("reststrength")
   RESTSTRENGTH("reststrength"),
   @XmlEnumValue("orphandrugdeliveryplace")
   ORPHANDRUGDELIVERYPLACE("orphandrugdeliveryplace"),
   @XmlEnumValue("orphandrugdeliveryid")
   ORPHANDRUGDELIVERYID("orphandrugdeliveryid"),
   @XmlEnumValue("coveragetype")
   COVERAGETYPE("coveragetype");

   private final String value;

   private CDITEMMAAvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMMAAvalues fromValue(String v) {
      CDITEMMAAvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDITEMMAAvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
