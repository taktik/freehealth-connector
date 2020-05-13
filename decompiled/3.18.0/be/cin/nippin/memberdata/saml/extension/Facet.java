package be.cin.nippin.memberdata.saml.extension;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"dimensions"}
)
@XmlRootElement(
   name = "Facet"
)
public class Facet implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Dimension",
      namespace = ""
   )
   protected List<Dimension> dimensions;
   @XmlAttribute(
      name = "id",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String id;

   public List<Dimension> getDimensions() {
      if (this.dimensions == null) {
         this.dimensions = new ArrayList();
      }

      return this.dimensions;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
