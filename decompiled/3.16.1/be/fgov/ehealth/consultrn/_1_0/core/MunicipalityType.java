package be.fgov.ehealth.consultrn._1_0.core;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MunicipalityType",
   propOrder = {"insCode", "postalCode", "descriptions"}
)
public class MunicipalityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "InsCode"
   )
   @XmlSchemaType(
      name = "integer"
   )
   protected Integer insCode;
   @XmlElement(
      name = "PostalCode"
   )
   protected String postalCode;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;

   public Integer getInsCode() {
      return this.insCode;
   }

   public void setInsCode(Integer value) {
      this.insCode = value;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String value) {
      this.postalCode = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
