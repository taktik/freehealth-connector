package be.fgov.ehealth.technicalconnector.services.daas;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.daas.complextype.v1.Actor;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.w3c.dom.Node;

public class AttributeValue {
   private Object value;

   public AttributeValue(Object value) {
      this.value = value;
   }

   public ValueType getValueType() {
      if (this.value instanceof String) {
         return AttributeValue.ValueType.STRING;
      } else if (this.value instanceof Node) {
         return AttributeValue.ValueType.NODE;
      } else {
         return this.value instanceof Actor ? AttributeValue.ValueType.ACTOR : AttributeValue.ValueType.UNKOWN_OBJECT;
      }
   }

   public String asString() throws TechnicalConnectorException {
      if (this.value instanceof String) {
         return (String)this.asObject(String.class);
      } else {
         return this.value instanceof Node ? ConnectorXmlUtils.toString((Node)this.value) : ConnectorXmlUtils.toString(this.value);
      }
   }

   public Node asNode() throws TechnicalConnectorException {
      if (this.value instanceof String) {
         return ConnectorXmlUtils.getDocumentBuilder().newDocument().createTextNode((String)this.value);
      } else {
         return (Node)(this.value instanceof Node ? (Node)this.asObject(Node.class) : ConnectorXmlUtils.toDocument(this.value));
      }
   }

   public Actor asActor() throws TechnicalConnectorException {
      return (Actor)this.asObject(Actor.class);
   }

   public <T> T asObject(Class<T> clazz) throws TechnicalConnectorException {
      Validate.notNull(clazz);
      if (this.value.getClass().isAssignableFrom(clazz)) {
         return this.value;
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"Unable to cast object to disered type [" + clazz.getName() + "]"});
      }
   }

   public Object getValue() {
      return this.value;
   }

   public String toString() {
      return ReflectionToStringBuilder.toString(this);
   }

   public static enum ValueType {
      STRING,
      NODE,
      ACTOR,
      UNKOWN_OBJECT;

      private ValueType() {
      }
   }
}
