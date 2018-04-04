package be.fgov.ehealth.hubservices.core.v3;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "PutTransactionSetResponse",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class PutTransactionSetResponse extends PutTransactionResponseType {
   private static final long serialVersionUID = 1L;
}
