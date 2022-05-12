package be.ehealth.business.mycarenetcommons.v4.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.business.mycarenetdomaincommons.domain.Reference;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.fgov.ehealth.mycarenet.commons.core.v4.AttributeType;
import be.fgov.ehealth.mycarenet.commons.core.v4.CareProviderType;
import be.fgov.ehealth.mycarenet.commons.core.v4.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v4.IdType;
import be.fgov.ehealth.mycarenet.commons.core.v4.NihiiType;
import be.fgov.ehealth.mycarenet.commons.core.v4.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v4.PackageType;
import be.fgov.ehealth.mycarenet.commons.core.v4.PartyType;
import be.fgov.ehealth.mycarenet.commons.core.v4.ReferenceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CommonInputMapper {
   @Mapping(
      source = "test",
      target = "request.isTest"
   )
   CommonInputType map(CommonInput var1);

   @Mappings({@Mapping(
   source = "packageInfo",
   target = "package"
), @Mapping(
   source = "siteId",
   target = "siteID.value"
)})
   OriginType map(Origin var1);

   @Mappings({@Mapping(
   source = "packageName",
   target = "name.value"
), @Mapping(
   source = "userName",
   target = "license.username"
), @Mapping(
   source = "password",
   target = "license.password"
)})
   PackageType map(PackageInfo var1);

   CareProviderType map(CareProvider var1);

   @Mapping(
      source = "value",
      target = "value.value"
   )
   NihiiType map(Nihii var1);

   @Mappings({@Mapping(
   source = "name",
   target = "name.value"
), @Mapping(
   source = "nihii",
   target = "nihii"
), @Mapping(
   source = "ssin",
   target = "ssin.value"
), @Mapping(
   source = "cbe",
   target = "cbe.value"
)})
   IdType map(Identification var1);

   PartyType map(Party var1);

   ReferenceType map(Reference var1);

   AttributeType map(Attribute var1);
}
