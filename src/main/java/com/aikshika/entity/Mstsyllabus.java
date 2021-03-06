package com.aikshika.entity;
// Generated Dec 6, 2016 4:52:30 PM by Hibernate Tools 4.3.1.Final

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
 * Mstsyllabus generated by hbm2java
 */
@Entity
@Table(name = "mstsyllabus", catalog = "dbschoolmanagementsystem")
public class Mstsyllabus implements java.io.Serializable {

	private Integer intSubjectId;
	private Mststudclass mststudclass;
	private String txtSubjectName;
	private byte[] blAttachment;
	private String txtFileName;
	private String txtContentType;

	public Mstsyllabus() {
	}

	public Mstsyllabus(Mststudclass mststudclass, String txtSubjectName, byte[] blAttachment,String txtFileName, String txtContentType) {
		this.mststudclass = mststudclass;
		this.txtSubjectName = txtSubjectName;
		this.blAttachment = blAttachment;
		this.txtFileName = txtFileName;
		this.txtContentType = txtContentType;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intSubjectId", unique = true, nullable = false)
	public Integer getIntSubjectId() {
		return this.intSubjectId;
	}

	public void setIntSubjectId(Integer intSubjectId) {
		this.intSubjectId = intSubjectId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "intClassId")
	public Mststudclass getMststudclass() {
		return this.mststudclass;
	}

	public void setMststudclass(Mststudclass mststudclass) {
		this.mststudclass = mststudclass;
	}

	@Column(name = "txtSubjectName", length = 50)
	public String getTxtSubjectName() {
		return this.txtSubjectName;
	}

	public void setTxtSubjectName(String txtSubjectName) {
		this.txtSubjectName = txtSubjectName;
	}

	@Column(name = "blAttachment")
	public byte[] getBlAttachment() {
		return this.blAttachment;
	}

	public void setBlAttachment(byte[] blAttachment) {
		this.blAttachment = blAttachment;
	}

	@Column(name = "txtFileName")
	public String getTxtFileName() {
		return txtFileName;
	}

	public void setTxtFileName(String txtFileName) {
		this.txtFileName = txtFileName;
	}
	@Column(name = "txtContentType")
	public String getTxtContentType() {
		return txtContentType;
	}

	public void setTxtContentType(String txtContentType) {
		this.txtContentType = txtContentType;
	}

	
}
