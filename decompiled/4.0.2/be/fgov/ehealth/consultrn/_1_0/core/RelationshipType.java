package be.fgov.ehealth.consultrn._1_0.core;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RelationshipType",
   propOrder = {"code", "descriptions"}
)
public class RelationshipType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code"
   )
   @XmlSchemaType(
      name = "integer"
   )
   protected int code;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;

   public RelationshipType() {
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int value) {
      this.code = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }
}
