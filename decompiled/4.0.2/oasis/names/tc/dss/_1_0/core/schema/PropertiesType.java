package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PropertiesType",
   propOrder = {"properties"}
)
public class PropertiesType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Property",
      required = true
   )
   protected List<Property> properties;

   public PropertiesType() {
   }

   public List<Property> getProperties() {
      if (this.properties == null) {
         this.properties = new ArrayList();
      }

      return this.properties;
   }
}
