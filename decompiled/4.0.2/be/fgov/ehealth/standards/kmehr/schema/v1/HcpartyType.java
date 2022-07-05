package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY;
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "hcpartyType",
   propOrder = {"ids", "cds", "firstname", "familyname", "name", "addresses", "telecoms"}
)
public class HcpartyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id"
   )
   protected List<IDHCPARTY> ids;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDHCPARTY> cds;
   protected String firstname;
   protected String familyname;
   protected String name;
   @XmlElement(
      name = "address"
   )
   protected List<AddressType> addresses;
   @XmlElement(
      name = "telecom"
   )
   protected List<TelecomType> telecoms;

   public HcpartyType() {
   }

   public List<IDHCPARTY> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDHCPARTY> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public String getFirstname() {
      return this.firstname;
   }

   public void setFirstname(String value) {
      this.firstname = value;
   }

   public String getFamilyname() {
      return this.familyname;
   }

   public void setFamilyname(String value) {
      this.familyname = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public List<AddressType> getAddresses() {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      return this.addresses;
   }

   public List<TelecomType> getTelecoms() {
      if (this.telecoms == null) {
         this.telecoms = new ArrayList();
      }

      return this.telecoms;
   }
}
