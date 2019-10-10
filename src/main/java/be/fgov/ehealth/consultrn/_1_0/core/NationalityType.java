package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityType"
)
public class NationalityType extends CountryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlAttribute(
      name = "Origin"
   )
   protected OriginType origin;

   public String getModificationDate() {
      return this.modificationDate;
   }

   public void setModificationDate(String value) {
      this.modificationDate = value;
   }

   public OriginType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OriginType value) {
      this.origin = value;
   }
}
