package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStatesDeclarationType",
   propOrder = {"civilStates"}
)
public class CivilStatesDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilState"
   )
   protected List civilStates;

   public List getCivilStates() {
      if (this.civilStates == null) {
         this.civilStates = new ArrayList();
      }

      return this.civilStates;
   }
}
