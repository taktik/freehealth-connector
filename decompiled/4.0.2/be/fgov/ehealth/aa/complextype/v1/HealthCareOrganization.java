package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HealthCareOrganizationType",
   propOrder = {"ids", "names", "addresses"}
)
@XmlRootElement(
   name = "HealthCareOrganization"
)
public class HealthCareOrganization implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   protected List<TypeCodeType> ids;
   @XmlElement(
      name = "Name"
   )
   protected List<NameType> names;
   @XmlElement(
      name = "Address"
   )
   protected List<Address> addresses;

   public HealthCareOrganization() {
   }

   public List<TypeCodeType> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<NameType> getNames() {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      return this.names;
   }

   public List<Address> getAddresses() {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      return this.addresses;
   }
}
