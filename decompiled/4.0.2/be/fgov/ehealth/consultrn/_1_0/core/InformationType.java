package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InformationType",
   propOrder = {"fieldName", "fieldValue"}
)
public class InformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FieldName",
      required = true
   )
   protected String fieldName;
   @XmlElement(
      name = "FieldValue",
      required = true
   )
   protected String fieldValue;

   public InformationType() {
   }

   public String getFieldName() {
      return this.fieldName;
   }

   public void setFieldName(String value) {
      this.fieldName = value;
   }

   public String getFieldValue() {
      return this.fieldValue;
   }

   public void setFieldValue(String value) {
      this.fieldValue = value;
   }
}
