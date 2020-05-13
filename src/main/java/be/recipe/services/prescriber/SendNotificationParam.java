package be.recipe.services.prescriber;

import be.recipe.services.core.PartyIdentification;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "sendNotificationParam",
   propOrder = {"content", "symmKey"}
)
@XmlRootElement(
   name = "sendNotificationParam"
)
public class SendNotificationParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected byte[] content;
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] value) {
      this.content = value;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof SendNotificationParam)) return false;
      if (!super.equals(o)) return false;
      SendNotificationParam that = (SendNotificationParam) o;
      return Arrays.equals(content, that.content) &&
              Arrays.equals(symmKey, that.symmKey);
   }

   @Override
   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + Arrays.hashCode(content);
      result = 31 * result + Arrays.hashCode(symmKey);
      return result;
   }
}
