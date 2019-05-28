package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilstateHistoryType",
   propOrder = {"civilState", "actNumber"}
)
public class CivilstateHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilState",
      required = true
   )
   protected CivilStateType civilState;
   @XmlElement(
      name = "ActNumber"
   )
   protected BigInteger actNumber;

   public CivilStateType getCivilState() {
      return this.civilState;
   }

   public void setCivilState(CivilStateType value) {
      this.civilState = value;
   }

   public BigInteger getActNumber() {
      return this.actNumber;
   }

   public void setActNumber(BigInteger value) {
      this.actNumber = value;
   }
}
