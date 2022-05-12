package be.ehealth.businessconnector.genericasync.mappers;

import be.cin.mycarenet.esb.common.v2.CareProviderType;
import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.IdType;
import be.cin.mycarenet.esb.common.v2.NihiiType;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.mycarenet.esb.common.v2.PackageType;
import be.cin.mycarenet.esb.common.v2.PartyType;
import be.ehealth.business.mycarenetdomaincommons.domain.CareProvider;
import be.ehealth.business.mycarenetdomaincommons.domain.Identification;
import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.business.mycarenetdomaincommons.domain.Nihii;
import be.ehealth.business.mycarenetdomaincommons.domain.Origin;
import be.ehealth.business.mycarenetdomaincommons.domain.Party;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CommonInputMapper {
   @Mapping(
      source = "test",
      target = "request.isTest"
   )
   CommonInput map(be.ehealth.business.mycarenetdomaincommons.domain.CommonInput var1);

   @Mappings({@Mapping(
   source = "packageInfo",
   target = "package"
), @Mapping(
   source = "siteId",
   target = "siteID.value"
)})
   OrigineType map(Origin var1);

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

   PackageType mapMCNPackage(McnPackageInfo var1);
}
