package org.taktik.connector.business.genericasync.builders;

import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.MsgQuery;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.Query;
import be.cin.nip.async.generic.TAckResponse;
import be.cin.types.v1.Blob;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import java.util.List;
import java.util.zip.DataFormatException;

public interface RequestObjectBuilder extends ConfigurableImplementation {
   Query createQuery(Integer var1, Boolean var2);

   MsgQuery createMsgQuery(Integer var1, Boolean var2, String... var3);

   Post buildPostRequest(CommonInput var1, Blob var2, byte[] var3);

   Get buildGetRequest(OrigineType var1, MsgQuery var2, Query var3);

   Confirm buildConfirmRequest(OrigineType var1, List<MsgResponse> var2, List<TAckResponse> var3) throws TechnicalConnectorException, DataFormatException;

   Confirm buildConfirmRequestWithHashes(OrigineType var1, List<byte[]> var2, List<byte[]> var3);
}
