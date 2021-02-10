package be.ehealth.business.intrahubcommons.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.SessionUtil;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

public class IntrahubEncryptionUtil {
   private static final Logger LOG = LoggerFactory.getLogger(IntrahubEncryptionUtil.class);

   public static <T> T encryptFolder(T request, String hubIdPropKey, String hubAppIdPropKey) throws TechnicalConnectorException {
      if (LOG.isDebugEnabled()) {
         MarshallerHelper<T, T> helper = new MarshallerHelper(request.getClass(), request.getClass());
         LOG.debug("Pre-encrypted request:\n" + helper.toString(request));
      }

      try {
         Marshaller marshaller = JAXBContext.newInstance(new Class[]{request.getClass()}).createMarshaller();
         DOMResult res = new DOMResult();
         marshaller.marshal(request, res);
         Document doc = FolderEncryptor.encryptFolder((Document)res.getNode(), SessionUtil.getEncryptionCrypto(), hubIdPropKey, hubAppIdPropKey);
         Unmarshaller unmarshaller = JAXBContext.newInstance(new Class[]{request.getClass()}).createUnmarshaller();
         return unmarshaller.unmarshal(doc.getFirstChild());
      } catch (JAXBException var7) {
         LOG.error("JAXBException when (un)marchalling the request", var7);
         return request;
      }
   }
}
