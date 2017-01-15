package com.aikshika.parent.daoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aikshika.common.DateComparator;
import com.aikshika.common.StudentTimingsCompare;
import com.aikshika.entity.Mstbloodgroup;
import com.aikshika.entity.Mstgender;
import com.aikshika.entity.Mstleave;
import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mststudclass;
import com.aikshika.entity.Mststudclasssection;
import com.aikshika.entity.Mststudenttimetable;
import com.aikshika.entity.Mstteacherinvite;
import com.aikshika.entity.Mstteacherproject;
import com.aikshika.entity.Mstterms;
import com.aikshika.entity.Msttimings;
import com.aikshika.entity.Mstuploadstudmarksheet;
import com.aikshika.entity.Tbldaysofweek;
import com.aikshika.entity.Tblhomework;
import com.aikshika.entity.Tblinvites;
import com.aikshika.entity.Tblparentqueries;
import com.aikshika.entity.Trnattendancerecord;
import com.aikshika.entity.Trnstudentleaveapp;
import com.aikshika.parent.dao.ParentDAO;

@Repository("parentdao")
@Transactional
public class ParentDAOImpl implements ParentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Session session = null; Transaction tx = null;
	 */

	@Override
	public List<Mstregistration> getStudentInformationByName(List<Mstlogin> list) {
		Session session = this.sessionFactory.openSession();

		String sn = "FROM Mstregistration as m where m.tblrolename.intRoleId = 1 and m.intRegistrationId in (:id)";

		/*
		 * org.hibernate.Query query =
		 * this.sessionFactory.getCurrentSession().createQuery(sn);
		 */
		org.hibernate.Query query = session.createQuery(sn);
		query.setParameterList("id", list);
		List<Mstregistration> result = query.list();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mstregistration> getStudentDetailByName(int parentId, String studentName) {
		Session session = this.sessionFactory.openSession();
		String sn = "from Mstregistration c where c.txtFirstName like '" + studentName + "%'";
		String hql = "Select m.txtFirstName,m.txtLastName,m.dtDob,m.mstgender.txtGenderName,m.mststudclass.txtClassName,"
				+ "m.mststudclasssection.txtSectionName,m.mstbloodgroup.txtBloodGroupName,m.txtPerAddress,m.txtFmobileNumber,m.txtMmobileNumber,"
				+ " m.txtFotherNumber, m.txtMotherNumber ,m.intRegistrationId from Mstregistration m , Mstlogin l where m.intRegistrationId = l.mstregistration.intRegistrationId and m.txtFirstName = '"
				+ studentName + "' and l.intParentId='" + parentId + "'";
		
		
		
		List<Mstregistration> result = new ArrayList<>();
		/*org.hibernate.Query query = session.createQuery(hql);
		System.out.println("Query=" + query);
		List<Mstregistration> result = query.list();*/
		org.hibernate.Query query = session.createQuery(hql);
		List<Object[]> o = query.list();
		for (Iterator iterator = o.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstregistration m = new Mstregistration();
			for (int i = 0; i < objects.length; i++) {
				m.setTxtFirstName((String) objects[0]);
				m.setTxtLastName((String) objects[1]);
				m.setDtDob((Date) objects[2]);
				Mstgender gen = new Mstgender();
				gen.setTxtGenderName((String) objects[3]);
				m.setMstgender(gen);
				
				Mststudclass c = new Mststudclass();
				c.setTxtClassName((String) objects[4]);
				m.setMststudclass(c);
				
				Mststudclasssection s = new Mststudclasssection();
				s.setTxtSectionName((String) objects[5]);
				m.setMststudclasssection(s);
				
				Mstbloodgroup g = new Mstbloodgroup();
				g.setTxtBloodGroupName((String) objects[6]);
				m.setMstbloodgroup(g);
				
				m.setTxtPerAddress((String) objects[7]);
				m.setTxtFmobileNumber((String) objects[8]);
				m.setTxtMmobileNumber((String) objects[9]);
				m.setTxtFotherNumber((String) objects[10]);
				m.setTxtMotherNumber((String) objects[11]);
				m.setIntRegistrationId((Integer) objects[12]);
			}
			result.add(m);
			
		}
		session.close();
		return result;
	}

	@Override
	public List<Trnattendancerecord> getStudentAttendanceByName() {
		Session session = this.sessionFactory.openSession();
		String sn = "from Trnattendancerecord";
		org.hibernate.Query query = session.createQuery(sn);
		System.out.println("Query=" + query);
		List<Trnattendancerecord> result = query.list();
		session.close();
		return result;
	}

	/*
	 * @Override public List<Mstregistration> getStudentMarksByName() { // TODO
	 * Auto-generated method stub String sn = "from Mstregistration";
	 * org.hibernate.Query query =
	 * this.sessionFactory.getCurrentSession().createQuery(sn);
	 * System.out.println("Query=" + query); List<Mstregistration> result =
	 * query.list();
	 * 
	 * return result; }
	 */
	// a
	/*
	 * @Override public List<Mstuploadstudmarksheet> getStudentMarksByName() {
	 * Session session = this.sessionFactory.openSession(); String sn =
	 * "from Mstuploadstudmarksheet"; org.hibernate.Query query =
	 * session.createQuery(sn); System.out.println("Query=" +query);
	 * List<Mstuploadstudmarksheet> result= query.list(); session.close();
	 * 
	 * return result; }
	 */

	@Override
	public List<Mstuploadstudmarksheet> getStudentMarksListByName(String studentName) {

		Session session = this.sessionFactory.openSession();

		String sn = "select t.dtDate,t.mststudclass,t.mststudclasssection"
				+ ", t.mstterms,t.intUploadStudMarksheetId,t.txtfileName from Mstregistration m,Mstuploadstudmarksheet t where"
				+ " (m.mststudclass.intClassId = t.mststudclass.intClassId and m.mststudclasssection.intSectionId"
				+ "=t.mststudclasssection.intSectionId and  t.mstregistration.intRegistrationId=m.intRegistrationId)"
				+ " and m.txtFirstName ='" + studentName + "' ORDER BY  t.dtDate DESC";
		/*
		 * String hql2 = ""; String fn = ""; int reg = 0; for (Iterator iterator
		 * = list.iterator(); iterator.hasNext();) { Integer mstlogin =
		 * (Integer) iterator.next(); reg = mstlogin; Mstregistration object =
		 * (Mstregistration) session.get(Mstregistration.class, reg);
		 * 
		 * if(object.getTxtFirstName().equals(studentName)) {
		 * 
		 * 
		 * hql2 =
		 * "SELECT distinct t.dtDate,t.mststudclass,t.mststudclasssection" +
		 * ", t.mstterms,t.intUploadStudMarksheetId,t.txtfileName FROM Mstregistration m,Mstuploadstudmarksheet t WHERE "
		 * + "((m.mststudclass.intClassId = t.mststudclass.intClassId AND " +
		 * "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId and  t.mstregistration.intRegistrationId=m.intRegistrationId"
		 * +
		 * " and t.mstregistration.intRegistrationId is null AND m.txtFirstName='"
		 * + studentName +
		 * "')) or( m.mststudclass.intClassId = t.mststudclass.intClassId AND "
		 * +
		 * "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId and ( t.mstregistration.intRegistrationId = '"
		 * +reg+"'" + "  AND m.txtFirstName='" + studentName +
		 * "')) order by t.dtAssignDate desc";
		 * 
		 * } }
		 */
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

	@Override
	public void leaveApp(Trnstudentleaveapp trnLeave) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		trnLeave.setIntStatus(1);
		session.saveOrUpdate(trnLeave);
		session.close();
	}

	@Override
	public List<Mstleave> getLeave() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		String sn = "from Mstleave";
		org.hibernate.Query query = session.createQuery(sn);
		List<Mstleave> result = query.list();
		session.close();
		return result;
	}

	/*
	 * @Override public List<Mststudenttimetable> getStudentTimeTable(String
	 * studentName) { // TODO Auto-generated method stub Session session =
	 * this.sessionFactory.openSession(); String hql =
	 * "from Mststudenttimetable "; org.hibernate.Query query =
	 * session.createQuery(hql);
	 * 
	 * List<Mststudenttimetable> result = query.list(); session.close(); return
	 * result; }
	 */

	/*
	 * @Override public List<Trnattendancerecord>
	 * getStudentAttendanceBetweenTwoDates(int intRegId, String name1,Date d1,
	 * Date d2) {
	 * 
	 * Session session = this.sessionFactory.openSession();
	 * 
	 * String hql = "FROM Trnattendancerecord AS t WHERE " +
	 * "t.mstregistration.intRegistrationId = 4 and t.dtSlotDate between '" + d1
	 * + "' and '" + d2 + "'";
	 * 
	 * org.hibernate.Query query = session.createQuery(hql);
	 * List<Trnattendancerecord> result = query.list();
	 * System.out.println("=======" + result); session.close(); return result; }
	 */

	/*@Override
	public ArrayList<ArrayList<Mststudenttimetable>> getStudentTT(int classId, int sectionId) {
		Session session = sessionFactory.openSession();
		ArrayList<ArrayList<Mststudenttimetable>> totalList = new ArrayList<>();
		ArrayList<Mststudenttimetable> list = new ArrayList<>();

		for (int i = 1; i <= 7; i++) {

			String hql = "from Mststudenttimetable a where a.mststudclass.intClassId = :intClassId "
					+ "and a.mststudclasssection.intSectionId = :intSectionId and a.intStudentStatus = :intStudentStatus "
					+ "and a.tbldaysofweek.intDaysId = :intDaysId";
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
*/
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

	/*@Override
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
	}*/

	public List<Trnattendancerecord> getStudentAttendanceBetweenTwoDates(int ParentId, String name, Date d1, Date d2) {

		Session session = this.sessionFactory.openSession();
		String hql = " Select t.dtSlotDate,t.dtSlotTime FROM Trnattendancerecord AS t ,Mstlogin as m WHERE "
				+ "t.mstregistration.intRegistrationId = m.mstregistration.intRegistrationId and t.dtSlotDate between '"
				+ d1 + "' and" + " '" + d2 + "' and m.intParentId='" + ParentId
				+ "' and t.mstregistration.txtFirstName='" + name + "' order by t.dtSlotDate desc";

		org.hibernate.Query query = session.createQuery(hql);
		System.out.println("b4");
		// List<Trnattendancerecord> trnlist = query.list();
		// System.out.println("After result "+trnlist);

		// session.close();
		List<Trnattendancerecord> trnlist = new ArrayList<>();
		List<Object[]> list = query.list();
		System.out.println("list in between:" + list);
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Trnattendancerecord trnattendancerecord = new Trnattendancerecord();
			for (int i = 0; i < objects.length; i++) {
				// System.out.println(".......aaa = " + objects[i]);
				trnattendancerecord.setDtSlotDate((Date) objects[0]);
				trnattendancerecord.setDtSlotTime((String) objects[1]);
				// System.out.println("inside for for loop");

			}
			trnlist.add(trnattendancerecord);
		}
		System.out.println("query success");
		// return homeworkList;
		return trnlist;
	}

	@Override
	public List<Tblhomework> getStudentHomeWorkByName(String studentName, Date date) {

		Session session = this.sessionFactory.openSession();
		String hql = "from Mstregistration  m INNER JOIN Tblhomework ON m.intClassId = Tlhomework.intClassId"
				+ "and m.intSectionId = Tblhomework.intSectionId where m.txtFirstName ='" + studentName + "'";

		String hql1 = "select t.txtSubject,t.txtHomeWork,t.dtDueDate,t.dtAssignDate from Mstregistration m,Tblhomework t where"
				+ " (m.mststudclass.intClassId = t.mststudclass.intClassId and m.mststudclasssection.intSectionId"
				+ "=t.mststudclasssection.intSectionId) and m.txtFirstName ='" + studentName
				+ "' AND t.dtAssignDate =:d order by t.dtAssignDate desc";

		/*
		 * String s =
		 * "SELECT t.txtSubject,t.txtHomeWork,t.dtDueDate FROM Mstregistration m,Tblhomework t "
		 * +
		 * "WHERE (m.intClassId = t.intClassId AND m.intSectionId=t.intSectionId) "
		 * + "AND m.txtFirstName ='"+studentName+"' AND t.dtAssignDate = '"
		 * +date+"'";
		 */
		org.hibernate.Query query = session.createQuery(hql1);
		query.setDate("d", date);

		List<Tblhomework> homeworkList = new ArrayList<>();
		List<Object[]> list = query.list();
		System.out.println("list:" + list);
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Tblhomework tblhomework = new Tblhomework();
			for (int i = 0; i < objects.length; i++) {
				System.out.println("........" + objects[i]);

				tblhomework.setTxtSubject((String) objects[0]);
				tblhomework.setTxtHomeWork((String) objects[1]);
				tblhomework.setDtDueDate((Date) objects[2]);
				tblhomework.setDtAssignDate((Date) objects[3]);
			}
			homeworkList.add(tblhomework);
		}

		System.out.println("query success");
		return homeworkList;
	}

	@Override
	public List<Tblhomework> getStudentHomeWorkByName(List<Mstlogin> list, String studentName) {

		Session session = this.sessionFactory.openSession();
		String hql = "from Mstregistration  INNER JOIN Tblhomework ON intClassId = Tlhomework.intClassId"
				+ "and m.intSectionId = Tblhomework.intSectionId where m.txtFirstName ='" + studentName + "'";

		/*
		 * String hql1 =
		 * "select t.txtSubject,t.txtHomeWork,t.dtDueDate from Mstregistration m,Tblhomework t where"
		 * +
		 * " (m.mststudclass.intClassId = t.mststudclass.intClassId and m.mststudclasssection.intSectionId"
		 * + "=t.mststudclasssection.intSectionId) and m.txtFirstName ='" +
		 * studentName + "' order by t.dtDueDate desc";
		 */

		String hql1 = "";
		String fn = "";
		String sql = "";
		int reg = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Integer mstlogin = (Integer) iterator.next();
			reg = mstlogin;
			Mstregistration object = (Mstregistration) session.get(Mstregistration.class, reg);

			if (object.getTxtFirstName().equals(studentName)) {

				sql = "SELECT DISTINCT t.txtSubject,t.txtHomeWork,t.dtDueDate,t.dtAssignDate FROM Mstregistration m,Tblhomework t  WHERE"
						+ "((m.intClassId = t.intClassId AND m.intSectionId=t.intSectionId"
						+ " AND t.intRegistrationId IS NULL AND m.txtFirstName='" + studentName
						+ "')) OR( m.intClassId = t.intClassId AND "
						+ "m.intSectionId=t.intSectionId AND ( t.intRegistrationId = '" + reg + "'"
						+ "AND m.txtFirstName='" + studentName + "')) ORDER BY t.dtAssignDate DESC";

				hql1 = "SELECT distinct t.txtSubject,t.txtHomeWork,t.dtDueDate,t.dtAssignDate FROM Mstregistration m,Tblhomework t  WHERE "
						+ "((m.mststudclass.intClassId = t.mststudclass.intClassId AND "
						+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId"
						+ " and t.mstregistration.intRegistrationId is null AND m.txtFirstName='" + studentName
						+ "')) or( m.mststudclass.intClassId = t.mststudclass.intClassId AND "
						+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId and ( t.mstregistration.intRegistrationId = '"
						+ reg + "'" + "  AND m.txtFirstName='" + studentName + "')) order by t.dtAssignDate desc";

			}
		}
		org.hibernate.Query query = session.createSQLQuery(sql);
		List<Tblhomework> homeworkList = new ArrayList<>();
		List<Object[]> list1 = query.list();
		session.close();
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Tblhomework tblhomework = new Tblhomework();
			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);

				tblhomework.setTxtSubject((String) objects[0]);
				tblhomework.setTxtHomeWork((String) objects[1]);
				tblhomework.setDtDueDate((Date) objects[2]);
				tblhomework.setDtAssignDate((Date) objects[3]);
			}
			homeworkList.add(tblhomework);
		}
		System.out.println("homework list = " + homeworkList);
		for (Iterator iterator = homeworkList.iterator(); iterator.hasNext();) {
			Tblhomework t = (Tblhomework) iterator.next();
			System.out.print("homework:" + t.getTxtHomeWork() + " ");
			System.out.print("Assigned date" + t.getDtAssignDate());
			System.out.println();
		}

		System.out.println("query success");
		return homeworkList;
	}

	@Override
	public List<Mstteacherproject> getStudentProjectByName(List<Mstlogin> list, String studentName) {
		Session session = this.sessionFactory.openSession();
		String hql1 = "select t.txtProjectName,t.txtProjectDescription,t.dtDueDate from Mstregistration m,Mstteacherproject t where"
				+ " (m.mststudclass.intClassId = t.mststudclass.intClassId and m.mststudclasssection.intSectionId"
				+ "=t.mststudclasssection.intSectionId) and m.txtFirstName ='" + studentName
				+ "' order by t.dtDueDate desc";

		String hql2 = "";
		String fn = "";
		int reg = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Integer mstlogin = (Integer) iterator.next();
			reg = mstlogin;
			Mstregistration object = (Mstregistration) session.get(Mstregistration.class, reg);

			if (object.getTxtFirstName().equals(studentName)) {

				hql2 = "SELECT distinct t.txtProjectName,t.txtProjectDescription,t.dtDueDate FROM Mstregistration m,Mstteacherproject t WHERE "
						+ "((m.mststudclass.intClassId = t.mststudclass.intClassId AND "
						+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId"
						+ " and t.mstregistration.intRegistrationId is null AND m.txtFirstName='" + studentName
						+ "')) or( m.mststudclass.intClassId = t.mststudclass.intClassId AND "
						+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId and ( t.mstregistration.intRegistrationId = '"
						+ reg + "'" + "  AND m.txtFirstName='" + studentName + "')) order by t.dtAssignDate desc";

			}
		}
		org.hibernate.Query query = session.createQuery(hql2);
		List<Mstteacherproject> projectWorkList = new ArrayList<>();
		List<Object[]> list1 = query.list();
		session.close();
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstteacherproject mstteacherproject = new Mstteacherproject();
			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);

				mstteacherproject.setTxtProjectName((String) objects[0]);
				mstteacherproject.setTxtProjectDescription((String) objects[1]);
				mstteacherproject.setDtDueDate((Date) objects[2]);
			}
			projectWorkList.add(mstteacherproject);
		}
		System.out.println("query success");
		return projectWorkList;
	}

	@Override
	public List<Trnattendancerecord> getStudentAttendanceByName(String studentName) {

		Session session = this.sessionFactory.openSession();
		String hql1 = "Select dtSlotTime, dtSlotDate,mstregistration from Trnattendancerecord as t where t.mstregistration.txtFirstName "
				+ "= '" + studentName + "' and t.dtSlotDate = current_date() ";
		org.hibernate.Query query = session.createQuery(hql1);
		// List<Trnattendancerecord> result = query.list();
		// return result;
		List<Trnattendancerecord> atdList = new ArrayList<>();
		List<Object[]> list = query.list();
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Trnattendancerecord trnattendancerecord = new Trnattendancerecord();
			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);
				trnattendancerecord.setDtSlotTime((String) objects[0]);
				trnattendancerecord.setDtSlotDate((Date) objects[1]);
			}
			atdList.add(trnattendancerecord);
		}
		System.out.println("query success");
		return atdList;
	}

	@Override
	public List<Tblinvites> getNotificationByStudentName(String studentName) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		/*
		 * String hql =
		 * "SELECT distinct t.txtInvitationTitle,t.txtBody,t.dtDate FROM Mstregistration m , Tblinvites t WHERE "
		 * + "(m.mststudclass.intClassId = t.mststudclass.intClassId AND " +
		 * "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId)"
		 * + " AND m.txtFirstName='" + studentName +
		 * "' OR (t.mststudclass.intClassId =13) OR (m.mststudclass.intClassId = t.mststudclass.intClassId"
		 * + " AND t.mststudclasssection.intSectionId = 5)";
		 */

		String hql = "SELECT i.dtDate, i.txtInvitationTitle , i.txtBody,i.intInvitesId,i.txtfileName FROM Tblinvites"
				+ " AS i INNER JOIN Mstregistration AS r ON ((i.intClassId=r.intClassId "
				+ "AND i.intSectionId=r.intSectionId) " + "OR (i.intSectionId=5 AND i.intClassId=r.intClassId) OR ( "
				+ "i.intClassId=13)) WHERE r.txtFirstName='" + studentName + "' order by i.dtDate desc";

		org.hibernate.Query query = session.createSQLQuery(hql);

		List<Tblinvites> tblntfList = new ArrayList<>();
		List<Object[]> list = query.list();
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Tblinvites tblinvites = new Tblinvites();
			for (int i = 0; i < objects.length; i++) {
				// System.out.println("================="+objects[i]);
				tblinvites.setTxtInvitationTitle((String) objects[1]);
				tblinvites.setTxtBody((String) objects[2]);
				tblinvites.setDtDate((Date) objects[0]);
				tblinvites.setIntInvitesId((Integer) objects[3]);
				tblinvites.setTxtfileName((String) objects[4]);
			}
			tblntfList.add(tblinvites);
		}
		System.out.println("query success for invitation sent to student");
		return tblntfList;
	}

	@Override
	public List<Mstteacherinvite> getNotificationToStudentNameByTeacher(List<Mstlogin> list, String studentName) {
		Session session = this.sessionFactory.openSession();
		// AND m.intRegistrationId = t.mstregistration.intRegistrationId
		String hql = "SELECT t.dtInvitesDate,t.txtMessage FROM Mstregistration m , Mstteacherinvite t WHERE "
				+ "(m.mststudclass.intClassId = t.mststudclass.intClassId AND "
				+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId"
				+ " or t.mstregistration.intRegistrationId = m.intRegistrationId  ) and m.intRegistrationId in (:id)"
				+ "  AND m.txtFirstName='" + studentName + "' order by t.dtInvitesDate desc";
		String hql1 = "";
		String fn = "";
		int reg = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Integer mstlogin = (Integer) iterator.next();
			reg = mstlogin;
			Mstregistration object = (Mstregistration) session.get(Mstregistration.class, reg);

			if (object.getTxtFirstName().equals(studentName)) {
				hql1 = "SELECT distinct t.dtInvitesDate,t.txtMessage FROM Mstregistration m , Mstteacherinvite t WHERE "
						+ "((m.mststudclass.intClassId = t.mststudclass.intClassId AND "
						+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId"
						+ " and t.mstregistration.intRegistrationId is null and t.intStatus=2 AND  m.txtFirstName='" + studentName
						+ "')) or( m.mststudclass.intClassId = t.mststudclass.intClassId AND "
						+ "m.mststudclasssection.intSectionId=t.mststudclasssection.intSectionId and t.intStatus=2 and ( t.mstregistration.intRegistrationId = '"
						+ reg + "'" + "  AND m.txtFirstName='" + studentName + "')) order by t.dtInvitesDate desc";

			}
		}

		org.hibernate.Query query = session.createQuery(hql1);
		// query.setParameterList("id", list);
		List<Mstteacherinvite> tblntfListSentByTeacher = new ArrayList<>();
		List<Object[]> list1 = query.list();
		session.close();
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstteacherinvite mstteacherinvite = new Mstteacherinvite();
			for (int i = 0; i < objects.length; i++) {
				 System.out.println("====== "+objects[i]);
				mstteacherinvite.setDtInvitesDate((Date) objects[0]);
				mstteacherinvite.setTxtMessage((String) objects[1]);
			}
			tblntfListSentByTeacher.add(mstteacherinvite);
		}

		System.out.println("query success for invitation sent by teacher");

		return tblntfListSentByTeacher;
	}

	@Override
	public void update(Mstregistration m, int id) {

		Session session = this.sessionFactory.openSession();
		String hql = "update from Mstregistration set txtPerAddress=:address,txtFmobileNumber=:Fnumber,"
				+ "txtMmobileNumber=:Mnumber,txtFotherNumber=:emergencyFNumber,txtMotherNumber=:emergencyMNumber where intRegistrationId=:id";

		org.hibernate.Query query = session.createQuery(hql);

		query.setParameter("address", m.getTxtPerAddress());
		query.setParameter("Fnumber", m.getTxtFmobileNumber());
		query.setParameter("Mnumber", m.getTxtMmobileNumber());
		query.setParameter("emergencyFNumber", m.getTxtFotherNumber());
		query.setParameter("emergencyMNumber", m.getTxtMotherNumber());
		query.setParameter("id", id);
		int i = query.executeUpdate();
		session.close();

	}

	/*
	 * @Override public List<Object[]> getStuTimeTablebyClass() { String sn =
	 * "Select txtClassTimings, mstsubject.txtSubjectName, tbldaysofweek.txtDay from"
	 * +
	 * " Mststudenttimetable where mststudclass.intClassId=9 and mststudclasssection.intSectionId=1 ORDER "
	 * +
	 * "BY txtClassTimings, FIELD(tbldaysofweek.txtDay, 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')"
	 * ; org.hibernate.Query query =
	 * this.sessionFactory.getCurrentSession().createQuery(sn); List<Object[]>
	 * result = query.list(); return result;
	 * 
	 * }
	 */

	// a
	public List<Object[]> getStuTimeTablebyClass() {
		Session session = this.sessionFactory.openSession();
		/*
		 * String sn =
		 * "Select txtClassTimings, mstsubject.txtSubjectName, tbldaysofweek.txtDay from Mststudenttimetable where mststudclass.intClassId=2 and mststudclasssection.intSectionId=2 ORDER BY txtClassTimings, FIELD(tbldaysofweek.txtDay, 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')"
		 * ;
		 */
		String sn = "SELECT t.msttimings.txtClassTimings,s.txtSubjectName,w.txtDay FROM Mststudenttimetable as t,Tbldaysofweek as w,"
				+ "Mstsubject as s , Mststudclass as c,Mststudclasssection as sec WHERE c.intClassId=t.mststudclass.intClassId AND "
				+ "sec.intSectionId=t.mststudclasssection.intSectionId AND w.intDaysId = t.tbldaysofweek.intDaysId "
				+ "AND s.intSubjectId= t.mstsubject.intSubjectId ORDER BY t.msttimings.txtClassTimings,"
				+ "FIELD(w.txtDay, 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')";
		org.hibernate.Query query = session.createQuery(sn);
		List<Object[]> result = query.list();
		session.close();
		// ..........
		List l = new ArrayList();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mststudenttimetable tblinvites = new Mststudenttimetable();
			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);
				// tblinvites.setTxtClassTimings((String) objects[0]);

				// tblinvites.setTxtBody((String) objects[1]);
				// tblinvites.setDtDate((Date) objects[2]);
			}
			l.add(tblinvites);
		}

		return result;
	}

	@Override
	public List<Trnstudentleaveapp> getStudentLeaveInbox(int id) {
		Session session = this.sessionFactory.openSession();
		/*
		 * List<Trnstudentleaveapp> list =
		 * session.createCriteria(Trnstudentleaveapp.class)
		 * .add(Restrictions.eq("mstregistration.intRegistrationId",
		 * id)).list();
		 */
		// SELECT
		// t.dtAppliedDate,t.dtFromDate,t.dtToDate,t.txtReason,t.intStatus,t.dtApprovedDate
		// from
		String hql = " from Trnstudentleaveapp t WHERE t.mstregistration.intRegistrationId='" + id
				+ "' order by  t.dtAppliedDate desc";
		org.hibernate.Query query = session.createQuery(hql);
		List<Trnstudentleaveapp> list = query.list();

		session.close();
		return list;
	}

	@Override
	public Mstuploadstudmarksheet getMarkSheet(int id) {
		Session s = this.sessionFactory.openSession();
		Mstuploadstudmarksheet m = (Mstuploadstudmarksheet) s.get(Mstuploadstudmarksheet.class, id);
		s.close();
		return m;

	}

	@Override
	public Tblinvites getNotificationById(int id) {
		Session s = this.sessionFactory.openSession();
		Tblinvites m = (Tblinvites) s.get(Tblinvites.class, id);
		s.close();
		return m;
	}

	@Override
	public void addQuestion(Tblparentqueries tpq) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.save(tpq);
		session.close();
	}

	@Override
	public List<Tblparentqueries> getListOfQuestionsAskedByParent(String parentId) {

		Session session = this.sessionFactory.openSession();
		String hql = "from Tblparentqueries t where t.txtParentId = '" + parentId
				+ "' order by t.intParentQueriesId desc";
		List<Tblparentqueries> list = session.createQuery(hql).list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Tblparentqueries tblparentqueries = (Tblparentqueries) iterator.next();
			System.out.println(tblparentqueries.getIntParentQueriesId());
			System.out.println(tblparentqueries.getTxtQuestion());
			// System.out.println(tblparentqueries.getTxtParentId());
		}
		session.close();
		return list;
	}

}