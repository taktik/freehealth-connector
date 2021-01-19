package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
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
   name = "ConsultVmpComponentType",
   propOrder = {"phaseNumber", "name", "virtualForm", "routeOfAdministrations", "additionalFields", "virtualIngredients"}
)
public class ConsultVmpComponentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PhaseNumber"
   )
   protected Integer phaseNumber;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "VirtualForm",
      required = true
   )
   protected VirtualFormWithStandardsType virtualForm;
   @XmlElement(
      name = "RouteOfAdministration",
      required = true
   )
   protected List<RouteOfAdministrationWithStandardsType> routeOfAdministrations;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "VirtualIngredient",
      required = true
   )
   protected List<ConsultVirtualIngredientType> virtualIngredients;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected int code;
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

   public Integer getPhaseNumber() {
      return this.phaseNumber;
   }

   public void setPhaseNumber(Integer value) {
      this.phaseNumber = value;
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public VirtualFormWithStandardsType getVirtualForm() {
      return this.virtualForm;
   }

   public void setVirtualForm(VirtualFormWithStandardsType value) {
      this.virtualForm = value;
   }

   public List<RouteOfAdministrationWithStandardsType> getRouteOfAdministrations() {
      if (this.routeOfAdministrations == null) {
         this.routeOfAdministrations = new ArrayList();
      }

      return this.routeOfAdministrations;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultVirtualIngredientType> getVirtualIngredients() {
      if (this.virtualIngredients == null) {
         this.virtualIngredients = new ArrayList();
      }

      return this.virtualIngredients;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int value) {
      this.code = value;
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
