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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Trndocumenttype generated by hbm2java
 */
@Entity
@Table(name = "trndocumenttype", catalog = "dbschoolmanagementsystem")
public class Trndocumenttype implements java.io.Serializable {

	private Integer intDocumentTypeId;
	private String txtDocumentName;
	private Set<Trndocumentupload> trndocumentuploads = new HashSet<Trndocumentupload>(0);

	public Trndocumenttype() {
	}

	public Trndocumenttype(String txtDocumentName, Set<Trndocumentupload> trndocumentuploads) {
		this.txtDocumentName = txtDocumentName;
		this.trndocumentuploads = trndocumentuploads;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intDocumentTypeId", unique = true, nullable = false)
	public Integer getIntDocumentTypeId() {
		return this.intDocumentTypeId;
	}

	public void setIntDocumentTypeId(Integer intDocumentTypeId) {
		this.intDocumentTypeId = intDocumentTypeId;
	}

	@Column(name = "txtDocumentName", length = 50)
	public String getTxtDocumentName() {
		return this.txtDocumentName;
	}

	public void setTxtDocumentName(String txtDocumentName) {
		this.txtDocumentName = txtDocumentName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trndocumenttype")
	public Set<Trndocumentupload> getTrndocumentuploads() {
		return this.trndocumentuploads;
	}

	public void setTrndocumentuploads(Set<Trndocumentupload> trndocumentuploads) {
		this.trndocumentuploads = trndocumentuploads;
	}

}
