package be.ehealth.businessconnector.mycarenet.attest.mappers;

import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.technicalconnector.mapper.MapperFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;

public final class CommonInputMapper {
   public static CommonInputType mapCommonInputType(CommonInput input) {
      return (CommonInputType)MapperFactory.getMapper("dozer/attest-commoninput.xml").map(input, CommonInputType.class);
   }
}
