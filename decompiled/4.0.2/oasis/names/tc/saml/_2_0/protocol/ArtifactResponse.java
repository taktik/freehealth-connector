package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ArtifactResponseType",
   propOrder = {"any"}
)
@XmlRootElement(
   name = "ArtifactResponse"
)
public class ArtifactResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement
   protected Element any;

   public ArtifactResponse() {
   }

   public Element getAny() {
      return this.any;
   }

   public void setAny(Element value) {
      this.any = value;
   }
}
