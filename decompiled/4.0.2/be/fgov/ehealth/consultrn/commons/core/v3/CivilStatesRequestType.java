package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStatesRequestType",
   propOrder = {"civilStates"}
)
public class CivilStatesRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CivilState",
      required = true
   )
   protected List<CivilStateRequestType> civilStates;

   public CivilStatesRequestType() {
   }

   public List<CivilStateRequestType> getCivilStates() {
      if (this.civilStates == null) {
         this.civilStates = new ArrayList();
      }

      return this.civilStates;
   }
}
