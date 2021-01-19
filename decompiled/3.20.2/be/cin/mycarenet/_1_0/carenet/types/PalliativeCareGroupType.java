package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PalliativeCareGroupType",
   propOrder = {"responsible", "thirdPartyPayer"}
)
public class PalliativeCareGroupType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Responsible",
      required = true
   )
   protected String responsible;
   @XmlElement(
      name = "ThirdPartyPayer"
   )
   protected String thirdPartyPayer;

   public String getResponsible() {
      return this.responsible;
   }

   public void setResponsible(String value) {
      this.responsible = value;
   }

   public String getThirdPartyPayer() {
      return this.thirdPartyPayer;
   }

   public void setThirdPartyPayer(String value) {
      this.thirdPartyPayer = value;
   }
}
