package org.taktik.connector.business.dmg.builders;

import be.cin.nip.async.generic.MsgResponse;
import org.taktik.connector.business.dmg.domain.DmgBuilderResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendResponseType;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;

public interface ResponseObjectBuilder {
   DmgBuilderResponse handleSendResponseType(SendResponseType var1) throws TechnicalConnectorException, NoSuchAlgorithmException, DataFormatException;

   DmgBuilderResponse handleAsyncResponse(MsgResponse var1) throws TechnicalConnectorException;
}
