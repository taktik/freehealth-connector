package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AtmType",
   propOrder = {"atmId", "name", "specialtyOrigin"}
)
@XmlSeeAlso({AtmAndChildrenType.class})
public class AtmType extends BaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AtmId"
   )
   protected long atmId;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "SpecialtyOrigin"
   )
   protected String specialtyOrigin;

   public long getAtmId() {
      return this.atmId;
   }

   public void setAtmId(long value) {
      this.atmId = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getSpecialtyOrigin() {
      return this.specialtyOrigin;
   }

   public void setSpecialtyOrigin(String value) {
      this.specialtyOrigin = value;
   }
}
