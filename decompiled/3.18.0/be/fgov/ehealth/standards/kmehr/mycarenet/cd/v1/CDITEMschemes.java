package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEMschemes"
)
@XmlEnum
public enum CDITEMschemes {
   @XmlEnumValue("CD-ITEM")
   CD_ITEM("CD-ITEM"),
   @XmlEnumValue("CD-ITEM-MAA")
   CD_ITEM_MAA("CD-ITEM-MAA"),
   @XmlEnumValue("CD-ITEM-CARENET")
   CD_ITEM_CARENET("CD-ITEM-CARENET"),
   @XmlEnumValue("CD-LAB")
   CD_LAB("CD-LAB"),
   @XmlEnumValue("CD-TECHNICAL")
   CD_TECHNICAL("CD-TECHNICAL"),
   @XmlEnumValue("CD-CONTACT-PERSON")
   CD_CONTACT_PERSON("CD-CONTACT-PERSON"),
   ICD("ICD"),
   ICPC("ICPC"),
   LOCAL("LOCAL"),
   @XmlEnumValue("CD-VACCINE")
   CD_VACCINE("CD-VACCINE"),
   @XmlEnumValue("CD-ECG")
   CD_ECG("CD-ECG"),
   @XmlEnumValue("CD-ECARE-CLINICAL")
   CD_ECARE_CLINICAL("CD-ECARE-CLINICAL"),
   @XmlEnumValue("CD-ECARE-LAB")
   CD_ECARE_LAB("CD-ECARE-LAB"),
   @XmlEnumValue("CD-ECARE-HAQ")
   CD_ECARE_HAQ("CD-ECARE-HAQ"),
   @XmlEnumValue("CD-ITEM-EBIRTH")
   CD_ITEM_EBIRTH("CD-ITEM-EBIRTH"),
   @XmlEnumValue("CD-PARAMETER")
   CD_PARAMETER("CD-PARAMETER"),
   @XmlEnumValue("CD-ITEM-BVT")
   CD_ITEM_BVT("CD-ITEM-BVT"),
   @XmlEnumValue("CD-BVT-AVAILABLEMATERIALS")
   CD_BVT_AVAILABLEMATERIALS("CD-BVT-AVAILABLEMATERIALS"),
   @XmlEnumValue("CD-BVT-CONSERVATIONDELAY")
   CD_BVT_CONSERVATIONDELAY("CD-BVT-CONSERVATIONDELAY"),
   @XmlEnumValue("CD-BVT-CONSERVATIONMODE")
   CD_BVT_CONSERVATIONMODE("CD-BVT-CONSERVATIONMODE"),
   @XmlEnumValue("CD-BVT-SAMPLETYPE")
   CD_BVT_SAMPLETYPE("CD-BVT-SAMPLETYPE"),
   @XmlEnumValue("CD-BCR-DIFFERENTATIONDEGREE")
   CD_BCR_DIFFERENTATIONDEGREE("CD-BCR-DIFFERENTATIONDEGREE"),
   @XmlEnumValue("CD-BVT-LATERALITY")
   CD_BVT_LATERALITY("CD-BVT-LATERALITY"),
   @XmlEnumValue("CD-BVT-PATIENTOPPOSITION")
   CD_BVT_PATIENTOPPOSITION("CD-BVT-PATIENTOPPOSITION"),
   @XmlEnumValue("CD-BVT-STATUS")
   CD_BVT_STATUS("CD-BVT-STATUS"),
   @XmlEnumValue("CD-ITEM-REG")
   CD_ITEM_REG("CD-ITEM-REG"),
   @XmlEnumValue("CD-ITEM-MYCARENET")
   CD_ITEM_MYCARENET("CD-ITEM-MYCARENET"),
   @XmlEnumValue("CD-DEFIB-DIAGNOSIS")
   CD_DEFIB_DIAGNOSIS("CD-DEFIB-DIAGNOSIS"),
   @XmlEnumValue("CD-ACTS-NURSING")
   CD_ACTS_NURSING("CD-ACTS-NURSING"),
   @XmlEnumValue("CD-QERMID-INTERVENTIONTYPE")
   CD_QERMID_INTERVENTIONTYPE("CD-QERMID-INTERVENTIONTYPE");

   private final String value;

   private CDITEMschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMschemes fromValue(String v) {
      CDITEMschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDITEMschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
