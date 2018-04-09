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
   Post buildPostRequest(CommonInput commonInput, Blob blob, byte[] xades);
   Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery);
   Confirm buildConfirmRequest(OrigineType origin, List<MsgResponse> msgResponses, List<TAckResponse> tackResponses) throws TechnicalConnectorException, DataFormatException;
   Confirm buildConfirmRequestWithHashes(OrigineType origin, List<byte[]> msgHashValues, List<byte[]> tackContents);
   Query createQuery(Integer max, Boolean include);
   MsgQuery createMsgQuery(Integer max, Boolean include, String... messageNames);
}
