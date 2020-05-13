package be.fgov.ehealth.dics.core.v3.actual.common;

import be.fgov.ehealth.dics.protocol.v3.ConsultRealActualIngredientType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RealActualIngredientKeyType"
)
@XmlSeeAlso({ConsultRealActualIngredientType.class})
public class RealActualIngredientKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Rank",
      required = true
   )
   protected short rank;

   public short getRank() {
      return this.rank;
   }

   public void setRank(short value) {
      this.rank = value;
   }
}
