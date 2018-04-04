package org.taktik.connector.technical.service.sts.security.impl.beid;

import be.fedict.commons.eid.client.PINPurpose;
import be.fedict.commons.eid.client.spi.BeIDCardUI;
import be.fedict.commons.eid.client.spi.UserCancelledException;

public interface BeIDConnectorGui extends BeIDCardUI {
   void advisePINBlocked();

   void advisePINChanged();

   void advisePINPadChangePIN(int var1);

   void advisePINPadNewPINEntry(int var1);

   void advisePINPadNewPINEntryAgain(int var1);

   void advisePINPadOldPINEntry(int var1);

   void advisePINPadOperationEnd();

   void advisePINPadPUKEntry(int var1);

   void advisePINUnblocked();

   void adviseSecureReaderOperation();

   void adviseSecureReaderOperationEnd();

   char[][] obtainOldAndNewPIN(int var1);

   char[] obtainPIN(int var1, PINPurpose var2) throws UserCancelledException;

   char[] obtainPIN(int var1, PINPurpose var2, String var3) throws UserCancelledException;

   char[][] obtainPUKCodes(int var1);

   void advisePINPadPINEntry(int var1, PINPurpose var2);

   void advisePINPadPINEntry(int var1, PINPurpose var2, String var3);
}
