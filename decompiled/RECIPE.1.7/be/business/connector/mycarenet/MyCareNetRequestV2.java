package be.business.connector.mycarenet;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.insurability.core.v2.CareProviderType;
import be.fgov.ehealth.insurability.core.v2.CareReceiverIdType;
import be.fgov.ehealth.insurability.core.v2.CommonInputType;
import be.fgov.ehealth.insurability.core.v2.IdType;
import be.fgov.ehealth.insurability.core.v2.InsurabilityForPharmacistRequestType;
import be.fgov.ehealth.insurability.core.v2.LicenseType;
import be.fgov.ehealth.insurability.core.v2.NihiiType;
import be.fgov.ehealth.insurability.core.v2.OriginType;
import be.fgov.ehealth.insurability.core.v2.PackageType;
import be.fgov.ehealth.insurability.core.v2.RecordCommonInputType;
import be.fgov.ehealth.insurability.core.v2.RequestType;
import be.fgov.ehealth.insurability.core.v2.SamlAttributeType;
import be.fgov.ehealth.insurability.core.v2.ValueRefString;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistRequest;
import java.math.BigDecimal;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang.StringUtils;

public class MyCareNetRequestV2 {
   public GetInsurabilityForPharmacistRequest createRequest(String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String recordCommonInputReference, String recordCommonInputUserReference) throws IntegrationModuleException, DatatypeConfigurationException {
      GetInsurabilityForPharmacistRequest request = new GetInsurabilityForPharmacistRequest();
      request.setCommonInput(this.createCommonInput(password, userName, pharmacyHolder, pharmcayNihii, pharmacySSIN, packageName));
      request.setRecordCommonInput(this.createRecordCommonInputType(recordCommonInputReference, recordCommonInputUserReference));
      XMLGregorianCalendar d = DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
      request.setInsurabilityRequest(this.createInsurabilityRequestPart(d, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut));
      SamlAttributeType extendedInfo = new SamlAttributeType();
      request.setExtendedInformation(extendedInfo);
      return request;
   }

   private InsurabilityForPharmacistRequestType createInsurabilityRequestPart(XMLGregorianCalendar date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut) {
      InsurabilityForPharmacistRequestType insurabilityForPharmacistRequestType = new InsurabilityForPharmacistRequestType();
      insurabilityForPharmacistRequestType.setDate(date);
      insurabilityForPharmacistRequestType.setRequestType(type);
      CareReceiverIdType careReceiverIdType = new CareReceiverIdType();
      if (!StringUtils.isEmpty(careReceiverSSIN)) {
         careReceiverIdType.setSsin(careReceiverSSIN);
      }

      if (!StringUtils.isEmpty(careReceiverMutuality)) {
         careReceiverIdType.setMutuality(careReceiverMutuality);
      }

      if (!StringUtils.isEmpty(careReceiverRegNrWithMut)) {
         careReceiverIdType.setRegNrWithMut(careReceiverRegNrWithMut);
      }

      insurabilityForPharmacistRequestType.setCareReceiver(careReceiverIdType);
      return insurabilityForPharmacistRequestType;
   }

   private RecordCommonInputType createRecordCommonInputType(String recordCommonInputReference, String recordCommonInputUserReference) {
      RecordCommonInputType paramRecordCommonInputType = new RecordCommonInputType();
      paramRecordCommonInputType.setReference(new BigDecimal(recordCommonInputReference));
      paramRecordCommonInputType.setUserReference(recordCommonInputUserReference);
      return paramRecordCommonInputType;
   }

   private CommonInputType createCommonInput(String password, String userName, String pharmacyHolder, String pharmcayNihii, String pharmcaySSIN, String packageName) {
      CareProviderType paramCareProviderType = new CareProviderType();
      RequestType paramRequestType = new RequestType();
      paramRequestType.setIsTest(false);
      LicenseType paramLicenseType = new LicenseType();
      OriginType paramOriginType = new OriginType();
      PackageType paramPackageType = new PackageType();
      ValueRefString packageNameString = new ValueRefString();
      packageNameString.setValue(packageName);
      paramPackageType.setName(packageNameString);
      paramLicenseType.setPassword(password);
      paramLicenseType.setUsername(userName);
      paramPackageType.setLicense(paramLicenseType);
      paramOriginType.setPackage(paramPackageType);
      NihiiType pharmacyHold = new NihiiType();
      ValueRefString pharmacyHolderValue = new ValueRefString();
      pharmacyHolderValue.setValue(pharmacyHolder);
      pharmacyHold.setQuality("PHARMACY-HOLDER");
      pharmacyHold.setValue(pharmacyHolderValue);
      paramCareProviderType.setNihii(pharmacyHold);
      ValueRefString paramValueRefString = new ValueRefString();
      IdType organisation = new IdType();
      paramValueRefString.setValue("A Pharmacy");
      organisation.setName(paramValueRefString);
      ValueRefString pharmcayNihiiString = new ValueRefString();
      NihiiType OrganisationNihii = new NihiiType();
      pharmcayNihiiString.setValue(pharmcayNihii);
      OrganisationNihii.setQuality("PHARMACY");
      OrganisationNihii.setValue(pharmcayNihiiString);
      organisation.setNihii(OrganisationNihii);
      paramCareProviderType.setOrganization(organisation);
      ValueRefString physicalPersonName = new ValueRefString();
      ValueRefString physicalPersonSSIN = new ValueRefString();
      IdType physicalPerson = new IdType();
      physicalPersonName.setValue("A Pharmacy Holder");
      physicalPersonSSIN.setValue(pharmcaySSIN);
      physicalPerson.setName(physicalPersonName);
      physicalPerson.setSsin(physicalPersonSSIN);
      paramCareProviderType.setPhysicalPerson(physicalPerson);
      paramOriginType.setCareProvider(paramCareProviderType);
      CommonInputType commonInputType = new CommonInputType();
      commonInputType.setRequest(paramRequestType);
      commonInputType.setOrigin(paramOriginType);
      return commonInputType;
   }
}
