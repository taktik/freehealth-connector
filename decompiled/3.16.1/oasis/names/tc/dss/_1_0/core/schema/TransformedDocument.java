package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"document"}
)
@XmlRootElement(
   name = "TransformedDocument"
)
public class TransformedDocument implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Document",
      required = true
   )
   protected DocumentType document;
   @XmlAttribute(
      name = "WhichReference",
      required = true
   )
   protected BigInteger whichReference;

   public DocumentType getDocument() {
      return this.document;
   }

   public void setDocument(DocumentType value) {
      this.document = value;
   }

   public BigInteger getWhichReference() {
      return this.whichReference;
   }

   public void setWhichReference(BigInteger value) {
      this.whichReference = value;
   }
}
