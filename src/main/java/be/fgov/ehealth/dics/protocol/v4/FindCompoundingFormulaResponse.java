package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
   name = "FindCompoundingFormulaResponseType",
   propOrder = {"compoundingFormulas"}
)
@XmlRootElement(
   name = "FindCompoundingFormulaResponse"
)
public class FindCompoundingFormulaResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CompoundingFormula"
   )
   protected List<ConsultCompoundingFormulaType> compoundingFormulas;
   @XmlAttribute(
      name = "SearchDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

   public List<ConsultCompoundingFormulaType> getCompoundingFormulas() {
      if (this.compoundingFormulas == null) {
         this.compoundingFormulas = new ArrayList();
      }

      return this.compoundingFormulas;
   }

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
