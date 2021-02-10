package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OcmwCpasType",
   propOrder = {"cbeNumber", "municipalityIns", "names"}
)
public class OcmwCpasType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CbeNumber",
      required = true
   )
   protected String cbeNumber;
   @XmlElement(
      name = "MunicipalityIns",
      required = true
   )
   protected String municipalityIns;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected List<NameType> names;

   public String getCbeNumber() {
      return this.cbeNumber;
   }

   public void setCbeNumber(String value) {
      this.cbeNumber = value;
   }

   public String getMunicipalityIns() {
      return this.municipalityIns;
   }

   public void setMunicipalityIns(String value) {
      this.municipalityIns = value;
   }

   public List<NameType> getNames() {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      return this.names;
   }
}
