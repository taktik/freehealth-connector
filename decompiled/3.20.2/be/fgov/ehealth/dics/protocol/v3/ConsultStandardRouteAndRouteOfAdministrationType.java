package be.fgov.ehealth.dics.protocol.v3;

import be.fgov.ehealth.dics.core.v3.refdata.RouteOfAdministrationKeyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultStandardRouteAndRouteOfAdministrationType",
   propOrder = {"routeOfAdministrations"}
)
public class ConsultStandardRouteAndRouteOfAdministrationType extends ConsultStandardRouteType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RouteOfAdministration"
   )
   protected List<RouteOfAdministrationKeyType> routeOfAdministrations;

   public List<RouteOfAdministrationKeyType> getRouteOfAdministrations() {
      if (this.routeOfAdministrations == null) {
         this.routeOfAdministrations = new ArrayList();
      }

      return this.routeOfAdministrations;
   }
}
