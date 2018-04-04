package be.ehealth.businessconnector.hub.security;

import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

public class IntrahubEncryptionUtil<X> {
   private static final Logger LOG = LoggerFactory.getLogger(IntrahubEncryptionUtil.class);

   public X handleEncryption(X request, Crypto crypto) throws IntraHubBusinessConnectorException, TechnicalConnectorException {
      try {
         Marshaller marshaller = JAXBContext.newInstance(request.getClass()).createMarshaller();
         DOMResult res = new DOMResult();
         marshaller.marshal(request, res);
         Document doc = FolderEncryptor.encryptFolder((Document)res.getNode(), crypto);
         Unmarshaller unmarshaller = JAXBContext.newInstance(request.getClass()).createUnmarshaller();
         return unmarshaller.unmarshal(doc.getFirstChild());
      } catch (JAXBException var7) {
         LOG.error("JAXBException when (un)marchalling the request", var7);
         return request;
      }
   }
}
