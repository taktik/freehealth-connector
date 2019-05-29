package be.fgov.ehealth.technicalconnector.signature.resolvers;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.utils.resolver.ResourceResolverContext;
import org.apache.xml.security.utils.resolver.ResourceResolverException;
import org.apache.xml.security.utils.resolver.ResourceResolverSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DocumentResolver extends ResourceResolverSpi {
   private static final Logger LOG = LoggerFactory.getLogger(DocumentResolver.class);
   private Document doc;

   public DocumentResolver(Document doc) {
      this.doc = doc;
   }

   public boolean engineCanResolveURI(ResourceResolverContext context) {
      String id = context.attr.getNodeValue();
      if (id.startsWith("#")) {
         id = id.substring(1);
      }

      LOG.debug("Searching deep for id [" + id + "]");
      if (this.hasAttribute(this.doc.getDocumentElement(), id)) {
         LOG.debug("Found id [" + id + "] deep in document");
         return true;
      } else {
         LOG.debug("Unable resolve attribute with id [" + id + "]");
         return false;
      }
   }

   public boolean engineIsThreadSafe() {
      return true;
   }

   public XMLSignatureInput engineResolveURI(ResourceResolverContext context) throws ResourceResolverException {
      String id = context.attr.getNodeValue();
      if (id.startsWith("#")) {
         id = id.substring(1);
      }

      if (LOG.isDebugEnabled()) {
         try {
            LOG.debug("Selected document: " + ConnectorXmlUtils.flatten(ConnectorXmlUtils.toString((Source)(new DOMSource(this.doc)))));
         } catch (TechnicalConnectorException var5) {
            LOG.error(var5.getMessage());
         }
      }

      Node selectedElem = this.doc.getElementById(id);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Try to catch an Element with ID " + id + " and Element was " + selectedElem);
      }

      this.processElement(context.attr, context.baseUri, selectedElem, id);
      XMLSignatureInput result = new XMLSignatureInput(selectedElem);
      result.setExcludeComments(true);
      result.setMIMEType("text/xml");
      result.setSourceURI(context.baseUri != null ? context.baseUri.concat(context.attr.getNodeValue()) : context.attr.getNodeValue());
      return result;
   }

   private void processElement(Attr uri, String baseURI, Node selectedElem, String id) throws ResourceResolverException {
      if (selectedElem == null) {
         Object[] exArgs = new Object[]{id};
         throw new ResourceResolverException("signature.Verification.MissingID", exArgs, uri.getBaseURI(), baseURI);
      }
   }

   private boolean hasAttribute(Element el, String attrValue) {
      NamedNodeMap attrMap = el.getAttributes();

      for(int i = 0; i < attrMap.getLength(); ++i) {
         Node item = attrMap.item(i);
         if (item.getTextContent().equalsIgnoreCase(attrValue)) {
            el.setIdAttribute(item.getLocalName(), true);
            return true;
         }
      }

      NodeList childs = el.getChildNodes();

      for(int i = 0; i < childs.getLength(); ++i) {
         Node child = childs.item(i);
         if (child.getNodeType() == 1 && this.hasAttribute((Element)childs.item(i), attrValue)) {
            return true;
         }
      }

      return false;
   }
}
