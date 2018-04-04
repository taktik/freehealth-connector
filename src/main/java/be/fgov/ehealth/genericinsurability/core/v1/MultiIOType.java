package be.fgov.ehealth.genericinsurability.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MultiIOType",
   propOrder = {"ios"}
)
public class MultiIOType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IO",
      required = true
   )
   protected List<String> ios;

   public List<String> getIOS() {
      if (this.ios == null) {
         this.ios = new ArrayList();
      }

      return this.ios;
   }
}
