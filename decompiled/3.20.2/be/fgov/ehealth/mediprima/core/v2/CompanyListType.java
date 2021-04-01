package be.fgov.ehealth.mediprima.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CompanyListType",
   propOrder = {"cbeNumbers"}
)
public class CompanyListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CbeNumber",
      required = true
   )
   protected List<String> cbeNumbers;

   public List<String> getCbeNumbers() {
      if (this.cbeNumbers == null) {
         this.cbeNumbers = new ArrayList();
      }

      return this.cbeNumbers;
   }
}
