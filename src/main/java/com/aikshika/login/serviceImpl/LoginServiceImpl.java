package com.aikshika.login.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstregistration;
import com.aikshika.login.dao.LoginDao;
import com.aikshika.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	@Override
	@Transactional
	public Mstlogin loadUserByUsername(String username, String pwd) {
		// TODO Auto-generated method stub
		return loginDao.loadUserByUsername(username, pwd);
	}

	/*@Override
	@Transactional
	public List<Mstlogin> forgotPassword(String email, String mobileNumber, Date dob) {
		// TODO Auto-generated method stub
		return loginDao.forgotPassword(email, mobileNumber, dob);
	}*/
	
	@Override
	@Transactional
	public List<Mstlogin> getStudentInfoByParentId(int intRegId) {
		return loginDao.getStudentInfoByParentId(intRegId);
	}

	@Override
	@Transactional
	public Integer getRegistrationIdFromMstloginByName(String userName,String mn){
		// TODO Auto-generated method stub
		return loginDao.getRegistrationIdFromMstloginByName(userName,mn);
		
	}

	@Override
	@Transactional
	public Integer updateSelectedIdFiledToSetOTP(int id, String otp) {
		// TODO Auto-generated method stub
		return loginDao.updateSelectedIdFiledToSetOTP(id, otp);
	}

	@Override
	@Transactional
	public String getOtpToCheck(String userName) {
		// TODO Auto-generated method stub
		return loginDao.getOtpToCheck(userName);
	}

	@Override
	@Transactional
	public Integer afterConfirmationUpdatePasswordAndReturnToLoginPage(String password, String userName) {
		
	return loginDao.afterConfirmationUpdatePasswordAndReturnToLoginPage(password, userName);
	}

	@Override
	@Transactional
	public List<Mstregistration> getFisrtLast(String userName) {
		// TODO Auto-generated method stub
		return loginDao.getFisrtLast(userName);
	}

	@Override
	@Transactional
	public void updateUserStatusIfUserLoginFirstTime(String username, String pwd) {
		// TODO Auto-generated method stub
		loginDao.updateUserStatusIfUserLoginFirstTime(username, pwd);
		
	}
}
