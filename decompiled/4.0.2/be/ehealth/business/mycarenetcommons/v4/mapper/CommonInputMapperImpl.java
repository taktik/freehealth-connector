package be.ehealth.business.mycarenetcommons.v4.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.Actor;
import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.business.mycarenetdomaincommons.domain.Reference;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.fgov.ehealth.mycarenet.commons.core.v4.ActorType;
import be.fgov.ehealth.mycarenet.commons.core.v4.AttributeType;
import be.fgov.ehealth.mycarenet.commons.core.v4.CareProviderType;
import be.fgov.ehealth.mycarenet.commons.core.v4.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v4.IdType;
import be.fgov.ehealth.mycarenet.commons.core.v4.Identifier;
import be.fgov.ehealth.mycarenet.commons.core.v4.LicenseType;
import be.fgov.ehealth.mycarenet.commons.core.v4.NihiiType;
import be.fgov.ehealth.mycarenet.commons.core.v4.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v4.PackageType;
import be.fgov.ehealth.mycarenet.commons.core.v4.PartyType;
import be.fgov.ehealth.mycarenet.commons.core.v4.ReferenceType;
import be.fgov.ehealth.mycarenet.commons.core.v4.RequestType;
import be.fgov.ehealth.mycarenet.commons.core.v4.ValueRefString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
         List list1;
         if (commonInputType.getReferences() != null) {
            list1 = this.referenceListToReferenceTypeList(input.getReferences());
            if (list1 != null) {
               commonInputType.getReferences().addAll(list1);
            }
         }

         if (commonInputType.getAttributes() != null) {
            list1 = this.attributeListToAttributeTypeList(input.getAttributes());
            if (list1 != null) {
               commonInputType.getAttributes().addAll(list1);
            }
         }

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
         originType.setSender(this.map(input.getSender()));
         if (originType.getActors() != null) {
            List<ActorType> list = this.actorListToActorTypeList(input.getActors());
            if (list != null) {
               originType.getActors().addAll(list);
            }
         }

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

   public ReferenceType map(Reference input) {
      if (input == null) {
         return null;
      } else {
         ReferenceType referenceType = new ReferenceType();
         referenceType.setValue(input.getValue());
         referenceType.setType(input.getType());
         return referenceType;
      }
   }

   public AttributeType map(Attribute input) {
      if (input == null) {
         return null;
      } else {
         AttributeType attributeType = new AttributeType();
         attributeType.setValue(input.getValue());
         attributeType.setKey(input.getKey());
         return attributeType;
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

   protected List<ReferenceType> referenceListToReferenceTypeList(List<Reference> list) {
      if (list == null) {
         return null;
      } else {
         List<ReferenceType> list1 = new ArrayList(list.size());
         Iterator var3 = list.iterator();

         while(var3.hasNext()) {
            Reference reference = (Reference)var3.next();
            list1.add(this.map(reference));
         }

         return list1;
      }
   }

   protected List<AttributeType> attributeListToAttributeTypeList(List<Attribute> list) {
      if (list == null) {
         return null;
      } else {
         List<AttributeType> list1 = new ArrayList(list.size());
         Iterator var3 = list.iterator();

         while(var3.hasNext()) {
            Attribute attribute = (Attribute)var3.next();
            list1.add(this.map(attribute));
         }

         return list1;
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

   protected Identifier identifierToIdentifier(be.ehealth.business.mycarenetdomaincommons.domain.Identifier identifier) {
      if (identifier == null) {
         return null;
      } else {
         Identifier identifier1 = new Identifier();
         identifier1.setValue(identifier.getValue());
         identifier1.setType(identifier.getType());
         return identifier1;
      }
   }

   protected List<Identifier> identifierListToIdentifierList(List<be.ehealth.business.mycarenetdomaincommons.domain.Identifier> list) {
      if (list == null) {
         return null;
      } else {
         List<Identifier> list1 = new ArrayList(list.size());
         Iterator var3 = list.iterator();

         while(var3.hasNext()) {
            be.ehealth.business.mycarenetdomaincommons.domain.Identifier identifier = (be.ehealth.business.mycarenetdomaincommons.domain.Identifier)var3.next();
            list1.add(this.identifierToIdentifier(identifier));
         }

         return list1;
      }
   }

   protected ActorType actorToActorType(Actor actor) {
      if (actor == null) {
         return null;
      } else {
         ActorType actorType = new ActorType();
         actorType.setType(actor.getType());
         actorType.setSubType(actor.getSubType());
         actorType.setRole(actor.getRole());
         List list1;
         if (actorType.getIdentifiers() != null) {
            list1 = this.identifierListToIdentifierList(actor.getIdentifiers());
            if (list1 != null) {
               actorType.getIdentifiers().addAll(list1);
            }
         }

         if (actorType.getAttributes() != null) {
            list1 = this.attributeListToAttributeTypeList(actor.getAttributes());
            if (list1 != null) {
               actorType.getAttributes().addAll(list1);
            }
         }

         return actorType;
      }
   }

   protected List<ActorType> actorListToActorTypeList(List<Actor> list) {
      if (list == null) {
         return null;
      } else {
         List<ActorType> list1 = new ArrayList(list.size());
         Iterator var3 = list.iterator();

         while(var3.hasNext()) {
            Actor actor = (Actor)var3.next();
            list1.add(this.actorToActorType(actor));
         }

         return list1;
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
