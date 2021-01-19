package be.fgov.ehealth.dics.protocol.v5;

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
   name = "FindListOfAmpResponseType",
   propOrder = {"amps"}
)
@XmlRootElement(
   name = "FindListOfAmpResponse"
)
public class FindListOfAmpResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Amp"
   )
   protected List<ListAmpType> amps;

   public List<ListAmpType> getAmps() {
      if (this.amps == null) {
         this.amps = new ArrayList();
      }

      return this.amps;
   }
}
