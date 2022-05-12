package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProductType",
   propOrder = {"name", "amppId", "paragraph"}
)
public class ProductType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "AmppId"
   )
   protected long amppId;
   @XmlElement(
      name = "Paragraph"
   )
   protected FindParagraphType paragraph;

   public ProductType() {
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public long getAmppId() {
      return this.amppId;
   }

   public void setAmppId(long value) {
      this.amppId = value;
   }

   public FindParagraphType getParagraph() {
      return this.paragraph;
   }

   public void setParagraph(FindParagraphType value) {
      this.paragraph = value;
   }
}
