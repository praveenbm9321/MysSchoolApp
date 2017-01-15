package com.aikshika.entity;
// Generated Oct 17, 2016 10:09:49 AM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Trndocumentupload generated by hbm2java
 */
@Entity
@Table(name = "trndocumentupload", catalog = "dbschoolmanagementsystem")
public class Trndocumentupload implements java.io.Serializable {

	private Integer intDocumentUploadId;
	private Mstcategory mstcategory;
	private Mstregistration mstregistration;
	private Mstsubcategory mstsubcategory;
	private Trndocumenttype trndocumenttype;
	private byte[] blUploadFile;
	private Integer intStatus;
	private String txtDescription;
	private String txtfileName;
	private String txtcontentType;
	private Set<Mstdocumentsupload> mstdocumentsuploads = new HashSet<Mstdocumentsupload>(0);

	public Trndocumentupload() {
	}

	public Trndocumentupload(Mstcategory mstcategory, Mstregistration mstregistration, Mstsubcategory mstsubcategory,
			Trndocumenttype trndocumenttype, byte[] blUploadFile, Integer intStatus, String txtDescription,
			String txtfileName, String txtcontentType, Set<Mstdocumentsupload> mstdocumentsuploads) {
		this.mstcategory = mstcategory;
		this.mstregistration = mstregistration;
		this.mstsubcategory = mstsubcategory;
		this.trndocumenttype = trndocumenttype;
		this.blUploadFile = blUploadFile;
		this.intStatus = intStatus;
		this.txtDescription = txtDescription;
		this.txtfileName = txtfileName;
		this.txtcontentType = txtcontentType;
		this.mstdocumentsuploads = mstdocumentsuploads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intDocumentUploadId", unique = true, nullable = false)
	public Integer getIntDocumentUploadId() {
		return this.intDocumentUploadId;
	}

	public void setIntDocumentUploadId(Integer intDocumentUploadId) {
		this.intDocumentUploadId = intDocumentUploadId;
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
	@JoinColumn(name = "intRegistrationId")
	public Mstregistration getMstregistration() {
		return this.mstregistration;
	}

	public void setMstregistration(Mstregistration mstregistration) {
		this.mstregistration = mstregistration;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intSubCategoryId")
	public Mstsubcategory getMstsubcategory() {
		return this.mstsubcategory;
	}

	public void setMstsubcategory(Mstsubcategory mstsubcategory) {
		this.mstsubcategory = mstsubcategory;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "intDocumentTypeId")
	public Trndocumenttype getTrndocumenttype() {
		return this.trndocumenttype;
	}

	public void setTrndocumenttype(Trndocumenttype trndocumenttype) {
		this.trndocumenttype = trndocumenttype;
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

	@Column(name = "txtDescription", length = 500)
	public String getTxtDescription() {
		return this.txtDescription;
	}

	public void setTxtDescription(String txtDescription) {
		this.txtDescription = txtDescription;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "trndocumentupload")
	public Set<Mstdocumentsupload> getMstdocumentsuploads() {
		return this.mstdocumentsuploads;
	}

	public void setMstdocumentsuploads(Set<Mstdocumentsupload> mstdocumentsuploads) {
		this.mstdocumentsuploads = mstdocumentsuploads;
	}

}
