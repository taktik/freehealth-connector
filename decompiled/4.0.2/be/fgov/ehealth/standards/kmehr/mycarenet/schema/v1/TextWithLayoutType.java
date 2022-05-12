package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "textWithLayoutType",
   propOrder = {"content"}
)
public class TextWithLayoutType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> content;
   @XmlAttribute(
      name = "L"
   )
   protected String l;

   public TextWithLayoutType() {
   }

   public List<Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public String getL() {
      return this.l;
   }

   public void setL(String value) {
      this.l = value;
   }
}
