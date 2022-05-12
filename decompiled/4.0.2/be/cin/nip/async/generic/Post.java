package be.cin.nip.async.generic;

import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.types.v1.Blob;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2005._05.xmlmime.Base64Binary;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"commonInput", "detail", "xadesT"}
)
@XmlRootElement(
   name = "post"
)
public class Post implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonInput",
      required = true
   )
   protected CommonInput commonInput;
   @XmlElement(
      name = "Detail",
      required = true
   )
   protected Blob detail;
   @XmlElement(
      name = "Xades-t"
   )
   protected Base64Binary xadesT;

   public Post() {
   }

   public CommonInput getCommonInput() {
      return this.commonInput;
   }

   public void setCommonInput(CommonInput value) {
      this.commonInput = value;
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
