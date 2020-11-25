package be.business.connector.gfddpp.utils;

import be.business.connector.projects.common.utils.INSZUtils;
import org.apache.commons.lang3.StringUtils;

public class DataEntryURI {
   private static final String SUBJECT_TAG = "subject";
   private static final String MEDICATION_SCHEME_TAG = "medication-scheme";
   private static final String NEW_TAG = "new";
   public static final String READ_URI_FORMAT = "/subject/%s/medication-scheme/%s/%s";
   public static final String READ_ALL_URI_FORMAT = "/subject/%s/medication-scheme";
   public static final String UPDATE_URI_FORMAT = "/subject/%s/medication-scheme/%s/new/%s";
   public static final String NEW_URI_FORMAT = "/subject/%s/medication-scheme/new";
   private boolean valid;
   private DataEntryURI.Purpose purpose;
   private String subjectId;
   private String dataEntryId;
   private int dataEntryVersion;
   private String fullURI;

   public DataEntryURI(String URI) {
      this.fullURI = URI;
      this.valid = false;
      if (!StringUtils.isBlank(URI)) {
         String[] tokens = StringUtils.split(URI, "/");
         int version;
         switch(tokens.length) {
         case 3:
            if ("subject".equals(tokens[0]) && INSZUtils.isValidINSZ(tokens[1]) && "medication-scheme".equals(tokens[2])) {
               this.valid = true;
               this.subjectId = tokens[1];
               this.purpose = DataEntryURI.Purpose.READ_ALL;
            }
            break;
         case 4:
            if ("subject".equals(tokens[0]) && INSZUtils.isValidINSZ(tokens[1]) && "medication-scheme".equals(tokens[2]) && "new".equals(tokens[3])) {
               this.valid = true;
               this.subjectId = tokens[1];
               this.purpose = DataEntryURI.Purpose.CREATE;
            }
            break;
         case 5:
            if ("subject".equals(tokens[0]) && INSZUtils.isValidINSZ(tokens[1]) && "medication-scheme".equals(tokens[2]) && StringUtils.isNotBlank(tokens[3])) {
               try {
                  version = Integer.valueOf(tokens[4]);
               } catch (Exception var5) {
                  return;
               }

               this.valid = true;
               this.subjectId = tokens[1];
               this.dataEntryId = tokens[3];
               this.dataEntryVersion = version;
               this.purpose = DataEntryURI.Purpose.READ;
            }
            break;
         case 6:
            if ("subject".equals(tokens[0]) && INSZUtils.isValidINSZ(tokens[1]) && "medication-scheme".equals(tokens[2]) && StringUtils.isNotBlank(tokens[3]) && "new".equals(tokens[4])) {
               try {
                  version = Integer.valueOf(tokens[5]);
               } catch (Exception var4) {
                  return;
               }

               this.valid = true;
               this.subjectId = tokens[1];
               this.dataEntryId = tokens[3];
               this.dataEntryVersion = version;
               this.purpose = DataEntryURI.Purpose.UPDATE;
            }
         }
      }

   }

   public static DataEntryURI updateURI(String subjectId, String dataEntryId, int dataEntryVersion) {
      return new DataEntryURI(String.format("/subject/%s/medication-scheme/%s/new/%s", subjectId, dataEntryId, dataEntryVersion));
   }

   public static DataEntryURI newURI(String subjectId) {
      return new DataEntryURI(String.format("/subject/%s/medication-scheme/new", subjectId));
   }

   public static DataEntryURI readURI(String subjectId, String dataEntryId, int dataEntryVersion) {
      return new DataEntryURI(String.format("/subject/%s/medication-scheme/%s/%s", subjectId, dataEntryId, dataEntryVersion));
   }

   public static DataEntryURI readAllURI(String subjectId) {
      return new DataEntryURI(String.format("/subject/%s/medication-scheme", subjectId));
   }

   public boolean isValid() {
      return this.valid;
   }

   public boolean isReadAllURI() {
      return this.isValid() && DataEntryURI.Purpose.READ_ALL.equals(this.purpose);
   }

   public boolean isReadURI() {
      return this.isValid() && DataEntryURI.Purpose.READ.equals(this.purpose);
   }

   public boolean isCreateURI() {
      return this.isValid() && DataEntryURI.Purpose.CREATE.equals(this.purpose);
   }

   public boolean isUpdateURI() {
      return this.isValid() && DataEntryURI.Purpose.UPDATE.equals(this.purpose);
   }

   public String getSubjectId() {
      return this.subjectId;
   }

   public String getDataEntryId() {
      return this.dataEntryId;
   }

   public int getDataEntryVersion() {
      return this.dataEntryVersion;
   }

   public String toString() {
      return this.fullURI;
   }

   private static enum Purpose {
      CREATE,
      UPDATE,
      READ_ALL,
      READ;
   }
}
