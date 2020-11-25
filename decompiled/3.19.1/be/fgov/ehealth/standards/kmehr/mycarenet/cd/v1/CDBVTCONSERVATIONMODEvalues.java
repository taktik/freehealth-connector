package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-CONSERVATIONMODEvalues"
)
@XmlEnum
public enum CDBVTCONSERVATIONMODEvalues {
   @XmlEnumValue("minus20degreesc")
   MINUS_20_DEGREESC("minus20degreesc"),
   @XmlEnumValue("minus80degreesc")
   MINUS_80_DEGREESC("minus80degreesc"),
   @XmlEnumValue("minus120degreesorcolder")
   MINUS_120_DEGREESORCOLDER("minus120degreesorcolder"),
   @XmlEnumValue("liquidnitrogen")
   LIQUIDNITROGEN("liquidnitrogen"),
   @XmlEnumValue("inclusioninparaffin")
   INCLUSIONINPARAFFIN("inclusioninparaffin"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDBVTCONSERVATIONMODEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTCONSERVATIONMODEvalues fromValue(String v) {
      CDBVTCONSERVATIONMODEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDBVTCONSERVATIONMODEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
