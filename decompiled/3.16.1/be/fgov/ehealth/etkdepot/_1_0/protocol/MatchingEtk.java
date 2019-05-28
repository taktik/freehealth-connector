package be.fgov.ehealth.etkdepot._1_0.protocol;

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
   propOrder = {"identifiers"}
)
@XmlRootElement(
   name = "MatchingEtk"
)
public class MatchingEtk implements Serializable {
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
}
