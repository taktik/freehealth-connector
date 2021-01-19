package be.fgov.ehealth.addressbook.core.v1;

import be.fgov.ehealth.aa.complextype.v1.ProfessionalAddressbookType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ProfessionalInformationType",
   propOrder = {"eHealthBox"}
)
public class ProfessionalInformationType extends ProfessionalAddressbookType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EHealthBox"
   )
   protected EHealthBoxType eHealthBox;

   public EHealthBoxType getEHealthBox() {
      return this.eHealthBox;
   }

   public void setEHealthBox(EHealthBoxType value) {
      this.eHealthBox = value;
   }
}
