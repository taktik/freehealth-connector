package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DicsResponseType"
)
@XmlSeeAlso({FindNonMedicinalProductResponse.class, FindAmppResponse.class, FindCompoundingFormulaResponse.class, FindCompoundingIngredientResponse.class, FindCommentedClassificationResponse.class, FindVtmResponse.class, FindVmpGroupResponse.class, FindReferencesResponse.class, FindReimbursementResponse.class, FindLegislationTextResponse.class, FindVmpResponse.class, FindCompanyResponse.class, FindListOfAmpResponse.class, FindAmpResponse.class})
public class DicsResponseType extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "SearchDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;
   @XmlAttribute(
      name = "SamId"
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String samId;

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }

   public String getSamId() {
      return this.samId;
   }

   public void setSamId(String value) {
      this.samId = value;
   }
}
