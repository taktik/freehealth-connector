package be.fgov.ehealth.mediprima.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultCarmedDataType",
   propOrder = {"bySsin"}
)
public class ConsultCarmedDataType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BySsin",
      required = true
   )
   protected BySsinType bySsin;

   public BySsinType getBySsin() {
      return this.bySsin;
   }

   public void setBySsin(BySsinType value) {
      this.bySsin = value;
   }
}
