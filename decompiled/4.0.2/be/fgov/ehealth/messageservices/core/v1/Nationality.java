package be.fgov.ehealth.messageservices.core.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"cd"}
)
public class Nationality implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDCOUNTRY cd;

   public Nationality() {
   }

   public CDCOUNTRY getCd() {
      return this.cd;
   }

   public void setCd(CDCOUNTRY value) {
      this.cd = value;
   }
}
