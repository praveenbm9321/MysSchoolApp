package com.aikshika.entity;
// Generated Dec 20, 2016 12:32:11 PM by Hibernate Tools 4.3.1.Final

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
 * Mststudclass generated by hbm2java
 */
@Entity
@Table(name = "mststudclass", catalog = "dbschoolmanagementsystem")
public class Mststudclass implements java.io.Serializable {

	private Integer intClassId;
	private String txtClassName;
	private Integer intStatus;
	private Set<Mststudclassgroup> mststudclassgroups = new HashSet<Mststudclassgroup>(0);
	private Set<Mstuploadstudmarksheet> mstuploadstudmarksheets = new HashSet<Mstuploadstudmarksheet>(0);
	private Set<Mstregistration> mstregistrations = new HashSet<Mstregistration>(0);
	private Set<Tblhomework> tblhomeworks = new HashSet<Tblhomework>(0);
	private Set<Tblinvites> tblinviteses = new HashSet<Tblinvites>(0);
	private Set<Mstsubject> mstsubjects = new HashSet<Mstsubject>(0);
	private Set<Mstteachertimetable> mstteachertimetables = new HashSet<Mstteachertimetable>(0);
	private Set<Tblclassteacher> tblclassteachers = new HashSet<Tblclassteacher>(0);
	private Set<Mstquestionandanswer> mstquestionandanswers = new HashSet<Mstquestionandanswer>(0);
	private Set<Tblstudentperformance> tblstudentperformances = new HashSet<Tblstudentperformance>(0);
	private Set<Mststudclasssection> mststudclasssections = new HashSet<Mststudclasssection>(0);
	private Set<Mstteacherinvite> mstteacherinvites = new HashSet<Mstteacherinvite>(0);
	private Set<Mstsyllabus> mstsyllabuses = new HashSet<Mstsyllabus>(0);
	private Set<Mstteacherproject> mstteacherprojects = new HashSet<Mstteacherproject>(0);
	private Set<Mststudenttimetable> mststudenttimetables = new HashSet<Mststudenttimetable>(0);
	private Set<Tblstudstaffrecord> tblstudstaffrecords = new HashSet<Tblstudstaffrecord>(0);
	private Set<Mstfeemanagement> mstfeemanagements = new HashSet<Mstfeemanagement>(0);

	public Mststudclass() {
	}

	public Mststudclass(String txtClassName, Integer intStatus, Set<Mststudclassgroup> mststudclassgroups,
			Set<Mstuploadstudmarksheet> mstuploadstudmarksheets, Set<Mstregistration> mstregistrations,
			Set<Tblhomework> tblhomeworks, Set<Tblinvites> tblinviteses, Set<Mstsubject> mstsubjects,
			Set<Mstteachertimetable> mstteachertimetables, Set<Tblclassteacher> tblclassteachers,
			Set<Mstquestionandanswer> mstquestionandanswers, Set<Tblstudentperformance> tblstudentperformances,
			Set<Mststudclasssection> mststudclasssections, Set<Mstteacherinvite> mstteacherinvites,
			Set<Mstsyllabus> mstsyllabuses, Set<Mstteacherproject> mstteacherprojects,
			Set<Mststudenttimetable> mststudenttimetables, Set<Tblstudstaffrecord> tblstudstaffrecords,
			Set<Mstfeemanagement> mstfeemanagements) {
		this.txtClassName = txtClassName;
		this.intStatus = intStatus;
		this.mststudclassgroups = mststudclassgroups;
		this.mstuploadstudmarksheets = mstuploadstudmarksheets;
		this.mstregistrations = mstregistrations;
		this.tblhomeworks = tblhomeworks;
		this.tblinviteses = tblinviteses;
		this.mstsubjects = mstsubjects;
		this.mstteachertimetables = mstteachertimetables;
		this.tblclassteachers = tblclassteachers;
		this.mstquestionandanswers = mstquestionandanswers;
		this.tblstudentperformances = tblstudentperformances;
		this.mststudclasssections = mststudclasssections;
		this.mstteacherinvites = mstteacherinvites;
		this.mstsyllabuses = mstsyllabuses;
		this.mstteacherprojects = mstteacherprojects;
		this.mststudenttimetables = mststudenttimetables;
		this.tblstudstaffrecords = tblstudstaffrecords;
		this.mstfeemanagements = mstfeemanagements;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "intClassId", unique = true, nullable = false)
	public Integer getIntClassId() {
		return this.intClassId;
	}

	public void setIntClassId(Integer intClassId) {
		this.intClassId = intClassId;
	}

	@Column(name = "txtClassName", length = 50)
	public String getTxtClassName() {
		return this.txtClassName;
	}

	public void setTxtClassName(String txtClassName) {
		this.txtClassName = txtClassName;
	}

	@Column(name = "intStatus")
	public Integer getIntStatus() {
		return this.intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mststudclassgroup> getMststudclassgroups() {
		return this.mststudclassgroups;
	}

	public void setMststudclassgroups(Set<Mststudclassgroup> mststudclassgroups) {
		this.mststudclassgroups = mststudclassgroups;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstuploadstudmarksheet> getMstuploadstudmarksheets() {
		return this.mstuploadstudmarksheets;
	}

	public void setMstuploadstudmarksheets(Set<Mstuploadstudmarksheet> mstuploadstudmarksheets) {
		this.mstuploadstudmarksheets = mstuploadstudmarksheets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstregistration> getMstregistrations() {
		return this.mstregistrations;
	}

	public void setMstregistrations(Set<Mstregistration> mstregistrations) {
		this.mstregistrations = mstregistrations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Tblhomework> getTblhomeworks() {
		return this.tblhomeworks;
	}

	public void setTblhomeworks(Set<Tblhomework> tblhomeworks) {
		this.tblhomeworks = tblhomeworks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Tblinvites> getTblinviteses() {
		return this.tblinviteses;
	}

	public void setTblinviteses(Set<Tblinvites> tblinviteses) {
		this.tblinviteses = tblinviteses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstsubject> getMstsubjects() {
		return this.mstsubjects;
	}

	public void setMstsubjects(Set<Mstsubject> mstsubjects) {
		this.mstsubjects = mstsubjects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstteachertimetable> getMstteachertimetables() {
		return this.mstteachertimetables;
	}

	public void setMstteachertimetables(Set<Mstteachertimetable> mstteachertimetables) {
		this.mstteachertimetables = mstteachertimetables;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Tblclassteacher> getTblclassteachers() {
		return this.tblclassteachers;
	}

	public void setTblclassteachers(Set<Tblclassteacher> tblclassteachers) {
		this.tblclassteachers = tblclassteachers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstquestionandanswer> getMstquestionandanswers() {
		return this.mstquestionandanswers;
	}

	public void setMstquestionandanswers(Set<Mstquestionandanswer> mstquestionandanswers) {
		this.mstquestionandanswers = mstquestionandanswers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Tblstudentperformance> getTblstudentperformances() {
		return this.tblstudentperformances;
	}

	public void setTblstudentperformances(Set<Tblstudentperformance> tblstudentperformances) {
		this.tblstudentperformances = tblstudentperformances;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mststudclasssection> getMststudclasssections() {
		return this.mststudclasssections;
	}

	public void setMststudclasssections(Set<Mststudclasssection> mststudclasssections) {
		this.mststudclasssections = mststudclasssections;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstteacherinvite> getMstteacherinvites() {
		return this.mstteacherinvites;
	}

	public void setMstteacherinvites(Set<Mstteacherinvite> mstteacherinvites) {
		this.mstteacherinvites = mstteacherinvites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstsyllabus> getMstsyllabuses() {
		return this.mstsyllabuses;
	}

	public void setMstsyllabuses(Set<Mstsyllabus> mstsyllabuses) {
		this.mstsyllabuses = mstsyllabuses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstteacherproject> getMstteacherprojects() {
		return this.mstteacherprojects;
	}

	public void setMstteacherprojects(Set<Mstteacherproject> mstteacherprojects) {
		this.mstteacherprojects = mstteacherprojects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mststudenttimetable> getMststudenttimetables() {
		return this.mststudenttimetables;
	}

	public void setMststudenttimetables(Set<Mststudenttimetable> mststudenttimetables) {
		this.mststudenttimetables = mststudenttimetables;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Tblstudstaffrecord> getTblstudstaffrecords() {
		return this.tblstudstaffrecords;
	}

	public void setTblstudstaffrecords(Set<Tblstudstaffrecord> tblstudstaffrecords) {
		this.tblstudstaffrecords = tblstudstaffrecords;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mststudclass")
	public Set<Mstfeemanagement> getMstfeemanagements() {
		return this.mstfeemanagements;
	}

	public void setMstfeemanagements(Set<Mstfeemanagement> mstfeemanagements) {
		this.mstfeemanagements = mstfeemanagements;
	}

}
