package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEM-CARENETvalues"
)
@XmlEnum
public enum CDITEMCARENETvalues {
   @XmlEnumValue("accidenttype")
   ACCIDENTTYPE("accidenttype"),
   @XmlEnumValue("advisingphysician")
   ADVISINGPHYSICIAN("advisingphysician"),
   @XmlEnumValue("agreement")
   AGREEMENT("agreement"),
   @XmlEnumValue("authorisedextensionenddatetime")
   AUTHORISEDEXTENSIONENDDATETIME("authorisedextensionenddatetime"),
   @XmlEnumValue("begindatetime")
   BEGINDATETIME("begindatetime"),
   @XmlEnumValue("billingdestinationnumber")
   BILLINGDESTINATIONNUMBER("billingdestinationnumber"),
   @XmlEnumValue("insurancydetails")
   INSURANCYDETAILS("insurancydetails"),
   @XmlEnumValue("insurancystatus")
   INSURANCYSTATUS("insurancystatus"),
   @XmlEnumValue("messagenumber")
   MESSAGENUMBER("messagenumber"),
   @XmlEnumValue("messagetype")
   MESSAGETYPE("messagetype"),
   @XmlEnumValue("missingdocument")
   MISSINGDOCUMENT("missingdocument"),
   @XmlEnumValue("mutationbegindatetime")
   MUTATIONBEGINDATETIME("mutationbegindatetime"),
   @XmlEnumValue("mutationdestination")
   MUTATIONDESTINATION("mutationdestination"),
   @XmlEnumValue("nationalinsurance")
   NATIONALINSURANCE("nationalinsurance"),
   @XmlEnumValue("otheradmission")
   OTHERADMISSION("otheradmission"),
   @XmlEnumValue("protectionmeasure")
   PROTECTIONMEASURE("protectionmeasure"),
   @XmlEnumValue("refusalreason")
   REFUSALREASON("refusalreason"),
   @XmlEnumValue("requestedextensiondatetime")
   REQUESTEDEXTENSIONDATETIME("requestedextensiondatetime"),
   @XmlEnumValue("requestedextensionenddatetime")
   REQUESTEDEXTENSIONENDDATETIME("requestedextensionenddatetime"),
   @XmlEnumValue("siscardadjustment")
   SISCARDADJUSTMENT("siscardadjustment"),
   @XmlEnumValue("siscarderror")
   SISCARDERROR("siscarderror"),
   @XmlEnumValue("socialcategory")
   SOCIALCATEGORY("socialcategory");

   private final String value;

   private CDITEMCARENETvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMCARENETvalues fromValue(String v) {
      CDITEMCARENETvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDITEMCARENETvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
