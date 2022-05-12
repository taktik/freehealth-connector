package be.ehealth.businessconnector.genins.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.fgov.ehealth.genericinsurability.core.v1.CareProviderType;
import be.fgov.ehealth.genericinsurability.core.v1.CommonInputType;
import be.fgov.ehealth.genericinsurability.core.v1.IdType;
import be.fgov.ehealth.genericinsurability.core.v1.LicenseType;
import be.fgov.ehealth.genericinsurability.core.v1.NihiiType;
import be.fgov.ehealth.genericinsurability.core.v1.OriginType;
import be.fgov.ehealth.genericinsurability.core.v1.PackageType;
import be.fgov.ehealth.genericinsurability.core.v1.RequestType;
import be.fgov.ehealth.genericinsurability.core.v1.ValueRefString;

public class CommonInputMapperImpl implements CommonInputMapper {
   public CommonInputMapperImpl() {
   }

   public CommonInputType map(CommonInput input) {
      if (input == null) {
         return null;
      } else {
         CommonInputType commonInputType = new CommonInputType();
         commonInputType.setRequest(this.commonInputToRequestType(input));
         commonInputType.setOrigin(this.map(input.getOrigin()));
         commonInputType.setInputReference(input.getInputReference());
         return commonInputType;
      }
   }

   public OriginType map(Origin input) {
      if (input == null) {
         return null;
      } else {
         OriginType originType = new OriginType();
         originType.setSiteID(this.originToValueRefString(input));
         originType.setPackage(this.map(input.getPackageInfo()));
         originType.setCareProvider(this.map(input.getCareProvider()));
         return originType;
      }
   }

   public PackageType map(PackageInfo input) {
      if (input == null) {
         return null;
      } else {
         PackageType packageType = new PackageType();
         packageType.setName(this.packageInfoToValueRefString(input));
         packageType.setLicense(this.packageInfoToLicenseType(input));
         return packageType;
      }
   }

   public CareProviderType map(CareProvider input) {
      if (input == null) {
         return null;
      } else {
         CareProviderType careProviderType = new CareProviderType();
         careProviderType.setNihii(this.map(input.getNihii()));
         careProviderType.setPhysicalPerson(this.map(input.getPhysicalPerson()));
         careProviderType.setOrganization(this.map(input.getOrganization()));
         return careProviderType;
      }
   }

   public NihiiType map(Nihii input) {
      if (input == null) {
         return null;
      } else {
         NihiiType nihiiType = new NihiiType();
         nihiiType.setValue(this.nihiiToValueRefString(input));
         nihiiType.setQuality(input.getQuality());
         return nihiiType;
      }
   }

   public IdType map(Identification input) {
      if (input == null) {
         return null;
      } else {
         IdType idType = new IdType();
         idType.setName(this.identificationToValueRefString(input));
         idType.setSsin(this.identificationToValueRefString1(input));
         idType.setCbe(this.identificationToValueRefString2(input));
         idType.setNihii(this.map(input.getNihii()));
         return idType;
      }
   }

   public CareProviderType map(Party input) {
      if (input == null) {
         return null;
      } else {
         CareProviderType careProviderType = new CareProviderType();
         careProviderType.setPhysicalPerson(this.map(input.getPhysicalPerson()));
         careProviderType.setOrganization(this.map(input.getOrganization()));
         return careProviderType;
      }
   }

   protected RequestType commonInputToRequestType(CommonInput commonInput) {
      if (commonInput == null) {
         return null;
      } else {
         RequestType requestType = new RequestType();
         if (commonInput.isTest() != null) {
            requestType.setIsTest(commonInput.isTest());
         }

         return requestType;
      }
   }

   protected ValueRefString originToValueRefString(Origin origin) {
      if (origin == null) {
         return null;
      } else {
         ValueRefString valueRefString = new ValueRefString();
         valueRefString.setValue(origin.getSiteId());
         return valueRefString;
      }
   }

   protected ValueRefString packageInfoToValueRefString(PackageInfo packageInfo) {
      if (packageInfo == null) {
         return null;
      } else {
         ValueRefString valueRefString = new ValueRefString();
         valueRefString.setValue(packageInfo.getPackageName());
         return valueRefString;
      }
   }

   protected LicenseType packageInfoToLicenseType(PackageInfo packageInfo) {
      if (packageInfo == null) {
         return null;
      } else {
         LicenseType licenseType = new LicenseType();
         licenseType.setUsername(packageInfo.getUserName());
         licenseType.setPassword(packageInfo.getPassword());
         return licenseType;
      }
   }

   protected ValueRefString nihiiToValueRefString(Nihii nihii) {
      if (nihii == null) {
         return null;
      } else {
         ValueRefString valueRefString = new ValueRefString();
         valueRefString.setValue(nihii.getValue());
         return valueRefString;
      }
   }

   protected ValueRefString identificationToValueRefString(Identification identification) {
      if (identification == null) {
         return null;
      } else {
         ValueRefString valueRefString = new ValueRefString();
         valueRefString.setValue(identification.getName());
         return valueRefString;
      }
   }

   protected ValueRefString identificationToValueRefString1(Identification identification) {
      if (identification == null) {
         return null;
      } else {
         ValueRefString valueRefString = new ValueRefString();
         valueRefString.setValue(identification.getSsin());
         return valueRefString;
      }
   }

   protected ValueRefString identificationToValueRefString2(Identification identification) {
      if (identification == null) {
         return null;
      } else {
         ValueRefString valueRefString = new ValueRefString();
         valueRefString.setValue(identification.getCbe());
         return valueRefString;
      }
   }
}
