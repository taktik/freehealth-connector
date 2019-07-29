package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CommitmentTypeIndicationType",
   propOrder = {"commitmentTypeId", "allSignedDataObjects", "objectReferences", "commitmentTypeQualifiers"}
)
@XmlRootElement(
   name = "CommitmentTypeIndication"
)
public class CommitmentTypeIndication implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommitmentTypeId",
      required = true
   )
   protected ObjectIdentifier commitmentTypeId;
   @XmlElement(
      name = "AllSignedDataObjects"
   )
   protected Object allSignedDataObjects;
   @XmlElement(
      name = "ObjectReference"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> objectReferences;
   @XmlElement(
      name = "CommitmentTypeQualifiers"
   )
   protected CommitmentTypeQualifiersListType commitmentTypeQualifiers;

   public ObjectIdentifier getCommitmentTypeId() {
      return this.commitmentTypeId;
   }

   public void setCommitmentTypeId(ObjectIdentifier value) {
      this.commitmentTypeId = value;
   }

   public Object getAllSignedDataObjects() {
      return this.allSignedDataObjects;
   }

   public void setAllSignedDataObjects(Object value) {
      this.allSignedDataObjects = value;
   }

   public List<String> getObjectReferences() {
      if (this.objectReferences == null) {
         this.objectReferences = new ArrayList();
      }

      return this.objectReferences;
   }

   public CommitmentTypeQualifiersListType getCommitmentTypeQualifiers() {
      return this.commitmentTypeQualifiers;
   }

   public void setCommitmentTypeQualifiers(CommitmentTypeQualifiersListType value) {
      this.commitmentTypeQualifiers = value;
   }
}
