package be.fgov.ehealth.schematron;

import be.fgov.ehealth.schematron.domain.SchematronResult;
import java.io.InputStream;

public interface SchematronSession {
   SchematronResult validate(InputStream var1) throws Exception;
}
