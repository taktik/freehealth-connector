package be.business.connector.recipe.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class Page.
 */
@XmlRootElement(namespace = "http:/services.recipe.be/paging")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Page")
public class Page implements Serializable {

	/** The Constant serialVersionUID. */
	private final static long serialVersionUID = 1L;

	/** The year. */
	@Min(value = 2000)
	@Max(value = 2100)
	private int year;

	/** The month. */
	@Min(value = 1)
	@Max(value = 12)
	private int month;

	public Page() {
		super();
	}

	/**
	 * Instantiates a new page.
	 */
	public Page(final int year, final int month) {
		this.year = year;
		this.month = month;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year
	 *            the new year
	 */
	public void setYear(final int year) {
		this.year = year;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month
	 *            the new month
	 */
	public void setMonth(final int month) {
		this.month = month;
	}
}