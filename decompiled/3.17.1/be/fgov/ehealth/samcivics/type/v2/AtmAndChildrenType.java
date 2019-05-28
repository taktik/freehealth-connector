package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AtmAndChildrenType",
   propOrder = {"ampps"}
)
public class AtmAndChildrenType extends AtmType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ampp"
   )
   protected List<FindAmppType> ampps;

   public List<FindAmppType> getAmpps() {
      if (this.ampps == null) {
         this.ampps = new ArrayList();
      }

      return this.ampps;
   }
}
