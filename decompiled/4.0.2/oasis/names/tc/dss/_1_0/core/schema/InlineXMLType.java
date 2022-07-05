package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InlineXMLType",
   propOrder = {"any"}
)
public class InlineXMLType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement
   protected Element any;
   @XmlAttribute(
      name = "ignorePIs"
   )
   protected Boolean ignorePIs;
   @XmlAttribute(
      name = "ignoreComments"
   )
   protected Boolean ignoreComments;

   public InlineXMLType() {
   }

   public Element getAny() {
      return this.any;
   }

   public void setAny(Element value) {
      this.any = value;
   }

   public boolean isIgnorePIs() {
      return this.ignorePIs == null ? true : this.ignorePIs;
   }

   public void setIgnorePIs(Boolean value) {
      this.ignorePIs = value;
   }

   public boolean isIgnoreComments() {
      return this.ignoreComments == null ? true : this.ignoreComments;
   }

   public void setIgnoreComments(Boolean value) {
      this.ignoreComments = value;
   }
}
