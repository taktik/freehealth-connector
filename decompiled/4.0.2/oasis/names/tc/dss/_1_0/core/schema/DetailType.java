package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DetailType",
   propOrder = {"code", "message", "anies"}
)
public class DetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String code;
   @XmlElement(
      name = "Message"
   )
   protected InternationalStringType message;
   @XmlAnyElement
   protected List<Element> anies;
   @XmlAttribute(
      name = "Type",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;

   public DetailType() {
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public InternationalStringType getMessage() {
      return this.message;
   }

   public void setMessage(InternationalStringType value) {
      this.message = value;
   }

   public List<Element> getAnies() {
      if (this.anies == null) {
         this.anies = new ArrayList();
      }

      return this.anies;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
