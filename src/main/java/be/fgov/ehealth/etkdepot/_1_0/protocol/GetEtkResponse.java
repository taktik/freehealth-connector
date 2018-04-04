package be.fgov.ehealth.etkdepot._1_0.protocol;

import be.fgov.ehealth.commons._1_0.protocol.ResponseType;
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
   propOrder = {"givenSearchCriteria", "errors", "etk", "matchingEtks"}
)
@XmlRootElement(
   name = "GetEtkResponse"
)
public class GetEtkResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GivenSearchCriteria"
   )
   protected SearchCriteriaType givenSearchCriteria;
   @XmlElement(
      name = "Error"
   )
   protected List<ErrorType> errors;
   @XmlElement(
      name = "ETK"
   )
   protected byte[] etk;
   @XmlElement(
      name = "MatchingEtk"
   )
   protected List<MatchingEtk> matchingEtks;

   public SearchCriteriaType getGivenSearchCriteria() {
      return this.givenSearchCriteria;
   }

   public void setGivenSearchCriteria(SearchCriteriaType value) {
      this.givenSearchCriteria = value;
   }

   public List<ErrorType> getErrors() {
      if (this.errors == null) {
         this.errors = new ArrayList();
      }

      return this.errors;
   }

   public byte[] getETK() {
      return this.etk;
   }

   public void setETK(byte[] value) {
      this.etk = value;
   }

   public List<MatchingEtk> getMatchingEtks() {
      if (this.matchingEtks == null) {
         this.matchingEtks = new ArrayList();
      }

      return this.matchingEtks;
   }
}
