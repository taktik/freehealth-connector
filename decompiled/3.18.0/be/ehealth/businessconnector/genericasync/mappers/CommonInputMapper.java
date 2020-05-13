package be.ehealth.businessconnector.genericasync.mappers;

import be.cin.mycarenet.esb.common.v2.CareProviderType;
import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.IdType;
import be.cin.mycarenet.esb.common.v2.LicenseType;
import be.cin.mycarenet.esb.common.v2.NihiiType;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.mycarenet.esb.common.v2.PackageType;
import be.cin.mycarenet.esb.common.v2.ValueRefString;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;

public final class CommonInputMapper {
   private CommonInputMapper() {
   }

   public static CommonInput mapCommonInputType(be.ehealth.business.mycarenetdomaincommons.domain.CommonInput input) {
      List<String> myMappingFiles = new ArrayList();
      myMappingFiles.add("dozer/genasync-commoninput.xml");
      DozerBeanMapper mapper = new DozerBeanMapper();
      mapper.setMappingFiles(myMappingFiles);
      CommonInput destObject = new CommonInput();
      mapper.map(input, destObject);
      return destObject;
   }

   public static OrigineType mapOrigin(Origin origin) {
      OrigineType result = new OrigineType();
      CareProvider careProvider = origin.getCareProvider();
      if (careProvider != null) {
         result.setCareProvider(mapCareProvider(careProvider));
      }

      McnPackageInfo packageInfo = origin.getMcnPackageInfo();
      if (packageInfo != null) {
         result.setPackage(mapMcnPackage(packageInfo));
      }

      String siteId = origin.getSiteId();
      if (siteId != null) {
         result.setSiteID(createValueRefString(siteId));
      }

      return result;
   }

   /** @deprecated */
   @Deprecated
   static PackageType mapPackage(PackageInfo packageInfo) {
      PackageType result = new PackageType();
      LicenseType license = new LicenseType();
      String password = packageInfo.getPassword();
      if (password != null) {
         license.setPassword(password);
      }

      String userName = packageInfo.getUserName();
      if (userName != null) {
         license.setUsername(userName);
      }

      result.setLicense(license);
      String packageName = packageInfo.getPackageName();
      if (packageName != null) {
         result.setName(createValueRefString(packageName));
      }

      return result;
   }

   static PackageType mapMcnPackage(McnPackageInfo packageInfo) {
      return mapPackage(packageInfo);
   }

   static CareProviderType mapCareProvider(CareProvider careProvider) {
      CareProviderType result = new CareProviderType();
      Nihii nihii = careProvider.getNihii();
      if (nihii != null) {
         result.setNihii(mapNihii(nihii));
      }

      Identification organization = careProvider.getOrganization();
      if (organization != null) {
         result.setOrganization(mapIdType(organization));
      }

      Identification physicalPerson = careProvider.getPhysicalPerson();
      if (physicalPerson != null) {
         result.setPhysicalPerson(mapIdType(physicalPerson));
      }

      return result;
   }

   static IdType mapIdType(Identification organization) {
      IdType result = new IdType();
      String cbe = organization.getCbe();
      if (cbe != null) {
         result.setCbe(createValueRefString(cbe));
      }

      String name = organization.getName();
      if (name != null) {
         result.setName(createValueRefString(name));
      }

      Nihii nihii = organization.getNihii();
      if (nihii != null) {
         result.setNihii(mapNihii(nihii));
      }

      String ssin = organization.getSsin();
      if (ssin != null) {
         result.setSsin(createValueRefString(ssin));
      }

      return result;
   }

   static NihiiType mapNihii(Nihii nihii) {
      NihiiType result = new NihiiType();
      String quality = nihii.getQuality();
      if (quality != null) {
         result.setQuality(quality);
      }

      String value = nihii.getValue();
      if (value != null) {
         result.setValue(createValueRefString(value));
      }

      return result;
   }

   static ValueRefString createValueRefString(String value) {
      ValueRefString result = new ValueRefString();
      result.setValue(value);
      return result;
   }
}
