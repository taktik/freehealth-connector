package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ElementType",
   propOrder = {"content"}
)
public class ElementType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String content;
   @XmlAttribute(
      name = "eName",
      required = true
   )
   protected String eName;
   @XmlAttribute(
      name = "eDescription",
      required = true
   )
   protected String eDescription;
   @XmlAttribute(
      name = "eType",
      required = true
   )
   protected TypeOfElementType eType;
   @XmlAttribute(
      name = "eFormat"
   )
   protected String eFormat;
   @XmlAttribute(
      name = "eValue",
      required = true
   )
   protected String eValue;
   @XmlAttribute(
      name = "ePerfData"
   )
   protected String ePerfData;

   public ElementType() {
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String value) {
      this.content = value;
   }

   public String getEName() {
      return this.eName;
   }

   public void setEName(String value) {
      this.eName = value;
   }

   public String getEDescription() {
      return this.eDescription;
   }

   public void setEDescription(String value) {
      this.eDescription = value;
   }

   public TypeOfElementType getEType() {
      return this.eType;
   }

   public void setEType(TypeOfElementType value) {
      this.eType = value;
   }

   public String getEFormat() {
      return this.eFormat;
   }

   public void setEFormat(String value) {
      this.eFormat = value;
   }

   public String getEValue() {
      return this.eValue;
   }

   public void setEValue(String value) {
      this.eValue = value;
   }

   public String getEPerfData() {
      return this.ePerfData;
   }

   public void setEPerfData(String value) {
      this.ePerfData = value;
   }
}
