package be.fgov.ehealth.seals.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.seals.core.v1.ChoiceEncodedDataErrorType;
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
   name = "EncodeResponseType",
   propOrder = {"applicationName", "responses"}
)
@XmlRootElement(
   name = "EncodeResponse"
)
public class EncodeResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationName"
   )
   protected String applicationName;
   @XmlElement(
      name = "Response"
   )
   protected List<ChoiceEncodedDataErrorType> responses;

   public EncodeResponse() {
   }

   public String getApplicationName() {
      return this.applicationName;
   }

   public void setApplicationName(String value) {
      this.applicationName = value;
   }

   public List<ChoiceEncodedDataErrorType> getResponses() {
      if (this.responses == null) {
         this.responses = new ArrayList();
      }

      return this.responses;
   }
}
