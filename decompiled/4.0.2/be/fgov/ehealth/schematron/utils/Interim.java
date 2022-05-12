package be.fgov.ehealth.schematron.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Interim {
   private StringWriter writer;
   private final String systemId;

   public Interim(String systemId) {
      this.systemId = systemId;
   }

   public Result makeEmptyResult() throws IllegalStateException, UnsupportedEncodingException {
      if (this.writer != null) {
         throw new IllegalStateException("The templates have already been produced.");
      } else {
         this.writer = new StringWriter();
         return new StreamResult(this.writer);
      }
   }

   public Source getSource() throws IllegalStateException {
      if (this.writer == null) {
         throw new IllegalStateException("The templates have not been produced.");
      } else {
         StreamSource source = new StreamSource(new StringReader(this.writer.toString()));
         source.setPublicId("compiled:" + this.systemId);
         return source;
      }
   }

   public void saveAs(File file) throws IOException {
      PrintStream pout = new PrintStream(file, "utf-8");
      pout.print(this.writer.toString());
      pout.close();
   }
}
