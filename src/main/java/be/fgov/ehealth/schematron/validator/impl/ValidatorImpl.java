package be.fgov.ehealth.schematron.validator.impl;

import be.fgov.ehealth.schematron.domain.SchematronResult;
import be.fgov.ehealth.schematron.validator.Validator;
import java.io.StringWriter;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang.StringUtils;

public final class ValidatorImpl implements Validator {
   private final Templates validator;

   public ValidatorImpl(Templates templates) throws IllegalArgumentException {
      if (templates == null) {
         throw new IllegalArgumentException("A validator cannot be constructed with null templates");
      } else {
         this.validator = templates;
      }
   }

   public SchematronResult validate(Source xml, String fnp, String fdp, String anp, String adp, String encoding) throws TransformerConfigurationException, TransformerException {
      Transformer transformer = this.validator.newTransformer();
      if (StringUtils.isNotBlank(encoding)) {
         transformer.setOutputProperty("encoding", encoding);
      }

      transformer.setOutputProperty("standalone", "no");
      String sid = xml.getSystemId();
      String aid = "";
      if (sid != null && (sid.startsWith("jar:") || sid.startsWith("zip:"))) {
         aid = sid.substring(0, sid.lastIndexOf("!"));
         sid = sid.substring(sid.lastIndexOf("!") + 1);
      }

      if (anp != null && anp.length() > 0) {
         transformer.setParameter("archiveNameParameter", anp);
      } else if (aid != null && aid.length() > 0) {
         transformer.setParameter("archiveNameParameter", aid.substring(aid.lastIndexOf("/") + 1));
      }

      if (adp != null && adp.length() > 0) {
         transformer.setParameter("archiveDirParameter", adp);
      } else if (aid != null && aid.length() > 0 && aid.lastIndexOf("/") > -1) {
         transformer.setParameter("archiveDirParameter", aid.substring(0, aid.lastIndexOf("/")));
      }

      if (fnp != null && fnp.length() > 0) {
         transformer.setParameter("fileNameParameter", fnp);
      } else if (sid != null && sid.length() > 0) {
         transformer.setParameter("fileNameParameter", sid.substring(sid.lastIndexOf("/") + 1));
      }

      if (fdp != null && fdp.length() > 0) {
         transformer.setParameter("fileDirParameter", fdp);
      } else if (sid != null && sid.length() > 0 && sid.lastIndexOf("/") > -1) {
         transformer.setParameter("fileDirParameter", sid.substring(0, sid.lastIndexOf("/")));
      }

      StringWriter writer = new StringWriter();
      transformer.transform(xml, new StreamResult(writer));
      return new SchematronResult(writer.toString());
   }
}
