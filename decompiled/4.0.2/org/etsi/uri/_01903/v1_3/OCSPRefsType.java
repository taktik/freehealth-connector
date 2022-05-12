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
   name = "OCSPRefsType",
   propOrder = {"ocspReves"}
)
public class OCSPRefsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OCSPRef",
      required = true
   )
   protected List<OCSPRefType> ocspReves;

   public OCSPRefsType() {
   }

   public List<OCSPRefType> getOCSPReves() {
      if (this.ocspReves == null) {
         this.ocspReves = new ArrayList();
      }

      return this.ocspReves;
   }
}
