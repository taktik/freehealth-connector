package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatusDetailType",
   propOrder = {"anies"}
)
@XmlRootElement(
   name = "StatusDetail"
)
public class StatusDetail implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement
   protected List<Element> anies;

   public StatusDetail() {
   }

   public List<Element> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }
}
