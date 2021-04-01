package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ExclusionType",
   propOrder = {"identifierNum"}
)
public class ExclusionType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IdentifierNum",
      required = true
   )
   protected IdentifierNumType identifierNum;

   public IdentifierNumType getIdentifierNum() {
      return this.identifierNum;
   }

   public void setIdentifierNum(IdentifierNumType value) {
      this.identifierNum = value;
   }
}
