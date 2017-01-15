package com.aikshika.entity;
// Generated Dec 20, 2016 12:32:11 PM by Hibernate Tools 4.3.1.Final

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
 * Tblclassteacher generated by hbm2java
 */
@Entity
@Table(name = "tblclassteacher", catalog = "dbschoolmanagementsystem")
public class Tblclassteacher implements java.io.Serializable {

	private Integer intClassTeacherId;
	private Mstregistration mstregistration;
	private Mststudclass mststudclass;
	private Mststudclasssection mststudclasssection;

	public Tblclassteacher() {
	}

	public Tblclassteacher(Mstregistration mstregistration, Mststudclass mststudclass,
			Mststudclasssection mststudclasssection) {
		this.mstregistration = mstregistration;
		this.mststudclass = mststudclass;
		this.mststudclasssection = mststudclasssection;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intClassTeacherId", unique = true, nullable = false)
	public Integer getIntClassTeacherId() {
		return this.intClassTeacherId;
	}

	public void setIntClassTeacherId(Integer intClassTeacherId) {
		this.intClassTeacherId = intClassTeacherId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intRegistrationId")
	public Mstregistration getMstregistration() {
		return this.mstregistration;
	}

	public void setMstregistration(Mstregistration mstregistration) {
		this.mstregistration = mstregistration;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intClassId")
	public Mststudclass getMststudclass() {
		return this.mststudclass;
	}

	public void setMststudclass(Mststudclass mststudclass) {
		this.mststudclass = mststudclass;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intSectionId")
	public Mststudclasssection getMststudclasssection() {
		return this.mststudclasssection;
	}

	public void setMststudclasssection(Mststudclasssection mststudclasssection) {
		this.mststudclasssection = mststudclasssection;
	}

}