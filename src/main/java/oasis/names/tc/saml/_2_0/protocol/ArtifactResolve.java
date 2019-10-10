package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ArtifactResolveType",
   propOrder = {"artifact"}
)
@XmlRootElement(
   name = "ArtifactResolve"
)
public class ArtifactResolve extends RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Artifact",
      required = true
   )
   protected String artifact;

   public String getArtifact() {
      return this.artifact;
   }

   public void setArtifact(String value) {
      this.artifact = value;
   }
}
