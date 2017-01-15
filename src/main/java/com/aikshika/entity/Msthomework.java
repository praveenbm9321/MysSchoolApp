package com.aikshika.entity;
// Generated Oct 17, 2016 10:09:49 AM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Msthomework generated by hbm2java
 */
@Entity
@Table(name = "msthomework", catalog = "dbschoolmanagementsystem")
public class Msthomework implements java.io.Serializable {

	private Integer intHomeWorkId;
	private String txtHomeWork;

	public Msthomework() {
	}

	public Msthomework(String txtHomeWork) {
		this.txtHomeWork = txtHomeWork;
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

	@Column(name = "txtHomeWork", length = 50)
	public String getTxtHomeWork() {
		return this.txtHomeWork;
	}

	public void setTxtHomeWork(String txtHomeWork) {
		this.txtHomeWork = txtHomeWork;
	}

}
