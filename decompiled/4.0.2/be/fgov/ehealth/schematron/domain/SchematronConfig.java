package be.fgov.ehealth.schematron.domain;

import java.io.InputStream;

public final class SchematronConfig {
   private String queryLanguageBinding = "auto";
   private String format = "svrl";
   private String phase;
   private InputStream schema;
   private boolean failOnError = true;
   private boolean debugMode = false;
   private String allowForeign;
   private String schExlstImports;
   private String messageNewline;
   private String attributes;
   private String onlyChildElements;
   private String visitText;
   private String selectContents;
   private String generatePaths;
   private String diagnose;
   private String terminate;
   private String langCode;
   private String schemaId;
   private String resolver;
   private String archiveNameParameter = null;
   private String archiveDirParameter = null;
   private String fileNameParameter = null;
   private String fileDirParameter = null;
   private String encoding = null;

   public SchematronConfig() {
   }

   public String getQueryLanguageBinding() {
      return this.queryLanguageBinding;
   }

   public void setQueryLanguageBinding(String queryLanguageBinding) {
      this.queryLanguageBinding = queryLanguageBinding;
   }

   public String getFormat() {
      return this.format;
   }

   public void setFormat(String format) {
      this.format = format;
   }

   public String getPhase() {
      return this.phase;
   }

   public void setPhase(String phase) {
      this.phase = phase;
   }

   public InputStream getSchema() {
      return this.schema;
   }

   public void setSchema(InputStream schema) {
      this.schema = schema;
   }

   public boolean isFailOnError() {
      return this.failOnError;
   }

   public void setFailOnError(boolean failOnError) {
      this.failOnError = failOnError;
   }

   public boolean isDebugMode() {
      return this.debugMode;
   }

   public void setDebugMode(boolean debugMode) {
      this.debugMode = debugMode;
   }

   public String getAllowForeign() {
      return this.allowForeign;
   }

   public void setAllowForeign(String allowForeign) {
      this.allowForeign = allowForeign;
   }

   public String getSchExlstImports() {
      return this.schExlstImports;
   }

   public void setSchExlstImports(String schExlstImports) {
      this.schExlstImports = schExlstImports;
   }

   public String getMessageNewline() {
      return this.messageNewline;
   }

   public void setMessageNewline(String messageNewline) {
      this.messageNewline = messageNewline;
   }

   public String getAttributes() {
      return this.attributes;
   }

   public void setAttributes(String attributes) {
      this.attributes = attributes;
   }

   public String getonlyChildElements() {
      return this.onlyChildElements;
   }

   public void setOnlyChildElements(String onlyChildElements) {
      this.onlyChildElements = onlyChildElements;
   }

   public String getVisitText() {
      return this.visitText;
   }

   public void setVisitText(String visitText) {
      this.visitText = visitText;
   }

   public String getSelectContents() {
      return this.selectContents;
   }

   public void setSelectContents(String selectContents) {
      this.selectContents = selectContents;
   }

   public String getGeneratePaths() {
      return this.generatePaths;
   }

   public void setGeneratePaths(String generatePaths) {
      this.generatePaths = generatePaths;
   }

   public String getDiagnose() {
      return this.diagnose;
   }

   public void setDiagnose(String diagnose) {
      this.diagnose = diagnose;
   }

   public String getTerminate() {
      return this.terminate;
   }

   public void setTerminate(String terminate) {
      this.terminate = terminate;
   }

   public String getLangCode() {
      return this.langCode;
   }

   public void setLangCode(String langCode) {
      this.langCode = langCode;
   }

   public String getSchemaId() {
      return this.schemaId;
   }

   public void setSchemaId(String schemaId) {
      this.schemaId = schemaId;
   }

   public String getResolver() {
      return this.resolver;
   }

   public void setResolver(String resolver) {
      this.resolver = resolver;
   }

   public String getArchiveNameParameter() {
      return this.archiveNameParameter;
   }

   public void setArchiveNameParameter(String archiveNameParameter) {
      this.archiveNameParameter = archiveNameParameter;
   }

   public String getArchiveDirParameter() {
      return this.archiveDirParameter;
   }

   public void setArchiveDirParameter(String archiveDirParameter) {
      this.archiveDirParameter = archiveDirParameter;
   }

   public String getFileNameParameter() {
      return this.fileNameParameter;
   }

   public void setFileNameParameter(String fileNameParameter) {
      this.fileNameParameter = fileNameParameter;
   }

   public String getFileDirParameter() {
      return this.fileDirParameter;
   }

   public void setFileDirParameter(String fileDirParameter) {
      this.fileDirParameter = fileDirParameter;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public void setEncoding(String encoding) {
      this.encoding = encoding;
   }
}
