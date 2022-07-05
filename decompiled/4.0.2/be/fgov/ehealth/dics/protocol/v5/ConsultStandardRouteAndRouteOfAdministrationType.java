package be.fgov.ehealth.dics.protocol.v5;

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
   propOrder = {"routeOfAdministrationCodes"}
)
public class ConsultStandardRouteAndRouteOfAdministrationType extends ConsultStandardRouteType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RouteOfAdministrationCode"
   )
   protected List<String> routeOfAdministrationCodes;

   public ConsultStandardRouteAndRouteOfAdministrationType() {
   }

   public List<String> getRouteOfAdministrationCodes() {
      if (this.routeOfAdministrationCodes == null) {
         this.routeOfAdministrationCodes = new ArrayList();
      }

      return this.routeOfAdministrationCodes;
   }
}
