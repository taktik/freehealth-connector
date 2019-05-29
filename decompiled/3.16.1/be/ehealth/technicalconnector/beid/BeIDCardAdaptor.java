package be.ehealth.technicalconnector.beid;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fedict.commons.eid.client.BeIDCard;

public interface BeIDCardAdaptor {
   BeIDCard getBeIDCard() throws TechnicalConnectorException;
}
