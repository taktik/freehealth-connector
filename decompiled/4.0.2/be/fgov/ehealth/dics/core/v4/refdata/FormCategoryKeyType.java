package be.fgov.ehealth.dics.core.v4.refdata;

import be.fgov.ehealth.dics.protocol.v4.FormCategoryType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FormCategoryKeyType"
)
@XmlSeeAlso({FormCategoryType.class})
public class FormCategoryKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public FormCategoryKeyType() {
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
