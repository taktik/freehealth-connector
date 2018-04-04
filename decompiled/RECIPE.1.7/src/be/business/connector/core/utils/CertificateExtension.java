package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.File;

public enum CertificateExtension {

	P12(".p12"), ACC_P12(".acc-p12");

	private final String name;

	CertificateExtension(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static CertificateExtension lookupType(File file) {
		for (CertificateExtension extension : CertificateExtension.values()) {
			if (file.getName().endsWith(extension.getName())) {
				return extension;
			}
		}
		throw new IllegalArgumentException();
	}

	public static boolean isSupported(File file) {
		if (file == null) {
			return false;
		}
		try{
			lookupType(file);
			return true;
		}catch(IllegalArgumentException e){
			return false;
		}
		
	}

	public String removeExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		return fileName.substring(0, fileName.length() - name.length());
	}
}
