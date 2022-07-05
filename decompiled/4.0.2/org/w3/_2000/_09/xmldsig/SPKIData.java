package org.w3._2000._09.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SPKIDataType",
   propOrder = {"spkiSexpsAndAnies"}
)
@XmlRootElement(
   name = "SPKIData"
)
public class SPKIData implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRef(
      name = "SPKISexp",
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      type = JAXBElement.class
   )
   @XmlAnyElement
   protected List<java.lang.Object> spkiSexpsAndAnies;

   public SPKIData() {
   }

   public List<java.lang.Object> getSPKISexpsAndAnies() {
      if (this.spkiSexpsAndAnies == null) {
         this.spkiSexpsAndAnies = new ArrayList();
      }

      return this.spkiSexpsAndAnies;
   }
}
