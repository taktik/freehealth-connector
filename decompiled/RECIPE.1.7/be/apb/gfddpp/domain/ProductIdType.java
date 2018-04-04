package be.apb.gfddpp.domain;

import be.apb.standards.smoa.schema.id.v1.AbstractMedicinalProductIdType;
import be.apb.standards.smoa.schema.id.v1.AtcDddSystemIdType;
import be.apb.standards.smoa.schema.id.v1.BeRegistrationIdType;
import be.apb.standards.smoa.schema.id.v1.CnkIdType;
import be.apb.standards.smoa.schema.id.v1.ObjectFactory;

public enum ProductIdType {
   ATC(AtcDddSystemIdType.class) {
      public AbstractMedicinalProductIdType createId(String id) {
         AtcDddSystemIdType atcDddSystemId = (new ObjectFactory()).createAtcDddSystemIdType();
         atcDddSystemId.setAtc(id);
         return atcDddSystemId;
      }

      public String getIdFrom(AbstractMedicinalProductIdType instance) {
         return ((AtcDddSystemIdType)instance).getAtc();
      }
   },
   CNK(CnkIdType.class) {
      public AbstractMedicinalProductIdType createId(String id) {
         CnkIdType cnkId = (new ObjectFactory()).createCnkIdType();
         cnkId.setCnk(id);
         return cnkId;
      }

      public String getIdFrom(AbstractMedicinalProductIdType instance) {
         return ((CnkIdType)instance).getCnk();
      }
   },
   BEREGISTRATIONID(BeRegistrationIdType.class) {
      public AbstractMedicinalProductIdType createId(String id) {
         BeRegistrationIdType beRegistrationIdType = (new ObjectFactory()).createBeRegistrationIdType();
         beRegistrationIdType.setCti(Integer.parseInt(id));
         return beRegistrationIdType;
      }

      public String getIdFrom(AbstractMedicinalProductIdType instance) {
         return String.valueOf(((BeRegistrationIdType)instance).getCti());
      }
   };

   private Class<? extends AbstractMedicinalProductIdType> type;

   public abstract AbstractMedicinalProductIdType createId(String var1);

   public abstract String getIdFrom(AbstractMedicinalProductIdType var1);

   private ProductIdType(Class<? extends AbstractMedicinalProductIdType> type) {
      this.type = type;
   }

   public Class<? extends AbstractMedicinalProductIdType> getType() {
      return this.type;
   }

   public static ProductIdType valueOf(Class<? extends AbstractMedicinalProductIdType> type) {
      ProductIdType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ProductIdType instance = arr$[i$];
         if (type.isAssignableFrom(instance.getType())) {
            return instance;
         }
      }

      throw new IllegalArgumentException("unsupported type");
   }

   public static <T extends AbstractMedicinalProductIdType> ProductIdType valueOf(T instance) {
      return valueOf(instance.getClass());
   }

   // $FF: synthetic method
   ProductIdType(Class x2, Object x3) {
      this(x2);
   }
}
