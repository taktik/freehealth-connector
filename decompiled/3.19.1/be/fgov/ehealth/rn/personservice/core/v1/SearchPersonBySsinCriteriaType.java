package be.fgov.ehealth.rn.personservice.core.v1;

import be.fgov.ehealth.rn.commons.v1.SsinType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchPersonBySsinCriteriaType",
   propOrder = {"ssin"}
)
public class SearchPersonBySsinCriteriaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin",
      required = true
   )
   protected SsinType ssin;

   public SsinType getSsin() {
      return this.ssin;
   }

   public void setSsin(SsinType value) {
      this.ssin = value;
   }
}
