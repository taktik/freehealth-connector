package be.fgov.ehealth.certra.protocol.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"searchCriterium"}
)
@XmlRootElement(
   name = "GetExistingApplicationIdsRequest"
)
public class GetExistingApplicationIdsRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SearchCriterium",
      required = true
   )
   protected SearchCriteriumType searchCriterium;

   public SearchCriteriumType getSearchCriterium() {
      return this.searchCriterium;
   }

   public void setSearchCriterium(SearchCriteriumType value) {
      this.searchCriterium = value;
   }
}
