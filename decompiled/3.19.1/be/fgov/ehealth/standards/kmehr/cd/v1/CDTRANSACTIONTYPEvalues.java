package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TRANSACTION-TYPEvalues"
)
@XmlEnum
public enum CDTRANSACTIONTYPEvalues {
   @XmlEnumValue("incapacity")
   INCAPACITY("incapacity"),
   @XmlEnumValue("incapacityextension")
   INCAPACITYEXTENSION("incapacityextension"),
   @XmlEnumValue("incapacityrelapse")
   INCAPACITYRELAPSE("incapacityrelapse"),
   @XmlEnumValue("nursing")
   NURSING("nursing"),
   @XmlEnumValue("physiotherapy")
   PHYSIOTHERAPY("physiotherapy"),
   @XmlEnumValue("intermediarynursing")
   INTERMEDIARYNURSING("intermediarynursing"),
   @XmlEnumValue("intermediaryphysiotherapy")
   INTERMEDIARYPHYSIOTHERAPY("intermediaryphysiotherapy"),
   @XmlEnumValue("transferdocument")
   TRANSFERDOCUMENT("transferdocument"),
   @XmlEnumValue("childrecord")
   CHILDRECORD("childrecord");

   private final String value;

   private CDTRANSACTIONTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTRANSACTIONTYPEvalues fromValue(String v) {
      CDTRANSACTIONTYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTRANSACTIONTYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
