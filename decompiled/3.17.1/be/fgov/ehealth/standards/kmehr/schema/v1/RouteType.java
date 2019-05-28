package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGROUTE;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "routeType",
   propOrder = {"cd"}
)
public class RouteType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDDRUGROUTE cd;

   public CDDRUGROUTE getCd() {
      return this.cd;
   }

   public void setCd(CDDRUGROUTE value) {
      this.cd = value;
   }
}
