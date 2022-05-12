package be.fgov.ehealth.schematron.utils;

import java.io.InputStream;
import javax.xml.transform.Source;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

public class XSLTURIFinder implements URIResolver {
   public XSLTURIFinder() {
   }

   public Source resolve(String href, String base) {
      InputStream is;
      try {
         if (href != null && href.length() != 0) {
            if (href.contains(":")) {
               is = XSLTURIFinder.class.getResourceAsStream(href);
            } else {
               is = XSLTURIFinder.class.getResourceAsStream("/" + href);
            }
         } else {
            int bang = base.indexOf("!");
            is = XSLTURIFinder.class.getResourceAsStream(base.substring(bang + 1));
         }
      } catch (Exception var5) {
         return null;
      }

      return new StreamSource(is);
   }
}
