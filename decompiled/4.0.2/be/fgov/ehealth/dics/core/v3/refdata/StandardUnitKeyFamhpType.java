package be.fgov.ehealth.dics.core.v3.refdata;

import be.fgov.ehealth.dics.protocol.v3.StandardUnitFamhpType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StandardUnitKeyFamhpType"
)
@XmlSeeAlso({StandardUnitFamhpType.class})
public class StandardUnitKeyFamhpType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Name",
      required = true
   )
   protected String name;

   public StandardUnitKeyFamhpType() {
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
