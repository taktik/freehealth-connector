package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusCodeType",
   propOrder = {"statusCode"}
)
@XmlRootElement(
   name = "StatusCode"
)
public class StatusCode implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "StatusCode"
   )
   protected StatusCode statusCode;
   @XmlAttribute(
      name = "Value",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String value;

   public StatusCode() {
   }

   public StatusCode getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(StatusCode value) {
      this.statusCode = value;
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }
}
