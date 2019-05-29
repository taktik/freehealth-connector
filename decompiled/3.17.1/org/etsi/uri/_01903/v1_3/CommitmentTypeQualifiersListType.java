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
   name = "CommitmentTypeQualifiersListType",
   propOrder = {"commitmentTypeQualifiers"}
)
public class CommitmentTypeQualifiersListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommitmentTypeQualifier"
   )
   protected List<Any> commitmentTypeQualifiers;

   public List<Any> getCommitmentTypeQualifiers() {
      if (this.commitmentTypeQualifiers == null) {
         this.commitmentTypeQualifiers = new ArrayList();
      }

      return this.commitmentTypeQualifiers;
   }
}
