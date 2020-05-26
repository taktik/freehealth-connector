package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.DataLocationType;
import be.apb.standards.smoa.schema.model.v1.MinSetPatient;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContinuedPharmaceuticalCareResponse",
   propOrder = {"minPatient", "currentDateTime", "entity", "dataLocation"}
)
public class ContinuedPharmaceuticalCareResponse extends AbstractEventType {
   @XmlElement(
      name = "min-Patient",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      required = true
   )
   protected MinSetPatient minPatient;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar currentDateTime;
   protected List<ContinuedPharmaceuticalCareEntity> entity;
   protected List<DataLocationType> dataLocation;

   public MinSetPatient getMinPatient() {
      return this.minPatient;
   }

   public void setMinPatient(MinSetPatient value) {
      this.minPatient = value;
   }

   public XMLGregorianCalendar getCurrentDateTime() {
      return this.currentDateTime;
   }

   public void setCurrentDateTime(XMLGregorianCalendar value) {
      this.currentDateTime = value;
   }

   public List<ContinuedPharmaceuticalCareEntity> getEntity() {
      if (this.entity == null) {
         this.entity = new ArrayList();
      }

      return this.entity;
   }

   public List<DataLocationType> getDataLocation() {
      if (this.dataLocation == null) {
         this.dataLocation = new ArrayList();
      }

      return this.dataLocation;
   }
}
