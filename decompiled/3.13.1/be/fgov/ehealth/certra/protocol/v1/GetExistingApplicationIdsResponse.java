package be.fgov.ehealth.certra.protocol.v1;

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
   propOrder = {"searchCriteria"}
)
@XmlRootElement(
   name = "GetExistingApplicationIdsResponse"
)
public class GetExistingApplicationIdsResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SearchCriterium"
   )
   protected List<SearchCriteriumType> searchCriteria;

   public List<SearchCriteriumType> getSearchCriteria() {
      if (this.searchCriteria == null) {
         this.searchCriteria = new ArrayList();
      }

      return this.searchCriteria;
   }
}
