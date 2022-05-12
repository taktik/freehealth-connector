package be.ehealth.business.mycarenetcommons.v3.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.fgov.ehealth.mycarenet.commons.core.v3.CareProviderType;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.IdType;
import be.fgov.ehealth.mycarenet.commons.core.v3.NihiiType;
import be.fgov.ehealth.mycarenet.commons.core.v3.OriginType;
import be.fgov.ehealth.mycarenet.commons.core.v3.PackageType;
import be.fgov.ehealth.mycarenet.commons.core.v3.PartyType;
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
}
