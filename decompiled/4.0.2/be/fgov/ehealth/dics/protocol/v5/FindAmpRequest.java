package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "FindAmpRequest",
   namespace = "urn:be:fgov:ehealth:dics:protocol:v5"
)
public class FindAmpRequest extends FindAmpRequestType implements Serializable {
   private static final long serialVersionUID = 1L;

   public FindAmpRequest() {
   }
}
