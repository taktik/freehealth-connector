package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FamilyCompositionType",
   propOrder = {"ssin", "address", "familyMembers"}
)
public class FamilyCompositionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected String ssin;
   @XmlElement(
      name = "Address"
   )
   protected AddressType address;
   @XmlElement(
      name = "FamilyMember"
   )
   protected List<FamilyMemberType> familyMembers;
   @XmlAttribute(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlAttribute(
      name = "Origin"
   )
   protected OriginType origin;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public AddressType getAddress() {
      return this.address;
   }

   public void setAddress(AddressType value) {
      this.address = value;
   }

   public List<FamilyMemberType> getFamilyMembers() {
      if (this.familyMembers == null) {
         this.familyMembers = new ArrayList();
      }

      return this.familyMembers;
   }

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
