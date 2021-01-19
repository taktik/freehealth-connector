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
   name = "CertifiedRolesListType",
   propOrder = {"certifiedRoles"}
)
public class CertifiedRolesListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CertifiedRole",
      required = true
   )
   protected List<EncapsulatedPKIData> certifiedRoles;

   public List<EncapsulatedPKIData> getCertifiedRoles() {
      if (this.certifiedRoles == null) {
         this.certifiedRoles = new ArrayList();
      }

      return this.certifiedRoles;
   }
}
