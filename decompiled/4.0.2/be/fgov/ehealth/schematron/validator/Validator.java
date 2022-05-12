package be.fgov.ehealth.schematron.validator;

import be.fgov.ehealth.schematron.domain.SchematronResult;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public interface Validator {
   SchematronResult validate(Source var1, String var2, String var3, String var4, String var5, String var6) throws TransformerConfigurationException, TransformerException;
}
