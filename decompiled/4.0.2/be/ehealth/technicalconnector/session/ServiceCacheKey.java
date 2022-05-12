package be.ehealth.technicalconnector.session;

import java.util.Arrays;

public class ServiceCacheKey {
   private Class<?> clazz;
   private String[] additionalParameters;

   public ServiceCacheKey(Class<?> clazz, String[] additionalParameters) {
      this.clazz = clazz;
      this.additionalParameters = (String[])additionalParameters.clone();
   }

   public int hashCode() {
      int prime = true;
      int result = 1;
      result = 31 * result + Arrays.hashCode(this.additionalParameters);
      result = 31 * result + (this.clazz == null ? 0 : this.clazz.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         ServiceCacheKey other = (ServiceCacheKey)obj;
         if (!Arrays.equals(this.additionalParameters, other.additionalParameters)) {
            return false;
         } else {
            if (this.clazz == null) {
               if (other.clazz != null) {
                  return false;
               }
            } else if (!this.clazz.equals(other.clazz)) {
               return false;
            }

            return true;
         }
      }
   }
}
