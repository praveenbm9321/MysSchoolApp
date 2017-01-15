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
 * Mststream generated by hbm2java
 */
@Entity
@Table(name = "mststream", catalog = "dbschoolmanagementsystem")
public class Mststream implements java.io.Serializable {

	private Integer intStreamId;
	private String txtStream;
	private Set<Mstregistration> mstregistrations = new HashSet<Mstregistration>(0);

	public Mststream() {
	}

	public Mststream(String txtStream, Set<Mstregistration> mstregistrations) {
		this.txtStream = txtStream;
		this.mstregistrations = mstregistrations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intStreamId", unique = true, nullable = false)
	public Integer getIntStreamId() {
		return this.intStreamId;
	}

	public void setIntStreamId(Integer intStreamId) {
		this.intStreamId = intStreamId;
	}

	@Column(name = "txtStream", length = 50)
	public String getTxtStream() {
		return this.txtStream;
	}

	public void setTxtStream(String txtStream) {
		this.txtStream = txtStream;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststream")
	public Set<Mstregistration> getMstregistrations() {
		return this.mstregistrations;
	}

	public void setMstregistrations(Set<Mstregistration> mstregistrations) {
		this.mstregistrations = mstregistrations;
	}

}
