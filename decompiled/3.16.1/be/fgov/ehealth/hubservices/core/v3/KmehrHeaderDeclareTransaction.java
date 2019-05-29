package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KmehrHeaderDeclareTransactionType",
   propOrder = {"folder"}
)
@XmlRootElement(
   name = "KmehrHeaderDeclareTransaction"
)
public class KmehrHeaderDeclareTransaction implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected FolderType folder;

   public FolderType getFolder() {
      return this.folder;
   }

   public void setFolder(FolderType value) {
      this.folder = value;
   }
}
