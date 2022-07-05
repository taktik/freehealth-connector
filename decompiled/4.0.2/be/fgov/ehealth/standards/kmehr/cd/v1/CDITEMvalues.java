package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEMvalues"
)
@XmlEnum
public enum CDITEMvalues {
   @XmlEnumValue("admissiontype")
   ADMISSIONTYPE("admissiontype"),
   @XmlEnumValue("adr")
   ADR("adr"),
   @XmlEnumValue("allergy")
   ALLERGY("allergy"),
   @XmlEnumValue("autonomy")
   AUTONOMY("autonomy"),
   @XmlEnumValue("bloodtransfusionrefusal")
   BLOODTRANSFUSIONREFUSAL("bloodtransfusionrefusal"),
   @XmlEnumValue("clinical")
   CLINICAL("clinical"),
   @XmlEnumValue("complaint")
   COMPLAINT("complaint"),
   @XmlEnumValue("complementaryproduct")
   COMPLEMENTARYPRODUCT("complementaryproduct"),
   @XmlEnumValue("conclusion")
   CONCLUSION("conclusion"),
   @XmlEnumValue("contactperson")
   CONTACTPERSON("contactperson"),
   @XmlEnumValue("dischargedatetime")
   DISCHARGEDATETIME("dischargedatetime"),
   @XmlEnumValue("dischargedestination")
   DISCHARGEDESTINATION("dischargedestination"),
   @XmlEnumValue("dischargetype")
   DISCHARGETYPE("dischargetype"),
   @XmlEnumValue("emergencyevaluation")
   EMERGENCYEVALUATION("emergencyevaluation"),
   @XmlEnumValue("encounterdatetime")
   ENCOUNTERDATETIME("encounterdatetime"),
   @XmlEnumValue("encounterlegalservice")
   ENCOUNTERLEGALSERVICE("encounterlegalservice"),
   @XmlEnumValue("encounterlocation")
   ENCOUNTERLOCATION("encounterlocation"),
   @XmlEnumValue("encounterresponsible")
   ENCOUNTERRESPONSIBLE("encounterresponsible"),
   @XmlEnumValue("encountersafetyissue")
   ENCOUNTERSAFETYISSUE("encountersafetyissue"),
   @XmlEnumValue("encountertype")
   ENCOUNTERTYPE("encountertype"),
   @XmlEnumValue("evolution")
   EVOLUTION("evolution"),
   @XmlEnumValue("expirationdatetime")
   EXPIRATIONDATETIME("expirationdatetime"),
   @XmlEnumValue("gmdmanager")
   GMDMANAGER("gmdmanager"),
   @XmlEnumValue("habit")
   HABIT("habit"),
   @XmlEnumValue("hcpartyavailability")
   HCPARTYAVAILABILITY("hcpartyavailability"),
   @XmlEnumValue("healthcareelement")
   HEALTHCAREELEMENT("healthcareelement"),
   @XmlEnumValue("healthissue")
   HEALTHISSUE("healthissue"),
   @XmlEnumValue("incapacity")
   INCAPACITY("incapacity"),
   @XmlEnumValue("lab")
   LAB("lab"),
   @XmlEnumValue("medication")
   MEDICATION("medication"),
   @XmlEnumValue("ntbr")
   NTBR("ntbr"),
   @XmlEnumValue("referrer")
   REFERRER("referrer"),
   @XmlEnumValue("referringtype")
   REFERRINGTYPE("referringtype"),
   @XmlEnumValue("reimbursementcertificate")
   REIMBURSEMENTCERTIFICATE("reimbursementcertificate"),
   @XmlEnumValue("requestdatetime")
   REQUESTDATETIME("requestdatetime"),
   @XmlEnumValue("requesteddecisionsharing")
   REQUESTEDDECISIONSHARING("requesteddecisionsharing"),
   @XmlEnumValue("requesteddischargedestination")
   REQUESTEDDISCHARGEDESTINATION("requesteddischargedestination"),
   @XmlEnumValue("requestedencountertype")
   REQUESTEDENCOUNTERTYPE("requestedencountertype"),
   @XmlEnumValue("requestedrecipient")
   REQUESTEDRECIPIENT("requestedrecipient"),
   @XmlEnumValue("requestnumber")
   REQUESTNUMBER("requestnumber"),
   @XmlEnumValue("requestor")
   REQUESTOR("requestor"),
   @XmlEnumValue("risk")
   RISK("risk"),
   @XmlEnumValue("socialrisk")
   SOCIALRISK("socialrisk"),
   @XmlEnumValue("specimendatetime")
   SPECIMENDATETIME("specimendatetime"),
   @XmlEnumValue("technical")
   TECHNICAL("technical"),
   @XmlEnumValue("transactionreason")
   TRANSACTIONREASON("transactionreason"),
   @XmlEnumValue("transcriptionist")
   TRANSCRIPTIONIST("transcriptionist"),
   @XmlEnumValue("transferdatetime")
   TRANSFERDATETIME("transferdatetime"),
   @XmlEnumValue("treatment")
   TREATMENT("treatment"),
   @XmlEnumValue("vaccine")
   VACCINE("vaccine"),
   @XmlEnumValue("actionplan")
   ACTIONPLAN("actionplan"),
   @XmlEnumValue("acts")
   ACTS("acts"),
   @XmlEnumValue("careplansubscription")
   CAREPLANSUBSCRIPTION("careplansubscription"),
   @XmlEnumValue("contacthcparty")
   CONTACTHCPARTY("contacthcparty"),
   @XmlEnumValue("diagnosis")
   DIAGNOSIS("diagnosis"),
   @XmlEnumValue("familyrisk")
   FAMILYRISK("familyrisk"),
   @XmlEnumValue("healthcareapproach")
   HEALTHCAREAPPROACH("healthcareapproach"),
   @XmlEnumValue("insurancystatus")
   INSURANCYSTATUS("insurancystatus"),
   @XmlEnumValue("memberinsurancystatus")
   MEMBERINSURANCYSTATUS("memberinsurancystatus"),
   @XmlEnumValue("parameter")
   PARAMETER("parameter"),
   @XmlEnumValue("patientwill")
   PATIENTWILL("patientwill"),
   @XmlEnumValue("professionalrisk")
   PROFESSIONALRISK("professionalrisk"),
   @XmlEnumValue("encounternumber")
   ENCOUNTERNUMBER("encounternumber"),
   @XmlEnumValue("claim")
   CLAIM("claim"),
   @XmlEnumValue("outcome")
   OUTCOME("outcome"),
   @XmlEnumValue("agreementwithpatient")
   AGREEMENTWITHPATIENT("agreementwithpatient"),
   @XmlEnumValue("patientcooperation")
   PATIENTCOOPERATION("patientcooperation"),
   @XmlEnumValue("reimbursementclass")
   REIMBURSEMENTCLASS("reimbursementclass"),
   @XmlEnumValue("financialcontract")
   FINANCIALCONTRACT("financialcontract"),
   @XmlEnumValue("justification")
   JUSTIFICATION("justification"),
   @XmlEnumValue("result")
   RESULT("result"),
   @XmlEnumValue("agreedtreatment")
   AGREEDTREATMENT("agreedtreatment"),
   @XmlEnumValue("membership")
   MEMBERSHIP("membership"),
   @XmlEnumValue("problem")
   PROBLEM("problem");

   private final String value;

   private CDITEMvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMvalues fromValue(String v) {
      CDITEMvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDITEMvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
