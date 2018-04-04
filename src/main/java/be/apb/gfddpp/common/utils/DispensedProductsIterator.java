package be.apb.gfddpp.common.utils;

import be.apb.standards.smoa.schema.model.v1.MaxSetProductType;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class DispensedProductsIterator implements Iterator<MaxSetProductType> {
   private List<List<? extends MaxSetProductType>> dispensedProductLists = new LinkedList();
   private int listIndex = 0;
   private int productIndex = -1;

   public DispensedProductsIterator(SingleMessageWrapper singleMessage) {
      List<PharmaceuticalCareEventType> allPharmaceuticalCareEvents = singleMessage.getAllEventsOfType(PharmaceuticalCareEventType.class);

      for (PharmaceuticalCareEventType careEvent : allPharmaceuticalCareEvents) {
         for (PharmaceuticalCareEventType.DispensedForSamePrescription productList : careEvent.getDispensedForSamePrescription()) {
            this.dispensedProductLists.add(productList.getProduct());
         }

         this.dispensedProductLists.add(careEvent.getDispensedWithoutPrescription().getProduct());
      }

   }

   public boolean hasNext() {
      return this.recursiveHasNextCheck(this.listIndex, this.productIndex);
   }

   public MaxSetProductType next() {
      if (this.dispensedProductLists.size() <= this.listIndex) {
         throw new NoSuchElementException();
      } else {
         ++this.productIndex;
         List<? extends MaxSetProductType> dispensedProductList = (List)this.dispensedProductLists.get(this.listIndex);
         if (dispensedProductList.size() > this.productIndex) {
            return (MaxSetProductType)dispensedProductList.get(this.productIndex);
         } else {
            ++this.listIndex;
            this.productIndex = -1;
            return this.next();
         }
      }
   }

   public void remove() {
      if (this.productIndex == -1) {
         throw new IllegalStateException();
      } else {
         ((List)this.dispensedProductLists.get(this.listIndex)).remove(this.productIndex);
         this.recursiveRemoveIndexUpdate();
      }
   }

   private boolean recursiveHasNextCheck(int tempListIndex, int tempProductIndex) {
      if (this.dispensedProductLists.size() <= tempListIndex) {
         return false;
      } else {
         List<? extends MaxSetProductType> dispensedProductList = (List)this.dispensedProductLists.get(tempListIndex);
         return dispensedProductList.size() <= tempProductIndex + 1 ? this.recursiveHasNextCheck(tempListIndex + 1, -1) : true;
      }
   }

   private void recursiveRemoveIndexUpdate() {
      --this.productIndex;
      if (this.productIndex == -1) {
         if (this.listIndex == 0) {
            return;
         }

         --this.listIndex;
         this.productIndex = ((List)this.dispensedProductLists.get(this.listIndex)).size();
         this.recursiveRemoveIndexUpdate();
      }

   }
}
