package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"dataReferencesAndKeyReferences"}
)
@XmlRootElement(
   name = "ReferenceList"
)
public class ReferenceList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElementRefs({@XmlElementRef(
   name = "KeyReference",
   namespace = "http://www.w3.org/2001/04/xmlenc#",
   type = JAXBElement.class
), @XmlElementRef(
   name = "DataReference",
   namespace = "http://www.w3.org/2001/04/xmlenc#",
   type = JAXBElement.class
)})
   protected List<JAXBElement<ReferenceType>> dataReferencesAndKeyReferences;

   public List<JAXBElement<ReferenceType>> getDataReferencesAndKeyReferences() {
      if (this.dataReferencesAndKeyReferences == null) {
         this.dataReferencesAndKeyReferences = new ArrayList();
      }

      return this.dataReferencesAndKeyReferences;
   }
}
