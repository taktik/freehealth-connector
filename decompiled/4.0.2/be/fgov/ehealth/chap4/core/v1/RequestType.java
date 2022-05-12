package be.fgov.ehealth.chap4.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestType",
   propOrder = {"isTest"}
)
public class RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IsTest"
   )
   protected boolean isTest;

   public RequestType() {
   }

   public boolean isIsTest() {
      return this.isTest;
   }

   public void setIsTest(boolean value) {
      this.isTest = value;
   }
}
