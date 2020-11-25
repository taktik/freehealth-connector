package be.apb.gfddpp.assuralia.batch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Bvac",
   propOrder = {"cbfa", "sguid", "bvacDocumentId", "encryptedSymmetricKey", "singleMessageSealed"}
)
public class Bvac {
   @XmlElement(
      name = "CBFA",
      required = true
   )
   protected String cbfa;
   @XmlElement(
      name = "SGUID",
      required = true
   )
   protected String sguid;
   @XmlElement(
      name = "BvacDocumentId",
      required = true
   )
   protected String bvacDocumentId;
   @XmlElement(
      name = "EncryptedSymmetricKey",
      required = true
   )
   protected byte[] encryptedSymmetricKey;
   @XmlElement(
      name = "SingleMessageSealed",
      required = true
   )
   protected byte[] singleMessageSealed;

   public String getCBFA() {
      return this.cbfa;
   }

   public void setCBFA(String value) {
      this.cbfa = value;
   }

   public String getSGUID() {
      return this.sguid;
   }

   public void setSGUID(String value) {
      this.sguid = value;
   }

   public String getBvacDocumentId() {
      return this.bvacDocumentId;
   }

   public void setBvacDocumentId(String value) {
      this.bvacDocumentId = value;
   }

   public byte[] getEncryptedSymmetricKey() {
      return this.encryptedSymmetricKey;
   }

   public void setEncryptedSymmetricKey(byte[] value) {
      this.encryptedSymmetricKey = value;
   }

   public byte[] getSingleMessageSealed() {
      return this.singleMessageSealed;
   }

   public void setSingleMessageSealed(byte[] value) {
      this.singleMessageSealed = value;
   }
}
