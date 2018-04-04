package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MetaType",
   propOrder = {"type", "values"}
)
public class MetaType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "Value",
      required = true
   )
   protected List<String> values;

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public List<String> getValues() {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      return this.values;
   }
}
