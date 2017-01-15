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
 * Mstreligion generated by hbm2java
 */
@Entity
@Table(name = "mstreligion", catalog = "dbschoolmanagementsystem")
public class Mstreligion implements java.io.Serializable {

	private Integer intReligionId;
	private String txtReligionName;
	private Set<Mstregistration> mstregistrations = new HashSet<Mstregistration>(0);

	public Mstreligion() {
	}

	public Mstreligion(String txtReligionName, Set<Mstregistration> mstregistrations) {
		this.txtReligionName = txtReligionName;
		this.mstregistrations = mstregistrations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intReligionId", unique = true, nullable = false)
	public Integer getIntReligionId() {
		return this.intReligionId;
	}

	public void setIntReligionId(Integer intReligionId) {
		this.intReligionId = intReligionId;
	}

	@Column(name = "txtReligionName", length = 50)
	public String getTxtReligionName() {
		return this.txtReligionName;
	}

	public void setTxtReligionName(String txtReligionName) {
		this.txtReligionName = txtReligionName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mstreligion")
	public Set<Mstregistration> getMstregistrations() {
		return this.mstregistrations;
	}

	public void setMstregistrations(Set<Mstregistration> mstregistrations) {
		this.mstregistrations = mstregistrations;
	}

}
