package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectGetLatestUpdateType",
   propOrder = {"criterias"}
)
public class SelectGetLatestUpdateType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "criteria",
      required = true
   )
   protected List<Criteria> criterias;

   public List<Criteria> getCriterias() {
      if (this.criterias == null) {
         this.criterias = new ArrayList();
      }

      return this.criterias;
   }
}
