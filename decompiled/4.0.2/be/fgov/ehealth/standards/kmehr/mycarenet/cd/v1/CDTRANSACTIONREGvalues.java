package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TRANSACTION-REGvalues"
)
@XmlEnum
public enum CDTRANSACTIONREGvalues {
   @XmlEnumValue("qermid-stent-intervention")
   QERMID_STENT_INTERVENTION("qermid-stent-intervention"),
   @XmlEnumValue("orthopride-intervention")
   ORTHOPRIDE_INTERVENTION("orthopride-intervention"),
   @XmlEnumValue("defib-intervention")
   DEFIB_INTERVENTION("defib-intervention"),
   @XmlEnumValue("pacemaker-intervention")
   PACEMAKER_INTERVENTION("pacemaker-intervention"),
   @XmlEnumValue("arthritis-notification")
   ARTHRITIS_NOTIFICATION("arthritis-notification");

   private final String value;

   private CDTRANSACTIONREGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTRANSACTIONREGvalues fromValue(String v) {
      CDTRANSACTIONREGvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTRANSACTIONREGvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
