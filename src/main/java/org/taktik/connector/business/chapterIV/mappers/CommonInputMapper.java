package org.taktik.connector.business.chapterIV.mappers;

import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.technical.mapper.MapperFactory;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;

public final class CommonInputMapper {
   private CommonInputMapper() {
      throw new UnsupportedOperationException();
   }

   public static CommonInputType mapCommonInputType(CommonInput input) {
      return MapperFactory.getMapper("dozer/chapter4-commoninput.xml").map(input, CommonInputType.class);
   }
}
