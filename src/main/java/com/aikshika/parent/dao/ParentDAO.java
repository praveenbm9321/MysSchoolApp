package com.aikshika.parent.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aikshika.entity.Mstleave;
import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstquestionandanswer;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mststudenttimetable;
import com.aikshika.entity.Mstsubject;
import com.aikshika.entity.Mstteacherinvite;
import com.aikshika.entity.Mstteacherproject;
import com.aikshika.entity.Msttimings;
import com.aikshika.entity.Mstuploadstudmarksheet;
import com.aikshika.entity.Tbldaysofweek;
import com.aikshika.entity.Tblhomework;
import com.aikshika.entity.Tblinvites;
import com.aikshika.entity.Tblparentqueries;
import com.aikshika.entity.Trnattendancerecord;
import com.aikshika.entity.Trnstudentleaveapp;

public interface ParentDAO {
	
	public List<Mstregistration> getStudentInformationByName(List<Mstlogin> list);
	public List<Mstregistration> getStudentDetailByName(int parentId, String studentName);
	public void update(Mstregistration m,int id);
	public List<Mstleave> getLeave();
	public List<Trnattendancerecord> getStudentAttendanceByName();
	//List<Mstregistration> getStudentMarksByName();
	//a
	/*public List<Mstuploadstudmarksheet> getStudentMarksByName();*/
	
	public List<Mstuploadstudmarksheet> getStudentMarksListByName(String studentName);
	public void leaveApp(Trnstudentleaveapp trnLeave);
	public List<Trnattendancerecord> getStudentAttendanceBetweenTwoDates(int intRegId, String name1,Date d1, Date d2);
	List<Trnattendancerecord> getStudentAttendanceByName(String studentName);
	//List<Mststudenttimetable> getStudentTimeTable(String studentName);
	List<Tblhomework> getStudentHomeWorkByName(List<Mstlogin> list,String studentName);
	List<Tblhomework> getStudentHomeWorkByName(String studentName,Date date);
	List<Mstteacherproject> getStudentProjectByName(List<Mstlogin> list,String studentName);
	List<Tblinvites> getNotificationByStudentName(String studentName);
	//a
	//List<Object[]> getStuTimeTablebyClass();
	
	//timetable
	
	public Mstregistration getClassSection(int intRegId);
	//public Mstregistration getClassSection(List<Mstlogin> list);
	/*public ArrayList<ArrayList<Mststudenttimetable>> getStudentTT(int classId, int sectionId);
	public ArrayList<Msttimings> getTimings();
	public ArrayList<Tbldaysofweek> getDays();*/
	
	
	
	List<Trnstudentleaveapp> getStudentLeaveInbox(int id);
	public Mstuploadstudmarksheet getMarkSheet(int id);
	 public Tblinvites getNotificationById(int id) ;
	 public List<Mstteacherinvite> getNotificationToStudentNameByTeacher(List<Mstlogin> list,String studentName);
	 
	 
	 public void addQuestion(Tblparentqueries tpq);
	 public List<Tblparentqueries> getListOfQuestionsAskedByParent(String parentId);
	 
	 
	 
	 /*public List<Mstsubject> getStudentQuestionByName();
		
		public void addQuestion(Mstquestionandanswer mq);
		
		public List<Mstquestionandanswer> getList();*/
	
	
}
