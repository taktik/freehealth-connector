package be.fgov.ehealth.samcivics.schemas.v2;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import be.fgov.ehealth.samcivics.type.v2.ParagraphAndChildrenType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindParagraphTextResponseType",
   propOrder = {"paragraph"}
)
@XmlRootElement(
   name = "FindParagraphTextResponse"
)
public class FindParagraphTextResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Paragraph",
      required = true
   )
   protected ParagraphAndChildrenType paragraph;

   public ParagraphAndChildrenType getParagraph() {
      return this.paragraph;
   }

   public void setParagraph(ParagraphAndChildrenType value) {
      this.paragraph = value;
   }
}
