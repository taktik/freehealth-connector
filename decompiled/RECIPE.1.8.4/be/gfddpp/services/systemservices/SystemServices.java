package be.gfddpp.services.systemservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"version", "systemServicesList"}
)
@XmlRootElement(
   name = "systemServices"
)
public class SystemServices {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar version;
   @XmlElement(
      required = true
   )
   protected SystemServices.SystemServicesList systemServicesList;

   public XMLGregorianCalendar getVersion() {
      return this.version;
   }

   public void setVersion(XMLGregorianCalendar value) {
      this.version = value;
   }

   public SystemServices.SystemServicesList getSystemServicesList() {
      return this.systemServicesList;
   }

   public void setSystemServicesList(SystemServices.SystemServicesList value) {
      this.systemServicesList = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"systemServicesEntry"}
   )
   public static class SystemServicesList {
      protected List<SystemServices.SystemServicesList.SystemServicesEntry> systemServicesEntry;

      public List<SystemServices.SystemServicesList.SystemServicesEntry> getSystemServicesEntry() {
         if (this.systemServicesEntry == null) {
            this.systemServicesEntry = new ArrayList();
         }

         return this.systemServicesEntry;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"system", "service"}
      )
      public static class SystemServicesEntry {
         @XmlElement(
            required = true
         )
         protected SystemServices.SystemServicesList.SystemServicesEntry.System system;
         @XmlElement(
            required = true
         )
         protected SystemServices.SystemServicesList.SystemServicesEntry.Service service;

         public SystemServices.SystemServicesList.SystemServicesEntry.System getSystem() {
            return this.system;
         }

         public void setSystem(SystemServices.SystemServicesList.SystemServicesEntry.System value) {
            this.system = value;
         }

         public SystemServices.SystemServicesList.SystemServicesEntry.Service getService() {
            return this.service;
         }

         public void setService(SystemServices.SystemServicesList.SystemServicesEntry.Service value) {
            this.service = value;
         }

         @XmlAccessorType(XmlAccessType.FIELD)
         @XmlType(
            name = "",
            propOrder = {"serviceName", "uri"}
         )
         public static class System {
            @XmlElement(
               required = true
            )
            protected String serviceName;
            @XmlElement(
               name = "URI",
               required = true
            )
            protected String uri;

            public String getServiceName() {
               return this.serviceName;
            }

            public void setServiceName(String value) {
               this.serviceName = value;
            }

            public String getURI() {
               return this.uri;
            }

            public void setURI(String value) {
               this.uri = value;
            }
         }

         @XmlAccessorType(XmlAccessType.FIELD)
         @XmlType(
            name = "",
            propOrder = {"systemIdType", "systemId"}
         )
         public static class Service {
            @XmlElement(
               required = true
            )
            protected String systemIdType;
            @XmlElement(
               required = true
            )
            protected String systemId;

            public String getSystemIdType() {
               return this.systemIdType;
            }

            public void setSystemIdType(String value) {
               this.systemIdType = value;
            }

            public String getSystemId() {
               return this.systemId;
            }

            public void setSystemId(String value) {
               this.systemId = value;
            }
         }
      }
   }
}
