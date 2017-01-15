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
 * Mstgender generated by hbm2java
 */
@Entity
@Table(name = "mstgender", catalog = "dbschoolmanagementsystem")
public class Mstgender implements java.io.Serializable {

	private Integer intGenderId;
	private String txtGenderName;
	private Set<Mstrelation> mstrelations = new HashSet<Mstrelation>(0);
	private Set<Mstregistration> mstregistrations = new HashSet<Mstregistration>(0);

	public Mstgender() {
	}

	public Mstgender(String txtGenderName, Set<Mstrelation> mstrelations, Set<Mstregistration> mstregistrations) {
		this.txtGenderName = txtGenderName;
		this.mstrelations = mstrelations;
		this.mstregistrations = mstregistrations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intGenderId", unique = true, nullable = false)
	public Integer getIntGenderId() {
		return this.intGenderId;
	}

	public void setIntGenderId(Integer intGenderId) {
		this.intGenderId = intGenderId;
	}

	@Column(name = "txtGenderName", length = 50)
	public String getTxtGenderName() {
		return this.txtGenderName;
	}

	public void setTxtGenderName(String txtGenderName) {
		this.txtGenderName = txtGenderName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mstgender")
	public Set<Mstrelation> getMstrelations() {
		return this.mstrelations;
	}

	public void setMstrelations(Set<Mstrelation> mstrelations) {
		this.mstrelations = mstrelations;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mstgender")
	public Set<Mstregistration> getMstregistrations() {
		return this.mstregistrations;
	}

	public void setMstregistrations(Set<Mstregistration> mstregistrations) {
		this.mstregistrations = mstregistrations;
	}

}
