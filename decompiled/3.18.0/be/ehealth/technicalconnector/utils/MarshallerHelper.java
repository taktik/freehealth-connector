package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.impl.AttachmentMarshallerImpl;
import be.ehealth.technicalconnector.utils.impl.AttachmentUnmarshallerImpl;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.namespace.QName;
import javax.xml.soap.AttachmentPart;
import javax.xml.transform.stream.StreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class MarshallerHelper<X, Y> {
   private static final Logger LOG = LoggerFactory.getLogger(MarshallerHelper.class);
   public static final int DEFAULT_XOP_THRESHOLD = 10;
   private AttachmentMarshallerImpl attachmentMarshaller;
   private Map<String, AttachmentPart> attachmentParts = new HashMap();
   private Class<Y> marshallClass;
   private Class<X> unmarshallClass;
   private boolean format;
   private boolean xop;
   private int threshold;

   public MarshallerHelper(Class<X> unmarshallClass, Class<Y> marshallClass) {
      this.createMarshaller(unmarshallClass, marshallClass, false, false, 10);
   }

   public MarshallerHelper(Class<X> unmarshallClass, Class<Y> marshallClass, boolean format) {
      this.createMarshaller(unmarshallClass, marshallClass, format, false, 10);
   }

   public MarshallerHelper(Class<X> unmarshallClass, Class<Y> marshallClass, boolean format, boolean xop) {
      this.createMarshaller(unmarshallClass, marshallClass, format, xop, 10);
   }

   public MarshallerHelper(Class<X> unmarshallClass, Class<Y> marshallClass, boolean format, boolean xop, int threshold) {
      this.createMarshaller(unmarshallClass, marshallClass, format, xop, threshold);
   }

   public void addAttachmentPart(String id, AttachmentPart attachmentPart) {
      this.attachmentParts.put(id, attachmentPart);
   }

   public void clearAttachmentPartMap() {
      this.attachmentParts.clear();
   }

   private void createMarshaller(Class<X> inUnmarshallClass, Class<Y> inMarshallClass, Boolean format, Boolean xop, int threshold) {
      this.format = format;
      this.unmarshallClass = inUnmarshallClass;
      this.marshallClass = inMarshallClass;
      this.xop = xop;
      this.threshold = threshold;
   }

   public Map<String, DataHandler> getDataHandlersMap() {
      return this.attachmentMarshaller.getDataHandlerMap();
   }

   public Document toDocument(Y data) {
      try {
         Document doc = ConnectorXmlUtils.getDocumentBuilder().newDocument();
         if (data.getClass().isAnnotationPresent(XmlRootElement.class)) {
            this.getMarshaller().marshal(data, doc);
         } else {
            JAXBElement<Y> jaxbElement = new JAXBElement(translate(data.getClass()), this.marshallClass, data);
            this.getMarshaller().marshal(jaxbElement, doc);
         }

         return doc;
      } catch (JAXBException var4) {
         throw handleException(var4);
      }
   }

   public X toObject(byte[] data) {
      ByteArrayInputStream boas = null;

      Object var3;
      try {
         boas = new ByteArrayInputStream(data);
         var3 = this.toObject((InputStream)boas);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)boas);
      }

      return var3;
   }

   public X toObject(InputStream inputStream) {
      Object var3;
      try {
         JAXBElement<X> root = this.getUnMarshaller().unmarshal(new StreamSource(inputStream), this.unmarshallClass);
         var3 = root.getValue();
      } catch (JAXBException var7) {
         throw handleException(var7);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)inputStream);
      }

      return var3;
   }

   public X toObject(Node source) {
      try {
         return this.getUnMarshaller().unmarshal(source);
      } catch (JAXBException var5) {
         JAXBException e = var5;

         try {
            LOG.debug("Unable to unmarshall class from source.", e);
            return this.getUnMarshaller().unmarshal(source, this.unmarshallClass).getValue();
         } catch (JAXBException var4) {
            var4.setLinkedException(var5);
            throw handleException(var4);
         }
      }
   }

   public X toObject(String data) {
      try {
         return this.toObject(ConnectorIOUtils.toBytes(data, Charset.UTF_8));
      } catch (TechnicalConnectorException var3) {
         LOG.error(var3.getMessage(), var3);
         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public X toObjectNoRootElementRequired(byte[] data) {
      return this.toObject(data);
   }

   public String toString(Y data) {
      StringWriter writer = new StringWriter();

      try {
         if (!data.getClass().isAnnotationPresent(XmlRootElement.class) && !(data instanceof JAXBElement)) {
            JAXBElement<Y> jaxbElement = new JAXBElement(translate(data.getClass()), this.marshallClass, data);
            this.getMarshaller().marshal(jaxbElement, writer);
         } else {
            this.getMarshaller().marshal(data, writer);
         }
      } catch (JAXBException var4) {
         throw handleException(var4);
      }

      return writer.toString();
   }

   private static QName translate(Class<?> clazz) {
      Annotation[] arr$ = clazz.getPackage().getAnnotations();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Annotation annotation = arr$[i$];
         if (annotation instanceof XmlSchema) {
            XmlSchema schema = (XmlSchema)annotation;
            return new QName(schema.namespace(), clazz.getSimpleName());
         }
      }

      LOG.debug("Unable to determine QName for class:{} using package as namespace.", clazz);
      return new QName(clazz.getPackage().getName(), clazz.getSimpleName());
   }

   /** @deprecated */
   @Deprecated
   public String toStringNoRootElementRequired(Y data) {
      return this.toString(data);
   }

   public byte[] toXMLByteArray(Y data) {
      ByteArrayOutputStream bos = null;

      byte[] var9;
      try {
         bos = new ByteArrayOutputStream();
         if (data.getClass().isAnnotationPresent(XmlRootElement.class)) {
            this.getMarshaller().marshal(data, bos);
         } else {
            JAXBElement<Y> jaxbElement = new JAXBElement(translate(data.getClass()), this.marshallClass, data);
            this.getMarshaller().marshal(jaxbElement, bos);
         }

         var9 = bos.toByteArray();
      } catch (JAXBException var7) {
         throw handleException(var7);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)bos);
      }

      return var9;
   }

   /** @deprecated */
   @Deprecated
   public byte[] toXMLByteArrayNoRootElementRequired(Y data) {
      return this.toXMLByteArray(data);
   }

   /** @deprecated */
   @Deprecated
   public byte[] toXMLByteArrayNoRootElementRequired(Y data, QName rootTag) {
      return this.toXMLByteArrayNoRootElementRequired(data);
   }

   private static IllegalArgumentException handleException(JAXBException e) {
      throw new IllegalArgumentException("Unable to (un)marchall class. Reason: " + e, e);
   }

   private Marshaller getMarshaller() throws JAXBException {
      this.attachmentMarshaller = new AttachmentMarshallerImpl(this.xop, this.threshold);
      Marshaller marshaller = JaxbContextFactory.getJaxbContextForClass(this.marshallClass).createMarshaller();
      marshaller.setAttachmentMarshaller(this.attachmentMarshaller);
      marshaller.setProperty("jaxb.encoding", Charset.UTF_8.getName());
      marshaller.setProperty("jaxb.formatted.output", this.format);
      return marshaller;
   }

   private Unmarshaller getUnMarshaller() throws JAXBException {
      AttachmentUnmarshallerImpl attachmentUnmarshaller = new AttachmentUnmarshallerImpl(true);
      attachmentUnmarshaller.getAttachmentPartMap().putAll(this.attachmentParts);
      Unmarshaller unmarshaller = JaxbContextFactory.getJaxbContextForClass(this.unmarshallClass).createUnmarshaller();
      unmarshaller.setAttachmentUnmarshaller(attachmentUnmarshaller);
      return unmarshaller;
   }
}
