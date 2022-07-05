package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SpecificTechnicalCareResponseDetailType",
   propOrder = {"nurseReference", "messageName"}
)
@XmlRootElement(
   name = "SpecificTechnicalResponseCareDetail"
)
public class SpecificTechnicalResponseCareDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NurseReference",
      required = true
   )
   protected String nurseReference;
   @XmlElement(
      name = "MessageName",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected MessageNameType messageName;

   public SpecificTechnicalResponseCareDetail() {
   }

   public String getNurseReference() {
      return this.nurseReference;
   }

   public void setNurseReference(String value) {
      this.nurseReference = value;
   }

   public MessageNameType getMessageName() {
      return this.messageName;
   }

   public void setMessageName(MessageNameType value) {
      this.messageName = value;
   }
}
