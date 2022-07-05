package be.fgov.ehealth.dics.protocol.v3;

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
   name = "FindCompoundingFormulaRequestType",
   propOrder = {"findByAnyName", "findByOfficialName", "findByCNK"}
)
@XmlRootElement(
   name = "FindCompoundingFormulaRequest"
)
public class FindCompoundingFormulaRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByAnyName"
   )
   protected String findByAnyName;
   @XmlElement(
      name = "FindByOfficialName"
   )
   protected String findByOfficialName;
   @XmlElement(
      name = "FindByCNK"
   )
   protected String findByCNK;
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

   public FindCompoundingFormulaRequest() {
   }

   public String getFindByAnyName() {
      return this.findByAnyName;
   }

   public void setFindByAnyName(String value) {
      this.findByAnyName = value;
   }

   public String getFindByOfficialName() {
      return this.findByOfficialName;
   }

   public void setFindByOfficialName(String value) {
      this.findByOfficialName = value;
   }

   public String getFindByCNK() {
      return this.findByCNK;
   }

   public void setFindByCNK(String value) {
      this.findByCNK = value;
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
