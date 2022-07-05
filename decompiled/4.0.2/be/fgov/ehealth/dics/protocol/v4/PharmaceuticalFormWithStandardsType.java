package be.fgov.ehealth.dics.protocol.v4;

import be.fgov.ehealth.dics.core.v4.refdata.PharmaceuticalFormKeyType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PharmaceuticalFormWithStandardsType",
   propOrder = {"name", "standardForms"}
)
public class PharmaceuticalFormWithStandardsType extends PharmaceuticalFormKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "StandardForm"
   )
   protected List<ConsultStandardFormType> standardForms;

   public PharmaceuticalFormWithStandardsType() {
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public List<ConsultStandardFormType> getStandardForms() {
      if (this.standardForms == null) {
         this.standardForms = new ArrayList();
      }

      return this.standardForms;
   }
}
