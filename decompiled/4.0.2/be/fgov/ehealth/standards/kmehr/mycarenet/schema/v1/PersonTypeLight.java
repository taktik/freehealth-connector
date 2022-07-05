package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.id.v1.IDPATIENT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "personTypeLight",
   propOrder = {"ids", "firstnames", "familyname", "addresses", "telecoms"}
)
public class PersonTypeLight implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDPATIENT> ids;
   @XmlElement(
      name = "firstname"
   )
   protected List<String> firstnames;
   protected String familyname;
   @XmlElement(
      name = "address"
   )
   protected List<AddressType> addresses;
   @XmlElement(
      name = "telecom"
   )
   protected List<TelecomType> telecoms;

   public PersonTypeLight() {
   }

   public List<IDPATIENT> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<String> getFirstnames() {
      if (this.firstnames == null) {
         this.firstnames = new ArrayList();
      }

      return this.firstnames;
   }

   public String getFamilyname() {
      return this.familyname;
   }

   public void setFamilyname(String value) {
      this.familyname = value;
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
