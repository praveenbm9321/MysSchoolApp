package com.aikshika.entity;
// Generated Oct 17, 2016 10:09:49 AM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mstschool generated by hbm2java
 */
@Entity
@Table(name = "mstschool", catalog = "dbschoolmanagementsystem")
public class Mstschool implements java.io.Serializable {

	private Integer intSchoolId;
	private String txtSchoolName;
	private String txtEmail;
	private String txtSchoolAddress;
	private String txtSchoolContactNumber;
	private Set<Mstschoolconnect> mstschoolconnects = new HashSet<Mstschoolconnect>(0);

	public Mstschool() {
	}

	public Mstschool(String txtSchoolName, Set<Mstschoolconnect> mstschoolconnects,String txtEmail,String txtSchoolContactNumber,String txtSchoolAddress) {
		this.txtSchoolName = txtSchoolName;
		this.mstschoolconnects = mstschoolconnects;
		this.txtEmail = txtEmail;
		this.txtSchoolAddress=txtSchoolAddress;
		this.txtSchoolContactNumber=txtSchoolContactNumber;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intSchoolId", unique = true, nullable = false)
	public Integer getIntSchoolId() {
		return this.intSchoolId;
	}

	public void setIntSchoolId(Integer intSchoolId) {
		this.intSchoolId = intSchoolId;
	}

	@Column(name = "txtSchoolName", length = 50)
	public String getTxtSchoolName() {
		return this.txtSchoolName;
	}

	public void setTxtSchoolName(String txtSchoolName) {
		this.txtSchoolName = txtSchoolName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mstschool")
	public Set<Mstschoolconnect> getMstschoolconnects() {
		return this.mstschoolconnects;
	}

	public void setMstschoolconnects(Set<Mstschoolconnect> mstschoolconnects) {
		this.mstschoolconnects = mstschoolconnects;
	}
	@Column(name = "txtEmail", length = 100)
	public String getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	@Column(name = "txtSchoolAddress", length = 200)
	public String getTxtSchoolAddress() {
		return txtSchoolAddress;
	}

	public void setTxtSchoolAddress(String txtSchoolAddress) {
		this.txtSchoolAddress = txtSchoolAddress;
	}

	@Column(name = "txtSchoolContactNumber", length = 20)
	public String getTxtSchoolContactNumber() {
		return txtSchoolContactNumber;
	}

	public void setTxtSchoolContactNumber(String txtSchoolContactNumber) {
		this.txtSchoolContactNumber = txtSchoolContactNumber;
	}
	
	

}
