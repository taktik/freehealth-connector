package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericExternalReference",
   propOrder = {"serviceOid", "version", "reference"}
)
public class GenericExternalReference {
   @XmlElement(
      required = true
   )
   protected String serviceOid;
   @XmlElement(
      required = true
   )
   protected String version;
   @XmlElement(
      required = true
   )
   protected String reference;

   public String getServiceOid() {
      return this.serviceOid;
   }

   public void setServiceOid(String value) {
      this.serviceOid = value;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String value) {
      this.version = value;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }
}
