package be.fgov.ehealth.standards.kmehr.schema.v1;

import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;
import org.taktik.connector.technical.adapter.XmlTimeNoTzAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "contentlocalitemattributeType",
   propOrder = {"ids", "cds", "lnks", "person", "hcparty", "date", "time", "text", "_boolean", "decimal", "unit"}
)
public class ContentlocalitemattributeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id"
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      name = "cd"
   )
   protected List<CDCONTENT> cds;
   @XmlElement(
      name = "lnk"
   )
   protected List<Object> lnks;
   protected PersonType person;
   protected HcpartyType hcparty;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime date;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlTimeNoTzAdapter.class)
   @XmlSchemaType(
      name = "time"
   )
   protected DateTime time;
   protected TextType text;
   @XmlElement(
      name = "boolean"
   )
   protected Boolean _boolean;
   protected BigDecimal decimal;
   protected UnitType unit;

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDCONTENT> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public List<Object> getLnks() {
      if (this.lnks == null) {
         this.lnks = new ArrayList();
      }

      return this.lnks;
   }

   public PersonType getPerson() {
      return this.person;
   }

   public void setPerson(PersonType value) {
      this.person = value;
   }

   public HcpartyType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HcpartyType value) {
      this.hcparty = value;
   }

   public DateTime getDate() {
      return this.date;
   }

   public void setDate(DateTime value) {
      this.date = value;
   }

   public DateTime getTime() {
      return this.time;
   }

   public void setTime(DateTime value) {
      this.time = value;
   }

   public TextType getText() {
      return this.text;
   }

   public void setText(TextType value) {
      this.text = value;
   }

   public Boolean isBoolean() {
      return this._boolean;
   }

   public void setBoolean(Boolean value) {
      this._boolean = value;
   }

   public BigDecimal getDecimal() {
      return this.decimal;
   }

   public void setDecimal(BigDecimal value) {
      this.decimal = value;
   }

   public UnitType getUnit() {
      return this.unit;
   }

   public void setUnit(UnitType value) {
      this.unit = value;
   }
}
