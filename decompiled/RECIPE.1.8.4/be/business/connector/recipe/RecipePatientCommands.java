package be.business.connector.recipe;

public enum RecipePatientCommands {
   PUT_RESERVATION("PutReservation"),
   PUT_VISION("PutVision"),
   GET_VISION("GetVision"),
   GET_PRESCRIPTION_STATUS("GetPrescriptionStatus"),
   LIST_PRESCRIPTION_HISTORY("ListPrescriptionHistory"),
   LIST_OPEN_PRESCRIPTIONS("ListOpenPrescriptions");

   private String command;

   private RecipePatientCommands(String command) {
      this.command = command;
   }
}
