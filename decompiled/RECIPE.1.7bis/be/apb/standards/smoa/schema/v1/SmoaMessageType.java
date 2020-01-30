package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SmoaMessageType",
   propOrder = {"header", "abstractFolder"}
)
public class SmoaMessageType {
   @XmlElement(
      required = true
   )
   protected HeaderType header;
   @XmlElementRef(
      name = "abstract-Folder",
      namespace = "http://www.apb.be/standards/smoa/schema/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractFolderType> abstractFolder;

   public HeaderType getHeader() {
      return this.header;
   }

   public void setHeader(HeaderType value) {
      this.header = value;
   }

   public JAXBElement<? extends AbstractFolderType> getAbstractFolder() {
      return this.abstractFolder;
   }

   public void setAbstractFolder(JAXBElement<? extends AbstractFolderType> value) {
      this.abstractFolder = value;
   }
}
