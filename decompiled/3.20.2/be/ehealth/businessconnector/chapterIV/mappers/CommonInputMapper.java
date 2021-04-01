package be.ehealth.businessconnector.chapterIV.mappers;

import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.technicalconnector.mapper.MapperFactory;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;

public final class CommonInputMapper {
   private CommonInputMapper() {
      throw new UnsupportedOperationException();
   }

   public static CommonInputType mapCommonInputType(CommonInput input) {
      return (CommonInputType)MapperFactory.getMapper("dozer/chapter4-commoninput.xml").map(input, CommonInputType.class);
   }
}
