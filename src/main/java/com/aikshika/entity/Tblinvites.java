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
 * Tblinvites generated by hbm2java
 */
@Entity
@Table(name = "tblinvites", catalog = "dbschoolmanagementsystem")
public class Tblinvites implements java.io.Serializable {

	private Integer intInvitesId;
	private Mststudclass mststudclass;
	private Mststudclasssection mststudclasssection;
	private Tblrolename tblrolename;
	private String txtInvitationTitle;
	private String txtBody;
	private byte[] blUploadFile;
	private Integer intStatus;
	private Date dtDate;
	private String txtfileName;
	private String txtcontentType;
	private String txtPath;

	public Tblinvites() {
	}

	public Tblinvites(Mststudclass mststudclass, Mststudclasssection mststudclasssection, Tblrolename tblrolename,
			String txtInvitationTitle, String txtBody, byte[] blUploadFile, Integer intStatus, Date dtDate,
			String txtfileName, String txtcontentType, String txtPath) {
		this.mststudclass = mststudclass;
		this.mststudclasssection = mststudclasssection;
		this.tblrolename = tblrolename;
		this.txtInvitationTitle = txtInvitationTitle;
		this.txtBody = txtBody;
		this.blUploadFile = blUploadFile;
		this.intStatus = intStatus;
		this.dtDate = dtDate;
		this.txtfileName = txtfileName;
		this.txtcontentType = txtcontentType;
		this.txtPath = txtPath;
	}

	@Column(name = "txtPath", length = 500)
	public String getTxtPath() {
		return txtPath;
	}

	public void setTxtPath(String txtPath) {
		this.txtPath = txtPath;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intInvitesId", unique = true, nullable = false)
	public Integer getIntInvitesId() {
		return this.intInvitesId;
	}

	public void setIntInvitesId(Integer intInvitesId) {
		this.intInvitesId = intInvitesId;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intRoleId")
	public Tblrolename getTblrolename() {
		return this.tblrolename;
	}

	public void setTblrolename(Tblrolename tblrolename) {
		this.tblrolename = tblrolename;
	}

	@Column(name = "txtInvitationTitle", length = 100)
	public String getTxtInvitationTitle() {
		return this.txtInvitationTitle;
	}

	public void setTxtInvitationTitle(String txtInvitationTitle) {
		this.txtInvitationTitle = txtInvitationTitle;
	}

	@Column(name = "txtBody", length = 1000)
	public String getTxtBody() {
		return this.txtBody;
	}

	public void setTxtBody(String txtBody) {
		this.txtBody = txtBody;
	}

	@Column(name = "blUploadFile")
	public byte[] getBlUploadFile() {
		return this.blUploadFile;
	}

	public void setBlUploadFile(byte[] blUploadFile) {
		this.blUploadFile = blUploadFile;
	}

	@Column(name = "intStatus")
	public Integer getIntStatus() {
		return this.intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dtDate", length = 10)
	public Date getDtDate() {
		return this.dtDate;
	}

	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}

	@Column(name = "txtfileName", length = 100)
	public String getTxtfileName() {
		return this.txtfileName;
	}

	public void setTxtfileName(String txtfileName) {
		this.txtfileName = txtfileName;
	}

	@Column(name = "txtcontentType", length = 200)
	public String getTxtcontentType() {
		return this.txtcontentType;
	}

	public void setTxtcontentType(String txtcontentType) {
		this.txtcontentType = txtcontentType;
	}

}
