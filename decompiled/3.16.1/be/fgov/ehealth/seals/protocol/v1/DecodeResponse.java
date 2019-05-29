package be.fgov.ehealth.seals.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.seals.core.v1.ChoiceDecodedDataErrorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DecodeResponseType",
   propOrder = {"applicationName", "responses"}
)
@XmlRootElement(
   name = "DecodeResponse"
)
public class DecodeResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationName"
   )
   protected String applicationName;
   @XmlElement(
      name = "Response"
   )
   protected List<ChoiceDecodedDataErrorType> responses;

   public String getApplicationName() {
      return this.applicationName;
   }

   public void setApplicationName(String value) {
      this.applicationName = value;
   }

   public List<ChoiceDecodedDataErrorType> getResponses() {
      if (this.responses == null) {
         this.responses = new ArrayList();
      }

      return this.responses;
   }
}
