package be.ehealth.technicalconnector.service.sts.security.impl.beid.impl;

import be.ehealth.technicalconnector.service.sts.security.impl.beid.BeIDConnectorGui;
import be.fedict.commons.eid.client.PINPurpose;
import be.fedict.commons.eid.client.spi.UserCancelledException;
import be.fedict.commons.eid.dialogs.DefaultBeIDCardUI;

public class BeIDConnectorGuiSwing extends DefaultBeIDCardUI implements BeIDConnectorGui {
   public void advisePINPadPINEntry(int retriesLeft, PINPurpose purpose) {
      super.advisePINPadPINEntry(retriesLeft, purpose, "technical-connector");
   }

   public char[] obtainPIN(int retriesLeft, PINPurpose reason) throws UserCancelledException {
      return this.obtainPIN(retriesLeft, reason, "technical-connector");
   }
}
