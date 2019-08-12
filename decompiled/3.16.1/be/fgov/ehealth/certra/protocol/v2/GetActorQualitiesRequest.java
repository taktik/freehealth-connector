package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.commons.core.v2.Id;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Signature;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetActorQualitiesRequestType",
   propOrder = {"ssin", "signature"}
)
@XmlRootElement(
   name = "GetActorQualitiesRequest"
)
public class GetActorQualitiesRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected Id ssin;
   @XmlElement(
      name = "Signature",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      required = true
   )
   protected Signature signature;

   public Id getSSIN() {
      return this.ssin;
   }

   public void setSSIN(Id value) {
      this.ssin = value;
   }

   public Signature getSignature() {
      return this.signature;
   }

   public void setSignature(Signature value) {
      this.signature = value;
   }
}
