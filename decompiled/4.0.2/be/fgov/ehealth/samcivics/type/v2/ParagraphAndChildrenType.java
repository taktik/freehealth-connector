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
   name = "ParagraphAndChildrenType",
   propOrder = {"verses", "exclusions"}
)
public class ParagraphAndChildrenType extends ParagraphType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Verse"
   )
   protected List<VerseType> verses;
   @XmlElement(
      name = "Exclusion"
   )
   protected List<ExclusionType> exclusions;

   public ParagraphAndChildrenType() {
   }

   public List<VerseType> getVerses() {
      if (this.verses == null) {
         this.verses = new ArrayList();
      }

      return this.verses;
   }

   public List<ExclusionType> getExclusions() {
      if (this.exclusions == null) {
         this.exclusions = new ArrayList();
      }

      return this.exclusions;
   }
}
