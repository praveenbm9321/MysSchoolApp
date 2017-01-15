package com.aikshika.login.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Tblrolename;
import com.aikshika.login.dao.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao
{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Mstlogin loadUserByUsername(String username, String pwd) {

		System.out.println("inside dao impl login");
		Session session = this.sessionFactory.openSession();
		boolean b = true;
		String sn = "select m.mstregistration.intRegistrationId , m.txtPassword , t.txtRoleName ,m.intStatus FROM Mstlogin AS m ,"
				+ "Tbluserrole AS r,Tblrolename AS t WHERE m.mstregistration.intRegistrationId = r.mstregistration.intRegistrationId"
				+ " AND  t.intRoleId = r.tblrolename.intRoleId AND m.txtUserName='"
				+ username + "'" + " and m.txtPassword='" + pwd + "' and m.intLoginId = r.mstlogin.intLoginId";
		org.hibernate.Query query = session.createQuery(sn);
		// List<Mstlogin> list = new ArrayList<>();

		List<Object[]> list = query.list();
		System.out.println("list in dao......"+list);
		session.close();
		// if (list != null){
		if (!(list.isEmpty())) {
			Mstlogin mstlogin = new Mstlogin();
			System.out.println("inside if");
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				System.out.println("first for loop......");
				Object[] objects = (Object[]) iterator.next();

				for (int i = 0; i < objects.length; i++) {
					System.out.println("second for loop......");
					System.out.println(objects[i]);
					Tblrolename tblrolename = new Tblrolename();     
					tblrolename.setTxtRoleName((String) objects[2]);
					Mstregistration mstregistration = new Mstregistration();  
					mstregistration.setIntRegistrationId((Integer) objects[0]); 
					mstregistration.setTblrolename(tblrolename);
					mstlogin.setMstregistration(mstregistration);
					mstlogin.setTxtUserName(username);

					mstlogin.setTxtPassword(pwd);
					mstlogin.setIntStatus((Integer) objects[3]);

				}
			}
			return mstlogin;

		} else {
			return null;
		}

	}

	/*@Override
	public List<Mstlogin> forgotPassword(String email, String mobileNumber, java.sql.Date dob) {

		Session session = this.sessionFactory.openSession();
		String sn = "SELECT m.intRegistrationId , m.txtFmobileNumber, l.txtPassword,m.txtFemailId FROM Mstregistration AS m, Mstlogin AS l WHERE "
				+ "m.txtFmobileNumber ='"+mobileNumber+"' AND m.dtDob= '"+dob+"' AND m.txtFemailId= '"+email+"' "
						+ "AND l.mstregistration.intRegistrationId= m.intRegistrationId";
		org.hibernate.Query query = session.createQuery(sn);
		List<Mstlogin> mstlogins = query.list();
		session.close();
		List<Mstlogin> mstlogins = new ArrayList<>();
		List<Object[]> list = query.list();
		session.close();
		Mstregistration mstregistration = new Mstregistration();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstlogin mst = new Mstlogin();
			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);
				
			//mst.setMstregistration( objects[0]);
			mstregistration.setIntRegistrationId((Integer) objects[0]);
			mstregistration.setTxtFmobileNumber((String) objects[1]);
			mst.setMstregistration(mstregistration);

			mst.setTxtPassword((String) objects[2]);		
			mstregistration.setTxtFemailId((String) objects[3]);
				
					
			}
			mstlogins.add(mst);
		}
		System.out.println("query success");
		return mstlogins;


	
	}*/
	
	@Override
	public List<Mstlogin> getStudentInfoByParentId(int intRegId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		String sn = "SELECT m.mstregistration.intRegistrationId FROM Mstlogin AS m WHERE  m.intParentId='"+intRegId+"'";
		org.hibernate.Query query = session.createQuery(sn);
		List<Mstlogin> list = query.list();
		
		session.close();
		return list;
	}

	@Override
	public Integer getRegistrationIdFromMstloginByName(String userName,String mn) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		String sql = "SELECT DISTINCT l.mstregistration.intRegistrationId FROM Mstlogin AS l, Mstregistration AS m"
				+ " WHERE l.mstregistration.intRegistrationId =m.intRegistrationId and l.txtUserName='"+userName+"'"
						+ "and m.txtFmobileNumber='"+mn+"'";
		org.hibernate.Query query = session.createQuery(sql);
		List<Integer> list = query.list();
		Integer id =0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 id = (Integer) iterator.next();
			
		}
		session.close();
		return id;
	}

	
	@Override
	public Integer updateSelectedIdFiledToSetOTP(int id, String otp) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		/*String hql = "update from Mstlogin set otp = :otp where mstregistration.intRegistrationId=:iddd";*/
		String hql = "update from Mstlogin set otp = '"+otp+"' where mstregistration.intRegistrationId='"+id+"'";
		org.hibernate.Query query = session.createQuery(hql);
		//query.setParameter("otp",otp);
		//query.setParameter("iddd", id);
		int i = query.executeUpdate();
		
		session.close();
		
		return i;
	}

	@Override
	public String getOtpToCheck(String userName) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		String hql = "SELECT DISTINCT l.otp FROM Mstlogin AS l WHERE l.txtUserName='"+userName+"'";
		org.hibernate.Query query = session.createQuery(hql);
		List<Integer> list = query.list();
		//Integer OTP = null;
		String otp = "";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			otp = (String) iterator.next();
			
		}
		
		session.close();
		return otp;
	}

	@Override
	public Integer afterConfirmationUpdatePasswordAndReturnToLoginPage(String password, String userName) {
		// TODO Auto-generated method stub
		
		Session session= this.sessionFactory.openSession();
		String sql = "update from Mstlogin set txtPassword = '"+password+"' where txtUserName = '"+userName+"'";
		org.hibernate.Query query = session.createQuery(sql);
		int i = query.executeUpdate();
		session.close();
		return i;
	}

	
	@Override
	public List<Mstregistration> getFisrtLast(String userName) {
		Session session= this.sessionFactory.openSession();
		/*String sql = "select DISTINCT t.txtFirstName,t.txtLastName,t.txtFatherName from Mstregistration as t, Mstlogin as l"
				+ "where t.intRegistrationId = l.mstregistration.intRegistrationId  and l.txtUserName = '"+userName+"'";*/
		
		String sql1 = "SELECT DISTINCT t.txtFirstName,t.txtLastName,t.txtFatherName FROM Mstlogin AS l, Mstregistration AS t"
				+ " WHERE l.mstregistration.intRegistrationId = t.intRegistrationId and l.txtUserName='"+userName+"'";
		org.hibernate.Query query = session.createQuery(sql1);
		List<Mstregistration> mstregistration = new ArrayList<>();
		List<Object[]> list = query.list();
		System.out.println("list:" + list);
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			Mstregistration mstregistration1 = new Mstregistration();
			for (int i = 0; i < objects.length; i++) {
				System.out.println("........" + objects[i]);
				mstregistration1.setTxtFirstName((String) objects[0]);
				mstregistration1.setTxtLastName((String) objects[1]);
				mstregistration1.setTxtFatherName((String) objects[2]);

			}
			mstregistration.add(mstregistration1);
		}
		return mstregistration;
		
	}

@Override
	public void updateUserStatusIfUserLoginFirstTime(String username, String pwd) {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.openSession();
		String hql = "update from Mstlogin set intStatus =:s where txtUserName = '" + username + "' and txtPassword ='"
				+ pwd + "' ";
		org.hibernate.Query query = session.createQuery(hql).setParameter("s", 1);
		int i = query.executeUpdate();
		session.close();

	}


}
