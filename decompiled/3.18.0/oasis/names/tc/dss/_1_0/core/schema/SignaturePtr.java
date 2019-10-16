package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = ""
)
@XmlRootElement(
   name = "SignaturePtr"
)
public class SignaturePtr implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "WhichDocument"
   )
   @XmlIDREF
   @XmlSchemaType(
      name = "IDREF"
   )
   protected Object whichDocument;
   @XmlAttribute(
      name = "XPath"
   )
   protected String xPath;

   public Object getWhichDocument() {
      return this.whichDocument;
   }

   public void setWhichDocument(Object value) {
      this.whichDocument = value;
   }

   public String getXPath() {
      return this.xPath;
   }

   public void setXPath(String value) {
      this.xPath = value;
   }
}
