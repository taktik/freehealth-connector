package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.v1.OxygenTherapies;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MaxSetPatient",
   propOrder = {"identification", "contact", "entourage", "careProviders", "physiology"}
)
public class MaxSetPatient extends AbstractPatientType {
   @XmlElement(
      required = true
   )
   protected MaxSetPersonType identification;
   protected Contact contact;
   protected Entourage entourage;
   protected CareProviders careProviders;
   protected Physiology physiology;

   public MaxSetPersonType getIdentification() {
      return this.identification;
   }

   public void setIdentification(MaxSetPersonType value) {
      this.identification = value;
   }

   public Contact getContact() {
      return this.contact;
   }

   public void setContact(Contact value) {
      this.contact = value;
   }

   public Entourage getEntourage() {
      return this.entourage;
   }

   public void setEntourage(Entourage value) {
      this.entourage = value;
   }

   public CareProviders getCareProviders() {
      return this.careProviders;
   }

   public void setCareProviders(CareProviders value) {
      this.careProviders = value;
   }

   public Physiology getPhysiology() {
      return this.physiology;
   }

   public void setPhysiology(Physiology value) {
      this.physiology = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"condition", "chronicPathologies"}
   )
   public static class Physiology {
      protected Condition condition;
      protected ChronicPathologies chronicPathologies;

      public Condition getCondition() {
         return this.condition;
      }

      public void setCondition(Condition value) {
         this.condition = value;
      }

      public ChronicPathologies getChronicPathologies() {
         return this.chronicPathologies;
      }

      public void setChronicPathologies(ChronicPathologies value) {
         this.chronicPathologies = value;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"female"}
      )
      public static class Condition {
         protected Female female;

         public Female getFemale() {
            return this.female;
         }

         public void setFemale(Female value) {
            this.female = value;
         }

         @XmlAccessorType(XmlAccessType.FIELD)
         @XmlType(
            name = "",
            propOrder = {"pregnant", "nursing"}
         )
         public static class Female {
            protected Pregnant pregnant;
            protected Boolean nursing;

            public Pregnant getPregnant() {
               return this.pregnant;
            }

            public void setPregnant(Pregnant value) {
               this.pregnant = value;
            }

            public Boolean isNursing() {
               return this.nursing;
            }

            public void setNursing(Boolean value) {
               this.nursing = value;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(
               name = ""
            )
            public static class Pregnant {
               @XmlAttribute(
                  name = "deliveryDate"
               )
               @XmlSchemaType(
                  name = "date"
               )
               protected XMLGregorianCalendar deliveryDate;

               public XMLGregorianCalendar getDeliveryDate() {
                  return this.deliveryDate;
               }

               public void setDeliveryDate(XMLGregorianCalendar value) {
                  this.deliveryDate = value;
               }
            }
         }
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"disease", "allergy", "homeOxygenTherapy"}
      )
      public static class ChronicPathologies {
         protected List<GenericExternalReference> disease;
         protected List<GenericExternalReference> allergy;
         protected List<HomeOxygenTherapy> homeOxygenTherapy;

         public List<GenericExternalReference> getDisease() {
            if (this.disease == null) {
               this.disease = new ArrayList();
            }

            return this.disease;
         }

         public List<GenericExternalReference> getAllergy() {
            if (this.allergy == null) {
               this.allergy = new ArrayList();
            }

            return this.allergy;
         }

         public List<HomeOxygenTherapy> getHomeOxygenTherapy() {
            if (this.homeOxygenTherapy == null) {
               this.homeOxygenTherapy = new ArrayList();
            }

            return this.homeOxygenTherapy;
         }

         @XmlAccessorType(XmlAccessType.FIELD)
         @XmlType(
            name = "",
            propOrder = {"type", "start", "end"}
         )
         public static class HomeOxygenTherapy {
            @XmlElement(
               required = true
            )
            @XmlSchemaType(
               name = "token"
            )
            protected OxygenTherapies type;
            @XmlElement(
               required = true
            )
            @XmlSchemaType(
               name = "date"
            )
            protected XMLGregorianCalendar start;
            @XmlSchemaType(
               name = "date"
            )
            protected XMLGregorianCalendar end;

            public OxygenTherapies getType() {
               return this.type;
            }

            public void setType(OxygenTherapies value) {
               this.type = value;
            }

            public XMLGregorianCalendar getStart() {
               return this.start;
            }

            public void setStart(XMLGregorianCalendar value) {
               this.start = value;
            }

            public XMLGregorianCalendar getEnd() {
               return this.end;
            }

            public void setEnd(XMLGregorianCalendar value) {
               this.end = value;
            }
         }
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"contactPerson"}
   )
   public static class Entourage {
      @XmlElement(
         required = true
      )
      protected List<MaxSetContactPersonType> contactPerson;

      public List<MaxSetContactPersonType> getContactPerson() {
         if (this.contactPerson == null) {
            this.contactPerson = new ArrayList();
         }

         return this.contactPerson;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"address", "telecom"}
   )
   public static class Contact {
      protected List<AbstractAddressType> address;
      protected List<AbstractTelecomType> telecom;

      public List<AbstractAddressType> getAddress() {
         if (this.address == null) {
            this.address = new ArrayList();
         }

         return this.address;
      }

      public List<AbstractTelecomType> getTelecom() {
         if (this.telecom == null) {
            this.telecom = new ArrayList();
         }

         return this.telecom;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"hcparty"}
   )
   public static class CareProviders {
      @XmlElement(
         required = true
      )
      protected List<MaxSetCareProviderType> hcparty;

      public List<MaxSetCareProviderType> getHcparty() {
         if (this.hcparty == null) {
            this.hcparty = new ArrayList();
         }

         return this.hcparty;
      }
   }
}
