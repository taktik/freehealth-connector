package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeceaseHistoryType",
   propOrder = {"decease", "actNumber"}
)
public class DeceaseHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Decease",
      required = true
   )
   protected BirthDeceaseType decease;
   @XmlElement(
      name = "ActNumber"
   )
   protected BigInteger actNumber;

   public DeceaseHistoryType() {
   }

   public BirthDeceaseType getDecease() {
      return this.decease;
   }

   public void setDecease(BirthDeceaseType value) {
      this.decease = value;
   }

   public BigInteger getActNumber() {
      return this.actNumber;
   }

   public void setActNumber(BigInteger value) {
      this.actNumber = value;
   }
}
