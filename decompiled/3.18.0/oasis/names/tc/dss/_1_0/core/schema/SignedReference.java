package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Transforms;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"transforms"}
)
@XmlRootElement(
   name = "SignedReference"
)
public class SignedReference implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Transforms",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected Transforms transforms;
   @XmlAttribute(
      name = "WhichDocument",
      required = true
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREF"
   )
   protected Object whichDocument;
   @XmlAttribute(
      name = "RefURI"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String refURI;
   @XmlAttribute(
      name = "RefId"
   )
   protected String refId;

   public Transforms getTransforms() {
      return this.transforms;
   }

   public void setTransforms(Transforms value) {
      this.transforms = value;
   }

   public Object getWhichDocument() {
      return this.whichDocument;
   }

   public void setWhichDocument(Object value) {
      this.whichDocument = value;
   }

   public String getRefURI() {
      return this.refURI;
   }

   public void setRefURI(String value) {
      this.refURI = value;
   }

   public String getRefId() {
      return this.refId;
   }

   public void setRefId(String value) {
      this.refId = value;
   }
}
