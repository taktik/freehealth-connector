package be.fgov.ehealth.dics.protocol.v4;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v4.core.StrengthRangeType;
import be.fgov.ehealth.dics.core.v4.virtual.common.VirtualIngredientKeyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultVirtualIngredientType",
   propOrder = {"type", "strength", "substance", "realVirtualIngredients"}
)
public class ConsultVirtualIngredientType extends VirtualIngredientKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "Strength"
   )
   protected StrengthRangeType strength;
   @XmlElement(
      name = "Substance",
      required = true
   )
   protected SubstanceWithStandardsType substance;
   @XmlElement(
      name = "RealVirtualIngredient"
   )
   protected List<ConsultRealVirtualIngredientType> realVirtualIngredients;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultVirtualIngredientType() {
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public StrengthRangeType getStrength() {
      return this.strength;
   }

   public void setStrength(StrengthRangeType value) {
      this.strength = value;
   }

   public SubstanceWithStandardsType getSubstance() {
      return this.substance;
   }

   public void setSubstance(SubstanceWithStandardsType value) {
      this.substance = value;
   }

   public List<ConsultRealVirtualIngredientType> getRealVirtualIngredients() {
      if (this.realVirtualIngredients == null) {
         this.realVirtualIngredients = new ArrayList();
      }

      return this.realVirtualIngredients;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
