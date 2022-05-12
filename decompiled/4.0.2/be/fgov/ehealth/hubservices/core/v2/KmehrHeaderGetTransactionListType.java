package be.fgov.ehealth.hubservices.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "KmehrHeaderGetTransactionListType",
   propOrder = {"folder"}
)
public class KmehrHeaderGetTransactionListType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected FolderTypeUnbounded folder;

   public KmehrHeaderGetTransactionListType() {
   }

   public FolderTypeUnbounded getFolder() {
      return this.folder;
   }

   public void setFolder(FolderTypeUnbounded value) {
      this.folder = value;
   }
}
