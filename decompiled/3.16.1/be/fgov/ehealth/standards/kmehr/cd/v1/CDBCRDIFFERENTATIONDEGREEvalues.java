package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BCR-DIFFERENTATIONDEGREEvalues"
)
@XmlEnum
public enum CDBCRDIFFERENTATIONDEGREEvalues {
   @XmlEnumValue("welldifferentiated")
   WELLDIFFERENTIATED("welldifferentiated"),
   @XmlEnumValue("moderatelydifferentiated")
   MODERATELYDIFFERENTIATED("moderatelydifferentiated"),
   @XmlEnumValue("poorlydifferentiated")
   POORLYDIFFERENTIATED("poorlydifferentiated"),
   @XmlEnumValue("undifferentiatedanaplastic")
   UNDIFFERENTIATEDANAPLASTIC("undifferentiatedanaplastic"),
   @XmlEnumValue("tcell")
   TCELL("tcell"),
   @XmlEnumValue("bcell")
   BCELL("bcell"),
   @XmlEnumValue("nullcell")
   NULLCELL("nullcell"),
   @XmlEnumValue("nkcell")
   NKCELL("nkcell"),
   @XmlEnumValue("unknown")
   UNKNOWN("unknown");

   private final String value;

   private CDBCRDIFFERENTATIONDEGREEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBCRDIFFERENTATIONDEGREEvalues fromValue(String v) {
      CDBCRDIFFERENTATIONDEGREEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDBCRDIFFERENTATIONDEGREEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
