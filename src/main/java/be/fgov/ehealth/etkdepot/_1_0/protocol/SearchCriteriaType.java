package be.fgov.ehealth.etkdepot._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SearchCriteriaType",
   propOrder = {"identifiers"}
)
public class SearchCriteriaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Identifier",
      required = true
   )
   protected List<IdentifierType> identifiers;

   public List<IdentifierType> getIdentifiers() {
      if (this.identifiers == null) {
         this.identifiers = new ArrayList();
      }

      return this.identifiers;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      SearchCriteriaType that = (SearchCriteriaType) o;
      return Objects.equals(identifiers, that.identifiers);
   }

   @Override
   public int hashCode() {
      return Objects.hash(identifiers);
   }
}
