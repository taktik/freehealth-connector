package be.apb.gfddpp.common.productfilter;

import be.apb.gfddpp.productfilter.Medicine;
import be.apb.gfddpp.productfilter.Preparation;
import be.apb.gfddpp.productfilter.Range;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListWrapper {
   private HashMap<String, Medicine> whiteListMedicine;
   private HashMap<String, Medicine> blackListMedicine;
   private List<Range> medicineRanges = new ArrayList();
   private HashMap<String, Preparation> whiteListPreparation;
   private HashMap<String, Preparation> blackListPreparation;
   private List<Range> preparationRanges = new ArrayList();
   private String precedence = "black";

   public ListWrapper(HashMap<String, Medicine> whiteListMedicine, HashMap<String, Medicine> blackListMedicine, List<Range> medicineRanges, HashMap<String, Preparation> whiteListPreparation, HashMap<String, Preparation> blackListPreparation, List<Range> preparationRanges, String precedence) {
      this.whiteListMedicine = whiteListMedicine;
      this.blackListMedicine = blackListMedicine;
      this.medicineRanges = medicineRanges;
      this.whiteListPreparation = whiteListPreparation;
      this.blackListPreparation = blackListPreparation;
      this.preparationRanges = preparationRanges;
      this.precedence = precedence;
   }

   public HashMap<String, Medicine> getWhiteListMedicine() {
      return this.whiteListMedicine;
   }

   public HashMap<String, Medicine> getBlackListMedicine() {
      return this.blackListMedicine;
   }

   public void setPrecedence(String precedence) {
      this.precedence = precedence;
   }

   public HashMap<String, Preparation> getBlackListPreparation() {
      return this.blackListPreparation;
   }

   public HashMap<String, Preparation> getWhiteListPreparation() {
      return this.whiteListPreparation;
   }

   public String getPrecedence() {
      return this.precedence;
   }

   public List<Range> getMedicineRanges() {
      return this.medicineRanges;
   }

   public void setMedicineRanges(List<Range> medicineRanges) {
      this.medicineRanges = medicineRanges;
   }

   public List<Range> getPreparationRanges() {
      return this.preparationRanges;
   }

   public void setPreparationRanges(List<Range> preparationRanges) {
      this.preparationRanges = preparationRanges;
   }
}
