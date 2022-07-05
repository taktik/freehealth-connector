package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MEDIATYPEvalues"
)
@XmlEnum
public enum CDMEDIATYPEvalues {
   @XmlEnumValue("application/msword")
   APPLICATION_MSWORD("application/msword"),
   @XmlEnumValue("application/pdf")
   APPLICATION_PDF("application/pdf"),
   @XmlEnumValue("audio/basic")
   AUDIO_BASIC("audio/basic"),
   @XmlEnumValue("audio/k32adpcm")
   AUDIO_K_32_ADPCM("audio/k32adpcm"),
   @XmlEnumValue("audio/mp3")
   AUDIO_MP_3("audio/mp3"),
   @XmlEnumValue("image/g3fax")
   IMAGE_G_3_FAX("image/g3fax"),
   @XmlEnumValue("image/gif")
   IMAGE_GIF("image/gif"),
   @XmlEnumValue("image/jpeg")
   IMAGE_JPEG("image/jpeg"),
   @XmlEnumValue("image/png")
   IMAGE_PNG("image/png"),
   @XmlEnumValue("image/tiff")
   IMAGE_TIFF("image/tiff"),
   @XmlEnumValue("model/vrml")
   MODEL_VRML("model/vrml"),
   @XmlEnumValue("multipart/x-hl7-cda-level1")
   MULTIPART_X_HL_7_CDA_LEVEL_1("multipart/x-hl7-cda-level1"),
   @XmlEnumValue("text/html")
   TEXT_HTML("text/html"),
   @XmlEnumValue("text/plain")
   TEXT_PLAIN("text/plain"),
   @XmlEnumValue("text/rtf")
   TEXT_RTF("text/rtf"),
   @XmlEnumValue("text/sgml")
   TEXT_SGML("text/sgml"),
   @XmlEnumValue("text/x-hl7-ft")
   TEXT_X_HL_7_FT("text/x-hl7-ft"),
   @XmlEnumValue("text/xml")
   TEXT_XML("text/xml"),
   @XmlEnumValue("video/mpeg")
   VIDEO_MPEG("video/mpeg"),
   @XmlEnumValue("video/x-avi")
   VIDEO_X_AVI("video/x-avi"),
   @XmlEnumValue("kmb/transaction")
   KMB_TRANSACTION("kmb/transaction"),
   @XmlEnumValue("image/dicom")
   IMAGE_DICOM("image/dicom"),
   @XmlEnumValue("application/hl7-sda+xml")
   APPLICATION_HL_7_SDA_XML("application/hl7-sda+xml");

   private final String value;

   private CDMEDIATYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMEDIATYPEvalues fromValue(String v) {
      CDMEDIATYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMEDIATYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
