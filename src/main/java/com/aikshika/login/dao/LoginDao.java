package com.aikshika.login.dao;

import java.sql.Date;
import java.util.List;

import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstregistration;

public interface LoginDao {

	/*public boolean login(int userId,String password);*/
	
	public Mstlogin loadUserByUsername(String username,String pwd);
	/*public List<Mstlogin> forgotPassword(String email,String mobileNumber,Date dob);*/
	
public Integer getRegistrationIdFromMstloginByName(String userName,String mn);
	
	public Integer updateSelectedIdFiledToSetOTP(int id,String otp);

	public String getOtpToCheck(String userName);
	public Integer afterConfirmationUpdatePasswordAndReturnToLoginPage(String password,String userName);
	
	public List<Mstlogin> getStudentInfoByParentId(int intRegId);
	
	public List<Mstregistration> getFisrtLast(String userName);
	
	public void updateUserStatusIfUserLoginFirstTime(String username, String pwd);
	
	
}
