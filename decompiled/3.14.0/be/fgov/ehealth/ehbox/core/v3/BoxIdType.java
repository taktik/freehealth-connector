package be.fgov.ehealth.ehbox.core.v3;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.SubstituteType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BoxIdType",
   propOrder = {"quality"}
)
@XmlSeeAlso({SubstituteType.class})
public class BoxIdType extends IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quality",
      required = true
   )
   protected String quality;

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }
}
