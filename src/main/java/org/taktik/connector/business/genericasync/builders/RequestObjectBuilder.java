package org.taktik.connector.business.genericasync.builders;

import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.OrigineType;
import be.cin.nip.async.generic.Confirm;
import be.cin.nip.async.generic.Get;
import be.cin.nip.async.generic.GetResponse;
import be.cin.nip.async.generic.MsgQuery;
import be.cin.nip.async.generic.MsgResponse;
import be.cin.nip.async.generic.Post;
import be.cin.nip.async.generic.Query;
import be.cin.nip.async.generic.QueryParameters;
import be.cin.nip.async.generic.TAckResponse;
import be.cin.types.v1.Blob;
import org.taktik.connector.business.genericasync.domain.ConfigName;
import org.taktik.connector.business.genericasync.exception.GenAsyncBusinessConnectorException;
import org.taktik.connector.business.mycarenetdomaincommons.domain.InputReference;
import org.taktik.connector.business.mycarenetdomaincommons.domain.async.PostContent;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import java.util.List;
import java.util.zip.DataFormatException;

public interface RequestObjectBuilder extends ConfigurableImplementation {
   Query createQuery(Integer max, Boolean include);

   MsgQuery createMsgQuery(Integer max, Boolean include, String... messageNames);

   Post buildPostRequest(CommonInput commonInput, Blob blob, byte[] xades);

   Post buildPostRequest(String projectName, PostContent postContent, String licenseUsername, String licensePassword) throws TechnicalConnectorException;

   Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery);

   Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, QueryParameters queryParameters);

   Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, QueryParameters queryParameters, byte[] replyToEtk);

   Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery, byte[] replyToEtk);

   Confirm buildConfirmRequest(OrigineType origin, List<MsgResponse> msgResponses, List<TAckResponse> tackResponses) throws TechnicalConnectorException, DataFormatException;

   Confirm buildConfirmRequestWithHashes(OrigineType origin, List<byte[]> msgHashValues, List<byte[]> tackContents);

   Confirm buildConfirmWithReferences(OrigineType origin, GetResponse getResponse);

   Post buildPostRequest(String messageName, String projectName, String platformName, Object object, String schemaLocation, KeyStoreCredential credential, KeyDepotManager keyDepotManager, InputReference inputReference, String licenseUsername, String licensePassword) throws TechnicalConnectorException, InstantiationException, GenAsyncBusinessConnectorException;

   Post buildPostRequest(String messageName, String projectName, String platformName, ConfigName configName, byte[] xmlByteArray, KeyStoreCredential credential, KeyDepotManager keyDepotManager, InputReference inputReference, String licenseUsername, String licensePassword) throws TechnicalConnectorException, InstantiationException, GenAsyncBusinessConnectorException;

   Post buildPostRequest(String messageName, String projectName, String platformName, byte[] xmlByteArray, KeyStoreCredential credential, KeyDepotManager keyDepotManager, InputReference inputReference, String licenseUsername, String licensePassword) throws TechnicalConnectorException, GenAsyncBusinessConnectorException, InstantiationException;
}
