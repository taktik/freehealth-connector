package be.apb.standards.smoa.schema.v1;

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
   name = "MedicationSchemeTimestampsResponse",
   propOrder = {"currentDateTime", "subjectTimestamp"}
)
public class MedicationSchemeTimestampsResponse extends AbstractMedicationSchemeResponse {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar currentDateTime;
   protected List<SubjectTimestamp> subjectTimestamp;

   public XMLGregorianCalendar getCurrentDateTime() {
      return this.currentDateTime;
   }

   public void setCurrentDateTime(XMLGregorianCalendar value) {
      this.currentDateTime = value;
   }

   public List<SubjectTimestamp> getSubjectTimestamp() {
      if (this.subjectTimestamp == null) {
         this.subjectTimestamp = new ArrayList();
      }

      return this.subjectTimestamp;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"subjectId", "lastUpdated", "version"}
   )
   public static class SubjectTimestamp {
      @XmlElement(
         required = true
      )
      protected String subjectId;
      @XmlElement(
         required = true
      )
      @XmlSchemaType(
         name = "dateTime"
      )
      protected XMLGregorianCalendar lastUpdated;
      protected int version;

      public String getSubjectId() {
         return this.subjectId;
      }

      public void setSubjectId(String value) {
         this.subjectId = value;
      }

      public XMLGregorianCalendar getLastUpdated() {
         return this.lastUpdated;
      }

      public void setLastUpdated(XMLGregorianCalendar value) {
         this.lastUpdated = value;
      }

      public int getVersion() {
         return this.version;
      }

      public void setVersion(int value) {
         this.version = value;
      }
   }
}
