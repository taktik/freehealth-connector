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

package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class IOUtils.
 */
public class IOUtils {

	/** The Constant LOG. */
	private final static Logger LOG = LogManager.getLogger(IOUtils.class);

	/**
	 * Gets the bytes.
	 *
	 * @param inputStream
	 *            the input stream
	 * @return the bytes
	 */
	public static byte[] getBytes(InputStream inputStream) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) >= 0) {
				baos.write(buffer, 0, len);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * To byte. This dummy method is very usefull when running in dotNet!
	 *
	 * @param string
	 *            the string
	 * @return the byte[]
	 */
	public static byte[] toByte(String string) {
		return string.getBytes();
	}

	/**
	 * Read stream.
	 *
	 * @param path
	 *            the path
	 * @return the byte[]
	 */
	public static byte[] loadResource(String path) {
		try {
			return getBytes(getResourceAsStream(path));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Compress.
	 *
	 * @param input
	 *            the input
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] compress(byte[] input) throws IOException {
		long size = input.length;
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		GZIPOutputStream out = new GZIPOutputStream(outstream);
		out.write(input);
		out.flush();
		outstream.flush();
		out.close();
		outstream.close();
		byte[] ret = outstream.toByteArray();
		LOG.debug("Compression of data from " + size + " bytes to " + ret.length + " bytes");
		return ret;
	}

	/**
	 * Decompress.
	 *
	 * @param unSealmessage
	 *            the un sealmessage
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static byte[] decompress(byte[] unSealmessage) throws IOException {
		long size = unSealmessage.length;
		ByteArrayInputStream inputstream = new ByteArrayInputStream(unSealmessage);
		GZIPInputStream input = new GZIPInputStream(inputstream);
		int i;
		ByteArrayOutputStream o = new ByteArrayOutputStream();

		byte buffer[] = new byte[1024];
		while ((i = input.read(buffer)) > 0) {
			o.write(buffer, 0, i);
		}
		o.flush();
		input.close();
		inputstream.close();
		o.close();
		byte[] ret = o.toByteArray();
		LOG.debug("Decompression of data from " + size + " bytes to " + ret.length + " bytes");
		return ret;
	}

	/**
	 * Gets the resource as stream.
	 *
	 * @param path
	 *            the path
	 * @return the resource as stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	// public static InputStream getResourceAsStream(String path) throws
	// IOException {
	// File f = new File(path);
	// if (f.exists()) {
	// LOG.info("Loading " + path + " from file system");
	// return new FileInputStream(f);
	// } else {
	// LOG.info("Loading " + path + " from classpath");
	// InputStream stream = IOUtils.class.getResourceAsStream(path);
	// if (stream == null) {
	// throw new IOException("Invalid resource " + path);
	// }
	// return stream;
	// }
	// }

	public static InputStream getResourceAsStream(String path) throws IOException {
		File f = new File(path);
		LOG.info("Loading " + f.getAbsolutePath() + " from file system");
		if (f.exists()) {
			LOG.info("Loading " + f.getAbsolutePath() + " from file system");
			return new FileInputStream(f);
		} else {
			LOG.info("Loading " + path + " from classpath");
			InputStream stream = IOUtils.class.getResourceAsStream(path);
			if (stream == null) {
				path = path.replace(" ", "%20");
				URL u = new URL(path);
				stream = u.openStream();
				if (stream == null)
					throw new IOException("Invalid resource " + path);
			}
			return stream;
		}
	}
}
