package be.fgov.ehealth.dics.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v3.core.VmpComponentKeyType;
import java.io.Serializable;
import java.math.BigInteger;
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
   propOrder = {"phaseNumber", "name", "virtualForm", "routeOfAdministrations", "virtualIngredients"}
)
public class ConsultVmpComponentType extends VmpComponentKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PhaseNumber"
   )
   @XmlSchemaType(
      name = "positiveInteger"
   )
   protected BigInteger phaseNumber;
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
      name = "VirtualIngredient",
      required = true
   )
   protected List<ConsultVirtualIngredientType> virtualIngredients;
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

   public BigInteger getPhaseNumber() {
      return this.phaseNumber;
   }

   public void setPhaseNumber(BigInteger value) {
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

   public List<ConsultVirtualIngredientType> getVirtualIngredients() {
      if (this.virtualIngredients == null) {
         this.virtualIngredients = new ArrayList();
      }

      return this.virtualIngredients;
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
