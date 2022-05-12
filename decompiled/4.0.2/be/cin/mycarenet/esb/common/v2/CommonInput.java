package be.cin.mycarenet.esb.common.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CommonInputType",
   propOrder = {"request", "origin", "inputReference", "nipReference"}
)
@XmlRootElement(
   name = "CommonInput"
)
public class CommonInput implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Request",
      required = true
   )
   protected RequestType request;
   @XmlElement(
      name = "Origin",
      required = true
   )
   protected OrigineType origin;
   @XmlElement(
      name = "InputReference"
   )
   protected String inputReference;
   @XmlElement(
      name = "NIPReference"
   )
   protected String nipReference;

   public CommonInput() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public OrigineType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OrigineType value) {
      this.origin = value;
   }

   public String getInputReference() {
      return this.inputReference;
   }

   public void setInputReference(String value) {
      this.inputReference = value;
   }

   public String getNIPReference() {
      return this.nipReference;
   }

   public void setNIPReference(String value) {
      this.nipReference = value;
   }
}
