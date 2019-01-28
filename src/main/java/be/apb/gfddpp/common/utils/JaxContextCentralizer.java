package be.apb.gfddpp.common.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JaxContextCentralizer {
   private static final Logger LOG = LogManager.getLogger(JaxContextCentralizer.class);
   private static JaxContextCentralizer instance;
   private Map<Class<?>, JAXBContext> contextStore;
   private XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

   private JaxContextCentralizer() {
      if (this.contextStore == null) {
         this.contextStore = new HashMap();

         try {
            this.getContext(SingleMessage.class);
         } catch (GFDDPPException var2) {
            LOG.warn("Can not cache singleMessage JaxbContext", var2);
         }
      }

   }

   public static JaxContextCentralizer getInstance() {
      if (instance == null) {
         instance = new JaxContextCentralizer();
      }

      return instance;
   }

   public synchronized void addContext(Class<?> clazz) {
      try {
         this.getContext(clazz);
      } catch (GFDDPPException var3) {
         LOG.warn("Jaxb context not cached : " + clazz, var3);
      }

   }

   public final JAXBContext getContext(Class<?> clazz) throws GFDDPPException {
      if (!this.contextStore.containsKey(clazz)) {
         try {
            this.contextStore.put(clazz, JAXBContext.newInstance(clazz));
         } catch (JAXBException var4) {
            String message = this.processJAXBException(var4);
            throw new GFDDPPException(StatusCode.COMMON_ERROR_JAXCONTEXT, new String[]{message, clazz.getName()});
         }

         LOG.info("Jaxbcontext for " + clazz + " cached");
      }

      return (JAXBContext)this.contextStore.get(clazz);
   }

   public Unmarshaller getUnmarshaller(Class<?> clazz) throws GFDDPPException {
      try {
         return this.getContext(clazz).createUnmarshaller();
      } catch (JAXBException var4) {
         LOG.error("", var4);
         String message = this.processJAXBException(var4);
         throw new GFDDPPException(StatusCode.COMMON_ERROR_UNMARSHALLER, new String[]{message, clazz.getName()});
      }
   }

   public Marshaller getMarshaller(Class<?> clazz) throws GFDDPPException {
      try {
         return this.getContext(clazz).createMarshaller();
      } catch (JAXBException var4) {
         LOG.error("", var4);
         String message = this.processJAXBException(var4);
         throw new GFDDPPException(StatusCode.COMMON_ERROR_MARSHALLER, new String[]{message, clazz.getName()});
      }
   }

   public <X> X toObject(Class<X> clazz, String data) throws GFDDPPException {
      try {
         return this.toObject(clazz, data.getBytes("UTF-8"));
      } catch (UnsupportedEncodingException var4) {
         throw new GFDDPPException(StatusCode.COMMON_ERROR_INVALID_FORMAT);
      }
   }

   public <X> X toObject(Class<X> clazz, byte[] data) throws GFDDPPException {
      try {
         ByteArrayInputStream bis = new ByteArrayInputStream(data);
         Object result;
         if (clazz.getAnnotation(XmlRootElement.class) != null) {
            result = this.getUnmarshaller(clazz).unmarshal((InputStream)bis);
         } else {
            try {
               JAXBElement<X> jax = this.getUnmarshaller(clazz).unmarshal(this.xmlInputFactory.createXMLStreamReader((InputStream)bis, (String)"UTF-8"), clazz);
               result = jax.getValue();
            } catch (XMLStreamException var6) {
               LOG.error("Incorrect xml : " + var6);
               throw new GFDDPPException(StatusCode.COMMON_ERROR_UNMARSHALL, new String[]{var6.getMessage(), clazz.getName()});
            }
         }

         return (X) result;
      } catch (JAXBException var7) {
         LOG.error("", var7);
         String message = this.processJAXBException(var7);
         throw new GFDDPPException(StatusCode.COMMON_ERROR_UNMARSHALL, new String[]{message, clazz.getName()});
      }
   }

   private String processJAXBException(JAXBException e) {
      String message = null;
      if (e.getLinkedException() != null) {
         message = e.getLinkedException().getMessage();
      } else {
         message = e.getLocalizedMessage();
      }

      return message;
   }

   public String toXml(Class<?> clazz, Object obj) throws GFDDPPException {
      StringWriter sw = new StringWriter();
      Marshaller marshaller = this.getMarshaller(clazz);

      try {
         marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
         if (clazz.getAnnotation(XmlRootElement.class) != null) {
            marshaller.marshal(obj, (Writer)sw);
         } else {
            JAXB.marshal(obj, (Writer)sw);
         }
      } catch (JAXBException var7) {
         LOG.error("", var7);
         String message = this.processJAXBException(var7);
         throw new GFDDPPException(StatusCode.COMMON_ERROR_MARSHAL, new String[]{message, clazz.getName()});
      }

      return sw.toString();
   }
}
