package be.fgov.ehealth.bcp.protocol.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ServiceType",
   propOrder = {"endpointList", "cache"}
)
@XmlRootElement(
   name = "Service"
)
public class Service implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EndpointList",
      required = true
   )
   protected EndpointList endpointList;
   @XmlElement(
      name = "Cache"
   )
   protected Cache cache;
   @XmlAttribute(
      name = "Id",
      required = true
   )
   protected String id;
   @XmlAttribute(
      name = "Name",
      required = true
   )
   protected String name;

   public EndpointList getEndpointList() {
      return this.endpointList;
   }

   public void setEndpointList(EndpointList value) {
      this.endpointList = value;
   }

   public Cache getCache() {
      return this.cache;
   }

   public void setCache(Cache value) {
      this.cache = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
