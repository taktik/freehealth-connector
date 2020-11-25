package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-LNKvalues"
)
@XmlEnum
public enum CDLNKvalues {
   @XmlEnumValue("isachildof")
   ISACHILDOF("isachildof"),
   @XmlEnumValue("isaconsequenceof")
   ISACONSEQUENCEOF("isaconsequenceof"),
   @XmlEnumValue("isanewversionof")
   ISANEWVERSIONOF("isanewversionof"),
   @XmlEnumValue("isareplyto")
   ISAREPLYTO("isareplyto"),
   @XmlEnumValue("multimedia")
   MULTIMEDIA("multimedia"),
   @XmlEnumValue("thumbnail")
   THUMBNAIL("thumbnail"),
   @XmlEnumValue("isanappendixof")
   ISANAPPENDIXOF("isanappendixof"),
   @XmlEnumValue("isaservicefor")
   ISASERVICEFOR("isaservicefor"),
   @XmlEnumValue("isrealisationof")
   ISREALISATIONOF("isrealisationof"),
   @XmlEnumValue("isapproachfor")
   ISAPPROACHFOR("isapproachfor"),
   @XmlEnumValue("isplannedfor")
   ISPLANNEDFOR("isplannedfor"),
   @XmlEnumValue("isattestationof")
   ISATTESTATIONOF("isattestationof");

   private final String value;

   private CDLNKvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDLNKvalues fromValue(String v) {
      CDLNKvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDLNKvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
