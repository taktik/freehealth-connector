package be.fgov.ehealth.technicalconnector.services.daas;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;

public interface DataAttributeService {
   Map<String, List<AttributeValue>> invoke(Map<String, List<String>> var1, DateTime var2, DateTime var3) throws TechnicalConnectorException;

   Map<String, List<AttributeValue>> invoke(Map<String, List<String>> var1) throws TechnicalConnectorException;
}
