package be.apb.gfddpp.motivation;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Motivations createMotivations() {
      return new Motivations();
   }

   public Motivation createMotivation() {
      return new Motivation();
   }
}
