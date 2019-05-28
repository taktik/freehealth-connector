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
   name = "ExcludedParagraphType",
   propOrder = {"chapterName", "paragraphName", "identifierNum", "atmAndChildrens"}
)
public class ExcludedParagraphType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ChapterName",
      required = true
   )
   protected String chapterName;
   @XmlElement(
      name = "ParagraphName",
      required = true
   )
   protected String paragraphName;
   @XmlElement(
      name = "IdentifierNum",
      required = true
   )
   protected IdentifierNumType identifierNum;
   @XmlElement(
      name = "AtmAndChildren"
   )
   protected List<AtmAndChildrenType> atmAndChildrens;

   public String getChapterName() {
      return this.chapterName;
   }

   public void setChapterName(String value) {
      this.chapterName = value;
   }

   public String getParagraphName() {
      return this.paragraphName;
   }

   public void setParagraphName(String value) {
      this.paragraphName = value;
   }

   public IdentifierNumType getIdentifierNum() {
      return this.identifierNum;
   }

   public void setIdentifierNum(IdentifierNumType value) {
      this.identifierNum = value;
   }

   public List<AtmAndChildrenType> getAtmAndChildrens() {
      if (this.atmAndChildrens == null) {
         this.atmAndChildrens = new ArrayList();
      }

      return this.atmAndChildrens;
   }
}
