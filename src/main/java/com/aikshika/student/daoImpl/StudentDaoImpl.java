package com.aikshika.student.daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aikshika.common.StudentTimingsCompare;
import com.aikshika.entity.Mstquestionandanswer;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mststudclass;
import com.aikshika.entity.Mststudclasssection;
import com.aikshika.entity.Mststudenttimetable;
import com.aikshika.entity.Mstsubject;
import com.aikshika.entity.Mstsyllabus;
import com.aikshika.entity.Mstteacherinvite;
import com.aikshika.entity.Mstteacherproject;
import com.aikshika.entity.Mstterms;
import com.aikshika.entity.Msttimings;
import com.aikshika.entity.Mstuploadstudmarksheet;
import com.aikshika.entity.Tbldaysofweek;
import com.aikshika.entity.Tblhomework;
import com.aikshika.entity.Tblinvites;
import com.aikshika.entity.Trnattendancerecord;
import com.aikshika.student.dao.StudentDao;

@Repository("studentdao")
public class StudentDaoImpl implements StudentDao {
	@Autowired
	SessionFactory sessionFactory;

	// Student Profile
	@SuppressWarnings("unchecked")
	public List<Mstregistration> getParentInformationByName(int id) {
		Session session = this.sessionFactory.openSession();
		String sn = "from Mstregistration where intRegistrationId='" + id + "'";
		org.hibernate.Query query = session.createQuery(sn);
		System.out.println("Query=" + query);
		List<Mstregistration> result = query.list();
		session.close();
		return result;
	}

	@Override
	public Integer getRegistrationIdFromMstlogin(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String sn = "Select l.mstregistration.intRegistrationId from Mstlogin where l.txtUserName='" + userName + "'";
		org.hibernate.Query query = session.createQuery(sn);
		List<Integer> list = query.list();
		Integer id = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			id = (Integer) iterator.next();

		}
		session.close();
		return id;
	}

	@Override
	public void saveImage(byte[] file, int id) {
		Session session = sessionFactory.openSession();
		System.out.println("inside save method");
		org.hibernate.Query q = (org.hibernate.Query) session
				.createQuery("update  Mstregistration set blImage= :p1 where intRegistrationId= :p2")
				.setParameter("p1", file).setParameter("p2", id);
		q.executeUpdate();
		session.close();

	}
	// student question and answer
	/*
	 * @Override public List<Mstsubject> getStudentQuestionByName() {
	 * 
	 * String sn = "from Mstsubject"; org.hibernate.Query query =
	 * this.sessionFactory.getCurrentSession().createQuery(sn);
	 * System.out.println("Query=" +query); List<Mstsubject> result=
	 * query.list(); System.out.println("sucess"); for (Iterator iterator =
	 * result.iterator(); iterator.hasNext();) { Mstsubject mstsubject =
	 * (Mstsubject) iterator.next();
	 * System.out.println(mstsubject.getTxtSubjectName());
	 * 
	 * } return result; }
	 * 
	 * @Override public void addQuestion(Mstquestionandanswer mq) {
	 * 
	 * this.sessionFactory.getCurrentSession().save(mq); }
	 * 
	 * @Override public List<Mstquestionandanswer> getList() { String hql =
	 * "from Mstquestionandanswer"; org.hibernate.Query q =
	 * this.sessionFactory.getCurrentSession().createQuery(hql);
	 * List<Mstquestionandanswer> list = q.list(); for (Iterator iterator =
	 * list.iterator(); iterator.hasNext();) { Mstquestionandanswer
	 * mstquestionandanswer = (Mstquestionandanswer) iterator.next();
	 * System.out.println(mstquestionandanswer.getTxtQuestion()); } return list;
	 * }
	 */

	@Override
	public List<Mstsubject> getStudentQuestionByName() {
		Session session = this.sessionFactory.openSession();
		String sn = " from Mstsubject order by txtSubjectName";
		org.hibernate.Query query = session.createQuery(sn);
		System.out.println("Query=" + query);
		List<Mstsubject> result = query.list();
		session.close();
		/*
		 * System.out.println("sucess"); for (Iterator iterator =
		 * result.iterator(); iterator.hasNext();) { Mstsubject mstsubject =
		 * (Mstsubject) iterator.next();
		 * System.out.println(mstsubject.getTxtSubjectName());
		 * 
		 * }
		 */
		return result;
	}

	@Override

	public List<String> getTeacherName() {
		// TODO Auto-generated method stub
		Session s = this.sessionFactory.openSession();
		String hql = "SELECT m.txtFirstName FROM Mstquestionandanswer AS t,Mstregistration "
				+ "AS m WHERE m.intRegistrationId = t.intTeacherId";
		org.hibernate.Query q = s.createQuery(hql);
		// List<Mstquestionandanswer> result= q.list();
		List<String> mstquestionandanswers = new ArrayList<>();
		List list = q.list();
		// System.out.println(list);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstquestionandanswer a = new Mstquestionandanswer();
			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);
				mstquestionandanswers.add((String) objects[0]);

			}
		}
		// System.out.println("query success");
		s.close();
		return list;
	}

	@Override
	public void addQuestion(Mstquestionandanswer mq) {
		Session s = sessionFactory.openSession();
		s.save(mq);
		s.close();
	}

	@Override
	public List<Mstquestionandanswer> getList() {
		// String hql = "from Mstquestionandanswer";
		Session session = this.sessionFactory.openSession();
		String hql = "from  Mstquestionandanswer as t where t.txtAnswer is not null  and t.intTeacherId is not null order by t.intQuestionAndAnswerId desc";
		org.hibernate.Query q = session.createQuery(hql);
		// System.out.println("before list");
		List<Mstquestionandanswer> list = q.list();
		// System.out.println("after list");
		/*
		 * for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		 * Mstquestionandanswer mstquestionandanswer = (Mstquestionandanswer)
		 * iterator.next(); System.out.println("in dao impl " +
		 * mstquestionandanswer.getTxtQuestion()); }
		 * System.out.println("success");
		 */
		System.out.println("list........"+list);
		session.close();
		return list;
	}

	@Override
	public List<Mstquestionandanswer> getTeacherNameAll(int classjj) {
		// TODO Auto-generated method stub
		Session s = this.sessionFactory.openSession();

		String hql = "from Mstquestionandanswer where intClassId=:intClassId";
		org.hibernate.Query q = s.createQuery(hql);
		q.setParameter("intClassId", classjj);
		List<Mstquestionandanswer> list = q.list();
		System.out.println("dao..................."+list.get(0).getIntQuestionAndAnswerId());
		System.out.println(list);
		s.close();
		return list;
		/*
		 * String hql =
		 * "SELECT m.txtFirstName,t.txtQuestion,t.txtAnswer,m.tblrolename.intRoleId FROM Mstquestionandanswer as t,Mstregistration as m WHERE"
		 * +
		 * " t.intTeacherId = m.intRegistrationId OR t.mstregistration.intRegistrationId= m.intRegistrationId"
		 * ; org.hibernate.Query q=s.createQuery(hql);
		 * List<Mstquestionandanswer> homeworkList = new ArrayList<>();
		 * List<Object[]> list = q.list(); for (Iterator iterator =
		 * list.iterator(); iterator.hasNext();) { Object[] objects = (Object[])
		 * iterator.next(); Mstquestionandanswer tblhomework = new
		 * Mstquestionandanswer(); Mstregistration mstregistration = new
		 * Mstregistration(); Tblrolename tblrolename = new Tblrolename(); for
		 * (int i = 0; i < objects.length; i++) {
		 * //System.out.println(objects[i]);
		 * mstregistration.setTxtFirstName((String)objects[0]);
		 * 
		 * tblhomework.setTxtQuestion((String)objects[1]);
		 * tblhomework.setTxtAnswer((String)objects[2]);
		 * tblrolename.setIntRoleId((Integer)objects[3]);
		 * mstregistration.setTblrolename(tblrolename);
		 * tblhomework.setMstregistration(mstregistration);
		 * 
		 * } homeworkList.add(tblhomework); } System.out.println("query success"
		 * ); return homeworkList;
		 */
	}

	/*
	 * @Override public String getTeachName(int teachId) { Session s=
	 * this.sessionFactory.openSession();
	 * 
	 * String hql =
	 * "SELECT a.txtFirstName FROM Mstregistration a where a.intRegistrationId=:intRegistrationId"
	 * ; org.hibernate.Query q=s.createQuery(hql);
	 * q.setParameter("intRegistrationId", teachId); q.uniqueResult();
	 * List<String> nameList=q.list(); String res=null; for(String ss:nameList){
	 * res=ss; } s.close(); return res; }
	 */

	/*@Override
	public String getTeachName(int teachId) {
		Session s = this.sessionFactory.openSession();
		String fN = null;
		String lN = null;
		String fullName = null;
		String hql = "FROM Mstregistration a where a.intRegistrationId=:intRegistrationId";
		org.hibernate.Query q = s.createQuery(hql);
		q.setParameter("intRegistrationId", teachId);
		q.uniqueResult();
		List<Mstregistration> objList = q.list();
		Iterator<Mstregistration> itr = objList.iterator();
		while (itr.hasNext()) {
			Mstregistration obj = (Mstregistration) itr.next();
			fN = obj.getTxtFirstName();
			lN = obj.getTxtLastName();
			fullName = fN + " " + lN;
		}
		s.close();
		return fullName;
	}*/
	 @Override
     public List<Mstregistration> getTeachName(int teachId) {
             Session s = this.sessionFactory.openSession();
             /*String fN = null;
             String lN = null;
             String fullName = null;*/
             String hql = "FROM Mstregistration a where a.tblrolename.intRoleId =:p1";
             org.hibernate.Query q = s.createQuery(hql).setParameter("p1",3);
             List<Mstregistration> objList = q.list();
             /*Iterator<Mstregistration> itr = objList.iterator();
             while (itr.hasNext()) {
                     Mstregistration obj = (Mstregistration) itr.next();
                     fN = obj.getTxtFirstName();
                     lN = obj.getTxtLastName();
                     fullName = fN + " " + lN;
             }*/
             s.close();
             return objList;
     }

	@Override
	public Mstregistration getClass(int stuId) {
		Session s = this.sessionFactory.openSession();
		System.out.println("bfr............");
		Mstregistration aa = (Mstregistration) s.get(Mstregistration.class, stuId);
		System.out.println("after..............");
		System.out.println(aa);
		s.close();
		return aa;
	}

	// student timetable
	@Override
	public ArrayList<ArrayList<Mststudenttimetable>> getStudentTT(int classId, int sectionId) {
		Session session = sessionFactory.openSession();
		ArrayList<ArrayList<Mststudenttimetable>> totalList = new ArrayList<>();
		ArrayList<Mststudenttimetable> list = new ArrayList<>();

		for (int i = 1; i <= 7; i++) {

			String hql = "from Mststudenttimetable a where a.mststudclass.intClassId = :intClassId and a.mststudclasssection.intSectionId = :intSectionId and a.intStudentStatus = :intStudentStatus and a.tbldaysofweek.intDaysId = :intDaysId";
			org.hibernate.Query q = session.createQuery(hql);
			q.setParameter("intClassId", classId);
			q.setParameter("intSectionId", sectionId);
			q.setParameter("intStudentStatus", 1);
			q.setParameter("intDaysId", i);

			list = (ArrayList<Mststudenttimetable>) q.list();
			Collections.sort(list, new StudentTimingsCompare());
			totalList.add(list);
		}
		session.close();
		return totalList;
	}

	@Override
	public Mstregistration getClassSection(int intRegId) {

		Session session = sessionFactory.openSession();
		Mstregistration regObj = null;
		String hql = "from Mstregistration a where a.intRegistrationId= :intRegistrationId"; //
		org.hibernate.Query q = session.createQuery(hql);

		q.setParameter("intRegistrationId", intRegId);

		List<Mstregistration> list = q.list();
		Iterator<Mstregistration> stuTTList = list.iterator();
		while (stuTTList.hasNext()) {
			regObj = (Mstregistration) stuTTList.next();

		}
		session.close();
		return regObj;
	}

	@Override
	public ArrayList<Msttimings> getTimings() {
		Session s = this.sessionFactory.openSession();
		org.hibernate.Query q = s.createQuery("from Msttimings");
		ArrayList<Msttimings> list = (ArrayList<Msttimings>) q.list();
		s.close();
		return list;

	}

	@Override
	public ArrayList<Tbldaysofweek> getDays() {
		Session s = this.sessionFactory.openSession();
		org.hibernate.Query q = s.createQuery("from Tbldaysofweek");
		ArrayList<Tbldaysofweek> list = (ArrayList<Tbldaysofweek>) q.list();
		s.close();
		return list;
	}

	// student notification
	public List<Tblinvites> getStudentNotificationByName(int id) {

		Session s = this.sessionFactory.openSession();

		String hql = "SELECT i.intInvitesId,i.dtDate, i.txtInvitationTitle , i.txtBody,i.txtfileName FROM Tblinvites"
				+ " AS i INNER JOIN Mstregistration AS r ON ((i.intClassId=r.intClassId "
				+ "AND i.intSectionId=r.intSectionId) " + "OR (i.intSectionId=5 AND i.intClassId=r.intClassId) OR ( "
				+ "i.intClassId=13)) WHERE r.intRegistrationId='" + id + "' order by i.dtDate desc";

		org.hibernate.Query query = s.createSQLQuery(hql);

		List<Tblinvites> tblntfList = new ArrayList<>();
		List<Object[]> list = query.list();
		s.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Tblinvites tblinvites = new Tblinvites();
			for (int i = 0; i < objects.length; i++) {

				System.out.println("=================" + objects[i]);
				tblinvites.setTxtInvitationTitle((String) objects[2]);
				tblinvites.setTxtBody((String) objects[3]);
				tblinvites.setDtDate((Date) objects[1]);
				tblinvites.setIntInvitesId((Integer) objects[0]);
				tblinvites.setTxtfileName((String) objects[4]);
			}
			tblntfList.add(tblinvites);
		}
		System.out.println("query success for invitation sent student");
		return tblntfList;

	}

	@Override
	public Tblinvites getAdminNotification(Integer id) {
		Session s = this.sessionFactory.openSession();
		Tblinvites m = (Tblinvites) s.get(Tblinvites.class, id);
		s.close();
		return m;
	}

	/*
	 * @Override public List<Mstteacherinvite> getTeacherNotificationByName() {
	 * Session s= this.sessionFactory.openSession(); org.hibernate.Query
	 * q=s.createQuery("from Mstteacherinvite"); List<Mstteacherinvite> result=
	 * q.list(); s.close(); return result; }
	 */

	@Override
	public List<Mstteacherinvite> getTeacherNotificationByName(int id,int classId, int sectionId) {
		
		Session session = sessionFactory.openSession();

		String hql = "FROM Mstteacherinvite AS c WHERE c.dtAssignDate BETWEEN :oneWeekBefore AND :curDt and c.mstregistration.intRegistrationId= :id";
		
		String hql1 = "from Mstteacherinvite t where t.intStatus=2 and (t.mstregistration.intRegistrationId = '" + id + "') or "
				+ "(t.mstregistration.intRegistrationId is NULL and t.intStatus=2 and t.mststudclass.intClassId = '" + classId + "' and t.mststudclasssection.intSectionId = '"
				+ sectionId + "')order by t.dtInvitesDate desc";

		List<Mstteacherinvite> list = session.createQuery(hql1).list();
		session.close();

		return list;
		// AND m.intRegistrationId = t.mstregistration.intRegistrationId
		/*String hql = "SELECT distinct t.dtInvitesDate,t.txtMessage FROM Mstregistration m , Mstteacherinvite t WHERE "
				+ "((m.mststudclass.intClassId = t.mststudclass.intClassId AND "
				+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId"
				+ " )  AND m.intRegistrationId='" + id + "') or t.mstregistration.intRegistrationId = '" + id
				+ "' order by t.dtInvitesDate desc ";*/

		/*org.hibernate.Query query = session.createQuery(hql);
		List<Mstteacherinvite> tblntfListSentByTeacher = new ArrayList<>();
		List<Object[]> list = query.list();
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstteacherinvite mstteacherinvite = new Mstteacherinvite();
			for (int i = 0; i < objects.length; i++) {
				// System.out.println("====== "+objects[i]);
				mstteacherinvite.setDtInvitesDate((Date) objects[0]);
				mstteacherinvite.setTxtMessage((String) objects[1]);
			}
			tblntfListSentByTeacher.add(mstteacherinvite);
		}
		System.out.println("query success for invitation sent by teacher");
		return tblntfListSentByTeacher;*/
	}

	// student marks

	@Override
	public Mstuploadstudmarksheet getMarkSheet(int id) {
		Session s = this.sessionFactory.openSession();
		Mstuploadstudmarksheet m = (Mstuploadstudmarksheet) s.get(Mstuploadstudmarksheet.class, id);
		s.close();
		return m;

	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Mstuploadstudmarksheet> getStudentMarksByName(int
	 * id) { Session s= this.sessionFactory.openSession(); org.hibernate.Query
	 * q=s.createQuery("from Mstuploadstudmarksheet");
	 * List<Mstuploadstudmarksheet> result= q.list(); s.close(); return result;
	 * }
	 */

	@Override
	public List<Mstuploadstudmarksheet> getStudentMarksByName(int id) {

		Session session = this.sessionFactory.openSession();

		String sn = "select t.dtDate,t.mststudclass,t.mststudclasssection"
				+ ", t.mstterms,t.intUploadStudMarksheetId,t.txtfileName from Mstregistration m,Mstuploadstudmarksheet t where"
				+ " (m.mststudclass.intClassId = t.mststudclass.intClassId and m.mststudclasssection.intSectionId"
				+ "=t.mststudclasssection.intSectionId and  t.mstregistration.intRegistrationId=m.intRegistrationId)"
				+ " and m.intRegistrationId ='" + id + "' order by t.dtDate desc";

		org.hibernate.Query query = session.createQuery(sn);
		// query.setDate("d", date);
		/*
		 * System.out.println("Query=" + query); List<Mstuploadstudmarksheet>
		 * result = query.list(); session.close(); return result;
		 */
		List<Mstuploadstudmarksheet> mstuploadstudmarksheets = new ArrayList<>();
		List<Object[]> list = query.list();
		System.out.println("list:" + list);
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstuploadstudmarksheet marksheet = new Mstuploadstudmarksheet();
			for (int i = 0; i < objects.length; i++) {
				System.out.println("........" + objects[i]);
				marksheet.setDtDate((Date) objects[0]);

				// Mststudclass mststudclass= new Mststudclass();
				// mststudclass.setIntClassId((Integer)objects[1]);
				// \ marksheet.setMststudclass((mststudclass));
				marksheet.setMststudclass((Mststudclass) (objects[1]));

				// Mststudclasssection mststudclasssection = new
				// Mststudclasssection();
				// mststudclasssection.setIntSectionId((Integer)objects[2]);
				// marksheet.setMststudclasssection(mststudclasssection);
				marksheet.setMststudclasssection((Mststudclasssection) objects[2]);

				/*
				 * Mstterms mstterms = new Mstterms();
				 * mstterms.setIntTermId((Integer)objects[3]);
				 * marksheet.setMstterms(mstterms);
				 */

				marksheet.setMstterms((Mstterms) objects[3]);
				marksheet.setIntUploadStudMarksheetId((Integer) objects[4]);
				marksheet.setTxtfileName((String) objects[5]);

			}
			mstuploadstudmarksheets.add(marksheet);
		}
		System.out.println("query success");
		return mstuploadstudmarksheets;
	}

	/*
	 * @Override public List<Object[]> getStuTimeTablebyClass() { // TODO
	 * Auto-generated method stub return null; }
	 */

	// student attendance
	@SuppressWarnings("unchecked")
	@Override
	public List<Trnattendancerecord> getStudAttendance(int studId, java.util.Date curDt) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Trnattendancerecord t where t.mstregistration.intRegistrationId= :intRegistrationId and t.dtSlotDate= :dtSlotDate"; //
		org.hibernate.Query q = session.createQuery(hql);

		q.setParameter("intRegistrationId", studId);
		q.setParameter("dtSlotDate", curDt);
		List<Trnattendancerecord> list = q.list();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getStuNames(int studId) {
		Session session = this.sessionFactory.openSession();
		String hql = "SELECT a.txtFirstName FROM Mstregistration a where a.intRegistrationId= :intRegistrationId";
		org.hibernate.Query q = session.createQuery(hql);
		q.setParameter("intRegistrationId", studId);
		q.uniqueResult();

		List<String> results = q.list();
		session.close();
		System.out.println(results);
		String res = null;
		for (String s : results) {
			res = s;
		}
		System.out.println(res);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getStudentListe(int intRegistrationId) {
		Session session = this.sessionFactory.openSession();
		String hql = "SELECT p.intRegistrationId FROM Mstregistration p where p.intRegistrationId= :intRegistrationId";
		org.hibernate.Query q = session.createQuery(hql);
		/*
		 * q.setParameter("intClassId", intClassId);
		 * q.setParameter("intSectionId", intSectionId);
		 */
		q.setParameter("intRegistrationId", intRegistrationId);
		q.uniqueResult();

		List<Integer> lists = q.list();
		session.close();
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trnattendancerecord> getStudentAttendFromToDate(java.util.Date startDate, java.util.Date endDate,
			int id) {
		Session session = this.sessionFactory.openSession();
		List<Trnattendancerecord> list = session
				.createQuery(
						"FROM Trnattendancerecord AS c WHERE c.dtSlotDate BETWEEN :startDate AND :endDate and c.mstregistration.intRegistrationId= :id order by c.dtSlotDate desc")
				.setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("id", id)

				/*
				 * .createCriteria(Trnattendancerecord.class)
				 * .add(Restrictions.between("dtSlotDate", startDate, endDate))
				 */

				.list();
		session.close();
		return list;

	}

	// homework
	@SuppressWarnings("unchecked")
	public List<Tblhomework> getHW(java.util.Date curDt, int regId, int classId, int sectionId) {
		Session session = sessionFactory.openSession();
		String hql = "from Tblhomework where dtAssignDate= :dtAssignDate"; //

		String hql1 = "from Tblhomework t where (t.mstregistration.intRegistrationId = '" + regId + "' and dtAssignDate= :dtAssignDate) or "
				+ "(t.mstregistration.intRegistrationId is NULL and t.mststudclass.intClassId = '" + classId
				+ "' and t.mststudclasssection.intSectionId = '" + sectionId + "'"
				+ " and dtAssignDate= :dtAssignDate)";
		org.hibernate.Query q = session.createQuery(hql1);

		q.setParameter("dtAssignDate", curDt);

		List<Tblhomework> list = q.list();
		System.out.println(list.size());
		session.close();
		return list;

	}

	@Override
	public List<Mstteacherproject> getProject(java.util.Date curDt, int regId, int classId, int sectionId) {
		String hql = "from Mstteacherproject t where t.dtAssignDate= :dtAssignDate";

		String hql1 = "from Mstteacherproject t where (t.mstregistration.intRegistrationId = '" + regId + "'  and dtAssignDate= :dtAssignDate) or "
				+ "(t.mstregistration.intRegistrationId is NULL and t.mststudclass.intClassId = '" + classId
				+ "' and t.mststudclasssection.intSectionId = '" + sectionId + "'"
				+ " and dtAssignDate= :dtAssignDate)";
		Session session = sessionFactory.openSession();
		org.hibernate.Query q = session.createQuery(hql1);

		q.setParameter("dtAssignDate", curDt);

		List list = q.list();
		session.close();
		return list;
	}

	@Override
	public List<Tblhomework> getHWFromTo(Date oneWeekBefore, Date curDt, int id, int classId, int sectionId) {
		Session session = sessionFactory.openSession();

		String hql = "FROM Tblhomework AS c WHERE c.dtAssignDate BETWEEN :oneWeekBefore AND :curDt and c.mstregistration.intRegistrationId= :id";
		String hql1 = "from Tblhomework t where (t.mstregistration.intRegistrationId = '" + id + "' and dtAssignDate BETWEEN :oneWeekBefore AND :curDt) or "
				+ "(t.mstregistration.intRegistrationId is NULL and t.mststudclass.intClassId = '" + classId
				+ "' and t.mststudclasssection.intSectionId = '" + sectionId + "'"
				+ " and dtAssignDate BETWEEN :oneWeekBefore AND :curDt) order by t.dtAssignDate desc";

		List<Tblhomework> list = session.createQuery(hql1).setParameter("oneWeekBefore", oneWeekBefore)
				.setParameter("curDt", curDt).list();
		session.close();

		return list;
	}

	@Override
	public List<Mstteacherproject> getProFromTo(Date oneWeekBefore, Date curDt, int id, int classId, int sectionId) {
		Session session = sessionFactory.openSession();

		String hql = "FROM Mstteacherproject AS c WHERE c.dtAssignDate BETWEEN :oneWeekBefore AND :curDt and c.mstregistration.intRegistrationId= :id";

		String hql1 = "from Mstteacherproject t where (t.mstregistration.intRegistrationId = '" + id + "' and dtAssignDate BETWEEN :oneWeekBefore AND :curDt) or "
				+ "(t.mstregistration.intRegistrationId is NULL and t.mststudclass.intClassId = '" + classId
				+ "' and t.mststudclasssection.intSectionId = '" + sectionId + "'"
				+ " and dtAssignDate BETWEEN :oneWeekBefore AND :curDt) order by t.dtAssignDate desc";
		List<Mstteacherproject> list = session.createQuery(hql1).setParameter("oneWeekBefore", oneWeekBefore)
				.setParameter("curDt", curDt).list();
		session.close();

		return list;
	}

	/*
	 * @Override public String getTeacherName(int teachId) { Session session =
	 * sessionFactory.openSession();
	 * 
	 * String hql =
	 * "SELECT a.txtFirstName FROM Mstregistration a where a.intRegistrationId= :intRegistrationId"
	 * ; org.hibernate.Query q = session.createQuery(hql);
	 * q.setParameter("intRegistrationId", teachId); q.uniqueResult();
	 * 
	 * List<String> results = q.list(); System.out.println(results); String res
	 * = null; for (String s : results) { res = s; } System.out.println(res);
	 * session.close();
	 * 
	 * return res; }
	 */

	@Override
	public String getTeacherName(int teachId) {
		Session session = sessionFactory.openSession();

		String hql = "from Mstregistration a where a.intRegistrationId= :intRegistrationId";
		org.hibernate.Query q = session.createQuery(hql);
		q.setParameter("intRegistrationId", teachId);
		q.uniqueResult();

		List<Mstregistration> results = q.list();
		String res = null;
		for (Mstregistration s : results) {
			String fN = s.getTxtFirstName();
			String lN = s.getTxtLastName();
			res = fN + " " + lN;
		}
		session.close();
		return res;
	}

	// Syllabus
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Mstsyllabus> getStudentSyllabus(int intRegId) {

		Session session = this.sessionFactory.openSession();
		String hql = "from Mstsyllabus t where t.mststudclass.intClassId = :intClassId order by t.intSubjectId desc"; //
		org.hibernate.Query q = session.createQuery(hql);

		q.setParameter("intClassId", intRegId);
		List<Mstsyllabus> list = q.list();
		session.close();
		return (ArrayList<Mstsyllabus>) list;
	}

	@Override
	public Mstsyllabus getStudentsSyllabus(Integer id) {
		Session s = this.sessionFactory.openSession();
		Mstsyllabus m = (Mstsyllabus) s.get(Mstsyllabus.class, id);
		s.close();
		return m;
	}

}
