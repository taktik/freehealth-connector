package be.ehealth.businessconnector.dmg.mappers;

import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.fgov.ehealth.globalmedicalfile.core.v1.CommonInputType;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;

public final class CommonInputMapper {
   public static CommonInputType mapCommonInputType(CommonInput input) {
      List<String> myMappingFiles = new ArrayList();
      myMappingFiles.add("dozer/dmg-commoninput.xml");
      DozerBeanMapper mapper = new DozerBeanMapper();
      mapper.setMappingFiles(myMappingFiles);
      return (CommonInputType)mapper.map(input, CommonInputType.class);
   }
}
