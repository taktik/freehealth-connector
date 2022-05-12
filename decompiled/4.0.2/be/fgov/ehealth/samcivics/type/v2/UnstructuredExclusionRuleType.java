package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UnstructuredExclusionRuleType",
   propOrder = {"verseSeq", "verseNum", "texts"}
)
public class UnstructuredExclusionRuleType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VerseSeq",
      required = true
   )
   protected BigInteger verseSeq;
   @XmlElement(
      name = "VerseNum",
      required = true
   )
   protected BigInteger verseNum;
   @XmlElement(
      name = "Text",
      required = true
   )
   protected List<TextType> texts;

   public UnstructuredExclusionRuleType() {
   }

   public BigInteger getVerseSeq() {
      return this.verseSeq;
   }

   public void setVerseSeq(BigInteger value) {
      this.verseSeq = value;
   }

   public BigInteger getVerseNum() {
      return this.verseNum;
   }

   public void setVerseNum(BigInteger value) {
      this.verseNum = value;
   }

   public List<TextType> getTexts() {
      if (this.texts == null) {
         this.texts = new ArrayList();
      }

      return this.texts;
   }
}
