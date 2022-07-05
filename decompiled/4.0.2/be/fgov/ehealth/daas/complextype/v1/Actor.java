package be.fgov.ehealth.daas.complextype.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ActorType",
   propOrder = {"ids", "names", "firstName", "middleName", "lastName", "period", "actor"}
)
@XmlRootElement(
   name = "Actor"
)
public class Actor implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Id",
      required = true
   )
   protected List<TypeCodeType> ids;
   @XmlElement(
      name = "Name"
   )
   protected List<Name> names;
   @XmlElement(
      name = "FirstName"
   )
   protected Object firstName;
   @XmlElement(
      name = "MiddleName"
   )
   protected Object middleName;
   @XmlElement(
      name = "LastName"
   )
   protected Object lastName;
   @XmlElement(
      name = "Period"
   )
   protected PeriodType period;
   @XmlElement(
      name = "Actor"
   )
   protected Actor actor;
   @XmlAttribute(
      name = "Type",
      required = true
   )
   protected String type;

   public Actor() {
   }

   public List<TypeCodeType> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<Name> getNames() {
      if (this.names == null) {
         this.names = new ArrayList();
      }

      return this.names;
   }

   public Object getFirstName() {
      return this.firstName;
   }

   public void setFirstName(Object value) {
      this.firstName = value;
   }

   public Object getMiddleName() {
      return this.middleName;
   }

   public void setMiddleName(Object value) {
      this.middleName = value;
   }

   public Object getLastName() {
      return this.lastName;
   }

   public void setLastName(Object value) {
      this.lastName = value;
   }

   public PeriodType getPeriod() {
      return this.period;
   }

   public void setPeriod(PeriodType value) {
      this.period = value;
   }

   public Actor getActor() {
      return this.actor;
   }

   public void setActor(Actor value) {
      this.actor = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }
}
