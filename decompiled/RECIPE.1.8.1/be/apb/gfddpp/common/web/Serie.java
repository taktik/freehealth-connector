package be.apb.gfddpp.common.web;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Serie"
)
public class Serie {
   @XmlElement(
      name = "name"
   )
   private String name;
   @XmlElementWrapper(
      name = "data"
   )
   @XmlElement(
      name = "point"
   )
   private List<Point> points;

   public Serie() {
      this.points = new ArrayList();
   }

   public Serie(String name, List<Point> points) {
      this.name = name;
      this.points = points;
   }

   public Serie(String name) {
      this();
      this.name = name;
   }

   public void addPoint(Point point) {
      this.points.add(point);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Point> getPoints() {
      return this.points;
   }

   public void setPoints(List<Point> points) {
      this.points = points;
   }
}
