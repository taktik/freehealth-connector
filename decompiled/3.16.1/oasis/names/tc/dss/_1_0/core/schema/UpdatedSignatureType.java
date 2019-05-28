package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UpdatedSignatureType",
   propOrder = {"signatureObject"}
)
public class UpdatedSignatureType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignatureObject",
      required = true
   )
   protected SignatureObject signatureObject;
   @XmlAttribute(
      name = "Type"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;

   public SignatureObject getSignatureObject() {
      return this.signatureObject;
   }

   public void setSignatureObject(SignatureObject value) {
      this.signatureObject = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
