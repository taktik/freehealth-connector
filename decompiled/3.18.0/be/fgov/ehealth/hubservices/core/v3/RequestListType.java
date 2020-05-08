package be.fgov.ehealth.hubservices.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RequestListType",
   propOrder = {"paginationinfo"}
)
public class RequestListType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected Paginationrequestinfo paginationinfo;

   public Paginationrequestinfo getPaginationinfo() {
      return this.paginationinfo;
   }

   public void setPaginationinfo(Paginationrequestinfo value) {
      this.paginationinfo = value;
   }
}
