package be.fgov.ehealth.etkra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CompleteETKRegistrationResponseType",
   propOrder = {"etk", "toBeActivated"}
)
@XmlRootElement(
   name = "CompleteETKRegistrationResponse"
)
public class CompleteETKRegistrationResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ETK"
   )
   protected byte[] etk;
   @XmlElement(
      name = "ToBeActivated"
   )
   protected Boolean toBeActivated;

   public byte[] getETK() {
      return this.etk;
   }

   public void setETK(byte[] value) {
      this.etk = value;
   }

   public Boolean isToBeActivated() {
      return this.toBeActivated;
   }

   public void setToBeActivated(Boolean value) {
      this.toBeActivated = value;
   }
}
