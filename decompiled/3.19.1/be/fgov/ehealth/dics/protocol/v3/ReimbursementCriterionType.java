package be.fgov.ehealth.dics.protocol.v3;

import be.fgov.ehealth.dics.core.v3.refdata.ReimbursementCriterionKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReimbursementCriterionType",
   propOrder = {"description"}
)
public class ReimbursementCriterionType extends ReimbursementCriterionKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description"
   )
   protected ConsultTextType description;

   public ConsultTextType getDescription() {
      return this.description;
   }

   public void setDescription(ConsultTextType value) {
      this.description = value;
   }
}
