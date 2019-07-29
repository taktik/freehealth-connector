package be.ehealth.businessconnector.genericasync.domain;

import java.util.ArrayList;
import java.util.List;

public class GetRequest {
   private String reference;
   private Integer maxMessages;
   private Integer maxTAcks;
   private List<String> includeIOs;
   private List<String> excludeIOs;
   private List<String> tackMessageNames;

   private GetRequest(GetRequest.Builder.BasicSteps builder) {
      this.reference = builder.reference;
      this.maxMessages = builder.maxMessages;
      this.maxTAcks = builder.maxTAcks;
      this.includeIOs = builder.includeIOs;
      this.excludeIOs = builder.excludeIOs;
      this.tackMessageNames = builder.tackMessageNames;
   }

   public static GetRequest.Builder.GetRequestBuilderStep newBuilder() {
      return new GetRequest.Builder.GetRequestSteps();
   }

   public String getReference() {
      return this.reference;
   }

   public Integer getMaxMessages() {
      return this.maxMessages;
   }

   public Integer getMaxTAcks() {
      return this.maxTAcks;
   }

   public List<String> getIncludeIOs() {
      return this.includeIOs;
   }

   public List<String> getExcludeIOs() {
      return this.excludeIOs;
   }

   public List<String> getTackMessageNames() {
      return this.tackMessageNames;
   }

   // $FF: synthetic method
   GetRequest(GetRequest.Builder.BasicSteps x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private static class BasicSteps implements GetRequest.Builder.DefaultsStep, GetRequest.Builder.ReferenceStep, GetRequest.Builder.MaxMessagesStep, GetRequest.Builder.MaxTAcksStep, GetRequest.Builder.IncludeIOsStep, GetRequest.Builder.ExcludeIOsStep, GetRequest.Builder.TackMessageNamesStep, GetRequest.Builder.BuildStep {
         private Integer maxMessages;
         private Integer maxTAcks;
         private String reference;
         private List<String> includeIOs;
         private List<String> excludeIOs;
         private List<String> tackMessageNames;

         private BasicSteps() {
            this.includeIOs = new ArrayList();
            this.excludeIOs = new ArrayList();
         }

         public GetRequest.Builder.BuildStep withDefaults() {
            return this;
         }

         public GetRequest.Builder.BuildStep withReference(String reference) {
            this.reference = reference;
            return this;
         }

         public GetRequest.Builder.MaxTAcksStep withMaxMessages(Integer maxMessages) {
            this.maxMessages = maxMessages;
            return this;
         }

         public GetRequest.Builder.IncludeIOsStep withMaxTAcks(Integer maxTAcks) {
            this.maxTAcks = maxTAcks;
            return this;
         }

         public GetRequest.Builder.ExcludeIOsStep withIncludeIOs(List<String> includeIOs) {
            this.includeIOs = includeIOs;
            return this;
         }

         public GetRequest.Builder.TackMessageNamesStep withExcludeIOs(List<String> excludeIOs) {
            this.excludeIOs = excludeIOs;
            return this;
         }

         public GetRequest.Builder.BuildStep withTackMessageNames(List<String> tackMessageNames) {
            this.tackMessageNames = tackMessageNames;
            return this;
         }

         public GetRequest build() {
            return new GetRequest(this);
         }

         // $FF: synthetic method
         BasicSteps(Object x0) {
            this();
         }
      }

      public interface BuildStep {
         GetRequest build();
      }

      public interface TackMessageNamesStep {
         GetRequest.Builder.BuildStep withTackMessageNames(List<String> var1);

         GetRequest build();
      }

      public interface ExcludeIOsStep {
         GetRequest.Builder.TackMessageNamesStep withExcludeIOs(List<String> var1);

         GetRequest build();
      }

      public interface IncludeIOsStep {
         GetRequest.Builder.ExcludeIOsStep withIncludeIOs(List<String> var1);

         GetRequest build();
      }

      public interface MaxTAcksStep {
         GetRequest.Builder.IncludeIOsStep withMaxTAcks(Integer var1);

         GetRequest build();
      }

      public interface MaxMessagesStep {
         GetRequest.Builder.MaxTAcksStep withMaxMessages(Integer var1);
      }

      public interface ReferenceStep {
         GetRequest.Builder.BuildStep withReference(String var1);
      }

      public interface DefaultsStep {
         GetRequest.Builder.BuildStep withDefaults();
      }

      static class GetRequestSteps implements GetRequest.Builder.GetRequestBuilderStep {
         public GetRequest.Builder.BuildStep withReference(String reference) {
            return (new GetRequest.Builder.BasicSteps()).withReference(reference);
         }

         public GetRequest.Builder.MaxTAcksStep withMaxMessages(Integer maxMessages) {
            return (new GetRequest.Builder.BasicSteps()).withMaxMessages(maxMessages);
         }

         public GetRequest.Builder.BuildStep withDefaults() {
            return (new GetRequest.Builder.BasicSteps()).withDefaults();
         }
      }

      public interface GetRequestBuilderStep {
         GetRequest.Builder.BuildStep withReference(String var1);

         GetRequest.Builder.MaxTAcksStep withMaxMessages(Integer var1);

         GetRequest.Builder.BuildStep withDefaults();
      }
   }
}
