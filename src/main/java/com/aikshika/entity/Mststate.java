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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mststate generated by hbm2java
 */
@Entity
@Table(name = "mststate", catalog = "dbschoolmanagementsystem")
public class Mststate implements java.io.Serializable {

	private Integer intStateId;
	private Mstcountry mstcountry;
	private String txtStateName;
	private Set<Mstcity> mstcities = new HashSet<Mstcity>(0);

	public Mststate() {
	}

	public Mststate(Mstcountry mstcountry, String txtStateName, Set<Mstcity> mstcities) {
		this.mstcountry = mstcountry;
		this.txtStateName = txtStateName;
		this.mstcities = mstcities;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intStateId", unique = true, nullable = false)
	public Integer getIntStateId() {
		return this.intStateId;
	}

	public void setIntStateId(Integer intStateId) {
		this.intStateId = intStateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intCountryId")
	public Mstcountry getMstcountry() {
		return this.mstcountry;
	}

	public void setMstcountry(Mstcountry mstcountry) {
		this.mstcountry = mstcountry;
	}

	@Column(name = "txtStateName", length = 50)
	public String getTxtStateName() {
		return this.txtStateName;
	}

	public void setTxtStateName(String txtStateName) {
		this.txtStateName = txtStateName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststate")
	public Set<Mstcity> getMstcities() {
		return this.mstcities;
	}

	public void setMstcities(Set<Mstcity> mstcities) {
		this.mstcities = mstcities;
	}

}
