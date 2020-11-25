package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDLIFECYCLE;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "lifecycleType",
   propOrder = {"cd"}
)
public class LifecycleType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDLIFECYCLE cd;

   public CDLIFECYCLE getCd() {
      return this.cd;
   }

   public void setCd(CDLIFECYCLE value) {
      this.cd = value;
   }
}
