package be.fgov.ehealth.messageservices.core.v1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "SelectRetrieveTransaction",
   namespace = "http://www.ehealth.fgov.be/messageservices/protocol/v1"
)
public class SelectRetrieveTransaction extends SelectRetrieveTransactionType {
   private static final long serialVersionUID = 1L;

   public SelectRetrieveTransaction() {
   }
}
