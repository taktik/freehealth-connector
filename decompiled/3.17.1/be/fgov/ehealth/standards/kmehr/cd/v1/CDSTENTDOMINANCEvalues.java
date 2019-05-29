package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-STENT-DOMINANCEvalues"
)
@XmlEnum
public enum CDSTENTDOMINANCEvalues {
   @XmlEnumValue("left")
   LEFT("left"),
   @XmlEnumValue("right")
   RIGHT("right");

   private final String value;

   private CDSTENTDOMINANCEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSTENTDOMINANCEvalues fromValue(String v) {
      CDSTENTDOMINANCEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDSTENTDOMINANCEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
