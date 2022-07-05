package be.fgov.ehealth.technicalconnector.signature.impl.extractor;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;

public class ForkedExtractor implements Extractor {
   private Extractor[] extractors;

   public ForkedExtractor(Extractor... extractors) {
      this.extractors = extractors;
   }

   public boolean canExtract(KeyInfo keyinfo) {
      Extractor[] var2 = this.extractors;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Extractor extractor = var2[var4];
         if (extractor.canExtract(keyinfo)) {
            return true;
         }
      }

      return false;
   }

   public List<X509Certificate> extract(KeyInfo keyinfo) throws XMLSecurityException {
      Extractor[] var2 = this.extractors;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Extractor extractor = var2[var4];
         if (extractor.canExtract(keyinfo)) {
            return extractor.extract(keyinfo);
         }
      }

      return new ArrayList();
   }
}
