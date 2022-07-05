package oasis.names.tc.saml._2_0.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IDPListType",
   propOrder = {"idpEntries", "getComplete"}
)
@XmlRootElement(
   name = "IDPList"
)
public class IDPList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "IDPEntry",
      required = true
   )
   protected List<IDPEntry> idpEntries;
   @XmlElement(
      name = "GetComplete"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String getComplete;

   public IDPList() {
   }

   public List<IDPEntry> getIDPEntries() {
      if (this.idpEntries == null) {
         this.idpEntries = new ArrayList();
      }

      return this.idpEntries;
   }

   public String getGetComplete() {
      return this.getComplete;
   }

   public void setGetComplete(String value) {
      this.getComplete = value;
   }
}
