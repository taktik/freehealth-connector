package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractMedicationSchemeResponse",
   propOrder = {"clientMessageId", "serverMessageId", "status"}
)
@XmlSeeAlso({MedicationSchemeTimestampsResponse.class, MedicationSchemeEntriesResponse.class})
public class AbstractMedicationSchemeResponse extends AbstractEventType {
   protected String clientMessageId;
   @XmlElement(
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String serverMessageId;
   @XmlElement(
      required = true
   )
   protected Status status;

   public String getClientMessageId() {
      return this.clientMessageId;
   }

   public void setClientMessageId(String value) {
      this.clientMessageId = value;
   }

   public String getServerMessageId() {
      return this.serverMessageId;
   }

   public void setServerMessageId(String value) {
      this.serverMessageId = value;
   }

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status value) {
      this.status = value;
   }
}
