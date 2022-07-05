package be.fgov.ehealth.commons.protocol.v2;

import be.fgov.ehealth.commons.core.v2.Author;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorRequestType",
   propOrder = {"author"}
)
@XmlSeeAlso({AuthorPaginationRequestType.class})
public class AuthorRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Author",
      required = true
   )
   protected Author author;

   public AuthorRequestType() {
   }

   public Author getAuthor() {
      return this.author;
   }

   public void setAuthor(Author value) {
      this.author = value;
   }
}
