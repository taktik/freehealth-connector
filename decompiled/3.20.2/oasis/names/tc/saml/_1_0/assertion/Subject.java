package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectType",
   propOrder = {"nameIdentifier", "subjectConfirmation"}
)
@XmlRootElement(
   name = "Subject"
)
public class Subject implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NameIdentifier"
   )
   protected NameIdentifierType nameIdentifier;
   @XmlElement(
      name = "SubjectConfirmation"
   )
   protected SubjectConfirmation subjectConfirmation;

   public NameIdentifierType getNameIdentifier() {
      return this.nameIdentifier;
   }

   public void setNameIdentifier(NameIdentifierType value) {
      this.nameIdentifier = value;
   }

   public SubjectConfirmation getSubjectConfirmation() {
      return this.subjectConfirmation;
   }

   public void setSubjectConfirmation(SubjectConfirmation value) {
      this.subjectConfirmation = value;
   }
}
