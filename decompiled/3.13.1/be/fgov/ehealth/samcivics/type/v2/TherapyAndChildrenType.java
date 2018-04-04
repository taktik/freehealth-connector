package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapyAndChildrenType",
   propOrder = {"atm", "reimbursementCriteria", "reimbursementCategory", "reimbursementAndChildrens"}
)
public class TherapyAndChildrenType extends TherapyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Atm",
      required = true
   )
   protected AtmType atm;
   @XmlElement(
      name = "ReimbursementCriteria",
      required = true
   )
   protected ReimbursementCriteriaType reimbursementCriteria;
   @XmlElement(
      name = "ReimbursementCategory",
      required = true
   )
   protected ReimbCategoryCvType reimbursementCategory;
   @XmlElement(
      name = "ReimbursementAndChildren",
      required = true
   )
   protected List<ReimbursementAndChildrenType> reimbursementAndChildrens;

   public AtmType getAtm() {
      return this.atm;
   }

   public void setAtm(AtmType value) {
      this.atm = value;
   }

   public ReimbursementCriteriaType getReimbursementCriteria() {
      return this.reimbursementCriteria;
   }

   public void setReimbursementCriteria(ReimbursementCriteriaType value) {
      this.reimbursementCriteria = value;
   }

   public ReimbCategoryCvType getReimbursementCategory() {
      return this.reimbursementCategory;
   }

   public void setReimbursementCategory(ReimbCategoryCvType value) {
      this.reimbursementCategory = value;
   }

   public List<ReimbursementAndChildrenType> getReimbursementAndChildrens() {
      if (this.reimbursementAndChildrens == null) {
         this.reimbursementAndChildrens = new ArrayList();
      }

      return this.reimbursementAndChildrens;
   }
}
