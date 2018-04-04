package org.taktik.connector.business.domain;

import java.io.Serializable;
import java.util.Map;

public class Error implements Serializable {
	private String code;
	private String descr;
	private String url;
	private String zone;
	private Map<String, String> codeDescription;

	public Error() {
	}

	public Error(String code, String url, String descr, Map<String,String> codeDescription) {
		this.code = code;
		this.url = url;
		this.descr = descr;
		this.codeDescription = codeDescription;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(Map<String, String> codeDescription) {
		this.codeDescription = codeDescription;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	@Override
	public String toString() {
		return "Error{" +
				"code='" + code + '\'' +
				", description='" + descr + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
