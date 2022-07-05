package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapyType",
   propOrder = {"maskedInd"}
)
@XmlSeeAlso({TherapyAndChildrenType.class})
public class TherapyType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "MaskedInd",
      required = true
   )
   protected String maskedInd;

   public TherapyType() {
   }

   public String getMaskedInd() {
      return this.maskedInd;
   }

   public void setMaskedInd(String value) {
      this.maskedInd = value;
   }
}
