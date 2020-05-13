package be.apb.gfddpp.common.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Point"
)
public class Point {
   @XmlElement(
      name = "x"
   )
   private String x;
   @XmlElement(
      name = "y"
   )
   private String y;
   @XmlElement(
      name = "z"
   )
   private String z;

   public Point() {
   }

   public Point(String x, String y, String z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public String getX() {
      return this.x;
   }

   public void setX(String x) {
      this.x = x;
   }

   public String getY() {
      return this.y;
   }

   public void setY(String y) {
      this.y = y;
   }

   public String getZ() {
      return this.z;
   }

   public void setZ(String z) {
      this.z = z;
   }
}
