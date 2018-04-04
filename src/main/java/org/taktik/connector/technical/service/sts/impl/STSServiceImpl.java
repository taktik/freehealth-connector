package org.taktik.connector.technical.service.sts.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.exception.InstantiationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute;
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator;
import org.taktik.connector.technical.service.sts.domain.SAMLNameIdentifier;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.utils.SAMLConverter;
import org.taktik.connector.technical.service.sts.utils.SAMLHelper;
import org.taktik.connector.technical.service.ws.ServiceFactory;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Source;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class STSServiceImpl extends AbstractSTSService {
   private static final String ATTRIBUTE_NAME = "AttributeName";
   private static final Logger LOG = LoggerFactory.getLogger(STSServiceImpl.class);
   private static final String ATTRIBUTE_QUERY = "AttributeQuery";
   private static final String AUTHENTICATION_STATEMENT = "AuthenticationStatement";
   private static final String ATTRIBUTE_STATEMENT = "AttributeStatement";
   private static final String CONFIRMATION_METHOD = "ConfirmationMethod";
   private static final String ATTRIBUTE_VALUE = "AttributeValue";
   private static final String ATTRIBUTE = "Attribute";
   private static final String ATTRIBUTE_NAMESPACE = "AttributeNamespace";
   private static final String XMLNS_SAML = "urn:oasis:names:tc:SAML:1.0:assertion";
   public static final String HOK_KEYINFO_TYPE = "org.taktik.connector.technical.service.sts.keyinfo";
   public static final String ALWAYS_SIGN_INNER_REQUEST = "org.taktik.connector.technical.service.sts.always.sign.inner.request";
   private static final String JSR105PROVIDER_CLASSNAME_DEFAULT = "org.jcp.xml.dsig.internal.dom.XMLDSigRI";
   private static XMLSignatureFactory xmlSignatureFactory;

   public Element getToken(Credential headerCredentials, Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String authenticationMethod, String nameQualifier, String value, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException {
      try {
         SAMLNameIdentifier nameIdentifier = this.generateNameIdentifier(headerCredentials, nameQualifier, value);
         String requestTemplate = "";
         boolean sign = false;
         if (subjectConfirmationMethod.equalsIgnoreCase("urn:oasis:names:tc:SAML:1.0:cm:holder-of-key")) {
            sign = true;
            String keyinfoType = ConfigFactory.getConfigValidator().getProperty("org.taktik.connector.technical.service.sts.keyinfo", "x509");
            if ("publickey".equalsIgnoreCase(keyinfoType)) {
               requestTemplate = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/legacy/issue.samlv11.hok.publickey.template.xml"));
            } else {
               requestTemplate = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/legacy/issue.samlv11.hok.template.xml"));
            }
         } else {
            if (!subjectConfirmationMethod.equalsIgnoreCase("urn:oasis:names:tc:SAML:1.0:cm:sender-vouches")) {
               throw new UnsupportedOperationException("SubjectConfirmationMethod [" + subjectConfirmationMethod + "] not supported.");
            }

            if (StringUtils.isEmpty(authenticationMethod)) {
               requestTemplate = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/legacy/issue.samlv11.sv.template.xml"));
            } else {
               requestTemplate = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/legacy/issue.samlv11.sv.authmethod.template.xml"));
            }
         }

         Document samlRequest = this.generateToken(requestTemplate, sign, headerCredentials, bodyCredentials, nameIdentifier, authenticationMethod, attributes, designators, validity);
         GenericRequest request = ServiceFactory.getSTSService(headerCredentials.getCertificate(), headerCredentials.getPrivateKey());
         request.setSoapAction("urn:be:fgov:ehealth:sts:protocol:v1:RequestSecureToken");
         request.setPayload(samlRequest);
         Source response = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(request).asSource();
         Element stsResponse = SAMLConverter.convert(response);
         String status = SAMLHelper.getStatusCode(stsResponse);
         if (!status.contains("Success")) {
            LOG.warn("The status of the SAMLResponse is " + status + " [" + SAMLHelper.getStatusMessage(stsResponse));
            TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.INVALID_TOKEN;
            throw new TechnicalConnectorException(errorValue, "Inbound Security problem: " + SAMLHelper.getStatusMessage(stsResponse));
         } else if (stsResponse.getElementsByTagName("Assertion").getLength() < 0) {
            String message = "SAMLResponse has a flag succesfull but contains no assertions.";
            LOG.warn(message);
            LOG.warn("SAMLResponse was: " + SAMLConverter.toXMLString(stsResponse));
            LOG.warn("The status of the SAMLResponse is " + status + " [" + SAMLHelper.getStatusMessage(stsResponse));
            TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.INVALID_TOKEN;
            throw new TechnicalConnectorException(errorValue, new Object[]{message});
         } else {
            return SAMLHelper.getAssertion(stsResponse);
         }
      } catch (SOAPException ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.getMessage());
      }
   }

   public Element getToken(Credential headerCredentials, Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException {
      return this.getToken(headerCredentials, bodyCredentials, attributes, designators, null, null, null, subjectConfirmationMethod, validity);
   }

   public Element renewToken(Credential headerCredentials, Credential bodyCredentials, Element samlToken, int validity) throws TechnicalConnectorException {
      List<SAMLAttributeDesignator> designators = new ArrayList();
      List<SAMLAttribute> attributes = new ArrayList();
      NodeList authenticationStatementList = samlToken.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "AuthenticationStatement");
      Set<String> idenAttr = new HashSet();

      String value;
      String namespace;
      for(int i = 0; i < authenticationStatementList.getLength(); ++i) {
         NodeList attrList = ((Element)authenticationStatementList.item(i)).getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "Attribute");

         for(int j = 0; j < attrList.getLength(); ++j) {
            Element attr = (Element)attrList.item(j);
            value = attr.getAttribute("AttributeNamespace");
            namespace = attr.getAttribute("AttributeName");
            String[] values = this.extractTextContent(attr.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "AttributeValue"));
            if (!"urn:be:fgov:certified-namespace:ehealth".equals(value)) {
               attributes.add(new SAMLAttribute(namespace, value, values));
            }
         }
      }

      NodeList attributesList = samlToken.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "AttributeStatement");

      Element attr;
      for(int j = 0; j < attributesList.getLength(); ++j) {
         NodeList attrList = ((Element)attributesList.item(j)).getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "Attribute");

         for(j = 0; j < attrList.getLength(); ++j) {
            attr = (Element)attrList.item(j);
            namespace = attr.getAttribute("AttributeNamespace");
            String name = attr.getAttribute("AttributeName");
            designators.add(new SAMLAttributeDesignator(name, namespace));
            if (!idenAttr.contains(name)) {
               String[] values = this.extractTextContent(attr.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "AttributeValue"));
               if (!"urn:be:fgov:certified-namespace:ehealth".equals(namespace)) {
                  attributes.add(new SAMLAttribute(name, namespace, values));
               }
            }
         }
      }

      String subjectConfirmationMethod = ((Element)samlToken.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "ConfirmationMethod").item(0)).getTextContent();
      String authenticationMethod = null;

      for(int i = 0; i < authenticationStatementList.getLength(); ++i) {
         attr = (Element)authenticationStatementList.item(i);
         authenticationMethod = attr.getAttribute("AuthenticationMethod");
      }

      String nameQualifier = null;
      value = null;
      NodeList nameIdentifierList = samlToken.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "NameIdentifier");

      for(int i = 0; i < attributesList.getLength(); ++i) {
         Element nameIdentifier = (Element)nameIdentifierList.item(i);
         if ("urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified".equals(nameIdentifier.getAttribute("Format"))) {
            nameQualifier = StringEscapeUtils.escapeXml(nameIdentifier.getAttribute("NameQualifier"));
            value = StringEscapeUtils.escapeXml(nameIdentifier.getTextContent());
            break;
         }
      }

      return this.getToken(headerCredentials, bodyCredentials, attributes, designators, authenticationMethod, nameQualifier, value, subjectConfirmationMethod, validity);
   }

   private String[] extractTextContent(NodeList nodelist) {
      String[] result = ArrayUtils.EMPTY_STRING_ARRAY;

      for(int i = 0; i < nodelist.getLength(); ++i) {
         Element el = (Element)nodelist.item(i);
         result = (String[])((String[])ArrayUtils.add(result, el.getTextContent()));
      }

      return result;
   }

   private Document generateToken(String requestTemplate, boolean sign, Credential headerCred, Credential hokCred, SAMLNameIdentifier nameIdentifier, String authmethod, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, int validity) throws TechnicalConnectorException {
      try {
         String request = ConnectorXmlUtils.flatten(requestTemplate);
         request = this.processDefaultFields(request, validity, nameIdentifier);
         request = this.processHolderOfKeyCredentials(hokCred, request);
         request = StringUtils.replace(request, "${authenticationMethod}", authmethod);
         Element payload = ConnectorXmlUtils.toElement(request.getBytes());
         Document doc = payload.getOwnerDocument();
         this.addDesignators(designators, doc);
         this.processAttributes(attributes, doc);
         boolean alwaysSign = Boolean.parseBoolean(ConfigFactory.getConfigValidator().getProperty("org.taktik.connector.technical.service.sts.always.sign.inner.request"));
         if (sign && (!headerCred.getCertificate().equals(hokCred.getCertificate()) || alwaysSign)) {
            try {
               String keyinfoType = ConfigFactory.getConfigValidator().getProperty("org.taktik.connector.technical.service.sts.keyinfo", "x509");
               if ("publickey".equalsIgnoreCase(keyinfoType)) {
                  this.signRequest(doc.getDocumentElement(), hokCred.getPrivateKey(), hokCred.getPublicKey());
               } else {
                  this.signRequest(doc.getDocumentElement(), hokCred.getPrivateKey(), hokCred.getCertificate());
               }
            } catch (Exception var15) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SIGNATURE, new Object[]{"XML signature error: " + var15.getMessage(), var15});
            }
         }

         return doc;
      } catch (CertificateEncodingException var16) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, var16, new Object[]{var16.getMessage()});
      }
   }

   private void processAttributes(List<SAMLAttribute> attributes, Document doc) {
      Element attributeStatement = (Element)doc.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "AttributeStatement").item(0);
      Iterator i$ = attributes.iterator();

      while(i$.hasNext()) {
         SAMLAttribute attr = (SAMLAttribute)i$.next();
         Element attrEl = doc.createElementNS("urn:oasis:names:tc:SAML:1.0:assertion", "saml:Attribute");
         attrEl.setAttribute("AttributeName", attr.getName());
         attrEl.setAttribute("AttributeNamespace", attr.getNamespace());
         this.processAttributeValues(attrEl, attr.getValues());
         attributeStatement.appendChild(attrEl);
      }

   }

   private void addDesignators(List<SAMLAttributeDesignator> designators, Document doc) {
      Element attributeQuery = (Element)doc.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:protocol", "AttributeQuery").item(0);
      Iterator i$ = designators.iterator();

      while(i$.hasNext()) {
         SAMLAttributeDesignator attr = (SAMLAttributeDesignator)i$.next();
         Element attrEl = doc.createElementNS("urn:oasis:names:tc:SAML:1.0:assertion", "saml:AttributeDesignator");
         attrEl.setAttribute("AttributeName", attr.getName());
         attrEl.setAttribute("AttributeNamespace", attr.getNamespace());
         attributeQuery.appendChild(attrEl);
      }

   }

   private String processHolderOfKeyCredentials(Credential hokCred, String request) throws TechnicalConnectorException, CertificateEncodingException {
      if (hokCred != null && hokCred.getCertificate() != null) {
         request = StringUtils.replace(request, "${holder.of.key}", new String(Base64.encode(hokCred.getCertificate().getEncoded())));
         PublicKey publicKey = hokCred.getCertificate().getPublicKey();
         if (publicKey instanceof RSAPublicKey) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey)publicKey;
            request = StringUtils.replace(request, "${publickey.rsa.modulus}", new String(Base64.encode(convertTo(rsaPublicKey.getModulus()))));
            request = StringUtils.replace(request, "${publickey.rsa.exponent}", new String(Base64.encode(convertTo(rsaPublicKey.getPublicExponent()))));
            request = StringUtils.replace(request, "<ds:DSAKeyValue><ds:G>${publickey.dsa.g}<ds:G><ds:P>${publickey.dsa.p}</ds:P><ds:Q>${publickey.dsa.q}</ds:Q></ds:DSAKeyValue>", "");
         } else if (publicKey instanceof DSAPublicKey) {
            DSAPublicKey dsaPublicKey = (DSAPublicKey)publicKey;
            request = StringUtils.replace(request, "${publickey.dsa.g}", new String(Base64.encode(convertTo(dsaPublicKey.getParams().getG()))));
            request = StringUtils.replace(request, "${publickey.dsa.p}", new String(Base64.encode(convertTo(dsaPublicKey.getParams().getP()))));
            request = StringUtils.replace(request, "${publickey.dsa.q}", new String(Base64.encode(convertTo(dsaPublicKey.getParams().getQ()))));
            request = StringUtils.replace(request, "<ds:RSAKeyValue><ds:Modulus>${publickey.rsa.modulus}</ds:Modulus><ds:Exponent>${publickey.rsa.exponent}</ds:Exponent></ds:RSAKeyValue>", "");
         } else {
            LOG.info("Unsupported public key: [" + publicKey.getClass().getName() + "+]");
         }
      }

      return request;
   }

   private void processAttributeValues(Element attrEl, String[] attributeValues) {
      String[] arr$ = attributeValues;
      int len$ = attributeValues.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String attributeValue = arr$[i$];
         Element attrVal = attrEl.getOwnerDocument().createElementNS("urn:oasis:names:tc:SAML:1.0:assertion", "saml:AttributeValue");
         attrVal.setTextContent(attributeValue);
         attrEl.appendChild(attrVal);
      }

   }

   private void signRequest(Element requestElement, PrivateKey privateKey, Object keyInfoValue) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException, KeyException {
      DOMSignContext domSignContext = new DOMSignContext(privateKey, requestElement, requestElement.getFirstChild());
      String requestId = requestElement.getAttribute("RequestID");
      requestElement.setIdAttribute("RequestID", true);
      List<Transform> transforms = new LinkedList();
      transforms.add(xmlSignatureFactory.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec)null));
      transforms.add(xmlSignatureFactory.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", (C14NMethodParameterSpec)null));
      Reference reference = xmlSignatureFactory.newReference("#" + requestId, xmlSignatureFactory.newDigestMethod("http://www.w3.org/2000/09/xmldsig#sha1", (DigestMethodParameterSpec)null), transforms, (String)null, (String)null);
      CanonicalizationMethod canonicalizationMethod = xmlSignatureFactory.newCanonicalizationMethod("http://www.w3.org/2001/10/xml-exc-c14n#", (C14NMethodParameterSpec)null);
      SignatureMethod signatureMethod = xmlSignatureFactory.newSignatureMethod("http://www.w3.org/2000/09/xmldsig#rsa-sha1", (SignatureMethodParameterSpec)null);
      SignedInfo signedInfo = xmlSignatureFactory.newSignedInfo(canonicalizationMethod, signatureMethod, Collections.singletonList(reference));
      KeyInfoFactory keyInfoFactory = xmlSignatureFactory.getKeyInfoFactory();
      KeyInfo keyInfo = null;
      if (keyInfoValue instanceof PublicKey) {
         keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(keyInfoFactory.newKeyValue((PublicKey)keyInfoValue)));
      } else {
         if (!(keyInfoValue instanceof X509Certificate)) {
            throw new IllegalArgumentException("Unsupported keyinfo type [" + keyInfoValue.getClass() + "]");
         }

         keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(keyInfoFactory.newX509Data(Collections.singletonList(keyInfoValue))));
      }

      XMLSignature xmlSignature = xmlSignatureFactory.newXMLSignature(signedInfo, keyInfo);
      xmlSignature.sign(domSignContext);
   }

   private static byte[] convertTo(BigInteger bigInteger) {
      byte[] array = bigInteger.toByteArray();
      if (array[0] == 0) {
         byte[] tmp = new byte[array.length - 1];
         System.arraycopy(array, 1, tmp, 0, tmp.length);
         array = tmp;
      }

      return array;
   }

   static {
      try {
         String providerName = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
         LOG.info("Instantiating providate with class [" + providerName + "]");
         Provider provider = (Provider)Class.forName(providerName).newInstance();
         LOG.info("Using the following provider: " + provider + " " + provider.getInfo());
         xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM", provider);
      } catch (IllegalAccessException var2) {
         throw new InstantiationException(var2.getClass().getSimpleName() + ": " + var2.getMessage(), var2);
      } catch (java.lang.InstantiationException var3) {
         throw new InstantiationException(var3.getClass().getSimpleName() + ": " + var3.getMessage(), var3);
      } catch (ClassNotFoundException var4) {
         throw new InstantiationException(var4.getClass().getSimpleName() + ": " + var4.getMessage(), var4);
      }
   }
}
