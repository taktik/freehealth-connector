package be.ehealth.businessconnector.genins.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.CommonInput;
import be.ehealth.businessconnector.genins.exception.GenInsBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.genericinsurability.core.v1.CommonInputType;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;

public final class CommonInputMapper {
   private CommonInputMapper() {
   }

   public static CommonInputType mapCommonInput(CommonInput input) throws TechnicalConnectorException, GenInsBusinessConnectorException, InstantiationException {
      List<String> myMappingFiles = new ArrayList();
      myMappingFiles.add("dozer/genins-commoninput.xml");
      DozerBeanMapper mapper = new DozerBeanMapper();
      mapper.setMappingFiles(myMappingFiles);
      CommonInputType destObject = new CommonInputType();
      mapper.map(input, destObject);
      return destObject;
   }
}
