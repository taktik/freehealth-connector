package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "pingResponse",
   propOrder = {"pingResult"}
)
@XmlRootElement(
   name = "pingResponse"
)
public class PingResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PingResult"
   )
   protected String pingResult;

   public String getPingResult() {
      return this.pingResult;
   }

   public void setPingResult(String value) {
      this.pingResult = value;
   }
}
