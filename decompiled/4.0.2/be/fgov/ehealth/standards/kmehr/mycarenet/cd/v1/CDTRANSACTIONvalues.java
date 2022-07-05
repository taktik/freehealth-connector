package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TRANSACTIONvalues"
)
@XmlEnum
public enum CDTRANSACTIONvalues {
   @XmlEnumValue("admission")
   ADMISSION("admission"),
   @XmlEnumValue("alert")
   ALERT("alert"),
   @XmlEnumValue("contactreport")
   CONTACTREPORT("contactreport"),
   @XmlEnumValue("death")
   DEATH("death"),
   @XmlEnumValue("discharge")
   DISCHARGE("discharge"),
   @XmlEnumValue("dischargereport")
   DISCHARGEREPORT("dischargereport"),
   @XmlEnumValue("epidemiology")
   EPIDEMIOLOGY("epidemiology"),
   @XmlEnumValue("labrequest")
   LABREQUEST("labrequest"),
   @XmlEnumValue("labresult")
   LABRESULT("labresult"),
   @XmlEnumValue("note")
   NOTE("note"),
   @XmlEnumValue("hospitalpharmaceuticalprescription")
   HOSPITALPHARMACEUTICALPRESCRIPTION("hospitalpharmaceuticalprescription"),
   @XmlEnumValue("pharmaceuticalprescription")
   PHARMACEUTICALPRESCRIPTION("pharmaceuticalprescription"),
   @XmlEnumValue("productdelivery")
   PRODUCTDELIVERY("productdelivery"),
   @XmlEnumValue("quickdischargereport")
   QUICKDISCHARGEREPORT("quickdischargereport"),
   @XmlEnumValue("referral")
   REFERRAL("referral"),
   @XmlEnumValue("request")
   REQUEST("request"),
   @XmlEnumValue("result")
   RESULT("result"),
   @XmlEnumValue("vaccination")
   VACCINATION("vaccination"),
   @XmlEnumValue("sumehr")
   SUMEHR("sumehr"),
   @XmlEnumValue("ecare-safe-consultation")
   ECARE_SAFE_CONSULTATION("ecare-safe-consultation"),
   @XmlEnumValue("ebirth-mother-notification")
   EBIRTH_MOTHER_NOTIFICATION("ebirth-mother-notification"),
   @XmlEnumValue("ebirth-baby-notification")
   EBIRTH_BABY_NOTIFICATION("ebirth-baby-notification"),
   @XmlEnumValue("ebirth-baby-medicalform")
   EBIRTH_BABY_MEDICALFORM("ebirth-baby-medicalform"),
   @XmlEnumValue("ebirth-mother-medicalform")
   EBIRTH_MOTHER_MEDICALFORM("ebirth-mother-medicalform"),
   @XmlEnumValue("clinicalsummary")
   CLINICALSUMMARY("clinicalsummary"),
   @XmlEnumValue("medicaladvisoragreement")
   MEDICALADVISORAGREEMENT("medicaladvisoragreement"),
   @XmlEnumValue("bvt-sample")
   BVT_SAMPLE("bvt-sample"),
   @XmlEnumValue("clinicalpath")
   CLINICALPATH("clinicalpath"),
   @XmlEnumValue("telemonitoring")
   TELEMONITORING("telemonitoring"),
   @XmlEnumValue("radiationexposuremonitoring")
   RADIATIONEXPOSUREMONITORING("radiationexposuremonitoring"),
   @XmlEnumValue("intervention")
   INTERVENTION("intervention"),
   @XmlEnumValue("medicationschemeelement")
   MEDICATIONSCHEMEELEMENT("medicationschemeelement"),
   @XmlEnumValue("treatmentsuspension")
   TREATMENTSUSPENSION("treatmentsuspension"),
   @XmlEnumValue("prescription")
   PRESCRIPTION("prescription"),
   @XmlEnumValue("notification")
   NOTIFICATION("notification"),
   @XmlEnumValue("report")
   REPORT("report"),
   @XmlEnumValue("medicationscheme")
   MEDICATIONSCHEME("medicationscheme"),
   @XmlEnumValue("vaccinationscheme")
   VACCINATIONSCHEME("vaccinationscheme"),
   @XmlEnumValue("vaccinationschemeelement")
   VACCINATIONSCHEMEELEMENT("vaccinationschemeelement"),
   @XmlEnumValue("ecare-tardis-consultation")
   ECARE_TARDIS_CONSULTATION("ecare-tardis-consultation"),
   @XmlEnumValue("genericregistryentry")
   GENERICREGISTRYENTRY("genericregistryentry"),
   @XmlEnumValue("population-based-screening")
   POPULATION_BASED_SCREENING("population-based-screening"),
   @XmlEnumValue("child-prevention")
   CHILD_PREVENTION("child-prevention"),
   @XmlEnumValue("careplan")
   CAREPLAN("careplan"),
   @XmlEnumValue("applicationlink")
   APPLICATIONLINK("applicationlink"),
   @XmlEnumValue("diarynote")
   DIARYNOTE("diarynote"),
   @XmlEnumValue("mea")
   MEA("mea"),
   @XmlEnumValue("perinatal")
   PERINATAL("perinatal"),
   @XmlEnumValue("belrai-report")
   BELRAI_REPORT("belrai-report"),
   @XmlEnumValue("nursingsummaryreport")
   NURSINGSUMMARYREPORT("nursingsummaryreport"),
   @XmlEnumValue("pharmaceuticalmedicationscheme")
   PHARMACEUTICALMEDICATIONSCHEME("pharmaceuticalmedicationscheme");

   private final String value;

   private CDTRANSACTIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTRANSACTIONvalues fromValue(String v) {
      CDTRANSACTIONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTRANSACTIONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
