package be.ehealth.businessconnector.genericasync.mappers;

import be.cin.mycarenet.esb.common.v2.CareProviderType;
import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.IdType;
import be.cin.mycarenet.esb.common.v2.LicenseType;
import be.cin.mycarenet.esb.common.v2.NihiiType;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.mycarenet.esb.common.v2.PackageType;
import be.cin.mycarenet.esb.common.v2.PartyType;
import be.cin.mycarenet.esb.common.v2.RequestType;
import be.cin.mycarenet.esb.common.v2.ValueRefString;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;

public class CommonInputMapperImpl implements CommonInputMapper {
   public CommonInputMapperImpl() {
   }

   public CommonInput map(be.ehealth.business.mycarenetdomaincommons.domain.CommonInput input) {
      if (input == null) {
         return null;
      } else {
         CommonInput commonInput = new CommonInput();
         commonInput.setRequest(this.commonInputToRequestType(input));
         commonInput.setOrigin(this.map(input.getOrigin()));
         commonInput.setInputReference(input.getInputReference());
         return commonInput;
      }
   }

   public OrigineType map(Origin input) {
      if (input == null) {
         return null;
      } else {
         OrigineType origineType = new OrigineType();
         origineType.setSiteID(this.originToValueRefString(input));
         origineType.setPackage(this.map(input.getPackageInfo()));
         origineType.setCareProvider(this.map(input.getCareProvider()));
         origineType.setSender(this.map(input.getSender()));
         return origineType;
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

   public PartyType map(Party input) {
      if (input == null) {
         return null;
      } else {
         PartyType partyType = new PartyType();
         partyType.setPhysicalPerson(this.map(input.getPhysicalPerson()));
         partyType.setOrganization(this.map(input.getOrganization()));
         return partyType;
      }
   }

   public PackageType mapMCNPackage(McnPackageInfo input) {
      if (input == null) {
         return null;
      } else {
         PackageType packageType = new PackageType();
         return packageType;
      }
   }

   protected RequestType commonInputToRequestType(be.ehealth.business.mycarenetdomaincommons.domain.CommonInput commonInput) {
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
