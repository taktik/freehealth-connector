package be.fgov.ehealth.bcp.protocol.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"endpoints"}
)
public class EndpointList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Endpoint",
      required = true
   )
   protected List<Endpoint> endpoints;

   public List<Endpoint> getEndpoints() {
      if (this.endpoints == null) {
         this.endpoints = new ArrayList();
      }

      return this.endpoints;
   }
}
