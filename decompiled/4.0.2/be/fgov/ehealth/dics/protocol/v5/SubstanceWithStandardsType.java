package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubstanceWithStandardsType",
   propOrder = {"chemicalForm", "name", "note", "standardSubstances"}
)
public class SubstanceWithStandardsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ChemicalForm"
   )
   protected String chemicalForm;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "Note"
   )
   protected ConsultTextType note;
   @XmlElement(
      name = "StandardSubstance"
   )
   protected List<ConsultStandardSubstanceType> standardSubstances;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public SubstanceWithStandardsType() {
   }

   public String getChemicalForm() {
      return this.chemicalForm;
   }

   public void setChemicalForm(String value) {
      this.chemicalForm = value;
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public ConsultTextType getNote() {
      return this.note;
   }

   public void setNote(ConsultTextType value) {
      this.note = value;
   }

   public List<ConsultStandardSubstanceType> getStandardSubstances() {
      if (this.standardSubstances == null) {
         this.standardSubstances = new ArrayList();
      }

      return this.standardSubstances;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
