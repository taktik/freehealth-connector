package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEM-MYCARENETvalues"
)
@XmlEnum
public enum CDITEMMYCARENETvalues {
   @XmlEnumValue("fee")
   FEE("fee"),
   @XmlEnumValue("financialcontract")
   FINANCIALCONTRACT("financialcontract"),
   @XmlEnumValue("patientfee")
   PATIENTFEE("patientfee"),
   @XmlEnumValue("payment")
   PAYMENT("payment"),
   @XmlEnumValue("reimbursement")
   REIMBURSEMENT("reimbursement"),
   @XmlEnumValue("refusal")
   REFUSAL("refusal"),
   @XmlEnumValue("patientpaid")
   PATIENTPAID("patientpaid"),
   @XmlEnumValue("supplement")
   SUPPLEMENT("supplement"),
   @XmlEnumValue("paymentreceivingparty")
   PAYMENTRECEIVINGPARTY("paymentreceivingparty"),
   @XmlEnumValue("internship")
   INTERNSHIP("internship"),
   @XmlEnumValue("documentidentity")
   DOCUMENTIDENTITY("documentidentity"),
   @XmlEnumValue("invoicingnumber")
   INVOICINGNUMBER("invoicingnumber"),
   @XmlEnumValue("reimbursement-fpssi")
   REIMBURSEMENT_FPSSI("reimbursement-fpssi"),
   @XmlEnumValue("reimbursement-pswc")
   REIMBURSEMENT_PSWC("reimbursement-pswc"),
   @XmlEnumValue("umc")
   UMC("umc"),
   @XmlEnumValue("mediprimanumber")
   MEDIPRIMANUMBER("mediprimanumber"),
   @XmlEnumValue("pswc")
   PSWC("pswc"),
   @XmlEnumValue("treatmentreason")
   TREATMENTREASON("treatmentreason"),
   @XmlEnumValue("agreementenddate")
   AGREEMENTENDDATE("agreementenddate"),
   @XmlEnumValue("agreementstartdate")
   AGREEMENTSTARTDATE("agreementstartdate"),
   @XmlEnumValue("agreementtype")
   AGREEMENTTYPE("agreementtype"),
   @XmlEnumValue("consultationenddate")
   CONSULTATIONENDDATE("consultationenddate"),
   @XmlEnumValue("consultationstartdate")
   CONSULTATIONSTARTDATE("consultationstartdate"),
   @XmlEnumValue("authorisationtype")
   AUTHORISATIONTYPE("authorisationtype"),
   @XmlEnumValue("decisionreference")
   DECISIONREFERENCE("decisionreference"),
   @XmlEnumValue("orphandrugdeliveryplace")
   ORPHANDRUGDELIVERYPLACE("orphandrugdeliveryplace"),
   @XmlEnumValue("refusaljustification")
   REFUSALJUSTIFICATION("refusaljustification"),
   @XmlEnumValue("reststrength")
   RESTSTRENGTH("reststrength"),
   @XmlEnumValue("restunitnumber")
   RESTUNITNUMBER("restunitnumber"),
   @XmlEnumValue("legalbasis")
   LEGALBASIS("legalbasis"),
   @XmlEnumValue("legalunitnumber")
   LEGALUNITNUMBER("legalunitnumber"),
   @XmlEnumValue("legalstrength")
   LEGALSTRENGTH("legalstrength"),
   @XmlEnumValue("istrialperiod")
   ISTRIALPERIOD("istrialperiod"),
   @XmlEnumValue("closurejustification")
   CLOSUREJUSTIFICATION("closurejustification");

   private final String value;

   private CDITEMMYCARENETvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMMYCARENETvalues fromValue(String v) {
      CDITEMMYCARENETvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDITEMMYCARENETvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
