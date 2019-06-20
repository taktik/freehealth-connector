package org.taktik.connector.business.intrahubcommons.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;

public class IntrahubEncryptionUtil {
   private static final Logger LOG = LoggerFactory.getLogger(IntrahubEncryptionUtil.class);

   public static <T> T encryptFolder(T request, Crypto crypto, Long hubId, String hubApplication) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      if (LOG.isDebugEnabled()) {
         MarshallerHelper<T, T> helper = new MarshallerHelper(request.getClass(), request.getClass());
         LOG.debug("Pre-encrypted request:\n" + helper.toString(request));
      }

      try {
         Marshaller marshaller = JAXBContext.newInstance(request.getClass()).createMarshaller();
         DOMResult res = new DOMResult();
         marshaller.marshal(request, res);
         Document doc = FolderEncryptor.encryptFolder((Document)res.getNode(), crypto, hubId, hubApplication);
         Unmarshaller unmarshaller = JAXBContext.newInstance(request.getClass()).createUnmarshaller();
         return (T) unmarshaller.unmarshal(doc.getFirstChild());
      } catch (JAXBException var7) {
         LOG.error("JAXBException when (un)marchalling the request", var7);
         return request;
      }
   }
}
