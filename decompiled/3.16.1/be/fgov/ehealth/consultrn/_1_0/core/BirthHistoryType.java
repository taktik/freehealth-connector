package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BirthHistoryType",
   propOrder = {"birth", "actNumber"}
)
public class BirthHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Birth",
      required = true
   )
   protected BirthDeceaseType birth;
   @XmlElement(
      name = "ActNumber"
   )
   protected BigInteger actNumber;

   public BirthDeceaseType getBirth() {
      return this.birth;
   }

   public void setBirth(BirthDeceaseType value) {
      this.birth = value;
   }

   public BigInteger getActNumber() {
      return this.actNumber;
   }

   public void setActNumber(BigInteger value) {
      this.actNumber = value;
   }
}
