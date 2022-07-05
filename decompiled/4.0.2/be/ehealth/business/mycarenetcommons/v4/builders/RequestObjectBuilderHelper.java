package be.ehealth.business.mycarenetcommons.v4.builders;

import be.ehealth.business.common.domain.Patient;
import be.ehealth.business.mycarenetcommons.v4.mapper.BlobMapper;
import be.ehealth.business.mycarenetcommons.v4.mapper.CommonInputMapper;
import be.ehealth.business.mycarenetcommons.v4.mapper.RoutingMapper;
import be.ehealth.business.mycarenetdomaincommons.builders.CommonBuilder;
import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.Ssin;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.fgov.ehealth.mycarenet.commons.core.v4.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v4.RoutingType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import java.time.LocalDateTime;
import java.util.List;
import org.joda.time.DateTime;
import org.mapstruct.factory.Mappers;

public class RequestObjectBuilderHelper<T extends SendRequestType> {
   public RequestObjectBuilderHelper() {
   }

   public T buildSendRequestType(boolean isTest, String inputReference, List<Attribute> attributes, Blob blob, String projectIdentifier, Class<T> clazz) throws TechnicalConnectorException {
      SendRequestType sendRequestType;
      try {
         sendRequestType = (SendRequestType)clazz.newInstance();
      } catch (Exception var10) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNEXPECTED_ERROR, var10, new Object[0]);
      }

      CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder(projectIdentifier);
      CommonInputType mapCommonInputType = ((CommonInputMapper)Mappers.getMapper(CommonInputMapper.class)).map(commonBuilder.createCommonInput(McnConfigUtil.retrievePackageInfo(projectIdentifier), isTest, inputReference, (List)null, attributes));
      sendRequestType.setCommonInput(mapCommonInputType);
      sendRequestType.setId(IdGeneratorFactory.getIdGenerator("xsid").generateId());
      sendRequestType.setIssueInstant(new DateTime());
      sendRequestType.setDetail(BlobMapper.mapBlobTypefromBlob(blob));
      return sendRequestType;
   }

   public void checkParameterNotNull(Object references, String parameterName) throws TechnicalConnectorException {
      if (references == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{parameterName});
      }
   }

   public static class WithoutRouting implements Routing {
      public WithoutRouting() {
      }

      public RoutingType createRouting() {
         return null;
      }
   }

   public static class WithRouting implements Routing {
      Ssin patientSsin;
      LocalDateTime referenceDate;
      String projectIdentifier;

      public WithRouting(Ssin patientSsin, LocalDateTime referenceDate, String projectIdentifier) {
         this.patientSsin = patientSsin;
         this.referenceDate = referenceDate;
         this.projectIdentifier = projectIdentifier;
      }

      public RoutingType createRouting() throws TechnicalConnectorException {
         CommonBuilder commonBuilder = RequestBuilderFactory.getCommonBuilder(this.projectIdentifier);
         Patient patient = (new Patient.Builder()).withInss(this.patientSsin.getValue()).build();
         RoutingMapper mapper = (RoutingMapper)Mappers.getMapper(RoutingMapper.class);
         return mapper.map(commonBuilder.createRouting(patient, this.referenceDate));
      }
   }

   interface Routing {
      RoutingType createRouting() throws TechnicalConnectorException;
   }
}
