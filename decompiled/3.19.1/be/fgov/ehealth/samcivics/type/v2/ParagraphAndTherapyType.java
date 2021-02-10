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
   name = "ParagraphAndTherapyType",
   propOrder = {"therapyAndChildrens"}
)
public class ParagraphAndTherapyType extends ParagraphType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TherapyAndChildren"
   )
   protected List<TherapyAndChildrenType> therapyAndChildrens;

   public List<TherapyAndChildrenType> getTherapyAndChildrens() {
      if (this.therapyAndChildrens == null) {
         this.therapyAndChildrens = new ArrayList();
      }

      return this.therapyAndChildrens;
   }
}
