package com.aikshika.entity;
// Generated Oct 17, 2016 10:09:49 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mstfeemanagement generated by hbm2java
 */
@Entity
@Table(name = "mstfeemanagement", catalog = "dbschoolmanagementsystem")
public class Mstfeemanagement implements java.io.Serializable {

	private Integer intFeeId;
	private Mstregistration mstregistration;
	private Mststudclass mststudclass;
	private Mststudclasssection mststudclasssection;
	private String txtStatus;

	public Mstfeemanagement() {
	}

	public Mstfeemanagement(Mstregistration mstregistration, Mststudclass mststudclass,
			Mststudclasssection mststudclasssection, String txtStatus) {
		this.mstregistration = mstregistration;
		this.mststudclass = mststudclass;
		this.mststudclasssection = mststudclasssection;
		this.txtStatus = txtStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intFeeId", unique = true, nullable = false)
	public Integer getIntFeeId() {
		return this.intFeeId;
	}

	public void setIntFeeId(Integer intFeeId) {
		this.intFeeId = intFeeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intRegistrationId")
	public Mstregistration getMstregistration() {
		return this.mstregistration;
	}

	public void setMstregistration(Mstregistration mstregistration) {
		this.mstregistration = mstregistration;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intClassId")
	public Mststudclass getMststudclass() {
		return this.mststudclass;
	}

	public void setMststudclass(Mststudclass mststudclass) {
		this.mststudclass = mststudclass;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intSectionId")
	public Mststudclasssection getMststudclasssection() {
		return this.mststudclasssection;
	}

	public void setMststudclasssection(Mststudclasssection mststudclasssection) {
		this.mststudclasssection = mststudclasssection;
	}

	@Column(name = "txtStatus", length = 50)
	public String getTxtStatus() {
		return this.txtStatus;
	}

	public void setTxtStatus(String txtStatus) {
		this.txtStatus = txtStatus;
	}

}
