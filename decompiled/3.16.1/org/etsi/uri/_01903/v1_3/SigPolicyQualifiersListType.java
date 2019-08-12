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
   name = "SigPolicyQualifiersListType",
   propOrder = {"sigPolicyQualifiers"}
)
public class SigPolicyQualifiersListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SigPolicyQualifier",
      required = true
   )
   protected List<Any> sigPolicyQualifiers;

   public List<Any> getSigPolicyQualifiers() {
      if (this.sigPolicyQualifiers == null) {
         this.sigPolicyQualifiers = new ArrayList();
      }

      return this.sigPolicyQualifiers;
   }
}
