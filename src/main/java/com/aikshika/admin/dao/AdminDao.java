package com.aikshika.admin.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aikshika.entity.FormEntity;
import com.aikshika.entity.Mstaffiliation;
import com.aikshika.entity.Mstbank;
import com.aikshika.entity.Mstbloodgroup;
import com.aikshika.entity.Mstcategory;
import com.aikshika.entity.Mstcity;
import com.aikshika.entity.Mstdisabilitychild;
import com.aikshika.entity.Mstgender;
import com.aikshika.entity.Mstleave;
import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstmaritalstatus;
import com.aikshika.entity.Mstmedium;
import com.aikshika.entity.Mstmothertongue;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mstreligion;
import com.aikshika.entity.Mstschool;
import com.aikshika.entity.Mstschoolconnect;
import com.aikshika.entity.Mstschooltype;
import com.aikshika.entity.Mstsemester;
import com.aikshika.entity.Mstsocialcategory;
import com.aikshika.entity.Mststream;
import com.aikshika.entity.Mststudclass;
import com.aikshika.entity.Mststudclasssection;
import com.aikshika.entity.Mststudenttimetable;
import com.aikshika.entity.Mstsubcategory;
import com.aikshika.entity.Mstsubject;
import com.aikshika.entity.Mstsyllabus;
import com.aikshika.entity.Mstteachercheckin;
import com.aikshika.entity.Mstteacherinvite;
import com.aikshika.entity.Mstteachertimetable;
import com.aikshika.entity.Mstterms;
import com.aikshika.entity.Msttimings;
import com.aikshika.entity.Mstuploadstudmarksheet;
import com.aikshika.entity.Tblbankname;
import com.aikshika.entity.Tblclassteacher;
import com.aikshika.entity.Tbldaysofweek;
import com.aikshika.entity.Tblinvites;
import com.aikshika.entity.Tblparentqueries;
import com.aikshika.entity.Tblrolename;
import com.aikshika.entity.Tblschoolandbranchdetail;
import com.aikshika.entity.Tbluserrole;
import com.aikshika.entity.Trnattendancerecord;
import com.aikshika.entity.Trndocumenttype;
import com.aikshika.entity.Trndocumentupload;
import com.aikshika.entity.Trnteacherleaveapp;

public interface AdminDao<T> {
	public List<Mstsemester> semester();

	// public List<Mstcity> city();

	public List<Mststream> stream();

	public List<Mstmothertongue> mothertongue();

	public List<Mstmedium> medium();

	public List<Mstaffiliation> affiliation();

	public List<Mstschooltype> schooltype();

	public List<Mstgender> gender();

	public List<Mstbloodgroup> bloodgroup();

	public List<Mstsocialcategory> socialcategory();

	public List<Mstdisabilitychild> disabilitychild();

	public List<Mstreligion> religion();

	// ----- master record start

	public List<Mstcategory> getCategory();

	public void deleteUser(int id);

	public List<Mstregistration> getAllStudentLists(int ClassId, int SectionId, String Id);

	public List<Mstregistration> getAllStaffLists(String Id);

	public void updateStudent(Mstregistration reg, int id);

	public Mstbank getBankDetails(int id);

	// ------ master record end
	// -----------user credentials start

	public List<Mstlogin> getUser();

	// -----------user credentials end

	// ------ school and branch detail

	public void addSandBDetails(Tblschoolandbranchdetail tbl);

	public List<Tblschoolandbranchdetail> getAllDetails();

	public void deleteSchool(int id);

	public void updateBranch(Tblschoolandbranchdetail tbl, int id);

	// ----------- school and branch detail

	// --------invites section

	public List<Mststudclass> getClasses(); // also used in fee management

	public List<Mststudclasssection> getSections();

	public List<Tblrolename> getRoles();

	public void delInvites(int id);

	// ------------end of invites

	// -----stu performance start
	public List<Mstterms> getTerms();

	// public List<Mstregistration> getAllStudentLists(int ClassId, int
	// SectionId );
	public List<Mstuploadstudmarksheet> getMarksList(int ClassId, int SectionId, int TermId, String StudentId);

	public Mstuploadstudmarksheet getMarkSheet(int id);
	// --------stu performance end

	/* start of school connect */

	public List<Mstschool> getUsers();

	public void adddetails(Mstschoolconnect tbl);

	public void addSchool(Mstschool mschool);

	public void delSchool(Integer id);

	public void updSchool(Mstschool mschool);
	/* void save(Mstschoolconnect tbl); */
	/* end of school connect */

	// syallbus
	public void addsyllabus(Mstsyllabus sub);

	public List<Mststudclass> getSyllabus();
	// attendance

	public List<Mststudclass> getClasseAd();

	public List<Mststudclasssection> getSection();
	// --------------Doc Upload for dao and service
	// interfaces------------------------

	public List<Trndocumenttype> getDocType();

	public List<Mstsubcategory> getSubCategories();

	public List<Mstcategory> getCategories();

	public void addDocUploadDetails(Trndocumentupload docUp);

	public List<Tblinvites> getInvObj(Integer id);

	// --------------Doc Upload end-----------------------------------
	// --------invites dao & service Interfaces

	// ------------end of invites

	public void addDocUploadDetails(Mstschoolconnect docUp);

	// time table
	public void addtimetableDetails(Mststudenttimetable mst);

	public void addtimetableDetails(Mstteachertimetable mstt);

	public List<Mstsubject> getSubject();

	public List<Tbldaysofweek> getdays();

	/*
	 * public List<Mststudenttimetable> getAllStudenttimetable(int ClassId, int
	 * SectionId);
	 * 
	 * 
	 * public List<Mstteachertimetable> getAllTeachertimetable(int c, int s);
	 * public void deleteTimetable(int id);
	 */
	public void addInvitesUpload(Tblinvites ti);

	public List<Trnattendancerecord> getStudAttend(int studId, java.util.Date curDt);

	public String getStuName(int studId);

	public List<Integer> getStudentList(int classId, int sectionId, int studId);

	public List<Trnattendancerecord> getStudentAttendFromTo(java.util.Date startDate, java.util.Date endDate, int id);

	public List<Integer> getStudentIds(int classId, int sectionId);

	public List<Trndocumentupload> getUploadDetails();

	public List<Tblinvites> getInviteDetails();

	public List<Trnteacherleaveapp> getLeaveReqFromTeacher();

	public List<Mstleave> getLeaveTypes();

	public List<Mstmaritalstatus> getMaritalStatus();

	public void saveTeachReg(Mstregistration regObj);

	public void saveTeachBank(Mstbank bankObj);

	public void letApprove(int nowId, java.util.Date curDt, int approvedStatusId);

	public Trndocumentupload uploadDocObj(Integer id);

	public Tblinvites invObj(Integer id);

	public int getRoleId(String stu);

	public List<Tblbankname> bankname();

	public void addRegister(Mstregistration reg);

	public void addBankDetails(Mstbank mstbank);

	public void addLoginDetails(Mstlogin mstlogin);

	public int getClassId(String string);

	public int getSectionId(String string);

	public List<Trnteacherleaveapp> getApprovedLeaves(java.util.Date fromDate, java.util.Date toDate, int status);

	public void letReject(int nowId, java.util.Date curDt, int approvedStatusId);

	// ---------------queries start

	public List<Tblparentqueries> pquestion();

	public void saveAnswer(String ans, int id);

	public Mstregistration checkParent(String fMobileLog, Integer regId);

	public void addUserRoleDetails(Tbluserrole userRoleParObj);

	public Mstregistration getParentObj(String fMobileLog, Integer regId);

	public List<Mstregistration> getTeacherRecords();

	public List<Mstteachercheckin> getTeacherCheckInList(java.util.Date curDt);

	public List<Mstteachertimetable> getAllTTForTeacher(int teachId, int dayOfWeek, java.util.Date nowDate);

	public Tbldaysofweek getDayOfWeekById(int dayOfWeek);
	// -----------------queries end

	public void addTimeTable2(Mststudenttimetable tT2);

	public Mstregistration getRegObjById(int teachId);

	public void addTimeTable2(Mstteachertimetable tT2);

	public int getParentLoginId(int regIdPar);

	public Mstlogin getLoginObj(int logInId);

	public Boolean checkParentIsThere(int regIdddd);

	public List<Mstteachertimetable> getTeachTimeTable(int teachId);

	public List<Tbldaysofweek> getDaysOfweek();

	public List<Mststudenttimetable> getStuTimeTable(int classIdInt, int sectionIdInt);

	public String getSectionName(String sectionStr);

	public ArrayList<java.util.Date> getAllDates(int teachId);

	public ArrayList<Msttimings> getTimings();

	public void updateTeachTT(Integer id);

	public void addNewRowTeachTT(Mstteachertimetable teachTTObj);

	public void updateStuTT(Integer id);

	public void addNewRowStuTT(Mststudenttimetable stuTTObj);

	public List<Mststudenttimetable> checkForTTRecord(int classInt, int section, int dayId, int timingId);

	public ArrayList<Mstteachertimetable> checkForTeachTTRecord(int teachId, int dayId, int timingId);

	public ArrayList<Trnteacherleaveapp> getApprovedLeaves(int i);

	public Boolean checkTeachIsThereOrNot(int teachId);

	// pie chart
	public List<Double> getStudentMonthlyAttendanceCount(int classId);

	public Mstsyllabus getSyllabusRow(Integer id);

	public void delSyllabus(Integer id);

	public ArrayList<Mstsyllabus> getSyllabusRecords();

	public Mstsubject getsubjectObj(String subjectStr);

	public Tbldaysofweek getDayObj(String dayStr);

	public Msttimings getTimeObj(String timeStr);

	public Mststudclass getClassObj(String classStr);

	public Mststudclasssection getSectionObj(String sectionStr);

	public ArrayList<Mstteacherinvite> getTeachInvites();

	public ArrayList<Mstteacherinvite> getAllTeachInvites(java.util.Date fromDate, java.util.Date toDate, int status);

	public ArrayList<Mstteacherinvite> getAllTeachInvites(int i);

	public void updateTeacherLeave(String msg, int id);

	public void appproveOrRejectTeacherInvite(int inviteId, int status, java.util.Date date);

	public ArrayList<ArrayList<Tblclassteacher>> getAllClassTeachersList(ArrayList<Mstregistration> teachList);

	public ArrayList<Tblclassteacher> getClassTeacherList(int teachId);

	public Tblclassteacher checkClassTeacherIsThereOrNot(int classId, int sectionId);

	public Mststudclass getClassObjById(int classId);

	public Mststudclasssection getSectionObjById(int sectionId);

	public void addClassTeacher(Tblclassteacher ctObj);

	public void removeClassTeacher(Tblclassteacher regObj);

}
