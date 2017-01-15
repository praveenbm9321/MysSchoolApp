package com.aikshika.student.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.aikshika.entity.Mstquestionandanswer;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mststudenttimetable;
import com.aikshika.entity.Mstsubject;
import com.aikshika.entity.Mstsyllabus;
import com.aikshika.entity.Mstteacherinvite;
import com.aikshika.entity.Mstteacherproject;
import com.aikshika.entity.Msttimings;
import com.aikshika.entity.Mstuploadstudmarksheet;
import com.aikshika.entity.Tbldaysofweek;
import com.aikshika.entity.Tblhomework;
import com.aikshika.entity.Tblinvites;
import com.aikshika.entity.Trnattendancerecord;

public interface StudentDao {
	
	//Student Profile
	
	
	public Integer getRegistrationIdFromMstlogin(String userName);
	public List<Mstregistration> getParentInformationByName(int id);
	public void saveImage(byte[] file, int id);	
	//student question and answer
public List<Mstsubject> getStudentQuestionByName();
	
	public void addQuestion(Mstquestionandanswer mq);
	
	public List<Mstquestionandanswer> getList();
	
public  List<String> getTeacherName();
    
    public  List<Mstquestionandanswer> getTeacherNameAll(int classjj);


	public List<Mstregistration>  getTeachName(int teachId);
	public Mstregistration getClass(int stuId);
	
	//student timetable
	
	
	
	public Mstregistration getClassSection(int intRegId);
	public ArrayList<ArrayList<Mststudenttimetable>> getStudentTT(int classId, int sectionId);
	public ArrayList<Msttimings> getTimings();
	public ArrayList<Tbldaysofweek> getDays();
	
	//public List<Object[]> getStuTimeTablebyClass();
	
	/*public List<Object[]> getStuTimeTablebyClass();*/
	
	//student notification
	
    public List<Tblinvites> getStudentNotificationByName(int id);
    
    public Tblinvites getAdminNotification(Integer id);
    
  //student marks
		
    public List<Mstuploadstudmarksheet> getStudentMarksByName(int id);
    
    public Mstuploadstudmarksheet getMarkSheet(int id);
    
  
	
	//student attendance
	
	public List<Trnattendancerecord> getStudAttendance(int studId, java.util.Date curDt);

	public String getStuNames(int studId);
	
	public List<Integer> getStudentListe(int studId);

	public List<Trnattendancerecord> getStudentAttendFromToDate(java.util.Date startDate, java.util.Date endDate ,int id);

	//homework
	public List<Tblhomework> getHW(java.util.Date curDt,int regId,int classId,int sectionId);

	public List<Mstteacherproject> getProject(java.util.Date curDt,int regId,int classId,int sectionId);

	public List<Tblhomework> getHWFromTo(Date oneWeekBefore, Date curDt, int id,int classId,int sectionId);

	public List<Mstteacherproject> getProFromTo(Date oneWeekBefore, Date curDt, int id,int classId,int sectionId);

	public String getTeacherName(int teachId);

	public List<Mstteacherinvite> getTeacherNotificationByName(int id,int classId, int sectionId);

//Syllabus
	public ArrayList<Mstsyllabus> getStudentSyllabus(int intRegId);
	public Mstsyllabus getStudentsSyllabus(Integer id);

	
}
