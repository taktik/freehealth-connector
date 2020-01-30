package be.business.connector.gfddpp.domain;

import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SealedMessageRequestTypeXml {
   SealedMessageRequestType sealedMessageRequestType;

   public SealedMessageRequestType getSealedMessageRequestType() {
      return this.sealedMessageRequestType;
   }

   public void setSealedMessageRequestType(SealedMessageRequestType sealedMessageRequestType) {
      this.sealedMessageRequestType = sealedMessageRequestType;
   }
}
