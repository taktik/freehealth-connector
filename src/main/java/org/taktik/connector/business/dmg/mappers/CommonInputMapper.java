package org.taktik.connector.business.dmg.mappers;

import org.taktik.connector.business.mycarenetdomaincommons.domain.CommonInput;
import be.fgov.ehealth.globalmedicalfile.core.v1.CommonInputType;

public final class CommonInputMapper {
   public static CommonInputType mapCommonInputType(CommonInput input) {
//      List<String> myMappingFiles = new ArrayList();
//      myMappingFiles.add("dozer/dmg-commoninput.xml");
//      DozerBeanMapper mapper = new DozerBeanMapper();
//      mapper.setMappingFiles(myMappingFiles);
//      return (CommonInputType)mapper.map(input, CommonInputType.class);
      return new CommonInputType();
   }
}
