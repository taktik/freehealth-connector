package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FreeInformationsType",
   propOrder = {"encryptableOldFreeInformation", "table", "encryptableFreeText"}
)
public class FreeInformationsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptableOldFreeInformation"
   )
   protected EncryptableOldFreeInformation encryptableOldFreeInformation;
   @XmlElement(
      name = "Table"
   )
   protected Table table;
   @XmlElement(
      name = "EncryptableFreeText"
   )
   protected byte[] encryptableFreeText;

   public FreeInformationsType() {
   }

   public EncryptableOldFreeInformation getEncryptableOldFreeInformation() {
      return this.encryptableOldFreeInformation;
   }

   public void setEncryptableOldFreeInformation(EncryptableOldFreeInformation value) {
      this.encryptableOldFreeInformation = value;
   }

   public Table getTable() {
      return this.table;
   }

   public void setTable(Table value) {
      this.table = value;
   }

   public byte[] getEncryptableFreeText() {
      return this.encryptableFreeText;
   }

   public void setEncryptableFreeText(byte[] value) {
      this.encryptableFreeText = value;
   }
}
