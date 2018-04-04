package be.recipe.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "uploadFile",
   propOrder = {"uploadFileParam"}
)
@XmlRootElement(
   name = "uploadFile"
)
public class UploadFile implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "UploadFileParam"
   )
   protected byte[] uploadFileParam;

   public byte[] getUploadFileParam() {
      return this.uploadFileParam;
   }

   public void setUploadFileParam(byte[] value) {
      this.uploadFileParam = (byte[])value;
   }
}
