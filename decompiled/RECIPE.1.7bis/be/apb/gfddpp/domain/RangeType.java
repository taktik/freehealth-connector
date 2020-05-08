package be.apb.gfddpp.domain;

import be.apb.gfddpp.productfilter.MedicinesRanges;
import be.apb.gfddpp.productfilter.PreparationsRanges;
import be.apb.gfddpp.productfilter.RangesType;

public enum RangeType {
   MEDICINE(MedicinesRanges.class) {
   },
   PREPARATION(PreparationsRanges.class) {
   };

   private Class<? extends RangesType> type;

   private RangeType(Class<? extends RangesType> type) {
      this.type = type;
   }

   public static RangeType lookupType(Class<? extends RangesType> type) {
      RangeType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         RangeType supported = var1[var3];
         if (type.isAssignableFrom(supported.getType())) {
            return supported;
         }
      }

      throw new IllegalArgumentException();
   }

   public Class<? extends RangesType> getType() {
      return this.type;
   }

   public static <T extends RangesType> RangeType valueOf(T instance) {
      return lookupType(instance.getClass());
   }

   // $FF: synthetic method
   RangeType(Class x2, Object x3) {
      this(x2);
   }
}
