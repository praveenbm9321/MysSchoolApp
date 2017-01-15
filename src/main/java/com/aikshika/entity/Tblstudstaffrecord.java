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
 * Tblstudstaffrecord generated by hbm2java
 */
@Entity
@Table(name = "tblstudstaffrecord", catalog = "dbschoolmanagementsystem")
public class Tblstudstaffrecord implements java.io.Serializable {

	private Integer intStudStaffRecordId;
	private Mstcategory mstcategory;
	private Mstlogin mstlogin;
	private Mststudclass mststudclass;
	private Mststudclasssection mststudclasssection;

	public Tblstudstaffrecord() {
	}

	public Tblstudstaffrecord(Mstcategory mstcategory, Mstlogin mstlogin, Mststudclass mststudclass,
			Mststudclasssection mststudclasssection) {
		this.mstcategory = mstcategory;
		this.mstlogin = mstlogin;
		this.mststudclass = mststudclass;
		this.mststudclasssection = mststudclasssection;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intStudStaffRecordId", unique = true, nullable = false)
	public Integer getIntStudStaffRecordId() {
		return this.intStudStaffRecordId;
	}

	public void setIntStudStaffRecordId(Integer intStudStaffRecordId) {
		this.intStudStaffRecordId = intStudStaffRecordId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intCategoryId1")
	public Mstcategory getMstcategory() {
		return this.mstcategory;
	}

	public void setMstcategory(Mstcategory mstcategory) {
		this.mstcategory = mstcategory;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intLoginId")
	public Mstlogin getMstlogin() {
		return this.mstlogin;
	}

	public void setMstlogin(Mstlogin mstlogin) {
		this.mstlogin = mstlogin;
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
