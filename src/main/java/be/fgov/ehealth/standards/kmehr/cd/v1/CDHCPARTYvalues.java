package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-HCPARTYvalues"
)
@XmlEnum
public enum CDHCPARTYvalues {
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
   @XmlEnumValue("deptrheumatology")
   DEPTRHEUMATOLOGY("deptrheumatology"),
   @XmlEnumValue("deptstomatology")
   DEPTSTOMATOLOGY("deptstomatology"),
   @XmlEnumValue("deptsurgery")
   DEPTSURGERY("deptsurgery"),
   @XmlEnumValue("depturology")
   DEPTUROLOGY("depturology"),
   @XmlEnumValue("orghospital")
   ORGHOSPITAL("orghospital"),
   @XmlEnumValue("orginsurance")
   ORGINSURANCE("orginsurance"),
   @XmlEnumValue("orglaboratory")
   ORGLABORATORY("orglaboratory"),
   @XmlEnumValue("orgpractice")
   ORGPRACTICE("orgpractice"),
   @XmlEnumValue("orgpublichealth")
   ORGPUBLICHEALTH("orgpublichealth"),
   @XmlEnumValue("orgpharmacy")
   ORGPHARMACY("orgpharmacy"),
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
   HUB("hub"),
   @XmlEnumValue("deptorthopedy")
   DEPTORTHOPEDY("deptorthopedy"),
   @XmlEnumValue("deptalgology")
   DEPTALGOLOGY("deptalgology"),
   @XmlEnumValue("deptcardiacsurgery")
   DEPTCARDIACSURGERY("deptcardiacsurgery"),
   @XmlEnumValue("depthandsurgery")
   DEPTHANDSURGERY("depthandsurgery"),
   @XmlEnumValue("deptmaxillofacialsurgery")
   DEPTMAXILLOFACIALSURGERY("deptmaxillofacialsurgery"),
   @XmlEnumValue("deptpediatricsurgery")
   DEPTPEDIATRICSURGERY("deptpediatricsurgery"),
   @XmlEnumValue("deptplasticandreparatorysurgery")
   DEPTPLASTICANDREPARATORYSURGERY("deptplasticandreparatorysurgery"),
   @XmlEnumValue("deptthoracicsurgery")
   DEPTTHORACICSURGERY("deptthoracicsurgery"),
   @XmlEnumValue("deptvascularsurgery")
   DEPTVASCULARSURGERY("deptvascularsurgery"),
   @XmlEnumValue("deptvisceraldigestiveabdominalsurgery")
   DEPTVISCERALDIGESTIVEABDOMINALSURGERY("deptvisceraldigestiveabdominalsurgery"),
   @XmlEnumValue("deptdentistry")
   DEPTDENTISTRY("deptdentistry"),
   @XmlEnumValue("deptdiabetology")
   DEPTDIABETOLOGY("deptdiabetology"),
   @XmlEnumValue("deptendocrinology")
   DEPTENDOCRINOLOGY("deptendocrinology"),
   @XmlEnumValue("deptoccupationaltherapy")
   DEPTOCCUPATIONALTHERAPY("deptoccupationaltherapy"),
   @XmlEnumValue("deptmajorburns")
   DEPTMAJORBURNS("deptmajorburns"),
   @XmlEnumValue("deptinfectiousdisease")
   DEPTINFECTIOUSDISEASE("deptinfectiousdisease"),
   @XmlEnumValue("deptspeechtherapy")
   DEPTSPEECHTHERAPY("deptspeechtherapy"),
   @XmlEnumValue("deptsportsmedecine")
   DEPTSPORTSMEDECINE("deptsportsmedecine"),
   @XmlEnumValue("deptphysicalmedecine")
   DEPTPHYSICALMEDECINE("deptphysicalmedecine"),
   @XmlEnumValue("depttropicalmedecine")
   DEPTTROPICALMEDECINE("depttropicalmedecine"),
   @XmlEnumValue("deptneurosurgery")
   DEPTNEUROSURGERY("deptneurosurgery"),
   @XmlEnumValue("deptnutritiondietetics")
   DEPTNUTRITIONDIETETICS("deptnutritiondietetics"),
   @XmlEnumValue("deptobstetrics")
   DEPTOBSTETRICS("deptobstetrics"),
   @XmlEnumValue("deptchildandadolescentpsychiatry")
   DEPTCHILDANDADOLESCENTPSYCHIATRY("deptchildandadolescentpsychiatry"),
   @XmlEnumValue("deptpodiatry")
   DEPTPODIATRY("deptpodiatry"),
   @XmlEnumValue("deptpsychology")
   DEPTPSYCHOLOGY("deptpsychology"),
   @XmlEnumValue("deptrevalidation")
   DEPTREVALIDATION("deptrevalidation"),
   @XmlEnumValue("deptsenology")
   DEPTSENOLOGY("deptsenology"),
   @XmlEnumValue("deptsocialservice")
   DEPTSOCIALSERVICE("deptsocialservice"),
   @XmlEnumValue("deptpediatricintensivecare")
   DEPTPEDIATRICINTENSIVECARE("deptpediatricintensivecare"),
   @XmlEnumValue("deptpalliativecare")
   DEPTPALLIATIVECARE("deptpalliativecare"),
   @XmlEnumValue("depttoxicology")
   DEPTTOXICOLOGY("depttoxicology"),
   @XmlEnumValue("persambulancefirstaid")
   PERSAMBULANCEFIRSTAID("persambulancefirstaid"),
   @XmlEnumValue("persaudician")
   PERSAUDICIAN("persaudician"),
   @XmlEnumValue("persaudiologist")
   PERSAUDIOLOGIST("persaudiologist"),
   @XmlEnumValue("perscaregiver")
   PERSCAREGIVER("perscaregiver"),
   @XmlEnumValue("persdietician")
   PERSDIETICIAN("persdietician"),
   @XmlEnumValue("perseducator")
   PERSEDUCATOR("perseducator"),
   @XmlEnumValue("perslogopedist")
   PERSLOGOPEDIST("perslogopedist"),
   @XmlEnumValue("persoccupationaltherapist")
   PERSOCCUPATIONALTHERAPIST("persoccupationaltherapist"),
   @XmlEnumValue("persorthopedist")
   PERSORTHOPEDIST("persorthopedist"),
   @XmlEnumValue("persorthoptist")
   PERSORTHOPTIST("persorthoptist"),
   @XmlEnumValue("persoptician")
   PERSOPTICIAN("persoptician"),
   @XmlEnumValue("perspharmacyassistant")
   PERSPHARMACYASSISTANT("perspharmacyassistant"),
   @XmlEnumValue("persphysiotherapist")
   PERSPHYSIOTHERAPIST("persphysiotherapist"),
   @XmlEnumValue("perspodologist")
   PERSPODOLOGIST("perspodologist"),
   @XmlEnumValue("perspracticalnurse")
   PERSPRACTICALNURSE("perspracticalnurse"),
   @XmlEnumValue("perspsychologist")
   PERSPSYCHOLOGIST("perspsychologist"),
   @XmlEnumValue("orgprimaryhealthcarecenter")
   ORGPRIMARYHEALTHCARECENTER("orgprimaryhealthcarecenter"),
   @XmlEnumValue("guardpost")
   GUARDPOST("guardpost"),
   @XmlEnumValue("groupofphysicianssameaddress")
   GROUPOFPHYSICIANSSAMEADDRESS("groupofphysicianssameaddress"),
   @XmlEnumValue("groupofphysiciansdifferentaddress")
   GROUPOFPHYSICIANSDIFFERENTADDRESS("groupofphysiciansdifferentaddress"),
   @XmlEnumValue("groupofnurses")
   GROUPOFNURSES("groupofnurses"),
   @XmlEnumValue("certificateholder")
   CERTIFICATEHOLDER("certificateholder"),
   @XmlEnumValue("perstrussmaker")
   PERSTRUSSMAKER("perstrussmaker"),
   @XmlEnumValue("patient")
   PATIENT("patient"),
   @XmlEnumValue("orgprevention")
   ORGPREVENTION("orgprevention"),
   @XmlEnumValue("persorthotist")
   PERSORTHOTIST("persorthotist"),
   @XmlEnumValue("perspatientdriver")
   PERSPATIENTDRIVER("perspatientdriver"),
   @XmlEnumValue("perspharmacistclinicalbiologist")
   PERSPHARMACISTCLINICALBIOLOGIST("perspharmacistclinicalbiologist"),
   @XmlEnumValue("deptnursing")
   DEPTNURSING("deptnursing"),
   @XmlEnumValue("persoraldentalhygienist")
   PERSORALDENTALHYGIENIST("persoraldentalhygienist"),
   @XmlEnumValue("persclinicalpsychologist")
   PERSCLINICALPSYCHOLOGIST("persclinicalpsychologist"),
   @XmlEnumValue("persclinicalorthopedagogist")
   PERSCLINICALORTHOPEDAGOGIST("persclinicalorthopedagogist");

   private final String value;

   private CDHCPARTYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDHCPARTYvalues fromValue(String v) {
      CDHCPARTYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDHCPARTYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
