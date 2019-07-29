package be.ehealth.technicalconnector.utils.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.xml.bind.attachment.AttachmentMarshaller;

public class AttachmentMarshallerImpl extends AttachmentMarshaller {
   private Map<String, DataHandler> attachments;
   private boolean xop;
   private int threshold;

   public AttachmentMarshallerImpl(boolean xop) {
      this(xop, 10);
   }

   public AttachmentMarshallerImpl(boolean xop, int threshold) {
      this.attachments = new HashMap();
      this.xop = xop;
      this.threshold = threshold;
   }

   public Map<String, DataHandler> getDataHandlerMap() {
      return this.attachments;
   }

   public String addMtomAttachment(DataHandler data, String elementNamespace, String elementLocalName) {
      return this.xop ? this.addDataHandler(data) : null;
   }

   public String addMtomAttachment(byte[] data, int offset, int length, String mimeType, String elementNamespace, String elementLocalName) {
      if (this.xop) {
         if (length < this.threshold) {
            return null;
         } else {
            byte[] subarray = new byte[length];
            System.arraycopy(data, offset, subarray, 0, length);
            return this.addDataHandler(new DataHandler(subarray, mimeType));
         }
      } else {
         return null;
      }
   }

   public String addSwaRefAttachment(DataHandler data) {
      return this.addDataHandler(data);
   }

   public boolean isXOPPackage() {
      return this.xop;
   }

   private String addDataHandler(DataHandler handler) {
      String cid = UUID.randomUUID() + "@ehealth.fgov.be";
      this.attachments.put(cid, handler);
      return "cid:" + cid;
   }
}
