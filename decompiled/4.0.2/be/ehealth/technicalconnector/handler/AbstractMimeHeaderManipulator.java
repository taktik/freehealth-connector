package be.ehealth.technicalconnector.handler;

import javax.xml.soap.MimeHeaders;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractMimeHeaderManipulator extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractMimeHeaderManipulator.class);

   public AbstractMimeHeaderManipulator() {
   }

   protected void addToHeader(SOAPMessageContext context, String headerName, String... headerValues) {
      if (context.getMessage() != null) {
         MimeHeaders mimeHeaders = context.getMessage().getMimeHeaders();
         if (mimeHeaders != null) {
            String[] oldHeaderValues = mimeHeaders.getHeader(headerName);
            if (ArrayUtils.isNotEmpty(oldHeaderValues)) {
               LOG.info("Removing MIME header [{}] with value [{}]", headerName, StringUtils.join(oldHeaderValues, ","));
               mimeHeaders.removeHeader(headerName);
            }

            LOG.debug("Adding MIME header [{}] with value [{}]", headerName, StringUtils.join(headerValues, ","));
            String[] var6 = headerValues;
            int var7 = headerValues.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               String headerValue = var6[var8];
               mimeHeaders.addHeader(headerName, headerValue);
            }
         }
      }

   }
}
