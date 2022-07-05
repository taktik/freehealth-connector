package oasis.names.tc.dss._1_0.core.schema;

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
   propOrder = {"signedReferences"}
)
@XmlRootElement(
   name = "SignedReferences"
)
public class SignedReferences implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SignedReference",
      required = true
   )
   protected List<SignedReference> signedReferences;

   public SignedReferences() {
   }

   public List<SignedReference> getSignedReferences() {
      if (this.signedReferences == null) {
         this.signedReferences = new ArrayList();
      }

      return this.signedReferences;
   }
}
