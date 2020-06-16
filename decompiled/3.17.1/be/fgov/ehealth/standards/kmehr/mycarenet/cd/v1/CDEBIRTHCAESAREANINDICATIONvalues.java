package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-CAESAREANINDICATIONvalues"
)
@XmlEnum
public enum CDEBIRTHCAESAREANINDICATIONvalues {
   @XmlEnumValue("previouscaesareansection")
   PREVIOUSCAESAREANSECTION("previouscaesareansection"),
   @XmlEnumValue("breechpresentation")
   BREECHPRESENTATION("breechpresentation"),
   @XmlEnumValue("transversepresentation")
   TRANSVERSEPRESENTATION("transversepresentation"),
   @XmlEnumValue("foetaldistress")
   FOETALDISTRESS("foetaldistress"),
   @XmlEnumValue("dystocienotinlabour")
   DYSTOCIENOTINLABOUR("dystocienotinlabour"),
   @XmlEnumValue("dystocieinlabourinsufficientdilatation")
   DYSTOCIEINLABOURINSUFFICIENTDILATATION("dystocieinlabourinsufficientdilatation"),
   @XmlEnumValue("dystocieinlabourinsufficientexpulsion")
   DYSTOCIEINLABOURINSUFFICIENTEXPULSION("dystocieinlabourinsufficientexpulsion"),
   @XmlEnumValue("maternalindication")
   MATERNALINDICATION("maternalindication"),
   @XmlEnumValue("abruptioplacentae")
   ABRUPTIOPLACENTAE("abruptioplacentae"),
   @XmlEnumValue("requestedbypatient")
   REQUESTEDBYPATIENT("requestedbypatient"),
   @XmlEnumValue("multiplepregnancy")
   MULTIPLEPREGNANCY("multiplepregnancy"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDEBIRTHCAESAREANINDICATIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHCAESAREANINDICATIONvalues fromValue(String v) {
      CDEBIRTHCAESAREANINDICATIONvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEBIRTHCAESAREANINDICATIONvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
