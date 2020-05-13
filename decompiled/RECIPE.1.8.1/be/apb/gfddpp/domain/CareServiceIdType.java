package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractCareServiceIdType;
import be.apb.standards.smoa.schema.id.v1.CbeIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum CareServiceIdType {
   CBE(CbeIdType.class) {
      public AbstractCareServiceIdType createId(String id) {
         CbeIdType cbeId = (new ObjectFactory()).createCbeIdType();
         cbeId.setCbe(id);
         return cbeId;
      }

      public String getIdFrom(AbstractCareServiceIdType instance) {
         return ((CbeIdType)instance).getCbe();
      }
   };

   private Class<? extends AbstractCareServiceIdType> type;

   public abstract AbstractCareServiceIdType createId(String var1);

   public abstract String getIdFrom(AbstractCareServiceIdType var1);

   private CareServiceIdType(Class<? extends AbstractCareServiceIdType> type) {
      this.type = type;
   }

   public Class<? extends AbstractCareServiceIdType> getType() {
      return this.type;
   }

   public static CareServiceIdType valueOf(Class<? extends AbstractCareServiceIdType> type) {
      CareServiceIdType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CareServiceIdType instance = var1[var3];
         if (type.isAssignableFrom(instance.getType())) {
            return instance;
         }
      }

      throw new IllegalArgumentException("unsupported type");
   }

   // $FF: synthetic method
   CareServiceIdType(Class x2, Object x3) {
      this(x2);
   }
}
