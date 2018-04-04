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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"xPathFirstChildOf", "xPathAfter"}
)
@XmlRootElement(
   name = "SignaturePlacement"
)
public class SignaturePlacement implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "XPathFirstChildOf"
   )
   protected String xPathFirstChildOf;
   @XmlElement(
      name = "XPathAfter"
   )
   protected String xPathAfter;
   @XmlAttribute(
      name = "WhichDocument"
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREF"
   )
   protected Object whichDocument;
   @XmlAttribute(
      name = "CreateEnvelopedSignature"
   )
   protected Boolean createEnvelopedSignature;

   public String getXPathFirstChildOf() {
      return this.xPathFirstChildOf;
   }

   public void setXPathFirstChildOf(String value) {
      this.xPathFirstChildOf = value;
   }

   public String getXPathAfter() {
      return this.xPathAfter;
   }

   public void setXPathAfter(String value) {
      this.xPathAfter = value;
   }

   public Object getWhichDocument() {
      return this.whichDocument;
   }

   public void setWhichDocument(Object value) {
      this.whichDocument = value;
   }

   public boolean isCreateEnvelopedSignature() {
      return this.createEnvelopedSignature == null ? true : this.createEnvelopedSignature.booleanValue();
   }

   public void setCreateEnvelopedSignature(Boolean value) {
      this.createEnvelopedSignature = value;
   }
}
