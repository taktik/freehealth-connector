package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "FaultCodeType"
)
@XmlEnum
public enum FaultCodeType {
   INPUT_ERROR,
   NO_IO_ERROR,
   MULTIIO_ERROR;

   private FaultCodeType() {
   }

   public String value() {
      return this.name();
   }

   public static FaultCodeType fromValue(String v) {
      return valueOf(v);
   }
}
