package be.fgov.ehealth.aa.complextype.v1;

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
   name = "GIAMIOrganizationType",
   propOrder = {"giamiQualities"}
)
@XmlRootElement(
   name = "GIAMIOrganization"
)
public class GIAMIOrganization extends GIAMIEntityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GIAMIQuality"
   )
   protected List<GIAMIEntityType> giamiQualities;

   public List<GIAMIEntityType> getGIAMIQualities() {
      if (this.giamiQualities == null) {
         this.giamiQualities = new ArrayList();
      }

      return this.giamiQualities;
   }
}
