package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MedicationSchemeEntriesResponse",
   propOrder = {"subjectId", "medicationScheme"}
)
public class MedicationSchemeEntriesResponse extends AbstractMedicationSchemeResponse {
   protected String subjectId;
   protected MedicationSchemeResponse medicationScheme;

   public String getSubjectId() {
      return this.subjectId;
   }

   public void setSubjectId(String value) {
      this.subjectId = value;
   }

   public MedicationSchemeResponse getMedicationScheme() {
      return this.medicationScheme;
   }

   public void setMedicationScheme(MedicationSchemeResponse value) {
      this.medicationScheme = value;
   }
}
