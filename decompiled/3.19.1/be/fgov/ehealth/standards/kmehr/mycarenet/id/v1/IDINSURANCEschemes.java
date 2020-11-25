package be.fgov.ehealth.standards.kmehr.mycarenet.id.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ID-INSURANCEschemes"
)
@XmlEnum
public enum IDINSURANCEschemes {
   @XmlEnumValue("ID-INSURANCE")
   ID_INSURANCE("ID-INSURANCE"),
   LOCAL("LOCAL");

   private final String value;

   private IDINSURANCEschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static IDINSURANCEschemes fromValue(String v) {
      IDINSURANCEschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         IDINSURANCEschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
