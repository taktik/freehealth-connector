package be.fgov.ehealth.consultrn.commons.protocol.v3;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultRnRequestType",
   propOrder = {"applicationID"}
)
@XmlSeeAlso({RegisterPersonRequest.class})
public class ConsultRnRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationID",
      required = true
   )
   protected String applicationID;

   public String getApplicationID() {
      return this.applicationID;
   }

   public void setApplicationID(String value) {
      this.applicationID = value;
   }
}
