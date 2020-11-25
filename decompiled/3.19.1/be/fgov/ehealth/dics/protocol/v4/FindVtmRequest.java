package be.fgov.ehealth.dics.protocol.v4;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindVtmRequestType",
   propOrder = {"findByProduct", "findByTherapeuticMoiety"}
)
@XmlRootElement(
   name = "FindVtmRequest"
)
public class FindVtmRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByProduct"
   )
   protected FindByVirtualProductType findByProduct;
   @XmlElement(
      name = "FindByTherapeuticMoiety"
   )
   protected FindByTherapeuticMoietyType findByTherapeuticMoiety;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;
   @XmlAttribute(
      name = "SearchDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

   public FindByVirtualProductType getFindByProduct() {
      return this.findByProduct;
   }

   public void setFindByProduct(FindByVirtualProductType value) {
      this.findByProduct = value;
   }

   public FindByTherapeuticMoietyType getFindByTherapeuticMoiety() {
      return this.findByTherapeuticMoiety;
   }

   public void setFindByTherapeuticMoiety(FindByTherapeuticMoietyType value) {
      this.findByTherapeuticMoiety = value;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
