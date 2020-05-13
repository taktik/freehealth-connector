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
   name = "SubjectReferenceType",
   propOrder = {"messageGUID", "sessionGUID", "dispensationGUID"}
)
public class SubjectReferenceType {
   @XmlElement(
      name = "MessageGUID",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String messageGUID;
   @XmlElement(
      name = "SessionGUID"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String sessionGUID;
   @XmlElement(
      name = "DispensationGUID"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "token"
   )
   protected String dispensationGUID;

   public String getMessageGUID() {
      return this.messageGUID;
   }

   public void setMessageGUID(String value) {
      this.messageGUID = value;
   }

   public String getSessionGUID() {
      return this.sessionGUID;
   }

   public void setSessionGUID(String value) {
      this.sessionGUID = value;
   }

   public String getDispensationGUID() {
      return this.dispensationGUID;
   }

   public void setDispensationGUID(String value) {
      this.dispensationGUID = value;
   }
}
