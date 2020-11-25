package be.ehealth.businessconnector.dmg.builders;

import be.cin.nip.async.generic.MsgResponse;
import be.ehealth.businessconnector.dmg.domain.DmgBuilderResponse;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.globalmedicalfile.protocol.v1.SendResponseType;
import java.security.NoSuchAlgorithmException;
import java.util.zip.DataFormatException;

public interface ResponseObjectBuilder {
   DmgBuilderResponse handleSendResponseType(SendResponseType var1) throws TechnicalConnectorException, NoSuchAlgorithmException, DataFormatException;

   DmgBuilderResponse handleAsyncResponse(MsgResponse var1) throws TechnicalConnectorException;
}
