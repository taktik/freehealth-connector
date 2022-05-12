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
   name = "AmppListType",
   propOrder = {"datas"}
)
public class AmppListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Data",
      required = true
   )
   protected List<AmppListDataType> datas;
   @XmlAttribute(
      name = "CtiExtended",
      required = true
   )
   protected String ctiExtended;

   public AmppListType() {
   }

   public List<AmppListDataType> getDatas() {
      if (this.datas == null) {
         this.datas = new ArrayList();
      }

      return this.datas;
   }

   public String getCtiExtended() {
      return this.ctiExtended;
   }

   public void setCtiExtended(String value) {
      this.ctiExtended = value;
   }
}
