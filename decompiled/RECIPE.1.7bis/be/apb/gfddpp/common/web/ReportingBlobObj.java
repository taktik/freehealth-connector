package be.apb.gfddpp.common.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReportingBlobObj"
)
public class ReportingBlobObj {
   private String name;
   private List<Object[]> members = new ArrayList();

   public ReportingBlobObj() {
   }

   public ReportingBlobObj(List<Object[]> members, String name) {
      this.members = members;
      this.name = name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Object[]> getMembers() {
      return this.members;
   }

   public void setMembers(List<Object[]> members) {
      this.members = members;
   }

   public byte[] toXmlByteArray(List<Object[]> data) throws IOException, JAXBException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      JAXBContext jaxbContext = JAXBContext.newInstance(ReportingBlobObj.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(this, bos);
      bos.close();
      return bos.toByteArray();
   }
}
