package be.fgov.ehealth.dics.core.v4.refdata;

import be.fgov.ehealth.dics.protocol.v4.ReimbursementCriterionType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementCriterionKeyType"
)
@XmlSeeAlso({ReimbursementCriterionType.class})
public class ReimbursementCriterionKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Category",
      required = true
   )
   protected String category;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public ReimbursementCriterionKeyType() {
   }

   public String getCategory() {
      return this.category;
   }

   public void setCategory(String value) {
      this.category = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
