package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDEBIRTHPLACE;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "locationBirthPlaceType",
   propOrder = {"cd", "text", "address"}
)
public class LocationBirthPlaceType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected CDEBIRTHPLACE cd;
   protected TextType text;
   @XmlElement(
      required = true
   )
   protected AddressType address;

   public CDEBIRTHPLACE getCd() {
      return this.cd;
   }

   public void setCd(CDEBIRTHPLACE value) {
      this.cd = value;
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }

   public AddressType getAddress() {
      return this.address;
   }

   public void setAddress(AddressType value) {
      this.address = value;
   }
}
