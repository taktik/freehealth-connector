package be.fgov.ehealth.schematron.impl;

import be.fgov.ehealth.schematron.SchematronSession;
import be.fgov.ehealth.schematron.domain.SchematronConfig;
import be.fgov.ehealth.schematron.domain.SchematronResult;
import be.fgov.ehealth.schematron.exception.InitialisationException;
import be.fgov.ehealth.schematron.utils.ValidatorErrorListener;
import be.fgov.ehealth.schematron.validator.Validator;
import be.fgov.ehealth.schematron.validator.ValidatorFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SchematronSessionImpl implements SchematronSession {
   private static final Logger LOG = LoggerFactory.getLogger(SchematronSessionImpl.class);
   private SchematronConfig config;
   private Validator validator;
   private File input;

   public SchematronSessionImpl(SchematronConfig config) {
      this.config = config;
   }

   private static File convert(InputStream is) throws IOException {
      File temp = File.createTempFile("schematron", ".tmp");
      temp.deleteOnExit();
      FileWriter fileWriter = null;
      InputStreamReader inputStreamReader = null;

      try {
         fileWriter = new FileWriter(temp);
         inputStreamReader = new InputStreamReader(is);
         IOUtils.copy(inputStreamReader, fileWriter);
         fileWriter.flush();
      } finally {
         IOUtils.closeQuietly(fileWriter);
         IOUtils.closeQuietly(inputStreamReader);
      }

      return temp;
   }

   public SchematronResult validate(InputStream is) throws Exception {
      this.initValidator();
      this.input = convert(is);
      return this.doValidate(new StreamSource(new FileInputStream(this.input)));
   }

   private void initValidator() throws InitialisationException {
      try {
         ValidatorFactory factory = new ValidatorFactory(this.config.getQueryLanguageBinding(), this.config.getFormat());
         if (this.config.getResolver() != null && !this.config.getResolver().isEmpty()) {
            factory.setResolver(this.loadClass(this.config.getResolver()));
         }

         factory.setDebugMode(this.config.isDebugMode());
         factory.setErrorListener(new ValidatorErrorListener());
         this.setParamOnFactory(factory, "phase", this.config.getPhase());
         this.setParamOnFactory(factory, "allow-foreign", this.config.getAllowForeign());
         this.setParamOnFactory(factory, "sch.exslt.imports", this.config.getSchExlstImports());
         this.setParamOnFactory(factory, "message-newline", this.config.getMessageNewline());
         this.setParamOnFactory(factory, "attributes", this.config.getAttributes());
         this.setParamOnFactory(factory, "only-child-elements", this.config.getonlyChildElements());
         this.setParamOnFactory(factory, "visit-text", this.config.getVisitText());
         this.setParamOnFactory(factory, "select-contents", this.config.getSelectContents());
         this.setParamOnFactory(factory, "generate-paths", this.config.getGeneratePaths());
         this.setParamOnFactory(factory, "diagnose", this.config.getDiagnose());
         this.setParamOnFactory(factory, "terminate", this.config.getTerminate());
         this.setParamOnFactory(factory, "schema-id", this.config.getSchemaId());
         this.setParamOnFactory(factory, "output-encoding", this.config.getEncoding());
         this.setParamOnFactory(factory, "langCode", this.config.getLangCode());
         LOG.info("Generating validator for schema " + this.config.getSchema() + "... ");
         this.validator = factory.newValidator(new StreamSource(this.config.getSchema()));
         LOG.info("Validator ready to process");
      } catch (TransformerException var3) {
         LOG.debug(var3.getMessage());
         SourceLocator locator = var3.getLocator();
         if (locator != null) {
            LOG.debug("SystemID: " + locator.getSystemId() + "; Line#: " + locator.getLineNumber() + "; Column#: " + locator.getColumnNumber());
         }

         throw new InitialisationException("The validator could not be initialised", var3);
      } catch (IOException var4) {
         throw new InitialisationException("Error when outputting preprocessor stylesheet:", var4);
      } catch (Exception var5) {
         LOG.error("Error with initializing validator: " + var5.getMessage());
         throw new InitialisationException("Error with initializing validator: ", var5);
      }
   }

   private SchematronResult doValidate(StreamSource xmlSource) throws Exception {
      try {
         SchematronResult validate = this.validator.validate(xmlSource, this.config.getFileNameParameter(), this.config.getFileDirParameter(), this.config.getArchiveNameParameter(), this.config.getArchiveDirParameter(), this.config.getEncoding());
         validate.setInput(this.input);
         return validate;
      } catch (TransformerConfigurationException var4) {
         throw new Exception("Could not instantiate validator for document ");
      } catch (TransformerException var5) {
         if (this.config.isFailOnError()) {
            throw new Exception("Could not validate document ", var5);
         } else {
            LOG.error("", var5);
            SourceLocator locator = var5.getLocator();
            if (locator != null) {
               LOG.info("SystemID: " + locator.getSystemId() + "; Line#: " + locator.getLineNumber() + "; Column#: " + locator.getColumnNumber());
            }

            return null;
         }
      }
   }

   private Class<?> loadClass(String classname) throws ClassNotFoundException {
      return Class.forName(classname);
   }

   private void setParamOnFactory(ValidatorFactory factory, String paramName, Object paramValue) {
      if (paramValue != null) {
         factory.setParameter(paramName, paramValue);
      }

   }
}
