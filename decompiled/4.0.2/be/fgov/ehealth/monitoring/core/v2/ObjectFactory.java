package be.fgov.ehealth.monitoring.core.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public MonitoringXML createMonitoringXML() {
      return new MonitoringXML();
   }

   public MonitoringType createMonitoringType() {
      return new MonitoringType();
   }

   public SchemaType createSchemaType() {
      return new SchemaType();
   }

   public ResultsType createResultsType() {
      return new ResultsType();
   }

   public SubResultsType createSubResultsType() {
      return new SubResultsType();
   }

   public DetailsType createDetailsType() {
      return new DetailsType();
   }

   public ExtensionType createExtensionType() {
      return new ExtensionType();
   }

   public ElementType createElementType() {
      return new ElementType();
   }
}
