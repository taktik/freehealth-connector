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
      PharmacyIdType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         PharmacyIdType instance = arr$[i$];
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
