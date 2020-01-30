package be.recipe.services.core;

import java.math.BigInteger;
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

   public String toString() {
      ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
      StringBuilder buffer = new StringBuilder();
      this.append((ObjectLocator)null, buffer, strategy);
      return buffer.toString();
   }

   public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      strategy.appendStart(locator, this, buffer);
      this.appendFields(locator, buffer, strategy);
      strategy.appendEnd(locator, this, buffer);
      return buffer;
   }

   public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
      int theYear = this.getMonth();
      strategy.appendField(locator, this, "month", buffer, theYear);
      theYear = this.getYear();
      strategy.appendField(locator, this, "year", buffer, theYear);
      BigInteger thePageNumber = this.getPageNumber();
      strategy.appendField(locator, this, "pageNumber", buffer, thePageNumber);
      byte[] theContext = this.getContext();
      strategy.appendField(locator, this, "context", buffer, theContext);
      return buffer;
   }

   public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof Page)) {
         return false;
      } else if (this == object) {
         return true;
      } else {
         Page that = (Page)object;
         int lhsYear = this.getMonth();
         int rhsYear = that.getMonth();
         if (!strategy.equals(LocatorUtils.property(thisLocator, "month", lhsYear), LocatorUtils.property(thatLocator, "month", rhsYear), lhsYear, rhsYear)) {
            return false;
         } else {
            lhsYear = this.getYear();
            rhsYear = that.getYear();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "year", lhsYear), LocatorUtils.property(thatLocator, "year", rhsYear), lhsYear, rhsYear)) {
               return false;
            } else {
               BigInteger lhsPageNumber = this.getPageNumber();
               BigInteger rhsPageNumber = that.getPageNumber();
               if (!strategy.equals(LocatorUtils.property(thisLocator, "pageNumber", lhsPageNumber), LocatorUtils.property(thatLocator, "pageNumber", rhsPageNumber), lhsPageNumber, rhsPageNumber)) {
                  return false;
               } else {
                  byte[] lhsContext = this.getContext();
                  byte[] rhsContext = that.getContext();
                  return strategy.equals(LocatorUtils.property(thisLocator, "context", lhsContext), LocatorUtils.property(thatLocator, "context", rhsContext), lhsContext, rhsContext);
               }
            }
         }
      }
   }

   public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return this.equals((ObjectLocator)null, (ObjectLocator)null, object, strategy);
   }

   public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;
      int theYear = this.getMonth();
      int currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "month", theYear), currentHashCode, theYear);
      theYear = this.getYear();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "year", theYear), currentHashCode, theYear);
      BigInteger thePageNumber = this.getPageNumber();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pageNumber", thePageNumber), currentHashCode, thePageNumber);
      byte[] theContext = this.getContext();
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "context", theContext), currentHashCode, theContext);
      return currentHashCode;
   }

   public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return this.hashCode((ObjectLocator)null, strategy);
   }
}
