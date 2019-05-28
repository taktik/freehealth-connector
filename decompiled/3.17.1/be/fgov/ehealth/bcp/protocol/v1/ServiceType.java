package be.fgov.ehealth.bcp.protocol.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ServiceType",
   propOrder = {"endpoints"}
)
public class ServiceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Endpoint",
      required = true
   )
   protected List<Endpoint> endpoints;
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

   public List<Endpoint> getEndpoints() {
      if (this.endpoints == null) {
         this.endpoints = new ArrayList();
      }

      return this.endpoints;
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
