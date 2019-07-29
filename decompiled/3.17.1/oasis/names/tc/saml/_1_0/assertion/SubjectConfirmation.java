package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.KeyInfo;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubjectConfirmationType",
   propOrder = {"confirmationMethods", "subjectConfirmationData", "keyInfo"}
)
@XmlRootElement(
   name = "SubjectConfirmation"
)
public class SubjectConfirmation implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ConfirmationMethod",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected List<String> confirmationMethods;
   @XmlElement(
      name = "SubjectConfirmationData"
   )
   protected Object subjectConfirmationData;
   @XmlElement(
      name = "KeyInfo",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected KeyInfo keyInfo;

   public List<String> getConfirmationMethods() {
      if (this.confirmationMethods == null) {
         this.confirmationMethods = new ArrayList();
      }

      return this.confirmationMethods;
   }

   public Object getSubjectConfirmationData() {
      return this.subjectConfirmationData;
   }

   public void setSubjectConfirmationData(Object value) {
      this.subjectConfirmationData = value;
   }

   public KeyInfo getKeyInfo() {
      return this.keyInfo;
   }

   public void setKeyInfo(KeyInfo value) {
      this.keyInfo = value;
   }
}
