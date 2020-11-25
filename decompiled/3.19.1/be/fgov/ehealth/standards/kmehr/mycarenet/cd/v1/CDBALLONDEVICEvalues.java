package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BALLON-DEVICEvalues"
)
@XmlEnum
public enum CDBALLONDEVICEvalues {
   @XmlEnumValue("ballon")
   BALLON("ballon"),
   @XmlEnumValue("deb")
   DEB("deb");

   private final String value;

   private CDBALLONDEVICEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBALLONDEVICEvalues fromValue(String v) {
      CDBALLONDEVICEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDBALLONDEVICEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
