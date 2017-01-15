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

/**
 * Trnteacherleaveapp generated by hbm2java
 */
@Entity
@Table(name = "trnteacherleaveapp", catalog = "dbschoolmanagementsystem")
public class Trnteacherleaveapp implements java.io.Serializable {

	private Integer intTeacheLeaveAppId;
	private Mstleave mstleave;
	private Mstregistration mstregistration;
	private Date dtFromDate;
	private Date dtToDate;
	private String txtReason;
	private Date dtAppliedDate;
	private Date dtApprovedDate;
	private Integer intStatus;

	public Trnteacherleaveapp() {
	}

	public Trnteacherleaveapp(Mstleave mstleave, Mstregistration mstregistration, Date dtFromDate, Date dtToDate,
			String txtReason, Date dtAppliedDate, Date dtApprovedDate, Integer intStatus) {
		this.mstleave = mstleave;
		this.mstregistration = mstregistration;
		this.dtFromDate = dtFromDate;
		this.dtToDate = dtToDate;
		this.txtReason = txtReason;
		this.dtAppliedDate = dtAppliedDate;
		this.dtApprovedDate = dtApprovedDate;
		this.intStatus = intStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intTeacheLeaveAppId", unique = true, nullable = false)
	public Integer getIntTeacheLeaveAppId() {
		return this.intTeacheLeaveAppId;
	}

	public void setIntTeacheLeaveAppId(Integer intTeacheLeaveAppId) {
		this.intTeacheLeaveAppId = intTeacheLeaveAppId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intLeaveId")
	public Mstleave getMstleave() {
		return this.mstleave;
	}

	public void setMstleave(Mstleave mstleave) {
		this.mstleave = mstleave;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intRegistrationId")
	public Mstregistration getMstregistration() {
		return this.mstregistration;
	}

	public void setMstregistration(Mstregistration mstregistration) {
		this.mstregistration = mstregistration;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtFromDate", length = 10)
	public Date getDtFromDate() {
		return this.dtFromDate;
	}

	public void setDtFromDate(Date dtFromDate) {
		this.dtFromDate = dtFromDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtToDate", length = 10)
	public Date getDtToDate() {
		return this.dtToDate;
	}

	public void setDtToDate(Date dtToDate) {
		this.dtToDate = dtToDate;
	}

	@Column(name = "txtReason", length = 500)
	public String getTxtReason() {
		return this.txtReason;
	}

	public void setTxtReason(String txtReason) {
		this.txtReason = txtReason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtAppliedDate", length = 10)
	public Date getDtAppliedDate() {
		return this.dtAppliedDate;
	}

	public void setDtAppliedDate(Date dtAppliedDate) {
		this.dtAppliedDate = dtAppliedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtApprovedDate", length = 10)
	public Date getDtApprovedDate() {
		return this.dtApprovedDate;
	}

	public void setDtApprovedDate(Date dtApprovedDate) {
		this.dtApprovedDate = dtApprovedDate;
	}

	@Column(name = "intStatus")
	public Integer getIntStatus() {
		return this.intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}

}
