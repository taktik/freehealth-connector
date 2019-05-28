package be.ehealth.businessconnector.genins.builders.impl;

import be.ehealth.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import be.ehealth.business.mycarenetdomaincommons.util.McnConfigUtil;
import be.ehealth.businessconnector.genins.builders.RequestObjectBuilder;
import be.ehealth.businessconnector.genins.domain.RequestParameters;
import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.businessconnector.genins.mapper.CommonInputMapper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.fgov.ehealth.genericinsurability.core.v1.CareReceiverIdType;
import be.fgov.ehealth.genericinsurability.core.v1.CommonInputType;
import be.fgov.ehealth.genericinsurability.core.v1.InsurabilityRequestDetailType;
import be.fgov.ehealth.genericinsurability.core.v1.PeriodType;
import be.fgov.ehealth.genericinsurability.core.v1.RecordCommonInputType;
import be.fgov.ehealth.genericinsurability.core.v1.SingleInsurabilityRequestType;
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType;
import java.math.BigDecimal;

public final class RequestObjectBuilderImpl implements RequestObjectBuilder {
   public GetInsurabilityAsXmlOrFlatRequestType createGetInsurabilityRequest(RequestParameters reqestParameters, boolean isTest) throws TechnicalConnectorException, GenInsBusinessConnectorException, InstantiationException {
      GetInsurabilityAsXmlOrFlatRequestType getInsurabilityAsXmlOrFlatRequestType = new GetInsurabilityAsXmlOrFlatRequestType();
      String commonReferenceId = IdGeneratorFactory.getIdGenerator().generateId();
      RecordCommonInputType recordCommonInputType = new RecordCommonInputType();
      recordCommonInputType.setInputReference(new BigDecimal(commonReferenceId));
      getInsurabilityAsXmlOrFlatRequestType.setRecordCommonInput(recordCommonInputType);
      String inputReferenceId = IdGeneratorFactory.getIdGenerator().generateId();
      CommonInputType commonInputType = CommonInputMapper.mapCommonInput(RequestBuilderFactory.getCommonBuilder("genins").createCommonInput(McnConfigUtil.retrievePackageInfo("genins"), isTest, inputReferenceId));
      getInsurabilityAsXmlOrFlatRequestType.setCommonInput(commonInputType);
      SingleInsurabilityRequestType singleInsurabilityRequestType = new SingleInsurabilityRequestType();
      CareReceiverIdType careReceiverId = new CareReceiverIdType();
      careReceiverId.setInss(reqestParameters.getInss());
      careReceiverId.setMutuality(reqestParameters.getMutuality());
      careReceiverId.setRegNrWithMut(reqestParameters.getRegNrWithMut());
      singleInsurabilityRequestType.setCareReceiverId(careReceiverId);
      InsurabilityRequestDetailType insurabilityRequestDetailType = new InsurabilityRequestDetailType();
      insurabilityRequestDetailType.setInsurabilityRequestType(reqestParameters.getInsurabilityRequestType());
      PeriodType periodType = new PeriodType();
      periodType.setPeriodEnd(reqestParameters.getPeriodEnd());
      periodType.setPeriodStart(reqestParameters.getPeriodStart());
      insurabilityRequestDetailType.setPeriod(periodType);
      insurabilityRequestDetailType.setInsurabilityContactType(reqestParameters.getInsurabilityContactType());
      insurabilityRequestDetailType.setInsurabilityReference(reqestParameters.getInsurabilityReference());
      singleInsurabilityRequestType.setInsurabilityRequestDetail(insurabilityRequestDetailType);
      getInsurabilityAsXmlOrFlatRequestType.setRequest(singleInsurabilityRequestType);
      return getInsurabilityAsXmlOrFlatRequestType;
   }
}
