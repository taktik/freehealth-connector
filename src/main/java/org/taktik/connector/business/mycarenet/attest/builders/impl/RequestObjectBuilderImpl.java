package org.taktik.connector.business.mycarenet.attest.builders.impl;

import be.cin.encrypted.BusinessContent;
import be.cin.encrypted.EncryptedKnownContent;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import org.taktik.connector.business.common.domain.Patient;
import org.taktik.connector.business.kmehrcommons.HcPartyUtil;
import org.taktik.connector.business.mycarenetdomaincommons.builders.BlobBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.builders.CommonBuilder;
import org.taktik.connector.business.mycarenetdomaincommons.builders.RequestBuilderFactory;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.business.mycarenetdomaincommons.domain.Ssin;
import org.taktik.connector.business.mycarenetdomaincommons.util.McnConfigUtil;
import org.taktik.connector.business.mycarenet.attest.builders.RequestObjectBuilder;
import org.taktik.connector.business.mycarenet.attest.domain.InputReference;
import org.taktik.connector.business.mycarenet.attest.exception.AttestBusinessConnectorException;
import org.taktik.connector.business.mycarenet.attest.exception.AttestBusinessConnectorExceptionValues;
import org.taktik.connector.business.mycarenet.attest.mappers.BlobMapper;
import org.taktik.connector.business.mycarenet.attest.mappers.CommonInputMapper;
import org.taktik.connector.business.mycarenet.attest.mappers.RoutingMapper;
import org.taktik.connector.business.mycarenet.attest.security.AttestEncryptionUtil;
import org.taktik.connector.business.mycarenet.attest.validators.impl.AttestXmlValidatorImpl;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.SessionUtil;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.messageservices.core.v1.RequestType;
import be.fgov.ehealth.messageservices.core.v1.SendTransactionRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestObjectBuilderImpl implements RequestObjectBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(RequestObjectBuilderImpl.class);

   private RequestType buildRequest(InputReference references) throws TechnicalConnectorException {
      RequestType req = new RequestType();
      req.setId(new IDKMEHR()/* TODO HcPartyUtil.createKmehrId("attest", references.getInputReference())*/);
      req.setAuthor(HcPartyUtil.createAuthor("attest"));
      req.setDate(new DateTime());
      req.setTime(new DateTime());
      return req;
   }

   private void checkInputParameters(InputReference inputReference, Ssin patientSsin, DateTime referenceDate) throws AttestBusinessConnectorException {
      this.checkParameterNotNull(inputReference, "InputReference");
      this.checkParameterNotNull(inputReference.getInputReference(), "Input reference");
      this.checkParameterNotNull(referenceDate, "Reference date");
      this.checkParameterNotNull(patientSsin, "patientSsin");
      this.checkParameterNotNull(patientSsin, "value of patientSsin");
   }

   private void checkParameterNotNull(Object references, String parameterName) throws AttestBusinessConnectorException {
      if (references == null) {
         throw new AttestBusinessConnectorException(AttestBusinessConnectorExceptionValues.PARAMETER_NULL, new Object[]{parameterName});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SendTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(CommonInputType.class);
      JaxbContextFactory.initJaxbContext(RoutingType.class);
   }
}
