package be.fgov.ehealth.mediprima.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NihiiNumberListType",
   propOrder = {"nihiiNumbers"}
)
@XmlSeeAlso({ProviderList.class})
public class NihiiNumberListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NihiiNumber",
      required = true
   )
   protected List<String> nihiiNumbers;

   public List<String> getNihiiNumbers() {
      if (this.nihiiNumbers == null) {
         this.nihiiNumbers = new ArrayList();
      }

      return this.nihiiNumbers;
   }
}
