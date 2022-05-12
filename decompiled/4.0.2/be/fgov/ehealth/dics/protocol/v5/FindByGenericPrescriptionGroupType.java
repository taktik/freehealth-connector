package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByGenericPrescriptionGroupType",
   propOrder = {"anyNamePart", "genericPrescriptionGroupCode"}
)
public class FindByGenericPrescriptionGroupType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AnyNamePart"
   )
   protected String anyNamePart;
   @XmlElement(
      name = "GenericPrescriptionGroupCode"
   )
   protected Integer genericPrescriptionGroupCode;

   public FindByGenericPrescriptionGroupType() {
   }

   public String getAnyNamePart() {
      return this.anyNamePart;
   }

   public void setAnyNamePart(String value) {
      this.anyNamePart = value;
   }

   public Integer getGenericPrescriptionGroupCode() {
      return this.genericPrescriptionGroupCode;
   }

   public void setGenericPrescriptionGroupCode(Integer value) {
      this.genericPrescriptionGroupCode = value;
   }
}
