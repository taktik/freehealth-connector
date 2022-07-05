package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectType",
   propOrder = {"encryptedID", "nameID", "baseID", "subjectConfirmations"}
)
@XmlRootElement(
   name = "Subject"
)
public class Subject implements Serializable {
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
      name = "SubjectConfirmation"
   )
   protected List<SubjectConfirmation> subjectConfirmations;

   public Subject() {
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

   public List<SubjectConfirmation> getSubjectConfirmations() {
      if (this.subjectConfirmations == null) {
         this.subjectConfirmations = new ArrayList();
      }

      return this.subjectConfirmations;
   }
}
