package com.aikshika.entity;
// Generated Oct 17, 2016 10:09:49 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Tblhomework generated by hbm2java
 */
@Entity
@Table(name = "tblhomework", catalog = "dbschoolmanagementsystem")
public class Tblhomework implements java.io.Serializable {

	private Integer intHomeWorkId;
	private Mstregistration mstregistration;
	private Mststudclass mststudclass;
	private Mststudclasssection mststudclasssection;
	private String txtSubject;
	private String txtHomeWork;
	private Date dtDueDate;
	private Date dtAssignDate;
	private Integer intTeacherId;

	public Tblhomework() {
	}

	public Tblhomework(Mstregistration mstregistration, Mststudclass mststudclass,
			Mststudclasssection mststudclasssection, String txtSubject, String txtHomeWork, Date dtDueDate,
			Date dtAssignDate,Integer intTeacherId) {
		this.mstregistration = mstregistration;
		this.mststudclass = mststudclass;
		this.mststudclasssection = mststudclasssection;
		this.txtSubject = txtSubject;
		this.txtHomeWork = txtHomeWork;
		this.dtDueDate = dtDueDate;
		this.dtAssignDate = dtAssignDate;
		this.intTeacherId = intTeacherId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intHomeWorkId", unique = true, nullable = false)
	public Integer getIntHomeWorkId() {
		return this.intHomeWorkId;
	}

	public void setIntHomeWorkId(Integer intHomeWorkId) {
		this.intHomeWorkId = intHomeWorkId;
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

	@Column(name = "txtSubject", length = 50)
	public String getTxtSubject() {
		return this.txtSubject;
	}

	public void setTxtSubject(String txtSubject) {
		this.txtSubject = txtSubject;
	}

	@Column(name = "txtHomeWork", length = 1000)
	public String getTxtHomeWork() {
		return this.txtHomeWork;
	}

	public void setTxtHomeWork(String txtHomeWork) {
		this.txtHomeWork = txtHomeWork;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtDueDate", length = 10)
	public Date getDtDueDate() {
		return this.dtDueDate;
	}

	public void setDtDueDate(Date dtDueDate) {
		this.dtDueDate = dtDueDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtAssignDate", length = 10)
	public Date getDtAssignDate() {
		return this.dtAssignDate;
	}

	public void setDtAssignDate(Date dtAssignDate) {
		this.dtAssignDate = dtAssignDate;
	}

	@Column(name = "intTeacherId", length = 11)
	public Integer getIntTeacherId() {
		return intTeacherId;
	}

	public void setIntTeacherId(Integer intTeacherId) {
		this.intTeacherId = intTeacherId;
	}
	
	

}