package be.fgov.ehealth.dics.core.v4.compounding.common;

import be.fgov.ehealth.dics.protocol.v4.ConsultCompoundingIngredientType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CompoundingIngredientKeyType"
)
@XmlSeeAlso({ConsultCompoundingIngredientType.class})
public class CompoundingIngredientKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;
   @XmlAttribute(
      name = "CodeType"
   )
   protected String codeType;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public String getCodeType() {
      return this.codeType == null ? "CNK" : this.codeType;
   }

   public void setCodeType(String value) {
      this.codeType = value;
   }
}
