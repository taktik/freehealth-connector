package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.FamilyCompositionType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FamilyCompositionReplyType",
   propOrder = {"familyComposition"}
)
@XmlRootElement(
   name = "FamilyCompositionReply"
)
public class FamilyCompositionReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FamilyComposition"
   )
   protected FamilyCompositionType familyComposition;

   public FamilyCompositionType getFamilyComposition() {
      return this.familyComposition;
   }

   public void setFamilyComposition(FamilyCompositionType value) {
      this.familyComposition = value;
   }
}
