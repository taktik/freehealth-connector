package be.fgov.ehealth.addressbook.protocol.v1;

import be.fgov.ehealth.aa.complextype.v1.HealthCareProfessional;
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
   name = "SearchProfessionalsResponseType",
   propOrder = {"healthCareProfessionals"}
)
@XmlRootElement(
   name = "SearchProfessionalsResponse"
)
public class SearchProfessionalsResponse extends PaginationStatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "HealthCareProfessional",
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1"
   )
   protected List<HealthCareProfessional> healthCareProfessionals;

   public SearchProfessionalsResponse() {
   }

   public List<HealthCareProfessional> getHealthCareProfessionals() {
      if (this.healthCareProfessionals == null) {
         this.healthCareProfessionals = new ArrayList();
      }

      return this.healthCareProfessionals;
   }
}
