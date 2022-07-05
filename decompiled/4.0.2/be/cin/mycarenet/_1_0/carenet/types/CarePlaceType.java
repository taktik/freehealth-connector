package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CarePlaceType",
   propOrder = {"carePlaceName", "carePlaceAdress", "carePlacePostalCode"}
)
public class CarePlaceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CarePlaceName",
      required = true
   )
   protected String carePlaceName;
   @XmlElement(
      name = "CarePlaceAdress",
      required = true
   )
   protected String carePlaceAdress;
   @XmlElement(
      name = "CarePlacePostalCode",
      required = true
   )
   protected String carePlacePostalCode;

   public CarePlaceType() {
   }

   public String getCarePlaceName() {
      return this.carePlaceName;
   }

   public void setCarePlaceName(String value) {
      this.carePlaceName = value;
   }

   public String getCarePlaceAdress() {
      return this.carePlaceAdress;
   }

   public void setCarePlaceAdress(String value) {
      this.carePlaceAdress = value;
   }

   public String getCarePlacePostalCode() {
      return this.carePlacePostalCode;
   }

   public void setCarePlacePostalCode(String value) {
      this.carePlacePostalCode = value;
   }
}
