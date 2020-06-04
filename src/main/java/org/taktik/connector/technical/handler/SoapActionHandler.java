package org.taktik.connector.technical.handler;

import org.apache.commons.lang.StringUtils;
import org.taktik.connector.technical.utils.SOAPFaultFactory;

import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.technical.utils.SOAPUtils;

public class SoapActionHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SoapActionHandler.class);
   public static final String SOAPACTION_WSI_COMPLAINT = "be.ehealth.technicalconnector.handler.soapactionhandler.wsi.complaint";
   private static final String QUOTE = "\"";
   private static final String MIME_SOAPACTION = "SOAPAction";

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
      if (org.apache.commons.lang3.StringUtils.startsWith(soapAction, "\"") && org.apache.commons.lang3.StringUtils.endsWith(soapAction, "\"")) {
         return soapAction;
      } else {
         LOG.debug("[WSI] R1109: SOAPACTION must be a quoted string [{}]", soapAction);
         String fixedSoapAction = org.apache.commons.lang3.StringUtils.prependIfMissing(soapAction, "\"");
         fixedSoapAction = org.apache.commons.lang3.StringUtils.appendIfMissing(fixedSoapAction, "\"");
         return fixedSoapAction;
      }
   }
}
