package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.refdata.AppendixKeyType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AppendixType",
   propOrder = {"description"}
)
public class AppendixType extends AppendixKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected ConsultTextType description;

   public AppendixType() {
   }

   public ConsultTextType getDescription() {
      return this.description;
   }

   public void setDescription(ConsultTextType value) {
      this.description = value;
   }
}
