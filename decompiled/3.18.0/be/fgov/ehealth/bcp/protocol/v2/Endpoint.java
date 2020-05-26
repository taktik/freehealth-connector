package be.fgov.ehealth.bcp.protocol.v2;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EndpointType",
   propOrder = {"value"}
)
@XmlRootElement(
   name = "Endpoint"
)
public class Endpoint implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String value;
   @XmlAttribute(
      name = "Order",
      required = true
   )
   protected BigInteger order;
   @XmlAttribute(
      name = "Status",
      required = true
   )
   protected StatusType status;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public BigInteger getOrder() {
      return this.order;
   }

   public void setOrder(BigInteger value) {
      this.order = value;
   }

   public StatusType getStatus() {
      return this.status;
   }

   public void setStatus(StatusType value) {
      this.status = value;
   }
}
