package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"document"}
)
@XmlRootElement(
   name = "DocumentWithSignature"
)
public class DocumentWithSignature implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Document",
      required = true
   )
   protected DocumentType document;

   public DocumentType getDocument() {
      return this.document;
   }

   public void setDocument(DocumentType value) {
      this.document = value;
   }
}
