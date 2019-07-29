package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MutationInformationType",
   propOrder = {"author", "type", "descriptions"}
)
public class MutationInformationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Author",
      required = true
   )
   protected String author;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;

   public String getAuthor() {
      return this.author;
   }

   public void setAuthor(String value) {
      this.author = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
