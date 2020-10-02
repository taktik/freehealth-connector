package be.fgov.ehealth.hubservices.core.v2;

import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"id", "therapeuticlink", "prooves"}
)
public class Therapeuticlinkrequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected IDKMEHR id;
   @XmlElement(
      required = true
   )
   protected TherapeuticLinkType therapeuticlink;
   @XmlElement(
      name = "proof"
   )
   protected List<ProofType> prooves;

   public IDKMEHR getId() {
      return this.id;
   }

   public void setId(IDKMEHR value) {
      this.id = value;
   }

   public TherapeuticLinkType getTherapeuticlink() {
      return this.therapeuticlink;
   }

   public void setTherapeuticlink(TherapeuticLinkType value) {
      this.therapeuticlink = value;
   }

   public List<ProofType> getProoves() {
      if (this.prooves == null) {
         this.prooves = new ArrayList();
      }

      return this.prooves;
   }
}
