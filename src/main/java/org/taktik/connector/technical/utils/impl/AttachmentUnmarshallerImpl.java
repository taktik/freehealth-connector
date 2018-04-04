package org.taktik.connector.technical.utils.impl;

import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.xml.bind.attachment.AttachmentUnmarshaller;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPException;

public class AttachmentUnmarshallerImpl extends AttachmentUnmarshaller {
   private Map<String, AttachmentPart> attachments = new HashMap();
   private boolean xop;

   public AttachmentUnmarshallerImpl(boolean xop) {
      this.xop = xop;
   }

   public Map<String, AttachmentPart> getAttachmentPartMap() {
      return this.attachments;
   }

   public DataHandler getAttachmentAsDataHandler(String cid) {
      AttachmentPart attachment = (AttachmentPart)this.attachments.get(decode(cid));

      try {
         return attachment.getDataHandler();
      } catch (SOAPException var4) {
         throw new IllegalStateException(var4);
      }
   }

   public byte[] getAttachmentAsByteArray(String cid) {
      try {
         DataHandler handler = this.getAttachmentAsDataHandler(cid);
         return ConnectorIOUtils.getBytes(handler.getInputStream());
      } catch (Exception var3) {
         throw new IllegalStateException(var3);
      }
   }

   private static String decode(String cid) {
      try {
         return URLDecoder.decode(cid, "UTF-8");
      } catch (UnsupportedEncodingException var2) {
         throw new IllegalStateException(var2);
      }
   }

   public boolean isXOPPackage() {
      return this.xop;
   }
}
