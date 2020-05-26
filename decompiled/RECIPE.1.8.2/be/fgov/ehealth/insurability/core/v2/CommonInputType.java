package be.fgov.ehealth.insurability.core.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CommonInputType",
   propOrder = {"request", "origin"}
)
public class CommonInputType {
   @XmlElement(
      name = "Request"
   )
   protected RequestType request;
   @XmlElement(
      name = "Origin",
      required = true
   )
   protected OriginType origin;

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public OriginType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OriginType value) {
      this.origin = value;
   }
}
