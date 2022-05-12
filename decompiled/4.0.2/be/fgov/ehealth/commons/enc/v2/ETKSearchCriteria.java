package be.fgov.ehealth.commons.enc.v2;

import be.fgov.ehealth.commons.core.v2.Id;
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
   name = "ETKSearchCriteriaType",
   propOrder = {"ids"}
)
@XmlRootElement(
   name = "ETKSearchCriteria"
)
public class ETKSearchCriteria implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      namespace = "urn:be:fgov:ehealth:commons:core:v2",
      required = true
   )
   protected List<Id> ids;

   public ETKSearchCriteria() {
   }

   public List<Id> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }
}
