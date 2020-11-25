package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.refdata.NoSwitchReasonKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NoSwitchReasonType",
   propOrder = {"description"}
)
public class NoSwitchReasonType extends NoSwitchReasonKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected ConsultTextType description;

   public ConsultTextType getDescription() {
      return this.description;
   }

   public void setDescription(ConsultTextType value) {
      this.description = value;
   }
}
