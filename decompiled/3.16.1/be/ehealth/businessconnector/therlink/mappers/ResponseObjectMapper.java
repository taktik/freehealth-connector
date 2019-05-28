package be.ehealth.businessconnector.therlink.mappers;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.businessconnector.therlink.builders.impl.TherlinkBuilderUtil;
import be.ehealth.businessconnector.therlink.domain.Author;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.domain.OperationContext;
import be.ehealth.businessconnector.therlink.domain.Proof;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLink;
import be.ehealth.businessconnector.therlink.domain.TherapeuticLinkRequestType;
import be.ehealth.businessconnector.therlink.domain.requests.BinaryProof;
import be.ehealth.businessconnector.therlink.domain.requests.GetTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.HasTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.PutTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.requests.RevokeTherapeuticLinkRequest;
import be.ehealth.businessconnector.therlink.domain.responses.Acknowledge;
import be.ehealth.businessconnector.therlink.domain.responses.GetTherapeuticLinkResponse;
import be.ehealth.businessconnector.therlink.domain.responses.HasTherapeuticLinkResponse;
import be.ehealth.businessconnector.therlink.domain.responses.PutTherapeuticLinkResponse;
import be.ehealth.businessconnector.therlink.domain.responses.RevokeTherapeuticLinkResponse;
import be.ehealth.businessconnector.therlink.domain.responses.TherapeuticLinkResponse;
import be.ehealth.businessconnector.therlink.domain.responses.TherapeuticLinkResponseError;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.fgov.ehealth.hubservices.core.v2.AcknowledgeType;
import be.fgov.ehealth.hubservices.core.v2.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v2.OperationContextType;
import be.fgov.ehealth.hubservices.core.v2.PatientIdType;
import be.fgov.ehealth.hubservices.core.v2.ProofType;
import be.fgov.ehealth.hubservices.core.v2.RequestType;
import be.fgov.ehealth.hubservices.core.v2.ResponseType;
import be.fgov.ehealth.hubservices.core.v2.TherapeuticLinkListType;
import be.fgov.ehealth.hubservices.core.v2.TherapeuticLinkWithOperationContext;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDERROR;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT;
import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENTschemes;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Base64EncryptedDataType;
import be.fgov.ehealth.standards.kmehr.schema.v1.ErrorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseObjectMapper {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseObjectMapper.class);
   private static final String E_HP_APPLICATION_ID = "eHP application id";
   private static final String ORGPUBLICHEALTH = "orgpublichealth";
   private static final String APPLICATION_ID = "application_ID";
   private static final String APPLICATION = "application";
   private static final String OUTPUTPRESENTATION = "Output request object :";

   public PutTherapeuticLinkResponse mapXMLToPutTherapeuticLinkResponse(String xml) throws TechnicalConnectorException {
      be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse jaxbResponse = (be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse)this.generateJAXB(xml, be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse.class);
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime date = jaxbResponseType.getDate();
      DateTime time = jaxbResponseType.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond(), DateTimeZone.getDefault());
      PutTherapeuticLinkResponse response = new PutTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalPutTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public RevokeTherapeuticLinkResponse mapXMLToRevokeTherapeuticLinkResponse(String xml) throws TechnicalConnectorException {
      be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse jaxbResponse = (be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse)this.generateJAXB(xml, be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse.class);
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime date = jaxbResponseType.getDate();
      DateTime time = jaxbResponseType.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond());
      RevokeTherapeuticLinkResponse response = new RevokeTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalRevokeTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public GetTherapeuticLinkResponse mapXMLToGetTherapeuticLinkResponse(String xml) throws TechnicalConnectorException {
      be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse jaxbResponse = (be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse)this.generateJAXB(xml, be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse.class);
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime date = jaxbResponseType.getDate();
      DateTime time = jaxbResponseType.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond());
      GetTherapeuticLinkResponse response = new GetTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalGetTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapListOfTherapeuticLinks(jaxbResponse.getTherapeuticlinklist()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public HasTherapeuticLinkResponse mapXMLToHasTherapeuticLinkResponse(String xml) throws TechnicalConnectorException {
      be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse jaxbResponse = (be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse)this.generateJAXB(xml, be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse.class);
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime date = jaxbResponseType.getDate();
      DateTime time = jaxbResponseType.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond());
      HasTherapeuticLinkResponse response = new HasTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalHasTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public PutTherapeuticLinkResponse mapJaxbToPutTherapeuticLinkResponse(be.fgov.ehealth.hubservices.core.v2.PutTherapeuticLinkResponse jaxbResponse) {
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime dateTime = this.mapDateTime(jaxbResponseType.getDate(), jaxbResponseType.getTime());
      PutTherapeuticLinkResponse response = new PutTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalPutTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public GetTherapeuticLinkResponse mapJaxbToGetTherapeuticLinkResponse(be.fgov.ehealth.hubservices.core.v2.GetTherapeuticLinkResponse jaxbResponse) {
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime dateTime = this.mapDateTime(jaxbResponseType.getDate(), jaxbResponseType.getTime());
      GetTherapeuticLinkResponse response = new GetTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalHasTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapListOfTherapeuticLinks(jaxbResponse.getTherapeuticlinklist()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public RevokeTherapeuticLinkResponse mapJaxbToRevokeTherapeuticLinkResponse(be.fgov.ehealth.hubservices.core.v2.RevokeTherapeuticLinkResponse jaxbResponse) {
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime date = jaxbResponseType.getDate();
      DateTime time = jaxbResponseType.getTime();
      DateTime dateTime = this.mapDateTime(date, time);
      RevokeTherapeuticLinkResponse response = new RevokeTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalPutTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public HasTherapeuticLinkResponse mapJaxbToHasTherapeuticLinkResponse(be.fgov.ehealth.hubservices.core.v2.HasTherapeuticLinkResponse jaxbResponse) {
      ResponseType jaxbResponseType = jaxbResponse.getResponse();
      DateTime dateTime = this.mapDateTime(jaxbResponseType.getDate(), jaxbResponseType.getTime());
      HasTherapeuticLinkResponse response = new HasTherapeuticLinkResponse(dateTime, this.mapAuthor(jaxbResponseType.getAuthor()), jaxbResponseType.getId().getValue(), this.mapOriginalHasTherapeuticLinkRequest(jaxbResponseType.getRequest()), this.mapAcknowledge(jaxbResponse.getAcknowledge()));
      LOG.info("Output request object :" + response.toString());
      return response;
   }

   public Acknowledge mapAcknowledge(AcknowledgeType acknowledge) {
      Acknowledge ack = new Acknowledge();
      ack.setComplete(acknowledge.isIscomplete());
      if (acknowledge.getErrors().size() > 0) {
         Iterator i$ = acknowledge.getErrors().iterator();

         while(i$.hasNext()) {
            ErrorType errorType = (ErrorType)i$.next();
            ack.getListOfErrors().add(this.mapTherapeuticLinkResponseError(errorType));
         }
      }

      return ack;
   }

   private TherapeuticLinkResponseError mapTherapeuticLinkResponseError(ErrorType errorType) {
      TherapeuticLinkResponseError error = new TherapeuticLinkResponseError();
      error.setErrorCode(((CDERROR)errorType.getCds().get(0)).getValue());
      error.setErrorDescription(errorType.getDescription().getValue());
      return error;
   }

   private List<TherapeuticLinkResponse> mapListOfTherapeuticLinks(TherapeuticLinkListType therapeuticlinklist) {
      if (therapeuticlinklist == null) {
         return null;
      } else {
         List<TherapeuticLinkResponse> listOfTherapeuticLinkResponse = new ArrayList();
         Iterator i$ = therapeuticlinklist.getTherapeuticlinks().iterator();

         while(i$.hasNext()) {
            TherapeuticLinkWithOperationContext therLink = (TherapeuticLinkWithOperationContext)i$.next();
            listOfTherapeuticLinkResponse.add(this.mapTherapeuticLinkResponse(therLink));
         }

         return listOfTherapeuticLinkResponse;
      }
   }

   private TherapeuticLinkResponse mapTherapeuticLinkResponse(TherapeuticLinkWithOperationContext therLink) {
      HcParty hcPartyToUse = this.mapHcParty(TherlinkBuilderUtil.retrieveFirstHCPartyIdTypeInList(therLink.getHcparties()));
      TherapeuticLinkResponse therLinkResponse = new TherapeuticLinkResponse(this.mapPatient(therLink.getPatient()), hcPartyToUse, therLink.getCd().getValue());
      therLinkResponse.setComment(therLink.getComment());
      DateTime enddate;
      if (therLink.getStartdate() != null) {
         enddate = therLink.getStartdate();
         therLinkResponse.setStartDate(new LocalDate(enddate.getYear(), enddate.getMonthOfYear(), enddate.getDayOfMonth()));
      }

      if (therLink.getEnddate() != null) {
         enddate = therLink.getEnddate();
         therLinkResponse.setEndDate(new LocalDate(enddate.getYear(), enddate.getMonthOfYear(), enddate.getDayOfMonth()));
      }

      therLinkResponse.setOperationContexts(this.mapListOfOperationContext(therLink.getOperationcontexts()));
      return therLinkResponse;
   }

   private List<OperationContext> mapListOfOperationContext(List<OperationContextType> operationContexts) {
      List<OperationContext> listOfOpContext = new ArrayList();
      Iterator i$ = operationContexts.iterator();

      while(i$.hasNext()) {
         OperationContextType opContextType = (OperationContextType)i$.next();
         listOfOpContext.add(this.mapOperationContext(opContextType));
      }

      return listOfOpContext;
   }

   private OperationContext mapOperationContext(OperationContextType opContextType) {
      return new OperationContext(opContextType.getRecorddatetime(), opContextType.getOperation().value(), this.mapAuthor(opContextType.getAuthor()), this.mapListOfProof(opContextType.getProoves()));
   }

   private List<Proof> mapListOfProof(List<ProofType> prooves) {
      List<Proof> listOfProof = new ArrayList();
      Iterator i$ = prooves.iterator();

      while(i$.hasNext()) {
         ProofType proofType = (ProofType)i$.next();
         listOfProof.add(this.mapProof(proofType));
      }

      return listOfProof;
   }

   private Proof mapProof(ProofType proofType) {
      Proof proof = new Proof(proofType.getCd().getValue());
      proof.setBinaryProof(this.mapBinaryProof(proofType.getBinaryproof()));
      return proof;
   }

   private BinaryProof mapBinaryProof(Base64EncryptedDataType base64EncryptedDateType) {
      return base64EncryptedDateType == null ? null : new BinaryProof(base64EncryptedDateType.getCd().getValue().value(), base64EncryptedDateType.getBase64EncryptedValue().getValue());
   }

   private HcParty mapHcParty(HCPartyIdType hcparty) {
      HcParty hcp = new HcParty();
      hcp.setFirstName(hcparty.getFirstname());
      hcp.setFamilyName(hcparty.getFamilyname());
      hcp.setName(hcparty.getName());
      hcp.setType(hcparty.getCd().getValue());
      Iterator i$ = hcparty.getIds().iterator();

      while(i$.hasNext()) {
         IDHCPARTY id = (IDHCPARTY)i$.next();
         if (id.getS().equals(IDHCPARTYschemes.INSS)) {
            hcp.setInss(id.getValue());
         } else if (id.getS().equals(IDHCPARTYschemes.ID_HCPARTY)) {
            if ("application".equals(hcparty.getCd().getValue())) {
               hcp.setCbe(id.getValue());
            } else {
               hcp.setNihii(id.getValue());
            }
         } else if ("application_ID".equals(id.getSL())) {
            hcp.setApplicationID(id.getValue());
         }
      }

      return hcp;
   }

   private Patient mapPatient(PatientIdType patientIdType) {
      Patient patient = new Patient();
      patient.setLastName(patientIdType.getFamilyname());
      patient.setFirstName(patientIdType.getFirstname());
      patient.setMiddleName(patientIdType.getName());
      Iterator i$ = patientIdType.getIds().iterator();

      while(i$.hasNext()) {
         IDPATIENT id = (IDPATIENT)i$.next();
         if (id.getS().equals(IDPATIENTschemes.EID_CARDNO)) {
            patient.setEidCardNumber(id.getValue());
         } else if (id.getS().equals(IDPATIENTschemes.INSS)) {
            patient.setInss(id.getValue());
         } else if (id.getS().equals(IDPATIENTschemes.SIS_CARDNO)) {
            patient.setSisCardNumber(id.getValue());
         } else if (id.getS().equals(IDPATIENTschemes.ISI_CARDNO)) {
            patient.setIsiCardNumber(id.getValue());
         }
      }

      return patient;
   }

   private TherapeuticLinkRequestType mapOriginalPutTherapeuticLinkRequest(RequestType request) {
      DateTime date = request.getDate();
      DateTime time = request.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond());
      return new PutTherapeuticLinkRequest(dateTime, request.getId().getValue(), this.mapAuthor((AuthorType)request.getAuthor()));
   }

   private TherapeuticLinkRequestType mapOriginalRevokeTherapeuticLinkRequest(RequestType request) {
      DateTime date = request.getDate();
      DateTime time = request.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond());
      return new RevokeTherapeuticLinkRequest(dateTime, request.getId().getValue(), this.mapAuthor(request), (TherapeuticLink)null, (Proof[])null);
   }

   private TherapeuticLinkRequestType mapOriginalGetTherapeuticLinkRequest(RequestType request) {
      DateTime date = request.getDate();
      DateTime time = request.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond());
      return new GetTherapeuticLinkRequest(dateTime, request.getId().getValue(), this.mapAuthor(request), (TherapeuticLink)null, 999, (Proof[])null);
   }

   private TherapeuticLinkRequestType mapOriginalHasTherapeuticLinkRequest(RequestType request) {
      DateTime date = request.getDate();
      DateTime time = request.getTime();
      DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond());
      return new HasTherapeuticLinkRequest(dateTime, request.getId().getValue(), this.mapAuthor((AuthorType)request.getAuthor()), (TherapeuticLink)null);
   }

   private Author mapAuthor(AuthorType authorType) {
      Author author = new Author();
      this.mapAuthor(authorType, author);
      return author;
   }

   private Author mapAuthor(RequestType requestType) {
      Author author = null;
      if (requestType != null) {
         author = new Author();
         this.mapAuthor(requestType.getAuthor(), author);
      }

      return author;
   }

   private void mapAuthor(AuthorType authorType, Author author) {
      Iterator i$ = authorType.getHcparties().iterator();

      while(i$.hasNext()) {
         HcpartyType hcpType = (HcpartyType)i$.next();
         author.getHcParties().add(this.mapHcParty(hcpType));
      }

   }

   private HcParty mapHcParty(HcpartyType hcpType) {
      HcParty hcp = new HcParty();
      hcp.setFamilyName(hcpType.getFamilyname());
      hcp.setFirstName(hcpType.getFirstname());
      hcp.setName(hcpType.getName());
      String type = hcpType.getCds().size() > 0 ? ((CDHCPARTY)hcpType.getCds().get(0)).getValue() : "";
      hcp.setType(type);
      hcp.setIds(hcpType.getIds());
      hcp.setCds(hcpType.getCds());
      Iterator i$ = hcpType.getIds().iterator();

      while(true) {
         while(true) {
            while(i$.hasNext()) {
               IDHCPARTY id = (IDHCPARTY)i$.next();
               if (id.getS().equals(IDHCPARTYschemes.ID_HCPARTY)) {
                  if (!"application".equals(type) && !"application_ID".equals(type)) {
                     if ("orgpublichealth".equals(type)) {
                        hcp.setEHP(id.getValue());
                     } else {
                        hcp.setNihii(id.getValue());
                     }
                  } else {
                     hcp.setCbe(id.getValue());
                  }
               } else if (id.getS().equals(IDHCPARTYschemes.INSS)) {
                  hcp.setInss(id.getValue());
               } else if (("application_ID".equals(id.getSL()) || "application id".equals(id.getSL())) && "application".equals(type)) {
                  hcp.setApplicationID(id.getValue());
               } else if ("orgpublichealth".equals(type) || "eHP application id".equals(id.getSL())) {
                  hcp.setEHP(id.getValue());
               }
            }

            return hcp;
         }
      }
   }

   private DateTime mapDateTime(DateTime date, DateTime time) {
      return new DateTime(date != null ? date.getYear() : 0, date != null ? date.getMonthOfYear() : 1, date != null ? date.getDayOfMonth() : 1, time.getHourOfDay(), time.getMinuteOfHour(), time.getSecondOfMinute(), time.getMillisOfSecond(), DateTimeZone.getDefault());
   }

   public <T> T generateJAXB(String request, Class<T> clazz) throws TechnicalConnectorException {
      MarshallerHelper<T, T> marshaller = new MarshallerHelper(clazz, clazz);
      LOG.info("XML input : " + request);
      return marshaller.toObject(request);
   }
}
