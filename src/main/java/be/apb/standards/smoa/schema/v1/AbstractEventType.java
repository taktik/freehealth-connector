package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractEventType",
   propOrder = {"id"}
)
@XmlSeeAlso({ArchivePrescriptionEventType.class, ArchivePrescriptionCommentEventType.class, MedicationDeliveryEventType.class, RegisterExportEventType.class, PharmaceuticalCareEventType.class, BvacEventType.class, MedicationHistoryEvent.class, ContinuedPharmaceuticalCareDossierEvent.class, AbstractMedicationSchemeResponse.class, MedicationSchemeEntriesRequest.class})
public abstract class AbstractEventType {
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String id;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
