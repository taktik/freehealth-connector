package be.ehealth.businessconnector.mycarenet.memberdatacommons.security;

import be.ehealth.business.mycarenetcommons.builders.util.RequestBuilderUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
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
