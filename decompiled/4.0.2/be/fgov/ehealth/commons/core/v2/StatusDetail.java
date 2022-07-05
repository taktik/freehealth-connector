package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> anies;

   public StatusDetail() {
   }

   public List<Object> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }
}
