package be.fgov.ehealth.commons.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MunicipalityType",
   propOrder = {"zipCode", "insCode", "descriptions"}
)
public class MunicipalityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ZipCode",
      required = true
   )
   protected String zipCode;
   @XmlElement(
      name = "InsCode"
   )
   protected String insCode;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected List<LocalisedString> descriptions;

   public String getZipCode() {
      return this.zipCode;
   }

   public void setZipCode(String value) {
      this.zipCode = value;
   }

   public String getInsCode() {
      return this.insCode;
   }

   public void setInsCode(String value) {
      this.insCode = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
