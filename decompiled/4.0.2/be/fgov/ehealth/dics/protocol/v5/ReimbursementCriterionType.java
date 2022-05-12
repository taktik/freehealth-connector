package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementCriterionType",
   propOrder = {"description"}
)
public class ReimbursementCriterionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description"
   )
   protected ConsultTextType description;
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

   public ReimbursementCriterionType() {
   }

   public ConsultTextType getDescription() {
      return this.description;
   }

   public void setDescription(ConsultTextType value) {
      this.description = value;
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
