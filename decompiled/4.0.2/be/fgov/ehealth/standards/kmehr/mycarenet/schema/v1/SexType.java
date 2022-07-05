package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDSEX;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "sexType",
   propOrder = {"cd"}
)
public class SexType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDSEX cd;

   public SexType() {
   }

   public CDSEX getCd() {
      return this.cd;
   }

   public void setCd(CDSEX value) {
      this.cd = value;
   }
}
