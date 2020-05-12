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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Plot"
)
public class Plot {
   @XmlElement(
      name = "series"
   )
   private List<Serie> series;
   @XmlAttribute
   private String name;

   public Plot() {
      this.name = "";
      this.series = new ArrayList();
   }

   public Plot(String name) {
      this();
      this.name = name;
   }

   public Plot(List<Serie> series) {
      this.name = "";
      this.series = series;
   }

   public Plot(List<Serie> series, String name) {
      this.name = "";
      this.series = series;
      this.name = name;
   }

   public void addSerie(Serie serie) {
      this.series.add(serie);
   }

   public byte[] toXmlByteArray() throws IOException, JAXBException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      JAXBContext jaxbContext = JAXBContext.newInstance(new Class[]{Plot.class, Serie.class, Point.class});
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(this, bos);
      bos.close();
      return bos.toByteArray();
   }

   public List<Serie> getSeries() {
      return this.series;
   }

   public void setSeries(List<Serie> series) {
      this.series = series;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
