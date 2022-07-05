package be.fgov.ehealth.messageservicescore.mycarenet.core.v1;

import be.fgov.ehealth.messageservices.mycarenet.core.v1.SelectRetrieveTransactionType;
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
