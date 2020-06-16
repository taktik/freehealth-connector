package be.apb.standards.smoa.schema.v1;

import be.apb.standards.smoa.schema.model.v1.AbstractEntityType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EventFolderType",
   propOrder = {"events", "entitySpace"}
)
public class EventFolderType extends AbstractFolderType {
   protected EventFolderType.Events events;
   protected EventFolderType.EntitySpace entitySpace;

   public EventFolderType.Events getEvents() {
      return this.events;
   }

   public void setEvents(EventFolderType.Events value) {
      this.events = value;
   }

   public EventFolderType.EntitySpace getEntitySpace() {
      return this.entitySpace;
   }

   public void setEntitySpace(EventFolderType.EntitySpace value) {
      this.entitySpace = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"event"}
   )
   public static class Events {
      protected List<AbstractEventType> event;

      public List<AbstractEventType> getEvent() {
         if (this.event == null) {
            this.event = new ArrayList();
         }

         return this.event;
      }
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"entity"}
   )
   public static class EntitySpace {
      protected List<AbstractEntityType> entity;

      public List<AbstractEntityType> getEntity() {
         if (this.entity == null) {
            this.entity = new ArrayList();
         }

         return this.entity;
      }
   }
}
