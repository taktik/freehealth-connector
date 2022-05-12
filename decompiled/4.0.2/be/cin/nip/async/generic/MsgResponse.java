package be.cin.nip.async.generic;

import be.cin.mycarenet.esb.common.v2.CommonOutputType;
import be.cin.types.v1.Blob;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2005._05.xmlmime.Base64Binary;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MsgResponse",
   propOrder = {"commonOutput", "detail", "xadesT"}
)
public class MsgResponse implements Serializable {
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
   protected Blob detail;
   @XmlElement(
      name = "Xades-t",
      required = true
   )
   protected Base64Binary xadesT;

   public MsgResponse() {
   }

   public CommonOutputType getCommonOutput() {
      return this.commonOutput;
   }

   public void setCommonOutput(CommonOutputType value) {
      this.commonOutput = value;
   }

   public Blob getDetail() {
      return this.detail;
   }

   public void setDetail(Blob value) {
      this.detail = value;
   }

   public Base64Binary getXadesT() {
      return this.xadesT;
   }

   public void setXadesT(Base64Binary value) {
      this.xadesT = value;
   }
}
