package be.cin.mycarenet._1_0.carenet.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExtCareReceiverDetailType",
   propOrder = {"regNrWithMut", "mutuality"}
)
public class ExtCareReceiverDetailType extends CareReceiverDetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "RegNrWithMut"
   )
   protected String regNrWithMut;
   @XmlElement(
      name = "Mutuality"
   )
   protected String mutuality;

   public String getRegNrWithMut() {
      return this.regNrWithMut;
   }

   public void setRegNrWithMut(String value) {
      this.regNrWithMut = value;
   }

   public String getMutuality() {
      return this.mutuality;
   }

   public void setMutuality(String value) {
      this.mutuality = value;
   }
}
