package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractCareProviderIdType;
import be.apb.standards.smoa.schema.v1.CDHCPARTY;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MaxSetCareProviderType",
   propOrder = {"id", "type", "name", "familyName", "address", "telecom"}
)
public class MaxSetCareProviderType extends AbstractCareProviderType {
   protected List<AbstractCareProviderIdType> id;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected List<CDHCPARTY> type;
   protected List<String> name;
   protected String familyName;
   protected List<AbstractAddressType> address;
   protected List<AbstractTelecomType> telecom;

   public List<AbstractCareProviderIdType> getId() {
      if (this.id == null) {
         this.id = new ArrayList();
      }

      return this.id;
   }

   public List<CDHCPARTY> getType() {
      if (this.type == null) {
         this.type = new ArrayList();
      }

      return this.type;
   }

   public List<String> getName() {
      if (this.name == null) {
         this.name = new ArrayList();
      }

      return this.name;
   }

   public String getFamilyName() {
      return this.familyName;
   }

   public void setFamilyName(String value) {
      this.familyName = value;
   }

   public List<AbstractAddressType> getAddress() {
      if (this.address == null) {
         this.address = new ArrayList();
      }

      return this.address;
   }

   public List<AbstractTelecomType> getTelecom() {
      if (this.telecom == null) {
         this.telecom = new ArrayList();
      }

      return this.telecom;
   }
}
