package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultRecursiveLegalReferenceType",
   propOrder = {"formalInterpretation", "legalTexts", "legalReferences"}
)
public class ConsultRecursiveLegalReferenceType extends ConsultLegalReferenceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FormalInterpretation"
   )
   protected ConsultFormalInterpretationType formalInterpretation;
   @XmlElement(
      name = "LegalText"
   )
   protected List<ConsultRecursiveLegalTextType> legalTexts;
   @XmlElement(
      name = "LegalReference"
   )
   protected List<ConsultRecursiveLegalReferenceType> legalReferences;

   public ConsultRecursiveLegalReferenceType() {
   }

   public ConsultFormalInterpretationType getFormalInterpretation() {
      return this.formalInterpretation;
   }

   public void setFormalInterpretation(ConsultFormalInterpretationType value) {
      this.formalInterpretation = value;
   }

   public List<ConsultRecursiveLegalTextType> getLegalTexts() {
      if (this.legalTexts == null) {
         this.legalTexts = new ArrayList();
      }

      return this.legalTexts;
   }

   public List<ConsultRecursiveLegalReferenceType> getLegalReferences() {
      if (this.legalReferences == null) {
         this.legalReferences = new ArrayList();
      }

      return this.legalReferences;
   }
}
