package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OtherCertStatusRefsType",
   propOrder = {"otherReves"}
)
public class OtherCertStatusRefsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OtherRef",
      required = true
   )
   protected List<Any> otherReves;

   public List<Any> getOtherReves() {
      if (this.otherReves == null) {
         this.otherReves = new ArrayList();
      }

      return this.otherReves;
   }
}
