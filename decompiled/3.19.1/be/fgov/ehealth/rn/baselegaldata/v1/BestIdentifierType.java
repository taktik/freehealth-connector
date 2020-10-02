package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BestIdentifierType",
   propOrder = {"namespace", "objectIdentifier", "versionIdentifier"}
)
public class BestIdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Namespace"
   )
   protected String namespace;
   @XmlElement(
      name = "ObjectIdentifier"
   )
   protected String objectIdentifier;
   @XmlElement(
      name = "VersionIdentifier"
   )
   protected String versionIdentifier;

   public String getNamespace() {
      return this.namespace;
   }

   public void setNamespace(String value) {
      this.namespace = value;
   }

   public String getObjectIdentifier() {
      return this.objectIdentifier;
   }

   public void setObjectIdentifier(String value) {
      this.objectIdentifier = value;
   }

   public String getVersionIdentifier() {
      return this.versionIdentifier;
   }

   public void setVersionIdentifier(String value) {
      this.versionIdentifier = value;
   }
}
