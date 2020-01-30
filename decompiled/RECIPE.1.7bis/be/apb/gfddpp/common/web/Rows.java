package be.apb.gfddpp.common.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rows<T> {
   @XmlElement(
      name = "page"
   )
   private Integer page;
   @XmlElement(
      name = "total"
   )
   private Integer total;
   @XmlElement(
      name = "records"
   )
   private Integer records;
   @XmlElement(
      name = "row"
   )
   private List<T> rows;

   public Rows() {
   }

   public Rows(List<T> rows) {
      this.rows = rows;
      this.page = new Integer(0);
      this.total = new Integer(rows.size());
      this.records = new Integer(rows.size());
   }

   public Rows(int page, int total, int records, List<T> rows) {
      this.rows = rows;
      this.page = page;
      this.total = total;
      this.records = records;
   }

   public byte[] toXmlByteArray() throws IOException, JAXBException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      List<Class> classes = new ArrayList();
      classes.add(Rows.class);
      if (this.rows != null && !this.rows.isEmpty()) {
         classes.add(this.rows.get(0).getClass());
      }

      JAXBContext jaxbContext = JAXBContext.newInstance(this.toClasstArray(classes));
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(this, bos);
      bos.close();
      return bos.toByteArray();
   }

   private Class[] toClasstArray(List<Class> list) {
      Class[] ret = new Class[list.size()];
      int i = 0;

      Class e;
      for(Iterator i$ = list.iterator(); i$.hasNext(); ret[i++] = e) {
         e = (Class)i$.next();
      }

      return ret;
   }

   public Integer getPage() {
      return this.page;
   }

   public Integer getTotal() {
      return this.total;
   }

   public Integer getRecords() {
      return this.records;
   }

   public List<T> getRows() {
      return this.rows;
   }
}
