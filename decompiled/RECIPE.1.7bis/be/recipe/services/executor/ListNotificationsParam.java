package be.recipe.services.executor;

import be.recipe.services.PartyIdentification;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class ListNotificationsParam extends PartyIdentification {
   private boolean readFlag;
   private byte[] symmKey;

   public ListNotificationsParam() {
   }

   public ListNotificationsParam(boolean readFlag, byte[] symmKey) {
      this.readFlag = readFlag;
      this.symmKey = symmKey;
   }

   public boolean isReadFlag() {
      return this.readFlag;
   }

   public void setReadFlag(boolean readFlag) {
      this.readFlag = readFlag;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }
}
