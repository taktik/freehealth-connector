package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.refdata.RouteOfAdministrationKeyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RouteOfAdministrationWithStandardsType",
   propOrder = {"name", "standardRoutes"}
)
public class RouteOfAdministrationWithStandardsType extends RouteOfAdministrationKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "StandardRoute"
   )
   protected List<ConsultStandardRouteType> standardRoutes;

   public RouteOfAdministrationWithStandardsType() {
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public List<ConsultStandardRouteType> getStandardRoutes() {
      if (this.standardRoutes == null) {
         this.standardRoutes = new ArrayList();
      }

      return this.standardRoutes;
   }
}
