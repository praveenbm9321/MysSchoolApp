package com.aikshika.teacher.daoImpl;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

/* This class acts as a dao(Data Access Object)layer and is responsible to
 * interact with the database 
*/
@SuppressWarnings("unchecked")
@Repository
public class TeacherDaoImpl implements TeacherDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	// this method is for fetching the profile details of a teacher
	public List<Mstregistration> Profile(int registrationId) {
		System.out.println("begin dao method");
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Mstregistration where intRegistrationId = '" + registrationId + "'");
		List<Mstregistration> list = query.list();
		System.out.println("Dao IMPL.......joining date:" + list.get(0).getDtJoiningDate());
		session.close();
		return list;

	}

	// this method is to update the teacher profile
	public void update(Mstregistration mstregistration) {
		Session session = this.sessionFactory.openSession();
		String hql = "update from Mstregistration set txtPerAddress=:add,txtTempAddress=:add2,txtEmailId=:em,txtFmobileNumber=:no"
				+ ",mstmaritalstatus.intMaritalStatusId=:mid, mstreligion.intReligionId=:rid,txtCity=:city "
				+ ",txtFatherName=:fname where intRegistrationId=:id";
		Query q = session.createQuery(hql);
		q.setParameter("add", mstregistration.getTxtPerAddress());
		q.setParameter("em", mstregistration.getTxtEmailId());
		q.setParameter("no", mstregistration.getTxtFmobileNumber());
		q.setParameter("add2", mstregistration.getTxtTempAddress());
		q.setParameter("mid", mstregistration.getMstmaritalstatus().getIntMaritalStatusId());
		q.setParameter("rid", mstregistration.getMstreligion().getIntReligionId());
		q.setParameter("city", mstregistration.getTxtCity());
		q.setParameter("fname", mstregistration.getTxtFatherName());
		q.setParameter("id", mstregistration.getIntRegistrationId());
		int i = q.executeUpdate();
		session.close();

	}

	// this method is to fetch all the classes
	public List<Mststudclass> getAllClasses() {
		Session session = this.sessionFactory.openSession();
		List<Mststudclass> classList = session.createQuery("from Mststudclass").list();

		/*
		 * List<Mststudclass> classList = session.createSQLQuery(
		 * "CALL classes()") .addEntity(Mststudclass.class).list();
		 */
		session.close();
		return classList;
	}

	// this method is to fetch the section
	public List<Mststudclasssection> getAllSections() {
		Session session = this.sessionFactory.openSession();
		List<Mststudclasssection> sectionList = session.createQuery("from Mststudclasssection").list();
		session.close();
		return sectionList;
	}

	// this method is used to post the homework
	public void homeworkPost(Tblhomework tblhomework) {
		Session session = this.sessionFactory.openSession();
		session.save(tblhomework);
		System.out.println("home work saved successfully");
		session.close();
	}

	// this method is used to display the history of posted homework's
	public List<Tblhomework> getHomeworkHistory(int registrationId, Date fromDate, Date toDate) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Tblhomework where dtAssignDate between :f and :t and intTeacherId='" + registrationId
				+ "' order by dtAssignDate desc";
		List<Tblhomework> homeworkHistoryList = session.createQuery(hql).setParameter("f", fromDate)
				.setParameter("t", toDate).list();
		session.close();
		return homeworkHistoryList;
	}

	// this method is used to post the project
	public void projectPost(Mstteacherproject mstteacherproject) {
		Session session = this.sessionFactory.openSession();
		session.save(mstteacherproject);
		System.out.println("project details saved successfully");
		session.close();

	}

	// this method is used to display the history of posted project's
	public List<Mstteacherproject> getProjectHistory(int registrationId, Date fromDate, Date toDate) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Mstteacherproject where dtAssignDate between :f and :t and intTeacherId='" + registrationId
				+ "' order by dtAssignDate desc ";
		List<Mstteacherproject> projectHistoryList = session.createQuery(hql).setParameter("f", fromDate)
				.setParameter("t", toDate).list();
		session.close();
		return projectHistoryList;
	}

	// this method is to fetch the leave type for teacher
	public List<Mstleave> getAllLeaveTypes() {
		Session session = this.sessionFactory.openSession();
		List<Mstleave> leaveTypeList = session.createQuery("from Mstleave").list();
		session.close();
		return leaveTypeList;
	}

	// this method is used to apply for leave
	public void applyLeave(Trnteacherleaveapp trnteacherleaveapp) {
		Session session = this.sessionFactory.openSession();
		session.save(trnteacherleaveapp);
		System.out.println("leave request sent successfully");
		session.close();
	}

	// this method is used to fetch the types of exam terms
	public List<Mstterms> getExamTerms() {
		Session session = this.sessionFactory.openSession();
		List<Mstterms> examTypeList = session.createQuery("from Mstterms").list();
		session.close();
		return examTypeList;
	}

	// this method is used to fetch the timetable of the teacher
	public List<Mstteachertimetable> getTimetable(int registrationId) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Mstteachertimetable as m where mstregistration.intRegistrationId =:id and m.intTeacherStatus=:s ";
		List<Mstteachertimetable> timetable = session.createQuery(hql).setParameter("id", registrationId)
				.setParameter("s", 1).list();
		session.close();
		return timetable;
	}

	// this method records the class check in time of the teacher
	public void recordCheckInTime(Mstteachercheckin mstteachercheckin) {
		Session session = this.sessionFactory.openSession();
		session.save(mstteachercheckin);
		session.close();
	}

	// this method is used to display class check-in time history
	public List<Mstteachercheckin> getClassCheckInTimeHistory(int registrationId, Date fromDate, Date toDate) {
		System.out.println("fromDate  " + fromDate + "to date " + toDate);
		/*
		 * DateTime dtOrg = new DateTime(toDate); DateTime dtPlusOne =
		 * dtOrg.plusDays(1); System.out.println("dtPlusOne "+dtPlusOne );
		 */
		Session session = this.sessionFactory.openSession();
		// (m.dtCheckIn >= :stDate AND m.dtCheckIn <= :edDate)
		String hql = "from Mstteachercheckin as m where m.mstteachertimetable.mstregistration.intRegistrationId=:id and "
				+ " m.dtCheckInDate BETWEEN :stDate AND :edDate order by m.mstteachertimetable.intTeacherTimeTableId desc ";
		List<Mstteachercheckin> list = session.createQuery(hql).setParameter("id", registrationId)
				.setParameter("stDate", fromDate).setParameter("edDate", toDate).list();
		System.out.println("query success" + list);
		session.close();
		return list;
	}

	// this method is used to get the nofitication sent by admin to teacher
	public List<Tblinvites> getTeacherNotification(int roleId) {
		Session session = this.sessionFactory.openSession();
		List<Tblinvites> nofitication = session
				.createQuery("from Tblinvites where tblrolename.intRoleId = '" + roleId + "' order by dtDate desc ")
				.list();
		session.close();
		return nofitication;
	}

	// this method is used to post the invite to the entire class
	public void postTeacherInvite(Mstteacherinvite mstteacherinvite) {
		Session session = this.sessionFactory.openSession();
		session.save(mstteacherinvite);
		session.close();
		}

	// this method is to get the posted invites
	public List<Mstteacherinvite> getPostedInvites(int id) {
		Session session = this.sessionFactory.openSession();
		List<Mstteacherinvite> list = session
				.createQuery("from Mstteacherinvite where intTeacherId=:i order by dtInvitesDate desc")
				.setParameter("i", id).list();
		System.out.println("............" + list);
		session.close();
		return list;
	}

	// this method is used to display the history of posted invite's
	public List<Mstteacherinvite> getInviteHistory(int registrationId, Date fromDate, Date toDate) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Mstteacherinvite where dtInvitesDate between :f and :t and intTeacherId='" + registrationId
				+ "' order by dtInvitesDate desc";
		List<Mstteacherinvite> inviteHistoryList = session.createQuery(hql).setParameter("f", fromDate)
				.setParameter("t", toDate).list();
		session.close();
		return inviteHistoryList;
	}

	// this method is used to delete the invite sent by teacher
	public void deleteTeacherInvite(int id) {
		Session session = this.sessionFactory.openSession();
		Mstteacherinvite invite = (Mstteacherinvite) session.load(Mstteacherinvite.class, id);
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(invite);
		transaction.commit();
		session.close();
	}

	// this method is to upload marksheet of students
	public void uploadStudentMarksheet(Mstuploadstudmarksheet mstuploadstudmarksheet) {
		Session session = this.sessionFactory.openSession();
		session.save(mstuploadstudmarksheet);
		session.close();

	}

	// this method is used to fetch teacher leave inbox
	public List<Trnteacherleaveapp> getTeacherLeaveInbox(int registrationId) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Trnteacherleaveapp where intRegistrationId=:id order by dtAppliedDate desc";
		List<Trnteacherleaveapp> leaveInbox = session.createQuery(hql).setParameter("id", registrationId).list();
		session.close();
		return leaveInbox;
	}

	// this method is used to fetch student leave request sent by parents
	@SuppressWarnings("rawtypes")
	public List<Trnstudentleaveapp> getStudentLeaveRequest(int id) {
		Session session = this.sessionFactory.openSession();
		List<Integer> cls = new ArrayList<>();
		List<Integer> sec = new ArrayList<>();
		Query query = session.createQuery(
				"select m.mststudclass.intClassId,m.mststudclasssection.intSectionId from Tblclassteacher m where m.mstregistration.intRegistrationId ='"
						+ id + "'");
		List<Object[]> list = query.list();
		List<Trnstudentleaveapp> studentLeave = null;
		System.out.println("teacher class:" + !(list.isEmpty()));
		if (!list.isEmpty()) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				System.out.println(objects.length);
				for (int i = 0; i < objects.length; i++) {
					System.out.println(objects[i]);
					if (objects[0] != null && objects[1] != null) {
						cls.add((int) objects[0]);
						sec.add((int) objects[1]);
					}
				}
			}
			String hql = "select distinct m.txtFirstName,m.txtLastName,l.dtAppliedDate,l.mstleave.txtLeaveType,l.dtFromDate,l.dtToDate,l.txtReason,l.intStatus,l.intStudentLeaveAppId "
					+ "FROM Trnstudentleaveapp AS l ,Mstregistration AS m , Mstleave as v "
					+ "WHERE  m.intRegistrationId = l.mstregistration.intRegistrationId AND m.mststudclass.intClassId in(:c) AND m.mststudclasssection.intSectionId in(:s) "
					+ "order by l.dtAppliedDate desc ";
			
			studentLeave = new ArrayList<>();
			
			// added by suhas 14-feb-2017
			// if no record exist then it throws error.
			if(sec.isEmpty())
			{
				sec.add(1);
			}
			
			List<Object[]> list2 = session.createQuery(hql).setParameterList("c", cls).setParameterList("s", sec)
					.list();
			session.close();
			for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				Trnstudentleaveapp t = new Trnstudentleaveapp();
				Mstregistration m = new Mstregistration();
				m.setTxtFirstName((String) objects[0]);
				m.setTxtLastName((String) objects[1]);
				t.setMstregistration(m);

				Mstleave l = new Mstleave();
				l.setTxtLeaveType((String) objects[3]);
				t.setMstleave(l);

				t.setDtAppliedDate((java.util.Date) objects[2]);
				t.setDtFromDate((java.util.Date) objects[4]);
				t.setDtToDate((java.util.Date) objects[5]);
				t.setTxtReason((String) objects[6]);
				t.setIntStatus((Integer) objects[7]);
				t.setIntStudentLeaveAppId((Integer) objects[8]);
				studentLeave.add(t);

			}

		}
		return studentLeave;

	}

	// this method is used to reject/approve leave
	public void approveOrRejectStudentLeave(int id, int status, Date appOrRejDate) {
		Session session = this.sessionFactory.openSession();
		String hql = "update from Trnstudentleaveapp set intStatus=:p1,dtApprovedDate=:p2 where intStudentLeaveAppId=:p3";
		Query query = session.createQuery(hql);
		query.setParameter("p1", status);
		query.setParameter("p2", appOrRejDate);
		query.setParameter("p3", id);
		int i = query.executeUpdate();
		System.out.println("rows affected:" + i);
		session.close();
	}

	// this method is used to get all the searched leave request by date
	public List<Trnstudentleaveapp> getAllSearchedLeaveReq(int id, Date fromDate, Date toDate) {
		Session session = this.sessionFactory.openSession();

		List<Integer> cls = new ArrayList<>();
		List<Integer> sec = new ArrayList<>();
		Query query = session.createQuery(
				"select m.mststudclass.intClassId,m.mststudclasssection.intSectionId from Tblclassteacher m where m.mstregistration.intRegistrationId ='"
						+ id + "'");

		List<Object[]> list = query.list();
		System.out.println(list);
		System.out.println("teacher class:" + !(list.isEmpty()));
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] != null) {

					cls.add((int) objects[0]);
					sec.add((int) objects[1]);
				}
			}
		}

		String hql = "select distinct m.txtFirstName,m.txtLastName,l.dtAppliedDate,l.mstleave.txtLeaveType,l.dtFromDate,l.dtToDate,l.txtReason,l.intStatus,l.dtApprovedDate "
				+ "FROM Trnstudentleaveapp AS l ,Mstregistration AS m , Mstleave as v "
				+ "WHERE dtAppliedDate between :d1 and :d2 and m.intRegistrationId = l.mstregistration.intRegistrationId AND m.mststudclass.intClassId in(:c) AND m.mststudclasssection.intSectionId in(:s) "
				+ "order by l.dtAppliedDate desc";

				// added by suhas 14-feb-2017
				// if no record exist then it throws error.
				if(sec.isEmpty())
				{
					sec.add(1);
				}
				
		Query query1 = session.createQuery(hql).setParameterList("c", cls).setParameterList("s", sec);
		query1.setParameter("d1", fromDate);
		query1.setParameter("d2", toDate);
		List<Trnstudentleaveapp> studentLeave = new ArrayList<>();
		List<Object[]> list2 = query1.list();
		session.close();
		for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Trnstudentleaveapp t = new Trnstudentleaveapp();
			Mstregistration m = new Mstregistration();
			m.setTxtFirstName((String) objects[0]);
			m.setTxtLastName((String) objects[1]);
			t.setMstregistration(m);

			Mstleave l = new Mstleave();
			l.setTxtLeaveType((String) objects[3]);
			t.setMstleave(l);

			t.setDtAppliedDate((java.util.Date) objects[2]);
			t.setDtFromDate((java.util.Date) objects[4]);
			t.setDtToDate((java.util.Date) objects[5]);
			t.setTxtReason((String) objects[6]);
			t.setIntStatus((Integer) objects[7]);
			t.setDtApprovedDate((java.util.Date) objects[8]);
			studentLeave.add(t);

		}
		return studentLeave;
	}

	// this method is used to get searched leave request by status and date
	public List<Trnstudentleaveapp> getSearchedLeaveReq(int id, int status, Date fromDate, Date toDate) {
		Session session = this.sessionFactory.openSession();
		List<Integer> cls = new ArrayList<>();
		List<Integer> sec = new ArrayList<>();
		Query query = session.createQuery(
				"select m.mststudclass.intClassId,m.mststudclasssection.intSectionId from Tblclassteacher m where m.mstregistration.intRegistrationId ='"
						+ id + "'");
		List<Object[]> list = query.list();
		System.out.println("teacher class:" + !(list.isEmpty()));
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] != null) {

					cls.add((int) objects[0]);
					sec.add((int) objects[1]);
				}

			}
		}

		String hql = "select distinct m.txtFirstName,m.txtLastName,l.dtAppliedDate,l.mstleave.txtLeaveType,l.dtFromDate,l.dtToDate,l.txtReason,l.intStatus,l.dtApprovedDate "
				+ "FROM Trnstudentleaveapp AS l ,Mstregistration AS m , Mstleave as v "
				+ "WHERE intStatus=:s and dtAppliedDate between :d1 and :d2 and m.intRegistrationId = l.mstregistration.intRegistrationId AND m.mststudclass.intClassId in(:c) AND m.mststudclasssection.intSectionId in(:s)"
				+ "order by l.dtAppliedDate desc";

		// added by suhas 14-feb-2017
		// if no record exist then it throws error.
		if(sec.isEmpty())
		{
			sec.add(1);
		}
		Query query1 = session.createQuery(hql).setParameterList("c", cls).setParameterList("s", sec);
		query1.setParameter("d1", fromDate);
		query1.setParameter("d2", toDate);
		query1.setParameter("s", status);
		List<Trnstudentleaveapp> studentLeave = new ArrayList<>();
		List<Object[]> list2 = query1.list();
		session.close();
		for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Trnstudentleaveapp t = new Trnstudentleaveapp();
			Mstregistration m = new Mstregistration();
			m.setTxtFirstName((String) objects[0]);
			m.setTxtLastName((String) objects[1]);
			t.setMstregistration(m);

			Mstleave l = new Mstleave();
			l.setTxtLeaveType((String) objects[3]);
			t.setMstleave(l);

			t.setDtAppliedDate((java.util.Date) objects[2]);
			t.setDtFromDate((java.util.Date) objects[4]);
			t.setDtToDate((java.util.Date) objects[5]);
			t.setTxtReason((String) objects[6]);
			t.setIntStatus((Integer) objects[7]);
			t.setDtApprovedDate((java.util.Date) objects[8]);
			studentLeave.add(t);

		}

		return studentLeave;
	}

	// this method is used to fetch the questions posted by students
	public List<Mstquestionandanswer> getStudentQuestions() {
		Session session = this.sessionFactory.openSession();
		String hql = "from Mstquestionandanswer where txtAnswer is NULL order by intQuestionAndAnswerId desc";
		List<Mstquestionandanswer> list = session.createQuery(hql).list();
		session.close();
		return list;
	}

	// this method is used to answer the question
	public void postAnswer(int questionId, String answer, int teacherId) {
		Session session = this.sessionFactory.openSession();
		String hql = "update from Mstquestionandanswer set intTeacherId=:tid,txtAnswer=:ans  where intQuestionAndAnswerId=:qid ";
		session.createQuery(hql).setParameter("tid", teacherId).setParameter("ans", answer)
				.setParameter("qid", questionId).executeUpdate();
		session.close();
	}

	// this method is used to get the answered questions
	public List<Mstquestionandanswer> getAnsweredQuestion(int teacherId) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Mstquestionandanswer where txtAnswer is not NULL and intTeacherId = '" + teacherId
				+ "' order by intQuestionAndAnswerId desc";
		List<Mstquestionandanswer> list = session.createQuery(hql).list();
		session.close();
		return list;

	}

	// this method is used to fetch marksheet of students by exam type
	public List<Mstuploadstudmarksheet> getStudentMarksheet(int classId, int sectionId, int examId, int teacherId) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Mstuploadstudmarksheet where mstterms.intTermId=:id and mststudclass.intClassId=:cid and mststudclasssection.intSectionId=:sid and intTeacherId=:tid";
		List<Mstuploadstudmarksheet> list = session.createQuery(hql).setParameter("id", examId)
				.setParameter("tid", teacherId).setParameter("cid", classId).setParameter("sid", sectionId).list();
		session.close();
		return list;
	}

	// this method is used to fetch all the students
	public List<Mstregistration> getStudents() {
		Session session = this.sessionFactory.openSession();
		String hql = "from Mstregistration where intRoleId=:id";
		List<Mstregistration> list = session.createQuery(hql).setParameter("id", 1).list();
		session.close();
		return list;
	}

	// this method is used to fetch the marksheet to download or view
	public Mstuploadstudmarksheet getMarksheet(int id) {
		Session session = this.sessionFactory.openSession();
		Mstuploadstudmarksheet mst = (Mstuploadstudmarksheet) session.get(Mstuploadstudmarksheet.class, id);
		session.close();
		return mst;
	}

	// this method is used to get the file related to notification
	public Tblinvites getNotificationFile(int id) {
		Session session = this.sessionFactory.openSession();
		Tblinvites invite = (Tblinvites) session.get(Tblinvites.class, id);
		session.close();
		return invite;

	}

}
