package be.ehealth.businessconnector.mycarenet.attest.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

public class AttestEncryptionUtil<X> {
   private static final Logger LOG = LoggerFactory.getLogger(AttestEncryptionUtil.class);

   public byte[] handleEncryption(X request, Crypto crypto, String detailId) throws TechnicalConnectorException, TransformerException, UnsupportedEncodingException, JAXBException {
      Marshaller marshaller = JAXBContext.newInstance(new Class[]{request.getClass()}).createMarshaller();
      DOMResult res = new DOMResult();
      marshaller.marshal(request, res);
      return BusinessContentEncryptor.encrypt((Document)res.getNode(), crypto, detailId);
   }
}
