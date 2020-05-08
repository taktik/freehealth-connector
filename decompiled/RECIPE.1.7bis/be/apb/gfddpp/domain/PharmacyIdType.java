package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractPharmacyIdType;
import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum PharmacyIdType {
   NIHII(NihiiIdType.class) {
      public AbstractPharmacyIdType createId(String id) {
         NihiiIdType nihiiId = (new ObjectFactory()).createNihiiIdType();
         nihiiId.setNihiiPharmacyNumber(id);
         return nihiiId;
      }

      public String getIdFrom(AbstractPharmacyIdType instance) {
         return ((NihiiIdType)instance).getNihiiPharmacyNumber();
      }
   };

   private Class<? extends AbstractPharmacyIdType> type;

   public abstract AbstractPharmacyIdType createId(String var1);

   public abstract String getIdFrom(AbstractPharmacyIdType var1);

   private PharmacyIdType(Class<? extends AbstractPharmacyIdType> type) {
      this.type = type;
   }

   public Class<? extends AbstractPharmacyIdType> getType() {
      return this.type;
   }

   public static PharmacyIdType valueOf(Class<? extends AbstractPharmacyIdType> type) {
      PharmacyIdType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         PharmacyIdType instance = var1[var3];
         if (type.isAssignableFrom(instance.getType())) {
            return instance;
         }
      }

      throw new IllegalArgumentException("unsupported type");
   }

   // $FF: synthetic method
   PharmacyIdType(Class x2, Object x3) {
      this(x2);
   }
}
