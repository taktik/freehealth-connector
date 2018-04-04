package be.fgov.ehealth.monitoring.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DetailsType",
   propOrder = {"content"}
)
public class DetailsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String content;
   @XmlAttribute(
      name = "Host",
      required = true
   )
   protected String host;
   @XmlAttribute(
      name = "Service",
      required = true
   )
   protected String service;
   @XmlAttribute(
      name = "Environment",
      required = true
   )
   protected EnvironmentType environment;
   @XmlAttribute(
      name = "Date",
      required = true
   )
   protected String date;
   @XmlAttribute(
      name = "Time",
      required = true
   )
   protected String time;
   @XmlAttribute(
      name = "Epochtime",
      required = true
   )
   protected String epochtime;
   @XmlAttribute(
      name = "Status",
      required = true
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   protected String status;
   @XmlAttribute(
      name = "StatusMessage",
      required = true
   )
   protected String statusMessage;
   @XmlAttribute(
      name = "PerfData"
   )
   protected String perfData;

   public String getContent() {
      return this.content;
   }

   public void setContent(String value) {
      this.content = value;
   }

   public String getHost() {
      return this.host;
   }

   public void setHost(String value) {
      this.host = value;
   }

   public String getService() {
      return this.service;
   }

   public void setService(String value) {
      this.service = value;
   }

   public EnvironmentType getEnvironment() {
      return this.environment;
   }

   public void setEnvironment(EnvironmentType value) {
      this.environment = value;
   }

   public String getDate() {
      return this.date;
   }

   public void setDate(String value) {
      this.date = value;
   }

   public String getTime() {
      return this.time;
   }

   public void setTime(String value) {
      this.time = value;
   }

   public String getEpochtime() {
      return this.epochtime;
   }

   public void setEpochtime(String value) {
      this.epochtime = value;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }

   public String getStatusMessage() {
      return this.statusMessage;
   }

   public void setStatusMessage(String value) {
      this.statusMessage = value;
   }

   public String getPerfData() {
      return this.perfData;
   }

   public void setPerfData(String value) {
      this.perfData = value;
   }
}
