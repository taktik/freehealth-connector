package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-HCPARTY"
)
@XmlEnum
public enum CDHCPARTY {
   @XmlEnumValue("deptanatomopathology")
   DEPTANATOMOPATHOLOGY("deptanatomopathology"),
   @XmlEnumValue("deptanesthesiology")
   DEPTANESTHESIOLOGY("deptanesthesiology"),
   @XmlEnumValue("deptbacteriology")
   DEPTBACTERIOLOGY("deptbacteriology"),
   @XmlEnumValue("deptcardiology")
   DEPTCARDIOLOGY("deptcardiology"),
   @XmlEnumValue("deptdermatology")
   DEPTDERMATOLOGY("deptdermatology"),
   @XmlEnumValue("deptdietetics")
   DEPTDIETETICS("deptdietetics"),
   @XmlEnumValue("deptemergency")
   DEPTEMERGENCY("deptemergency"),
   @XmlEnumValue("deptgastroenterology")
   DEPTGASTROENTEROLOGY("deptgastroenterology"),
   @XmlEnumValue("deptgeneralpractice")
   DEPTGENERALPRACTICE("deptgeneralpractice"),
   @XmlEnumValue("deptgenetics")
   DEPTGENETICS("deptgenetics"),
   @XmlEnumValue("deptgeriatry")
   DEPTGERIATRY("deptgeriatry"),
   @XmlEnumValue("deptgynecology")
   DEPTGYNECOLOGY("deptgynecology"),
   @XmlEnumValue("depthematology")
   DEPTHEMATOLOGY("depthematology"),
   @XmlEnumValue("deptintensivecare")
   DEPTINTENSIVECARE("deptintensivecare"),
   @XmlEnumValue("deptkinesitherapy")
   DEPTKINESITHERAPY("deptkinesitherapy"),
   @XmlEnumValue("deptlaboratory")
   DEPTLABORATORY("deptlaboratory"),
   @XmlEnumValue("deptmedicine")
   DEPTMEDICINE("deptmedicine"),
   @XmlEnumValue("deptmolecularbiology")
   DEPTMOLECULARBIOLOGY("deptmolecularbiology"),
   @XmlEnumValue("deptneonatalogy")
   DEPTNEONATALOGY("deptneonatalogy"),
   @XmlEnumValue("deptnephrology")
   DEPTNEPHROLOGY("deptnephrology"),
   @XmlEnumValue("deptneurology")
   DEPTNEUROLOGY("deptneurology"),
   @XmlEnumValue("deptnte")
   DEPTNTE("deptnte"),
   @XmlEnumValue("deptnuclear")
   DEPTNUCLEAR("deptnuclear"),
   @XmlEnumValue("deptoncology")
   DEPTONCOLOGY("deptoncology"),
   @XmlEnumValue("deptophtalmology")
   DEPTOPHTALMOLOGY("deptophtalmology"),
   @XmlEnumValue("deptpediatry")
   DEPTPEDIATRY("deptpediatry"),
   @XmlEnumValue("deptpharmacy")
   DEPTPHARMACY("deptpharmacy"),
   @XmlEnumValue("deptphysiotherapy")
   DEPTPHYSIOTHERAPY("deptphysiotherapy"),
   @XmlEnumValue("deptpneumology")
   DEPTPNEUMOLOGY("deptpneumology"),
   @XmlEnumValue("deptpsychiatry")
   DEPTPSYCHIATRY("deptpsychiatry"),
   @XmlEnumValue("deptradiology")
   DEPTRADIOLOGY("deptradiology"),
   @XmlEnumValue("deptradiotherapy")
   DEPTRADIOTHERAPY("deptradiotherapy"),
   @XmlEnumValue("deptrhumatology")
   DEPTRHUMATOLOGY("deptrhumatology"),
   @XmlEnumValue("deptstomatology")
   DEPTSTOMATOLOGY("deptstomatology"),
   @XmlEnumValue("deptsurgery")
   DEPTSURGERY("deptsurgery"),
   @XmlEnumValue("depttoxicology")
   DEPTTOXICOLOGY("depttoxicology"),
   @XmlEnumValue("depturology")
   DEPTUROLOGY("depturology"),
   @XmlEnumValue("orghospital")
   ORGHOSPITAL("orghospital"),
   @XmlEnumValue("orginsurance")
   ORGINSURANCE("orginsurance"),
   @XmlEnumValue("orglaboratory")
   ORGLABORATORY("orglaboratory"),
   @XmlEnumValue("orgpublichealth")
   ORGPUBLICHEALTH("orgpublichealth"),
   @XmlEnumValue("persbiologist")
   PERSBIOLOGIST("persbiologist"),
   @XmlEnumValue("persdentist")
   PERSDENTIST("persdentist"),
   @XmlEnumValue("persnurse")
   PERSNURSE("persnurse"),
   @XmlEnumValue("persparamedical")
   PERSPARAMEDICAL("persparamedical"),
   @XmlEnumValue("perspharmacist")
   PERSPHARMACIST("perspharmacist"),
   @XmlEnumValue("persphysician")
   PERSPHYSICIAN("persphysician"),
   @XmlEnumValue("perssocialworker")
   PERSSOCIALWORKER("perssocialworker"),
   @XmlEnumValue("perstechnician")
   PERSTECHNICIAN("perstechnician"),
   @XmlEnumValue("persadministrative")
   PERSADMINISTRATIVE("persadministrative"),
   @XmlEnumValue("persmidwife")
   PERSMIDWIFE("persmidwife"),
   @XmlEnumValue("ecaresafe")
   ECARESAFE("ecaresafe"),
   @XmlEnumValue("application")
   APPLICATION("application"),
   @XmlEnumValue("hub")
   HUB("hub");

   private final String value;

   private CDHCPARTY(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDHCPARTY fromValue(String v) {
      CDHCPARTY[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDHCPARTY c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
