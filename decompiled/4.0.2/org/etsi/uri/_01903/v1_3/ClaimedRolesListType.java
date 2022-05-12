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
   name = "ClaimedRolesListType",
   propOrder = {"claimedRoles"}
)
public class ClaimedRolesListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ClaimedRole",
      required = true
   )
   protected List<Any> claimedRoles;

   public ClaimedRolesListType() {
   }

   public List<Any> getClaimedRoles() {
      if (this.claimedRoles == null) {
         this.claimedRoles = new ArrayList();
      }

      return this.claimedRoles;
   }
}
