package org.taktik.connector.technical.ws.feature;

public class XOPFeature extends GenericFeature {
   public static final String ID = "http://www.w3.org/2004/08/soap/features/http-optimization";
   private int threshold;

   public XOPFeature() {
      super(true);
      this.threshold = 10;
   }

   public XOPFeature(int threshold) {
      super(true);
      if (threshold < 0) {
         throw new IllegalArgumentException("threshold must be >= 0, actual value: " + threshold);
      } else {
         this.threshold = threshold;
      }
   }

   public int getThreshold() {
      return this.threshold;
   }

   public String getID() {
      return "http://www.w3.org/2004/08/soap/features/http-optimization";
   }
}
