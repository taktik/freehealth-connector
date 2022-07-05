package be.fgov.ehealth.hubservices.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TherapeuticLinkListType",
   propOrder = {"therapeuticlinks"}
)
public class TherapeuticLinkListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "therapeuticlink"
   )
   protected List<TherapeuticLinkType> therapeuticlinks;

   public TherapeuticLinkListType() {
   }

   public List<TherapeuticLinkType> getTherapeuticlinks() {
      if (this.therapeuticlinks == null) {
         this.therapeuticlinks = new ArrayList();
      }

      return this.therapeuticlinks;
   }
}
