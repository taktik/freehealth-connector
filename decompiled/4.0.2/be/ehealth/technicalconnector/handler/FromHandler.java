package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import java.util.regex.Pattern;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FromHandler extends AbstractMimeHeaderManipulator {
   private static final Logger LOG = LoggerFactory.getLogger(UserAgentHandler.class);
   private static final String HEADER_NAME = "From";
   private static final String PROP_FROM_VALUE = "be.ehealth.technicalconnector.handler.mime-headers.from.value";
   private static final String PROP_FROM_REGEX = "be.ehealth.technicalconnector.handler.mime-headers.from.regex";
   private static final String DEFAULT_MAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
   private final Pattern MAIL_PATTERN = Pattern.compile(System.getProperty("be.ehealth.technicalconnector.handler.mime-headers.from.regex", "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"));
   private final Configuration config = ConfigFactory.getConfigValidator();

   public FromHandler() {
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      if (this.config.hasProperty("be.ehealth.technicalconnector.handler.mime-headers.from.value")) {
         String headerValue = this.config.getProperty("be.ehealth.technicalconnector.handler.mime-headers.from.value");
         if (this.MAIL_PATTERN.matcher(headerValue).matches()) {
            LOG.debug("Adding MIME header [{}] with value [{}]", "From", headerValue);
            this.addToHeader(context, "From", new String[]{headerValue});
         } else {
            LOG.warn("Invalid From field with value [{}], ignoring field adding.", headerValue);
         }
      }

      return true;
   }
}
