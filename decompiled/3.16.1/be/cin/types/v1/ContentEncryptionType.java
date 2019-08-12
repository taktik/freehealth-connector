package be.cin.types.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ContentEncryptionType"
)
@XmlEnum
public enum ContentEncryptionType {
   @XmlEnumValue("encryptedForKnownRecipient")
   ENCRYPTED_FOR_KNOWN_RECIPIENT("encryptedForKnownRecipient"),
   @XmlEnumValue("encryptedForKnownIO")
   ENCRYPTED_FOR_KNOWN_IO("encryptedForKnownIO"),
   @XmlEnumValue("encryptedForKnownHCP")
   ENCRYPTED_FOR_KNOWN_HCP("encryptedForKnownHCP"),
   @XmlEnumValue("encryptedForKnownBED")
   ENCRYPTED_FOR_KNOWN_BED("encryptedForKnownBED"),
   @XmlEnumValue("encryptedForKnownCINNIC")
   ENCRYPTED_FOR_KNOWN_CINNIC("encryptedForKnownCINNIC"),
   @XmlEnumValue("toEncryptByIM")
   TO_ENCRYPT_BY_IM("toEncryptByIM"),
   @XmlEnumValue("decryptedByIM")
   DECRYPTED_BY_IM("decryptedByIM");

   private final String value;

   private ContentEncryptionType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static ContentEncryptionType fromValue(String v) {
      ContentEncryptionType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ContentEncryptionType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
