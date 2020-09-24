package be.business.connector.recipe.domain;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/paging"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Page"
)
public class Page implements Serializable {
   private static final long serialVersionUID = 1L;
   @Min(2000L)
   @Max(2100L)
   private int year;
   @Min(1L)
   @Max(12L)
   private int month;

   public Page() {
   }

   public Page(int year, int month) {
      this.year = year;
      this.month = month;
   }

   public int getYear() {
      return this.year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public int getMonth() {
      return this.month;
   }

   public void setMonth(int month) {
      this.month = month;
   }
}
