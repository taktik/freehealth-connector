package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.utils.SOAPUtils;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoapActionHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SoapActionHandler.class);
   public static final String SOAPACTION_WSI_COMPLAINT = "be.ehealth.technicalconnector.handler.soapactionhandler.wsi.complaint";
   private static final String QUOTE = "\"";
   private static final String MIME_SOAPACTION = "SOAPAction";

   public SoapActionHandler() {
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      try {
         Boolean wsiEnabled = (Boolean)context.get("be.ehealth.technicalconnector.handler.soapactionhandler.wsi.complaint");
         String soapAction = (String)context.get("javax.xml.ws.soap.http.soapaction.uri");
         if (wsiEnabled == null || wsiEnabled == Boolean.TRUE) {
            LOG.debug("[WSI] compliant mode active");
            soapAction = fixSoapAction(soapAction);
         }

         if (StringUtils.isNotBlank(soapAction)) {
            addSoapAction(soapAction, context);
         }

         return true;
      } catch (SOAPException var4) {
         throw SOAPUtils.newSOAPFaultException("WSSecurity problem: [SOAPACTION]" + var4.getMessage(), var4);
      }
   }

   private static void addSoapAction(String soapAction, SOAPMessageContext context) throws SOAPException {
      SOAPMessage msg = context.getMessage();
      MimeHeaders mimeHeaders = msg.getMimeHeaders();
      String[] headers = mimeHeaders.getHeader("SOAPAction");
      if (headers != null) {
         LOG.warn("Removing SOAPAction with values: {}", ArrayUtils.toString(headers));
         mimeHeaders.removeHeader("SOAPAction");
      }

      LOG.debug("Adding mimeheader [{}] with value [{}]", "SOAPAction", soapAction);
      mimeHeaders.addHeader("SOAPAction", soapAction);
      msg.saveChanges();
   }

   private static String fixSoapAction(String soapAction) {
      if (StringUtils.startsWith(soapAction, "\"") && StringUtils.endsWith(soapAction, "\"")) {
         return soapAction;
      } else {
         LOG.debug("[WSI] R1109: SOAPACTION must be a quoted string [{}]", soapAction);
         String fixedSoapAction = StringUtils.prependIfMissing(soapAction, "\"", new CharSequence[0]);
         fixedSoapAction = StringUtils.appendIfMissing(fixedSoapAction, "\"", new CharSequence[0]);
         return fixedSoapAction;
      }
   }
}
