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
 * Mstbloodgroup generated by hbm2java
 */
@Entity
@Table(name = "mstbloodgroup", catalog = "dbschoolmanagementsystem")
public class Mstbloodgroup implements java.io.Serializable {

	private Integer intBloodGroupId;
	private String txtBloodGroupName;
	private Set<Mstregistration> mstregistrations = new HashSet<Mstregistration>(0);

	public Mstbloodgroup() {
	}

	public Mstbloodgroup(String txtBloodGroupName, Set<Mstregistration> mstregistrations) {
		this.txtBloodGroupName = txtBloodGroupName;
		this.mstregistrations = mstregistrations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intBloodGroupId", unique = true, nullable = false)
	public Integer getIntBloodGroupId() {
		return this.intBloodGroupId;
	}

	public void setIntBloodGroupId(Integer intBloodGroupId) {
		this.intBloodGroupId = intBloodGroupId;
	}

	@Column(name = "txtBloodGroupName", length = 50)
	public String getTxtBloodGroupName() {
		return this.txtBloodGroupName;
	}

	public void setTxtBloodGroupName(String txtBloodGroupName) {
		this.txtBloodGroupName = txtBloodGroupName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mstbloodgroup")
	public Set<Mstregistration> getMstregistrations() {
		return this.mstregistrations;
	}

	public void setMstregistrations(Set<Mstregistration> mstregistrations) {
		this.mstregistrations = mstregistrations;
	}

}
