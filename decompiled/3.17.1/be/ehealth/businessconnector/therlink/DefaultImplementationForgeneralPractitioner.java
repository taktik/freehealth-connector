package be.ehealth.businessconnector.therlink;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.common.util.EidUtils;
import be.ehealth.businessconnector.therlink.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.therlink.builders.RequestObjectBuilderFactory;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.domain.requests.PutTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.therlink.mappers.PatientMapper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.impl.BeIDCredential;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

public final class DefaultImplementationForgeneralPractitioner {
   private static final String GPCONSULTATION = "gpconsultation";

   private DefaultImplementationForgeneralPractitioner() {
   }

   public static PutTherapeuticLinkRequest createDefaultTherapeuticLinkWithEidReading() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      Proof proof = RequestObjectBuilderFactory.getProofBuilder().createProofForEidReading();
      return createDefaultPutTherapeuticLinkRequestWithProof(proof);
   }

   private static PutTherapeuticLinkRequest createDefaultPutTherapeuticLinkRequestWithProof(Proof proof) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      Patient patient = PatientMapper.mapPatient(EidUtils.readFromEidCard());
      return createDefaultPutTherapeuticLinkRequestWithProofAndPatient(proof, patient);
   }

   private static PutTherapeuticLinkRequest createDefaultPutTherapeuticLinkRequestWithProofAndPatient(Proof proof, Patient patient) throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      RequestObjectBuilder requestObjectBuilder = RequestObjectBuilderFactory.getRequestObjectBuilder();
      return requestObjectBuilder.createPutTherapeuticLinkRequest((DateTime)null, (Patient)patient, (HcParty)null, "gpconsultation", (String)null, proof);
   }

   public static PutTherapeuticLinkRequest createDefaultTherapeuticLinkWithEidSigning() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      Proof proof = RequestObjectBuilderFactory.getProofBuilder().createProofForEidSigning(BeIDCredential.getInstance("doctor", "Signature"));
      return createDefaultPutTherapeuticLinkRequestWithProof(proof);
   }

   public static PutTherapeuticLinkRequest createDefaultTherapeuticLinkWithSisReading(Patient patient) throws TherLinkBusinessConnectorException, TechnicalConnectorException, InstantiationException {
      if (StringUtils.isEmpty(patient.getSisCardNumber())) {
         throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"the sis card number field of the patient should be filled out"});
      } else if (patient.getInss() == null) {
         throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"the niss number field of the patient should be filled out"});
      } else {
         Proof proof = RequestObjectBuilderFactory.getProofBuilder().createProofForEidReading();
         return createDefaultPutTherapeuticLinkRequestWithProofAndPatient(proof, patient);
      }
   }

   public static PutTherapeuticLinkRequest createDefaultTherapeuticLinkWithIsiReading(Patient patient) throws TherLinkBusinessConnectorException, TechnicalConnectorException, InstantiationException {
      if (StringUtils.isEmpty(patient.getIsiCardNumber())) {
         throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"the isi + card number field of the patient should be filled out"});
      } else if (patient.getInss() == null) {
         throw new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.REQUIRED_FIELD_NULL, new Object[]{"the niss number field of the patient should be filled out"});
      } else {
         Proof proof = RequestObjectBuilderFactory.getProofBuilder().createProofForIsiReading();
         return createDefaultPutTherapeuticLinkRequestWithProofAndPatient(proof, patient);
      }
   }
}
