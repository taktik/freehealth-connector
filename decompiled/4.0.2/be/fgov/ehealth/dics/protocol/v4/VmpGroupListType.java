package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VmpGroupListType",
   propOrder = {"datas"}
)
public class VmpGroupListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Data",
      required = true
   )
   protected List<VmpGroupListDataType> datas;
   @XmlAttribute(
      name = "Code",
      required = true
   )
   protected String code;

   public VmpGroupListType() {
   }

   public List<VmpGroupListDataType> getDatas() {
      if (this.datas == null) {
         this.datas = new ArrayList();
      }

      return this.datas;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
