package com.aikshika.entity;
// Generated Nov 23, 2016 7:07:07 PM by Hibernate Tools 4.3.1.Final

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
 * Msttimings generated by hbm2java
 */
@Entity
@Table(name = "msttimings", catalog = "dbschoolmanagementsystem")
public class Msttimings implements java.io.Serializable {

	private Integer intMsttimingsId;
	private String txtClassTimings;
	private Set<Mstteachertimetable> mstteachertimetables = new HashSet<Mstteachertimetable>(0);
	private Set<Mststudenttimetable> mststudenttimetables = new HashSet<Mststudenttimetable>(0);

	public Msttimings() {
	}

	public Msttimings(String txtClassTimings, Set<Mstteachertimetable> mstteachertimetables,
			Set<Mststudenttimetable> mststudenttimetables) {
		this.txtClassTimings = txtClassTimings;
		this.mstteachertimetables = mstteachertimetables;
		this.mststudenttimetables = mststudenttimetables;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intMsttimingsId", unique = true, nullable = false)
	public Integer getIntMsttimingsId() {
		return this.intMsttimingsId;
	}

	public void setIntMsttimingsId(Integer intMsttimingsId) {
		this.intMsttimingsId = intMsttimingsId;
	}  

	@Column(name = "txtClassTimings", length = 100)
	public String getTxtClassTimings() {
		return this.txtClassTimings;
	}

	public void setTxtClassTimings(String txtClassTimings) {
		this.txtClassTimings = txtClassTimings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "msttimings")
	public Set<Mstteachertimetable> getMstteachertimetables() {
		return this.mstteachertimetables;
	}

	public void setMstteachertimetables(Set<Mstteachertimetable> mstteachertimetables) {
		this.mstteachertimetables = mstteachertimetables;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "msttimings")
	public Set<Mststudenttimetable> getMststudenttimetables() {
		return this.mststudenttimetables;
	}

	public void setMststudenttimetables(Set<Mststudenttimetable> mststudenttimetables) {
		this.mststudenttimetables = mststudenttimetables;
	}

}
