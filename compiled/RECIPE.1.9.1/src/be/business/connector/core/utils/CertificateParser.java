package be.business.connector.core.utils;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.security.auth.x500.X500Principal;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import be.business.connector.core.exceptions.IntegrationModuleException;


/**
 * The Class CertificateParser.
 */
public class CertificateParser {
		
	  /** The Constant LOG. */
  	private static final Logger LOG = Logger.getLogger(CertificateParser.class);
	  
  	/** The Constant ORGANIZATION_UNIT_NAME_ATTRIBUTE_TYPE. */
  	private static final String ORGANIZATION_UNIT_NAME_ATTRIBUTE_TYPE = "OU";
	  
  	/** The Constant COMMON_NAME_ATTRIBUTE_TYPE. */
  	private static final String COMMON_NAME_ATTRIBUTE_TYPE = "CN";
	  
  	/** The Constant REGXP_VALIDATION. */
  	private static final String REGXP_VALIDATION= "([A-Z(-|_)]+=[0-9]+)";
	  
  	/** The Constant FEDERAL_GOVERMENT. */
  	private static final String FEDERAL_GOVERMENT= "Federal Government";
	  
  	/** The Constant EHEALTH_PLATFORM. */
  	private static final String EHEALTH_PLATFORM= "eHealth-platform Belgium";
	  
	/** The Constant ESCAPE_CHAR. */
	private static final String ESCAPE_CHAR = "=";
	
	  /** The Constant X500_KEY_VALUE_SEPARATOR. */
  	private static final String X500_KEY_VALUE_SEPARATOR = "=";
	  
  	/** The Constant X500_FIELD_SEPARATOR. */
  	private static final String X500_FIELD_SEPARATOR = ",";
	  
  	/** The Constant X500_START_STRING_DELIMITER. */
  	private static final String X500_START_STRING_DELIMITER = "=\"";
	  
  	/** The Constant X500_END_STRING_DELIMITER. */
  	private static final String X500_END_STRING_DELIMITER = "\", ";
	  
  	/** The Constant X500_ESCAPE_CHARACTER. */
  	private static final String X500_ESCAPE_CHARACTER = "\\";
	  
  	/** The Constant X500_ESCAPED_QUOTE. */
  	//private static final String X500_ESCAPED_QUOTE = "\"";
	  
  	/** The Constant SAN_ESCAPE_CHAR. */
  	private static final String SAN_ESCAPE_CHAR = "&bksp;";
	  
  	/** The Constant SAN_ESCAPED_QUOTE. */
  	private static final String SAN_ESCAPED_QUOTE = "&bkqt;";
	  
	  /** The type. */
  	private String type = "";

	  /** The value. */
  	private String value = "";

	  /** The application. */
  	private String application = "";

	  /**
  	 * Instantiates a new certificate parser.
  	 * 
  	 * @param cert the cert
	 * @throws IntegrationModuleException 
  	 */
  	public CertificateParser(X509Certificate cert) throws IntegrationModuleException
	  {
	    this(cert.getSubjectX500Principal().getName(X500Principal.RFC1779));
	  }

	  /**
  	 * Instantiates a new certificate parser.
  	 * 
  	 * @param subject the subject
	 * @throws IntegrationModuleException 
  	 */
  	public CertificateParser(String subject) throws IntegrationModuleException
	  {
  		LOG.info("CertificateParser subject: " + subject );
		HashMap<String, ArrayList<String>> subjects = splitCertificateString(subject);	
	
			String commonName = (String) ((ArrayList<String>) subjects.get(COMMON_NAME_ATTRIBUTE_TYPE)).get(0);
			LOG.debug("Certificate CN:" + commonName);

			ArrayList<String> ouList = (ArrayList<String>) subjects.get(ORGANIZATION_UNIT_NAME_ATTRIBUTE_TYPE);
			LOG.debug("Certificate OUList loaded");
			
			if(ouList ==null || ouList.size() == 0){
				throw new IntegrationModuleException(I18nHelper.getLabel("error.corrupt.system.certificate"));
			}
			
			for (Iterator<String> iterator = ouList.iterator(); iterator.hasNext();) {
				String ou = (String) iterator.next();
				LOG.debug("OU:" + ou);
				if (Pattern.matches(REGXP_VALIDATION, ou)) {
					String[] splittedOU = ou.split(ESCAPE_CHAR);
					this.type = splittedOU[0];
					this.value = splittedOU[1];
					LOG.debug("Certificat type: " + type + " & " + " value: "
							+ value);
				} else if ((ouList.size() >= 4)
						&& (!ou.equals(FEDERAL_GOVERMENT))
						&& (!ou.equals(EHEALTH_PLATFORM))) {
					if (commonName.indexOf(ou) >= 0){
						LOG.debug("Certificat application id: " + application);
					this.application = ou;
					}
				}
			}
		}
	  
	 /**
 	 * Split certificate string.
 	 * 
 	 * @param x500PrincipalName the x500 principal name
 	 * @return the hash map
 	 */
 	private HashMap<String, ArrayList<String>> splitCertificateString(String x500PrincipalName)
	  {
	    HashMap<String, ArrayList<String>> items = new HashMap<String, ArrayList<String>>();

	    int indexOfEscapeCharacter = x500PrincipalName.indexOf(X500_ESCAPE_CHARACTER);
	    String workString = null;
	    if (indexOfEscapeCharacter > 0)
	    {
	      workString = x500PrincipalName.replace("\\\\", SAN_ESCAPE_CHAR);
	      workString = workString.replace("\\\"", SAN_ESCAPED_QUOTE);
	    } else {
	      workString = x500PrincipalName;
	    }
	    
	    LOG.debug("The certificatestring in splitCertificateString 1: " + workString);
	    int indexOfStringDelimiter = x500PrincipalName.indexOf(X500_START_STRING_DELIMITER);
	    if (indexOfStringDelimiter == -1)
	    {
	      String[] x500Fields = workString.split(X500_FIELD_SEPARATOR);
	      for (int i = 0; i < x500Fields.length; i++){
	    	  
	    	 String x500Field = x500Fields[i];
	    	  
	    	 //Specialy for the application for retrieving the application id
	    	 
	    	  if(StringUtils.isNotEmpty(x500Field) && x500Field.split(X500_KEY_VALUE_SEPARATOR).length == 3){
	    		  LOG.debug("x500Field has several = in his value: " + x500Field + " and we check the next field for the application id");
	    		  if((i +1)< x500Fields.length){
	    			  i =  i + 1;
	    		  String x500Field2 = x500Fields[i];
	    		  
		    		  if(StringUtils.isNotEmpty(x500Field2) && x500Field2.split(X500_KEY_VALUE_SEPARATOR).length > 1){
		    			  LOG.debug("x500Field has several = in his value: " + x500Field + " and we didn't find the application id");
		    			  addX500Field(x500Field, items);
		    			  addX500Field(x500Field2, items);
		    		  }else{
		    			  LOG.debug("x500Field has several = in his value: " + x500Field + " and we found the application id: " + x500Field2);
		    			  addX500Field(x500Field + ", " + x500Field2, items);
		    		  }
	    		  }else{
	    			  addX500Field(x500Field, items);
	    		  } 
	    	  }else{
	    		  addX500Field(x500Field, items);
	    	  }
	      }
	    }
	    else {
	    	 LOG.debug("The workstring had seceral quotes. The method will be used: splitStringWithquotes");
	      String[] x500Fields = (String[])splitStringWithquotes(workString).toArray(new String[0]);
	     
	      for (int i = 0; i < x500Fields.length; i++) {
	        addX500Field(x500Fields[i], items);
	      }
	    }
	    return items;
	  }

	  /**
  	 * Split string withquotes.
  	 * 
  	 * @param x500PrincipalName the x500 principal name
  	 * @return the list
  	 */
  	private List<String> splitStringWithquotes(String x500PrincipalName) {
	    boolean notFinished = true;
	    List<String> tempList = new ArrayList<String>();
	    String workString = x500PrincipalName;
	    int indexOfStringDelimiter = workString.indexOf(X500_START_STRING_DELIMITER);
	    int indexOfFieldSeparator = workString.indexOf(X500_FIELD_SEPARATOR);

	    while (notFinished)
	    {
	      if ((indexOfStringDelimiter == -1) || (indexOfFieldSeparator < indexOfStringDelimiter))
	      {
	    	  LOG.debug("splitStringWithquotes: if statement one");
	        String[] tempStringTable = workString.split(X500_FIELD_SEPARATOR, 2);
	        tempList.add(tempStringTable[0].trim());
	        workString = tempStringTable[1].trim();
	      }
	      else if(workString.indexOf(X500_END_STRING_DELIMITER) != -1)
	      {
	    	  LOG.debug("splitStringWithquotes: if statement two");
	        String tempString = workString.substring(0, workString.indexOf(X500_END_STRING_DELIMITER) + 1);
	        tempList.add(tempString);

	        workString = workString.substring(workString.indexOf(X500_END_STRING_DELIMITER) + 3);
	      }else if(workString.indexOf(X500_FIELD_SEPARATOR) != -1 && workString.indexOf(X500_KEY_VALUE_SEPARATOR) != -1){
	    	  LOG.debug("splitStringWithquotes: if statement three");
	    	  tempList.add(workString);
		      workString = workString.substring(workString.length()); 
	      }

	      indexOfStringDelimiter = workString.indexOf(X500_START_STRING_DELIMITER);
	      indexOfFieldSeparator = workString.indexOf(X500_FIELD_SEPARATOR);
	      if (indexOfFieldSeparator != -1)
	        continue;
	      notFinished = false;
	      if(StringUtils.isNotEmpty(workString)){
	    	  LOG.debug("splitStringWithquotes: last field added for pharmacist");
	    	  tempList.add(workString);
	      }
	    }

	    return tempList;
	  }

	  /**
  	 * Adds the x500 field.
  	 * 
  	 * @param x500Field the x500 field
  	 * @param x500Fields the x500 fields
  	 */
  	private void addX500Field(String x500Field, HashMap<String, ArrayList<String>> x500Fields) {
	    String[] parts = x500Field.split(X500_KEY_VALUE_SEPARATOR, 2);
	    if (parts.length == 2) {
	      String key = parts[0].trim();

	      String value = parts[1].trim();

	      value = value.replace(SAN_ESCAPE_CHAR, "\\\\");
	      value = value.replace(SAN_ESCAPED_QUOTE, "\\\"");
	      if ((value.charAt(0) == '"') && (value.charAt(value.length() - 1) == '"')) {
	        value = value.substring(1, value.length() - 1);
	      }
	      LOG.info("X500Field: key [" + key + "], value [" + value + "]");
	      ArrayList<String> list = (ArrayList<String>)x500Fields.get(key);
	      if (list == null) {
	        list = new ArrayList<String>();
	        x500Fields.put(key, list);
	      }
	      list.add(value);
	    } else {
	      LOG.info("X500Field [" + x500Field + "] has invalid structure. Ignoring ...");
	    }
	  }
	  
	  /**
	  	 * Gets the type.
	  	 * 
	  	 * @return the type
	  	 */
	  	public String getType()
		  {
		    return this.type;
		  }

		  /**
	  	 * Gets the value.
	  	 * 
	  	 * @return the value
	  	 */
	  	public String getValue()
		  {
		    return this.value;
		  }

		  /**
	  	 * Gets the application.
	  	 * 
	  	 * @return the application
	  	 */
	  	public String getApplication()
		  {
		    return this.application;
		  }
}
