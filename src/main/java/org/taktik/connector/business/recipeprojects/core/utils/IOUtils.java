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
import org.apache.log4j.Logger;

/**
 * The Class IOUtils.
 */
public class IOUtils {

	/** The Constant LOG. */
	private final static Logger LOG = Logger.getLogger(IOUtils.class);

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
	
	public static String getFileAsFullPath(String path) throws IOException {
		File f = new File(path);
		if (f.exists()) {
			LOG.info("Loading " + path + " from file system");
			return f.getAbsolutePath();
		} 
		
		return null;

	}

	/**
	 * Str convert.
	 * 
	 * @param message
	 *            the message
	 * @return the string
	 */
	public static String strConvert(byte[] message) {
		return message != null ? new String(message) : "";
	}

	/**
	 * Gets the list of P12 files for a specific user (SSIN).
	 * 
	 * @param path
	 *            the path to the folder containing P12 files
	 * @param ssin
	 *            the ssin of the user
	 * @return the p12 file list
	 */
	public static List<String> getP12FileList(String path, String ssinRiziv) {
		if (path == null) {
			return Collections.emptyList();
		}
		List<String> fileList = new ArrayList<String>();
		File dir = new File(path);

		if (!dir.exists() || !dir.isDirectory()) {
			LOG.error("The directory " + path + " does not exist");
			return Collections.emptyList();
		}

		FileFilter filter = new FileFilter() {
			public boolean accept(File pathname) {
				if (pathname.isFile()) {
					return true;
				} else {
					return false;
				}
			}
		};
		File[] files = dir.listFiles(filter);
		String olddate = null;
		String filenames = null;
		if (files == null) {
			return Collections.emptyList();
		} else {
			for (File f : files) {
				LOG.info("Keystore filename: " + f.getName());
				if ((f.getName().contains(ssinRiziv)) && CertificateExtension.isSupported(f)) {
					try {
						CertificateExtension certificateExtension = CertificateExtension.lookupType(f);
						filenames = certificateExtension.removeExtension(f.getName());
						String[] filename = null;
						if (f.getName().contains(" ")) {
							filename = filenames.split(" ");
						} else {
							filename = filenames.split("_");
						}

						for (int i = 0; i < filename.length; i++) {
							LOG.info("Keystore filename part " + i + ":" + filename[i]);
						}

						String[] date = filename[filename.length - 1].split("-");

						for (int i = 0; i < date.length; i++) {
							LOG.info("Keystore date part " + i + ":" + date[i]);
						}

						LOG.info("Keystore date.length: " + date.length);
						LOG.info("Keystore date last part: " + date[date.length - 1]);

						String lastDt = date[date.length - 1];
						lastDt = lastDt.substring(0, 6);
						LOG.info("Keystore lastDt: " + lastDt);

						long newDate = Long.parseLong(date[0] + lastDt);

						LOG.info("Keystore new date:" + newDate);

						if (olddate != null && newDate > Long.parseLong(olddate)) {
							if (fileList.size() > 0) {
								fileList.remove(0);
							}
							fileList.add(f.getCanonicalPath());
							olddate = String.valueOf(newDate);
						} else if (olddate == null) {
							fileList.add(f.getCanonicalPath());
							olddate = String.valueOf(newDate);
						} else {
							fileList.add(f.getCanonicalPath());
						}
					} catch (IOException e) {
						LOG.error("IOException on getP12FileList()", e);
					}
				}
			}
			return fileList;
		}

	}

	public static List<String> getConfigurationFileList(String path, String configName) {
		if (path == null) {
			return Collections.emptyList();
		}
		List<String> fileList = new ArrayList<String>();
		File dir = new File(path);

		if (!dir.exists() || !dir.isDirectory()) {
			LOG.error("The directory " + path + " does not exist");
			return Collections.emptyList();
		}

		FileFilter filter = new FileFilter() {
			public boolean accept(File pathname) {
				if (pathname.isFile()) {
					return true;
				} else {
					return false;
				}
			}
		};
		File[] files = dir.listFiles(filter);
		String filenames = null;
		Integer oldTot = 0;
		if (files == null) {
			return Collections.emptyList();
		} else {
			for (File f : files) {
				LOG.info("filename: " + f.getName());
				if ((f.getName().contains(configName)) && (f.getName().endsWith(".xml"))) {
					filenames = f.getName();
					String[] filename = null;

					filename = filenames.split("_");

					String version = null;
					for (int i = 0; i < filename.length; i++) {
						LOG.info("Config filename part " + i + ":" + filename[i]);
						if (filename[i].endsWith(".xml")) {
							String fi = filename[i].replace(".xml", "");

							if (!StringUtils.isEmpty(fi)) {
								version = fi.replace("v", "");

								Integer tot = Integer.valueOf(version);
								if (tot > oldTot) {
									if (fileList.size() > 0) {
										fileList.remove(0);
									}
									fileList.add(f.getAbsolutePath());
									oldTot = tot;
								}
							}
						}
					}
				}
			}
			return fileList;
		}

	}

	public static List<String> getP12OldFileList(String path, String ssinRiziv) {
		if (path == null) {
			return Collections.emptyList();
		}
		List<String> fileList = new ArrayList<String>();
		File dir = new File(path);

		if (!dir.exists() || !dir.isDirectory()) {
			LOG.error("The directory " + path + " does not exist");
			return Collections.emptyList();
		}

		FileFilter filter = new FileFilter() {
			public boolean accept(File pathname) {
				if (pathname.isFile()) {
					return true;
				} else {
					return false;
				}
			}
		};
		File[] files = dir.listFiles(filter);
		String olddate = null;

		String filenames = null;
		if (files == null) {
			return Collections.emptyList();
		} else {
			for (int j = 0; j < files.length; j++) {
				File f = files[j];

				LOG.info("Keystore filename: " + f.getName());
				if ((f.getName().contains(ssinRiziv)) && (f.getName().endsWith(".p12"))) {
					try {
						filenames = f.getName();
						String[] filename = null;
						if (f.getName().contains(" ")) {
							filename = filenames.split(" ");
						} else {
							filename = filenames.split("_");
						}
						for (int i = 0; i < filename.length; i++) {
							LOG.debug("Keystore filename part " + i + ":" + filename[i]);
						}

						String[] date = filename[filename.length - 1].split("-");

						for (int i = 0; i < date.length; i++) {
							LOG.debug("Keystore date part " + i + ":" + date[i]);
						}

						LOG.debug("Keystore date.length: " + date.length);
						LOG.debug("Keystore date last part: " + date[date.length - 1]);

						String lastDt = date[date.length - 1];
						lastDt = lastDt.substring(0, 6);
						LOG.debug("Keystore lastDt: " + lastDt);

						long newDate = Long.parseLong(date[0] + lastDt);

						LOG.debug("Keystore new date:" + newDate);

						if (olddate != null && newDate > Long.parseLong(olddate)) {
							if (fileList.size() > 0) {
								fileList.remove(0);
							}
							fileList.add(files[j - 1].getCanonicalPath());
							olddate = String.valueOf(newDate);
						} else if (olddate == null) {
							fileList.add(f.getCanonicalPath());
							olddate = String.valueOf(newDate);
						} else {
							fileList.add(f.getCanonicalPath());
						}
					} catch (IOException e) {
						LOG.error("IOException on getP12OldFileList()", e);
					}
				}
			}
			return fileList;
		}

	}

	/**
	 * Load resource.
	 * 
	 * @param string
	 *            the string
	 * @return the byte[]
	 * @throws IOException
	 */
	/*
	 * public static byte[] loadResource(String string) throws IOException {
	 * return getBytes(getResourceAsStream(string)); }
	 */

}
