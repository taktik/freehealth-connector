package be.ehealth.businessconnector.therlink.builders.impl;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.common.util.EidUtils;
import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.therlink.builders.CommonObjectBuilder;
import be.ehealth.businessconnector.therlink.builders.ProofBuilder;
import be.ehealth.businessconnector.therlink.builders.RequestObjectBuilderFactory;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.domain.ProofTypeValues;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.businessconnector.therlink.domain.requests.BinaryProof;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.therlink.mappers.MapperFactory;
import be.ehealth.businessconnector.therlink.mappers.PatientMapper;
import be.ehealth.businessconnector.therlink.mappers.RequestObjectMapper;
import be.ehealth.businessconnector.therlink.util.ConfigReader;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProofBuilderImpl implements ProofBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(ProofBuilderImpl.class);
   private static final String CMS = "CMS";

   public ProofBuilderImpl() {
   }

   public Proof createProofForEidSigning(Credential cred) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      Proof proof = new Proof(ProofTypeValues.EIDSIGNING.getValue());

      try {
         TherapeuticLink therapeuticLink = this.createMandateTherapeuticLinkForProof();
         this.addSignature(cred, proof, therapeuticLink);
         return proof;
      } catch (Exception var5) {
         TherLinkBusinessConnectorException therLinkBusinessConnectorException = new TherLinkBusinessConnectorException(TherLinkBusinessConnectorExceptionValues.ERROR_CREATEPROOF, new Object[]{var5.getMessage(), var5});
         LOG.error(therLinkBusinessConnectorException.getMessage());
         throw therLinkBusinessConnectorException;
      }
   }

   private void addSignature(Credential cred, Proof proof, TherapeuticLink therapeuticLink) throws TherLinkBusinessConnectorException, TechnicalConnectorException {
      RequestObjectMapper requestObjectMapper = MapperFactory.getRequestObjectMapper();
      String contentToSign = requestObjectMapper.createTherapeuticLinkAsXmlString(therapeuticLink);
      SignatureBuilder signatureBuilder = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.CAdES);
      Map<String, Object> options = new HashMap();
      options.put("encapsulate", Boolean.TRUE);
      byte[] signatureBytes = signatureBuilder.sign(cred, contentToSign.getBytes(), options);
      BinaryProof binaryProof = new BinaryProof("CMS", signatureBytes);
      proof.setBinaryProof(binaryProof);
   }

   public Proof createProofForEidReading() {
      Proof proof = new Proof(ProofTypeValues.EIDREADING.getValue());
      proof.setBinaryProof((BinaryProof)null);
      return proof;
   }

   public Proof createProofForSisReading() {
      Proof proof = new Proof(ProofTypeValues.SISREADING.getValue());
      proof.setBinaryProof((BinaryProof)null);
      return proof;
   }

   public Proof createProofForIsiReading() {
      Proof proof = new Proof(ProofTypeValues.ISIREADING.getValue());
      proof.setBinaryProof((BinaryProof)null);
      return proof;
   }

   private TherapeuticLink createMandateTherapeuticLinkForProof() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      CommonObjectBuilder commonBuilder = RequestObjectBuilderFactory.getCommonBuilder();
      Patient patient = PatientMapper.mapPatient(EidUtils.readFromEidCard());
      HcParty hcp = ConfigReader.getCareProvider();
      return commonBuilder.createTherapeuticLink((DateTime)(new DateTime()), (DateTime)(new DateTime()).plusMinutes(5), (Patient)patient, (String)HcPartyUtil.getAuthorKmehrQuality(), (String)"ignored", (String)null, hcp);
   }

   public Proof createSimpleProof(String proofType) {
      return new Proof(proofType);
   }
}
