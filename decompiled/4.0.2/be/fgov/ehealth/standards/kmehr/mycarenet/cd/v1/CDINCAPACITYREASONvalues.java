package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-INCAPACITYREASONvalues"
)
@XmlEnum
public enum CDINCAPACITYREASONvalues {
   @XmlEnumValue("sickness")
   SICKNESS("sickness"),
   @XmlEnumValue("accident")
   ACCIDENT("accident"),
   @XmlEnumValue("family")
   FAMILY("family"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("careencounter")
   CAREENCOUNTER("careencounter"),
   @XmlEnumValue("illness")
   ILLNESS("illness"),
   @XmlEnumValue("hospitalisation")
   HOSPITALISATION("hospitalisation"),
   @XmlEnumValue("pregnancy")
   PREGNANCY("pregnancy"),
   @XmlEnumValue("workaccident")
   WORKACCIDENT("workaccident"),
   @XmlEnumValue("occupationaldisease")
   OCCUPATIONALDISEASE("occupationaldisease"),
   @XmlEnumValue("traveltofromworkaccident")
   TRAVELTOFROMWORKACCIDENT("traveltofromworkaccident");

   private final String value;

   private CDINCAPACITYREASONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDINCAPACITYREASONvalues fromValue(String v) {
      CDINCAPACITYREASONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDINCAPACITYREASONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
