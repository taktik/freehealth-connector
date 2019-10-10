package be.fgov.ehealth.technicalconnector.signature.impl.xades;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.impl.SignatureUtils;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.QualifyingPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.resolvers.DocumentResolver;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.ObjectContainer;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XadesHandler {
   private final Map<String, Object> options;
   private XMLSignature sig;
   private Credential signatureCredential;
   private XadesSpecification[] specs;
   private Element xadesQualProperties;

   public XadesHandler(XMLSignature sig, Credential signatureCredential, Map<String, Object> options, XadesSpecification... specs) throws TechnicalConnectorException {
      this.sig = sig;
      this.signatureCredential = signatureCredential;
      this.options = options;
      this.specs = specs;
   }

   public void before() throws TechnicalConnectorException, XMLSecurityException {
      if (!ArrayUtils.isEmpty(this.specs)) {
         ObjectContainer container = new ObjectContainer(this.sig.getDocument());
         this.sig.appendObject(container);
         QualifyingPropertiesBuilder qualProperties = new QualifyingPropertiesBuilder();
         String xadesSignedId = IdGeneratorFactory.getIdGenerator("uuid").generateId();
         XadesSpecification[] arr$ = this.specs;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            XadesSpecification spec = arr$[i$];
            spec.addOptionalBeforeSignatureParts(qualProperties.getSignedProps(), this.sig, this.signatureCredential, xadesSignedId, this.options);
         }

         Document xadesQualPropertiesDocument = qualProperties.buildBeforeSigningAsDocument();
         this.xadesQualProperties = (Element)this.sig.getDocument().importNode(xadesQualPropertiesDocument.getDocumentElement(), true);
         container.appendChild(this.xadesQualProperties);
         this.sig.addResourceResolver(new DocumentResolver(xadesQualPropertiesDocument));
         Transforms xadesTransform = new Transforms(this.sig.getDocument());
         xadesTransform.addTransform("http://www.w3.org/2001/10/xml-exc-c14n#");
         this.sig.addDocument(ref(qualProperties.getSignedProps().getId()), xadesTransform, (String)SignatureUtils.getOption("digestURI", this.options, "http://www.w3.org/2001/04/xmlenc#sha256"), (String)null, "http://uri.etsi.org/01903#SignedProperties");
      }
   }

   public void after() throws TechnicalConnectorException {
      if (!ArrayUtils.isEmpty(this.specs)) {
         this.xadesQualProperties.setAttribute("Target", ref(this.sig.getId()));
         String xadesUnsignedId = IdGeneratorFactory.getIdGenerator("uuid").generateId();
         UnsignedPropertiesBuilder unsignedProperties = new UnsignedPropertiesBuilder();
         unsignedProperties.setId(xadesUnsignedId);
         XadesSpecification[] arr$ = this.specs;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            XadesSpecification spec = arr$[i$];
            spec.addOptionalAfterSignatureParts(unsignedProperties, this.sig, this.signatureCredential, xadesUnsignedId, this.options);
         }

         Document xadesUnsignedPropertiesDoc = unsignedProperties.buildAsDocument();
         if (xadesUnsignedPropertiesDoc != null) {
            Element xadesUnsignedProperties = (Element)this.sig.getDocument().importNode(unsignedProperties.buildAsDocument().getDocumentElement(), true);
            this.xadesQualProperties.appendChild(xadesUnsignedProperties);
         }

      }
   }

   private static String ref(String id) {
      return "#" + id;
   }
}
