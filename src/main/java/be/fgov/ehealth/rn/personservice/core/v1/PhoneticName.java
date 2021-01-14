package be.fgov.ehealth.rn.personservice.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PhoneticName",
   propOrder = {"lastName", "givenNames", "givenNameMatching"}
)
public class PhoneticName implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LastName",
      required = true
   )
   protected String lastName;
   @XmlElement(
      name = "GivenName"
   )
   protected List givenNames;
   @XmlElement(
      name = "GivenNameMatching",
      required = true
   )
   protected String givenNameMatching;

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public List getGivenNames() {
      if (this.givenNames == null) {
         this.givenNames = new ArrayList();
      }

      return this.givenNames;
   }

   public String getGivenNameMatching() {
      return this.givenNameMatching;
   }

   public void setGivenNameMatching(String value) {
      this.givenNameMatching = value;
   }
}
