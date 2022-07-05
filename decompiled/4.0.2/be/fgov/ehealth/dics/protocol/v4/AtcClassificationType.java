package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.refdata.AtcClassificationKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AtcClassificationType",
   propOrder = {"description"}
)
public class AtcClassificationType extends AtcClassificationKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected String description;

   public AtcClassificationType() {
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }
}
