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
 * Mstcity generated by hbm2java
 */
@Entity
@Table(name = "mstcity", catalog = "dbschoolmanagementsystem")
public class Mstcity implements java.io.Serializable {

	private Integer intCityId;
	private Mststate mststate;
	private String txtCityName;
	//private Set<Mstregistration> mstregistrations = new HashSet<Mstregistration>(0);

	public Mstcity() {
	}

	public Mstcity(Mststate mststate, String txtCityName/*, Set<Mstregistration> mstregistrations*/) {
		this.mststate = mststate;
		this.txtCityName = txtCityName;
		//this.mstregistrations = mstregistrations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intCityId", unique = true, nullable = false)
	public Integer getIntCityId() {
		return this.intCityId;
	}

	public void setIntCityId(Integer intCityId) {
		this.intCityId = intCityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intStateId")
	public Mststate getMststate() {
		return this.mststate;
	}

	public void setMststate(Mststate mststate) {
		this.mststate = mststate;
	}

	@Column(name = "txtCityName", length = 50)
	public String getTxtCityName() {
		return this.txtCityName;
	}

	public void setTxtCityName(String txtCityName) {
		this.txtCityName = txtCityName;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "mstcity")
	public Set<Mstregistration> getMstregistrations() {
		return this.mstregistrations;
	}

	public void setMstregistrations(Set<Mstregistration> mstregistrations) {
		this.mstregistrations = mstregistrations;
	}*/

}
