package com.aikshika.admin.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aikshika.admin.service.AdminService;
import com.aikshika.common.AESencrp;
import com.aikshika.common.AdAstraApi;
import com.aikshika.common.RandomUtil;
import com.aikshika.common.TeacherTimingsCompare;
import com.aikshika.common.TempStorage;
import com.aikshika.common.TimeTableTemp;
import com.aikshika.entity.FormEntity;
import com.aikshika.entity.Mstaffiliation;
import com.aikshika.entity.Mstbank;
import com.aikshika.entity.Mstbloodgroup;
import com.aikshika.entity.Mstcategory;
import com.aikshika.entity.Mstcity;
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
import com.aikshika.student.service.StudentService;

@Controller
@SuppressWarnings({ "unused" })
public class AdminController<T> {

	private Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	public AdminService<T> adminService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private HttpSession session;

	@Autowired
	StudentService Studentservice;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}

	@RequestMapping(value = "admin_profile.web", method = RequestMethod.POST)
	public String saveImage(@RequestParam CommonsMultipartFile blAttachment) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			Mstregistration reg = new Mstregistration();

			int intRegId = (int) session.getAttribute("intRegId");
			byte[] byteArr = blAttachment.getBytes();

			Studentservice.saveImage(byteArr, intRegId);
			return "redirect:/ad_index.web";
		}
	}

	@RequestMapping(value = "ad_index.web")
	public ModelAndView adIndex(Model model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			// return "adminDashboard";

			ModelAndView mav = new ModelAndView("adminDashboard");
			int intRegId = (int) session.getAttribute("intRegId");

			List<Mstregistration> mst = (List<Mstregistration>) Studentservice.getParentInformationByName(intRegId);

			// Iterator<Mstregistration> rr=mst

			mav.addObject("mst", mst);

			return mav;
		}
	}

	@RequestMapping(value = "/getUserImages/{id}.web")
	public void getUserImage(HttpServletResponse response, @PathVariable("id") int tweetID) throws IOException {

		response.setContentType("image/jpeg");
		int id = (int) session.getAttribute("intRegId");
		List<Mstregistration> list = Studentservice.getParentInformationByName(id);
		for (Iterator<Mstregistration> iterator = list.iterator(); iterator.hasNext();) {
			Mstregistration mstregistration = (Mstregistration) iterator.next();
			if (mstregistration.getIntRegistrationId() == tweetID) {
				byte[] buffer = mstregistration.getBlImage();
				InputStream in1 = new ByteArrayInputStream(buffer);
				IOUtils.copy(in1, response.getOutputStream());
			}
		}

	}

	// ----------------------start Assign Class Teacher

	@RequestMapping(value = "ad_assignClassTeacher.web", method = RequestMethod.GET)
	public ModelAndView assignClassTeacherGet(Model model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView("ad_class_teach");
			ArrayList<Mstregistration> teachList = (ArrayList<Mstregistration>) adminService.getTeacherRecords();
			mav.addObject("teachList", teachList);

			ArrayList<ArrayList<Tblclassteacher>> classTeachListOfList = adminService
					.getAllClassTeachersList(teachList);
			mav.addObject("classTeachListOfList", classTeachListOfList);
			return mav;
		}
	}

	@RequestMapping(value = "ad_assignClassTeacher.web", method = RequestMethod.POST)
	public ModelAndView assignClassTeacherPost2(HttpServletRequest req, RedirectAttributes redAtt) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ArrayList<Mststudclass> classList = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sectionsList = (ArrayList<Mststudclasssection>) adminService.getSections();
			redAtt.addFlashAttribute("classList", classList);
			redAtt.addFlashAttribute("sectionsList", sectionsList);

			int hiddenValue = Integer.parseInt(req.getParameter("hiddenValue"));
			if (hiddenValue == 11) {
				int teachId = Integer.parseInt(req.getParameter("teacherId"));
				ArrayList<Tblclassteacher> specificClassTeachList = adminService.getClassTeacherList(teachId);
				redAtt.addFlashAttribute("specificClassTeachList", specificClassTeachList);
				redAtt.addFlashAttribute("teachId", teachId);
				redAtt.addFlashAttribute("sucMsg",
						" Displayed " + " '" + adminService.getRegObjById(teachId).getTxtFirstName() + " "
								+ adminService.getRegObjById(teachId).getTxtLastName()
								+ "'s ' details Successfully!!!");
				redAtt.addFlashAttribute("teacherName", adminService.getRegObjById(teachId).getTxtFirstName() + " "
						+ adminService.getRegObjById(teachId).getTxtLastName());
				redAtt.addFlashAttribute("checkPost", "checkPost");
			} else if (hiddenValue == 22) {
				Tblclassteacher ctObj = new Tblclassteacher();
				int teachId = Integer.parseInt(req.getParameter("teachIdId"));
				int classId = Integer.parseInt(req.getParameter("ClassId"));
				int sectionId = Integer.parseInt(req.getParameter("SectionId"));
				Tblclassteacher regObj = adminService.checkClassTeacherIsThereOrNot(classId, sectionId);
				if (regObj == null) {
					ctObj.setMstregistration(adminService.getRegObjById(teachId));
					ctObj.setMststudclass(adminService.getClassObjById(classId));
					ctObj.setMststudclasssection(adminService.getSectionObjById(sectionId));
					adminService.addClassTeacher(ctObj);
					redAtt.addFlashAttribute("sucMsg2", " Assigned " + " '"
							+ adminService.getRegObjById(teachId).getTxtFirstName() + " "
							+ adminService.getRegObjById(teachId).getTxtLastName() + "' as a Class Teacher for 'Class "
							+ adminService.getClassObjById(classId).getTxtClassName() + "' & 'Section "
							+ adminService.getSectionObjById(sectionId).getTxtSectionName() + "' Successfully!!!");
				} else {
					adminService.removeClassTeacher(regObj);
					String classStr = adminService.getClassObjById(classId).getTxtClassName();
					String sectionStr = adminService.getSectionObjById(sectionId).getTxtSectionName();

					redAtt.addFlashAttribute("errorMsg",
							" Removed " + " '" + regObj.getMstregistration().getTxtFirstName() + " "
									+ regObj.getMstregistration().getTxtLastName() + "' as a Class Teacher for 'Class "
									+ classStr + "' & 'Section " + sectionStr + "' Successfully!!!");
					ctObj.setMstregistration(adminService.getRegObjById(teachId));
					ctObj.setMststudclass(adminService.getClassObjById(classId));
					ctObj.setMststudclasssection(adminService.getSectionObjById(sectionId));
					adminService.addClassTeacher(ctObj);
					redAtt.addFlashAttribute("sucMsg2",
							" Assigned " + " '" + adminService.getRegObjById(teachId).getTxtFirstName() + " "
									+ adminService.getRegObjById(teachId).getTxtLastName()
									+ "' as a Class Teacher for 'Class " + classStr + "' & 'Section " + sectionStr
									+ "' Successfully!!!");
				}
				ArrayList<Tblclassteacher> specificClassTeachList = adminService.getClassTeacherList(teachId);
				redAtt.addFlashAttribute("specificClassTeachList", specificClassTeachList);
				redAtt.addFlashAttribute("teachId", teachId);
				redAtt.addFlashAttribute("teacherName", adminService.getRegObjById(teachId).getTxtFirstName() + " "
						+ adminService.getRegObjById(teachId).getTxtLastName());
				redAtt.addFlashAttribute("checkPost", "checkPost");
			}
			return new ModelAndView("redirect:/ad_assignClassTeacher.web");
		}
	}

	// ----------------------end Assign Class Teacher

	// -------------start attendance
	@RequestMapping(value = "ad_attendance.web", method = RequestMethod.GET)
	public ModelAndView attendance() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView("ad_attendance");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			mav.addObject("classes", classes);
			mav.addObject("sections", sections);
			/*
			 * for (int i = 1; i <=classes.size()-1; i++) { List<Integer> cls =
			 * adminService.getStudentMonthlyAttendanceCount(i);
			 * 
			 * }
			 */

			List<Double> s = adminService.getStudentMonthlyAttendanceCount(3);
			System.out.println("class 3 Present:" + s.get(0));
			System.out.println("class 3 Absent:" + s.get(1));

			mav.addObject("p1", adminService.getStudentMonthlyAttendanceCount(1));
			mav.addObject("p2", adminService.getStudentMonthlyAttendanceCount(2));
			mav.addObject("p3", adminService.getStudentMonthlyAttendanceCount(3));
			mav.addObject("p4", adminService.getStudentMonthlyAttendanceCount(4));
			mav.addObject("p5", adminService.getStudentMonthlyAttendanceCount(5));
			mav.addObject("p6", adminService.getStudentMonthlyAttendanceCount(6));
			mav.addObject("p7", adminService.getStudentMonthlyAttendanceCount(7));
			mav.addObject("p8", adminService.getStudentMonthlyAttendanceCount(8));
			mav.addObject("p9", adminService.getStudentMonthlyAttendanceCount(9));
			mav.addObject("p10", adminService.getStudentMonthlyAttendanceCount(10));
			mav.addObject("p11", adminService.getStudentMonthlyAttendanceCount(11));
			mav.addObject("p12", adminService.getStudentMonthlyAttendanceCount(12));

			return mav;
		}
	}

	@RequestMapping(value = "ad_attendance.web", method = RequestMethod.POST)
	public ModelAndView attendancePost(HttpServletRequest req, @RequestParam Map<String, String> params,
			RedirectAttributes redAtt) throws ParseException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			redAtt.addFlashAttribute("classes", classes);
			redAtt.addFlashAttribute("sections", sections);

			String classStr = req.getParameter("ClassId");
			int classId = Integer.parseInt(classStr);
			String sectionStr = req.getParameter("SectionId");
			int sectionId = Integer.parseInt(sectionStr);
			String studIdStr = req.getParameter("StudentId");
			String d1 = params.get("from");
			String d2 = params.get("to");

			// start initialization
			int studId = 0;
			String stuName = null;
			ArrayList<Trnattendancerecord> attndList = new ArrayList<Trnattendancerecord>();

			String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date curDt = sdf.parse(sdfw);

			ArrayList<String> output = new ArrayList<String>();
			ArrayList<Integer> stuIdList = new ArrayList<Integer>();
			ArrayList<Integer> stuIdListSecondary = new ArrayList<Integer>();
			ArrayList<String> stuNameList = new ArrayList<String>();
			ArrayList<Integer> stuIdListFromReg = new ArrayList<Integer>();
			// end initialization

			if (!studIdStr.equals("") && !d1.equals("") && !d2.equals("")) {

				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date startDate = dateFormat1.parse(d1);
				java.util.Date endDate = dateFormat1.parse(d2);

				studId = Integer.parseInt(studIdStr);
				stuIdListSecondary = (ArrayList<Integer>) adminService.getStudentList(classId, sectionId, studId);

				if (stuIdListSecondary.size() != 0) {

					attndList = (ArrayList<Trnattendancerecord>) adminService.getStudentAttendFromTo(startDate, endDate,
							studId);
					Iterator<Trnattendancerecord> itr = attndList.iterator();
					while (itr.hasNext()) {
						Trnattendancerecord trnAObj = (Trnattendancerecord) itr.next();
						int nowStuId = trnAObj.getMstregistration().getIntRegistrationId();
						stuIdList.add(nowStuId);

						stuName = adminService.getStuName(nowStuId);
						stuNameList.add(stuName);
					}
				}

			} else if (!studIdStr.equals("") && d1.equals("") && d2.equals("")) {

				studId = Integer.parseInt(studIdStr);

				stuIdListSecondary = (ArrayList<Integer>) adminService.getStudentList(classId, sectionId, studId);

				if (stuIdListSecondary.size() != 0) {
					attndList = (ArrayList<Trnattendancerecord>) adminService.getStudAttend(studId, curDt);

					Iterator<Trnattendancerecord> itr = attndList.iterator();
					while (itr.hasNext()) {
						Trnattendancerecord trnAObj = (Trnattendancerecord) itr.next();
						int nowStuId = trnAObj.getMstregistration().getIntRegistrationId();
						stuIdList.add(nowStuId);

						stuName = adminService.getStuName(nowStuId);
						stuNameList.add(stuName);
					}
				}

			} else if (studIdStr.equals("") && !d1.equals("") && !d2.equals("")) {

				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date startDate = dateFormat1.parse(d1);
				java.util.Date endDate = dateFormat1.parse(d2);

				stuIdListFromReg = (ArrayList<Integer>) adminService.getStudentIds(classId, sectionId);

				for (int i = 0; i < stuIdListFromReg.size(); i++) {

					ArrayList<Trnattendancerecord> attndList11 = (ArrayList<Trnattendancerecord>) adminService
							.getStudentAttendFromTo(startDate, endDate, stuIdListFromReg.get(i));

					Iterator<Trnattendancerecord> itr = attndList11.iterator();
					while (itr.hasNext()) {
						Trnattendancerecord trnAObj = (Trnattendancerecord) itr.next();
						int nowStuId = trnAObj.getMstregistration().getIntRegistrationId();
						stuIdList.add(nowStuId);

						stuName = adminService.getStuName(nowStuId);
						stuNameList.add(stuName);
					}
					if (attndList11 != null) {
						attndList.addAll(attndList11);
					}
				}

			} else if ((studIdStr.equals("") && d1.equals("") && d2.equals("")) || (d1.equals("") && d2.equals(""))) {

				stuIdListFromReg = (ArrayList<Integer>) adminService.getStudentIds(classId, sectionId);

				for (int i = 0; i < stuIdListFromReg.size(); i++) {

					ArrayList<Trnattendancerecord> attndList11 = (ArrayList<Trnattendancerecord>) adminService
							.getStudAttend(stuIdListFromReg.get(i), curDt);

					Iterator<Trnattendancerecord> itr = attndList11.iterator();
					while (itr.hasNext()) {
						Trnattendancerecord trnAObj = (Trnattendancerecord) itr.next();
						int nowStuId = trnAObj.getMstregistration().getIntRegistrationId();
						stuIdList.add(nowStuId);

						stuName = adminService.getStuName(nowStuId);
						stuNameList.add(stuName);
					}
					if (attndList11 != null) {
						attndList.addAll(attndList11);
					}
				}

			} else {
				System.out.println("JHGJHGJHGJHGJHGJHGJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
			}

			if (attndList != null) {
				redAtt.addFlashAttribute("attndList", attndList);
				Iterator<Trnattendancerecord> i2 = attndList.iterator();
				while (i2.hasNext()) {
					Trnattendancerecord attdObj = (Trnattendancerecord) i2.next();

					String inOutTime = attdObj.getDtSlotTime();

					ArrayList<String> inOutTotalList = (ArrayList<String>) getTotalTime(inOutTime);
					String inTime = inOutTotalList.get(0);
					String outTime = inOutTotalList.get(1);
					String totalTime = inOutTotalList.get(2);

					output.add(inTime);
					output.add(outTime);
					output.add(totalTime);
				} // while
			}
			redAtt.addFlashAttribute("output", output);
			redAtt.addFlashAttribute("stuIdList", stuIdList);
			redAtt.addFlashAttribute("stuNameList", stuNameList);
			redAtt.addFlashAttribute("attendance", "attendance");

			return new ModelAndView("redirect:/ad_attendance.web");
		}
	}

	public List<String> getTotalTime(String inOutTime) {
		String totalTime = null;
		String outTime = null;
		String inTime = inOutTime.substring(0, 5);

		if (inOutTime.length() == 11) {
			outTime = inOutTime.substring(6, 11);

			String inFHalf = inTime.substring(0, 2);
			String inSHalf = inTime.substring(3, 5);

			// String inStr=inFHalf.concat(inSHalf);
			int inFHfInt = Integer.parseInt(inFHalf);
			int inSHfInt = Integer.parseInt(inSHalf);

			String outFHalf = outTime.substring(0, 2);
			String outSHalf = outTime.substring(3, 5);

			// String outStr=outFHalf.concat(outSHalf);
			int outFHfInt = Integer.parseInt(outFHalf);
			int outSHfInt = Integer.parseInt(outSHalf);

			int totalFst = outFHfInt - inFHfInt; // ----------------first
													// half main o/p
			int totalScnd = 0;
			if (outSHfInt == 00) {
				outSHfInt = 60;
				totalScnd = outSHfInt - inSHfInt;
				totalFst--;
			} else {

				if (outSHfInt < inSHfInt) {
					int totalScndaa = Math.abs(outSHfInt - inSHfInt);
					totalScnd = 60 - totalScndaa;
					totalFst--;
				} else {
					totalScnd = Math.abs(outSHfInt - inSHfInt);
				}
			}

			if (totalScnd == 60) {
				totalScnd = 00;
				totalFst++;
			}

			int lengthOut = String.valueOf(totalScnd).length();
			int lengthIn = String.valueOf(totalFst).length();
			if (lengthOut == 1 && lengthIn == 1) {
				totalTime = String.valueOf("0" + totalFst + ":" + "0" + totalScnd);
			} else if (lengthOut == 2 && lengthIn == 2) {
				totalTime = String.valueOf(totalFst + ":" + totalScnd);
			} else if (lengthIn == 1) {
				totalTime = String.valueOf("0" + totalFst + ":" + totalScnd);
			} else if (lengthOut == 1) {
				totalTime = String.valueOf(totalFst + ":" + "0" + totalScnd);
			} else {

			}

		} else {
			outTime = "--:--";
			totalTime = "00:00";
		}

		ArrayList<String> list = new ArrayList<>();
		list.add(inTime);
		list.add(outTime);
		list.add(totalTime);
		return list;
	}
	// -------------end attendance

	// -------------------teacher attend start

	@RequestMapping(value = "ad_teach_attend.web")
	public ModelAndView adminTeacherAttend(Model model) throws ParseException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView("ad_teach_attend");
			ArrayList<Mstregistration> teachList = (ArrayList<Mstregistration>) adminService.getTeacherRecords();
			mav.addObject("teachList", teachList);

			return mav;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "ad_teach_attend.web", method = RequestMethod.POST)
	public ModelAndView adminTeacherAttendPost(HttpServletRequest req, RedirectAttributes redAtt)
			throws ParseException {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			String teachIdStr = req.getParameter("teacherId");
			String fromDateStr = req.getParameter("from");
			String toDateStr = req.getParameter("to");
			int dayOfWeek = 0;

			if (!teachIdStr.equals("")) {
				int teachId = Integer.parseInt(teachIdStr);
				ArrayList<Mstteachercheckin> teachCheckInDatesList = new ArrayList<>();
				ArrayList<ArrayList<Mstteachercheckin>> listOfListForCheckIn = new ArrayList<>();
				ArrayList<ArrayList<Mstteachertimetable>> allList = new ArrayList<>();
				ArrayList<Mstteachertimetable> someList = new ArrayList<>();
				ArrayList<Mstteachertimetable> someList22 = new ArrayList<>();
				ArrayList<Msttimings> timingList = (ArrayList<Msttimings>) adminService.getTimings();
				Tbldaysofweek dayOfWeekObj = null;
				ArrayList<Tbldaysofweek> tblDOWList = new ArrayList<>();
				ArrayList<Date> dateList = new ArrayList<>();
				ArrayList<Integer> totalDays = new ArrayList<>();
				// for today
				String curDtStr = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date curDt = sdf.parse(curDtStr);

				// getting all dates
				ArrayList<Date> assignedDatesList = (ArrayList<Date>) adminService.getAllDates(teachId);

				Boolean cc = adminService.checkTeachIsThereOrNot(teachId);
				if (cc == false) {
					redAtt.addFlashAttribute("cc", "cc");
					Mstregistration reg = adminService.getRegObjById(teachId);
					redAtt.addFlashAttribute("fName", reg.getTxtFirstName());
					redAtt.addFlashAttribute("lName", reg.getTxtLastName());
				} else {

					if (fromDateStr.equals("") && toDateStr.equals("")) {

						teachCheckInDatesList = (ArrayList<Mstteachercheckin>) adminService
								.getTeacherCheckInList(curDt);
						listOfListForCheckIn.add(teachCheckInDatesList);
						dateList.add(curDt);
						dayOfWeek = curDt.getDay();
						/*
						 * if (dayOfWeek == 0) { dayOfWeek = 7; }
						 */
						dayOfWeekObj = adminService.getDayOfWeekById(dayOfWeek);
						tblDOWList.add(dayOfWeekObj);

						Date nowDate = getFinalDate(assignedDatesList, curDt);

						someList22 = (ArrayList<Mstteachertimetable>) adminService.getAllTTForTeacher(teachId,
								dayOfWeek, nowDate);
						if (someList22.size() == timingList.size()) {
							someList.addAll(someList22);
							allList.add(someList);
						} else {
							someList.addAll(someList22);
							Date previousDate = null;
							while (someList.size() < timingList.size()) {
								previousDate = assignedDatesList.get((assignedDatesList.indexOf(nowDate) - 1));
								someList22 = (ArrayList<Mstteachertimetable>) adminService.getAllTTForTeacher(teachId,
										dayOfWeek, previousDate);
								someList = getUniqueTTRecords(someList, someList22);
								if (someList.size() == 7) {
									Collections.sort(someList, new TeacherTimingsCompare());
									allList.add(someList);
									break;
								}
								nowDate = previousDate;
							}
						}

						totalDays.add(1);

					} else {
						Date nowDate = null;
						java.util.Date fromDt = sdf.parse(fromDateStr);
						java.util.Date toDt = sdf.parse(toDateStr);

						Date dateeTo = getFinalDate(assignedDatesList, toDt);
						Date datee = getFinalDate(assignedDatesList, fromDt);

						if (datee == null && dateeTo == null) {
							redAtt.addFlashAttribute("danger", "danger");
						} else if (datee == null && dateeTo != null) {
							dateList = (ArrayList<Date>) getListOfDaysBetweenTwoDates(assignedDatesList.get(0), toDt);
						} else {
							dateList = (ArrayList<Date>) getListOfDaysBetweenTwoDates(fromDt, toDt);
						}

						Iterator<Date> itr = dateList.iterator();
						while (itr.hasNext()) {
							totalDays.add(1);

							ArrayList<Mstteachertimetable> someListt = new ArrayList<>();
							ArrayList<Mstteachertimetable> someListt22 = new ArrayList<>();

							Date nowDate11 = (Date) itr.next();
							nowDate = getFinalDate(assignedDatesList, nowDate11);
							dayOfWeek = nowDate11.getDay();
							/*
							 * if (dayOfWeek == 0) { dayOfWeek = 7; }
							 */
							dayOfWeekObj = adminService.getDayOfWeekById(dayOfWeek);
							tblDOWList.add(dayOfWeekObj);

							teachCheckInDatesList = (ArrayList<Mstteachercheckin>) adminService
									.getTeacherCheckInList(nowDate11);
							listOfListForCheckIn.add(teachCheckInDatesList);

							someListt22 = (ArrayList<Mstteachertimetable>) adminService.getAllTTForTeacher(teachId,
									dayOfWeek, nowDate);
							if (someListt22.size() == timingList.size()) {
								someListt.addAll(someListt22);
								System.out.println("someList Size: " + someListt.size());
								allList.add(someListt);
							} else if (dayOfWeek == 0) {
								someListt.addAll(someListt22);
								allList.add(someListt);
							} else {
								someListt.addAll(someListt22);
								Date previousDate = null;
								while (someListt.size() < timingList.size()) {
									previousDate = assignedDatesList.get((assignedDatesList.indexOf(nowDate) - 1));
									someListt22 = (ArrayList<Mstteachertimetable>) adminService
											.getAllTTForTeacher(teachId, dayOfWeek, previousDate);
									someListt = getUniqueTTRecords(someListt, someListt22);
									if (someListt.size() == 7) {
										Collections.sort(someListt, new TeacherTimingsCompare());
										allList.add(someListt);
										break;
									}
									nowDate = previousDate;
								}
							}
						}
					}
				}
				redAtt.addFlashAttribute("timingList", timingList);
				redAtt.addFlashAttribute("totalDays", totalDays);
				redAtt.addFlashAttribute("dateList", dateList);
				redAtt.addFlashAttribute("tblDOWList", tblDOWList);

				redAtt.addFlashAttribute("allList", allList);
				redAtt.addFlashAttribute("listOfListForCheckIn", listOfListForCheckIn);
				redAtt.addFlashAttribute("check", "check");
			}
			return new ModelAndView("redirect:/ad_teach_attend.web");
		}
	}

	public ArrayList<Mstteachertimetable> getUniqueTTRecords(ArrayList<Mstteachertimetable> someList,
			ArrayList<Mstteachertimetable> someList22) {

		Map<Integer, Mstteachertimetable> insideObjMap = new HashMap<Integer, Mstteachertimetable>();
		Iterator<Mstteachertimetable> itr = someList.iterator();
		while (itr.hasNext()) {
			Mstteachertimetable tTTObj = (Mstteachertimetable) itr.next();
			int timeId = tTTObj.getMsttimings().getIntMsttimingsId();
			if (!insideObjMap.containsKey(timeId)) {
				insideObjMap.put(timeId, tTTObj);
			}
		}
		Iterator<Mstteachertimetable> itr22 = someList22.iterator();
		while (itr22.hasNext()) {
			Mstteachertimetable tTTObj22 = (Mstteachertimetable) itr22.next();
			int timeId22 = tTTObj22.getMsttimings().getIntMsttimingsId();
			if (!insideObjMap.containsKey(timeId22)) {
				insideObjMap.put(timeId22, tTTObj22);
			}
		}
		someList.clear();
		someList22.clear();

		Collection<Mstteachertimetable> collection = insideObjMap.values();
		someList.addAll(collection);

		return someList;
	}

	private Date getFinalDate(ArrayList<Date> assignedDatesList, Date nowDate11) throws ParseException {
		boolean check = false;
		Date nowDate = null;
		if (assignedDatesList.size() > 1) {

			Date s1 = null;
			Date s2 = null;
			for (int i = 0; i < assignedDatesList.size(); i++) {
				s1 = assignedDatesList.get(i);
				if (assignedDatesList.indexOf(s1) == (assignedDatesList.size() - 1)) {
					s2 = s1;
					if (nowDate11.compareTo(s1) > 0) {
						nowDate = s1;
						break;
					}
				} else {
					s2 = assignedDatesList.get(i + 1);
				}

				if (nowDate11.compareTo(s1) == 0) {
					nowDate = s1;
					break;
				} else if (nowDate11.compareTo(s2) == 0) {
					nowDate = s2;
					break;
				} else {
					check = isDateThere(s1, s2, nowDate11);
					if (check == true) {
						nowDate = s1;
						break;
					}
				}

			}

		}
		return nowDate;
	}

	private Boolean isDateThere(Date startDate, Date endDate, Date d) throws ParseException {
		Boolean check = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String oeStartDateStr = sdf.format(startDate);
		String oeEndDateStr = sdf.format(endDate);
		String currDt = sdf.format(d);

		if ((d.after(startDate) && (d.before(endDate)))
				|| (currDt.equals(sdf.format(startDate)) || currDt.equals(sdf.format(endDate)))) {
			check = true;
		} else {
			check = false;
		}

		return check;

	}

	private List<Date> getListOfDaysBetweenTwoDates(Date startDate, Date endDate) {
		List<Date> result = new ArrayList<Date>();
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day to endDate to make sure
											// endDate is included into the
											// final list
		while (start.before(end)) {
			result.add(start.getTime());
			start.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	// ------------------- teacher attend end

	// =================-----> start leave request <-----===============

	@RequestMapping(value = "ad_leaveInfo.web")
	public ModelAndView ad_leaveInfo() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView("ad_leaveInfo");
			ArrayList<Trnteacherleaveapp> TeachLeaveObjList = (ArrayList<Trnteacherleaveapp>) adminService
					.getLeaveReqFromTeacher();
			Collections.reverse(TeachLeaveObjList);
			int size = TeachLeaveObjList.size();
			if (size > 0) {
				if (size == 1) {
					mav.addObject("msgReqList", " " + "'" + size + "'" + " request found!!!!!!!!");
				} else {
					mav.addObject("msgReqList", " " + "'" + size + "'" + " requests found!!!!!!!!");
				}
			}
			mav.addObject("teachList", TeachLeaveObjList);

			ArrayList<Trnteacherleaveapp> teachLeaveListGet = (ArrayList<Trnteacherleaveapp>) adminService
					.getApprovedLeaves(1);
			Collections.reverse(teachLeaveListGet);
			ArrayList<Mstleave> leaveTypeList = (ArrayList<Mstleave>) adminService.getLeaveTypes();
			mav.addObject("leaveTypeList", leaveTypeList);
			mav.addObject("teachLeaveListGet", teachLeaveListGet);
			mav.addObject("msgGet", "msgGet");
			return mav;
		}
	}

	@RequestMapping(value = "ad_leaveInfo.web", method = RequestMethod.POST)
	public ModelAndView ad_leaveInfoPost(@RequestParam Map<String, String> params, RedirectAttributes redAtt,
			HttpServletRequest req) throws ParseException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			// pending requests handling
			String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date curDt = sdf.parse(sdfw);

			String[] stringIds = req.getParameterValues("teachLeaveReqIds");
			int count = stringIds.length;

			String action = req.getParameter("action");
			if ("app".equals(action)) {
				for (String s : stringIds) {
					int nowId = Integer.parseInt(s);
					int approvedStatusId = 2;
					adminService.letApprove(nowId, curDt, approvedStatusId);
				}
				redAtt.addFlashAttribute("msg1", " Your are approved " + count);
				if (count == 1) {
					redAtt.addFlashAttribute("msg2", " teacher leave request successfully!!!!");
				} else {
					redAtt.addFlashAttribute("msg2", " teachers leave request successfully!!!!");
				}
			} else if ("rej".equals(action)) {
				for (String s : stringIds) {
					int nowId = Integer.parseInt(s);
					int approvedStatusId = 3;
					adminService.letReject(nowId, curDt, approvedStatusId);
				}
				redAtt.addFlashAttribute("msg1", " Your are rejected " + count);
				if (count == 1) {
					redAtt.addFlashAttribute("msg2", " teacher leave request successfully!!!!");
				} else {
					redAtt.addFlashAttribute("msg2", " teachers leave request successfully!!!!");
				}
			}
			return new ModelAndView("redirect:/ad_leaveInfo.web");
		}
	}

	@RequestMapping(value = "ad_leave_request_check.web", method = RequestMethod.POST)
	public ModelAndView adLeaveRequestPost(@RequestParam Map<String, String> params, RedirectAttributes redAtt)
			throws ParseException {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView();

			// request status
			String from = params.get("fromDate");
			String to = params.get("toDate");
			String statusStr = params.get("status");
			int status = Integer.parseInt(statusStr);

			String statusForScndtab = params.get("secondTab");
			redAtt.addFlashAttribute("statusForScndtab", statusForScndtab);
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fromDate = dateFormat1.parse(from);
			java.util.Date toDate = dateFormat1.parse(to);

			ArrayList<Trnteacherleaveapp> teachLeaveList = (ArrayList<Trnteacherleaveapp>) adminService
					.getApprovedLeaves(fromDate, toDate, status);
			Collections.reverse(teachLeaveList);
			redAtt.addFlashAttribute("teachLeaveList", teachLeaveList);
			int size = teachLeaveList.size();
			if (size > 0) {
				if (size == 1) {
					redAtt.addFlashAttribute("msg11", " " + "'" + size + "'" + " record found!!!!!!!!");
				} else {
					redAtt.addFlashAttribute("msg11", " " + "'" + size + "'" + " records found!!!!!!!!");
				}
			}
			redAtt.addFlashAttribute("msg22", "msg");
			return new ModelAndView("redirect:/ad_leaveInfo.web");
		}
	}

	// =================-----> end leave request <-----===============

	@RequestMapping(value = "ad_activity log.web")
	public String activityLog(Model model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			return "ad_activity log";
		}
	}

	@RequestMapping(value = "login.web")
	public String login(Model model) {

		return "login";

	}

	@RequestMapping(value = "logout.web")
	public String logout(Model model, HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("logout", "You are SuccessFully Logged Out");

		System.out.println("inside logout method......");
		session.removeAttribute("intRegId");
		session.removeAttribute("txtFirstName");
		session.removeAttribute("txtLastName");
		session.removeAttribute("txtFatherName");
		session.removeAttribute("nameforpwd");
		session.removeAttribute("name");
		session.removeAttribute("mobile");
		// request.getSession().invalidate();
		session.invalidate();

		return "redirect:/login.web";
		// return "logout";
	}

	// -------- Master record start

	TempStorage tempstorage = new TempStorage();

	@RequestMapping(value = "user_staff.web")
	public ModelAndView userStaff() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			System.out.println("inside get");
			ModelAndView m = new ModelAndView("user_staff");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstcategory> category = (ArrayList<Mstcategory>) adminService.getCategory();
			// ArrayList<Mstcity> cities = (ArrayList<Mstcity>)
			// adminService.city();
			ArrayList<Mstgender> gal = (ArrayList<Mstgender>) adminService.gender();
			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("category", category);
			m.addObject("gal", gal);
			// m.addObject("cities", cities);
			m.addObject("i", "i");
			return m;
		}
	}

	@RequestMapping(value = "Ajax_userstaff.web")
	public ModelAndView ajaxCall(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("Ajax_userstaff");
			String cat = req.getParameter("val3");
			String cid = req.getParameter("val1");
			String sid = req.getParameter("val2");

			if (cat == null) {
				cat = "";
			}

			if (cat.equals("2") || cat.equals("3")) {
				ArrayList<Mstregistration> mstal = (ArrayList<Mstregistration>) adminService.getAllStaffLists("");
				m.addObject("Tmstal", mstal);

				System.out.println("inside teacher");
			} else if (cat.equals("1") && !(cid.equals("") || cid == null) && !(sid.equals("") || sid == null)) {
				int ClassId = Integer.parseInt(cid);
				int SectionId = Integer.parseInt(sid);
				ArrayList<Mstregistration> mstal = (ArrayList<Mstregistration>) adminService.getAllStudentLists(ClassId,
						SectionId, "");
				m.addObject("Smstal", mstal);
				System.out.println("inside student");
			} else if (!(cid.equals("") || cid == null) && !(sid.equals("") || sid == null)
					&& (cat.equals("") || cat == null)) {
				int ClassId = Integer.parseInt(cid);
				int SectionId = Integer.parseInt(sid);
				ArrayList<Mstregistration> mstal = (ArrayList<Mstregistration>) adminService.getAllStudentLists(ClassId,
						SectionId, "");
				m.addObject("Smstal", mstal);
			} else {
				System.err.println("nothing here");
			}

			return m;
		}
	}

	@RequestMapping(value = "user_staff.web", method = RequestMethod.POST)
	public ModelAndView userStaffSearch(HttpServletRequest req) {
		System.out.println("inside post");
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("user_staff");
			// ArrayList<Mstcity> cities = (ArrayList<Mstcity>)
			// adminService.city();
			ArrayList<Mstgender> gal = (ArrayList<Mstgender>) adminService.gender();
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstcategory> category = (ArrayList<Mstcategory>) adminService.getCategory();
			System.out.println("inside post after ");
			String CatId = req.getParameter("categoryId");
			String Id = req.getParameter("StudentId").trim();
			String Cls = req.getParameter("ClassId");
			String Sec = req.getParameter("SectionId");
			ArrayList<String> tempal = new ArrayList<>();
			tempal.add(CatId);
			tempal.add(Id);
			tempal.add(Cls);
			tempal.add(Sec);
			tempstorage.setTemp(tempal);

			System.out.println(Id + "hey here its printing empty");
			if (CatId.equals("1")) {
				int ClassId = Integer.parseInt(Cls);
				int SectionId = Integer.parseInt(Sec);
				ArrayList<Mstregistration> Mstal = (ArrayList<Mstregistration>) adminService.getAllStudentLists(ClassId,
						SectionId, Id);
				m.addObject("Mstal", Mstal);
				m.addObject("performance", "performance");

			} else if (CatId.equals("2") || CatId.equals("3")) {
				ArrayList<Mstregistration> Mstal = (ArrayList<Mstregistration>) adminService.getAllStaffLists(Id);
				m.addObject("Mstal", Mstal);
				m.addObject("performance1", "performance1");
			}
			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("gal", gal);
			// m.addObject("cities", cities);
			m.addObject("category", category);

			return m;
		}
	}

	@RequestMapping(value = "Ajax_UserstaffDelete.web", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {

			adminService.deleteUser(Integer.parseInt(req.getParameter("val1")));
			return new ModelAndView("redirect:/user_staff.web");
		}
	}

	@RequestMapping(value = "updateStudent/{id}.web", method = RequestMethod.GET)
	public String updateStudent(@PathVariable("id") Integer id, RedirectAttributes re) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			System.out.println(id);
			System.out.println("inside method");
			Mstregistration reg = adminService.getRegObjById(id);
			System.out.println(reg.getBlBelongToBpl());
			Mstbank bank = adminService.getBankDetails(id);
			re.addFlashAttribute("reg", reg);
			re.addFlashAttribute("bnk", bank);
			return "redirect:/ad_reg.web";
		}
	}

	@RequestMapping(value = "updateStaff/{id}.web", method = RequestMethod.GET)
	public String updateStaff(@PathVariable("id") Integer id, RedirectAttributes re, HttpServletRequest req) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			System.out.println(id);
			System.out.println("inside method");
			Mstregistration reg = adminService.getRegObjById(id);
			Mstbank bank = adminService.getBankDetails(id);
			re.addFlashAttribute("Treg", reg);
			re.addFlashAttribute("bnk", bank);
			return "redirect:/ad_reg.web";
		}
	}

	@RequestMapping(value = "ad_Sreg/{id}.web", method = RequestMethod.POST)
	public String updateStudentPost(@PathVariable("id") Integer id, RedirectAttributes re, HttpServletRequest req)
			throws ParseException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			ModelAndView mav = new ModelAndView();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Mstregistration reg = new Mstregistration();

			String tcNum = req.getParameter("txtTransferCertificateNo");

			String tcDate = req.getParameter("dtTransferCertificateDate");
			if (tcDate.equals("")) {
			} else {
				java.util.Date tcDt = sdf.parse(tcDate);
				reg.setDtTransferCertificateDate(tcDt);
			}

			String preSchool = req.getParameter("txtPreviousSchoolName");
			String preSchoolAdd = req.getParameter("txtPreviousSchoolAddress");

			String stuFirstName = req.getParameter("txtFirstName");
			String stuMiddleName = req.getParameter("txtMiddleName");
			String stuLastName = req.getParameter("txtLastName");

			String fatherName = req.getParameter("txtFatherName");

			String motherName = req.getParameter("txtMotherName");

			String fOccu = req.getParameter("txtFoccupation");
			String mOccu = req.getParameter("txtMoccupation");

			String fMobile = req.getParameter("txtFmobileNumber");
			System.out.println(fMobile);
			String mMobile = req.getParameter("txtMmobileNumber");

			String fEduQua = req.getParameter("txtFeducationalQualification");
			String mEduQua = req.getParameter("txtMeducationalQualification");

			String fOtherNum = req.getParameter("txtFotherNumber");
			String mOtherNum = req.getParameter("txtMotherNumber");

			String fEmail = req.getParameter("txtFemailId");
			String mEmail = req.getParameter("txtMemailId");
			System.out.println(mEmail);

			String fAadhar = req.getParameter("txtFaadharNumber");
			String mAadhar = req.getParameter("txtMaadharNumber");

			reg.setTxtTransferCertificateNo(tcNum);
			reg.setTxtPreviousSchoolName(preSchool);
			reg.setTxtPreviousSchoolAddress(preSchoolAdd);
			reg.setTxtFirstName(stuFirstName);
			reg.setTxtMiddleName(stuMiddleName);
			reg.setTxtLastName(stuLastName);
			reg.setTxtFatherName(fatherName);
			reg.setTxtMotherName(motherName);
			reg.setTxtFoccupation(fOccu);
			reg.setTxtMoccupation(mOccu);
			reg.setTxtFmobileNumber(fMobile);
			reg.setTxtMmobileNumber(mMobile);
			reg.setTxtFeducationalQualification(fEduQua);
			reg.setTxtMeducationalQualification(mEduQua);
			reg.setTxtFotherNumber(fOtherNum);
			reg.setTxtMotherNumber(mOtherNum);
			reg.setTxtFemailId(fEmail);
			reg.setTxtMemailId(mEmail);
			reg.setTxtFaadharNumber(fAadhar);
			reg.setTxtMaadharNumber(mAadhar);

			int clas = Integer.parseInt(req.getParameter("mststudclass"));
			int section = Integer.parseInt(req.getParameter("mststudclasssection"));
			String semester = req.getParameter("mstsemester");
			System.out.println(semester);
			if (semester.equals("")) {
			} else {
				Mstsemester semObj = new Mstsemester();
				int sem = Integer.parseInt(semester);
				semObj.setIntSemesterId(sem);
				reg.setMstsemester(semObj);
			}
			int stream = Integer.parseInt(req.getParameter("mststream"));
			int medium = Integer.parseInt(req.getParameter("mstmedium"));
			int motherTongue = Integer.parseInt(req.getParameter("mstmothertongue"));
			String affStr = req.getParameter("mstaffiliation");

			if (affStr.equals("")) {
			} else {
				Mstaffiliation affObj = new Mstaffiliation();
				int aff = Integer.parseInt(affStr);
				affObj.setIntAffiliationId(aff);
				reg.setMstaffiliation(affObj);
			}
			String schlTypeStr = req.getParameter("mstschooltype");
			if (schlTypeStr.equals("")) {
			} else {
				Mstschooltype schltypeObj = new Mstschooltype();
				int schlType = Integer.parseInt(schlTypeStr);
				schltypeObj.setIntSchoolTypeId(schlType);
				reg.setMstschooltype(schltypeObj);
			}

			Mststudclass claObj = new Mststudclass();
			Mststudclasssection secObj = new Mststudclasssection();
			Mststream strmObj = new Mststream();
			Mstmedium mediumObj = new Mstmedium();
			Mstmothertongue motherObj = new Mstmothertongue();

			claObj.setIntClassId(clas);
			secObj.setIntSectionId(section);
			strmObj.setIntStreamId(stream);
			mediumObj.setIntMediumId(medium);
			motherObj.setIntmothertongueId(motherTongue);

			reg.setMststudclass(claObj);
			reg.setMststudclasssection(secObj);
			reg.setMststream(strmObj);
			reg.setMstmedium(mediumObj);
			reg.setMstmothertongue(motherObj);

			// ---------------------------------------

			Mstgender gen = new Mstgender();
			Mstbloodgroup bg = new Mstbloodgroup();
			Mstreligion rel = new Mstreligion();
			Mstsocialcategory soc = new Mstsocialcategory();
			Mstdisabilitychild dis = new Mstdisabilitychild();

			// ----- getting values
			String dob = req.getParameter("dob");
			java.util.Date dt = sdf.parse(dob); // date of birth
			String adate = req.getParameter("Adate");
			if (adate.equals("")) {
			} else {
				java.util.Date admissionDate = sdf.parse(adate); // admission
																	// date
				reg.setDtAdmissionDate(admissionDate);
			}
			int blood = Integer.parseInt(req.getParameter("bloodgroup"));
			int gender = Integer.parseInt(req.getParameter("gender"));
			int religion = Integer.parseInt(req.getParameter("religion"));
			int social = Integer.parseInt(req.getParameter("social"));
			int childgg = Integer.parseInt(req.getParameter("child"));
			if (childgg != 1) {
				dis.setIntDisabilityChildId(Integer.parseInt(req.getParameter("disChild")));
			} else if (childgg == 1) {
				dis.setIntDisabilityChildId(childgg);
			}

			String cityStr = req.getParameter("city");
			String nation = req.getParameter("nationality");
			String incomeStr = req.getParameter("income");
			System.out.println(incomeStr);

			if (incomeStr.equals("")) {
			} else {
				Long income = (long) Integer.parseInt(incomeStr);
				reg.setNmParentsAnnualIncome(income);
			}
			String scasteno = req.getParameter("scasteno");
			String scaste = req.getParameter("scaste");
			String perAddress = req.getParameter("perAdd");
			System.out.println(perAddress);
			String tempAddress = req.getParameter("tempAdd");
			System.out.println(tempAddress);
			String local = req.getParameter("local");
			String taluk = req.getParameter("taluk");
			String district = req.getParameter("district");
			String code = req.getParameter("code");
			System.out.println(code);
			String steno = req.getParameter("steno");
			String bplcardno = req.getParameter("bplCardNo");

			String acno = req.getParameter("acNum");
			String ifsc = req.getParameter("ifsc");

			// --setting values to db
			gen.setIntGenderId(gender);
			reg.setMstgender(gen); // gender
			bg.setIntBloodGroupId(blood);
			reg.setMstbloodgroup(bg); // blood group
			rel.setIntReligionId(religion);
			reg.setMstreligion(rel); // religion
			soc.setIntSocialCategoryId(social);
			reg.setMstsocialcategory(soc); // social
			reg.setMstdisabilitychild(dis); // disability
			reg.setTxtCity(cityStr); // city
			reg.setTxtNationality(nation);
			reg.setTxtStudentCasteCertificateNo(scasteno);
			reg.setTxtStudentCaste(scaste);
			reg.setTxtPerAddress(perAddress);
			reg.setTxtTempAddress(tempAddress);
			reg.setTxtLocality(local);
			reg.setTxtTaluk(taluk);
			reg.setTxtDistrict(district);
			reg.setTxtPincode(code);
			reg.setTxtStudentEnrollmentNumber(steno);
			reg.setDtDob(dt);
			reg.setTxtBplcardNo(bplcardno);
			reg.setIntRegistrationId(id);
			// -----------------------

			String yOrNo = req.getParameter("blBelongToBpl");
			if (yOrNo.equals("")) {
			} else {
				Boolean btbpl = Boolean.parseBoolean(yOrNo);
				reg.setBlBelongToBpl(btbpl);
			}

			// for role
			Tblrolename trn = new Tblrolename();
			trn.setIntRoleId(adminService.getRoleId("Student"));
			reg.setTblrolename(trn);
			adminService.addRegister(reg);
			// for bank
			Mstbank mstbank = new Mstbank();
			String bankName = req.getParameter("bank");
			if (bankName.equals("")) {
			} else {
				int bankid = Integer.parseInt(bankName);
				Tblbankname tblbn = new Tblbankname();
				tblbn.setIntBankNameId(bankid);
				mstbank.setTblbankname(tblbn);
			}

			mstbank.setTxtBankAccountNo(acno);
			mstbank.setTxtIfsccode(ifsc);

			mstbank.setMstregistration(reg);
			adminService.addBankDetails(mstbank);
			re.addFlashAttribute("msg3", "success!! " + stuFirstName + " your data updated");
			return "redirect:/user_staff.web";
		}
	}

	@RequestMapping(value = "ad_Treg/{id}.web", method = RequestMethod.POST)
	public String updateStaffPost(@PathVariable("id") Integer id, RedirectAttributes re, HttpServletRequest req)
			throws ParseException {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			ModelAndView mav = new ModelAndView();
			Mstregistration regObj = new Mstregistration();
			List<Mstgender> gender = adminService.gender();
			List<Mstreligion> religion = adminService.religion();
			ArrayList<Mstmaritalstatus> marital = (ArrayList<Mstmaritalstatus>) adminService.getMaritalStatus();
			ArrayList<Mstsubject> subject = (ArrayList<Mstsubject>) adminService.getSubject();
			mav.addObject("gender", gender);
			mav.addObject("religion", religion);
			mav.addObject("marital", marital);
			mav.addObject("subject", subject);
			// not required start
			String rel = req.getParameter("religion");
			if (rel.equals("")) {
			} else {
				int relId = Integer.parseInt(rel);
				Mstreligion relObj = new Mstreligion();
				relObj.setIntReligionId(relId);
				regObj.setMstreligion(relObj);
			}

			String marry = req.getParameter("marital");
			if (marry.equals("")) {
			} else {
				int marryId = Integer.parseInt(marry);
				Mstmaritalstatus marryObj = new Mstmaritalstatus();
				marryObj.setIntMaritalStatusId(marryId);
				regObj.setMstmaritalstatus(marryObj);
			}

			String ct = req.getParameter("city");
			regObj.setTxtCity(ct);

			String sub = req.getParameter("subject");
			if (sub.equals("")) {
			} else {
				int subId = Integer.parseInt(sub);
				Mstsubject subObj = new Mstsubject();
				subObj.setIntSubjectId(subId);
				regObj.setMstsubject(subObj);
			}
			// not required end

			String fN = req.getParameter("firstName");
			String mN = req.getParameter("middleName");
			String lN = req.getParameter("lastName");
			String fthrN = req.getParameter("fatherName");
			String gen = req.getParameter("gender");
			int genId = Integer.parseInt(gen);
			String mNum = req.getParameter("mobNum");
			String email = req.getParameter("email");
			String jDt = req.getParameter("joinDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date joinDt = sdf.parse(jDt);
			String preShlNm = req.getParameter("preSchoolName");
			String exp = req.getParameter("experience");
			String desi = req.getParameter("designation");
			String ctc = req.getParameter("ctc");
			String perAdd = req.getParameter("perAdd");
			String tempAdd = req.getParameter("tempAdd");

			regObj.setTxtFirstName(fN);
			regObj.setTxtMiddleName(mN);
			regObj.setTxtLastName(lN);
			regObj.setTxtFatherName(fthrN);
			regObj.setTxtFmobileNumber(mNum);
			regObj.setTxtEmailId(email);
			regObj.setDtJoiningDate(joinDt);
			regObj.setTxtPreviousSchoolName(preShlNm);
			regObj.setTxtExperience(exp);
			regObj.setTxtDesignation(desi);
			regObj.setTxtPerAddress(perAdd);
			regObj.setTxtTempAddress(tempAdd);
			regObj.setIntRegistrationId(id);
			System.out.println(regObj.getIntRegistrationId());

			Mstgender genObj = new Mstgender();
			genObj.setIntGenderId(genId);

			Tblrolename trn = new Tblrolename();
			trn.setIntRoleId(3);

			regObj.setMstgender(genObj);
			regObj.setTblrolename(trn);

			adminService.saveTeachReg(regObj);

			// for bank
			Mstbank bankObj = new Mstbank();
			bankObj.setMstregistration(regObj);
			bankObj.setTxtCtc(ctc);
			adminService.saveTeachBank(bankObj);

			String secondTab = req.getParameter("secondTab");
			re.addFlashAttribute("secondTab", secondTab);

			re.addFlashAttribute("msg3", "success!! " + fN + " data updated");
			return "redirect:/user_staff.web";
		}
	}

	// ------- master record end

	// ---------user credentials start
	@RequestMapping(value = "user_credentials.web")
	public ModelAndView userCredentials() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			System.out.println("inside first user");
			ModelAndView m = new ModelAndView("user_credentials");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstcategory> category = (ArrayList<Mstcategory>) adminService.getCategory();
			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("category", category);
			return m;
		}
	}

	@RequestMapping(value = "user_credentials.web", method = RequestMethod.POST)
	public ModelAndView userCredentialsSubmit(HttpServletRequest req, RedirectAttributes redAtt) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			System.out.println("inside user");
			ModelAndView m = new ModelAndView();
			String CatId = req.getParameter("categoryId");

			String Id = req.getParameter("StudentId");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstcategory> category = (ArrayList<Mstcategory>) adminService.getCategory();
			ArrayList<Mstlogin> loginal = (ArrayList<Mstlogin>) adminService.getUser();

			if (CatId.equals("1")) {
				int ClassId = Integer.parseInt(req.getParameter("ClassId"));
				int SectionId = Integer.parseInt(req.getParameter("SectionId"));
				ArrayList<Mstregistration> Mstal = (ArrayList<Mstregistration>) adminService.getAllStudentLists(ClassId,
						SectionId, Id);

				redAtt.addFlashAttribute("loginal", loginal);
				redAtt.addFlashAttribute("Mstal", Mstal);
				redAtt.addFlashAttribute("performance", "performance");
			} else if (CatId.equals("2") || CatId.equals("3")) {
				ArrayList<Mstregistration> Mstal = (ArrayList<Mstregistration>) adminService.getAllStaffLists(Id);
				redAtt.addFlashAttribute("Mstal", Mstal);
				redAtt.addFlashAttribute("loginal", loginal);
				redAtt.addFlashAttribute("performance1", "performance1");
			}

			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("category", category);

			return new ModelAndView("redirect:/user_credentials.web");
		}
	}

	// -----------user credentials end

	@RequestMapping(value = "ad_library.web")
	public String adLibrary(Model model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			return "ad_library";
		}
	}

	// ---------------------------doc upload start--------------------------

	@RequestMapping(value = "ad_document-upload.web", method = RequestMethod.GET)
	public ModelAndView adDocumentUpload() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView("ad_document-upload");
			ArrayList<Trndocumenttype> docType = (ArrayList<Trndocumenttype>) adminService.getDocType();
			ArrayList<Mstsubcategory> subCat = (ArrayList<Mstsubcategory>) adminService.getSubCategories();
			ArrayList<Mstcategory> cat = (ArrayList<Mstcategory>) adminService.getCategories();

			mav.addObject("docType", docType);
			mav.addObject("subCat", subCat);
			mav.addObject("cat", cat);

			ArrayList<Trndocumentupload> docList = (ArrayList<Trndocumentupload>) adminService.getUploadDetails();
			mav.addObject("docList", docList);

			return mav;
		}
	}

	@RequestMapping(value = "ad_document-upload.web", method = RequestMethod.POST)
	public ModelAndView adDocUploadData(HttpServletRequest req, @RequestParam CommonsMultipartFile[] blUploadFile) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			Trndocumentupload docUp = new Trndocumentupload();
			ModelAndView mav = new ModelAndView("redirect:/ad_document-upload.web");
			ArrayList<Trndocumenttype> docType1 = (ArrayList<Trndocumenttype>) adminService.getDocType();
			ArrayList<Mstsubcategory> subCat1 = (ArrayList<Mstsubcategory>) adminService.getSubCategories();
			ArrayList<Mstcategory> cat1 = (ArrayList<Mstcategory>) adminService.getCategories();

			String desc = req.getParameter("txtDescription");

			docUp.setTxtDescription(desc);

			int type = Integer.parseInt(req.getParameter("trndocumenttype"));
			int sc = Integer.parseInt(req.getParameter("mstsubcategory"));
			int c = Integer.parseInt(req.getParameter("mstcategory"));

			Trndocumenttype docType = new Trndocumenttype();
			Mstsubcategory subCat = new Mstsubcategory();
			Mstcategory cat = new Mstcategory();
			docType.setIntDocumentTypeId(type);
			subCat.setIntSubCategoryId(sc);
			cat.setIntCategoryId1(c);

			docUp.setTrndocumenttype(docType);
			docUp.setMstsubcategory(subCat);
			docUp.setMstcategory(cat);

			if (blUploadFile != null && blUploadFile.length > 0) {
				for (CommonsMultipartFile aFile : blUploadFile) {
					docUp.setBlUploadFile(aFile.getBytes());
					docUp.setTxtfileName(aFile.getOriginalFilename());
					docUp.setTxtcontentType(aFile.getContentType());
					adminService.addDocUploadDetails(docUp);
				}
			}
			ArrayList<Trndocumentupload> docList = (ArrayList<Trndocumentupload>) adminService.getUploadDetails();
			mav.addObject("docList", docList);
			mav.addObject("docType", docType1);
			mav.addObject("subCat", subCat1);
			mav.addObject("cat", cat1);

			return mav;
		}
	}

	@RequestMapping(value = "downloadUploadDoc/{id}.web")
	public void DownloadDoc(HttpServletResponse res, @PathVariable Integer id) throws IOException {
		Trndocumentupload file = adminService.uploadDocObj(id);
		res.setContentLength(file.getBlUploadFile().length);
		res.setContentType(file.getTxtcontentType());
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getTxtfileName() + "\"");
		FileCopyUtils.copy(file.getBlUploadFile(), out);
		out.flush();
		out.close();
		return;
	}

	// ---------------------------doc upload end--------------------------

	/* fee management start */
	@RequestMapping(value = "fin_fee management.web")
	public ModelAndView finFeeManagement() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("fin_fee management");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			m.addObject("classes", classes);
			m.addObject("sections", sections);
			return m;
		}
	}

	@RequestMapping(value = "fin_fee management.web", method = RequestMethod.POST)
	public ModelAndView finFeeManagementSearch(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("fin_fee management");
			int classId = Integer.parseInt(req.getParameter("ClassID"));
			int SectionId = Integer.parseInt(req.getParameter("SectionId"));
			int StudentId = Integer.parseInt(req.getParameter("StudentId"));

			return m;
		}
	}

	/* fee management end */

	// --------invites start

	@RequestMapping(value = "ad_invites.web", method = RequestMethod.GET)
	public ModelAndView adInvites() {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_invites");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Tblrolename> roles = (ArrayList<Tblrolename>) adminService.getRoles();

			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("roles", roles);

			ArrayList<Tblinvites> invDetails = (ArrayList<Tblinvites>) adminService.getInviteDetails();
			Collections.reverse(invDetails);
			m.addObject("invDetails", invDetails);

			m.addObject("roleId", 1);

			return m;
		}
	}

	@RequestMapping(value = "ad_invites.web", method = RequestMethod.POST)
	public ModelAndView adInvitesData(HttpServletRequest req, @RequestParam CommonsMultipartFile[] blUploadFile,
			RedirectAttributes redAtt) throws IllegalStateException, IOException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			String saveDirectory = "/invites";
			ModelAndView m = new ModelAndView();
			Tblinvites inv = new Tblinvites();
			Tblrolename tr = new Tblrolename();
			Mststudclass mc = new Mststudclass();
			Mststudclasssection ms = new Mststudclasssection();

			String title = req.getParameter("txtInvitationTitle");
			String body = req.getParameter("txtBody");
			inv.setTxtInvitationTitle(title);
			inv.setTxtBody(body);
			Date dt = new Date();
			inv.setDtDate(dt);

			int r = Integer.parseInt(req.getParameter("tblrolename"));
			tr.setIntRoleId(r);
			inv.setTblrolename(tr);

			String InvClass = req.getParameter("mststudclass");
			String InvSection = req.getParameter("mststudclasssection");

			int classIdAll = adminService.getClassId("All");
			int sectionIdAll = adminService.getSectionId("All");
			m.addObject("classIdAll", classIdAll);
			m.addObject("sectionIdAll", sectionIdAll);

			if (r == 1) {
				if (InvClass.equals("") && InvSection.equals("")) {
					if (blUploadFile != null && blUploadFile.length > 0) {
						Mststudclass classObj = new Mststudclass();
						classObj.setIntClassId(classIdAll);
						Mststudclasssection secObj = new Mststudclasssection();
						secObj.setIntSectionId(sectionIdAll);
						inv.setMststudclass(classObj);
						inv.setMststudclasssection(secObj);
						for (CommonsMultipartFile aFile : blUploadFile) {
							String oName = aFile.getOriginalFilename();
							String fileWithPath = null;
							if (!oName.equals("")) {
								fileWithPath = saveDirectory + aFile.getOriginalFilename();
								aFile.transferTo(new File(fileWithPath));
							}
							inv.setTxtfileName(aFile.getOriginalFilename());
							inv.setTxtcontentType(aFile.getContentType());
							inv.setTxtPath(fileWithPath);
						}
					}
				} else if (InvSection.equals("")) {
					mc.setIntClassId(Integer.parseInt(InvClass));
					inv.setMststudclass(mc);
					if (blUploadFile != null && blUploadFile.length > 0) {
						Mststudclasssection secObj = new Mststudclasssection();
						secObj.setIntSectionId(sectionIdAll);
						inv.setMststudclasssection(secObj);
						for (CommonsMultipartFile aFile : blUploadFile) {
							String oName = aFile.getOriginalFilename();
							String fileWithPath = null;
							if (!oName.equals("")) {
								fileWithPath = saveDirectory + aFile.getOriginalFilename();
								aFile.transferTo(new File(fileWithPath));
							}
							inv.setTxtfileName(aFile.getOriginalFilename());
							inv.setTxtcontentType(aFile.getContentType());
							inv.setTxtPath(fileWithPath);
						}
					}
				} else {
					mc.setIntClassId(Integer.parseInt(InvClass));
					inv.setMststudclass(mc);
					ms.setIntSectionId(Integer.parseInt(InvSection));
					inv.setMststudclasssection(ms);

					if (blUploadFile != null && blUploadFile.length > 0) {
						for (CommonsMultipartFile aFile : blUploadFile) {
							String oName = aFile.getOriginalFilename();
							String fileWithPath = null;
							if (!oName.equals("")) {
								fileWithPath = saveDirectory + aFile.getOriginalFilename();
								aFile.transferTo(new File(fileWithPath));
							}
							inv.setTxtfileName(aFile.getOriginalFilename());
							inv.setTxtcontentType(aFile.getContentType());
							inv.setTxtPath(fileWithPath);
						}
					}
				}
			} else {
				if (blUploadFile != null && blUploadFile.length > 0) {
					for (CommonsMultipartFile aFile : blUploadFile) {
						String oName = aFile.getOriginalFilename();
						String fileWithPath = null;
						if (!oName.equals("")) {
							fileWithPath = saveDirectory + aFile.getOriginalFilename();
							aFile.transferTo(new File(fileWithPath));
						}
						inv.setTxtfileName(aFile.getOriginalFilename());
						inv.setTxtcontentType(aFile.getContentType());
						inv.setTxtPath(fileWithPath);
					}
				}
			}
			adminService.addInvitesUpload(inv);
			m.addObject("roleId", 1);
			redAtt.addFlashAttribute("sucmsg", " Invitation sent successfully");
			return new ModelAndView("redirect:/ad_invites.web");
		}
	}

	@RequestMapping(value = "downloadInvite/{id}.web")
	public void DownloadInv(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer id)
			throws IOException {
		Tblinvites invObj = adminService.invObj(id);
		String dataDirectory = req.getServletContext().getRealPath("/assets/invites/");
		Path file = Paths.get(dataDirectory, invObj.getTxtfileName());
		if (Files.exists(file)) {
			res.setContentType(invObj.getTxtcontentType());
			res.addHeader("Content-Disposition", "attachment; filename=" + invObj.getTxtfileName());
			try {
				Files.copy(file, res.getOutputStream());
				res.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "deleteInvites/{id}.web", method = RequestMethod.GET)
	public String delInvites(@PathVariable("id") Integer id, RedirectAttributes re) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			adminService.delInvites(id);
			re.addFlashAttribute("sucmsg", " Success!!! Entry deleted");
			return "redirect:/ad_invites.web";
		}
	}

	// ------- invites end

	// ------- teacher Invites start

	@RequestMapping(value = "ad_teach_invites.web", method = RequestMethod.GET)
	public ModelAndView adteacherInvites() {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ArrayList<Mstregistration> regTeachList = new ArrayList<>();
			ArrayList<Mstregistration> teachListGet = new ArrayList<>();
			ModelAndView m = new ModelAndView("ad_teach_invites");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstteacherinvite> teachInvList = (ArrayList<Mstteacherinvite>) adminService.getTeachInvites();
			Collections.reverse(teachInvList);
			Iterator<Mstteacherinvite> itr = teachInvList.iterator();
			while (itr.hasNext()) {
				Mstteacherinvite mstteacherinvite = (Mstteacherinvite) itr.next();
				int intTeacherId = mstteacherinvite.getIntTeacherId();
				regTeachList.add(adminService.getRegObjById(intTeacherId));
			}

			ArrayList<Mstteacherinvite> teachInvListGet = (ArrayList<Mstteacherinvite>) adminService
					.getAllTeachInvites(1);
			Collections.reverse(teachInvListGet);
			Iterator<Mstteacherinvite> itr2 = teachInvListGet.iterator();
			while (itr2.hasNext()) {
				Mstteacherinvite mstteacherinvite = (Mstteacherinvite) itr2.next();
				int intTeacherId = mstteacherinvite.getIntTeacherId();
				teachListGet.add(adminService.getRegObjById(intTeacherId));
			}

			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("teachInvList", teachInvList);
			m.addObject("regTeachList", regTeachList);
			m.addObject("teachInvListGet", teachInvListGet);
			m.addObject("teachListGet", teachListGet);
			return m;
		}
	}

	@RequestMapping(value = "ad_teach_invites_check.web", method = RequestMethod.POST)
	public ModelAndView adteacherInvitesPost(@RequestParam Map<String, String> params, RedirectAttributes redAtt)
			throws ParseException {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ArrayList<Mstregistration> teachList = new ArrayList<>();
			String from = params.get("fromDate");
			String to = params.get("toDate");
			String statusStr = params.get("status");
			int status = Integer.parseInt(statusStr);

			String statusForScndtab = params.get("secondTab");
			redAtt.addFlashAttribute("statusForScndtab", statusForScndtab);
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fromDate = dateFormat1.parse(from);
			java.util.Date toDate = dateFormat1.parse(to);

			ArrayList<Mstteacherinvite> teachInvListPost = (ArrayList<Mstteacherinvite>) adminService
					.getAllTeachInvites(fromDate, toDate, status);
			Collections.reverse(teachInvListPost);
			Iterator<Mstteacherinvite> itr = teachInvListPost.iterator();
			while (itr.hasNext()) {
				Mstteacherinvite mstteacherinvite = (Mstteacherinvite) itr.next();
				int intTeacherId = mstteacherinvite.getIntTeacherId();
				teachList.add(adminService.getRegObjById(intTeacherId));
			}

			redAtt.addFlashAttribute("teachInvListPost", teachInvListPost);
			redAtt.addFlashAttribute("teachList", teachList);
			int size = teachInvListPost.size();
			if (size > 0) {
				if (size == 1) {
					redAtt.addFlashAttribute("msg11", " " + "'" + size + "'" + " record found!!!!!!!!");
				} else {
					redAtt.addFlashAttribute("msg11", " " + "'" + size + "'" + " records found!!!!!!!!");
				}
			}
			redAtt.addFlashAttribute("msg22", "msg");
			return new ModelAndView("redirect:/ad_teach_invites.web");
		}
	}

	@RequestMapping(value = "Ajax_TeacherLeaveUpdate.web", method = RequestMethod.GET)
	public String updateTeacherLeave(HttpServletRequest req) {
		String msg = req.getParameter("val1");
		int id = Integer.parseInt(req.getParameter("val2"));
		adminService.updateTeacherLeave(msg, id);
		return "redirect:/ad_teach_invites.web";
	}
	
	@RequestMapping(value="ApproveOrReject/{id}/{status}.web")
	public String approveOrrejectInvite(@PathVariable Integer id,@PathVariable Integer status, RedirectAttributes re)
	{
		/*int id=Integer.parseInt(req.getParameter("val1"));
		int status=Integer.parseInt(req.getParameter("val2"));*/
		Date dt=new Date();
		adminService.appproveOrRejectTeacherInvite(id, status, new java.sql.Date(dt.getTime()) );
		
		if(status==2){
		re.addFlashAttribute("msg1", "Invite aproved");
		}
		else{
			re.addFlashAttribute("msg1", "Invite rejected");
		}
		return "redirect:/ad_teach_invites.web";
	}

	// ------- teacher Invites end

	// -------------schools connect start

	@RequestMapping(value = "ad_school's_connect.web")
	public ModelAndView adSchoolConnect() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_school's_connect");
			ArrayList<Mstschool> mstschools = (ArrayList<Mstschool>) adminService.getUsers();
			m.addObject("mstschools", mstschools);
			return m;
		}
	}

	@RequestMapping(value = "ad_school's_connect.web", method = RequestMethod.POST)
	public ModelAndView adSchoolConnect(HttpServletRequest req, @RequestParam CommonsMultipartFile[] blAttachment,
			RedirectAttributes re) throws MessagingException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			Mstschoolconnect sc = new Mstschoolconnect();
			int id = Integer.parseInt(req.getParameter("mstschool"));
			Mstschool msc = new Mstschool();
			msc.setIntSchoolId(id);
			sc.setMstschool(msc);
			String title = req.getParameter("txtTitle").trim();
			String body = req.getParameter("txtBody").trim();
			String mailid = req.getParameter("txtMailId");

			sc.setTxtTitle(title);
			sc.setTxtMailId(mailid);
			sc.setTxtBody(body);

			if (blAttachment != null && blAttachment.length > 0) {
				for (CommonsMultipartFile aFile : blAttachment) {
					sc.setBlAttachment(aFile.getBytes());
					adminService.addDocUploadDetails(sc);
				}
			}

			// adminService.addDocUploadDetails(sc);

			System.out.println("mail sending process start");

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(mailid);
			mimeMessageHelper.setSubject(title);
			mimeMessageHelper.setText(body);
			ByteArrayDataSource byteArrayDataSource = null;
			if (blAttachment != null && blAttachment.length > 0) {
				for (CommonsMultipartFile aFile : blAttachment) {

					byteArrayDataSource = new ByteArrayDataSource(aFile.getBytes(), aFile.getContentType());
					mimeMessageHelper.addAttachment(aFile.getOriginalFilename(), byteArrayDataSource);
				}
			}

			System.out.println("Start of mail");
			mailSender.send(mimeMessage);
			System.out.println("End of mail");

			System.out.println("mail sending process end");

			re.addFlashAttribute("msg", "Mail sent successfully");

			return new ModelAndView("redirect:/ad_school's_connect.web");
		}

	}

	@RequestMapping(value = "AddNewSchool.web", method = RequestMethod.GET)
	public ModelAndView addSchool(@ModelAttribute("mschool") Mstschool mschool, RedirectAttributes re) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			adminService.addSchool(mschool);
			re.addFlashAttribute("smsg", "School added successfully");
			/* re.addFlashAttribute("msg", "School added successfully"); */
			return new ModelAndView("redirect:/ad_school's_connect.web");
		}
	}

	@RequestMapping(value = "Ajax_SchoolsconnectDelete.web", method = RequestMethod.GET)
	public ModelAndView deleteMstSchool(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			System.out.println("inside del");
			ModelAndView m = new ModelAndView("Ajax_Messages");
			int id = Integer.parseInt(req.getParameter("val1"));
			adminService.delSchool(id);
			ArrayList<Mstschool> mstschools = (ArrayList<Mstschool>) adminService.getUsers();
			m.addObject("mstschools", mstschools);
			return m;
		}

	}

	@RequestMapping(value = "Ajax_SchoolsconnectUpdate.web", method = RequestMethod.GET)
	public ModelAndView updateMstSchool(HttpServletRequest req, RedirectAttributes re) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("Ajax_Messages");
			String name = req.getParameter("val1");
			String email = req.getParameter("val2");
			String id = req.getParameter("val3");
			Mstschool mschool = new Mstschool();
			mschool.setTxtSchoolName(name);
			mschool.setTxtEmail(email);
			mschool.setIntSchoolId(Integer.parseInt(id));
			adminService.updSchool(mschool);
			ArrayList<Mstschool> mstschools = (ArrayList<Mstschool>) adminService.getUsers();
			m.addObject("mstschools", mstschools);
			return m;
		}
	}

	// -------------schools connect end

	// --------school and branch start
	@RequestMapping(value = "ad_school & branch.web")
	public ModelAndView adSchoolAndBranch() {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_school & branch");
			ArrayList<Tblschoolandbranchdetail> sandbal = (ArrayList<Tblschoolandbranchdetail>) adminService
					.getAllDetails();
			m.addObject("sandbal", sandbal);

			return m;
		}
	}

	@RequestMapping(value = "ad_school & branch.web", method = RequestMethod.POST)
	public ModelAndView listAll(@ModelAttribute("sandbdetails") Tblschoolandbranchdetail sandbdetails,
			RedirectAttributes re) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView();
			adminService.addSandBDetails(sandbdetails);
			re.addFlashAttribute("sucmsg", "success!!!");
			re.addFlashAttribute("sucmsg1", "    school details has been added");
			return new ModelAndView("redirect:/ad_school & branch.web");
		}
	}

	@RequestMapping(value = "Ajax_School_BranchDelete.web")
	public ModelAndView delete(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			int id = Integer.parseInt(req.getParameter("val1"));
			adminService.deleteSchool(id);
			return new ModelAndView("redirect:/ad_school & branch.web");
		}

	}

	@RequestMapping(value = "Ajax_School_BranchUpdate.web")
	public ModelAndView updateSchool(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("Ajax_Messages");

			Tblschoolandbranchdetail tbl = new Tblschoolandbranchdetail();
			int id = Integer.parseInt(req.getParameter("val6"));
			tbl.setTxtSchoolName(req.getParameter("val1"));
			tbl.setTxtResponsiblePerson(req.getParameter("val2"));
			tbl.setTxtSchoolAddress(req.getParameter("val3"));
			tbl.setTxtEmail(req.getParameter("val4"));
			tbl.setTxtWebsite(req.getParameter("val5"));
			adminService.updateBranch(tbl, id);
			return m;
		}
	}

	// -------school and branch end

	// start Syllabus

	@RequestMapping(value = "ad_syllabus.web")
	public ModelAndView adSyllbus() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_syllabus");
			ArrayList<Mststudclass> mststudclasses = (ArrayList<Mststudclass>) adminService.getSyllabus();
			ArrayList<Mstsyllabus> syllabusList = adminService.getSyllabusRecords();
			ArrayList<Mststudclass> classList = (ArrayList<Mststudclass>) adminService.getClasses();

			m.addObject("mststudclasses", mststudclasses);
			m.addObject("syllabusList", syllabusList);
			m.addObject("classList", classList);
			return m;
		}
	}

	@RequestMapping(value = "ad_syllabus.web", method = RequestMethod.POST)
	public ModelAndView adsyllabus(HttpServletRequest req, RedirectAttributes redAtt,
			@RequestParam CommonsMultipartFile[] blUploadFile) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			int m, n = 0;
			int i = Integer.parseInt(req.getParameter("Mststudclass"));
			Mststudclass mstClassObj = new Mststudclass();
			mstClassObj.setIntClassId(i);
			String[] subject = req.getParameterValues("subs");

			for (n = 0; n < ((blUploadFile.length) - 1); n++) {
				Mstsyllabus syllObj = new Mstsyllabus();
				CommonsMultipartFile aFile = blUploadFile[n];
				syllObj.setBlAttachment(aFile.getBytes());
				syllObj.setMststudclass(mstClassObj);
				syllObj.setTxtFileName(aFile.getOriginalFilename());
				syllObj.setTxtContentType(aFile.getContentType());

				for (m = 0; m < subject.length; m++) {
					syllObj.setTxtSubjectName(subject[n]);
					break;
				}
				adminService.addsyllabus(syllObj);
			}

			redAtt.addFlashAttribute("sucmsg", " Syllabus for");
			redAtt.addFlashAttribute("sucmsg1", " 'Class " + i + "' ");

			return new ModelAndView("redirect:/ad_syllabus.web");

		}
	}

	@RequestMapping(value = "downloadSyllabus/{id}.web")
	public void downloadyllabus(HttpServletResponse res, @PathVariable Integer id) throws IOException {
		Mstsyllabus syllabusObj = adminService.getSyllabusRow(id);
		res.setContentLength(syllabusObj.getBlAttachment().length);
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "attachment; filename=\"" + syllabusObj.getTxtFileName() + "\"");
		FileCopyUtils.copy(syllabusObj.getBlAttachment(), out);
		out.flush();
		out.close();
		return;
	}

	@RequestMapping(value = "deleteSyllabus/{id}.web", method = RequestMethod.GET)
	public String deleteSyllabus(@PathVariable("id") Integer id, RedirectAttributes re) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			adminService.delSyllabus(id);
			re.addFlashAttribute("delMsg", " Success!!!  entry deleted");
			return "redirect:/ad_syllabus.web";
		}
	}

	// end Syllabus

	// timetable start
	@RequestMapping(value = "ad_timetable.web")
	public ModelAndView adTimeTable2(Model model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_timetable");
			ArrayList<Mstcategory> cat = (ArrayList<Mstcategory>) adminService.getCategories();
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstregistration> teachList = (ArrayList<Mstregistration>) adminService.getTeacherRecords();

			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("cat", cat);
			m.addObject("teachList", teachList);
			return m;
		}
	}

	@RequestMapping(value = "ad_timetable.web", method = RequestMethod.POST)
	public ModelAndView adTimeTable2(HttpServletRequest req, RedirectAttributes redAtt,
			@RequestParam CommonsMultipartFile[] txtFile) throws IllegalStateException, IOException, ParseException {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			byte[] b = null;
			int uploadFor = Integer.parseInt(req.getParameter("mstcategory"));
			if (uploadFor == 1) {

				int classInt = Integer.parseInt(req.getParameter("mststudclass"));
				int section = Integer.parseInt(req.getParameter("mststudclasssection"));
				for (CommonsMultipartFile aFile : txtFile) {
					b = aFile.getBytes();
				}
				List<?> sheetData = getSheetData(b);
				ArrayList<Mststudenttimetable> aa = (ArrayList<Mststudenttimetable>) studentTimeTableToDB(sheetData);
				if (!aa.isEmpty()) {
					Mststudclass classObj = new Mststudclass();
					classObj.setIntClassId(classInt);
					Mststudclasssection sectionObj = new Mststudclasssection();
					sectionObj.setIntSectionId(section);

					Iterator<Mststudenttimetable> itr = aa.iterator();
					while (itr.hasNext()) {
						Mststudenttimetable tT2 = (Mststudenttimetable) itr.next();
						tT2.setMststudclass(classObj);
						tT2.setMststudclasssection(sectionObj);

						int dayId = tT2.getTbldaysofweek().getIntDaysId();
						int timingId = tT2.getMsttimings().getIntMsttimingsId();
						List<Mststudenttimetable> checkedTTList = adminService.checkForTTRecord(classInt, section,
								dayId, timingId);
						if (!checkedTTList.isEmpty()) {
							Iterator<Mststudenttimetable> itr222 = checkedTTList.iterator();
							while (itr222.hasNext()) {
								Mststudenttimetable mststudenttimetable = (Mststudenttimetable) itr222.next();
								adminService.updateStuTT(mststudenttimetable.getIntStudentTimeTableId());
							}
						}
						adminService.addTimeTable2(tT2);
					}

					redAtt.addFlashAttribute("success", "Student TimeTable Set Successfully!!");
				} else {
					redAtt.addFlashAttribute("failure", "Please Select proper Excel File!!!");
				}
			} else {
				int teachId = Integer.parseInt(req.getParameter("teacherId"));
				Mstregistration teachRegObj = adminService.getRegObjById(teachId);
				String fName = teachRegObj.getTxtFirstName();
				for (CommonsMultipartFile aFile : txtFile) {
					b = aFile.getBytes();
				}
				List<?> sheetData = getSheetData(b);
				ArrayList<Mstteachertimetable> aa = (ArrayList<Mstteachertimetable>) teacherTimeTableToDB(sheetData);
				if (!aa.isEmpty()) {
					String curDtStr = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date curDt = sdf.parse(curDtStr);
					Iterator<Mstteachertimetable> itr = aa.iterator();
					while (itr.hasNext()) {
						Mstteachertimetable tT2 = (Mstteachertimetable) itr.next();
						tT2.setMstregistration(teachRegObj);
						tT2.setDtAssignDate(curDt);

						int dayId = tT2.getTbldaysofweek().getIntDaysId();
						int timingId = tT2.getMsttimings().getIntMsttimingsId();
						ArrayList<Mstteachertimetable> checkedTTList = (ArrayList<Mstteachertimetable>) adminService
								.checkForTeachTTRecord(teachId, dayId, timingId);

						if (!checkedTTList.isEmpty()) {
							Iterator<Mstteachertimetable> itr222 = checkedTTList.iterator();
							while (itr222.hasNext()) {
								Mstteachertimetable mstteachertimetable = (Mstteachertimetable) itr222.next();
								adminService.updateTeachTT(mstteachertimetable.getIntTeacherTimeTableId());
							}
						}

						adminService.addTimeTable2(tT2);
					}
					redAtt.addFlashAttribute("success", "'" + fName + "'");
					redAtt.addFlashAttribute("success2", " TimeTable Set Successfully!!");
				} else {
					redAtt.addFlashAttribute("failure", "Please Select proper Excel File!!!");
				}
			}
			return new ModelAndView("redirect:/ad_timetable.web");
		}
	}

	private List<List<XSSFCell>> getSheetData(byte[] b) throws IOException {
		List<List<XSSFCell>> sheetData = new ArrayList<List<XSSFCell>>();

		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(b);
			XSSFWorkbook workbook = new XSSFWorkbook(bais);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<?> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator<?> cells = row.cellIterator();

				List<XSSFCell> data = new ArrayList<XSSFCell>();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					data.add(cell);
				}
				sheetData.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bais != null) {
				bais.close();
			}
		}
		return sheetData;
	}

	private List<Mststudenttimetable> studentTimeTableToDB(List<?> sheetData) {

		ArrayList<Mststudenttimetable> aa = new ArrayList<>();
		List<?> list2 = (List<?>) sheetData.get(0);
		if (list2.size() == 5) {
			for (int i = 1; i < sheetData.size(); i++) {
				List<?> list = (List<?>) sheetData.get(i);
				XSSFCell employeeid = (XSSFCell) list.get(0);
				XSSFCell subject = (XSSFCell) list.get(1);
				XSSFCell day = (XSSFCell) list.get(2);
				XSSFCell timing = (XSSFCell) list.get(3);
				XSSFCell status = (XSSFCell) list.get(4);

				Mstsubject subObj = new Mstsubject();
				String subjectStr = subject.toString();
				if (subjectStr == null) {
				} else {
					subObj = adminService.getsubjectObj(subjectStr);
				}

				Tbldaysofweek daysObj = new Tbldaysofweek();
				String dayStr = day.toString();
				daysObj = adminService.getDayObj(dayStr);

				Msttimings timeObj = new Msttimings();
				String timeStr = timing.toString();
				timeObj = adminService.getTimeObj(timeStr);

				String statusStr = status.toString();
				int statusInt = 0;
				if (statusStr.equals("Yes")) {
					statusInt = 1;
				}

				Mststudenttimetable aaObj = new Mststudenttimetable();
				aaObj.setMstsubject(subObj);
				aaObj.setTbldaysofweek(daysObj);
				aaObj.setMsttimings(timeObj);
				aaObj.setIntStatus(statusInt);
				aaObj.setIntStudentStatus(1);

				aa.add(aaObj);
			}
		}

		return aa;
	}

	private List<Mstteachertimetable> teacherTimeTableToDB(List<?> sheetData) {

		ArrayList<Mstteachertimetable> aa = new ArrayList<>();
		List<?> list2 = (List<?>) sheetData.get(0);

		if (list2.size() == 7) {
			for (int i = 1; i < sheetData.size(); i++) {
				List<?> list = (List<?>) sheetData.get(i);
				XSSFCell id = (XSSFCell) list.get(0);
				XSSFCell classId = (XSSFCell) list.get(1);
				XSSFCell sectionId = (XSSFCell) list.get(2);
				XSSFCell subject = (XSSFCell) list.get(3);
				XSSFCell day = (XSSFCell) list.get(4);
				XSSFCell timing = (XSSFCell) list.get(5);
				XSSFCell status = (XSSFCell) list.get(6);

				Mststudclass classObj = new Mststudclass();
				String classStr = classId.toString();
				if (classStr == null) {
				} else {
					classObj = adminService.getClassObj(classStr);
				}

				Mststudclasssection sectionObj = new Mststudclasssection();
				String sectionStr = sectionId.toString();
				if (sectionStr == null) {
				} else {
					sectionObj = adminService.getSectionObj(sectionStr);
				}

				Mstsubject subObj = new Mstsubject();
				String subjectStr = subject.toString();
				if (subjectStr == null) {
				} else {
					subObj = adminService.getsubjectObj(subjectStr);
				}

				Tbldaysofweek daysObj = new Tbldaysofweek();
				String dayStr = day.toString();
				daysObj = adminService.getDayObj(dayStr);

				Msttimings timeObj = new Msttimings();
				String timeStr = timing.toString();
				timeObj = adminService.getTimeObj(timeStr);

				String statusStr = status.toString();
				int statusInt = 0;
				if (statusStr.equals("Yes")) {
					statusInt = 1;
				}

				Mstteachertimetable aaObj = new Mstteachertimetable();
				aaObj.setMststudclass(classObj);
				aaObj.setMststudclasssection(sectionObj);
				aaObj.setMstsubject(subObj);
				aaObj.setTbldaysofweek(daysObj);
				aaObj.setMsttimings(timeObj);
				aaObj.setIntStatus(statusInt);
				aaObj.setIntTeacherStatus(1);

				aa.add(aaObj);
			}
		}
		return aa;
	}

	@RequestMapping(value = "ad_getTimetable.web")
	public ModelAndView aGetTimetable(Model model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_timetable");

			ArrayList<Mstcategory> cat = (ArrayList<Mstcategory>) adminService.getCategories();
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstsubject> subject = (ArrayList<Mstsubject>) adminService.getSubject();
			ArrayList<Tbldaysofweek> days = (ArrayList<Tbldaysofweek>) adminService.getDaysOfweek();
			ArrayList<Msttimings> timings = (ArrayList<Msttimings>) adminService.getTimings();
			ArrayList<Mstregistration> teachList = (ArrayList<Mstregistration>) adminService.getTeacherRecords();

			m.addObject("cat", cat);
			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("subject", subject);
			m.addObject("days", days);
			m.addObject("timings", timings);
			m.addObject("teachList", teachList);
			return m;
		}
	}

	TimeTableTemp temp = new TimeTableTemp();

	@RequestMapping(value = "ad_getTimetable.web", method = RequestMethod.POST)
	public ModelAndView getTimetable(HttpServletRequest req, RedirectAttributes redAtt) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			int categoryInt = Integer.parseInt(req.getParameter("mstcategory2"));
			ArrayList<String> tempAl = new ArrayList<>();
			if (categoryInt == 2) {
				int teachId = Integer.parseInt(req.getParameter("teacherIdForInbox2"));
				tempAl.add(String.valueOf(teachId));
				temp.setTemp(tempAl);

				ArrayList<Mstteachertimetable> teachTTList = (ArrayList<Mstteachertimetable>) adminService
						.getTeachTimeTable(teachId);
				redAtt.addFlashAttribute("teachTTList", teachTTList);
				redAtt.addFlashAttribute("teachStatus", "teachStatus");
				if (!teachTTList.isEmpty()) {
					Mstregistration teachRegObj = adminService.getRegObjById(teachId);
					String teachFullName = teachRegObj.getTxtFirstName() + " " + teachRegObj.getTxtLastName();
					redAtt.addFlashAttribute("success", teachFullName);
					redAtt.addFlashAttribute("success2", "'s Timetable displayed Successfully!!!");
				}
			} else {
				int classIdInt = Integer.parseInt(req.getParameter("mststudclass2"));
				int sectionIdInt = Integer.parseInt(req.getParameter("mststudclasssection2"));

				tempAl.add(String.valueOf(classIdInt));
				tempAl.add(String.valueOf(sectionIdInt));
				temp.setTemp(tempAl);

				ArrayList<Mststudenttimetable> stuTTList = (ArrayList<Mststudenttimetable>) adminService
						.getStuTimeTable(classIdInt, sectionIdInt);
				redAtt.addFlashAttribute("stuTTList", stuTTList);
				redAtt.addFlashAttribute("stuStatus", "stuStatus");
				if (!stuTTList.isEmpty()) {
					String sectionStr = adminService.getSectionName(String.valueOf(sectionIdInt));
					redAtt.addFlashAttribute("success", " 'Class " + classIdInt + "' 'Section" + sectionStr + "'");
					redAtt.addFlashAttribute("success2", " Timetable displayed Successfully!!!");
				}
			}
			String secondTab = req.getParameter("secondTab");
			redAtt.addFlashAttribute("secondTab", secondTab);
			return new ModelAndView("redirect:/ad_getTimetable.web");
		}
	}

	@RequestMapping(value = "updateStudentTiming/{id}.web")
	public ModelAndView updateStudentTT(@ModelAttribute Mststudenttimetable stuTTObj, @PathVariable Integer id,
			RedirectAttributes redAtt, HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			Mststudclass classObj = new Mststudclass();
			int classIdInt = Integer.parseInt(temp.getTemp().get(0));
			classObj.setIntClassId(classIdInt);

			Mststudclasssection sectionObj = new Mststudclasssection();
			int sectionIdInt = Integer.parseInt(temp.getTemp().get(1));
			sectionObj.setIntSectionId(sectionIdInt);

			Mstsubject subObj = new Mstsubject();
			subObj.setIntSubjectId(Integer.parseInt(req.getParameter("subject")));

			Tbldaysofweek dayObj = new Tbldaysofweek();
			dayObj.setIntDaysId(Integer.parseInt(req.getParameter("day")));

			Msttimings timeObj = new Msttimings();
			timeObj.setIntMsttimingsId(Integer.parseInt(req.getParameter("timing")));

			stuTTObj.setMststudclass(classObj);
			stuTTObj.setMststudclasssection(sectionObj);
			stuTTObj.setIntStatus(1);
			stuTTObj.setIntStudentStatus(1);
			stuTTObj.setMstsubject(subObj);
			stuTTObj.setTbldaysofweek(dayObj);
			stuTTObj.setMsttimings(timeObj);

			adminService.updateStuTT(id);
			adminService.addNewRowStuTT(stuTTObj);

			ArrayList<Mststudenttimetable> stuTTList = (ArrayList<Mststudenttimetable>) adminService
					.getStuTimeTable(classIdInt, sectionIdInt);
			redAtt.addFlashAttribute("stuTTList", stuTTList);

			String secondTab = req.getParameter("secondTab");
			redAtt.addFlashAttribute("secondTab", secondTab);
			redAtt.addFlashAttribute("stuStatus", "stuStatus");

			String sectionName = adminService.getSectionName(String.valueOf(sectionIdInt));
			redAtt.addFlashAttribute("success3", "'Class " + classIdInt + "'  'Section " + sectionName + "'");
			return new ModelAndView("redirect:/ad_getTimetable.web");
		}
	}

	@RequestMapping(value = "updateTeacherTiming/{id}.web")
	public ModelAndView updateTeacherTT(@PathVariable Integer id, RedirectAttributes redAtt, HttpServletRequest req)
			throws ParseException {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			Mstregistration regObj = new Mstregistration();
			int teachId = Integer.parseInt(temp.getTemp().get(0));
			regObj.setIntRegistrationId(teachId);
			Mstteachertimetable teachTTObj = new Mstteachertimetable();
			teachTTObj.setMstregistration(regObj);

			System.out.println("***********************###########################**********************");
			System.out.println(teachId);

			Mststudclass classObj = new Mststudclass();
			classObj.setIntClassId(Integer.parseInt(req.getParameter("class")));

			Mststudclasssection secObj = new Mststudclasssection();
			secObj.setIntSectionId(Integer.parseInt(req.getParameter("section")));

			Mstsubject subObj = new Mstsubject();
			subObj.setIntSubjectId(Integer.parseInt(req.getParameter("subject")));

			Tbldaysofweek dayObj = new Tbldaysofweek();
			dayObj.setIntDaysId(Integer.parseInt(req.getParameter("day")));

			Msttimings timeObj = new Msttimings();
			timeObj.setIntMsttimingsId(Integer.parseInt(req.getParameter("timing")));

			String curDtStr = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date curDt = sdf.parse(curDtStr);

			teachTTObj.setMststudclass(classObj);
			teachTTObj.setMststudclasssection(secObj);
			teachTTObj.setIntStatus(1);
			teachTTObj.setIntTeacherStatus(1);
			teachTTObj.setMstsubject(subObj);
			teachTTObj.setTbldaysofweek(dayObj);
			teachTTObj.setMsttimings(timeObj);
			teachTTObj.setDtAssignDate(curDt);

			adminService.updateTeachTT(id);
			adminService.addNewRowTeachTT(teachTTObj);

			redAtt.addFlashAttribute("updateMsg", adminService.getRegObjById(teachId).getTxtFirstName());

			ArrayList<Mstteachertimetable> teachTTList = (ArrayList<Mstteachertimetable>) adminService
					.getTeachTimeTable(teachId);
			redAtt.addFlashAttribute("teachTTList", teachTTList);

			String secondTab = req.getParameter("secondTab");
			redAtt.addFlashAttribute("secondTab", secondTab);
			redAtt.addFlashAttribute("teachStatus", "teachStatus");

			return new ModelAndView("redirect:/ad_getTimetable.web");
		}
	}

	// end timetable

	@RequestMapping(value = "ad_reg.web")
	public ModelAndView adReg() {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView("ad_reg");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstsemester> sems = (ArrayList<Mstsemester>) adminService.semester();
			ArrayList<Mststream> streams = (ArrayList<Mststream>) adminService.stream();
			ArrayList<Mstmedium> mediums = (ArrayList<Mstmedium>) adminService.medium();
			ArrayList<Mstmothertongue> motherTongues = (ArrayList<Mstmothertongue>) adminService.mothertongue();
			ArrayList<Mstaffiliation> affiliations = (ArrayList<Mstaffiliation>) adminService.affiliation();
			ArrayList<Mstschooltype> schoolType = (ArrayList<Mstschooltype>) adminService.schooltype();

			mav.addObject("classes", classes);
			mav.addObject("sections", sections);
			mav.addObject("sems", sems);
			mav.addObject("streams", streams);
			mav.addObject("mediums", mediums);
			mav.addObject("motherTongues", motherTongues);
			mav.addObject("affiliations", affiliations);
			mav.addObject("schoolType", schoolType);

			ArrayList<Mstgender> gal = (ArrayList<Mstgender>) adminService.gender();
			ArrayList<Mstbloodgroup> bal = (ArrayList<Mstbloodgroup>) adminService.bloodgroup();
			ArrayList<Mstreligion> ral = (ArrayList<Mstreligion>) adminService.religion();
			ArrayList<Mstsocialcategory> sal = (ArrayList<Mstsocialcategory>) adminService.socialcategory();
			ArrayList<Mstdisabilitychild> dal = (ArrayList<Mstdisabilitychild>) adminService.disabilitychild();
			mav.addObject("gal", gal);
			mav.addObject("bal", bal);
			mav.addObject("ral", ral);
			mav.addObject("sal", sal);
			mav.addObject("dal", dal);

			ArrayList<Tblbankname> banks = (ArrayList<Tblbankname>) adminService.bankname();
			mav.addObject("banks", banks);
			// ----------------------------------------------------------------------------------------------------------------
			List<Mstgender> gender = adminService.gender();
			List<Mstreligion> religion = adminService.religion();
			ArrayList<Mstmaritalstatus> marital = (ArrayList<Mstmaritalstatus>) adminService.getMaritalStatus();
			ArrayList<Mstsubject> subject = (ArrayList<Mstsubject>) adminService.getSubject();

			mav.addObject("gender", gender);
			mav.addObject("religion", religion);
			mav.addObject("marital", marital);
			mav.addObject("subject", subject);

			return mav;
		}
	}

	@RequestMapping(value = "user_new reg.web", method = RequestMethod.POST)
	public ModelAndView userNewRegSave(HttpServletRequest req, RedirectAttributes redAtt)
			throws ParseException, MessagingException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Mstregistration reg = new Mstregistration();

			String tcNum = req.getParameter("txtTransferCertificateNo");

			String tcDate = req.getParameter("dtTransferCertificateDate");
			if (tcDate.equals("")) {
			} else {
				java.util.Date tcDt = sdf.parse(tcDate);
				reg.setDtTransferCertificateDate(tcDt);
			}

			String preSchool = req.getParameter("txtPreviousSchoolName");
			String preSchoolAdd = req.getParameter("txtPreviousSchoolAddress");

			String stuFirstName = req.getParameter("txtFirstName");
			String stuMiddleName = req.getParameter("txtMiddleName");
			String stuLastName = req.getParameter("txtLastName");

			String fatherName = req.getParameter("txtFatherName");

			String motherName = req.getParameter("txtMotherName");

			String fOccu = req.getParameter("txtFoccupation");
			String mOccu = req.getParameter("txtMoccupation");

			String fMobile = req.getParameter("txtFmobileNumber");
			String mMobile = req.getParameter("txtMmobileNumber");

			String fEduQua = req.getParameter("txtFeducationalQualification");
			String mEduQua = req.getParameter("txtMeducationalQualification");

			String fOtherNum = req.getParameter("txtFotherNumber");
			String mOtherNum = req.getParameter("txtMotherNumber");

			String fEmail = req.getParameter("txtFemailId");
			String mEmail = req.getParameter("txtMemailId");

			String fAadhar = req.getParameter("txtFaadharNumber");
			String mAadhar = req.getParameter("txtMaadharNumber");

			reg.setTxtTransferCertificateNo(tcNum);
			reg.setTxtPreviousSchoolName(preSchool);
			reg.setTxtPreviousSchoolAddress(preSchoolAdd);
			reg.setTxtFirstName(stuFirstName);
			reg.setTxtMiddleName(stuMiddleName);
			reg.setTxtLastName(stuLastName);
			reg.setTxtFatherName(fatherName);
			reg.setTxtMotherName(motherName);
			reg.setTxtFoccupation(fOccu);
			reg.setTxtMoccupation(mOccu);
			reg.setTxtFmobileNumber(fMobile);
			reg.setTxtMmobileNumber(mMobile);
			reg.setTxtFeducationalQualification(fEduQua);
			reg.setTxtMeducationalQualification(mEduQua);
			reg.setTxtFotherNumber(fOtherNum);
			reg.setTxtMotherNumber(mOtherNum);
			reg.setTxtFemailId(fEmail);
			reg.setTxtMemailId(mEmail);
			reg.setTxtFaadharNumber(fAadhar);
			reg.setTxtMaadharNumber(mAadhar);

			Mstsemester semObj = new Mstsemester();
			Mstaffiliation affObj = new Mstaffiliation();
			Mstschooltype schltypeObj = new Mstschooltype();
			Mststudclass claObj = new Mststudclass();
			Mststudclasssection secObj = new Mststudclasssection();
			Mststream strmObj = new Mststream();
			Mstmedium mediumObj = new Mstmedium();
			Mstmothertongue motherObj = new Mstmothertongue();

			int clas = Integer.parseInt(req.getParameter("mststudclass"));
			int section = Integer.parseInt(req.getParameter("mststudclasssection"));
			String semester = req.getParameter("mstsemester");
			System.out.println(semester);
			if (semester.equals("")) {
			} else {
				int sem = Integer.parseInt(semester);
				semObj.setIntSemesterId(sem);
				reg.setMstsemester(semObj);
			}
			int stream = Integer.parseInt(req.getParameter("mststream"));
			int medium = Integer.parseInt(req.getParameter("mstmedium"));
			int motherTongue = Integer.parseInt(req.getParameter("mstmothertongue"));
			String affStr = req.getParameter("mstaffiliation");

			if (affStr.equals("")) {
			} else {
				int aff = Integer.parseInt(affStr);
				affObj.setIntAffiliationId(aff);
				reg.setMstaffiliation(affObj);
			}
			String schlTypeStr = req.getParameter("mstschooltype");
			if (schlTypeStr.equals("")) {
			} else {
				int schlType = Integer.parseInt(schlTypeStr);
				schltypeObj.setIntSchoolTypeId(schlType);
				reg.setMstschooltype(schltypeObj);
			}

			claObj.setIntClassId(clas);
			secObj.setIntSectionId(section);

			strmObj.setIntStreamId(stream);
			mediumObj.setIntMediumId(medium);
			motherObj.setIntmothertongueId(motherTongue);

			reg.setMststudclass(claObj);
			reg.setMststudclasssection(secObj);

			reg.setMststream(strmObj);
			reg.setMstmedium(mediumObj);
			reg.setMstmothertongue(motherObj);

			// ---------------------------------------

			Mstgender gen = new Mstgender();
			Mstbloodgroup bg = new Mstbloodgroup();
			Mstreligion rel = new Mstreligion();
			Mstsocialcategory soc = new Mstsocialcategory();
			Mstdisabilitychild dis = new Mstdisabilitychild();

			// ----- getting values
			String dob = req.getParameter("dob");
			java.util.Date dt = sdf.parse(dob); // date of birth
			String adate = req.getParameter("Adate");
			if (adate.equals("")) {
			} else {
				java.util.Date admissionDate = sdf.parse(adate); // admission
																	// date
				reg.setDtAdmissionDate(admissionDate);
			}
			int blood = Integer.parseInt(req.getParameter("bloodgroup"));
			int gender = Integer.parseInt(req.getParameter("gender"));
			int religion = Integer.parseInt(req.getParameter("religion"));
			int social = Integer.parseInt(req.getParameter("social"));

			/*
			 * String child=req.getParameter("child"); int dchild=0;
			 * if(!child.equals("")) { dchild=Integer.parseInt(child); }else {
			 * dchild=1; }
			 */

			int childgg = Integer.parseInt(req.getParameter("child"));
			if (childgg != 1) {
				dis.setIntDisabilityChildId(Integer.parseInt(req.getParameter("disChild")));
			} else if (childgg == 1) {
				dis.setIntDisabilityChildId(childgg);
			}

			String cityStr = req.getParameter("city");
			String nation = req.getParameter("nationality");
			String incomeStr = req.getParameter("income");
			if (incomeStr.equals("")) {
			} else {
				Long income = (long) Integer.parseInt(incomeStr);
				reg.setNmParentsAnnualIncome(income);
			}
			String scasteno = req.getParameter("scasteno");
			String scaste = req.getParameter("scaste");
			String perAddress = req.getParameter("perAdd");
			String tempAddress = req.getParameter("tempAdd");
			String local = req.getParameter("local");
			String taluk = req.getParameter("taluk");
			String district = req.getParameter("district");
			String code = req.getParameter("code");
			String steno = req.getParameter("steno");
			String bplcardno = req.getParameter("bplCardNo");

			String acno = req.getParameter("acNum");
			String ifsc = req.getParameter("ifsc");

			// --setting values to db
			gen.setIntGenderId(gender);
			reg.setMstgender(gen); // gender
			bg.setIntBloodGroupId(blood);
			reg.setMstbloodgroup(bg); // blood group
			rel.setIntReligionId(religion);
			reg.setMstreligion(rel); // religion
			soc.setIntSocialCategoryId(social);
			reg.setMstsocialcategory(soc); // social
			reg.setMstdisabilitychild(dis); // disability
			reg.setTxtCity(cityStr); // city
			reg.setTxtNationality(nation);
			reg.setTxtStudentCasteCertificateNo(scasteno);
			reg.setTxtStudentCaste(scaste);
			reg.setTxtPerAddress(perAddress);
			reg.setTxtTempAddress(tempAddress);
			reg.setTxtLocality(local);
			reg.setTxtTaluk(taluk);
			reg.setTxtDistrict(district);
			reg.setTxtPincode(code);
			reg.setTxtStudentEnrollmentNumber(steno);
			reg.setDtDob(dt);
			reg.setTxtBplcardNo(bplcardno);

			// -----------------------

			String yOrNo = req.getParameter("blBelongToBpl");
			if (yOrNo.equals("")) {
			} else {
				Boolean btbpl = Boolean.parseBoolean(yOrNo);
				reg.setBlBelongToBpl(btbpl);
			}

			// for role
			Tblrolename trn = new Tblrolename();
			trn.setIntRoleId(adminService.getRoleId("Student"));
			reg.setTblrolename(trn);
			adminService.addRegister(reg);
			// for bank
			Mstbank mstbank = new Mstbank();
			String bankName = req.getParameter("bank");
			if (bankName.equals("")) {
			} else {
				int bankid = Integer.parseInt(bankName);
				Tblbankname tblbn = new Tblbankname();
				tblbn.setIntBankNameId(bankid);
				mstbank.setTblbankname(tblbn);
			}

			mstbank.setTxtBankAccountNo(acno);
			mstbank.setTxtIfsccode(ifsc);

			mstbank.setMstregistration(reg);
			adminService.addBankDetails(mstbank);
			// for login
			Boolean check = false;
			String fMobileLog = reg.getTxtFmobileNumber();
			int regId = reg.getIntRegistrationId();
			Mstregistration regObj = adminService.checkParent(fEmail, regId);
			if (regObj == null) {
				check = false;
			} else {
				int regIdddd = regObj.getIntRegistrationId();
				check = adminService.checkParentIsThere(regIdddd);
			}
			if (check == false) {

				int regIdNow = reg.getIntRegistrationId();
				String userId = Integer.toString(regIdNow);

				String fnameforextracting = fatherName;
				String[] vals = fnameforextracting.split("\\s+");
				String firstFatherName = vals[0];
				// String fatherNameLower = fatherName.toLowerCase();
				String fatherNameLower = firstFatherName.toLowerCase();
				// String fatherNameLower = fatherName.toLowerCase();
				String userNamePar = fatherNameLower.concat(userId);
				String studentNameLower = stuFirstName.toLowerCase();
				String userNameStu = studentNameLower.concat(userId);

				// for login parent
				Mstlogin mstloginPar = new Mstlogin();
				mstloginPar.setMstregistration(reg);

				mstloginPar.setTxtPassword(AESencrp.encrypt(RandomUtil.generateRandomPassword()));
				mstloginPar.setTxtUserName(userNamePar);
				adminService.addLoginDetails(mstloginPar);
				// for login student
				Mstlogin mstloginStu = new Mstlogin();
				mstloginStu.setMstregistration(reg);
				mstloginStu.setTxtPassword(AESencrp.encrypt(RandomUtil.generateRandomPassword()));
				mstloginStu.setTxtUserName(userNameStu);
				mstloginStu.setIntParentId(reg.getIntRegistrationId());
				adminService.addLoginDetails(mstloginStu);

				// for userrole parent
				Tblrolename trn2 = new Tblrolename();
				trn2.setIntRoleId(adminService.getRoleId("PARENT"));

				Tbluserrole userRoleParObj = new Tbluserrole();
				userRoleParObj.setTblrolename(trn2);
				userRoleParObj.setMstlogin(mstloginPar);
				userRoleParObj.setMstregistration(reg);
				adminService.addUserRoleDetails(userRoleParObj);

				// for userrole student
				Tbluserrole userRoleStuObj = new Tbluserrole();
				userRoleStuObj.setTblrolename(reg.getTblrolename());
				userRoleStuObj.setMstlogin(mstloginStu);
				userRoleStuObj.setMstregistration(reg);
				adminService.addUserRoleDetails(userRoleStuObj);

				// for sending user Name & Password
				String parUserName = mstloginPar.getTxtUserName();
				String parPassword = mstloginPar.getTxtPassword();
				String stuUserName = mstloginStu.getTxtUserName();
				String stuPassword = mstloginStu.getTxtPassword();
				AdAstraApi smsObj = new AdAstraApi();
				smsObj.sendingDetailsToParent(fMobile, parUserName, parPassword, stuUserName, stuPassword);
				smsObj.sendingDetailsToParent(mMobile, parUserName, parPassword, stuUserName, stuPassword);
				// for send Email
				if ((!fEmail.equals("")) || (!mEmail.equals(""))) {
					MimeMessage mimeMessage = mailSender.createMimeMessage();
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
					String body = "Thank you for registering with us.\n" + "Your Login Credentials are,"
							+ "\nParent Id : " + parUserName + "\nPassword : " + parPassword + "\nStudent Id : "
							+ stuUserName + "\nPassword : " + stuPassword + "\nPlease do not share it with anyone.";
					;
					if (!fEmail.equals("")) {
						mimeMessageHelper.setTo(fEmail);
					}
					if (!mEmail.equals("")) {
						mimeMessageHelper.setTo(mEmail);
					}
					mimeMessageHelper.setSubject("Registration Success!!!");
					mimeMessageHelper.setText(body);
					mailSender.send(mimeMessage);
				}

			} else {
				Mstregistration regObjLog = adminService.getParentObj(fEmail, regId);

				int regIdNow = reg.getIntRegistrationId();
				String userName2 = Integer.toString(regIdNow);
				String studentNameLower = stuFirstName.toLowerCase();
				String userNameStu = studentNameLower.concat(userName2);

				// for login student
				Mstlogin mstloginStu = new Mstlogin();
				mstloginStu.setMstregistration(reg);
				mstloginStu.setTxtPassword(AESencrp.encrypt(RandomUtil.generateRandomPassword()));
				mstloginStu.setTxtUserName(userNameStu);
				mstloginStu.setIntParentId(regObjLog.getIntRegistrationId());
				adminService.addLoginDetails(mstloginStu);

				// for userrole student
				Tbluserrole userRoleStuObj = new Tbluserrole();
				userRoleStuObj.setTblrolename(reg.getTblrolename());
				userRoleStuObj.setMstlogin(mstloginStu);
				userRoleStuObj.setMstregistration(reg);
				adminService.addUserRoleDetails(userRoleStuObj);

				// for sending user Name & Password
				int regIdPar = regObjLog.getIntRegistrationId();
				int logInId = adminService.getParentLoginId(regIdPar);
				Mstlogin parLoginObj = adminService.getLoginObj(logInId);

				String parUserName = parLoginObj.getTxtUserName();
				String parPassword = parLoginObj.getTxtPassword();
				String stuUserName = mstloginStu.getTxtUserName();
				String stuPassword = mstloginStu.getTxtPassword();
				AdAstraApi smsObj = new AdAstraApi();
				smsObj.sendingDetailsToParent(fMobile, parUserName, parPassword, stuUserName, stuPassword);

				// for send Email
				MimeMessage mimeMessage = mailSender.createMimeMessage();
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
				String body = "Thank you for registering with us.\n" + "Your Login Credentials are," + "\nParent Id : "
						+ parUserName + "\nPassword : " + parPassword + "\nStudent Id : " + stuUserName
						+ "\nPassword : " + stuPassword + "\nPlease do not share it with anyone.";
				;
				mimeMessageHelper.setTo(fEmail);
				mimeMessageHelper.setSubject("Registration Success!!!");
				mimeMessageHelper.setText(body);
				mailSender.send(mimeMessage);

			}

			// end login

			redAtt.addFlashAttribute("msg1", " '" + stuFirstName + "'");
			redAtt.addFlashAttribute("msg2", " was Registered Successfully!!!");

			String secondTab = req.getParameter("secondTab");
			redAtt.addFlashAttribute("secondTab", secondTab);
			return new ModelAndView("redirect:/ad_reg.web");
		}

	}

	// --- new reg end
	@RequestMapping(value = "ad_reg22.web", method = RequestMethod.POST)
	public ModelAndView adRegPost(@RequestParam Map<String, String> params, RedirectAttributes redAtt)
			throws ParseException, MessagingException {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView mav = new ModelAndView();
			Mstregistration regObj = new Mstregistration();

			List<Mstgender> gender = adminService.gender();
			List<Mstreligion> religion = adminService.religion();
			ArrayList<Mstmaritalstatus> marital = (ArrayList<Mstmaritalstatus>) adminService.getMaritalStatus();
			ArrayList<Mstsubject> subject = (ArrayList<Mstsubject>) adminService.getSubject();

			mav.addObject("gender", gender);
			mav.addObject("religion", religion);
			mav.addObject("marital", marital);
			mav.addObject("subject", subject);

			// not required start

			String rel = params.get("religion");
			if (rel.equals("")) {
			} else {
				int relId = Integer.parseInt(rel);
				Mstreligion relObj = new Mstreligion();
				relObj.setIntReligionId(relId);
				regObj.setMstreligion(relObj);
			}

			String marry = params.get("marital");
			if (marry.equals("")) {
			} else {
				int marryId = Integer.parseInt(marry);
				Mstmaritalstatus marryObj = new Mstmaritalstatus();
				marryObj.setIntMaritalStatusId(marryId);
				regObj.setMstmaritalstatus(marryObj);
			}

			String ct = params.get("city");
			regObj.setTxtCity(ct);

			String sub = params.get("subject");
			if (sub.equals("")) {
			} else {
				int subId = Integer.parseInt(sub);
				Mstsubject subObj = new Mstsubject();
				subObj.setIntSubjectId(subId);
				regObj.setMstsubject(subObj);
			}
			// not required end

			String fN = params.get("firstName");
			String mN = params.get("middleName");
			String lN = params.get("lastName");
			String fthrN = params.get("fatherName");
			String gen = params.get("gender");
			int genId = Integer.parseInt(gen);
			String mNum = params.get("mobNum");
			String email = params.get("email");
			String jDt = params.get("joinDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date joinDt = sdf.parse(jDt);
			String preShlNm = params.get("preSchoolName");
			String exp = params.get("experience");
			String desi = params.get("designation");
			String ctc = params.get("ctc");
			String perAdd = params.get("perAdd");
			String tempAdd = params.get("tempAdd");

			regObj.setTxtFirstName(fN);
			regObj.setTxtMiddleName(mN);
			regObj.setTxtLastName(lN);
			regObj.setTxtFatherName(fthrN);
			regObj.setTxtFmobileNumber(mNum);
			regObj.setTxtEmailId(email);
			regObj.setDtJoiningDate(joinDt);
			regObj.setTxtPreviousSchoolName(preShlNm);
			regObj.setTxtExperience(exp);
			regObj.setTxtDesignation(desi);
			regObj.setTxtPerAddress(perAdd);
			regObj.setTxtTempAddress(tempAdd);

			Mstgender genObj = new Mstgender();
			genObj.setIntGenderId(genId);

			Tblrolename trn = new Tblrolename();
			trn.setIntRoleId(3);

			regObj.setMstgender(genObj);
			regObj.setTblrolename(trn);

			adminService.saveTeachReg(regObj);

			// for bank
			Mstbank bankObj = new Mstbank();
			bankObj.setMstregistration(regObj);
			bankObj.setTxtCtc(ctc);
			adminService.saveTeachBank(bankObj);

			// for login
			int regIdNow = regObj.getIntRegistrationId();
			String userName2 = Integer.toString(regIdNow);
			String teacherNameLower = fN.toLowerCase();
			String userName = teacherNameLower.concat(userName2);

			Mstlogin mstloginTeach = new Mstlogin();
			mstloginTeach.setMstregistration(regObj);
			mstloginTeach.setTxtPassword(AESencrp.encrypt(RandomUtil.generateRandomPassword()));
			mstloginTeach.setTxtUserName(userName);
			adminService.addLoginDetails(mstloginTeach);

			// for userrole
			Tbluserrole userRoleStuObj = new Tbluserrole();
			userRoleStuObj.setTblrolename(regObj.getTblrolename());
			userRoleStuObj.setMstlogin(mstloginTeach);
			userRoleStuObj.setMstregistration(regObj);
			adminService.addUserRoleDetails(userRoleStuObj);

			// for sending user Name & Password
			String teachUserName = mstloginTeach.getTxtUserName();
			String teachPassword = mstloginTeach.getTxtPassword();
			AdAstraApi smsObj = new AdAstraApi();
			smsObj.sendingDetailsToTeacher(mNum, teachUserName, teachPassword);

			// for send Email
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			String body = "Thank you for registering with us.\n" + "Your Login Credentials are," + "\nTeacher Id : "
					+ teachUserName + "\nPassword : " + teachPassword + "\nPlease do not share it with anyone.";
			;
			mimeMessageHelper.setTo(email);
			mimeMessageHelper.setSubject("Registration Success!!!");
			mimeMessageHelper.setText(body);
			mailSender.send(mimeMessage);

			redAtt.addFlashAttribute("msg1", " '" + fN + "'" + " is registered Successfully!!!!!!");
			String secondTab = params.get("secondTab");
			redAtt.addFlashAttribute("secondTab", secondTab);
			return new ModelAndView("redirect:/ad_reg.web");
		}
	}

	// --------- reg end----------------

	// ---------Student performance start

	@RequestMapping(value = "ad_student perform.web")
	public ModelAndView adStudentPerform() {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_student perform");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstterms> terms = (ArrayList<Mstterms>) adminService.getTerms();
			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("terms", terms);

			ObjectMapper mapper = new ObjectMapper();

			List<String> list = new ArrayList<String>();
			list.add("List A");
			list.add("List B");
			list.add("List C");
			list.add("List D");
			list.add("List E");

			String json = "";
			try {
				json = mapper.writeValueAsString(list);
			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * m.addObject("list", json); List l = new ArrayList<>(); l.add(22);
			 * l.add(44); l.add(64); m.addObject("l",l);
			 */
			/*
			 * m.addObject("s2",42); m.addObject("s3",36);
			 */

			return m;
		}
	}

	@RequestMapping(value = "ad_student perform.web", method = RequestMethod.POST)
	public ModelAndView adStudentPerformSearch(HttpServletRequest req) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_student perform");
			int ClassId = Integer.parseInt(req.getParameter("ClassId"));
			int SectionId = Integer.parseInt(req.getParameter("SectionId"));
			System.out.println("inside stu per");
			int TermId = Integer.parseInt(req.getParameter("TermId"));

			String StudentId = req.getParameter("StudentId");
			ArrayList<Mststudclass> classes = (ArrayList<Mststudclass>) adminService.getClasses();
			ArrayList<Mststudclasssection> sections = (ArrayList<Mststudclasssection>) adminService.getSections();
			ArrayList<Mstterms> terms = (ArrayList<Mstterms>) adminService.getTerms();
			m.addObject("classes", classes);
			m.addObject("sections", sections);
			m.addObject("terms", terms);
			ArrayList<Mstregistration> Mstal = (ArrayList<Mstregistration>) adminService.getAllStudentLists(ClassId,
					SectionId, StudentId);

			ArrayList<Mstuploadstudmarksheet> Mstms = (ArrayList<Mstuploadstudmarksheet>) adminService
					.getMarksList(ClassId, SectionId, TermId, StudentId);
			System.out.println(Mstms.size());
			/*
			 * Iterator<Mstuploadstudmarksheet> i=Mstms.iterator(); while
			 * (i.hasNext()) { Mstuploadstudmarksheet mstuploadstudmarksheet =
			 * (Mstuploadstudmarksheet) i.next();
			 * 
			 * }
			 */
			m.addObject("Mstms", Mstms);
			m.addObject("Mstal", Mstal);
			m.addObject("performance", "performance");
			return m;
		}
	}

	@RequestMapping(value = "downloadPerformance/{id}")
	public void DownloadMarks(HttpServletResponse res, @PathVariable Integer id) throws IOException {
		/* ModelAndView m = new ModelAndView("ad_student perform"); */
		Mstuploadstudmarksheet file = adminService.getMarkSheet(id);
		res.setContentLength(file.getBlMarkSheet().length);
		res.setContentType(file.getTxtcontentType());
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getTxtfileName() + "\"");
		FileCopyUtils.copy(file.getBlMarkSheet(), out);
		/* res.sendRedirect("ad_student perform.jsp"); */
		out.flush();
		out.close();
		return;

	}

	@RequestMapping(value = "viewPerformance/{id}")
	public void ViewMarks(HttpServletResponse res, @PathVariable Integer id) throws IOException {
		/* ModelAndView m = new ModelAndView("ad_student perform"); */
		Mstuploadstudmarksheet file = adminService.getMarkSheet(id);
		res.setContentLength(file.getBlMarkSheet().length);
		res.setContentType(file.getTxtcontentType());
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "inline; filename=\"" + file.getTxtfileName() + "\"");
		FileCopyUtils.copy(file.getBlMarkSheet(), out);
		/* res.sendRedirect("ad_student perform.jsp"); */
		out.flush();
		out.close();
		return;
	}
	// --------Student performance end

	// -------------query start

	@RequestMapping(value = "ad_query.web", method = RequestMethod.GET)
	public ModelAndView askedQuery() {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ad_query");
			ArrayList<Tblparentqueries> list = (ArrayList<Tblparentqueries>) adminService.pquestion();
			m.addObject("list", list);
			return m;
		}

	}

	@RequestMapping(value = "ad_query/{id}.web", method = RequestMethod.POST)
	public String answeredQuery(HttpServletRequest req, RedirectAttributes re, @PathVariable("id") Integer id) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			String answer = req.getParameter("answer");
			adminService.saveAnswer(answer, id);
			re.addFlashAttribute("msg", "Answer submitted successfully");
			return "redirect:/ad_query.web";
		}
	}

	// ------------------Query end

}