package org.w3._2001._04.xmlenc_;

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
   propOrder = {"dataReferenceOrKeyReference"}
)
@XmlRootElement(
   name = "ReferenceList"
)
public class ReferenceList {
   @XmlElementRefs({@XmlElementRef(
   name = "KeyReference",
   namespace = "http://www.w3.org/2001/04/xmlenc#",
   type = JAXBElement.class,
   required = false
), @XmlElementRef(
   name = "DataReference",
   namespace = "http://www.w3.org/2001/04/xmlenc#",
   type = JAXBElement.class,
   required = false
)})
   protected List<JAXBElement<ReferenceType>> dataReferenceOrKeyReference;

   public List<JAXBElement<ReferenceType>> getDataReferenceOrKeyReference() {
      if (this.dataReferenceOrKeyReference == null) {
         this.dataReferenceOrKeyReference = new ArrayList();
      }

      return this.dataReferenceOrKeyReference;
   }
}
