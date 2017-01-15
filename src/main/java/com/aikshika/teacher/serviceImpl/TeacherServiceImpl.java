package com.aikshika.teacher.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aikshika.entity.Mstleave;
import com.aikshika.entity.Mstquestionandanswer;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mststudclass;
import com.aikshika.entity.Mststudclasssection;
import com.aikshika.entity.Mstteachercheckin;
import com.aikshika.entity.Mstteacherinvite;
import com.aikshika.entity.Mstteacherproject;
import com.aikshika.entity.Mstteachertimetable;
import com.aikshika.entity.Mstterms;
import com.aikshika.entity.Mstuploadstudmarksheet;
import com.aikshika.entity.Tblhomework;
import com.aikshika.entity.Tblinvites;
import com.aikshika.entity.Trnstudentleaveapp;
import com.aikshika.entity.Trnteacherleaveapp;
import com.aikshika.teacher.dao.TeacherDao;
import com.aikshika.teacher.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	public TeacherDao teacherDao;

	// this method is for fetching the profile details of a teacher
	@Transactional
	public List<Mstregistration> Profile(int registrationId) {

		return teacherDao.Profile(registrationId);
	}

	// this method is to update the teacher profile
	@Transactional
	public void update(Mstregistration mstregistration) {
		teacherDao.update(mstregistration);
	}

	// this method is to fetch all the classes
	@Transactional
	public List<Mststudclass> getAllClasses() {

		return teacherDao.getAllClasses();
	}

	// this method is to fetch the section
	@Transactional
	public List<Mststudclasssection> getAllSections() {
		return teacherDao.getAllSections();
	}

	// this method is used to post the homework
	@Transactional
	public void homeworkPost(Tblhomework tblhomework) {
		teacherDao.homeworkPost(tblhomework);

	}

	// this method is used to display the history of posted homework's
	@Transactional
	public List<Tblhomework> getHomeworkHistory(int registrationId, Date fromDate, Date toDate) {
		return teacherDao.getHomeworkHistory(registrationId, fromDate, toDate);
	}

	// this method is used to post the project
	@Transactional
	public void projectPost(Mstteacherproject mstteacherproject) {
		teacherDao.projectPost(mstteacherproject);
	}

	// this method is used to display the history of posted project's
	@Transactional
	public List<Mstteacherproject> getProjectHistory(int registrationId, Date fromDate, Date toDate) {
		return teacherDao.getProjectHistory(registrationId, fromDate, toDate);
	}

	// this method is to fetch the leave type for teacher
	@Transactional
	public List<Mstleave> getAllLeaveTypes() {
		return teacherDao.getAllLeaveTypes();
	}

	// this method is used to apply for leave
	@Transactional
	public void applyLeave(Trnteacherleaveapp trnteacherleaveapp) {
		teacherDao.applyLeave(trnteacherleaveapp);

	}

	// this method is used to fetch the types of exam terms
	@Transactional
	public List<Mstterms> getExamTerms() {
		return teacherDao.getExamTerms();
	}

	@Transactional
	// this method is used to fetch the timetable of the teacher
	public List<Mstteachertimetable> getTimetable(int registrationId) {

		return teacherDao.getTimetable(registrationId);
	}

	@Transactional
	// this method records the class check in time of the teacher
	public void recordCheckInTime(Mstteachercheckin mstteachercheckin) {
		teacherDao.recordCheckInTime(mstteachercheckin);
	}

	@Transactional
	// this method is used to display class check-in time history
	public List<Mstteachercheckin> getClassCheckInTimeHistory(int registrationId, Date fromDate, Date toDate) {
		return teacherDao.getClassCheckInTimeHistory(registrationId, fromDate, toDate);
	}

	@Transactional
	//this method is used to get the nofitication sent by admin to teacher
	public List<Tblinvites> getTeacherNotification(int roleId) {
		
		return teacherDao.getTeacherNotification(roleId);
	}

	@Transactional
	//this method is used to post the invite to the entire class
	public void postTeacherInvite(Mstteacherinvite mstteacherinvite) {
		teacherDao.postTeacherInvite(mstteacherinvite);
		
	}
	
	@Transactional
	//this method is to get the posted invites
		public List<Mstteacherinvite> getPostedInvites(int id)
		{
		return teacherDao.getPostedInvites(id);
		}
	
	@Transactional
	// this method is used to display the history of posted invite's
		public List<Mstteacherinvite> getInviteHistory(int registrationId, Date fromDate, Date toDate)
		{
			return teacherDao.getInviteHistory(registrationId, fromDate, toDate);
		}
	
	@Transactional
	//this method is used to delete the invite sent by teacher
		public void deleteTeacherInvite(int id)
		{
			teacherDao.deleteTeacherInvite(id);
		}

	@Transactional
	//this method is to upload marksheet of students
	public void uploadStudentMarksheet(Mstuploadstudmarksheet mstuploadstudmarksheet) {
		teacherDao.uploadStudentMarksheet(mstuploadstudmarksheet);
	}

	@Transactional
	//this method is used to fetch teacher leave inbox
	public List<Trnteacherleaveapp> getTeacherLeaveInbox(int registrationId) {
		return teacherDao.getTeacherLeaveInbox(registrationId);
	}

	@Transactional
	//this method is used to fetch student leave request sent by parents
	public List<Trnstudentleaveapp> getStudentLeaveRequest(int id) {
		return teacherDao.getStudentLeaveRequest(id);
	}

	@Transactional
	//this method is used to reject/approve leave
	public void approveOrRejectStudentLeave(int id, int status, Date appOrRejDate) {
		teacherDao.approveOrRejectStudentLeave(id, status, appOrRejDate);
		
	}

	@Transactional
	//this method is used to get all the searched leave request by date
	public List<Trnstudentleaveapp> getAllSearchedLeaveReq(int id,Date fromDate, Date toDate) {
		return teacherDao.getAllSearchedLeaveReq(id, fromDate, toDate);
	}

	@Transactional
	public List<Trnstudentleaveapp> getSearchedLeaveReq(int id,int status, Date fromDate, Date toDate) {
		
		return teacherDao.getSearchedLeaveReq(id, status, fromDate, toDate);
	}

	@Transactional
	//this method is used to fetch the questions posted by students
	public List<Mstquestionandanswer> getStudentQuestions() {
		return teacherDao.getStudentQuestions();
	}

	@Transactional
	//this method is used to answer the question 
	public void postAnswer(int questionId, String answer, int teacherId) {
		teacherDao.postAnswer(questionId, answer, teacherId);
	}

	@Transactional
	//this method is used to get the answered questions
		public List<Mstquestionandanswer> getAnsweredQuestion(int teacherId) {
		return teacherDao.getAnsweredQuestion(teacherId);
		}
		
	@Transactional
	//this method is used to fetch marksheet of students by exam type
	public List<Mstuploadstudmarksheet> getStudentMarksheet(int classId,int sectionId,int examId,int teacherId) {
		// TODO Auto-generated method stub
		return teacherDao.getStudentMarksheet(classId,sectionId,examId,teacherId);
	}
	
	@Transactional
	//this method is used to fetch all the students
		public List<Mstregistration> getStudents()
		{
		return teacherDao.getStudents();
		}
	@Transactional 
	//this method is used to fetch the marksheet to download or view
		public Mstuploadstudmarksheet getMarksheet(int id)
		{
			return teacherDao.getMarksheet(id);
		}

	@Transactional 
	//this method is used to get the file related to notification
	public Tblinvites getNotificationFile(int id) {
		return teacherDao.getNotificationFile(id);
	} 
	

}
