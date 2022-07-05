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
   name = "FindVmpResponseType",
   propOrder = {"vmps"}
)
@XmlRootElement(
   name = "FindVmpResponse"
)
public class FindVmpResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Vmp"
   )
   protected List<ConsultVmpType> vmps;

   public FindVmpResponse() {
   }

   public List<ConsultVmpType> getVmps() {
      if (this.vmps == null) {
         this.vmps = new ArrayList();
      }

      return this.vmps;
   }
}
