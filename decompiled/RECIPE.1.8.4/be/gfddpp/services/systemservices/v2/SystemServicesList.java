package be.gfddpp.services.systemservices.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "systemServicesList",
   propOrder = {"systemServicesEntry"}
)
public class SystemServicesList {
   protected List<SystemServicesEntry> systemServicesEntry;

   public List<SystemServicesEntry> getSystemServicesEntry() {
      if (this.systemServicesEntry == null) {
         this.systemServicesEntry = new ArrayList();
      }

      return this.systemServicesEntry;
   }
}
