package be.fgov.ehealth.dics.core.v4.actual.common;

import be.fgov.ehealth.dics.protocol.v4.ConsultRealActualIngredientEquivalentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RealActualIngredientEquivalentKeyType"
)
@XmlSeeAlso({ConsultRealActualIngredientEquivalentType.class})
public class RealActualIngredientEquivalentKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "SequenceNr",
      required = true
   )
   protected short sequenceNr;

   public RealActualIngredientEquivalentKeyType() {
   }

   public short getSequenceNr() {
      return this.sequenceNr;
   }

   public void setSequenceNr(short value) {
      this.sequenceNr = value;
   }
}
