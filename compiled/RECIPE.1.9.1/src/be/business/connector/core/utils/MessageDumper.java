/**
 * Copyright (C) 2010 Recip-e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package be.business.connector.core.utils;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

/**
 * The Class MessageDumper.
 */
public class MessageDumper {
	
	/** The Constant LOG. */
	private final static Logger LOG = Logger.getLogger(MessageDumper.class);

	/** The Constant IN. */
	public static final String IN = "IN";
	
	/** The Constant OUT. */
	public static final String OUT = "OUT";
	
	
	/** The Constant sdf. */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
	
	/** The instance. */
	private static MessageDumper instance;
	
	/** The path. */
	private static String path = "";
	
	/** The Constant MESSAGE_DUMPER_DIRECTORY. */
	public static final String MESSAGE_DUMPER_DIRECTORY = "messageDumper.directory";
	
	/**
	 * Instantiates a new message dumper.
	 */
	private MessageDumper() {}
	
	/**
	 * Gets the single instance of MessageDumper.
	 * 
	 * @return single instance of MessageDumper
	 */
	public static MessageDumper getInstance() {
		if (instance == null) {
			instance = new MessageDumper();
		}
		return instance;
	}
	
	/**
	 * Inits the MessageDumper
	 * 
	 * @param propertyHandler the property handler
	 */
	public void init(PropertyHandler propertyHandler) {
		if (propertyHandler.hasProperty(MESSAGE_DUMPER_DIRECTORY)) {
			path = propertyHandler.getProperty(MESSAGE_DUMPER_DIRECTORY);
		}
	}
	
	
	/**
	 * Checks if is dump enabled.
	 *
	 * @return true, if is dump enabled
	 */
	public boolean isDumpEnabled(){
		if (path != null && !"".equals(path)) {
			File dir = new File(path);
			if(dir.exists() && dir.isDirectory()) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Dump.
	 * 
	 * @param bos the bos
	 * @param name the name
	 */
	public void dump(ByteArrayOutputStream bos, String name, String way) {
		try {
			if (path != null && !"".equals(path)) {
				File dir = new File(path);
				if(dir.exists() && dir.isDirectory() && isNotBlank(name)) {
					OutputStream fos = new FileOutputStream(generateFileName(name, way));
					bos.writeTo(fos); 
					fos.close();			
				}
			}
		} catch (FileNotFoundException e) {
			LOG.error("dump error",e);
		} catch (IOException e) {
			LOG.error("dump error",e);
		} 
	}
	
	
	/**
	 * Dump.
	 * 
	 * @param bis the bis
	 * @param name the name
	 * @param way the way
	 */
	public void dump(byte[] data , String name, String way) {
		try {
			ByteArrayInputStream bis =  new ByteArrayInputStream(data);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] temp = new byte[1024];
			int read;	
			while((read = bis.read(temp)) > 0){
			   buffer.write(temp, 0, read);
			}			      
			dump(buffer, name, way);
			bis.close();
			buffer.close();
		} catch (FileNotFoundException e) {
			LOG.error("dump error",e);
		} catch (IOException e) {
			LOG.error("dump error",e);
		} 
	}
	
	/**
	 * Generate file name.
	 * 
	 * @param name the name
	 * @param way the way
	 * @return the string
	 */
	private File generateFileName(String name, String way) {
		Date now =  new Date();
		String direction = "";
		if (IN.equalsIgnoreCase(way)) {
			direction = "-" + IN + "-";
		} else if (OUT.equalsIgnoreCase(way)) {
			direction = "-" + OUT + "-";
		}
		return new File(path, name + ".xml");

	}
	
	/**
	 * Gets the operation name.
	 * 
	 * @param context the context
	 * @return the operation name
	 */
	public static String getOperationName(SOAPMessageContext context) {
		try {			
			return context.getMessage().getSOAPBody().getFirstChild().getLocalName();
		} catch (Exception e) {
			throw new RuntimeException("Error while trying to get wsdl operation name", e);
		}
	}
}
