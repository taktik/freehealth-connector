package be.ehealth.technicalconnector.service.sts.security.impl.beid;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.awt.event.ActionListener;

public interface PinPadPanel {
   void setActionListenerOnGoButton(ActionListener var1);

   char[] getPassWord() throws TechnicalConnectorException;

   void setRetriesLeft(int var1);
}
