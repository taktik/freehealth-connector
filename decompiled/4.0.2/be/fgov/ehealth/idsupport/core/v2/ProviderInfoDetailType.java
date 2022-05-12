package be.fgov.ehealth.idsupport.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProviderInfoDetailType",
   propOrder = {"fieldName", "fieldValue"}
)
public class ProviderInfoDetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FieldName"
   )
   protected String fieldName;
   @XmlElement(
      name = "FieldValue"
   )
   protected String fieldValue;

   public ProviderInfoDetailType() {
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
