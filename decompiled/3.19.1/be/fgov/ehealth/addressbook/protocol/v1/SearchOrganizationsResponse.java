package be.fgov.ehealth.addressbook.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.PaginationStatusResponseType;
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
   name = "SearchOrganizationsResponseType",
   propOrder = {"healthCareOrganizations"}
)
@XmlRootElement(
   name = "SearchOrganizationsResponse"
)
public class SearchOrganizationsResponse extends PaginationStatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "HealthCareOrganization"
   )
   protected List<HealthCareOrganization> healthCareOrganizations;

   public List<HealthCareOrganization> getHealthCareOrganizations() {
      if (this.healthCareOrganizations == null) {
         this.healthCareOrganizations = new ArrayList();
      }

      return this.healthCareOrganizations;
   }
}
