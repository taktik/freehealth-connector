package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectConfirmationType",
   propOrder = {"encryptedID", "nameID", "baseID", "subjectConfirmationData"}
)
@XmlRootElement(
   name = "SubjectConfirmation"
)
public class SubjectConfirmation implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptedID"
   )
   protected EncryptedElementType encryptedID;
   @XmlElement(
      name = "NameID"
   )
   protected NameIDType nameID;
   @XmlElement(
      name = "BaseID"
   )
   protected BaseID baseID;
   @XmlElement(
      name = "SubjectConfirmationData"
   )
   protected SubjectConfirmationDataType subjectConfirmationData;
   @XmlAttribute(
      name = "Method",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String method;

   public SubjectConfirmation() {
   }

   public EncryptedElementType getEncryptedID() {
      return this.encryptedID;
   }

   public void setEncryptedID(EncryptedElementType value) {
      this.encryptedID = value;
   }

   public NameIDType getNameID() {
      return this.nameID;
   }

   public void setNameID(NameIDType value) {
      this.nameID = value;
   }

   public BaseID getBaseID() {
      return this.baseID;
   }

   public void setBaseID(BaseID value) {
      this.baseID = value;
   }

   public SubjectConfirmationDataType getSubjectConfirmationData() {
      return this.subjectConfirmationData;
   }

   public void setSubjectConfirmationData(SubjectConfirmationDataType value) {
      this.subjectConfirmationData = value;
   }

   public String getMethod() {
      return this.method;
   }

   public void setMethod(String value) {
      this.method = value;
   }
}
