package com.aikshika.parent.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikshika.entity.Mstleave;
import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mststudenttimetable;
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
import com.aikshika.parent.dao.ParentDAO;
import com.aikshika.parent.service.ParentService;
@Service("parentService")
public class ParentServiceImpl implements ParentService{


	@Autowired
	private ParentDAO parentdao;
	
	@Override
	@Transactional
	public List<Mstregistration> getStudentInformationByName(List<Mstlogin> list) {
		// TODO Auto-generated method stub
		return parentdao.getStudentInformationByName(list);
	}

	@Override
	@Transactional
	public List<Trnattendancerecord> getStudentAttendanceBetweenTwoDates(int intRegId, String name1,Date d1, Date d2) {
		return parentdao.getStudentAttendanceBetweenTwoDates(intRegId,name1,d1, d2);
	}

	@Override
	@Transactional
	public List<Trnattendancerecord> getStudentAttendanceByName() {
		// TODO Auto-generated method stub
		return parentdao.getStudentAttendanceByName();
	}

	/*@Transactional
	public List<Mstregistration> getStudentMarksByName() {
		// TODO Auto-generated method stub
		return parentdao.getStudentMarksByName();
	}*/
	
	/*@Override
	@Transactional
	public List<Mstuploadstudmarksheet> getStudentMarksByName() {
	return parentdao.getStudentMarksByName();
	}*/

	@Override
	@Transactional
	public List<Mstuploadstudmarksheet> getStudentMarksListByName(String studentName) {
		// TODO Auto-generated method stub
		return parentdao.getStudentMarksListByName(studentName);
	}
	@Override
	@Transactional
	public void leaveApp(Trnstudentleaveapp trnLeave) {
		// TODO Auto-generated method stub
		 parentdao.leaveApp(trnLeave);
	}

	@Override
	@Transactional
	public List<Mstleave> getLeave() {
		// TODO Auto-generated method stub
		return parentdao.getLeave();
	}


	@Override
	@Transactional
	public List<Mstregistration> getStudentDetailByName(int parentId, String studentName) {
		// TODO Auto-generated method stub
		return parentdao.getStudentDetailByName(parentId,studentName);
	}


	@Override
	@Transactional
	public List<Tblhomework> getStudentHomeWorkByName(String studentName, Date date) {
		// TODO Auto-generated method stub
		return parentdao.getStudentHomeWorkByName(studentName,date);
	}

	@Override
	@Transactional
	public List<Tblhomework> getStudentHomeWorkByName(List<Mstlogin> list,String studentName) {
		// TODO Auto-generated method stub
		return parentdao.getStudentHomeWorkByName(list,studentName);
	}

	@Override
	@Transactional
	public List<Mstteacherproject> getStudentProjectByName(List<Mstlogin>list,String studentName) {
		// TODO Auto-generated method stub
		return parentdao.getStudentProjectByName(list,studentName);
	}

	@Override
	@Transactional
	public List<Trnattendancerecord> getStudentAttendanceByName(String studentName) {
		// TODO Auto-generated method stub
		return parentdao.getStudentAttendanceByName(studentName);
	}

	@Override
	@Transactional

	public List<Tblinvites> getNotificationByStudentName(String studentName) {
		// TODO Auto-generated method stub
		return parentdao.getNotificationByStudentName(studentName);
	}

	@Override
	@Transactional
	public void update(Mstregistration m,int id) {
		// TODO Auto-generated method stub
		parentdao.update(m,id);
	}

	/*@Override
	public List<Object[]> getStuTimeTablebyClass() {
		// TODO Auto-generated method stub
		return parentdao.getStuTimeTablebyClass();
	}
*/
	@Override
	@Transactional
	public
	List<Trnstudentleaveapp> getStudentLeaveInbox(int id) {
		return parentdao.getStudentLeaveInbox(id);
	}

	@Override
	@Transactional
	public Mstuploadstudmarksheet getMarkSheet(int id) {
		return parentdao.getMarkSheet(id);
	}

	@Override
	@Transactional
	public Tblinvites getNotificationById(int id) {
		return parentdao.getNotificationById(id);
	}

	@Override
	@Transactional
	public List<Mstteacherinvite> getNotificationToStudentNameByTeacher(List<Mstlogin> list,String studentName) {
		return parentdao.getNotificationToStudentNameByTeacher(list,studentName);
	}

	@Override
	@Transactional
	public void addQuestion(Tblparentqueries tpq) {
		// TODO Auto-generated method stub
		parentdao.addQuestion(tpq);
	}

	@Override
	@Transactional
	public List<Tblparentqueries> getListOfQuestionsAskedByParent(String parentId) {
		// TODO Auto-generated method stub
		return parentdao.getListOfQuestionsAskedByParent(parentId);
	}

	@Override
	@Transactional
	public Mstregistration getClassSection(int id) {
		// TODO Auto-generated method stub
		return parentdao.getClassSection(id);
	}

	/*@Override
	@Transactional
	public ArrayList<ArrayList<Mststudenttimetable>> getStudentTT(int classId, int sectionId) {
		// TODO Auto-generated method stub
		return parentdao.getStudentTT(classId, sectionId);
	}

	@Override
	@Transactional
	public ArrayList<Msttimings> getTimings() {
		// TODO Auto-generated method stub
		return parentdao.getTimings();
	}

	@Override
	@Transactional
	public ArrayList<Tbldaysofweek> getDays() {
		// TODO Auto-generated method stub
		return parentdao.getDays();
	}
*/
	/*@Override
	public List<Mststudenttimetable> getStudentTimeTable(String studentName) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	/*@Override
	public List<Object[]> getStuTimeTablebyClass() {
		// TODO Auto-generated method stub
		return null;
	}*/

	
		
}
