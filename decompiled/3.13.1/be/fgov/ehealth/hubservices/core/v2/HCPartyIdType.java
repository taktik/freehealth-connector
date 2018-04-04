package be.fgov.ehealth.hubservices.core.v2;

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
   name = "HCPartyIdType",
   propOrder = {"ids", "cd", "firstname", "familyname", "name"}
)
public class HCPartyIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDHCPARTY> ids;
   protected CDHCPARTY cd;
   protected String firstname;
   protected String familyname;
   protected String name;

   public List<IDHCPARTY> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public CDHCPARTY getCd() {
      return this.cd;
   }

   public void setCd(CDHCPARTY value) {
      this.cd = value;
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
}
