package com.aikshika.teacher.service;

import java.sql.Date;
import java.util.List;

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

public interface TeacherService {
	//this method is for fetching the profile details of a teacher
		public List<Mstregistration> Profile(int registrationId);
		
		//this method is to update the teacher profile
		public void update(Mstregistration mstregistration);
		
		//this method is to fetch all the classes
		public List<Mststudclass> getAllClasses();
		
		//this method is to fetch the section
		public List<Mststudclasssection> getAllSections();

		//this method is used to post the homework
		public void homeworkPost(Tblhomework tblhomework);
		
		//this method is used to display the history of posted homework's
		public List<Tblhomework> getHomeworkHistory(int registrationId,Date fromDate, Date toDate);
		
		//this method is used to post the project
		public void projectPost(Mstteacherproject mstteacherproject);
				
		//this method is used to display the history of posted project's
		public List<Mstteacherproject> getProjectHistory(int registrationId,Date fromDate, Date toDate);
		
		//this method is to fetch the leave type for teacher
		public List<Mstleave> getAllLeaveTypes();
		
		//this method is used to apply for leave
		public void applyLeave(Trnteacherleaveapp trnteacherleaveapp);
		
		//this method is used to fetch the types of exam terms
		public List<Mstterms> getExamTerms();
		
		//this method is used to fetch the timetable of the teacher
		public List<Mstteachertimetable> getTimetable(int registrationId);
		
		//this method records the class check in time of the teacher
		public void recordCheckInTime(Mstteachercheckin mstteachercheckin);
		
		// this method is used to display class check-in time history
		public List<Mstteachercheckin> getClassCheckInTimeHistory(int registrationId, Date fromDate, Date toDate);
		
		//this method is used to get the nofitication sent by admin to teacher
		public List<Tblinvites> getTeacherNotification(int roleId);
		
		//this method is used to post the invite to the entire class
		public void postTeacherInvite(Mstteacherinvite mstteacherinvite);
		
		//this method is to get the posted invites
		public List<Mstteacherinvite> getPostedInvites(int id);
		
		// this method is used to display the history of posted invite's
		public List<Mstteacherinvite> getInviteHistory(int registrationId, Date fromDate, Date toDate);
		
		//this method is used to delete the invite sent by teacher
		public void deleteTeacherInvite(int id);
		
		//this method is to upload marksheet of students
		public void uploadStudentMarksheet(Mstuploadstudmarksheet mstuploadstudmarksheet);
		
		//this method is used to fetch teacher leave inbox
		public List<Trnteacherleaveapp> getTeacherLeaveInbox(int registrationId);
		
		//this method is used to fetch student leave request sent by parents
		public List<Trnstudentleaveapp> getStudentLeaveRequest(int id);
		
		//this method is used to reject/approve leave
		public void approveOrRejectStudentLeave(int id,int status,Date appOrRejDate);
		
		//this method is used to get all the searched leave request by date
		public List<Trnstudentleaveapp> getAllSearchedLeaveReq(int id,Date fromDate,Date toDate);
		
		//this method is used to get searched leave request by status and date
		public List<Trnstudentleaveapp> getSearchedLeaveReq(int id,int status,Date fromDate,Date toDate);
		
		//this method is used to fetch the questions posted by students
		public List<Mstquestionandanswer> getStudentQuestions();
		
		//this method is used to answer the question 
		public void postAnswer(int questionId,String answer,int teacherId);
		
		//this method is used to get the answered questions
		public List<Mstquestionandanswer> getAnsweredQuestion(int teacherId);
		
		//this method is used to fetch marksheet of students by exam type
		public List<Mstuploadstudmarksheet> getStudentMarksheet(int classId,int sectionId, int examId,int teacherId);
		
		//this method is used to fetch all the students
		public List<Mstregistration> getStudents();
		
		//this method is used to fetch the marksheet to download or view
		public Mstuploadstudmarksheet getMarksheet(int id);
		
		//this method is used to get the file related to notification
		public Tblinvites getNotificationFile(int id);
		
}
