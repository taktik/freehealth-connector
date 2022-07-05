package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDPERIODICITY;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "periodicityType",
   propOrder = {"cd"}
)
public class PeriodicityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDPERIODICITY cd;

   public PeriodicityType() {
   }

   public CDPERIODICITY getCd() {
      return this.cd;
   }

   public void setCd(CDPERIODICITY value) {
      this.cd = value;
   }
}
