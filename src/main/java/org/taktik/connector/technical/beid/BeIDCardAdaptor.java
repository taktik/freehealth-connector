package org.taktik.connector.technical.beid;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fedict.commons.eid.client.BeIDCard;

public interface BeIDCardAdaptor {
   BeIDCard getBeIDCard() throws TechnicalConnectorException;
}
