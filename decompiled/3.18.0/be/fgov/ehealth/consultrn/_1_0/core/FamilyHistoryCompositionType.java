package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FamilyHistoryCompositionType",
   propOrder = {"familyMembers"}
)
public class FamilyHistoryCompositionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FamilyMember"
   )
   protected List<FamilyMemberType> familyMembers;

   public List<FamilyMemberType> getFamilyMembers() {
      if (this.familyMembers == null) {
         this.familyMembers = new ArrayList();
      }

      return this.familyMembers;
   }
}
