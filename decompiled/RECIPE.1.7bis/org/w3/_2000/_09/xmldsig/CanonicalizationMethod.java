package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CanonicalizationMethodType",
   propOrder = {"content"}
)
@XmlRootElement(
   name = "CanonicalizationMethod"
)
public class CanonicalizationMethod implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlMixed
   @XmlAnyElement(
      lax = true
   )
   protected List<java.lang.Object> content;
   @XmlAttribute(
      name = "Algorithm",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String algorithm;

   public List<java.lang.Object> getContent() {
      if (this.content == null) {
         this.content = new ArrayList();
      }

      return this.content;
   }

   public String getAlgorithm() {
      return this.algorithm;
   }

   public void setAlgorithm(String value) {
      this.algorithm = value;
   }
}
