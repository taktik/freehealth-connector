package be.business.connector.recipe.executor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import be.recipe.services.core.ResponseType;

/**
 * The Class GetAllRidInProcessResult.
 */
@XmlRootElement(namespace = "http://services.recipe.be/executor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllRidInProcessResult")
public class GetAllRidInProcessResult extends ResponseType {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The rids. */
	private List<String> rids;

	/**
	 * Instantiates a new gets the all rid in process response.
	 */
	public GetAllRidInProcessResult() {
		super();
	}

	/**
	 * Adds the rid.
	 *
	 * @param rid
	 *            the rid
	 */
	public void addRid(String rid) {
		if (rids == null) {
			rids = new ArrayList<String>();
		}
		rids.add(rid);
	}

	/**
	 * Gets the rids.
	 *
	 * @return the rids
	 */
	public List<String> getRids() {
		return rids;
	}

	/**
	 * Sets the rids.
	 *
	 * @param rids
	 *            the new rids
	 */
	public void setRids(List<String> rids) {
		this.rids = rids;
	}
}
