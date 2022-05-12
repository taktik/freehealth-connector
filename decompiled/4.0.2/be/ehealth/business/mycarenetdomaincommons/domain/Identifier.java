package be.ehealth.business.mycarenetdomaincommons.domain;

import java.io.Serializable;

public class Identifier implements Serializable {
   private String Type;
   private String Value;

   public Identifier(String type, String value) {
      this.Type = type;
      this.Value = value;
   }

   public String getType() {
      return this.Type;
   }

   public void setType(String type) {
      this.Type = type;
   }

   public String getValue() {
      return this.Value;
   }

   public void setValue(String value) {
      this.Value = value;
   }
}
