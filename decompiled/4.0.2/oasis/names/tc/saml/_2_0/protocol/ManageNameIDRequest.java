package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.EncryptedElementType;
import oasis.names.tc.saml._2_0.assertion.NameIDType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ManageNameIDRequestType",
   propOrder = {"encryptedID", "nameID", "terminate", "newEncryptedID", "newID"}
)
@XmlRootElement(
   name = "ManageNameIDRequest"
)
public class ManageNameIDRequest extends RequestAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptedID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected EncryptedElementType encryptedID;
   @XmlElement(
      name = "NameID",
      namespace = "urn:oasis:names:tc:SAML:2.0:assertion"
   )
   protected NameIDType nameID;
   @XmlElement(
      name = "Terminate"
   )
   protected Terminate terminate;
   @XmlElement(
      name = "NewEncryptedID"
   )
   protected EncryptedElementType newEncryptedID;
   @XmlElement(
      name = "NewID"
   )
   protected String newID;

   public ManageNameIDRequest() {
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

   public Terminate getTerminate() {
      return this.terminate;
   }

   public void setTerminate(Terminate value) {
      this.terminate = value;
   }

   public EncryptedElementType getNewEncryptedID() {
      return this.newEncryptedID;
   }

   public void setNewEncryptedID(EncryptedElementType value) {
      this.newEncryptedID = value;
   }

   public String getNewID() {
      return this.newID;
   }

   public void setNewID(String value) {
      this.newID = value;
   }
}
