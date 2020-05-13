package be.fgov.ehealth.insurability.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AbstractIdType",
   propOrder = {"name"}
)
@XmlSeeAlso({PackageType.class, IdType.class})
public abstract class AbstractIdType extends SelfRefType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name"
   )
   protected ValueRefString name;

   public ValueRefString getName() {
      return this.name;
   }

   public void setName(ValueRefString value) {
      this.name = value;
   }
}
