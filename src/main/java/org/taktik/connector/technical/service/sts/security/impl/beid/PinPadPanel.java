package org.taktik.connector.technical.service.sts.security.impl.beid;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.awt.event.ActionListener;

public interface PinPadPanel {
   void setActionListenerOnGoButton(ActionListener var1);

   char[] getPassWord() throws TechnicalConnectorException;

   void setRetriesLeft(int var1);
}
