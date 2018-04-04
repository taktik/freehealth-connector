package be.fgov.ehealth.certra.protocol.v1;

import be.fgov.ehealth.etee.commons.core.v1.OrganizationTypes;
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
   propOrder = {"organizationTypes"}
)
@XmlRootElement(
   name = "OrganizationTypeResponse"
)
public class OrganizationTypeResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OrganizationTypes",
      namespace = "urn:be:fgov:ehealth:etee:commons:core:v1"
   )
   protected List<OrganizationTypes> organizationTypes;

   public List<OrganizationTypes> getOrganizationTypes() {
      if (this.organizationTypes == null) {
         this.organizationTypes = new ArrayList();
      }

      return this.organizationTypes;
   }
}
