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
      Extractor[] arr$ = this.extractors;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Extractor extractor = arr$[i$];
         if (extractor.canExtract(keyinfo)) {
            return true;
         }
      }

      return false;
   }

   public List<X509Certificate> extract(KeyInfo keyinfo) throws XMLSecurityException {
      Extractor[] arr$ = this.extractors;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Extractor extractor = arr$[i$];
         if (extractor.canExtract(keyinfo)) {
            return extractor.extract(keyinfo);
         }
      }

      return new ArrayList();
   }
}
