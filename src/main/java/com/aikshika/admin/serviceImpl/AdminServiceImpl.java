package com.aikshika.admin.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aikshika.admin.dao.AdminDao;
import com.aikshika.admin.service.AdminService;
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

@Service("adminService")
public class AdminServiceImpl<T> implements AdminService<T> {
	
   
	@Autowired
	AdminDao<T> adminDao;

	@Override
	public List<Mstsemester> semester() {
		// TODO Auto-generated method stub
		return adminDao.semester();
	}

	@Override
	public List<Mststream> stream() {
		// TODO Auto-generated method stub
		return adminDao.stream();
	}

	@Override
	public List<Mstmothertongue> mothertongue() {
		// TODO Auto-generated method stub
		return adminDao.mothertongue();
	}

	@Override
	public List<Mstmedium> medium() {
		// TODO Auto-generated method stub
		return adminDao.medium();
	}

	@Override
	public List<Mstaffiliation> affiliation() {
		// TODO Auto-generated method stub
		return adminDao.affiliation();
	}

	@Override
	public List<Mstschooltype> schooltype() {
		// TODO Auto-generated method stub
		return adminDao.schooltype();
	}

	@Override
	public List<Mstgender> gender() {
		// TODO Auto-generated method stub
		return adminDao.gender();
	}

	@Override
	public List<Mstbloodgroup> bloodgroup() {
		// TODO Auto-generated method stub
		return adminDao.bloodgroup();
	}

	@Override
	public List<Mstsocialcategory> socialcategory() {
		// TODO Auto-generated method stub
		return adminDao.socialcategory();
	}

	@Override
	public List<Mstdisabilitychild> disabilitychild() {
		// TODO Auto-generated method stub
		return adminDao.disabilitychild();
	}

	@Override
	public List<Mstreligion> religion() {
		// TODO Auto-generated method stub
		return adminDao.religion();
	}

	/*
	 * @Override public List<Mstcity> city() { return adminDao.city(); }
	 */
	// ---------school and branch details
	@Override
	public void addSandBDetails(Tblschoolandbranchdetail tbl) {
		adminDao.addSandBDetails(tbl);
	}

	@Override
	public List<Tblschoolandbranchdetail> getAllDetails() {
		// TODO Auto-generated method stub
		return adminDao.getAllDetails();
	}

	@Override
	public void deleteSchool(int id) {
		adminDao.deleteSchool(id);
	}

	@Override
	public void updateBranch(Tblschoolandbranchdetail tbl, int id) {
		adminDao.updateBranch(tbl, id);
	}

	/* end of school and branch */

	// ----- master record start

	public List<Mstcategory> getCategory() {
		return adminDao.getCategory();
	}

	@Override
	public void deleteUser(int id) {
		adminDao.deleteUser(id);

	}

	public List<Mstregistration> getAllStudentLists(int ClassId, int SectionId, String Id) {
		return adminDao.getAllStudentLists(ClassId, SectionId, Id);
	}

	public List<Mstregistration> getAllStaffLists(String Id) {
		return adminDao.getAllStaffLists(Id);
	}

	@Override
	public void updateStudent(Mstregistration reg, int id) {

		adminDao.updateStudent(reg, id);

	}

	@Override
	public Mstbank getBankDetails(int id) {
		return adminDao.getBankDetails(id);
	}

	// ------ master record end

	// -------------------user credentials start

	@Override
	public List<Mstlogin> getUser() {
		// TODO Auto-generated method stub
		return adminDao.getUser();
	}

	// ----------------------user credentials end

	@Override
	public List<Tblrolename> getRoles() {
		// TODO Auto-generated method stub
		return adminDao.getRoles();
	}

	// ---------- student performance

	@Override
	public List<Mststudclass> getClasses() {
		// TODO Auto-generated method stub
		return adminDao.getClasses();
	}

	@Override
	public List<Mststudclasssection> getSections() {
		// TODO Auto-generated method stub
		return adminDao.getSections();
	}

	@Override
	public List<Mstterms> getTerms() {
		// TODO Auto-generated method stub
		return adminDao.getTerms();
	}

	@Override
	public List<Mstuploadstudmarksheet> getMarksList(int ClassId, int SectionId, int TermId, String StudentId) {
		// TODO Auto-generated method stub
		return adminDao.getMarksList(ClassId, SectionId, TermId, StudentId);
	}

	@Override
	public Mstuploadstudmarksheet getMarkSheet(int id) {
		// TODO Auto-generated method stub
		return adminDao.getMarkSheet(id);
	}

	// ------------student performance end

	// ----------------------------------schools connect start

	@Override
	public void adddetails(Mstschoolconnect tbl) {
		// TODO Auto-generated method stub
		adminDao.adddetails(tbl);
	}

	@Override
	public List<Mstschool> getUsers() {
		// TODO Auto-generated method stub
		return adminDao.getUsers();

	}

	@Override
	public void addSchool(Mstschool mschool) {
		adminDao.addSchool(mschool);

	}

	@Override
	public void delSchool(Integer id) {
		System.out.println("inside service");
		adminDao.delSchool(id);

	}

	@Override
	public void updSchool(Mstschool mschool) {
		adminDao.updSchool(mschool);
	}
	// ----------------------------------schools connect end

	@Override
	public void addsyllabus(Mstsyllabus sub) {
		adminDao.addsyllabus(sub);
	}

	@Override
	public List<Mststudclass> getSyllabus() {
		return adminDao.getSyllabus();
	}

	@Override
	public List<Mststudclass> getClasseAd() {
		// TODO Auto-generated method stub
		return adminDao.getClasseAd();
	}

	@Override
	public List<Mststudclasssection> getSection() {
		// TODO Auto-generated method stub
		return adminDao.getSection();
	}
	// --------doc upload service impl----------------------

	@Override
	public List<Trndocumenttype> getDocType() {
		return adminDao.getDocType();
	}

	@Override
	public List<Mstsubcategory> getSubCategories() {
		return adminDao.getSubCategories();
	}

	@Override
	public List<Mstcategory> getCategories() {
		return adminDao.getCategories();
	}

	@Override
	public void addDocUploadDetails(Trndocumentupload docUp) {
		adminDao.addDocUploadDetails(docUp);
	}

	@Override
	public void addInvitesUpload(Tblinvites ti) {
		adminDao.addInvitesUpload(ti);

	}

	@Override
	public List<Trndocumentupload> getUploadDetails() {
		return adminDao.getUploadDetails();

	}

	// --------doc upload end----------------------
	// --------invites service impl start----------------------

	@Override
	public void delInvites(int id) {
		adminDao.delInvites(id);
	}

	// --------invites service impl end----------------------
	@Override
	public void addDocUploadDetails(Mstschoolconnect docUp) {
		// TODO Auto-generated method stub
		adminDao.addDocUploadDetails(docUp);
	}

	@Override
	public void addtimetableDetails(Mststudenttimetable mst) {
		// TODO Auto-generated method stub
		adminDao.addtimetableDetails(mst);
	}

	/*
	 * @Override public List<Mststudenttimetable> getAllStudenttimetable(int
	 * ClassId, int SectionId) { // TODO Auto-generated method stub return
	 * adminDao.getAllStudenttimetable(ClassId, SectionId); }
	 */

	@Override
	public List<Mstsubject> getSubject() {
		// TODO Auto-generated method stub
		return adminDao.getSubject();
	}

	@Override
	public List<Tbldaysofweek> getdays() {
		// TODO Auto-generated method stub
		return adminDao.getdays();
	}

	/*
	 * @Override public List<Mstteachertimetable> getAllTeachertimetable(int c,
	 * int s) { // TODO Auto-generated method stub return
	 * adminDao.getAllTeachertimetable(c, s); }
	 */

	@Override
	public void addtimetableDetails(Mstteachertimetable mstt) {
		// TODO Auto-generated method stub

		adminDao.addtimetableDetails(mstt);

	}

	/*
	 * @Override public String deleteTimetable(int id) {
	 * adminDao.deleteTimetable(id); return "table deleted succesfully";
	 * 
	 * }
	 */
	@Override
	public List<Trnattendancerecord> getStudAttend(int studId, Date curDt) {
		return adminDao.getStudAttend(studId, curDt);
	}

	@Override
	public String getStuName(int studId) {
		return adminDao.getStuName(studId);
	}

	@Override
	public List<Integer> getStudentList(int classId, int sectionId, int studId) {
		return adminDao.getStudentList(classId, sectionId, studId);
	}

	@Override
	public List<Trnattendancerecord> getStudentAttendFromTo(java.util.Date startDate, java.util.Date endDate, int id) {
		return adminDao.getStudentAttendFromTo(startDate, endDate, id);
	}

	@Override
	public List<Integer> getStudentIds(int classId, int sectionId) {
		// TODO Auto-generated method stub
		return adminDao.getStudentIds(classId, sectionId);
	}

	@Override
	public List<Tblinvites> getInviteDetails() {
		// TODO Auto-generated method stub
		return adminDao.getInviteDetails();
	}

	@Override
	public List<Tblinvites> getInvObj(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.getInvObj(id);
	}

	@Override
	public List<Trnteacherleaveapp> getLeaveReqFromTeacher() {
		// TODO Auto-generated method stub
		return adminDao.getLeaveReqFromTeacher();
	}

	@Override
	public List<Mstleave> getLeaveTypes() {
		// TODO Auto-generated method stub
		return adminDao.getLeaveTypes();
	}

	@Override
	public List<Mstmaritalstatus> getMaritalStatus() {
		// TODO Auto-generated method stub
		return adminDao.getMaritalStatus();
	}

	@Override
	public void saveTeachReg(Mstregistration regObj) {
		// TODO Auto-generated method stub
		adminDao.saveTeachReg(regObj);
	}

	@Override
	public void saveTeachBank(Mstbank bankObj) {
		// TODO Auto-generated method stub
		adminDao.saveTeachBank(bankObj);
	}

	@Override
	public void letApprove(int nowId, Date curDt, int approvedStatusId) {
		// TODO Auto-generated method stub
		adminDao.letApprove(nowId, curDt, approvedStatusId);
	}

	@Override
	public Trndocumentupload uploadDocObj(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.uploadDocObj(id);
	}

	@Override
	public Tblinvites invObj(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.invObj(id);
	}

	@Override
	public int getRoleId(String stu) {
		// TODO Auto-generated method stub
		return adminDao.getRoleId(stu);
	}

	@Override
	public List<Tblbankname> bankname() {
		// TODO Auto-generated method stub
		return adminDao.bankname();
	}

	@Override
	public void addRegister(Mstregistration reg) {
		// TODO Auto-generated method stub
		adminDao.addRegister(reg);
	}

	@Override
	public void addBankDetails(Mstbank mstbank) {
		// TODO Auto-generated method stub
		adminDao.addBankDetails(mstbank);
	}

	@Override
	public void addLoginDetails(Mstlogin mstlogin) {
		// TODO Auto-generated method stub
		adminDao.addLoginDetails(mstlogin);
	}

	@Override
	public int getClassId(String string) {
		// TODO Auto-generated method stub
		return adminDao.getClassId(string);
	}

	@Override
	public int getSectionId(String string) {
		// TODO Auto-generated method stub
		return adminDao.getSectionId(string);
	}

	@Override
	public List<Trnteacherleaveapp> getApprovedLeaves(Date fromDate, Date toDate, int status) {
		// TODO Auto-generated method stub
		return adminDao.getApprovedLeaves(fromDate, toDate, status);
	}

	@Override
	public void letReject(int nowId, Date curDt, int approvedStatusId) {
		// TODO Auto-generated method stub
		adminDao.letReject(nowId, curDt, approvedStatusId);
	}

	// --------Attendance end----------------------

	// --------invites end----------------------

	// ---------------------queries start

	@Override
	public List<Tblparentqueries> pquestion() {
		// TODO Auto-generated method stub
		return adminDao.pquestion();
	}

	@Override
	public void saveAnswer(String ans, int id) {
		adminDao.saveAnswer(ans, id);

	}

	@Override
	public Mstregistration checkParent(String fMobileLog, Integer regId) {
		// TODO Auto-generated method stub
		return adminDao.checkParent(fMobileLog, regId);
	}

	@Override
	public void addUserRoleDetails(Tbluserrole userRoleParObj) {
		// TODO Auto-generated method stub
		adminDao.addUserRoleDetails(userRoleParObj);
	}

	@Override
	public Mstregistration getParentObj(String fMobileLog, Integer regId) {
		// TODO Auto-generated method stub
		return adminDao.getParentObj(fMobileLog, regId);
	}

	@Override
	public List<Mstregistration> getTeacherRecords() {
		// TODO Auto-generated method stub
		return adminDao.getTeacherRecords();
	}

	@Override
	public List<Mstteachercheckin> getTeacherCheckInList(Date curDt) {
		// TODO Auto-generated method stub
		return adminDao.getTeacherCheckInList(curDt);
	}

	@Override
	public List<Mstteachertimetable> getAllTTForTeacher(int teachId, int dayOfWeek, Date nowDate) {
		// TODO Auto-generated method stub
		return adminDao.getAllTTForTeacher(teachId, dayOfWeek, nowDate);
	}

	@Override
	public Tbldaysofweek getDayOfWeekById(int dayOfWeek) {
		// TODO Auto-generated method stub
		return adminDao.getDayOfWeekById(dayOfWeek);
	}

	@Override
	public void addTimeTable2(Mststudenttimetable tT2) {
		// TODO Auto-generated method stub
		adminDao.addTimeTable2(tT2);
	}

	@Override
	public Mstregistration getRegObjById(int teachId) {
		// TODO Auto-generated method stub
		return adminDao.getRegObjById(teachId);
	}

	@Override
	public void addTimeTable2(Mstteachertimetable tT2) {
		// TODO Auto-generated method stub
		adminDao.addTimeTable2(tT2);
	}

	@Override
	public int getParentLoginId(int regIdPar) {
		// TODO Auto-generated method stub
		return adminDao.getParentLoginId(regIdPar);
	}

	@Override
	public Mstlogin getLoginObj(int logInId) {
		// TODO Auto-generated method stub
		return adminDao.getLoginObj(logInId);
	}

	@Override
	public Boolean checkParentIsThere(int regIdddd) {
		// TODO Auto-generated method stub
		return adminDao.checkParentIsThere(regIdddd);
	}

	@Override
	public List<Mstteachertimetable> getTeachTimeTable(int teachId) {
		// TODO Auto-generated method stub
		return adminDao.getTeachTimeTable(teachId);
	}

	@Override
	public List<Tbldaysofweek> getDaysOfweek() {
		// TODO Auto-generated method stub
		return adminDao.getDaysOfweek();
	}

	@Override
	public List<Mststudenttimetable> getStuTimeTable(int classIdInt, int sectionIdInt) {
		// TODO Auto-generated method stub
		return adminDao.getStuTimeTable(classIdInt, sectionIdInt);
	}

	@Override
	public String getSectionName(String sectionStr) {
		// TODO Auto-generated method stub
		return adminDao.getSectionName(sectionStr);
	}

	@Override
	public ArrayList<Date> getAllDates(int teachId) {
		// TODO Auto-generated method stub
		return adminDao.getAllDates(teachId);
	}

	@Override
	public ArrayList<Msttimings> getTimings() {
		// TODO Auto-generated method stub
		return adminDao.getTimings();
	}

	@Override
	public void updateTeachTT(Integer id) {
		// TODO Auto-generated method stub
		adminDao.updateTeachTT(id);
	}

	@Override
	public void addNewRowTeachTT(Mstteachertimetable teachTTObj) {
		// TODO Auto-generated method stub
		adminDao.addNewRowTeachTT(teachTTObj);
	}

	@Override
	public void updateStuTT(Integer id) {
		// TODO Auto-generated method stub
		adminDao.updateStuTT(id);
	}

	@Override
	public void addNewRowStuTT(Mststudenttimetable stuTTObj) {
		// TODO Auto-generated method stub
		adminDao.addNewRowStuTT(stuTTObj);
	}

	@Override
	public List<Mststudenttimetable> checkForTTRecord(int classInt, int section, int dayId, int timingId) {
		// TODO Auto-generated method stub
		return adminDao.checkForTTRecord(classInt, section, dayId, timingId);
	}

	@Override
	public ArrayList<Mstteachertimetable> checkForTeachTTRecord(int teachId, int dayId, int timingId) {
		// TODO Auto-generated method stub
		return adminDao.checkForTeachTTRecord(teachId, dayId, timingId);
	}

	@Override
	public ArrayList<Trnteacherleaveapp> getApprovedLeaves(int i) {
		// TODO Auto-generated method stub
		return adminDao.getApprovedLeaves(i);
	}

	@Override
	public Boolean checkTeachIsThereOrNot(int teachId) {
		// TODO Auto-generated method stub
		return adminDao.checkTeachIsThereOrNot(teachId);
	}

	// ---------------------queries end

	// pie chart
	public List<Double> getStudentMonthlyAttendanceCount(int classId) {

		return adminDao.getStudentMonthlyAttendanceCount(classId);
	}

	@Override
	public Mstsyllabus getSyllabusRow(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.getSyllabusRow(id);
	}

	@Override
	public void delSyllabus(Integer id) {
		// TODO Auto-generated method stub
		adminDao.delSyllabus(id);
	}

	@Override
	public ArrayList<Mstsyllabus> getSyllabusRecords() {
		// TODO Auto-generated method stub
		return adminDao.getSyllabusRecords();
	}

	@Override
	public Mstsubject getsubjectObj(String subjectStr) {
		// TODO Auto-generated method stub
		return adminDao.getsubjectObj(subjectStr);
	}

	@Override
	public Tbldaysofweek getDayObj(String dayStr) {
		// TODO Auto-generated method stub
		return adminDao.getDayObj(dayStr);
	}

	@Override
	public Msttimings getTimeObj(String timeStr) {
		// TODO Auto-generated method stub
		return adminDao.getTimeObj(timeStr);
	}

	@Override
	public Mststudclass getClassObj(String classStr) {
		// TODO Auto-generated method stub
		return adminDao.getClassObj(classStr);
	}

	@Override
	public Mststudclasssection getSectionObj(String sectionStr) {
		// TODO Auto-generated method stub
		return adminDao.getSectionObj(sectionStr);
	}

	@Override
	public ArrayList<Mstteacherinvite> getTeachInvites() {
		// TODO Auto-generated method stub
		return adminDao.getTeachInvites();
	}

	@Override
	public ArrayList<Mstteacherinvite> getAllTeachInvites(Date fromDate, Date toDate, int status) {
		// TODO Auto-generated method stub
		return adminDao.getAllTeachInvites(fromDate, toDate, status);
	}

	@Override
	public ArrayList<Mstteacherinvite> getAllTeachInvites(int i) {
		// TODO Auto-generated method stub
		return adminDao.getAllTeachInvites(i);
	}

	@Override
	public void updateTeacherLeave(String msg, int id) {

		adminDao.updateTeacherLeave(msg, id);

	}

	public void appproveOrRejectTeacherInvite(int inviteId, int status,java.util.Date date) {
     adminDao.appproveOrRejectTeacherInvite(inviteId, status, date);
	}

	@Override
	public ArrayList<ArrayList<Tblclassteacher>> getAllClassTeachersList(ArrayList<Mstregistration> teachList) {
		// TODO Auto-generated method stub
		return adminDao.getAllClassTeachersList(teachList);
	}

	@Override
	public ArrayList<Tblclassteacher> getClassTeacherList(int teachId) {
		// TODO Auto-generated method stub
		return adminDao.getClassTeacherList(teachId);
	}

	@Override
	public Tblclassteacher checkClassTeacherIsThereOrNot(int classId, int sectionId) {
		// TODO Auto-generated method stub
		return adminDao.checkClassTeacherIsThereOrNot(classId, sectionId);
	}

	@Override
	public Mststudclass getClassObjById(int classId) {
		// TODO Auto-generated method stub
		return adminDao.getClassObjById(classId);
	}

	@Override
	public Mststudclasssection getSectionObjById(int sectionId) {
		// TODO Auto-generated method stub
		return adminDao.getSectionObjById(sectionId);
	}

	@Override
	public void addClassTeacher(Tblclassteacher ctObj) {
		// TODO Auto-generated method stub
		adminDao.addClassTeacher(ctObj);
	}

	@Override
	public void removeClassTeacher(Tblclassteacher regObj) {
		// TODO Auto-generated method stub
		adminDao.removeClassTeacher(regObj);
	}

}
