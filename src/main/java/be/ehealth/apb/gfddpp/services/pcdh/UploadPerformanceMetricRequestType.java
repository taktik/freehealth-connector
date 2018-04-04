package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UploadPerformanceMetricRequestType",
   propOrder = {"uploadPerformanceMetricParamSealed"}
)
public class UploadPerformanceMetricRequestType extends RequestType {
   @XmlElement(
      name = "UploadPerformanceMetricParamSealed",
      required = true
   )
   protected byte[] uploadPerformanceMetricParamSealed;

   public byte[] getUploadPerformanceMetricParamSealed() {
      return this.uploadPerformanceMetricParamSealed;
   }

   public void setUploadPerformanceMetricParamSealed(byte[] var1) {
      this.uploadPerformanceMetricParamSealed = (byte[])var1;
   }
}
