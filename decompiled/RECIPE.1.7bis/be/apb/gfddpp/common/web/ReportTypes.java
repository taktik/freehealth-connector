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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReportTypes"
)
public class ReportTypes {
   @XmlElement(
      name = "ReportType"
   )
   private List<String> reportTypes;

   public ReportTypes() {
      this.reportTypes = new ArrayList();
   }

   public ReportTypes(List<String> reportTypes) {
      this();
      this.reportTypes = reportTypes;
   }

   public List<String> getReportTypes() {
      return this.reportTypes;
   }

   public byte[] toXmlByteArray() throws IOException, JAXBException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      JAXBContext jaxbContext = JAXBContext.newInstance(ReportTypes.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(this, bos);
      bos.close();
      return bos.toByteArray();
   }
}
