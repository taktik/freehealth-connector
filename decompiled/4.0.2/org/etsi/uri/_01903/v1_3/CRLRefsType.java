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
   name = "CRLRefsType",
   propOrder = {"crlReves"}
)
public class CRLRefsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CRLRef",
      required = true
   )
   protected List<CRLRefType> crlReves;

   public CRLRefsType() {
   }

   public List<CRLRefType> getCRLReves() {
      if (this.crlReves == null) {
         this.crlReves = new ArrayList();
      }

      return this.crlReves;
   }
}
