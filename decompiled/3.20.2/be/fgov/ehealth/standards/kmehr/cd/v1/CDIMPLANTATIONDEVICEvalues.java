package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-IMPLANTATION-DEVICEvalues"
)
@XmlEnum
public enum CDIMPLANTATIONDEVICEvalues {
   @XmlEnumValue("spacer")
   SPACER("spacer"),
   @XmlEnumValue("hipprosthesis")
   HIPPROSTHESIS("hipprosthesis"),
   @XmlEnumValue("kneeprosthesis")
   KNEEPROSTHESIS("kneeprosthesis");

   private final String value;

   private CDIMPLANTATIONDEVICEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDIMPLANTATIONDEVICEvalues fromValue(String v) {
      CDIMPLANTATIONDEVICEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDIMPLANTATIONDEVICEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
