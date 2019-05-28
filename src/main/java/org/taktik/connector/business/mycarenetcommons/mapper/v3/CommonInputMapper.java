package org.taktik.connector.business.mycarenetcommons.mapper.v3;

import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import org.taktik.connector.technical.mapper.MapperFactory;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;

public final class CommonInputMapper {
   private CommonInputMapper() {
   }

   public static CommonInputType mapCommonInputType(CommonInput input) {
      return (CommonInputType)MapperFactory.getMapper("dozer/commoninput.xml").map(input, CommonInputType.class);
   }
}
