package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.FamilyCompositionHistoryType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonHistoryFamilyCompositionReplyType",
   propOrder = {"ssin", "familyCompositionHistories"}
)
@XmlRootElement(
   name = "PersonHistoryFamilyCompositionReply"
)
public class PersonHistoryFamilyCompositionReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "FamilyCompositionHistory"
   )
   protected List<FamilyCompositionHistoryType> familyCompositionHistories;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public List<FamilyCompositionHistoryType> getFamilyCompositionHistories() {
      if (this.familyCompositionHistories == null) {
         this.familyCompositionHistories = new ArrayList();
      }

      return this.familyCompositionHistories;
   }
}
