package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"ssin", "entityType"}
)
@XmlRootElement(
   name = "EHActorQualitiesDataRequest"
)
public class EHActorQualitiesDataRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected String ssin;
   @XmlElement(
      name = "EntityType",
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected EntityType entityType;

   public EHActorQualitiesDataRequest() {
   }

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public EntityType getEntityType() {
      return this.entityType;
   }

   public void setEntityType(EntityType value) {
      this.entityType = value;
   }
}
