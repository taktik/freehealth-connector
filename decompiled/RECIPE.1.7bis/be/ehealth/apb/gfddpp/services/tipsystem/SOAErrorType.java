package be.ehealth.apb.gfddpp.services.tipsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SOAErrorType",
   namespace = "urn:be:fgov:ehealth:errors:soa:v1",
   propOrder = {"environment"}
)
@XmlSeeAlso({SystemError.class, BusinessError.class})
public class SOAErrorType extends ErrorType {
   @XmlElement(
      name = "Environment",
      namespace = "urn:be:fgov:ehealth:errors:soa:v1",
      required = true
   )
   protected String environment;

   public String getEnvironment() {
      return this.environment;
   }

   public void setEnvironment(String var1) {
      this.environment = var1;
   }
}
