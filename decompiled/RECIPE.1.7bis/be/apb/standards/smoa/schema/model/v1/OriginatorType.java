package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OriginatorType",
   propOrder = {"originatorType", "originatorId", "serviceType"}
)
public class OriginatorType {
   @XmlElement(
      name = "OriginatorType",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String originatorType;
   @XmlElement(
      name = "OriginatorId",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String originatorId;
   @XmlElement(
      name = "ServiceType",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String serviceType;

   public String getOriginatorType() {
      return this.originatorType;
   }

   public void setOriginatorType(String value) {
      this.originatorType = value;
   }

   public String getOriginatorId() {
      return this.originatorId;
   }

   public void setOriginatorId(String value) {
      this.originatorId = value;
   }

   public String getServiceType() {
      return this.serviceType;
   }

   public void setServiceType(String value) {
      this.serviceType = value;
   }
}
