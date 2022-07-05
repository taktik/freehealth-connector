package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDDRUGPRESENTATION;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "presentationType",
   propOrder = {"cd"}
)
public class PresentationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDDRUGPRESENTATION cd;

   public PresentationType() {
   }

   public CDDRUGPRESENTATION getCd() {
      return this.cd;
   }

   public void setCd(CDDRUGPRESENTATION value) {
      this.cd = value;
   }
}
