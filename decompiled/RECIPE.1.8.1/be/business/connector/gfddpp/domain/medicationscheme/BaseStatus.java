package be.business.connector.gfddpp.domain.medicationscheme;

import java.io.Serializable;

public class BaseStatus implements Serializable {
   private static final long serialVersionUID = -8424512521259291178L;
   private int code;
   private String message;

   public BaseStatus() {
   }

   public BaseStatus(int code) {
      this.code = code;
   }

   public BaseStatus(int code, String message) {
      this(code);
      this.message = message;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Base Status: ");
      sb.append(this.code);
      if (this.message != null && !this.message.equalsIgnoreCase("")) {
         sb.append(" : ");
         sb.append(this.message);
      }

      return sb.toString();
   }
}
