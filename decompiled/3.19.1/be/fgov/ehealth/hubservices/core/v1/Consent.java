package be.fgov.ehealth.hubservices.core.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"cds"}
)
public class Consent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "cd"
   )
   protected List<CDCONSENT> cds;

   public List<CDCONSENT> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }
}
