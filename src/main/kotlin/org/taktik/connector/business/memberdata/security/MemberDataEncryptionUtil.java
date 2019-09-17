package org.taktik.connector.business.memberdata.security;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import java.util.Set;

public class MemberDataEncryptionUtil<X> {
   public byte[] handleEncryption(X request, Crypto crypto) throws TechnicalConnectorException {
      byte[] byteArray = ConnectorXmlUtils.toByteArray(request);
      return seal(crypto, byteArray);
   }

   private static byte[] seal(Crypto crypto, byte[] content) throws TechnicalConnectorException {
      return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, getEtk(), content);
   }

   private static Set<EncryptionToken> getEtk() throws TechnicalConnectorException {
      return RequestBuilderUtil.getEtk("memberdata");
   }
}
