package org.oclc.purl.dsdl.svrl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"text"}
)
@XmlRootElement(
   name = "diagnostic-reference"
)
public class DiagnosticReference {
   @XmlElement(
      required = true
   )
   protected String text;
   @XmlAttribute(
      name = "diagnostic",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "NMTOKEN"
   )
   protected String diagnostic;

   public DiagnosticReference() {
   }

   public String getText() {
      return this.text;
   }

   public void setText(String value) {
      this.text = value;
   }

   public String getDiagnostic() {
      return this.diagnostic;
   }

   public void setDiagnostic(String value) {
      this.diagnostic = value;
   }
}
