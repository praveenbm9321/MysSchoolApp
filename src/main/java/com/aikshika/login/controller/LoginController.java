package com.aikshika.login.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aikshika.admin.controller.AdminController;
import com.aikshika.common.AESencrp;
import com.aikshika.common.AdAstraApi;
import com.aikshika.common.RandomUtil;
import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstregistration;
import com.aikshika.login.service.LoginService;

@Controller
public class LoginController {

	
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;

	/*
	 * @RequestMapping(value = "check.web") public String
	 * login(HttpServletRequest request,ModelMap map) { boolean login =
	 * loginService.login(Integer.parseInt(request.getParameter("username")),
	 * request.getParameter("password")); if(login) { return "ad_index";
	 * 
	 * }
	 * 
	 * else { map.addAttribute("msg", "Invalid username or password"); return
	 * "login"; }
	 * 
	 * }
	 */

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomeAdmin(ModelMap model) {

		model.addAttribute("message", "Welome to School");
		return "login";

	}

	@RequestMapping(value = "check.web", method = RequestMethod.POST)
	public String welcomeUser(@RequestParam("uname") String uname, @RequestParam("password") String pwd, ModelMap model,
			HttpServletRequest req) {

		
		List<Mstregistration> mst = loginService.getFisrtLast(uname);
		for (Iterator iterator = mst.iterator(); iterator.hasNext();) {
			Mstregistration mstregistration = (Mstregistration) iterator.next();
			String txtFirstName = mstregistration.getTxtFirstName();
			String txtLastName = mstregistration.getTxtLastName();
			String txtFatherName = mstregistration.getTxtFatherName();
			System.out.println(txtFirstName);
			System.out.println(txtLastName);
			System.out.println(txtFatherName);
			session.setAttribute("txtFirstName", txtFirstName);
			session.setAttribute("txtLastName", txtLastName);
			session.setAttribute("txtFatherName", txtFatherName);
			
		}
		
		String returnpage = "AccessDenied";
		// HttpSession session = req.getSession();
		session.setAttribute("nameforpwd", uname);
		Mstlogin userdto = loginService.loadUserByUsername(uname, AESencrp.encrypt(pwd));
		String oldPass = userdto.getTxtPassword();
		session.setAttribute("oldPass", oldPass);
		// System.out.println("In Welcome Ctrl userdto=
		// "+userdto.getMstregistration().getIntRegistrationId());
		// System.out.println(userdto.getIntParentId());

		if (userdto != null && userdto.getMstregistration() != null) {
			session.setAttribute("intRegId", userdto.getMstregistration().getIntRegistrationId());
			switch (userdto.getMstregistration().getTblrolename().getTxtRoleName()) {
			case "Admin":
				if (userdto.getIntStatus() == 0) {
					loginService.updateUserStatusIfUserLoginFirstTime(uname, AESencrp.encrypt(pwd));
					returnpage = "redirect:/FirstAttemptToLoginByAdmin.web";
				} else {
					model.addAttribute("message", "Admin Logged in Successfully");
					returnpage = "redirect:/ad_index.web";
				}
				break;
			case "Teacher":
				if (userdto.getIntStatus() == 0) {
					loginService.updateUserStatusIfUserLoginFirstTime(uname, AESencrp.encrypt(pwd));
					returnpage = "redirect:/FirstAttemptToLoginByTeacher.web";
				} else {
					model.addAttribute("message", "Teacher Logged in Successfully");
					returnpage = "redirect:/ad_index_For_Teacher.web";
				}
				break;
			case "Student":
				if (userdto.getIntStatus() == 0) {
					loginService.updateUserStatusIfUserLoginFirstTime(uname, AESencrp.encrypt(pwd));
					returnpage = "redirect:/FirstAttemptToLoginByStudent.web";
				} else {
					model.addAttribute("message", "Student Logged in Successfully");
					returnpage = "redirect:/ad_index_For_Student.web";
				}
				break;
			case "Parent":
				if (userdto.getIntStatus() == 0) {
					loginService.updateUserStatusIfUserLoginFirstTime(uname, AESencrp.encrypt(pwd));
					returnpage = "redirect:/FirstAttemptToLoginByParent.web";
				} else {
					model.addAttribute("message", "PARENT Logged in Successfully");
					returnpage = "redirect:/ad_index_For_Parent.web";
				}
				break;
			}
		} else {

			// model.addAttribute("message", "Access Denaied! , Authentication
			// Failed");
			model.addAttribute("msg", "Invalid user name and password");
			// returnpage = "AccessDenied";
			returnpage = "login";
		}
		return returnpage;
	}

	@RequestMapping(value = "EnterNameNumber.web")
	public ModelAndView sendOTP1(HttpServletRequest req, ModelMap model, ModelMap map, RedirectAttributes re) {
		ModelAndView m = new ModelAndView("EnterNameNumber");

		return m;
	}

	String userName = null;

	@RequestMapping(value = "EnterNameNumber.web", method = RequestMethod.POST)
	public ModelAndView sendOTP(HttpServletRequest req, ModelMap model, ModelMap map, RedirectAttributes re)
			throws ParseException, MessagingException, IOException {
		ModelAndView m = new ModelAndView("login");
		userName = req.getParameter("userName");
		session.setAttribute("name", userName);

		String mobileNumber = req.getParameter("phno");
		session.setAttribute("mobile", mobileNumber);
		// get registration id for forgot password
		Integer returnedIdFromMstlogin = loginService.getRegistrationIdFromMstloginByName(userName, mobileNumber);
		System.out.println("getRegistrationIdFromMstloginByName " + returnedIdFromMstlogin);
		// check registration id
		if (returnedIdFromMstlogin != 0) {

			// generate ramdom otp
			String otp = RandomUtil.generateOTP();

			// set otp to database
			Integer settingOTPToMstlogin = loginService.updateSelectedIdFiledToSetOTP(returnedIdFromMstlogin, otp);

			// send otp to the given mobile number
			AdAstraApi adAstraApi = new AdAstraApi();
			adAstraApi.sms(otp, mobileNumber);
			return new ModelAndView("redirect:/OTPToEnter.web");

		} else {
			// m.addObject("msg1", "User Name or Mobile Number does not Exist");
			return new ModelAndView("EnterNameNumber", "msg1", "User Name or Mobile Number does not Exist");
		}

	}

	@RequestMapping(value = "OTPToEnter.web")
	public ModelAndView checkOTPPage(HttpServletRequest req, ModelMap model, ModelMap map) {

		ModelAndView mav = new ModelAndView("OTPToEnter");
		return mav;

	}

	@RequestMapping(value = "OTPToEnter1.web")
	public ModelAndView checkOTP(HttpServletRequest req, ModelMap model, ModelMap map) {

		ModelAndView mav = new ModelAndView("OTPToEnter");

		String otp = req.getParameter("otp");
		String otpToCheck = loginService.getOtpToCheck(userName);

		if (otpToCheck.equals(otp)) {

			return new ModelAndView("NewPassword");
		} else {
			// mav.addObject("msg", "Wrong OTP");
			return new ModelAndView("OTPToEnter", "msg", "Wrong OTP");
		}
		// return mav;

	}

	@RequestMapping(value = "NewPassword.web")
	public ModelAndView confirmationPassword(HttpServletRequest req, ModelMap model, ModelMap map) {

		ModelAndView mav = new ModelAndView("NewPassword");
		
		String newPass = req.getParameter("newPassword");
		String cnfPass = req.getParameter("confirmPassword");

		if (newPass.equals(cnfPass)) {
			Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), userName);
			// mav.addObject("msg2", "Password changed successfully!");
			return new ModelAndView("login", "msg2", "Password changed successfully!");
		} else {
			// mav.addObject("msg2", "Password does not match!");
			return new ModelAndView("NewPassword", "msg2", "Password does not match!");
		}

	}
	
	@RequestMapping(value = "resendOTP.web")
	public ModelAndView resendOTPOnRequest(HttpServletRequest req, ModelMap model, ModelMap map) throws IOException {

		ModelAndView mav = new ModelAndView("OTPToEnter");
		String attribute = (String) session.getAttribute("mobile");
		String attribute1 = (String) session.getAttribute("name");
		// session.setAttribute("attribute1", attribute1);
		// System.out.println("attribute" + attribute);
		// System.out.println("attribute1 " + attribute1);
		Integer returnedIdFromMstlogin = loginService.getRegistrationIdFromMstloginByName(attribute1, attribute);
		System.out.println("getRegistrationIdFromMstloginByName " + returnedIdFromMstlogin);
		if (returnedIdFromMstlogin != 0) {

			String otp = RandomUtil.generateOTP();

			// set otp to database
			Integer settingOTPToMstlogin = loginService.updateSelectedIdFiledToSetOTP(returnedIdFromMstlogin, otp);

			// send otp to the given mobile number
			AdAstraApi adAstraApi = new AdAstraApi();
			adAstraApi.sms(otp, attribute);
			return new ModelAndView("redirect:/OTPToEnter.web");

		} else {
			// m.addObject("msg1", "User Name or Mobile Number does not Exist");
			return new ModelAndView("EnterNameNumber", "msg1", "User Name or Mobile Number does not Exist");
		}

	}

	@RequestMapping(value = "changePasswordForParent.web")
	public ModelAndView changePasswordOnRequest1(HttpServletRequest req, ModelMap model) throws IOException {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForParent");
		return mav;
		}
	}

	@RequestMapping(value = "changePasswordForParent.web", method = RequestMethod.POST)
	public ModelAndView changePasswordOnRequest(HttpServletRequest req, ModelMap model) throws IOException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForParent");
		
		String oldPassword = req.getParameter("txtOldPassword");
		
		String attribute = (String) session.getAttribute("oldPass");
		
		String newPass = req.getParameter("txtNewPassword");
		String cnfPass = req.getParameter("txtConfirmPassword");
		String attribute2 = (String) session.getAttribute("nameforpwd");

		if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
			Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
			return new ModelAndView("changePasswordForParent", "msg2", "Password changed successfully");
		} else {

			return new ModelAndView("changePasswordForParent", "msg3", "Password does not match!");
		}
		}

	}

	@RequestMapping(value = "changePasswordForAdmin.web")
	public ModelAndView changePasswordOnRequestForAdmin1(HttpServletRequest req, ModelMap model) throws IOException {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForAdmin");
		return mav;
		}
	}

	@RequestMapping(value = "changePasswordForAdmin.web", method = RequestMethod.POST)
	public ModelAndView changePasswordOnRequestForAdmin(HttpServletRequest req, ModelMap model) throws IOException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForAdmin");

		String oldPassword = req.getParameter("txtOldPassword");
		String attribute = (String) session.getAttribute("oldPass");
		
		String newPass = req.getParameter("txtNewPassword");
		String cnfPass = req.getParameter("txtConfirmPassword");
		String attribute2 = (String) session.getAttribute("nameforpwd");

		if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
			Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
			return new ModelAndView("changePasswordForAdmin", "msg2", "Password changed successfully!");
		} else {

			return new ModelAndView("changePasswordForAdmin", "msg3", "Password does not match!");
		}
		}

	}

	@RequestMapping(value = "changePasswordForStudent.web")
	public ModelAndView changePasswordOnRequestForStudent1(HttpServletRequest req, ModelMap model) throws IOException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForStudent");
		return mav;
		}
	}

	@RequestMapping(value = "changePasswordForStudent.web", method = RequestMethod.POST)
	public ModelAndView changePasswordOnRequestForStudent(HttpServletRequest req, ModelMap model) throws IOException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForStudent");
		
		String oldPassword = req.getParameter("txtOldPassword");
		String attribute = (String) session.getAttribute("oldPass");

		String newPass = req.getParameter("txtNewPassword");
		String cnfPass = req.getParameter("txtConfirmPassword");
		String attribute2 = (String) session.getAttribute("nameforpwd");

		if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
			Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
			return new ModelAndView("changePasswordForStudent", "msg2", "Password changed successfully!");
		} else {

			return new ModelAndView("changePasswordForStudent", "msg3", "Password does not match!");
		}
		}

	}

	@RequestMapping(value = "changePasswordForTeacher.web")
	public ModelAndView changePasswordOnRequestForTeacher1(HttpServletRequest req, ModelMap model) throws IOException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForTeacher");
		return mav;
		}
	}

	@RequestMapping(value = "changePasswordForTeacher.web", method = RequestMethod.POST)
	public ModelAndView changePasswordOnRequestForTeacher(HttpServletRequest req, ModelMap model) throws IOException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("changePasswordForTeacher");
		
		String oldPassword = req.getParameter("txtOldPassword");
		String attribute = (String) session.getAttribute("oldPass");

		String newPass = req.getParameter("txtNewPassword");
		String cnfPass = req.getParameter("txtConfirmPassword");
		String attribute2 = (String) session.getAttribute("nameforpwd");

		if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
			Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
			return new ModelAndView("changePasswordForTeacher", "msg2", "Password changed successfully!");
		} else {

			return new ModelAndView("changePasswordForTeacher", "msg3", "Password does not match!");
		}
		}

	}
	

	/// first attempt
		@RequestMapping(value = "FirstAttemptToLoginByParent.web")
		public ModelAndView FirstAttemptToLoginByParent(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByParent");
				return mav;
			}
		}
		
		@RequestMapping(value = "FirstAttemptToLoginByParent.web", method = RequestMethod.POST)
		public ModelAndView FirstAttemptToLoginByParent1(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByParent");
				
				String oldPassword = req.getParameter("txtOldPassword");
				String attribute = (String) session.getAttribute("oldPass");

				String newPass = req.getParameter("txtNewPassword");
				String cnfPass = req.getParameter("txtConfirmPassword");
				String attribute2 = (String) session.getAttribute("nameforpwd");

				if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
					Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
					return new ModelAndView("redirect:/ad_index_For_Parent.web", "msg2", "Password changed successfully!");
				} else {

					return new ModelAndView("FirstAttemptToLoginByParent", "msg3", "Password does not match!");
				}
			}

		}
		
		@RequestMapping(value = "FirstAttemptToLoginByAdmin.web")
		public ModelAndView FirstAttemptToLoginByAdmin(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByAdmin");
				return mav;
			}
		}
		
		@RequestMapping(value = "FirstAttemptToLoginByAdmin.web", method = RequestMethod.POST)
		public ModelAndView FirstAttemptToLoginByAdmin1(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByAdmin");
				
				String oldPassword = req.getParameter("txtOldPassword");
				String attribute = (String) session.getAttribute("oldPass");

				String newPass = req.getParameter("txtNewPassword");
				String cnfPass = req.getParameter("txtConfirmPassword");
				String attribute2 = (String) session.getAttribute("nameforpwd");

				if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
					Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
					return new ModelAndView("redirect:/ad_index.web", "msg2", "Password changed successfully!");
				} else {

					return new ModelAndView("FirstAttemptToLoginByAdmin", "msg3", "Password does not match!");
				}
			}

		}
		
		@RequestMapping(value = "FirstAttemptToLoginByStudent.web")
		public ModelAndView FirstAttemptToLoginByStudent(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByStudent");
				return mav;
			}
		}
		
		@RequestMapping(value = "FirstAttemptToLoginByStudent.web", method = RequestMethod.POST)
		public ModelAndView FirstAttemptToLoginByStudent1(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByStudent");
				
				String oldPassword = req.getParameter("txtOldPassword");
				String attribute = (String) session.getAttribute("oldPass");

				String newPass = req.getParameter("txtNewPassword");
				String cnfPass = req.getParameter("txtConfirmPassword");
				String attribute2 = (String) session.getAttribute("nameforpwd");

				if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
					Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
					return new ModelAndView("redirect:/ad_index_For_Student.web", "msg2", "Password changed successfully!");
				} else {

					return new ModelAndView("FirstAttemptToLoginByStudent", "msg3", "Password does not match!");
				}
			}

		}
		
		@RequestMapping(value = "FirstAttemptToLoginByTeacher.web")
		public ModelAndView FirstAttemptToLoginByTeacher(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByTeacher");
				return mav;
			}
		}
		
		@RequestMapping(value = "FirstAttemptToLoginByTeacher.web", method = RequestMethod.POST)
		public ModelAndView FirstAttemptToLoginByTeacher1(HttpServletRequest req, ModelMap model) throws IOException {
			if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
				return new ModelAndView("redirect:/login.web");
			} else {
				ModelAndView mav = new ModelAndView("FirstAttemptToLoginByTeacher");
				
				String oldPassword = req.getParameter("txtOldPassword");
				String attribute = (String) session.getAttribute("oldPass");

				String newPass = req.getParameter("txtNewPassword");
				String cnfPass = req.getParameter("txtConfirmPassword");
				String attribute2 = (String) session.getAttribute("nameforpwd");

				if ( (newPass.equals(cnfPass)) && (attribute.equals(AESencrp.encrypt(oldPassword)))) {
					Integer result = loginService.afterConfirmationUpdatePasswordAndReturnToLoginPage(AESencrp.encrypt(newPass), attribute2);
					return new ModelAndView("redirect:/ad_index_For_Teacher.web", "msg2", "Password changed successfully!");
				} else {

					return new ModelAndView("FirstAttemptToLoginByTeacher", "msg3", "Password does not match!");
				}
			}

		}
}
