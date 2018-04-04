package org.taktik.connector.business.genericasync.builders.impl;

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
import org.taktik.connector.business.mycarenetdomaincommons.mapper.DomainBlobMapper;
import org.taktik.connector.business.genericasync.builders.RequestObjectBuilder;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

public class RequestObjectBuilderImpl implements RequestObjectBuilder {
   public final Post buildPostRequest(CommonInput commonInput, Blob blob, byte[] xades) {
      Post post = new Post();
      post.setCommonInput(commonInput);
      post.setDetail(blob);
      if (xades != null) {
         post.setXadesT(DomainBlobMapper.mapB64fromByte(xades));
      }

      return post;
   }

   public final Get buildGetRequest(OrigineType origin, MsgQuery msgQuery, Query tackQuery) {
      Get get = new Get();
      get.setMsgQuery(msgQuery);
      get.setOrigin(origin);
      get.setTAckQuery(tackQuery);
      return get;
   }

   public final Confirm buildConfirmRequest(OrigineType origin, List<MsgResponse> msgResponses, List<TAckResponse> tackResponses) throws TechnicalConnectorException, DataFormatException {
      List<byte[]> msgHashValues = new ArrayList();
      List<byte[]> tackContents = new ArrayList();
      Iterator i$;
      if (msgResponses != null && !msgResponses.isEmpty()) {
         i$ = msgResponses.iterator();

         while(i$.hasNext()) {
            MsgResponse msgResponse = (MsgResponse)i$.next();
            msgHashValues.add(msgResponse.getDetail().getHashValue());
         }
      } else {
         msgHashValues.add(new byte[0]);
      }

      if (tackResponses != null && !tackResponses.isEmpty()) {
         i$ = tackResponses.iterator();

         while(i$.hasNext()) {
            TAckResponse tackResponse = (TAckResponse)i$.next();
            tackContents.add(tackResponse.getTAck().getValue());
         }
      } else {
         tackContents.add(new byte[0]);
      }

      return this.buildConfirmRequestWithHashes(origin, msgHashValues, tackContents);
   }

   public Confirm buildConfirmRequestWithHashes(OrigineType origin, List<byte[]> msgHashValues, List<byte[]> tackContents) {
      Confirm confirm = new Confirm();
      confirm.setOrigin(origin);
      confirm.getMsgHashValues().addAll(msgHashValues);
      confirm.getTAckContents().addAll(tackContents);
      return confirm;
   }

   public Query createQuery(Integer max, Boolean include) {
      Query query = new Query();
      query.setInclude(include);
      query.setMax(max);
      return query;
   }

   public MsgQuery createMsgQuery(Integer max, Boolean include, String... messageNames) {
      MsgQuery msgQuery = new MsgQuery();
      msgQuery.setInclude(include);
      msgQuery.setMax(max);
      String[] arr$ = messageNames;
      int len$ = messageNames.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String messageName = arr$[i$];
         msgQuery.getMessageNames().add(messageName);
      }

      return msgQuery;
   }

   public void initialize(Map<String, Object> parameterMap) throws TechnicalConnectorException {
   }
}
