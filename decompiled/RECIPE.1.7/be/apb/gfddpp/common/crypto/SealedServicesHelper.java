package be.apb.gfddpp.common.crypto;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class SealedServicesHelper<X> {
   private Unmarshaller unmarshaller;
   private Marshaller marshaller;
   private JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();

   public SealedServicesHelper(Class<X> theClass) throws GFDDPPException {
      this.unmarshaller = this.jaxContextCentralizer.getUnmarshaller(theClass);
      this.marshaller = this.jaxContextCentralizer.getMarshaller(theClass);
   }

   public byte[] unseal(byte[] data) {
      return data;
   }

   public String marsh(X data) throws JAXBException {
      StringWriter stringWriter = new StringWriter();
      this.marshaller.marshal(data, (Writer)stringWriter);
      return stringWriter.toString();
   }

   public X unmarshall(byte[] data) throws GFDDPPException {
      try {
         X object = this.unmarshaller.unmarshal((InputStream)(new ByteArrayInputStream(data)));
         return object;
      } catch (JAXBException var3) {
         throw new GFDDPPException(StatusCode.COMMON_ERROR_UNMARSHALL);
      }
   }

   public byte[] seal(byte[] data) {
      return data;
   }

   public byte[] marshall(X object) throws GFDDPPException {
      try {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         this.marshaller.marshal(object, (OutputStream)bos);
         bos.close();
         return bos.toByteArray();
      } catch (JAXBException var3) {
         throw new GFDDPPException(StatusCode.COMMON_ERROR_UNMARSHALL);
      } catch (IOException var4) {
         throw new GFDDPPException(StatusCode.COMMON_ERROR_INVALID_FORMAT);
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }
}
