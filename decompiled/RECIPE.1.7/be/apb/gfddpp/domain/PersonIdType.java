package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractPersonIdType;
import be.apb.standards.smoa.schema.id.v1.InssIdType;
import be.apb.standards.smoa.schema.id.v1.LocalIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum PersonIdType {
   INSS(InssIdType.class) {
      public AbstractPersonIdType createId(String id) {
         InssIdType inssId = (new ObjectFactory()).createInssIdType();
         inssId.setId(id);
         return inssId;
      }

      public String getIdFrom(AbstractPersonIdType instance) {
         return ((InssIdType)instance).getId();
      }
   },
   LOCAL(LocalIdType.class) {
      public AbstractPersonIdType createId(String id) {
         LocalIdType localId = (new ObjectFactory()).createLocalIdType();
         localId.setId(id);
         return localId;
      }

      public String getIdFrom(AbstractPersonIdType instance) {
         return ((LocalIdType)instance).getId();
      }
   };

   private Class<? extends AbstractPersonIdType> type;

   public abstract AbstractPersonIdType createId(String var1);

   public abstract String getIdFrom(AbstractPersonIdType var1);

   private PersonIdType(Class<? extends AbstractPersonIdType> type) {
      this.type = type;
   }

   public Class<? extends AbstractPersonIdType> getType() {
      return this.type;
   }

   public static PersonIdType valueOf(Class<? extends AbstractPersonIdType> type) {
      PersonIdType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         PersonIdType instance = arr$[i$];
         if (type.isAssignableFrom(instance.getType())) {
            return instance;
         }
      }

      throw new IllegalArgumentException("unsupported type");
   }

   public static <T extends AbstractPersonIdType> PersonIdType valueOf(T instance) {
      return valueOf(instance.getClass());
   }

   // $FF: synthetic method
   PersonIdType(Class x2, Object x3) {
      this(x2);
   }
}
