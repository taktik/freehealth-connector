package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindVtmResponseType",
   propOrder = {"vtms"}
)
@XmlRootElement(
   name = "FindVtmResponse"
)
public class FindVtmResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Vtm"
   )
   protected List<ConsultVtmType> vtms;

   public FindVtmResponse() {
   }

   public List<ConsultVtmType> getVtms() {
      if (this.vtms == null) {
         this.vtms = new ArrayList();
      }

      return this.vtms;
   }
}
