package be.fgov.ehealth.mycarenet.commons.protocol.v2;

import be.fgov.ehealth.mycarenet.commons.core.v2.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v2.CommonOutputType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2005._05.xmlmime.Base64Binary;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResponseReturnType",
   propOrder = {"commonOutput", "detail", "xadesT"}
)
public class ResponseReturnType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonOutput",
      required = true
   )
   protected CommonOutputType commonOutput;
   @XmlElement(
      name = "Detail",
      required = true
   )
   protected BlobType detail;
   @XmlElement(
      name = "XadesT"
   )
   protected Base64Binary xadesT;

   public ResponseReturnType() {
   }

   public CommonOutputType getCommonOutput() {
      return this.commonOutput;
   }

   public void setCommonOutput(CommonOutputType value) {
      this.commonOutput = value;
   }

   public BlobType getDetail() {
      return this.detail;
   }

   public void setDetail(BlobType value) {
      this.detail = value;
   }

   public Base64Binary getXadesT() {
      return this.xadesT;
   }

   public void setXadesT(Base64Binary value) {
      this.xadesT = value;
   }
}
