package be.ehealth.businessconnector.genericasync.builders;

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
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.business.mycarenetdomaincommons.domain.async.PostContent;
import be.ehealth.businessconnector.genericasync.domain.ConfigName;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import java.util.List;
import java.util.zip.DataFormatException;

public interface RequestObjectBuilder extends ConfigurableImplementation {
   Query createQuery(Integer var1, Boolean var2);

   MsgQuery createMsgQuery(Integer var1, Boolean var2, String... var3);

   Post buildPostRequest(CommonInput var1, Blob var2, byte[] var3);

   Post buildPostRequest(String var1, PostContent var2) throws TechnicalConnectorException;

   Get buildGetRequest(OrigineType var1, MsgQuery var2, Query var3);

   Get buildGetRequest(OrigineType var1, MsgQuery var2, Query var3, QueryParameters var4);

   Get buildGetRequest(OrigineType var1, MsgQuery var2, Query var3, QueryParameters var4, byte[] var5);

   Get buildGetRequest(OrigineType var1, MsgQuery var2, Query var3, byte[] var4);

   Confirm buildConfirmRequest(OrigineType var1, List<MsgResponse> var2, List<TAckResponse> var3) throws TechnicalConnectorException, DataFormatException;

   Confirm buildConfirmRequestWithHashes(OrigineType var1, List<byte[]> var2, List<byte[]> var3);

   Confirm buildConfirmWithReferences(OrigineType var1, GetResponse var2);

   Post buildPostRequest(String var1, String var2, String var3, Object var4, String var5, InputReference var6) throws TechnicalConnectorException;

   Post buildPostRequest(String var1, String var2, String var3, ConfigName var4, byte[] var5, InputReference var6) throws TechnicalConnectorException;

   Post buildPostRequest(String var1, String var2, String var3, byte[] var4, InputReference var5) throws TechnicalConnectorException;
}
