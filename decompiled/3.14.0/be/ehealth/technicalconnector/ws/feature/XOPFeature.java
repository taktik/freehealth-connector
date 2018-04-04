package be.ehealth.technicalconnector.ws.feature;

import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.handler.Handler;

public class XOPFeature extends GenericFeature {
   public static final String ID = "http://www.w3.org/2004/08/soap/features/http-optimization";
   private int threshold;

   public XOPFeature() {
      this.enabled = true;
      this.threshold = 10;
   }

   public XOPFeature(int threshold) {
      if (threshold < 0) {
         throw new IllegalArgumentException("threshold must be >= 0, actual value: " + threshold);
      } else {
         this.enabled = true;
         this.threshold = threshold;
      }
   }

   public int getThreshold() {
      return this.threshold;
   }

   public String getID() {
      return "http://www.w3.org/2004/08/soap/features/http-optimization";
   }

   public List<Handler<?>> getHandlers() {
      return new ArrayList();
   }
}
