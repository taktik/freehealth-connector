package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KmehrHeaderDeclareTransactionType",
   propOrder = {"folder"}
)
public class KmehrHeaderDeclareTransactionType implements Serializable {
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
