package be.ehealth.businessconnector.therlink.mappers;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLinkRequestType;
import be.ehealth.businessconnector.therlink.domain.jaxb.Therapeuticlink;
import be.ehealth.businessconnector.therlink.domain.requests.BinaryProof;
import be.ehealth.businessconnector.therlink.domain.requests.GetTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.HasTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.PutTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.RevokeTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType;
import be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkSelectType;
import be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkSelectType;
import be.fgov.ehealth.hubservices.core.v2.PatientIdType;
import be.fgov.ehealth.hubservices.core.v2.ProofType;
import be.fgov.ehealth.hubservices.core.v2.RequestType;
import be.fgov.ehealth.hubservices.core.v2.TherapeuticLinkType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONMETHOD;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONMETHODschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDENCRYPTIONMETHODvalues;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDPROOF;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDPROOFschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTHERAPEUTICLINK;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTHERAPEUTICLINKschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.Base64EncryptedDataType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Base64EncryptedValueType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestObjectMapper {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectMapper.class);
   private static final int MAXROWS = 999;
   private static final String IDVERSION = "1.0";
   private static final String CDVERSION = "1.0";
   private static final String INPUTPRESENTATION = "Input request object :";

   public String mapPutTherapeuticLinkToXML(PutTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      LOG.info("Input request object :" + request.toString());
      be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest jaxbRequest = this.mapPutTherapeuticLinkRequest(request);
      return this.generateXML(jaxbRequest, be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest.class);
   }

   public String mapRevokeTherapeuticLinkToXML(RevokeTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      return this.generateXML(this.mapRevokeTherapeuticLinkRequest(request), be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest.class);
   }

   public String mapHasTherapeuticLinkToXML(HasTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      return this.generateXML(this.mapHasTherapeuticLinkRequest(request), be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest.class);
   }

   public String mapGetTherapeuticLinkToXml(GetTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      return this.generateXML(this.mapGetTherapeuticLinkRequest(request), be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest.class);
   }

   public be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest mapPutTherapeuticLinkRequest(PutTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      LOG.info("Input request object :" + request.toString());
      be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest jaxbRequest = new be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkRequest();
      jaxbRequest.setRequest(this.mapRequest(request.getDateTime(), request.getID(), request.getAuthor(), 0));
      jaxbRequest.setTherapeuticlink(this.mapTherapeuticLinkType(request.getLink()));
      jaxbRequest.getProoves().addAll(this.mapProoves(request.getProofs()));
      return jaxbRequest;
   }

   public be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest mapGetTherapeuticLinkRequest(GetTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      LOG.info("Input request object :" + request.toString());
      be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest jaxbRequest = new be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkRequest();
      int maxRowsToUse = 999;
      if (request.getMaxRows() != 0) {
         maxRowsToUse = request.getMaxRows();
      }

      jaxbRequest.setRequest(this.mapRequest(request.getDateTime(), request.getID(), request.getAuthor(), maxRowsToUse));
      jaxbRequest.setSelect(this.mapGetTherapeuticLinkSelectType(request.getLink()));
      jaxbRequest.getProoves().addAll(this.mapProoves(request.getProofs()));
      return jaxbRequest;
   }

   public be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest mapHasTherapeuticLinkRequest(HasTherapeuticLinkRequest request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      LOG.info("Input request object :" + request.toString());
      be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest jaxbRequest = new be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkRequest();
      jaxbRequest.setRequest(this.mapRequest(request.getDateTime(), request.getID(), request.getAuthor(), 0));
      jaxbRequest.setSelect(this.mapHasTherapeuticLinkSelectType(request.getLink()));
      return jaxbRequest;
   }

   public be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest mapRevokeTherapeuticLinkRequest(TherapeuticLinkRequestType request) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      LOG.info("Input request object :" + request.toString());
      be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest jaxbRequest = new be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkRequest();
      jaxbRequest.setRequest(this.mapRequest(request.getDateTime(), request.getID(), request.getAuthor(), 0));
      jaxbRequest.setTherapeuticlink(this.mapTherapeuticLinkType(request.getLink()));
      jaxbRequest.getProoves().addAll(this.mapProoves(request.getProofs()));
      return jaxbRequest;
   }

   private List<ProofType> mapProoves(List<Proof> proofs) {
      List<ProofType> listOfProofType = new ArrayList();
      Iterator i$ = proofs.iterator();

      while(i$.hasNext()) {
         Proof proof = (Proof)i$.next();
         if (proof != null) {
            listOfProofType.add(this.mapProof(proof));
         }
      }

      return listOfProofType;
   }

   private ProofType mapProof(Proof proof) {
      ProofType proofType = new ProofType();
      if (proof.getBinaryProof() != null) {
         proofType.setBinaryproof(this.mapBase64EncryptedDataType(proof.getBinaryProof()));
      }

      CDPROOF cdProof = this.createProofType(proof.getType());
      proofType.setCd(cdProof);
      return proofType;
   }

   private Base64EncryptedDataType mapBase64EncryptedDataType(BinaryProof binaryProof) {
      Base64EncryptedDataType base64EncryptedDataType = new Base64EncryptedDataType();
      CDENCRYPTIONMETHOD cdEncryptionMethod = this.createEncryptionMethodType(binaryProof.getMethod());
      base64EncryptedDataType.setCd(cdEncryptionMethod);
      Base64EncryptedValueType value = new Base64EncryptedValueType();
      value.setEncoding(binaryProof.getMethod());
      value.setValue(binaryProof.getBinary());
      base64EncryptedDataType.setBase64EncryptedValue(value);
      return base64EncryptedDataType;
   }

   private GetTherapeuticLinkSelectType mapGetTherapeuticLinkSelectType(TherapeuticLink link) throws TherLinkBusinessConnectorException {
      GetTherapeuticLinkSelectType request = new GetTherapeuticLinkSelectType();
      if (link.getStartDate() != null) {
         request.setBegindate(link.getStartDate().toDateTime(LocalTime.MIDNIGHT));
      }

      if (link.getEndDate() != null) {
         request.setEnddate(link.getEndDate().toDateTime(LocalTime.MIDNIGHT));
      }

      if (link.getType() != null) {
         CDTHERAPEUTICLINK therlinkType = this.createTherapeuticLinkType(link.getType());
         request.getCds().add(therlinkType);
      }

      if (link.getStatus() != null) {
         request.setTherapeuticlinkstatus(link.getStatus().toString().toLowerCase(Locale.getDefault()));
      }

      if (link.getPatient() != null) {
         request.getPatientsAndHcparties().add(this.mapPatient(link.getPatient()));
      }

      if (link.getHcParty() != null) {
         request.getPatientsAndHcparties().add(HcPartyMapper.mapHcPartyIdType(link.getHcParty()));
      }

      return request;
   }

   private HasTherapeuticLinkSelectType mapHasTherapeuticLinkSelectType(TherapeuticLink link) throws TherLinkBusinessConnectorException {
      HasTherapeuticLinkSelectType request = new HasTherapeuticLinkSelectType();
      if (link.getPatient() != null) {
         request.setPatient(this.mapPatient(link.getPatient()));
      }

      if (link.getHcParty() != null) {
         request.setHcparty(HcPartyMapper.mapHcPartyIdType(link.getHcParty()));
      }

      return request;
   }

   private RequestType mapRequest(DateTime date, String id, Author author, int maxRows) throws TechnicalConnectorException, TherLinkBusinessConnectorException {
      RequestType requestType = new RequestType();
      requestType.setAuthor(this.mapAuthor(author));
      requestType.setDate(new DateTime(date));
      requestType.setTime(new DateTime(date));
      IDKMEHR idKmehr = this.createIdKmehr(id);
      requestType.setId(idKmehr);
      if (maxRows != 0) {
         requestType.setMaxrows(new BigDecimal(maxRows));
      }

      return requestType;
   }

   private AuthorWithPatientAndPersonType mapAuthor(Author author) throws TherLinkBusinessConnectorException {
      AuthorWithPatientAndPersonType authorKmehr = new AuthorWithPatientAndPersonType();
      Iterator i$ = author.getHcParties().iterator();

      while(i$.hasNext()) {
         HcParty hcp = (HcParty)i$.next();
         authorKmehr.getHcparties().add(HcPartyMapper.mapToHcpartyType(hcp));
      }

      return authorKmehr;
   }

   private PatientIdType mapPatient(Patient patient) {
      PatientIdType patientIdType = new PatientIdType();
      patientIdType.setFamilyname(patient.getLastName());
      patientIdType.setFirstname(patient.getFirstName());
      if (StringUtils.isEmpty(patient.getFirstName()) && StringUtils.isEmpty(patient.getLastName()) && patient instanceof be.ehealth.businessconnector.therlink.domain.Patient) {
         be.ehealth.businessconnector.therlink.domain.Patient therlinkPatient = (be.ehealth.businessconnector.therlink.domain.Patient)patient;
         patientIdType.setName(therlinkPatient.getName());
      }

      IDPATIENT inss = this.createIDPatient(IDPATIENTschemes.INSS, "1.0", patient.getInss());
      patientIdType.getIds().add(inss);
      IDPATIENT isi;
      if (patient.getEidCardNumber() != null) {
         isi = this.createIDPatient(IDPATIENTschemes.EID_CARDNO, "1.0", patient.getEidCardNumber());
         patientIdType.getIds().add(isi);
      }

      if (patient.getSisCardNumber() != null) {
         isi = this.createIDPatient(IDPATIENTschemes.SIS_CARDNO, "1.0", patient.getSisCardNumber());
         patientIdType.getIds().add(isi);
      }

      if (patient.getIsiCardNumber() != null) {
         isi = this.createIDPatient(IDPATIENTschemes.ISI_CARDNO, "1.0", patient.getIsiCardNumber());
         patientIdType.getIds().add(isi);
      }

      return patientIdType;
   }

   private CDTHERAPEUTICLINK mapCdTherapeuticLink(String therLinkType) {
      CDTHERAPEUTICLINK cdTL = new CDTHERAPEUTICLINK();
      cdTL.setS(CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE);
      cdTL.setSV("1.1");
      cdTL.setValue(therLinkType);
      return cdTL;
   }

   private TherapeuticLinkType mapTherapeuticLinkType(TherapeuticLink link) throws TherLinkBusinessConnectorException {
      TherapeuticLinkType therLink = new TherapeuticLinkType();
      therLink.setCd(this.mapCdTherapeuticLink(link.getType()));
      therLink.setComment(link.getComment());
      if (link.getEndDate() != null) {
         therLink.setEnddate(link.getEndDate().toDateTime(LocalTime.MIDNIGHT));
      }

      if (link.getStartDate() != null) {
         therLink.setStartdate(link.getStartDate().toDateTime(LocalTime.MIDNIGHT));
      }

      HcParty hcParty = link.getHcParty();
      therLink.getHcparties().add(HcPartyMapper.mapHcPartyIdType(hcParty));
      therLink.setPatient(this.mapPatient(link.getPatient()));
      return therLink;
   }

   public String createTherapeuticLinkAsXmlString(TherapeuticLink therLink) throws TherLinkBusinessConnectorException, TechnicalConnectorException {
      Therapeuticlink therLinkRoot = new Therapeuticlink();
      CDTHERAPEUTICLINK cdTherLink = this.createTherapeuticLinkType(therLink.getType());
      therLinkRoot.setCd(cdTherLink);
      therLinkRoot.setComment(therLink.getComment());
      therLinkRoot.setEnddate(therLink.getEndDate() == null ? null : therLink.getEndDate().toDateTimeAtCurrentTime());
      therLinkRoot.setStartdate(therLink.getStartDate() == null ? null : therLink.getStartDate().toDateTimeAtCurrentTime());
      therLinkRoot.setPatient(this.mapPatient(therLink.getPatient()));
      therLinkRoot.getHcparties().add(HcPartyMapper.mapHcPartyIdType(therLink.getHcParty()));
      String generatedXML = this.generateXML(therLinkRoot, Therapeuticlink.class);
      return generatedXML;
   }

   private IDPATIENT createIDPatient(IDPATIENTschemes s, String sv, String value) {
      IDPATIENT id = new IDPATIENT();
      id.setS(s);
      id.setSV(sv);
      id.setValue(value);
      return id;
   }

   private IDKMEHR createIdKmehr(String id) {
      IDKMEHR idKmehr = new IDKMEHR();
      idKmehr.setS(IDKMEHRschemes.ID_KMEHR);
      idKmehr.setSV("1.0");
      idKmehr.setValue(id);
      return idKmehr;
   }

   private CDTHERAPEUTICLINK createTherapeuticLinkType(String type) {
      CDTHERAPEUTICLINK therlinkType = new CDTHERAPEUTICLINK();
      therlinkType.setS(CDTHERAPEUTICLINKschemes.CD_THERAPEUTICLINKTYPE);
      therlinkType.setSV("1.0");
      therlinkType.setValue(type);
      return therlinkType;
   }

   private CDENCRYPTIONMETHOD createEncryptionMethodType(String method) {
      CDENCRYPTIONMETHOD cdEncryptionMethod = new CDENCRYPTIONMETHOD();
      cdEncryptionMethod.setS(CDENCRYPTIONMETHODschemes.CD_ENCRYPTION_METHOD);
      cdEncryptionMethod.setSV("1.0");
      cdEncryptionMethod.setValue(CDENCRYPTIONMETHODvalues.fromValue(method));
      return cdEncryptionMethod;
   }

   private CDPROOF createProofType(String type) {
      CDPROOF cdProof = new CDPROOF();
      cdProof.setS(CDPROOFschemes.CD_PROOFTYPE);
      cdProof.setSV("1.1");
      cdProof.setValue(type);
      return cdProof;
   }

   private <T> String generateXML(T object, Class<T> clazz) throws TechnicalConnectorException {
      MarshallerHelper<T, T> marshaller = new MarshallerHelper(clazz, clazz);
      String xml = marshaller.toString(object);
      LOG.info("generated XML" + xml);
      return xml;
   }
}
