package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "RequestTypeType"
)
@XmlEnum
public enum RequestTypeType {
   @XmlEnumValue("new")
   NEW("new"),
   @XmlEnumValue("extention")
   EXTENTION("extention"),
   @XmlEnumValue("modification")
   MODIFICATION("modification");

   private final String value;

   private RequestTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static RequestTypeType fromValue(String v) {
      RequestTypeType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         RequestTypeType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
