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

package org.taktik.connector.business.recipeprojects.core.performance.metric;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;

import org.apache.log4j.rolling.RollingPolicy;
import org.apache.log4j.rolling.RolloverDescription;
import org.apache.log4j.rolling.TimeBasedRollingPolicy;
import org.apache.log4j.rolling.TriggeringPolicy;
import org.apache.log4j.rolling.helper.FileRenameAction;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

/**
 * The Class MetricsUploadPolicy.
 */
@Deprecated
public class MetricsUploadPolicy implements RollingPolicy, OptionHandler, TriggeringPolicy  {
	
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(MetricsUploadPolicy.class);

	/** The tbrp. */
	private TimeBasedRollingPolicy tbrp;
	
	/** The destination. */
	private File destination;
	
	/** The destination field. */
	private Field destinationField;

	/**
	 * Instantiates a new metrics upload policy.
	 */
	public MetricsUploadPolicy() {
		this.tbrp = new TimeBasedRollingPolicy();
	}
	
	/** {@inheritDoc} */
	public RolloverDescription rollover(String currentActiveFile) {
		RolloverDescription desc = tbrp.rollover(currentActiveFile);
		this.destination = this.getDestination(desc);		
		return desc;
	}
	
	/**
	 * Gets the destination.
	 * 
	 * @param desc the desc
	 * @return the destination
	 */
	private File getDestination(RolloverDescription desc) {
		if (desc != null) {
			try {
				FileRenameAction fra = (FileRenameAction) desc.getSynchronous();
				if (destinationField == null) {				
					for (Field f : fra.getClass().getDeclaredFields()) {
						if (f.getName().equalsIgnoreCase("source") 
								&& Modifier.isPrivate(f.getModifiers())) {
							f.setAccessible(true);
							this.destinationField = f;
							break;												
						}
					}
				}
				return (File) destinationField.get(fra);
			} catch (IllegalArgumentException e) {
				LOG.error("Could not get metrics upload file.", e);
			} catch (IllegalAccessException e) {
				LOG.error("Could not get metrics upload file.", e);
			}
		}
		return null;
	}
	
	/** {@inheritDoc} */
	public void activateOptions() {
		tbrp.activateOptions();
	}

	/** {@inheritDoc} */
	public boolean equals(Object obj) {
		return tbrp.equals(obj);
	}

	/**
	 * Gets the active file name.
	 * 
	 * @return the active file name
	 */
	public String getActiveFileName() {
		return tbrp.getActiveFileName();
	}

	/**
	 * Gets the file name pattern.
	 * 
	 * @return the file name pattern
	 */
	public String getFileNamePattern() {
		return tbrp.getFileNamePattern();
	}

	/** {@inheritDoc} */
	public int hashCode() {
		return tbrp.hashCode();
	}

	/** {@inheritDoc} */
	public RolloverDescription initialize(String currentActiveFile,
			boolean append) {
		return tbrp.initialize(currentActiveFile, append);
	}

	/** {@inheritDoc} */
	public boolean isTriggeringEvent(Appender appender, LoggingEvent event,
			String filename, long fileLength) {
		return tbrp.isTriggeringEvent(appender, event, filename, fileLength);
	}

	/**
	 * Sets the active file name.
	 * 
	 * @param afn the new active file name
	 */
	public void setActiveFileName(String afn) {
		tbrp.setActiveFileName(afn);
	}

	/**
	 * Sets the file name pattern.
	 * 
	 * @param fnp the new file name pattern
	 */
	public void setFileNamePattern(String fnp) {
		tbrp.setFileNamePattern(fnp);
	}

	/** {@inheritDoc} */
	public String toString() {
		return tbrp.toString();
	}	  
	
	/**
	 * Gets the destination.
	 * 
	 * @return the destination
	 */
	public File getDestination() {
		return destination;
	}
}
