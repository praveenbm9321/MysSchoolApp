package com.aikshika.entity;
// Generated Oct 17, 2016 10:09:49 AM by Hibernate Tools 4.3.1.Final

import java.sql.Time;
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
 * Mstteachercheckin generated by hbm2java
 */
@Entity
@Table(name = "mstteachercheckin", catalog = "dbschoolmanagementsystem")
public class Mstteachercheckin implements java.io.Serializable {

	private Integer intTeacherCheckInId;
	private Mstteachertimetable mstteachertimetable;
	private Date dtCheckInDate;
	private String dtCheckInTime;

	public Mstteachercheckin() {
	}


	public Mstteachercheckin(Mstteachertimetable mstteachertimetable, Date dtCheckInDate,String dtCheckInTime) {
		this.mstteachertimetable = mstteachertimetable;
		this.dtCheckInDate = dtCheckInDate;
		this.dtCheckInTime = dtCheckInTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intTeacherCheckInId", unique = true, nullable = false)
	public Integer getIntTeacherCheckInId() {
		return this.intTeacherCheckInId;
	}

	public void setIntTeacherCheckInId(Integer intTeacherCheckInId) {
		this.intTeacherCheckInId = intTeacherCheckInId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intTeacherTimeTableId")
	public Mstteachertimetable getMstteachertimetable() {
		return this.mstteachertimetable;
	}

	public void setMstteachertimetable(Mstteachertimetable mstteachertimetable) {
		this.mstteachertimetable = mstteachertimetable;
	}

	

	@Temporal(TemporalType.DATE)
	@Column(name = "dtCheckInDate")
	public Date getDtCheckInDate() {
		return dtCheckInDate;
	}

	public void setDtCheckInDate(Date dtCheckInDate) {
		this.dtCheckInDate = dtCheckInDate;
	}

	@Column(name = "dtCheckInTime")
	public String getDtCheckInTime() {
		return dtCheckInTime;
	}

	public void setDtCheckInTime(String dtCheckInTime) {
		this.dtCheckInTime = dtCheckInTime;
	}
	

}
