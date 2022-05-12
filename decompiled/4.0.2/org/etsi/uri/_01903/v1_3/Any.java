package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AnyType",
   propOrder = {"content"}
)
@XmlRootElement(
   name = "Any"
)
public class Any implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<Object> content;
   @XmlAnyAttribute
   private Map<QName, String> otherAttributes = new HashMap();

   public Any() {
   }

   public List<Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public Map<QName, String> getOtherAttributes() {
      return this.otherAttributes;
   }
}
