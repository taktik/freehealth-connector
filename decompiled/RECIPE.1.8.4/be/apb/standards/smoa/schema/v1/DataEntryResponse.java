package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DataEntryResponse",
   propOrder = {"author"}
)
public class DataEntryResponse extends AbstractDataEntry {
   @XmlElement(
      required = true
   )
   protected Author author;

   public Author getAuthor() {
      return this.author;
   }

   public void setAuthor(Author value) {
      this.author = value;
   }
}
