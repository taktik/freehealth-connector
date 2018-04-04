package be.gfddpp.services.systemservices.v1;

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
   protected SystemServicesList systemServicesList;

   public XMLGregorianCalendar getVersion() {
      return this.version;
   }

   public void setVersion(XMLGregorianCalendar value) {
      this.version = value;
   }

   public SystemServicesList getSystemServicesList() {
      return this.systemServicesList;
   }

   public void setSystemServicesList(SystemServicesList value) {
      this.systemServicesList = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"systemServicesEntry"}
   )
   public static class SystemServicesList {
      protected List<SystemServicesEntry> systemServicesEntry;

      public List<SystemServicesEntry> getSystemServicesEntry() {
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
         protected System system;
         @XmlElement(
            required = true
         )
         protected List<Service> service;

         public System getSystem() {
            return this.system;
         }

         public void setSystem(System value) {
            this.system = value;
         }

         public List<Service> getService() {
            if (this.service == null) {
               this.service = new ArrayList();
            }

            return this.service;
         }

         @XmlAccessorType(XmlAccessType.FIELD)
         @XmlType(
            name = "",
            propOrder = {"systemType", "systemId"}
         )
         public static class System {
            @XmlElement(
               required = true
            )
            protected String systemType;
            @XmlElement(
               required = true
            )
            protected String systemId;

            public String getSystemType() {
               return this.systemType;
            }

            public void setSystemType(String value) {
               this.systemType = value;
            }

            public String getSystemId() {
               return this.systemId;
            }

            public void setSystemId(String value) {
               this.systemId = value;
            }
         }

         @XmlAccessorType(XmlAccessType.FIELD)
         @XmlType(
            name = "",
            propOrder = {"serviceName", "uri"}
         )
         public static class Service {
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
      }
   }
}
