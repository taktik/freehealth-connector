package be.ehealth.technicalconnector.handler.wss4j;

import be.ehealth.technicalconnector.service.sts.security.Credential;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AlgorithmHelperFactory {
   private static List<AlgorithmHelper> helpers = new ArrayList();

   public AlgorithmHelperFactory() {
   }

   public static AlgorithmHelper getAlgorithmHelper(Credential credential) {
      Iterator var1 = helpers.iterator();

      AlgorithmHelper helper;
      do {
         if (!var1.hasNext()) {
            throw new IllegalArgumentException("Unsupported algorithm " + credential);
         }

         helper = (AlgorithmHelper)var1.next();
      } while(!helper.canHandle(credential));

      return helper;
   }

   static {
      helpers.add(new RSAAlgorithmHelper());
      helpers.add(new ECAlgorithmHelper());
   }
}
