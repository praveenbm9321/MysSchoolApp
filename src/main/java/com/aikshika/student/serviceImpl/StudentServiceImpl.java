package com.aikshika.student.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.aikshika.student.dao.StudentDao;
import com.aikshika.student.service.StudentService;

@Service("Studentservice")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentdao;
	private Date Date;

	//Student Profile
	
	
	@Override
	@Transactional

	public List<Mstregistration> getParentInformationByName(int id) {
		return studentdao.getParentInformationByName(id);
	}

	//student question and answer
	@Override
	@Transactional

	public List<Mstsubject> getStudentQuestionByName() {

		return studentdao.getStudentQuestionByName();
	}

	@Override
	@Transactional

	public void addQuestion(Mstquestionandanswer mq) {

		studentdao.addQuestion(mq);
	}

	@Override
	@Transactional

	public List<Mstquestionandanswer> getList() {

		return studentdao.getList();
	}
	
	//student timetable
	
	
	
	
	@Override
	public Mstregistration getClassSection(int intRegId) {
		// TODO Auto-generated method stub
		return studentdao.getClassSection(intRegId);
	}

	@Override
	public ArrayList<ArrayList<Mststudenttimetable>> getStudentTT(int classId, int sectionId) {
		// TODO Auto-generated method stub
		return studentdao.getStudentTT(classId, sectionId);
	}

	public ArrayList<Msttimings> getTimings() {
		// TODO Auto-generated method stub
		return studentdao.getTimings();
	}

	public ArrayList<Tbldaysofweek> getDays() {
		// TODO Auto-generated method stub
		return studentdao.getDays();
	}
	
	
	
	
	 /* @Override
	  @Transactional

	  public List<Object[]> getStuTimeTablebyClass()
	  {
		  return studentdao.getStuTimeTablebyClass();
	  }*/
	/*@Override
	public List<Object[]> getStuTimeTablebyClass() {
		// TODO Auto-generated method stub
		return studentdao.getStuTimeTablebyClass();
	}*/

	/*
	 * @Override public List<Trnattendancerecord>
	 * getStudentAttendanceBetweenTwoDates(Date d1, Date d2) {
	 * 
	 * 
	 * return studentdao.getStudentAttendanceBetweenTwoDates(d1, d2); }
	 */
	//student notification
	@Override
	@Transactional

	public List<Tblinvites> getStudentNotificationByName(int id) {
		return studentdao.getStudentNotificationByName(id);
	}

	@Override
	@Transactional
	public Tblinvites getAdminNotification(Integer id)
	{
		return studentdao.getAdminNotification(id);
	}
	//student marks
	@Override
	@Transactional

	public List<Mstuploadstudmarksheet> getStudentMarksByName(int id) {
		return studentdao.getStudentMarksByName(id);
	}
	
	@Override
	@Transactional
	public Mstuploadstudmarksheet getMarkSheet(int id) {
		// TODO Auto-generated method stub
		return studentdao.getMarkSheet(id);
	}

	
	//student attendance
	@Override
	@Transactional
	public List<Trnattendancerecord> getStudAttendance(int studId, java.util.Date curDt) {
		return studentdao.getStudAttendance(studId, curDt);
	}

	@Override
	@Transactional
	public String getStuNames(int studId) {
		return studentdao.getStuNames(studId);
	}

	@Override
	@Transactional
	public List<Integer> getStudentListe(int studId) {
		return studentdao.getStudentListe(studId);
	}

	@Override
	@Transactional

	public List<Trnattendancerecord> getStudentAttendFromToDate(java.util.Date startDate, java.util.Date endDate,
			int id) {
		return studentdao.getStudentAttendFromToDate(startDate, endDate, id);
	}

	@Override
	@Transactional
	public List<String> getTeacherName() {
		// TODO Auto-generated method stub
		return studentdao.getTeacherName();
	}

	@Override
	@Transactional
	public List<Mstquestionandanswer> getTeacherNameAll(int classjj) {
		// TODO Auto-generated method stub
		return studentdao.getTeacherNameAll(classjj);
	}

	@Override
	@Transactional
	public List<Mstregistration> getTeachName(int teachId) {
		// TODO Auto-generated method stub
		return studentdao.getTeachName(teachId);
	}

	@Override
	@Transactional
	public Mstregistration getClass(int stuId) {
		// TODO Auto-generated method stub
		return studentdao.getClass(stuId);
	}

	//homework
	@Override
	@Transactional
	public List<Tblhomework> getHW(java.util.Date curDt,int regId,int classId,int sectionId) {

		return studentdao.getHW(curDt,regId,classId,sectionId);
	}

	@Override
	@Transactional
	public List<Mstteacherproject> getProject(java.util.Date curDt,int regId,int classId,int sectionId) {

		return studentdao.getProject(curDt,regId,classId,sectionId);
	}

	@Override
	@Transactional
	public List<Tblhomework> getHWFromTo(Date oneWeekBefore, Date curDt, int id,int classId,int sectionId) {
		// TODO Auto-generated method stub
		return studentdao.getHWFromTo(oneWeekBefore,curDt,id,classId,sectionId);
	}

	@Override
	@Transactional
	public List<Mstteacherproject> getProFromTo(Date oneWeekBefore, Date curDt, int id,int classId,int sectionId) {
		// TODO Auto-generated method stub
		return studentdao.getProFromTo(oneWeekBefore,curDt,id,classId,sectionId);	}

	@Override
	@Transactional
	public String getTeacherName(int teachId) {
		// TODO Auto-generated method stub
		return studentdao.getTeacherName(teachId);	
		}

	@Override
	public List<Mstteacherinvite> getTeacherNotificationByName(int id,int classId, int sectionId) {
		// TODO Auto-generated method stub
		return studentdao.getTeacherNotificationByName(id,classId,sectionId);
	}

	@Override
	@Transactional
	public Integer getRegistrationIdFromMstlogin(String userName) {
		// TODO Auto-generated method stub
		return studentdao.getRegistrationIdFromMstlogin(userName);
	}

	@Override
	@Transactional
	public void saveImage(byte[] file, int id) {
		// TODO Auto-generated method stub
		studentdao.saveImage(file, id);
	}

	
	
	//Syllabus
	@Override
	@Transactional
	public ArrayList<Mstsyllabus> getStudentSyllabus(int intRegId) {
		// TODO Auto-generated method stub
		return studentdao.getStudentSyllabus(intRegId);
	}

	@Override
	public Mstsyllabus getStudentsSyllabus(Integer id) {
		// TODO Auto-generated method stub
		return studentdao.getStudentsSyllabus(id);
	}

	

	

}
