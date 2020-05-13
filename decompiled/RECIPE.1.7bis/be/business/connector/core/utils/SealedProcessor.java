package be.business.connector.core.utils;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

public class SealedProcessor {
   private static final Logger LOG = Logger.getLogger(SealedProcessor.class);
   private static JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();
   public static String Version = "1.7.0";
   public static String Build = "a";

   public static XMLGregorianCalendar getXMLGregorianCalendar(Date dateValue) {
      XMLGregorianCalendar dateTime = null;

      try {
         GregorianCalendar calendar = new GregorianCalendar();
         calendar.setTime(dateValue);
         DatatypeFactory df = DatatypeFactory.newInstance();
         dateTime = df.newXMLGregorianCalendar(calendar);
      } catch (DatatypeConfigurationException var4) {
      }

      return dateTime;
   }

   private static byte[] PackSealed(byte[] SealedData, String DataType, boolean Sync) throws UnsupportedEncodingException, IntegrationModuleException {
      JSONObject jo = new JSONObject();
      jo.put("DataType", DataType);
      jo.put("Sync", Sync);
      jo.put("Version", Version);
      jo.put("Build", Build);

      X509Certificate AuthCertificate;
      try {
         AuthCertificate = EncryptionUtils.getInstance().getCertificate();
         if (AuthCertificate != null) {
            jo.put("AuthCertificateSerialNumber", AuthCertificate.getSerialNumber().toString());
            jo.put("AuthCertificateNotAfter", getXMLGregorianCalendar(AuthCertificate.getNotAfter()).toString());
         }
      } catch (Exception var7) {
      }

      try {
         AuthCertificate = EncryptionUtils.getInstance().getOldCertificate();
         if (AuthCertificate != null) {
            jo.put("AuthCertificateOldSerialNumber", AuthCertificate.getSerialNumber().toString());
            jo.put("AuthCertificateOldNotAfter", getXMLGregorianCalendar(AuthCertificate.getNotAfter()).toString());
         }
      } catch (Exception var6) {
      }

      LOG.info("PackSealed:" + jo.toJSONString());
      jo.put("SealedData", Base64.encodeBase64String(SealedData));
      String d = jo.toJSONString();
      LOG.debug("PackSealed:" + DataType + ":" + d);
      byte[] s = Base64.encodeBase64(d.getBytes("UTF-8"));
      return s;
   }

   public static byte[] createSealedAsync(EncryptionToken etk, byte[] data, String type) throws IntegrationModuleException, UnsupportedEncodingException {
      byte[] sealed = Crypto.seal(etk, data);
      byte[] s = PackSealed(sealed, type, false);
      return s;
   }

   public static byte[] createSealedSync(EncryptionToken etk, byte[] data, String type) throws IntegrationModuleException, UnsupportedEncodingException {
      byte[] sealed = Crypto.seal(etk, data);
      byte[] s = PackSealed(sealed, type, true);
      return s;
   }

   public static byte[] createSealedSync(List<EncryptionToken> etks, byte[] data, String type) throws IntegrationModuleException, UnsupportedEncodingException {
      byte[] sealed = Crypto.seal(etks, data);
      byte[] s = PackSealed(sealed, type, true);
      return s;
   }

   public static byte[] createSealedAsync(EncryptionToken etk, String data, String type) throws IntegrationModuleException, UnsupportedEncodingException {
      return createSealedAsync(etk, data.getBytes("UTF-8"), type);
   }

   public static byte[] createSealedSync(EncryptionToken etk, String data, String type) throws IntegrationModuleException, UnsupportedEncodingException {
      return createSealedSync(etk, data.getBytes("UTF-8"), type);
   }

   public static byte[] createSealedSync(List<EncryptionToken> etks, String data, String type) throws IntegrationModuleException, UnsupportedEncodingException {
      return createSealedSync(etks, data.getBytes("UTF-8"), type);
   }
}
