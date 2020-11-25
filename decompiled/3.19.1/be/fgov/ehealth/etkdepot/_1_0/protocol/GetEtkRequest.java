package be.fgov.ehealth.etkdepot._1_0.protocol;

import be.fgov.ehealth.commons._1_0.protocol.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"searchCriteria"}
)
@XmlRootElement(
   name = "GetEtkRequest"
)
public class GetEtkRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SearchCriteria",
      required = true
   )
   protected SearchCriteriaType searchCriteria;

   public SearchCriteriaType getSearchCriteria() {
      return this.searchCriteria;
   }

   public void setSearchCriteria(SearchCriteriaType value) {
      this.searchCriteria = value;
   }
}
