package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.Key;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FileUtils;

public class MarshallerHelper<X, Y> {
   private Unmarshaller unmarshaller;
   private Marshaller marshaller;

   public MarshallerHelper(Class<X> unmarshallClass, Class<Y> marshallClass) {
      try {
         this.unmarshaller = JAXBContext.newInstance(unmarshallClass).createUnmarshaller();
         this.marshaller = JAXBContext.newInstance(marshallClass).createMarshaller();
         this.marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      } catch (JAXBException var4) {
         throw new IllegalArgumentException("JAXBException " + var4);
      }
   }

   public byte[] toXMLByteArray(Y data) {
      try {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         this.marshaller.marshal(data, bos);
         bos.close();
         MessageDumper.getInstance().dump(bos, data.getClass().getSimpleName(), "OUT");
         return bos.toByteArray();
      } catch (JAXBException var3) {
         throw new IllegalArgumentException("JAXBException " + var3);
      } catch (IOException var4) {
         throw new IllegalArgumentException("IOException " + var4);
      }
   }

   public X toObject(byte[] data) {
      try {
         ByteArrayInputStream bis = new ByteArrayInputStream(data);
         X result = this.unmarshaller.unmarshal(bis);
         bis.close();
         MessageDumper.getInstance().dump(data, result.getClass().getSimpleName(), "IN");
         return result;
      } catch (JAXBException var4) {
         throw new IllegalArgumentException("JAXBException " + var4);
      } catch (IOException var5) {
         throw new IllegalArgumentException("IOException " + var5);
      }
   }

   public X toObject(InputStream inputStream) {
      byte[] data = IOUtils.getBytes(inputStream);
      return this.toObject(data);
   }

   public String marsh(Y data) throws JAXBException {
      StringWriter stringWriter = new StringWriter();
      this.marshaller.marshal(data, stringWriter);
      return stringWriter.toString();
   }

   public X unmarsh(String data) throws JAXBException {
      return this.unmarshaller.unmarshal(new StringReader(data));
   }

   public X unmarsh(byte[] data) throws IntegrationModuleException {
      try {
         return this.unmarshaller.unmarshal(new ByteArrayInputStream(data));
      } catch (JAXBException var3) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.single.message.validation"), var3);
      }
   }

   public X unmarshSystemConfiguration(byte[] data) throws IntegrationModuleException {
      try {
         return this.unmarshaller.unmarshal(new ByteArrayInputStream(data));
      } catch (JAXBException var3) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.systemconfiguration.validation"), var3);
      }
   }

   public X unmarshProductFilter(byte[] data) throws IntegrationModuleException {
      try {
         return this.unmarshaller.unmarshal(new ByteArrayInputStream(data));
      } catch (JAXBException var3) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.productFilter.validation"), var3);
      }
   }

   public X unsealWithSymmKey(byte[] data, Key symmKey) {
      data = EncryptionUtils.unsealWithSymmKey(symmKey, data);
      return this.toObject(data);
   }

   public byte[] unsealWithKey(byte[] data, Key symmKey) {
      return EncryptionUtils.unsealWithSymmKey(symmKey, data);
   }

   public void wrtiePrescriptionToFile(byte[] unsealByteWithSymmKeyDecodeAndDecompress, String archivingPath) throws IOException {
      FileUtils.writeByteArrayToFile(new File(archivingPath), unsealByteWithSymmKeyDecodeAndDecompress);
   }
}
