package be.ehealth.business.mycarenetcommons.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ByteArrayDatasource;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.mycarenet.commons.core.v2.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v2.CareProviderType;
import be.fgov.ehealth.mycarenet.commons.core.v2.CareReceiverIdType;
import be.fgov.ehealth.mycarenet.commons.core.v2.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v2.IdType;
import be.fgov.ehealth.mycarenet.commons.core.v2.LicenseType;
import be.fgov.ehealth.mycarenet.commons.core.v2.NihiiType;
import be.fgov.ehealth.mycarenet.commons.core.v2.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v2.PackageType;
import be.fgov.ehealth.mycarenet.commons.core.v2.PartyType;
import be.fgov.ehealth.mycarenet.commons.core.v2.PeriodType;
import be.fgov.ehealth.mycarenet.commons.core.v2.RequestType;
import be.fgov.ehealth.mycarenet.commons.core.v2.RoutingType;
import be.fgov.ehealth.mycarenet.commons.core.v2.ValueRefString;
import java.io.IOException;
import javax.activation.DataHandler;
import org.apache.commons.io.IOUtils;

public final class SendRequestMapper implements ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public static CommonInputType mapCommonInput(CommonInput commonInput) {
      CommonInputType inputType = new CommonInputType();
      inputType.setOrigin(getOrigin(commonInput));
      inputType.setInputReference(commonInput.getInputReference());
      inputType.setRequest(getRequestType(commonInput.isTest()));
      return inputType;
   }

   public static RoutingType mapRouting(Routing inRouting) {
      RoutingType routing = new RoutingType();
      routing.setCareReceiver(getCareReceiver(inRouting.getCareReceiver()));
      routing.setPeriod(getPeriod(inRouting.getPeriod()));
      routing.setReferenceDate(inRouting.getReferenceDate());
      return routing;
   }

   public static BlobType mapBlobToBlobType(Blob inBlob) {
      BlobType blob = new BlobType();
      blob.setId(inBlob.getId());
      blob.setValue(inBlob.getContent());
      blob.setHashValue(inBlob.getHashValue());
      blob.setContentEncoding(inBlob.getContentEncoding());
      blob.setContentType(inBlob.getContentType());
      return blob;
   }

   public static Blob mapBlobTypeToBlob(BlobType inBlob) {
      Blob blob = new Blob();
      blob.setId(inBlob.getId());
      blob.setContent(inBlob.getValue());
      blob.setHashValue(inBlob.getHashValue());
      blob.setContentEncoding(inBlob.getContentEncoding());
      blob.setContentType(inBlob.getContentType());
      return blob;
   }

   private static OriginType getOrigin(CommonInput commonInput) {
      OriginType origin = new OriginType();
      origin.setCareProvider(getCareprovider(commonInput.getOrigin().getCareProvider()));
      origin.setPackage(getPackage(commonInput.getOrigin().getMcnPackageInfo()));
      origin.setSender(getParty(commonInput.getOrigin().getSender()));
      return origin;
   }

   private static CareProviderType getCareprovider(CareProvider inProvider) {
      CareProviderType careProvider = new CareProviderType();
      careProvider.setNihii(getNihii(inProvider.getNihii()));
      careProvider.setOrganization(getIdType(inProvider.getOrganization()));
      careProvider.setPhysicalPerson(getIdType(inProvider.getPhysicalPerson()));
      return careProvider;
   }

   private static RequestType getRequestType(boolean isTest) {
      RequestType requestType = new RequestType();
      requestType.setIsTest(isTest);
      return requestType;
   }

   private static CareReceiverIdType getCareReceiver(CareReceiverId inCareReceiver) {
      CareReceiverIdType careReceiver = new CareReceiverIdType();
      careReceiver.setMutuality(inCareReceiver.getMutuality());
      careReceiver.setRegNrWithMut(inCareReceiver.getRegistrationNumberWithMutuality());
      careReceiver.setSsin(inCareReceiver.getSsinNumber());
      return careReceiver;
   }

   private static PackageType getPackage(McnPackageInfo info) {
      PackageType type = new PackageType();
      type.setName(getValueRef(info.getPackageName(), (String)null));
      type.setLicense(getLicense(info.getUserName(), info.getPassword()));
      return type;
   }

   private static NihiiType getNihii(Nihii inNihii) {
      NihiiType nihii = null;
      if (inNihii != null) {
         nihii = new NihiiType();
         nihii.setValue(getValueRef(inNihii.getValue(), (String)null));
         nihii.setQuality(inNihii.getQuality());
      }

      return nihii;
   }

   private static IdType getIdType(Identification id) {
      IdType idType = null;
      if (id != null) {
         idType = new IdType();
         idType.setCbe(getValueRef(id.getCbe(), (String)null));
         idType.setName(getValueRef(id.getName(), (String)null));
         idType.setSsin(getValueRef(id.getSsin(), (String)null));
         idType.setNihii(getNihii(id.getNihii()));
      }

      return idType;
   }

   private static ValueRefString getValueRef(String value, String reference) {
      ValueRefString valueRef = null;
      if (value != null) {
         valueRef = new ValueRefString();
         valueRef.setValue(value);
         valueRef.setValueRef(reference);
      }

      return valueRef;
   }

   private static LicenseType getLicense(String userName, String password) {
      LicenseType license = new LicenseType();
      license.setUsername(userName);
      license.setPassword(password);
      return license;
   }

   private static PartyType getParty(Party inParty) {
      PartyType party = new PartyType();
      party.setOrganization(getIdType(inParty.getOrganization()));
      party.setPhysicalPerson(getIdType(inParty.getPhysicalPerson()));
      return party;
   }

   private static PeriodType getPeriod(Period inPeriod) {
      PeriodType period = null;
      if (inPeriod != null) {
         period = new PeriodType();
         period.setStart(inPeriod.getBegin());
         period.setEnd(inPeriod.getEnd());
      }

      return period;
   }

   public static Blob mapToBlob(be.cin.types.v1.Blob blob) throws TechnicalConnectorException {
      Blob result = new Blob();
      result.setContent(convertToByteArray(blob.getValue()));
      result.setId(blob.getId());
      result.setContentEncoding(blob.getContentEncoding());
      result.setHashValue(blob.getHashValue());
      result.setContentType(blob.getContentType());
      return result;
   }

   public static be.cin.types.v1.Blob mapBlobToCinBlob(Blob blob) {
      be.cin.types.v1.Blob result = new be.cin.types.v1.Blob();
      ByteArrayDatasource rawData = new ByteArrayDatasource(blob.getContent());
      DataHandler dh = new DataHandler(rawData);
      result.setValue(dh);
      result.setMessageName(blob.getMessageName());
      result.setId(blob.getId());
      result.setContentEncoding(blob.getContentEncoding());
      result.setHashValue(blob.getHashValue());
      result.setContentType(blob.getContentType());
      return result;
   }

   private static byte[] convertToByteArray(DataHandler value) throws TechnicalConnectorException {
      if (value == null) {
         return new byte[0];
      } else {
         try {
            return IOUtils.toByteArray(value.getInputStream());
         } catch (IOException var2) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, new Object[]{"IoException while converting dataHandler to byteArray", var2});
         }
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(BlobType.class);
      JaxbContextFactory.initJaxbContext(CareProviderType.class);
      JaxbContextFactory.initJaxbContext(CareReceiverIdType.class);
      JaxbContextFactory.initJaxbContext(CommonInputType.class);
      JaxbContextFactory.initJaxbContext(IdType.class);
      JaxbContextFactory.initJaxbContext(LicenseType.class);
      JaxbContextFactory.initJaxbContext(NihiiType.class);
      JaxbContextFactory.initJaxbContext(OriginType.class);
      JaxbContextFactory.initJaxbContext(PackageType.class);
      JaxbContextFactory.initJaxbContext(PartyType.class);
      JaxbContextFactory.initJaxbContext(PeriodType.class);
      JaxbContextFactory.initJaxbContext(RequestType.class);
      JaxbContextFactory.initJaxbContext(RoutingType.class);
      JaxbContextFactory.initJaxbContext(ValueRefString.class);
   }
}
