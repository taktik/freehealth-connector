package org.taktik.connector.business.domain.dmg;

import java.io.Serializable;
import java.util.Date;

public class DmgMessageWithPatient extends DmgMessage implements Serializable {
	protected String inss;
	protected String firstName;
	protected String lastName;
	protected Date birthday;
	protected Date deceased;
	protected String sex;
	protected String regNrWithMut;
	protected String mutuality;

	public String getInss() {
		return inss;
	}

	public void setInss(String inss) {
		this.inss = inss;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDeceased() {
		return deceased;
	}

	public void setDeceased(Date deceased) {
		this.deceased = deceased;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRegNrWithMut() {
		return regNrWithMut;
	}

	public void setRegNrWithMut(String regNrWithMut) {
		this.regNrWithMut = regNrWithMut;
	}

	public String getMutuality() {
		return mutuality;
	}

	public void setMutuality(String mutuality) {
		this.mutuality = mutuality;
	}

}
