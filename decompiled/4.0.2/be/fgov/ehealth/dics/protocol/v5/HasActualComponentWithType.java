package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HasActualComponentWithType",
   propOrder = {"pharmaceuticalFormName", "pharmaceuticalFormCode", "routeOfAdministrationName", "routeOfAdministrationCode"}
)
public class HasActualComponentWithType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PharmaceuticalFormName"
   )
   protected StandardFormNameCriterionType pharmaceuticalFormName;
   @XmlElement(
      name = "PharmaceuticalFormCode"
   )
   protected StandardFormCodeCriterionType pharmaceuticalFormCode;
   @XmlElement(
      name = "RouteOfAdministrationName"
   )
   protected StandardRouteNameCriterionType routeOfAdministrationName;
   @XmlElement(
      name = "RouteOfAdministrationCode"
   )
   protected StandardRouteCodeCriterionType routeOfAdministrationCode;

   public HasActualComponentWithType() {
   }

   public StandardFormNameCriterionType getPharmaceuticalFormName() {
      return this.pharmaceuticalFormName;
   }

   public void setPharmaceuticalFormName(StandardFormNameCriterionType value) {
      this.pharmaceuticalFormName = value;
   }

   public StandardFormCodeCriterionType getPharmaceuticalFormCode() {
      return this.pharmaceuticalFormCode;
   }

   public void setPharmaceuticalFormCode(StandardFormCodeCriterionType value) {
      this.pharmaceuticalFormCode = value;
   }

   public StandardRouteNameCriterionType getRouteOfAdministrationName() {
      return this.routeOfAdministrationName;
   }

   public void setRouteOfAdministrationName(StandardRouteNameCriterionType value) {
      this.routeOfAdministrationName = value;
   }

   public StandardRouteCodeCriterionType getRouteOfAdministrationCode() {
      return this.routeOfAdministrationCode;
   }

   public void setRouteOfAdministrationCode(StandardRouteCodeCriterionType value) {
      this.routeOfAdministrationCode = value;
   }
}
