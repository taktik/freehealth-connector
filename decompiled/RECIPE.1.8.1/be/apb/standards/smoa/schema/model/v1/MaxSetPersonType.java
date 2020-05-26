package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPersonIdType;
import be.apb.standards.smoa.schema.v1.CDSEX;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MaxSetPersonType",
   propOrder = {"personId", "name", "familyName", "sex", "birthDate", "usuallanguage"}
)
public class MaxSetPersonType extends AbstractPersonType {
   @XmlElement(
      required = true
   )
   protected AbstractPersonIdType personId;
   @XmlElement(
      required = true
   )
   protected List<String> name;
   @XmlElement(
      required = true
   )
   protected String familyName;
   @XmlSchemaType(
      name = "token"
   )
   protected CDSEX sex;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar birthDate;
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "language"
   )
   protected String usuallanguage;

   public AbstractPersonIdType getPersonId() {
      return this.personId;
   }

   public void setPersonId(AbstractPersonIdType value) {
      this.personId = value;
   }

   public List<String> getName() {
      if (this.name == null) {
         this.name = new ArrayList();
      }

      return this.name;
   }

   public String getFamilyName() {
      return this.familyName;
   }

   public void setFamilyName(String value) {
      this.familyName = value;
   }

   public CDSEX getSex() {
      return this.sex;
   }

   public void setSex(CDSEX value) {
      this.sex = value;
   }

   public XMLGregorianCalendar getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(XMLGregorianCalendar value) {
      this.birthDate = value;
   }

   public String getUsuallanguage() {
      return this.usuallanguage;
   }

   public void setUsuallanguage(String value) {
      this.usuallanguage = value;
   }
}
