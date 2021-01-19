package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HasVirtualComponentWithType",
   propOrder = {"virtualFormName", "virtualFormCode", "routeOfAdministrationName", "routeOfAdministrationCode"}
)
public class HasVirtualComponentWithType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VirtualFormName"
   )
   protected StandardFormNameCriterionType virtualFormName;
   @XmlElement(
      name = "VirtualFormCode"
   )
   protected StandardFormCodeCriterionType virtualFormCode;
   @XmlElement(
      name = "RouteOfAdministrationName"
   )
   protected StandardRouteNameCriterionType routeOfAdministrationName;
   @XmlElement(
      name = "RouteOfAdministrationCode"
   )
   protected StandardRouteCodeCriterionType routeOfAdministrationCode;

   public StandardFormNameCriterionType getVirtualFormName() {
      return this.virtualFormName;
   }

   public void setVirtualFormName(StandardFormNameCriterionType value) {
      this.virtualFormName = value;
   }

   public StandardFormCodeCriterionType getVirtualFormCode() {
      return this.virtualFormCode;
   }

   public void setVirtualFormCode(StandardFormCodeCriterionType value) {
      this.virtualFormCode = value;
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
