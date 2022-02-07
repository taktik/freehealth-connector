package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2001._04.xmlenc.EncryptedData;
import org.w3._2001._04.xmlenc.EncryptedKey;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptedElementType",
   propOrder = {"encryptedData", "encryptedKeies"}
)
public class EncryptedElementType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptedData",
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      required = true
   )
   protected EncryptedData encryptedData;
   @XmlElement(
      name = "EncryptedKey",
      namespace = "http://www.w3.org/2001/04/xmlenc#"
   )
   protected List<EncryptedKey> encryptedKeies;

   public EncryptedData getEncryptedData() {
      return this.encryptedData;
   }

   public void setEncryptedData(EncryptedData value) {
      this.encryptedData = value;
   }

   public List<EncryptedKey> getEncryptedKeies() {
      if (this.encryptedKeies == null) {
         this.encryptedKeies = new ArrayList();
      }

      return this.encryptedKeies;
   }
}
