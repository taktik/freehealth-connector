package be.ehealth.businessconnector.medadmin.session.impl;

import be.cin.mycarenet._1_0.carenet.types.MedAdminRequestList;
import be.cin.mycarenet._1_0.carenet.types.MedAdminResponseList;
import be.cin.mycarenet._1_0.carenet.types.SingleNurseContractualCareRequest;
import be.cin.mycarenet._1_0.carenet.types.SingleNurseContractualCareResponse;
import be.cin.mycarenet._1_0.carenet.types.SingleNurseContractualCareUpdate;
import be.cin.mycarenet._1_0.carenet.types.SinglePalliativeCareRequest;
import be.cin.mycarenet._1_0.carenet.types.SinglePalliativeCareResponse;
import be.cin.mycarenet._1_0.carenet.types.SingleSpecificTechnicalCareRequest;
import be.cin.mycarenet._1_0.carenet.types.SingleSpecificTechnicalCareResponse;
import be.cin.nip.async.generic.RejectInb;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.businessconnector.genericasync.domain.GetRequest;
import be.ehealth.businessconnector.genericasync.domain.ProcessedGetResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedMsgResponse;
import be.ehealth.businessconnector.genericasync.domain.ProcessedPostResponse;
import be.ehealth.businessconnector.genericasync.helper.DefaultCommonAsyncService;
import be.ehealth.businessconnector.genericasync.helper.GetHelper;
import be.ehealth.businessconnector.genericasync.helper.PostHelper;
import be.ehealth.businessconnector.medadmin.domain.M4ACnfXmlProcessedGetResponse;
import be.ehealth.businessconnector.medadmin.domain.M4ACnfXmlProcessedMsgResponse;
import be.ehealth.businessconnector.medadmin.domain.M4AXmlProcessedGetResponse;
import be.ehealth.businessconnector.medadmin.domain.M4AXmlProcessedMsgResponse;
import be.ehealth.businessconnector.medadmin.domain.RejectXmlProcessedGetResponse;
import be.ehealth.businessconnector.medadmin.domain.RejectXmlProcessedMsgResponse;
import be.ehealth.businessconnector.medadmin.session.MedAdminService;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.validator.ValidatorHelper;
import java.util.Iterator;
import org.w3c.dom.Element;

public class MedadminServiceImpl extends DefaultCommonAsyncService implements MedAdminService {
   private static final Configuration config = ConfigFactory.getConfigValidator();
   private static final String PROJECT_NAME = "medadmin";
   private static final String PLATFORM_NAME = "mycarenet";
   public static final String M4A_XML = "M4A_XML";
   public static final String M4A_FLAT = "M4A_FLAT";
   public static final String M4A_CNF_FLAT = "M4A_CNF_FLAT";
   public static final String REJECT = "REJECT";
   public static final String SCHEMA_LOCATION = "/mycarenet-genasync/XSD/MyCareNet_MedAdmin.xsd";
   public static final String SCHEMA_LOCATION_REJECT = "/mycarenet-commons/XSD/Reject.xsd";
   private static final String GENERICASYNC = "genericasync.";
   private GetHelper getHelper = new GetHelper("medadmin");
   private PostHelper postHelper = new PostHelper("mycarenet", "medadmin");

   public MedadminServiceImpl() {
      super("medadmin");
   }

   public ProcessedPostResponse postM4AFlat(byte[] records, String recipient, InputReference inputReference) throws ConnectorException {
      return this.postHelper.post(records, "M4A_FLAT", recipient, inputReference);
   }

   public ProcessedGetResponse<byte[]> getM4AFlat(GetRequest request) throws ConnectorException {
      return this.getHelper.get(request, "M4A_FLAT", byte[].class);
   }

   public ProcessedPostResponse postMedAdminRequestList(MedAdminRequestList medAdminRequestList, String recipient, InputReference inputReference) throws ConnectorException {
      return this.postHelper.post(medAdminRequestList, "M4A_XML", "/mycarenet-genasync/XSD/MyCareNet_MedAdmin.xsd", recipient, inputReference);
   }

   public ProcessedPostResponse postSingleNurseContractualCareRequest(SingleNurseContractualCareRequest singleNurseContractualCareRequest, String recipient, InputReference inputReference) throws ConnectorException {
      return this.postHelper.post(singleNurseContractualCareRequest, "M4A_XML", "/mycarenet-genasync/XSD/MyCareNet_MedAdmin.xsd", recipient, inputReference);
   }

   public ProcessedPostResponse postSinglePalliativeCareRequest(SinglePalliativeCareRequest singlePalliativeCareRequest, String recipient, InputReference inputReference) throws ConnectorException {
      return this.postHelper.post(singlePalliativeCareRequest, "M4A_XML", "/mycarenet-genasync/XSD/MyCareNet_MedAdmin.xsd", recipient, inputReference);
   }

   public ProcessedPostResponse postSingleSpecificTechnicalCareRequest(SingleSpecificTechnicalCareRequest singleSpecificTechnicalCareRequest, String recipient, InputReference inputReference) throws ConnectorException {
      return this.postHelper.post(singleSpecificTechnicalCareRequest, "M4A_XML", "/mycarenet-genasync/XSD/MyCareNet_MedAdmin.xsd", recipient, inputReference);
   }

   public M4AXmlProcessedGetResponse getM4AXml(GetRequest request) throws ConnectorException {
      ProcessedGetResponse<byte[]> processedGetResponse = this.getHelper.get(request, "M4A_XML", byte[].class);
      M4AXmlProcessedGetResponse medAdminProcessedGetResponse = new M4AXmlProcessedGetResponse();
      medAdminProcessedGetResponse.getTAckResponses().addAll(processedGetResponse.getTAckResponses());
      this.transformRawResponseToBusinessObjects(processedGetResponse, medAdminProcessedGetResponse);
      return medAdminProcessedGetResponse;
   }

   public void transformRawResponseToBusinessObjects(ProcessedGetResponse<byte[]> processedGetResponse, M4AXmlProcessedGetResponse medAdminProcessedGetResponse) throws TechnicalConnectorException {
      Iterator var3 = processedGetResponse.getMsgResponses().iterator();

      while(true) {
         while(var3.hasNext()) {
            ProcessedMsgResponse<byte[]> processedMsgResponse = (ProcessedMsgResponse)var3.next();
            M4AXmlProcessedMsgResponse adminProcessedMsgResponse = new M4AXmlProcessedMsgResponse(processedMsgResponse);
            medAdminProcessedGetResponse.getMsgResponses().add(adminProcessedMsgResponse);
            byte[] businessResponse = (byte[])processedMsgResponse.getBusinessResponse();
            String type = ((Element)ConnectorXmlUtils.toObject(businessResponse, Object.class)).getLocalName();
            if ("SingleNurseContractualCareResponse".equals(type)) {
               SingleNurseContractualCareResponse singleNurseContractualCareResponse = (SingleNurseContractualCareResponse)ConnectorXmlUtils.toObject(businessResponse, SingleNurseContractualCareResponse.class);
               this.validateAgainstXsd(singleNurseContractualCareResponse);
               adminProcessedMsgResponse.getSingleNurseContractualCareResponses().add(singleNurseContractualCareResponse);
            } else if ("SinglePalliativeCareResponse".equals(type)) {
               SinglePalliativeCareResponse singlePalliativeCareResponse = (SinglePalliativeCareResponse)ConnectorXmlUtils.toObject(businessResponse, SinglePalliativeCareResponse.class);
               this.validateAgainstXsd(singlePalliativeCareResponse);
               adminProcessedMsgResponse.getSinglePalliativeCareResponses().add(singlePalliativeCareResponse);
            } else if ("SingleSpecificTechnicalCareResponse".equals(type)) {
               SingleSpecificTechnicalCareResponse singleSpecificTechnicalCareResponse = (SingleSpecificTechnicalCareResponse)ConnectorXmlUtils.toObject(businessResponse, SingleSpecificTechnicalCareResponse.class);
               this.validateAgainstXsd(singleSpecificTechnicalCareResponse);
               adminProcessedMsgResponse.getSingleSpecificTechnicalCareResponses().add(singleSpecificTechnicalCareResponse);
            } else if ("SingleNurseContractualCareUpdate".equals(type)) {
               SingleNurseContractualCareUpdate singleNurseContractualCareUpdate = (SingleNurseContractualCareUpdate)ConnectorXmlUtils.toObject(businessResponse, SingleNurseContractualCareUpdate.class);
               this.validateAgainstXsd(singleNurseContractualCareUpdate);
               adminProcessedMsgResponse.getSingleNurseContractualCareUpdates().add(singleNurseContractualCareUpdate);
            } else {
               if (!"MedAdminResponseList".equals(type)) {
                  throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{"[" + type + "] is not a valid root element for the business response"});
               }

               MedAdminResponseList medAdminResponseList = (MedAdminResponseList)ConnectorXmlUtils.toObject(businessResponse, MedAdminResponseList.class);
               this.validateAgainstXsd(medAdminResponseList);
               Iterator var9 = medAdminResponseList.getSingleNurseContractualCareResponsesAndSingleNurseContractualCareUpdatesAndSinglePalliativeCareResponses().iterator();

               while(var9.hasNext()) {
                  Object o = var9.next();
                  if (o instanceof SingleNurseContractualCareResponse) {
                     adminProcessedMsgResponse.getSingleNurseContractualCareResponses().add((SingleNurseContractualCareResponse)o);
                  } else if (o instanceof SingleNurseContractualCareUpdate) {
                     adminProcessedMsgResponse.getSingleNurseContractualCareUpdates().add((SingleNurseContractualCareUpdate)o);
                  } else if (o instanceof SinglePalliativeCareResponse) {
                     adminProcessedMsgResponse.getSinglePalliativeCareResponses().add((SinglePalliativeCareResponse)o);
                  } else if (o instanceof SingleSpecificTechnicalCareResponse) {
                     adminProcessedMsgResponse.getSingleSpecificTechnicalCareResponses().add((SingleSpecificTechnicalCareResponse)o);
                  }
               }
            }
         }

         return;
      }
   }

   public M4ACnfXmlProcessedGetResponse getM4ACnfXml(GetRequest request) throws ConnectorException {
      M4ACnfXmlProcessedGetResponse medAdminProcessedGetResponse = new M4ACnfXmlProcessedGetResponse();
      ProcessedGetResponse<byte[]> processedGetResponse = this.getHelper.get(request, "M4A_CNF_XML", byte[].class);
      medAdminProcessedGetResponse.getTAckResponses().addAll(processedGetResponse.getTAckResponses());
      this.transformRawResponseToBusinessObjects(processedGetResponse, medAdminProcessedGetResponse);
      return medAdminProcessedGetResponse;
   }

   public void transformRawResponseToBusinessObjects(ProcessedGetResponse<byte[]> processedGetResponse, M4ACnfXmlProcessedGetResponse medAdminProcessedGetResponse) throws TechnicalConnectorException {
      Iterator var3 = processedGetResponse.getMsgResponses().iterator();

      while(true) {
         while(var3.hasNext()) {
            ProcessedMsgResponse<byte[]> processedMsgResponse = (ProcessedMsgResponse)var3.next();
            M4ACnfXmlProcessedMsgResponse adminProcessedMsgResponse = new M4ACnfXmlProcessedMsgResponse(processedMsgResponse);
            medAdminProcessedGetResponse.getMsgResponses().add(adminProcessedMsgResponse);
            byte[] businessResponse = (byte[])processedMsgResponse.getBusinessResponse();
            String type = ((Element)ConnectorXmlUtils.toObject(businessResponse, Object.class)).getLocalName();
            if ("SingleNurseContractualCareRequest".equals(type)) {
               SingleNurseContractualCareRequest singleNurseContractualCareRequest = (SingleNurseContractualCareRequest)ConnectorXmlUtils.toObject(businessResponse, SingleNurseContractualCareRequest.class);
               this.validateAgainstXsd(singleNurseContractualCareRequest);
               adminProcessedMsgResponse.getSingleNurseContractualCareRequests().add(singleNurseContractualCareRequest);
            } else if ("SinglePalliativeCareRequest".equals(type)) {
               SinglePalliativeCareRequest singlePalliativeCareRequest = (SinglePalliativeCareRequest)ConnectorXmlUtils.toObject(businessResponse, SinglePalliativeCareRequest.class);
               this.validateAgainstXsd(singlePalliativeCareRequest);
               adminProcessedMsgResponse.getSinglePalliativeCareRequests().add(singlePalliativeCareRequest);
            } else if ("SingleSpecificTechnicalCareRequest".equals(type)) {
               SingleSpecificTechnicalCareRequest singleSpecificTechnicalCareRequest = (SingleSpecificTechnicalCareRequest)ConnectorXmlUtils.toObject(businessResponse, SingleSpecificTechnicalCareRequest.class);
               this.validateAgainstXsd(singleSpecificTechnicalCareRequest);
               adminProcessedMsgResponse.getSingleSpecificTechnicalCareRequests().add(singleSpecificTechnicalCareRequest);
            } else {
               if (!"MedAdminRequestList".equals(type)) {
                  throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{"[" + type + "] is not a valid root element for the business response"});
               }

               MedAdminRequestList medAdminRequestList = (MedAdminRequestList)ConnectorXmlUtils.toObject(businessResponse, MedAdminRequestList.class);
               this.validateAgainstXsd(medAdminRequestList);
               Iterator var9 = medAdminRequestList.getSingleNurseContractualCareRequestsAndSinglePalliativeCareRequestsAndSingleSpecificTechnicalCareRequests().iterator();

               while(var9.hasNext()) {
                  Object o = var9.next();
                  if (o instanceof SingleNurseContractualCareRequest) {
                     adminProcessedMsgResponse.getSingleNurseContractualCareRequests().add((SingleNurseContractualCareRequest)o);
                  } else if (o instanceof SinglePalliativeCareRequest) {
                     adminProcessedMsgResponse.getSinglePalliativeCareRequests().add((SinglePalliativeCareRequest)o);
                  } else if (o instanceof SingleSpecificTechnicalCareRequest) {
                     adminProcessedMsgResponse.getSingleSpecificTechnicalCareRequests().add((SingleSpecificTechnicalCareRequest)o);
                  }
               }
            }
         }

         return;
      }
   }

   public void validateAgainstXsd(Object object) throws TechnicalConnectorException {
      if (config.getBooleanProperty("genericasync.medadmin.validation.incoming.businessresponse", true)) {
         ValidatorHelper.validate(object, "/mycarenet-genasync/XSD/MyCareNet_MedAdmin.xsd");
      }

   }

   public ProcessedGetResponse<byte[]> getM4ACnfFlat(GetRequest request) throws ConnectorException {
      return this.getHelper.get(request, "M4A_CNF_FLAT", byte[].class);
   }

   public RejectXmlProcessedGetResponse getRejected(GetRequest request) throws ConnectorException {
      RejectXmlProcessedGetResponse medAdminProcessedGetResponse = new RejectXmlProcessedGetResponse();
      ProcessedGetResponse<byte[]> processedGetResponse = this.getHelper.get(request, "REJECT", byte[].class);
      medAdminProcessedGetResponse.getTAckResponses().addAll(processedGetResponse.getTAckResponses());
      this.transformRawResponseToBusinessObjects(processedGetResponse, medAdminProcessedGetResponse);
      return medAdminProcessedGetResponse;
   }

   public void transformRawResponseToBusinessObjects(ProcessedGetResponse<byte[]> processedGetResponse, RejectXmlProcessedGetResponse medAdminProcessedGetResponse) throws TechnicalConnectorException {
      RejectXmlProcessedMsgResponse adminProcessedMsgResponse;
      RejectInb rejectInbResponse;
      for(Iterator var3 = processedGetResponse.getMsgResponses().iterator(); var3.hasNext(); adminProcessedMsgResponse.getRejectInbResponses().add(rejectInbResponse)) {
         ProcessedMsgResponse<byte[]> processedMsgResponse = (ProcessedMsgResponse)var3.next();
         adminProcessedMsgResponse = new RejectXmlProcessedMsgResponse(processedMsgResponse);
         medAdminProcessedGetResponse.getMsgResponses().add(adminProcessedMsgResponse);
         byte[] businessResponse = (byte[])processedMsgResponse.getBusinessResponse();
         String receivedXml = new String(businessResponse);
         if (receivedXml.contains("\u0000")) {
            receivedXml = receivedXml.replaceAll("\u0000", "");
         }

         String type = ((Element)ConnectorXmlUtils.toObject(receivedXml, Object.class)).getLocalName();
         if (!"RejectInb".equals(type)) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{"[" + type + "] is not a valid root element for the business response"});
         }

         rejectInbResponse = (RejectInb)ConnectorXmlUtils.toObject(receivedXml, RejectInb.class);
         if (config.getBooleanProperty("genericasync.medadmin.validation.incoming.businessresponse", true)) {
            ValidatorHelper.validate(rejectInbResponse, "/mycarenet-commons/XSD/Reject.xsd");
         }
      }

   }
}
