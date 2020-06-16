package be.fgov.ehealth.insurability.core.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"isTest"}
)
public class RequestType {
   @XmlElement(
      name = "IsTest"
   )
   protected boolean isTest;

   public boolean isIsTest() {
      return this.isTest;
   }

   public void setIsTest(boolean value) {
      this.isTest = value;
   }
}
