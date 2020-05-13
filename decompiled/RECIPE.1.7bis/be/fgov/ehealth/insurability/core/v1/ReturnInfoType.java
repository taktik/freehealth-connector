package be.fgov.ehealth.insurability.core.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReturnInfoType",
   propOrder = {"returnCode", "multipleIO"}
)
public class ReturnInfoType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReturnCode",
      required = true
   )
   protected ReturnCodeType returnCode;
   @XmlList
   @XmlElement(
      name = "MultipleIO"
   )
   protected List<String> multipleIO;

   public ReturnCodeType getReturnCode() {
      return this.returnCode;
   }

   public void setReturnCode(ReturnCodeType value) {
      this.returnCode = value;
   }

   public List<String> getMultipleIO() {
      if (this.multipleIO == null) {
         this.multipleIO = new ArrayList();
      }

      return this.multipleIO;
   }
}
