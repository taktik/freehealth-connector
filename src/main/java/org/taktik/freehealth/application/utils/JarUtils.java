/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */

package org.taktik.freehealth.application.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class JarUtils {
	public static String getJarPath() {
		// Note : This will only work when packaged as JAR
		try {
			String jarPath = new URI(JarUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("jar\\!/.+", "jar")).getPath();

			// Make sure we found a jar path
			if (jarPath != null && jarPath.toLowerCase().endsWith(".jar")) {
				return jarPath;
			}
		} catch (URISyntaxException ignored) {
		}

		return null;
	}

	public static Manifest getManifest() {
		Manifest manifest = null;

		// Load JAR Manifest
		String jarPath = getJarPath();
		if (jarPath != null) {
			JarFile jar = null;
			try {
				jar = new JarFile(jarPath);
				manifest = jar.getManifest();
			} catch (IOException ignored) {
			} finally {
				try {
					if (jar != null) {
						jar.close();
					}
				} catch (IOException ignored) {
				}
			}
		}

		return manifest;
	}
}
