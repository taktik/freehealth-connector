package be.recipe.services.core;

import java.math.BigInteger;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Page",
   propOrder = {"month", "year", "pageNumber", "context"}
)
public class Page {
   protected int month;
   protected int year;
   @XmlElement(
      required = true
   )
   protected BigInteger pageNumber;
   @XmlElement(
      required = true
   )
   protected byte[] context;

   public int getMonth() {
      return this.month;
   }

   public void setMonth(int value) {
      this.month = value;
   }

   public int getYear() {
      return this.year;
   }

   public void setYear(int value) {
      this.year = value;
   }

   public BigInteger getPageNumber() {
      return this.pageNumber;
   }

   public void setPageNumber(BigInteger value) {
      this.pageNumber = value;
   }

   public byte[] getContext() {
      return this.context;
   }

   public void setContext(byte[] value) {
      this.context = value;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Page)) return false;
      Page page = (Page) o;
      return month == page.month &&
              year == page.year &&
              Objects.equals(pageNumber, page.pageNumber);
   }

   @Override
   public int hashCode() {
      return Objects.hash(month, year, pageNumber);
   }
}
