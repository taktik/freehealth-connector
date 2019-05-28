package be.fgov.ehealth.technicalconnector.signature.impl.extractor;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;

public class X509DataExctractor implements Extractor {
   public boolean canExtract(KeyInfo keyinfo) {
      return keyinfo.containsX509Data();
   }

   public List<X509Certificate> extract(KeyInfo keyInfo) throws XMLSecurityException {
      List<X509Certificate> result = new ArrayList();

      for(int i = 0; i < keyInfo.lengthX509Data(); ++i) {
         X509Data data = keyInfo.itemX509Data(i);

         for(int j = 0; j < data.lengthCertificate(); ++j) {
            result.add(data.itemCertificate(j).getX509Certificate());
         }
      }

      return result;
   }
}
