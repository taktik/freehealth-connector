package be.fgov.ehealth.certra.protocol.v1;

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
   name = "",
   propOrder = {"requestValid", "additionalSearchCriteria", "validNotAfterDate", "numberOfDaysBeforeExpiration", "contactDataType"}
)
@XmlRootElement(
   name = "ValidateRenewResponse"
)
public class ValidateRenewResponse extends RaResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected Boolean requestValid;
   @XmlElement(
      name = "additionalSearchCriterium"
   )
   protected List<SearchCriteriumType> additionalSearchCriteria;
   protected String validNotAfterDate;
   protected Integer numberOfDaysBeforeExpiration;
   protected ContactDataType contactDataType;

   public Boolean isRequestValid() {
      return this.requestValid;
   }

   public void setRequestValid(Boolean value) {
      this.requestValid = value;
   }

   public List<SearchCriteriumType> getAdditionalSearchCriteria() {
      if (this.additionalSearchCriteria == null) {
         this.additionalSearchCriteria = new ArrayList();
      }

      return this.additionalSearchCriteria;
   }

   public String getValidNotAfterDate() {
      return this.validNotAfterDate;
   }

   public void setValidNotAfterDate(String value) {
      this.validNotAfterDate = value;
   }

   public Integer getNumberOfDaysBeforeExpiration() {
      return this.numberOfDaysBeforeExpiration;
   }

   public void setNumberOfDaysBeforeExpiration(Integer value) {
      this.numberOfDaysBeforeExpiration = value;
   }

   public ContactDataType getContactDataType() {
      return this.contactDataType;
   }

   public void setContactDataType(ContactDataType value) {
      this.contactDataType = value;
   }
}
