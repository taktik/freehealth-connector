package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"businessAnomalies"}
)
@XmlRootElement(
   name = "BusinessAnomalies"
)
public class BusinessAnomalies implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BusinessAnomaly",
      required = true
   )
   protected List<BusinessAnomalyType> businessAnomalies;

   public List<BusinessAnomalyType> getBusinessAnomalies() {
      if (this.businessAnomalies == null) {
         this.businessAnomalies = new ArrayList();
      }

      return this.businessAnomalies;
   }
}
