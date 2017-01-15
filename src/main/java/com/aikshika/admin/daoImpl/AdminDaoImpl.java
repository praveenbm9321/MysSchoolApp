package com.aikshika.admin.daoImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aikshika.admin.dao.AdminDao;
import com.aikshika.common.StudentTimingsCompare;
import com.aikshika.common.TeacherTimingsCompare;
import com.aikshika.common.WorkingDaysInPreviousMonth;
import com.aikshika.entity.Mstaffiliation;
import com.aikshika.entity.Mstbank;
import com.aikshika.entity.Mstbloodgroup;
import com.aikshika.entity.Mstcategory;
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

@Repository("adminDao")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AdminDaoImpl<T> implements AdminDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Mstsemester> semester() {
		Session session = this.sessionFactory.openSession();

		List<Mstsemester> list = session.createQuery("FROM Mstsemester").list();
		session.close();

		return list;
	}

	@Override
	public List<Mststream> stream() {
		Session session = this.sessionFactory.openSession();
		List<Mststream> list = session.createQuery("FROM Mststream").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstmothertongue> mothertongue() {
		Session session = this.sessionFactory.openSession();

		List<Mstmothertongue> list = session.createQuery("FROM Mstmothertongue").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstmedium> medium() {
		Session session = this.sessionFactory.openSession();

		List<Mstmedium> list = session.createQuery("FROM Mstmedium").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstaffiliation> affiliation() {
		Session session = this.sessionFactory.openSession();

		List<Mstaffiliation> list = session.createQuery("FROM Mstaffiliation").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstschooltype> schooltype() {
		Session session = this.sessionFactory.openSession();
		List<Mstschooltype> list = session.createQuery("FROM Mstschooltype").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstgender> gender() {
		Session session = this.sessionFactory.openSession();
		List<Mstgender> list = session.createQuery("FROM Mstgender").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstbloodgroup> bloodgroup() {
		Session session = this.sessionFactory.openSession();
		List<Mstbloodgroup> list = session.createQuery("FROM Mstbloodgroup").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstsocialcategory> socialcategory() {
		Session session = this.sessionFactory.openSession();
		List<Mstsocialcategory> list = session.createQuery("FROM Mstsocialcategory").list();
		session.close();

		return list;
	}

	@Override
	public List<Mstdisabilitychild> disabilitychild() {
		Session session = this.sessionFactory.openSession();
		List<Mstdisabilitychild> list = session.createQuery("FROM Mstdisabilitychild").list();

		session.close();

		return list;
	}

	@Override
	public List<Mstreligion> religion() {
		Session session = this.sessionFactory.openSession();

		List<Mstreligion> list = session.createQuery("FROM Mstreligion").list();
		session.close();

		return list;
	}

	/*
	 * @Override public List<Mstcity> city() { Session session =
	 * this.sessionFactory.openSession();
	 * 
	 * List<Mstcity> list = session.createQuery("FROM Mstcity ").list();
	 * session.close();
	 * 
	 * return list; }
	 */

	// -------school and branch start

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addSandBDetails(Tblschoolandbranchdetail tbl) {
		Session session = this.sessionFactory.openSession();

		session.save(tbl);
		session.close();
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tblschoolandbranchdetail> getAllDetails() {
		Session session = this.sessionFactory.openSession();

		List<Tblschoolandbranchdetail> list = session.createQuery("from Tblschoolandbranchdetail").list();
		session.close();

		return list;

	}

	@Override
	public void deleteSchool(int id) {

		Tblschoolandbranchdetail myObject = new Tblschoolandbranchdetail();

		Session session = this.sessionFactory.openSession();
		myObject = (Tblschoolandbranchdetail) session.load(Tblschoolandbranchdetail.class, id);
		session.delete(myObject);

		// This makes the pending delete to be done
		session.flush();
		session.close();

	}

	@Override
	public void updateBranch(Tblschoolandbranchdetail tbl, int id) {
		Session s = this.sessionFactory.openSession();

		Query q = s
				.createQuery(
						"update Tblschoolandbranchdetail t set t.txtEmail= :p1, t.txtResponsiblePerson= :p2, t.txtSchoolAddress= :p3, t.txtSchoolName= :p4,"
								+ " t.txtWebsite= :p5 where t.intSchoolId= :id1 ")
				.setParameter("p1", tbl.getTxtEmail()).setParameter("p2", tbl.getTxtResponsiblePerson())
				.setParameter("p3", tbl.getTxtSchoolAddress()).setParameter("p4", tbl.getTxtSchoolName())
				.setParameter("p5", tbl.getTxtWebsite()).setParameter("id1", id);
		q.executeUpdate();

		s.close();
	}

	// -------school and branch end
	// ----- master record start

	public List<Mstcategory> getCategory() {
		Session s = sessionFactory.openSession();
		List<Mstcategory> q = s.createQuery("from Mstcategory").list();
		s.close();
		return q;
	}

	@Override
	public void deleteUser(int id) {
		Session s = sessionFactory.openSession();
		Mstregistration obj = (Mstregistration) s.load(Mstregistration.class, id);
		s.delete(obj);
		s.flush();
		s.close();
	}

	public List<Mstregistration> getAllStudentLists(int ClassId, int SectionId, String Id) {
		Session s = sessionFactory.openSession();
		int StudentIntId = 0;
		List<Mstregistration> q;
		Query hql;
		String str = "";
		/* ArrayList<Integer> loginal = new ArrayList<>(); */

		char c = ' ';
		if (!Id.isEmpty()) {
			c = Id.charAt(0);
		}

		// ------ condition starts

		if (Id.equals("") || Id.equals(null)) {
			System.out.println("inside first loop");
			str = "from Mstregistration m where  m.mststudclass.intClassId= :intClassId and m.mststudclasssection.intSectionId= :intSectionId and m.tblrolename.intRoleId= :intRoleId";
			hql = s.createQuery(str);
			hql.setParameter("intClassId", ClassId);
			hql.setParameter("intSectionId", SectionId);
			hql.setParameter("intRoleId", 1);

			q = hql.list();

			/*
			 * Iterator<Mstregistration> i = q.iterator(); while (i.hasNext()) {
			 * Mstregistration mstregistration = (Mstregistration) i.next();
			 * loginal.add(mstregistration.getIntRegistrationId()); }
			 */

			s.close();
			return q;
		}

		else if (!(Id.equals("") || Id.equals(null)) && Character.isLetter(c)) {
			System.out.println(c);
			str = "from Mstregistration m where m.mststudclass.intClassId= :intClassId and m.mststudclasssection.intSectionId= :intSectionId "
					+ "and m.txtFirstName= :txtFirstName and m.tblrolename.intRoleId= :intRoleId";
			hql = s.createQuery(str);
			hql.setParameter("intClassId", ClassId);
			hql.setParameter("intSectionId", SectionId);
			hql.setParameter("txtFirstName", Id.toLowerCase());
			hql.setParameter("intRoleId", 1);

			q = hql.list();
			s.close();
			return q;

		} else {
			StudentIntId = Integer.parseInt(Id);
			str = "from Mstregistration m where  m.mststudclass.intClassId= :intClassId and m.mststudclasssection.intSectionId= :intSectionId "
					+ "and m.intRegistrationId= :intRegistrationId and m.tblrolename.intRoleId= :intRoleId";
			hql = s.createQuery(str);
			hql.setParameter("intClassId", ClassId);
			hql.setParameter("intSectionId", SectionId);
			hql.setParameter("intRegistrationId", StudentIntId);
			hql.setParameter("intRoleId", 1);
			q = hql.list();
			s.close();
			return q;
		}

	}

	@Override
	public List<Mstregistration> getAllStaffLists(String Id) {
		Session s = sessionFactory.openSession();
		String str = "";
		List<Mstregistration> list;
		int StaffId = 0;
		char c = ' ';
		if (!Id.equals("")) {
			c = Id.charAt(0);
		}

		if (Id.equals("") || Id.equals(null)) {
			str = "from Mstregistration m where  m.tblrolename.intRoleId= :intRoleId";
			Query q = s.createQuery(str);
			q.setParameter("intRoleId", 3);
			list = q.list();
			s.close();
			return list;
		} else if (Character.isLetter(c)) {
			str = "from Mstregistration m where m.txtFirstName = :txtFirstName and m.tblrolename.intRoleId= :intRoleId";
			Query q = s.createQuery(str);
			q.setParameter("txtFirstName", Id.toLowerCase());
			q.setParameter("intRoleId", 3);
			list = q.list();
			s.close();
			return list;

		} else {
			StaffId = Integer.parseInt(Id);
			str = "from Mstregistration m where m.intRegistrationId = :intRegistrationId and m.tblrolename.intRoleId= :intRoleId";
			Query q = s.createQuery(str);
			q.setParameter("intRegistrationId", StaffId);
			q.setParameter("intRoleId", 3);
			list = q.list();
			s.close();
			return list;
		}

	}

	@Override
	public void updateStudent(Mstregistration reg, int id) {
		Session s = sessionFactory.openSession();
		System.out.println("inside dao");
		Query q = s
				.createQuery(
						"update  Mstregistration t set t.txtFirstName= :p1, t.txtLastName= :p2, t.txtFatherName= :p3, t.dtDob = :p4,t.txtPerAddress= :p5,t.txtFmobileNumber= :p6 where t.intRegistrationId= :p7")
				.setParameter("p1", reg.getTxtFirstName()).setParameter("p2", reg.getTxtLastName())
				.setParameter("p3", reg.getTxtFatherName()).setParameter("p4", reg.getDtDob())
				.setParameter("p5", reg.getTxtPerAddress()).setParameter("p6", reg.getTxtFmobileNumber())
				.setParameter("p7", id);
		q.executeUpdate();
		s.close();
	}

	@Override
	public Mstbank getBankDetails(int id) {
		Session session = sessionFactory.openSession();
		Mstbank bank = new Mstbank();
		Query q = session.createQuery("from Mstbank b where b.mstregistration.intRegistrationId= :p1")
				.setParameter("p1", id);
		ArrayList<Mstbank> albank = (ArrayList<Mstbank>) q.list();
		Iterator<Mstbank> itr = albank.iterator();
		while (itr.hasNext()) {
			bank = (Mstbank) itr.next();
		}
		session.close();
		return bank;
	}

	// ------ master record end

	// -------------------user credentials start

	@Override
	public List<Mstlogin> getUser() {
		Session s = sessionFactory.openSession();
		List<Mstlogin> login = s.createQuery("from Mstlogin").list();
		s.close();
		return login;
	}

	// ----------------------user credentials end

	// ----------------dao invites start

	@Override
	public List<Mststudclass> getClasses() {

		Session session = this.sessionFactory.openSession();

		List<Mststudclass> list = session.createQuery("from Mststudclass").list();
		session.close();

		return list;

	}

	@Override
	public List<Mststudclasssection> getSections() {
		Session session = this.sessionFactory.openSession();

		List<Mststudclasssection> list = session.createQuery("from Mststudclasssection").list();
		session.close();

		return list;

		// return sessionFactory.getCurrentSession().createQuery("from
		// Mststudclasssection").list();

	}

	@Override
	public List<Tblrolename> getRoles() {
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Tblrolename").list();
		session.close();
		return list;
	}

	@Override
	public void delInvites(int id) {
		Session s = sessionFactory.openSession();
		Tblinvites tbl = (Tblinvites) s.load(Tblinvites.class, id);
		s.delete(tbl);
		s.flush();
		s.close();
	}

	// ------------ end invites

	// --------------------------------------start of schools connect
	@Override
	public void adddetails(Mstschoolconnect tbl) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();

		session.save(tbl);
		session.close();
	}

	@Override
	public List<Mstschool> getUsers() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List<Mstschool> list = session.createQuery("from Mstschool").list();
		session.close();
		return list;
	}
	/*
	 * @Override public void save(Mstschoolconnect tbl) {
	 * sessionFactory.getCurrentSession().save(tbl); }
	 */

	@Override
	public void addSchool(Mstschool mschool) {
		Session s = sessionFactory.openSession();
		s.save(mschool);
		s.close();

	}

	@Override
	public void delSchool(Integer id) {
		System.out.println("inside dao");
		Session s = sessionFactory.openSession();
		Mstschool m = (Mstschool) s.load(Mstschool.class, id);
		s.delete(m);
		s.flush();
		s.close();

	}

	@Override
	public void updSchool(Mstschool mschool) {
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		s.saveOrUpdate(mschool);
		t.commit();
		s.close();
	}

	/* end of school connect */

	// syallbus

	@Override
	public void addsyllabus(Mstsyllabus sub) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		session.save(sub);
		session.close();
	}

	@Override
	public List<Mststudclass> getSyllabus() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Mststudclass").list();
		session.close();
		return list;
	}

	@Override
	public List<Mststudclass> getClasseAd() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Mststudclass").list();
		session.close();
		return list;
	}

	@Override
	public List<Mststudclasssection> getSection() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Mststudclasssection").list();
		session.close();
		return list;
	}
	// --------doc upload dao impl----------------------

	@Override
	public List<Trndocumenttype> getDocType() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Trndocumenttype").list();
		session.close();
		return list;
	}

	@Override
	public List<Mstsubcategory> getSubCategories() {
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Mstsubcategory").list();
		session.close();
		return list;
	}

	@Override
	public List<Mstcategory> getCategories() {
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Mstcategory").list();
		session.close();
		return list;

	}

	@Override
	public void addDocUploadDetails(Trndocumentupload docUp) {
		Session session = this.sessionFactory.openSession();

		session.save(docUp);
		session.close();

	}

	@Override
	public List<Trndocumentupload> getUploadDetails() {
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Trndocumentupload").list();
		session.close();
		return list;
	}

	// --------doc upload end----------------------

	@Override
	public void addInvitesUpload(Tblinvites ti) {
		Session session = this.sessionFactory.openSession();
		session.save(ti);
		session.close();
	}

	// ------------ end invites dao impl

	@Override
	public void addDocUploadDetails(Mstschoolconnect docUp) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		session.save(docUp);
		session.close();
	}

	@Override
	public void addtimetableDetails(Mststudenttimetable mst) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		session.save(mst);
		session.close();
	}

	@Override
	public List<Mstsubject> getSubject() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Mstsubject").list();
		session.close();
		return list;
	}

	@Override
	public List<Tbldaysofweek> getdays() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Tbldaysofweek").list();
		session.close();
		return list;
	}

	/*
	 * @Override public List<Mstteachertimetable> getAllTeachertimetable(int
	 * ClassId, int SectionId) { // TODO Auto-generated method stub Session
	 * session = this.sessionFactory.openSession(); String hql =
	 * "from Mststudenttimetable m where m.mststudclass.intClassId= :intClassId  and m.mststudclasssection.intSectionId= :intSectionId"
	 * ; Query q = session.createQuery(hql); q.setParameter("intClassId",
	 * ClassId); q.setParameter("intSectionId", SectionId); List list =
	 * q.list(); session.close(); return list; }
	 */

	/*
	 * @Override public void deleteTimetable(int id) { // TODO Auto-generated
	 * method stub Mststudenttimetable mststudenttimetable;
	 * 
	 * Session session = this.sessionFactory.openSession(); mststudenttimetable
	 * = (Mststudenttimetable) session.load(Mststudenttimetable.class, id);
	 * session.delete(mststudenttimetable);
	 * 
	 * // This makes the pending delete to be done session.flush();
	 * session.close();
	 * 
	 * }
	 */

	@Override
	public void addtimetableDetails(Mstteachertimetable mstt) {
		// TODO Auto-generated method stub
		System.out.println("before datails");
		Session session = this.sessionFactory.openSession();
		session.save(mstt);
		session.close();
	}

	@Override
	public List<Tblinvites> getInviteDetails() {

		Session session = this.sessionFactory.openSession();
		List list = session.createQuery("from Tblinvites").list();
		session.close();
		return list;

	}

	@Override
	public List<Tblinvites> getInvObj(Integer id) {
		Session session = this.sessionFactory.openSession();
		String hql = "from Tblinvites t where t.intInvitesId= :intInvitesId"; //
		Query q = session.createQuery(hql);

		q.setParameter("intInvitesId", id);
		List<Tblinvites> list = q.list();
		session.close();
		return list;
	}

	// ------------ end invites

	// ----------------start attendance
	@Override
	public List<Trnattendancerecord> getStudAttend(int studId, Date curDt) {
		Session session = this.sessionFactory.openSession();

		String hql = "from Trnattendancerecord t where t.mstregistration.intRegistrationId= :intRegistrationId and t.dtSlotDate= :dtSlotDate"; //
		Query q = session.createQuery(hql);

		q.setParameter("intRegistrationId", studId);
		q.setParameter("dtSlotDate", curDt);
		List list = q.list();
		session.close();

		return list;
	}
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public String getStuName(int studId) { Session session =
	 * this.sessionFactory.openSession();
	 * 
	 * String hql =
	 * "SELECT a.txtFirstName FROM Mstregistration a where a.intRegistrationId= :intRegistrationId"
	 * ; Query q = session.createQuery(hql); q.setParameter("intRegistrationId",
	 * studId); q.uniqueResult();
	 * 
	 * List<String> results = q.list(); System.out.println(results); String res
	 * = null; for (String s : results) { res = s; } System.out.println(res);
	 * session.close();
	 * 
	 * return res; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public String getStuName(int studId) {
		Session session = this.sessionFactory.openSession();

		String hql = "from Mstregistration a where a.intRegistrationId= :intRegistrationId";
		Query q = session.createQuery(hql);
		q.setParameter("intRegistrationId", studId);
		q.uniqueResult();

		/*
		 * List<String> results = q.list(); System.out.println(results); String
		 * res = null; for (String s : results) { res = s; }
		 * System.out.println(res);
		 */
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getStudentList(int intClassId, int intSectionId, int intRegistrationId) {
		Session session = this.sessionFactory.openSession();

		String hql = "SELECT p.intRegistrationId FROM Mstregistration p where p.mststudclass.intClassId= :intClassId and p.mststudclasssection.intSectionId= :intSectionId and p.intRegistrationId= :intRegistrationId";
		Query q = session.createQuery(hql);
		q.setParameter("intClassId", intClassId);
		q.setParameter("intSectionId", intSectionId);
		q.setParameter("intRegistrationId", intRegistrationId);
		q.uniqueResult();

		List<Integer> lists = q.list();
		session.close();

		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trnattendancerecord> getStudentAttendFromTo(java.util.Date startDate, java.util.Date endDate, int id) {
		Session session = this.sessionFactory.openSession();

		List<Trnattendancerecord> list = session
				.createQuery(
						"FROM Trnattendancerecord AS c WHERE c.dtSlotDate BETWEEN :startDate AND :endDate and c.mstregistration.intRegistrationId= :id")
				.setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("id", id)

				/*
				 * .createCriteria(Trnattendancerecord.class)
				 * .add(Restrictions.between("dtSlotDate", startDate, endDate))
				 */

				.list();
		session.close();

		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getStudentIds(int classId, int sectionId) {
		Session session = this.sessionFactory.openSession();

		String hql = "SELECT p.intRegistrationId FROM Mstregistration p where p.mststudclass.intClassId= :intClassId and p.mststudclasssection.intSectionId= :intSectionId";
		Query q = session.createQuery(hql);
		q.setParameter("intClassId", classId);
		q.setParameter("intSectionId", sectionId);

		List<Integer> lists = q.list();
		session.close();

		return lists;
	}

	// ---------Attendance end

	// -------student performance start

	@Override
	public List<Mstuploadstudmarksheet> getMarksList(int ClassId, int SectionId, int TermId, String StudentId) {
		Session s = this.sessionFactory.openSession();
		List<Mstuploadstudmarksheet> q;
		int StudentIntId = 0;
		if (StudentId.equals("")) {
			Query hql = s.createQuery("from Mstuploadstudmarksheet m where m.mststudclass.intClassId= :intClassId and "
					+ "m.mststudclasssection.intSectionId= :intSectionId and m.mstterms.intTermId= :intTermId");
			hql.setParameter("intClassId", ClassId);
			hql.setParameter("intSectionId", SectionId);
			hql.setParameter("intTermId", TermId);
			q = hql.list();
			s.close();
			return q;
		} else {
			StudentIntId = Integer.parseInt(StudentId);
			Query hql = s.createQuery("from Mstuploadstudmarksheet m where m.mststudclass.intClassId= :intClassId and"
					+ " m.mststudclasssection.intSectionId= :intSectionId and m.mstterms.intTermId= :intTermId and "
					+ "m.mstregistration.intRegistrationId= :intRegistrationId");
			hql.setParameter("intClassId", ClassId);
			hql.setParameter("intSectionId", SectionId);
			hql.setParameter("intTermId", TermId);
			hql.setParameter("intRegistrationId", StudentIntId);
			q = hql.list();
			s.close();
			return q;
		}
	}

	@Override
	public List<Mstterms> getTerms() {

		Session s = this.sessionFactory.openSession();

		List<Mstterms> q = s.createQuery("from Mstterms").list();
		s.close();
		return q;
	}

	@Override
	public Mstuploadstudmarksheet getMarkSheet(int id) {
		Session s = this.sessionFactory.openSession();
		Mstuploadstudmarksheet m = (Mstuploadstudmarksheet) s.get(Mstuploadstudmarksheet.class, id);
		s.close();
		return m;

	}

	// -------student performance end

	@Override
	public List<Trnteacherleaveapp> getLeaveReqFromTeacher() {
		Session s = this.sessionFactory.openSession();
		List<Trnteacherleaveapp> list = s.createQuery("from Trnteacherleaveapp p where p.intStatus = '1'").list();
		s.close();
		return list;
	}

	@Override
	public List<Mstleave> getLeaveTypes() {
		Session s = this.sessionFactory.openSession();
		List<Mstleave> list = s.createQuery("from Mstleave").list();
		s.close();
		return list;
	}

	@Override
	public List<Mstmaritalstatus> getMaritalStatus() {
		Session s = this.sessionFactory.openSession();
		List<Mstmaritalstatus> list = s.createQuery("from Mstmaritalstatus").list();
		s.close();
		return list;
	}

	@Override
	public void saveTeachReg(Mstregistration regObj) {
		Session s = this.sessionFactory.openSession();
		Mststudclass cls = new Mststudclass();
		Mststudclasssection sec = new Mststudclasssection();
		Integer cid = 0;
		Integer sid = 0;
		Query q = s
				.createQuery(
						"select r.mststudclass.intClassId, r.mststudclasssection.intSectionId from Mstregistration r where r.intRegistrationId=:p1")
				.setParameter("p1", regObj.getIntRegistrationId());
		ArrayList<Object[]> reg = (ArrayList<Object[]>) q.list();

		Iterator<Object[]> itr = reg.iterator();
		while (itr.hasNext()) {
			Object[] object = itr.next();

			/*
			 * cid = (String) object[0]; sid = (String) object[1];
			 */

			cid = (Integer) object[0];
			sid = (Integer) object[1];
			System.out.println(cid);
			System.out.println(sid);

		}
		if (regObj.getIntRegistrationId() != null) {
			if (cid != null && sid != null) {
				cls.setIntClassId(cid);
				sec.setIntSectionId(sid);
				regObj.setMststudclass(cls);
				regObj.setMststudclasssection(sec);
			} else {
				System.out.println("class and section assigned");
			}
		}
		Transaction transaction = s.beginTransaction();
		s.saveOrUpdate(regObj);
		transaction.commit();
		s.close();

	}

	@Override
	public void saveTeachBank(Mstbank bankObj) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(bankObj);
		tx.commit();
		s.close();

	}

	@Override
	public void letApprove(int nowId, Date curDt, int approvedStatusId) {
		Session s = this.sessionFactory.openSession();
		String hql = "UPDATE Trnteacherleaveapp as t set " + "intStatus = :intStatus ,"
				+ "dtApprovedDate = :dtApprovedDate " + "where id = :id";

		Query q = s.createQuery(hql);
		q.setParameter("id", nowId);
		q.setParameter("dtApprovedDate", curDt);
		q.setParameter("intStatus", approvedStatusId);
		int aa = q.executeUpdate();
		System.out.println(aa);
		s.close();
	}

	@Override
	public void letReject(int nowId, Date curDt, int approvedStatusId) {
		Session s = this.sessionFactory.openSession();
		String hql = "UPDATE Trnteacherleaveapp as t set " + "intStatus = :intStatus ,"
				+ "dtApprovedDate = :dtApprovedDate " + "where id = :id";
		// String hql="update Trnteacherleaveapp set intStatus = :intStatus
		// where id = :id";
		Query q = s.createQuery(hql);
		q.setParameter("id", nowId);
		q.setParameter("dtApprovedDate", curDt);
		q.setParameter("intStatus", approvedStatusId);
		int aa = q.executeUpdate();
		System.out.println(aa);
		s.close();
	}

	@Override
	public Trndocumentupload uploadDocObj(Integer id) {
		Trndocumentupload type = new Trndocumentupload();
		Session s = this.sessionFactory.openSession();
		type = (Trndocumentupload) s.get(Trndocumentupload.class, id);
		s.close();
		return type;
	}

	@Override
	public Tblinvites invObj(Integer id) {
		Tblinvites obj = new Tblinvites();
		Session s = this.sessionFactory.openSession();
		obj = (Tblinvites) s.get(Tblinvites.class, id);
		s.close();
		return obj;
	}

	@Override
	public int getRoleId(String stu) {
		Session s = sessionFactory.openSession();

		Query q = s.createQuery("SELECT intRoleId from Tblrolename where txtRoleName=:txtRoleName");
		q.setParameter("txtRoleName", stu);
		List<Integer> list = q.list();
		int id22 = 0;
		for (Integer id : list) {
			id22 = id;
		}
		s.close();
		return id22;
	}

	@Override
	public List<Tblbankname> bankname() {
		Session s = this.sessionFactory.openSession();
		List<Tblbankname> list = s.createQuery("from Tblbankname").list();
		s.close();
		return list;
	}

	@Override
	public void addRegister(Mstregistration reg) {
		Session session = this.sessionFactory.openSession();
		System.out.println("inside dao impl");
		System.out.println(reg.getIntRegistrationId());
		System.out.println(reg.getTxtFmobileNumber());
		Transaction tx = session.getTransaction();
		session.beginTransaction();
		session.saveOrUpdate(reg);
		tx.commit();
		session.close();

	}

	@Override
	public void addBankDetails(Mstbank mstbank) {
		Session session = this.sessionFactory.openSession();
		System.out.println("inside dao impl bank");
		Transaction tx = session.beginTransaction();
		session.save(mstbank);
		tx.commit();
		session.close();
	}

	@Override
	public void addLoginDetails(Mstlogin mstlogin) {
		Session session = this.sessionFactory.openSession();
		session.save(mstlogin);
		session.close();
	}

	@Override
	public int getClassId(String string) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery("SELECT a.intClassId from Mststudclass a where a.txtClassName = :txtClassName");
		q.setParameter("txtClassName", string);
		q.uniqueResult();
		List<Integer> a = q.list();
		int dd = 0;
		for (Integer in : a) {
			dd = in;
		}
		s.close();
		return dd;
	}

	@Override
	public int getSectionId(String string) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery(
				"SELECT a.intSectionId from Mststudclasssection a where a.txtSectionName = :txtSectionName");
		q.setParameter("txtSectionName", string);
		q.uniqueResult();
		List<Integer> a = q.list();
		int dd = 0;
		for (Integer in : a) {
			dd = in;
		}
		s.close();
		return dd;
	}

	@Override
	public List<Trnteacherleaveapp> getApprovedLeaves(Date fromDate, Date toDate, int status) {
		List<Trnteacherleaveapp> list = new ArrayList<Trnteacherleaveapp>();
		Session session = this.sessionFactory.openSession();
		if (status != 1) {
			list = session
					.createQuery(
							"FROM Trnteacherleaveapp AS c WHERE c.dtAppliedDate BETWEEN :startDate AND :endDate and c.intStatus= :intStatus")
					.setParameter("startDate", fromDate).setParameter("endDate", toDate)
					.setParameter("intStatus", status).list();
		} else {
			list = session
					.createQuery(
							"FROM Trnteacherleaveapp AS c WHERE c.dtAppliedDate BETWEEN :startDate AND :endDate and c.intStatus!= :intStatus")
					.setParameter("startDate", fromDate).setParameter("endDate", toDate).setParameter("intStatus", 1)
					.list();
		}
		session.close();
		return list;
	}

	// teacher attendance track start

	@Override
	public List<Mstregistration> getTeacherRecords() {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery("from Mstregistration a where a.tblrolename.intRoleId = :intRoleId");
		q.setParameter("intRoleId", 3);
		List<Mstregistration> list = q.list();
		s.close();
		return list;
	}

	@Override
	public List<Mstteachercheckin> getTeacherCheckInList(Date curDt) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery("from Mstteachercheckin a where a.dtCheckInDate = :dtCheckInDate");
		q.setParameter("dtCheckInDate", curDt);
		List<Mstteachercheckin> list = q.list();
		s.close();
		return list;

	}

	@Override
	public List<Mstteachertimetable> getAllTTForTeacher(int teachId, int dayOfWeek, Date curDtNow) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery(
				"from Mstteachertimetable a where a.mstregistration.intRegistrationId = :intRegistrationId and a.tbldaysofweek.intDaysId = :intDaysId and a.dtAssignDate = :dtAssignDate");
		q.setParameter("intRegistrationId", teachId);
		q.setParameter("intDaysId", dayOfWeek);
		q.setParameter("dtAssignDate", curDtNow);
		List<Mstteachertimetable> list = q.list();
		Collections.sort(list, new TeacherTimingsCompare());
		s.close();
		return list;
	}

	@Override
	public Tbldaysofweek getDayOfWeekById(int dayOfWeek) {
		Session s = this.sessionFactory.openSession();
		Tbldaysofweek obj = (Tbldaysofweek) s.get(Tbldaysofweek.class, dayOfWeek);
		s.close();
		return obj;
	}

	// end of teacher attend track

	// ------------------------querires start

	@Override
	public List<Tblparentqueries> pquestion() {
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Tblparentqueries");
		List<Tblparentqueries> list = q.list();
		session.close();
		return list;
	}

	@Override
	public void saveAnswer(String ans, int id) {
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery(
						"update Tblparentqueries as p set p.txtAnswer=:p1, p.txtStatus=:p2  where intParentQueriesId=:p3")
				.setParameter("p1", ans).setParameter("p2", 1).setParameter("p3", id);
		q.executeUpdate();
		session.close();
	}

	// ---------------------queries end

	@Override
	public Mstregistration checkParent(String fMobileLog, Integer regId) {
		Session session = this.sessionFactory.openSession();
		Mstregistration regObj = null;
		Query q = session.createQuery(
				"from Mstregistration a where a.txtFemailId = :txtFmobileNumber and a.intRegistrationId != :intRegistrationId");
		q.setParameter("txtFmobileNumber", fMobileLog);
		q.setParameter("intRegistrationId", regId);
		List<Mstregistration> a = q.list();
		Iterator<Mstregistration> itr = a.iterator();
		while (itr.hasNext()) {
			regObj = (Mstregistration) itr.next();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(regObj.getIntRegistrationId());
			break;
		}
		session.close();
		return regObj;

	}

	@Override
	public void addUserRoleDetails(Tbluserrole userRoleParObj) {
		Session session = this.sessionFactory.openSession();
		session.save(userRoleParObj);
		session.close();
	}

	@Override
	public Mstregistration getParentObj(String fMobileLog, Integer regId) {
		Session session = this.sessionFactory.openSession();
		Boolean check = false;
		Query q = session.createQuery(
				"from Mstregistration a where a.txtFemailId = :txtFmobileNumber and a.intRegistrationId != :intRegistrationId");
		q.setParameter("txtFmobileNumber", fMobileLog);
		q.setParameter("intRegistrationId", regId);
		List<Mstregistration> a = q.list();

		Mstregistration regObjj = a.get(0);

		/*
		 * Iterator<Mstregistration> itr=a.iterator(); while (itr.hasNext()) {
		 * regObj = (Mstregistration) itr.next();
		 * 
		 * }
		 */
		session.close();
		return regObjj;

	}

	@Override
	public void addTimeTable2(Mststudenttimetable tT2) {
		Session s = this.sessionFactory.openSession();
		s.save(tT2);
		s.close();
	}

	@Override
	public Mstregistration getRegObjById(int teachId) {
		Session s = this.sessionFactory.openSession();
		Mstregistration regObj = null;
		Query q = s.createQuery("from Mstregistration a where a.intRegistrationId = :intRegistrationId");
		q.setParameter("intRegistrationId", teachId);
		List<Mstregistration> list = q.list();
		Iterator<Mstregistration> itr = list.iterator();
		while (itr.hasNext()) {
			regObj = (Mstregistration) itr.next();
		}
		s.close();
		return regObj;
	}

	@Override
	public void addTimeTable2(Mstteachertimetable tT2) {
		Session s = this.sessionFactory.openSession();
		s.save(tT2);
		s.close();
	}

	@Override
	public int getParentLoginId(int regIdPar) {
		Session s = this.sessionFactory.openSession();
		Tbluserrole userRoleObj = null;
		Query q = s.createQuery(
				"from Tbluserrole a where a.mstregistration.intRegistrationId = :intRegistrationId and a.tblrolename.intRoleId= :roleId");
		q.setParameter("intRegistrationId", regIdPar);
		q.setParameter("roleId", 2);
		q.uniqueResult();
		List<Tbluserrole> list = q.list();
		Iterator<Tbluserrole> itr = list.iterator();
		while (itr.hasNext()) {
			userRoleObj = (Tbluserrole) itr.next();
		}
		s.close();
		return userRoleObj.getMstlogin().getIntLoginId();
	}

	@Override
	public Mstlogin getLoginObj(int logInId) {
		Session s = this.sessionFactory.openSession();
		Mstlogin loginObj = null;
		Query q = s.createQuery("from Mstlogin a where a.intLoginId = :intLoginId");
		q.setParameter("intLoginId", logInId);
		q.uniqueResult();
		List<Mstlogin> list = q.list();
		Iterator<Mstlogin> itr = list.iterator();
		while (itr.hasNext()) {
			loginObj = (Mstlogin) itr.next();
		}
		s.close();
		return loginObj;
	}

	@Override
	public Boolean checkParentIsThere(int regIdddd) {
		Session s = this.sessionFactory.openSession();
		Boolean check = false;
		Query q = s.createQuery(
				"from Tbluserrole a where a.mstregistration.intRegistrationId = :intRegistrationId and a.tblrolename.intRoleId = :intRoleId");
		q.setParameter("intRegistrationId", regIdddd);
		q.setParameter("intRoleId", 2);
		q.uniqueResult();
		List<Tbluserrole> list = q.list();
		if (!list.isEmpty()) {
			check = true;
		}
		s.close();
		return check;
	}

	@Override
	public List<Mstteachertimetable> getTeachTimeTable(int teachId) {
		Session s = this.sessionFactory.openSession();
		ArrayList<Mstteachertimetable> list = new ArrayList<>();
		ArrayList<Mstteachertimetable> listFinal = new ArrayList<>();
		for (int i = 1; i <= 7; i++) {
			Query q = s.createQuery(
					"from Mstteachertimetable a where a.tbldaysofweek.intDaysId = :intDaysId and a.mstregistration.intRegistrationId = :intRegistrationId and a.intTeacherStatus = :intTeacherStatus");
			q.setParameter("intRegistrationId", teachId);
			q.setParameter("intTeacherStatus", 1);
			q.setParameter("intDaysId", i);
			list = (ArrayList<Mstteachertimetable>) q.list();
			Collections.sort(list, new TeacherTimingsCompare());
			listFinal.addAll(list);
		}
		s.close();
		return listFinal;
	}

	@Override
	public List<Tbldaysofweek> getDaysOfweek() {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery("from Tbldaysofweek");
		List<Tbldaysofweek> list = q.list();
		s.close();
		return list;
	}

	@Override
	public List<Mststudenttimetable> getStuTimeTable(int classIdInt, int sectionIdInt) {
		Session s = this.sessionFactory.openSession();
		ArrayList<Mststudenttimetable> list = new ArrayList<>();
		ArrayList<Mststudenttimetable> listFinal = new ArrayList<>();
		for (int i = 1; i <= 7; i++) {
			Query q = s.createQuery(
					"from Mststudenttimetable a where a.tbldaysofweek.intDaysId = :intDaysId and a.mststudclass.intClassId = :intClassId and a.mststudclasssection.intSectionId = :intSectionId and a.intStudentStatus = :intStudentStatus");
			q.setParameter("intClassId", classIdInt);
			q.setParameter("intSectionId", sectionIdInt);
			q.setParameter("intStudentStatus", 1);
			q.setParameter("intDaysId", i);
			list = (ArrayList<Mststudenttimetable>) q.list();
			Collections.sort(list, new StudentTimingsCompare());
			listFinal.addAll(list);
		}
		s.close();
		return listFinal;
	}

	@Override
	public String getSectionName(String sectionStr) {
		Session s = this.sessionFactory.openSession();
		String secName = null;
		Query q = s.createQuery("from Mststudclasssection a where a.intSectionId = :intSectionId");
		q.setParameter("intSectionId", Integer.parseInt(sectionStr));
		List<Mststudclasssection> sectionList = q.list();
		if (sectionList.size() > 0) {
			Mststudclasssection secObj = sectionList.get(0);
			secName = secObj.getTxtSectionName();
		}
		s.close();
		return secName;
	}

	@Override
	public ArrayList<Date> getAllDates(int teachId) {
		Session s = this.sessionFactory.openSession();
		ArrayList<Date> list = new ArrayList<>();
		Query q = s.createQuery(
				"select distinct a.dtAssignDate from Mstteachertimetable a where a.mstregistration.intRegistrationId = :intRegistrationId");
		q.setParameter("intRegistrationId", teachId);
		list = (ArrayList<Date>) q.list();
		s.close();
		return list;
	}

	@Override
	public ArrayList<Msttimings> getTimings() {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery("from Msttimings");
		List<Msttimings> list = q.list();
		s.close();
		return (ArrayList<Msttimings>) list;

	}

	@Override
	public void updateStuTT(Integer id) {
		Session s = this.sessionFactory.openSession();
		Query q = s
				.createQuery("update Mststudenttimetable t set t.intStudentStatus = :intStudentStatus"
						+ " where t.intStudentTimeTableId = :intStudentTimeTableId")
				.setParameter("intStudentStatus", 0).setParameter("intStudentTimeTableId", id);
		q.executeUpdate();

		s.close();

	}

	@Override
	public void addNewRowStuTT(Mststudenttimetable stuTTObj) {
		Session s = this.sessionFactory.openSession();
		s.save(stuTTObj);
		s.close();
	}

	@Override
	public void updateTeachTT(Integer id) {
		Session s = this.sessionFactory.openSession();
		Query q = s
				.createQuery("update Mstteachertimetable t set t.intTeacherStatus = :intTeacherStatus"
						+ " where t.intTeacherTimeTableId = :intTeacherTimeTableId")
				.setParameter("intTeacherStatus", 0).setParameter("intTeacherTimeTableId", id);
		q.executeUpdate();

		s.close();
	}

	@Override
	public void addNewRowTeachTT(Mstteachertimetable teachTTObj) {
		Session s = this.sessionFactory.openSession();
		s.save(teachTTObj);
		s.close();
	}

	@Override
	public List<Mststudenttimetable> checkForTTRecord(int classInt, int section, int dayId, int timingId) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery(
				"from  Mststudenttimetable a where a.mststudclass.intClassId = :intClassId and a.mststudclasssection.intSectionId = :intSectionId and a.tbldaysofweek.intDaysId = :intDaysId and a.msttimings.intMsttimingsId = :intMsttimingsId and a.intStudentStatus = :intStudentStatus");
		q.setParameter("intClassId", classInt);
		q.setParameter("intSectionId", section);
		q.setParameter("intDaysId", dayId);
		q.setParameter("intMsttimingsId", timingId);
		q.setParameter("intStudentStatus", 1);
		ArrayList list = (ArrayList<Mststudenttimetable>) q.list();
		s.close();
		return list;
	}

	@Override
	public ArrayList<Mstteachertimetable> checkForTeachTTRecord(int teachId, int dayId, int timingId) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery(
				"from  Mstteachertimetable a where a.mstregistration.intRegistrationId = :intRegistrationId and a.tbldaysofweek.intDaysId = :intDaysId and a.msttimings.intMsttimingsId = :intMsttimingsId and a.intTeacherStatus = :intTeacherStatus");
		q.setParameter("intRegistrationId", teachId);
		q.setParameter("intDaysId", dayId);
		q.setParameter("intMsttimingsId", timingId);
		q.setParameter("intTeacherStatus", 1);
		ArrayList list = (ArrayList<Mstteachertimetable>) q.list();
		s.close();
		return list;
	}

	@Override
	public Boolean checkTeachIsThereOrNot(int teachId) {
		Session s = this.sessionFactory.openSession();
		Boolean c = false;
		Query q = s.createQuery(
				"from  Mstteachertimetable a where a.mstregistration.intRegistrationId = :intRegistrationId and a.intTeacherStatus = :intTeacherStatus");
		q.setParameter("intRegistrationId", teachId);
		q.setParameter("intTeacherStatus", 1);
		ArrayList list = (ArrayList<Mstteachertimetable>) q.list();
		if (list.isEmpty()) {
			c = false;
		} else {
			c = true;
		}
		s.close();
		return c;
	}

	@Override
	public ArrayList<Trnteacherleaveapp> getApprovedLeaves(int i) {
		List<Trnteacherleaveapp> list = new ArrayList<Trnteacherleaveapp>();
		Session session = this.sessionFactory.openSession();
		list = session.createQuery("FROM Trnteacherleaveapp AS c WHERE c.intStatus!= :intStatus")
				.setParameter("intStatus", 1).list();
		session.close();
		return (ArrayList<Trnteacherleaveapp>) list;
	}

	// pie chart
	public List<Double> getStudentMonthlyAttendanceCount(int classId) {
		List<Double> l = new ArrayList<>();
		Session session = this.sessionFactory.openSession();
		String hql1 = "select distinct count(m.intRegistrationId) from Mstregistration m where m.intRoleId = 1 and"
				+ " m.intClassId = '" + classId + "'";

		BigInteger totalStrength = (BigInteger) session.createSQLQuery(hql1).uniqueResult();
		System.out.println("count of students in a class:" + totalStrength);
		int strength = totalStrength.intValue();

		String hql2 = "select count(t.intRegistrationId) from Mstregistration m , Trnattendancerecord t where m.intClassId = '"
				+ classId + "'"
				+ " and t.intRegistrationId = m.intRegistrationId and MONTH(t.dtSlotDate) = MONTH(current_date - INTERVAL 1 MONTH)";
		BigInteger present = (BigInteger) session.createSQLQuery(hql2).uniqueResult();
		int pres = present.intValue();
		System.out.println("count of students record present :" + present);
		session.close();
		int workingDays = WorkingDaysInPreviousMonth.workingDays();
		int totalrecords = strength * workingDays;
		System.out.println("workingDays:" + workingDays);
		System.out.println("totalrecords:" + totalrecords);

		if (strength != 0) {
			int absent = totalrecords - pres;
			// Double strengthDou=new Double(strength);
			// presentDou=new Double(pres);

			double presPercent = (double) ((pres * 100) / totalrecords);
			double absPercent = (double) 100 - presPercent;
			System.out.println("presPercent " + presPercent);
			System.out.println("absPercent " + absPercent);
			l.add(presPercent);
			l.add(absPercent);
		}
		return l;
	}

	@Override
	public ArrayList<Mstsyllabus> getSyllabusRecords() {
		Session s = this.sessionFactory.openSession();
		ArrayList<Mstsyllabus> syllabusList = (ArrayList<Mstsyllabus>) s.createQuery("FROM Mstsyllabus").list();
		s.close();
		return syllabusList;
	}

	@Override
	public Mstsyllabus getSyllabusRow(Integer id) {
		Session s = this.sessionFactory.openSession();
		Mstsyllabus type = null;
		Query q = s.createQuery("FROM Mstsyllabus a where a.intSubjectId = :intSubjectId");
		q.setParameter("intSubjectId", id);
		q.uniqueResult();
		ArrayList<Mstsyllabus> syllList = (ArrayList<Mstsyllabus>) q.list();
		Iterator<Mstsyllabus> it = syllList.iterator();
		while (it.hasNext()) {
			type = (Mstsyllabus) it.next();
		}
		s.close();
		return type;
	}

	@Override
	public void delSyllabus(Integer id) {
		Session s = sessionFactory.openSession();
		Mstsyllabus syl = (Mstsyllabus) s.load(Mstsyllabus.class, id);
		s.delete(syl);
		s.flush();
		s.close();
	}

	@Override
	public Mstsubject getsubjectObj(String subjectStr) {
		Session s = this.sessionFactory.openSession();
		Mstsubject mstsubject = null;
		Query q = s.createQuery("FROM Mstsubject a where a.txtSubjectName = :txtSubjectName");
		q.setParameter("txtSubjectName", subjectStr);
		q.uniqueResult();
		ArrayList<Mstsubject> syllList = (ArrayList<Mstsubject>) q.list();
		Iterator<Mstsubject> it = syllList.iterator();
		while (it.hasNext()) {
			mstsubject = (Mstsubject) it.next();
		}
		s.close();
		return mstsubject;
	}

	@Override
	public Tbldaysofweek getDayObj(String dayStr) {
		Session s = this.sessionFactory.openSession();
		Tbldaysofweek mstsubject = null;
		Query q = s.createQuery("FROM Tbldaysofweek a where a.txtDay = :txtDay");
		q.setParameter("txtDay", dayStr);
		q.uniqueResult();
		ArrayList<Tbldaysofweek> syllList = (ArrayList<Tbldaysofweek>) q.list();
		Iterator<Tbldaysofweek> it = syllList.iterator();
		while (it.hasNext()) {
			mstsubject = (Tbldaysofweek) it.next();
		}
		s.close();
		return mstsubject;
	}

	@Override
	public Msttimings getTimeObj(String timeStr) {
		Session s = this.sessionFactory.openSession();
		Msttimings mstsubject = null;
		Query q = s.createQuery("FROM Msttimings a where a.txtClassTimings = :txtClassTimings");
		q.setParameter("txtClassTimings", timeStr);
		q.uniqueResult();
		ArrayList<Msttimings> syllList = (ArrayList<Msttimings>) q.list();
		Iterator<Msttimings> it = syllList.iterator();
		while (it.hasNext()) {
			mstsubject = (Msttimings) it.next();
		}
		s.close();
		return mstsubject;
	}

	@Override
	public Mststudclass getClassObj(String classStr) {
		Session s = this.sessionFactory.openSession();
		classStr = String.valueOf((int) Double.parseDouble(classStr));
		Mststudclass mstsubject = null;
		Query q = s.createQuery("FROM Mststudclass a where a.txtClassName = :txtClassName");
		q.setParameter("txtClassName", classStr);
		q.uniqueResult();
		ArrayList<Mststudclass> syllList = (ArrayList<Mststudclass>) q.list();
		Iterator<Mststudclass> it = syllList.iterator();
		while (it.hasNext()) {
			mstsubject = (Mststudclass) it.next();
		}
		s.close();
		return mstsubject;
	}

	@Override
	public Mststudclasssection getSectionObj(String sectionStr) {
		Session s = this.sessionFactory.openSession();
		Mststudclasssection mstsubject = null;
		Query q = s.createQuery("FROM Mststudclasssection a where a.txtSectionName = :txtSectionName");
		q.setParameter("txtSectionName", sectionStr);
		q.uniqueResult();
		ArrayList<Mststudclasssection> syllList = (ArrayList<Mststudclasssection>) q.list();
		Iterator<Mststudclasssection> it = syllList.iterator();
		while (it.hasNext()) {
			mstsubject = (Mststudclasssection) it.next();
		}
		s.close();
		return mstsubject;
	}

	@Override
	public ArrayList<Mstteacherinvite> getTeachInvites() {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery("FROM Mstteacherinvite a where a.intStatus = :intStatus");
		q.setParameter("intStatus", 1);
		ArrayList<Mstteacherinvite> syllList = (ArrayList<Mstteacherinvite>) q.list();
		s.close();
		return syllList;
	}

	@Override
	public ArrayList<Mstteacherinvite> getAllTeachInvites(Date fromDate, Date toDate, int status) {
		ArrayList<Mstteacherinvite> list = new ArrayList<Mstteacherinvite>();
		Session session = this.sessionFactory.openSession();
		if (status != 1) {
			list = (ArrayList<Mstteacherinvite>) session
					.createQuery(
							"FROM Mstteacherinvite AS c WHERE c.dtInvitesDate BETWEEN :startDate AND :endDate and c.intStatus= :intStatus")
					.setParameter("startDate", fromDate).setParameter("endDate", toDate)
					.setParameter("intStatus", status).list();
		} else {
			list = (ArrayList<Mstteacherinvite>) session
					.createQuery(
							"FROM Mstteacherinvite AS c WHERE c.dtInvitesDate BETWEEN :startDate AND :endDate and c.intStatus!= :intStatus")
					.setParameter("startDate", fromDate).setParameter("endDate", toDate).setParameter("intStatus", 1)
					.list();
		}
		session.close();
		return list;
	}

	@Override
	public ArrayList<Mstteacherinvite> getAllTeachInvites(int i) {
		ArrayList<Mstteacherinvite> list = new ArrayList<Mstteacherinvite>();
		Session session = this.sessionFactory.openSession();
		list = (ArrayList<Mstteacherinvite>) session
				.createQuery("FROM Mstteacherinvite AS c WHERE c.intStatus!= :intStatus").setParameter("intStatus", 1)
				.list();
		session.close();
		return list;
	}

	@Override
	public void updateTeacherLeave(String msg, int id) {
		// TODO Auto-generated method stub

		Session s = sessionFactory.openSession();
		Query q = s.createQuery("update Mstteacherinvite set txtMessage=:p1 where intteacherinviteId=:p2")
				.setParameter("p1", msg).setParameter("p2", id);
		q.executeUpdate();
		s.close();

	}

	public void appproveOrRejectTeacherInvite(int inviteId, int status,java.util.Date date) {
		Session s = sessionFactory.openSession();
		System.out.println("int inviteId, int status,java.util.Date date"+inviteId+" "+status+" "+date);
		Query q = s.createQuery("update Mstteacherinvite set intStatus=:s , dtApprovedDate=:d where intteacherinviteId=:i")
				.setParameter("s", status).setParameter("d", date).setParameter("i", inviteId);
		q.executeUpdate();
		s.close();
	}

	@Override
	public ArrayList<Tblclassteacher> getClassTeacherList(int teachId) {
		ArrayList<Tblclassteacher> classTeachList = new ArrayList<>();
		Session session = this.sessionFactory.openSession();
		Query q = session
				.createQuery("FROM Tblclassteacher a where a.mstregistration.intRegistrationId = :intRegistrationId");
		q.setParameter("intRegistrationId", teachId);
		classTeachList = (ArrayList<Tblclassteacher>) q.list();
		session.close();
		return classTeachList;
	}

	@Override
	public Tblclassteacher checkClassTeacherIsThereOrNot(int classId, int sectionId) {
		Tblclassteacher tblclassteacher = null;
		Session session = this.sessionFactory.openSession();
		Query q = session.createQuery(
				"FROM Tblclassteacher a where a.mststudclass.intClassId = :intClassId and a.mststudclasssection.intSectionId = :intSectionId");
		q.setParameter("intClassId", classId);
		q.setParameter("intSectionId", sectionId);
		ArrayList<Tblclassteacher> classTeachersList = (ArrayList<Tblclassteacher>) q.list();
		Iterator<Tblclassteacher> itr = classTeachersList.iterator();
		while (itr.hasNext()) {
			tblclassteacher = (Tblclassteacher) itr.next();
		}
		session.close();
		return tblclassteacher;
	}

	@Override
	public Mststudclass getClassObjById(int classId) {
		Session s = this.sessionFactory.openSession();
		Mststudclass regObj = null;
		Query q = s.createQuery("from Mststudclass a where a.intClassId = :intClassId");
		q.setParameter("intClassId", classId);
		List<Mststudclass> list = q.list();
		Iterator<Mststudclass> itr = list.iterator();
		while (itr.hasNext()) {
			regObj = (Mststudclass) itr.next();
		}
		s.close();
		return regObj;
	}

	@Override
	public Mststudclasssection getSectionObjById(int sectionId) {
		Session s = this.sessionFactory.openSession();
		Mststudclasssection regObj = null;
		Query q = s.createQuery("from Mststudclasssection a where a.intSectionId = :intSectionId");
		q.setParameter("intSectionId", sectionId);
		List<Mststudclasssection> list = q.list();
		Iterator<Mststudclasssection> itr = list.iterator();
		while (itr.hasNext()) {
			regObj = (Mststudclasssection) itr.next();
		}
		s.close();
		return regObj;
	}

	@Override
	public void addClassTeacher(Tblclassteacher ctObj) {
		Session s = this.sessionFactory.openSession();
		s.save(ctObj);
		s.close();
	}

	@Override
	public void removeClassTeacher(Tblclassteacher regObj) {
		Session s = this.sessionFactory.openSession();
		Query q = s.createQuery("delete from Tblclassteacher a where a.intClassTeacherId = :intClassTeacherId");
		q.setParameter("intClassTeacherId", regObj.getIntClassTeacherId());
		q.executeUpdate();
		s.close();
	}

	@Override
	public ArrayList<ArrayList<Tblclassteacher>> getAllClassTeachersList(ArrayList<Mstregistration> teachList) {
		ArrayList<ArrayList<Tblclassteacher>> finalList = new ArrayList<>();
		ArrayList<Tblclassteacher> classTeachList = new ArrayList<>();
		Session session = this.sessionFactory.openSession();
		for (int i = 0; i < teachList.size(); i++) {
			Query q = session.createQuery(
					"FROM Tblclassteacher a where a.mstregistration.intRegistrationId = :intRegistrationId");
			q.setParameter("intRegistrationId", teachList.get(i).getIntRegistrationId());
			classTeachList = (ArrayList<Tblclassteacher>) q.list();
			if (!classTeachList.isEmpty()) {
				finalList.add(classTeachList);
			}
		}
		session.close();
		return finalList;
	}

}