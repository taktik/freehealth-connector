package be.fgov.ehealth.dics.protocol.v5;

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
   name = "",
   propOrder = {"unitType", "domains"}
)
public class Parameter implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "UnitType",
      required = true
   )
   protected String unitType;
   @XmlElement(
      name = "Domain"
   )
   protected List<DomainType> domains;
   @XmlAttribute(
      name = "Name",
      required = true
   )
   protected String name;

   public Parameter() {
   }

   public String getUnitType() {
      return this.unitType;
   }

   public void setUnitType(String value) {
      this.unitType = value;
   }

   public List<DomainType> getDomains() {
      if (this.domains == null) {
         this.domains = new ArrayList();
      }

      return this.domains;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
