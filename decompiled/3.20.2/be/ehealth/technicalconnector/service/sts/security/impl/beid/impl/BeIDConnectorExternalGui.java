package be.ehealth.technicalconnector.service.sts.security.impl.beid.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.impl.beid.BeIDConnectorGui;
import be.ehealth.technicalconnector.utils.PCSCUtils;
import be.ehealth.technicalconnector.utils.SecureString;
import be.fedict.commons.eid.client.PINPurpose;
import be.fedict.commons.eid.client.spi.UserCancelledException;
import java.security.GeneralSecurityException;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeIDConnectorExternalGui implements BeIDConnectorGui {
   public static final String PROP_VERIFY_PIN = "be.ehealth.technicalconnector.service.sts.security.impl.beid.impl.verify_pin";
   private static final Logger LOG = LoggerFactory.getLogger(BeIDConnectorExternalGui.class);
   private BeIDConnectorGui backup;
   private Map<PINPurpose, SecureString> pincodeMap;
   private Integer triesLeft;
   private ConfigValidator config;

   public static BeIDConnectorExternalGui getInstance() {
      return BeIDConnectorExternalGui.BeIDConnectorExternalGuiSingleton.INSTANCE.getBeIDConnectorExternalGui();
   }

   private BeIDConnectorExternalGui() {
      this.backup = new BeIDConnectorGuiSwing();
      this.pincodeMap = new EnumMap(PINPurpose.class);
      this.config = ConfigFactory.getConfigValidator();
   }

   public void setLocale(Locale newLocale) {
   }

   public Locale getLocale() {
      return null;
   }

   public void advisePINBlocked() {
      this.backup.advisePINBlocked();
   }

   public void advisePINChanged() {
      this.backup.advisePINChanged();
   }

   public void advisePINPadChangePIN(int arg0) {
      this.backup.advisePINPadChangePIN(arg0);
   }

   public void advisePINPadNewPINEntry(int arg0) {
      this.backup.advisePINPadNewPINEntry(arg0);
   }

   public void advisePINPadNewPINEntryAgain(int arg0) {
      this.backup.advisePINPadNewPINEntryAgain(arg0);
   }

   public void advisePINPadOldPINEntry(int arg0) {
      this.backup.advisePINPadOldPINEntry(arg0);
   }

   public void advisePINPadOperationEnd() {
      this.backup.advisePINPadOperationEnd();
   }

   public void advisePINPadPUKEntry(int arg0) {
      this.backup.advisePINPadPUKEntry(arg0);
   }

   public void advisePINUnblocked() {
      this.backup.advisePINUnblocked();
   }

   public void adviseSecureReaderOperation() {
      this.backup.adviseSecureReaderOperation();
   }

   public void adviseSecureReaderOperationEnd() {
      this.backup.adviseSecureReaderOperationEnd();
   }

   public char[][] obtainOldAndNewPIN(int arg0) {
      return this.backup.obtainOldAndNewPIN(arg0);
   }

   public void advisePINPadPINEntry(int arg0, PINPurpose arg1, String arg2) {
      this.backup.advisePINPadPINEntry(arg0, arg1, arg2);
   }

   public char[] obtainPIN(int arg0, PINPurpose arg1, String arg2) throws UserCancelledException {
      return this.obtainPIN(arg0, arg1);
   }

   public char[] obtainPIN(int triesLeft, PINPurpose type) throws UserCancelledException {
      if (!this.pincodeMap.containsKey(type)) {
         char[] result = this.backup.obtainPIN(triesLeft, type);

         try {
            this.pincodeMap.put(type, new SecureString(result));
         } catch (Exception var5) {
            LOG.error(var5.getClass().getSimpleName() + ":" + var5.getMessage(), var5);
         }

         return ArrayUtils.clone(result);
      } else {
         SecureString content = (SecureString)this.pincodeMap.get(type);
         if (this.triesLeft == null) {
            this.triesLeft = triesLeft;
         } else if (this.triesLeft.compareTo(triesLeft) != 0) {
            LOG.warn("Second attempt detected: reseting pincode.");
            this.pincodeMap.remove(type);
            throw new UserCancelledException();
         }

         try {
            if (content != null && content.getValue().length != 0) {
               return ArrayUtils.clone(content.getValue());
            } else {
               LOG.error("No pincode detected.");
               throw new UserCancelledException();
            }
         } catch (Exception var6) {
            LOG.error(var6.getClass().getSimpleName() + ":" + var6.getMessage(), var6);
            throw new UserCancelledException();
         }
      }
   }

   public char[][] obtainPUKCodes(int arg0) {
      return this.backup.obtainPUKCodes(arg0);
   }

   public void advisePINPadPINEntry(int retriesLeft, PINPurpose type) {
      this.backup.advisePINPadPINEntry(retriesLeft, type);
   }

   public void setPIN(PINPurpose pinpurpose, char[] pin) throws TechnicalConnectorException {
      try {
         if ("true".equalsIgnoreCase(this.config.getProperty("be.ehealth.technicalconnector.service.sts.security.impl.beid.impl.verify_pin", "true"))) {
            PCSCUtils.verifyPin(ArrayUtils.clone(pin));
         }

         this.pincodeMap.put(pinpurpose, new SecureString(ArrayUtils.clone(pin)));
      } catch (GeneralSecurityException var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var4, new Object[]{var4.getMessage()});
      }
   }

   public void setFallbackBeIDConnectorGui(BeIDConnectorGui backup) {
      this.backup = backup;
   }

   // $FF: synthetic method
   BeIDConnectorExternalGui(Object x0) {
      this();
   }

   private static enum BeIDConnectorExternalGuiSingleton {
      INSTANCE;

      private BeIDConnectorExternalGui instance = new BeIDConnectorExternalGui();

      public BeIDConnectorExternalGui getBeIDConnectorExternalGui() {
         return this.instance;
      }
   }
}
