package org.taktik.connector.technical.service.sts.utils;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.SessionManagementExceptionValues;
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute;
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SAMLConfigHelper {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLConfigHelper.class);
   private static final String PROP_DELIMITER = ",";
   private static final int SAMLATTRIBUTE_EXPECTED_PROP_SIZE = 3;
   private static final int SAMLDESIGNATOR_EXPECTED_PROP_SIZE = 2;
   private static ConfigValidator config = ConfigFactory.getConfigValidator();

   public static List<SAMLAttributeDesignator> getSAMLAttributeDesignators(String key) throws SessionManagementException {
      List<SAMLAttributeDesignator> designators = new ArrayList();
      List<String> designatorAttributes = config.getMatchingProperties(key);
      Iterator i$ = designatorAttributes.iterator();

      while(i$.hasNext()) {
         String attribute = (String)i$.next();
         String[] values = StringUtils.split(attribute, ",");
         if (values.length != 2) {
            LOG.error("Expecting samlattributedesignator with 2 parts.[" + attribute + "]");
            throw new SessionManagementException(SessionManagementExceptionValues.ERROR_GENERAL, new Object[]{"Expecting samlattributedesignator with 2 parts."});
         }

         designators.add(new SAMLAttributeDesignator(values[1], values[0]));
      }

      return designators;
   }

   public static List<SAMLAttribute> getSAMLAttributes(String key) throws SessionManagementException {
      List<SAMLAttribute> attributes = new ArrayList();
      List<String> samlAttributes = config.getMatchingProperties(key);
      Iterator i$ = samlAttributes.iterator();

      while(i$.hasNext()) {
         String attribute = (String)i$.next();
         String[] values = StringUtils.split(attribute, ",");
         if (values.length < 3) {
            LOG.error("Expecting samlattribute with at least 3 parts.[" + attribute + "]");
            throw new SessionManagementException(SessionManagementExceptionValues.ERROR_GENERAL, new Object[]{"Expecting samlattribute with at least 3 parts."});
         }

         attributes.add(new SAMLAttribute(values[1], values[0], (String[])((String[])ArrayUtils.subarray(values, 2, values.length))));
      }

      return attributes;
   }
}
