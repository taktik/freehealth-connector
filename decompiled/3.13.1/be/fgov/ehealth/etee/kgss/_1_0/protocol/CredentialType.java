package be.fgov.ehealth.etee.kgss._1_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CredentialType",
   propOrder = {"namespace", "name", "values"}
)
public class CredentialType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Namespace",
      required = true
   )
   protected String namespace;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "Value"
   )
   protected List<String> values;

   public String getNamespace() {
      return this.namespace;
   }

   public void setNamespace(String value) {
      this.namespace = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public List<String> getValues() {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      return this.values;
   }
}
