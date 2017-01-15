package com.aikshika.parent.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aikshika.admin.controller.AdminController;
import com.aikshika.entity.Mstfeemanagement;
import com.aikshika.entity.Mstleave;
import com.aikshika.entity.Mstlogin;
import com.aikshika.entity.Mstquestionandanswer;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mststudclass;
import com.aikshika.entity.Mststudenttimetable;
import com.aikshika.entity.Mstsubject;
import com.aikshika.entity.Mstsyllabus;
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
import com.aikshika.login.controller.LoginController;
import com.aikshika.login.service.LoginService;
import com.aikshika.parent.service.ParentService;
import com.aikshika.student.service.StudentService;

@Controller
public class ParentController {

	private Logger logger = Logger.getLogger(ParentController.class);
	
	
	@Autowired
	ParentService parentService;

	@Autowired
	private HttpSession session;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	StudentService Studentservice;

	@RequestMapping("ad_index_For_Parent.web")
	public ModelAndView Index() {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("parentDashboard");
		int intRegId =  (int) session.getAttribute("intRegId");
				intRegId = 0;
				List<Mstregistration> mst = (List<Mstregistration>)Studentservice.getParentInformationByName(intRegId);
				mav.addObject("mst",mst);
				System.out.println("parent..........");
				System.out.println("............"+mst);

				return mav;

		}
	}
	@RequestMapping(value="/getUserImageee/{id}.web")
	public void getUserImage(HttpServletResponse response , @PathVariable("id") int tweetID) throws IOException{

	 response.setContentType("image/jpeg");
	 int id = (int) session.getAttribute("intRegId");
	 List<Mstregistration> list = Studentservice.getParentInformationByName(id);
	 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		Mstregistration mstregistration = (Mstregistration) iterator.next();
		if(mstregistration.getIntRegistrationId()==tweetID){
		 byte[] buffer = mstregistration.getBlImage();
		 InputStream in1 = new ByteArrayInputStream(buffer);
		  IOUtils.copy(in1, response.getOutputStream());
	}}
	          
	}
	
	@RequestMapping(value="parent_profile.web", method=RequestMethod.POST)
	public String saveImage( @RequestParam CommonsMultipartFile blAttachment)
	{
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return "redirect:/login.web";
		}
		else
		{
		Mstregistration reg=new Mstregistration();
		int intRegId =  (int) session.getAttribute("intRegId");
		byte [] byteArr=blAttachment.getBytes();
		
				Studentservice.saveImage(byteArr,intRegId);
		return "redirect:/ad_index_For_Parent.web";
		}
	}
	@RequestMapping(value = "ajax_par_timetable.web")
	public ModelAndView partimetable1(
			HttpServletRequest request) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("ajax_par_timetable");
		int intRegId = (int) session.getAttribute("intRegId");
		
		List<Mstlogin> list = loginService.getStudentInfoByParentId(intRegId);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		int sid = Integer.parseInt(request.getParameter("val1"));
		Mstregistration regObj = parentService.getClassSection(sid);
		int classId = regObj.getMststudclass().getIntClassId();
		int sectionId = regObj.getMststudclasssection().getIntSectionId();
		ArrayList<ArrayList<Mststudenttimetable>> totalList = Studentservice.getStudentTT(classId, sectionId);

		ArrayList<Msttimings> timingList = (ArrayList<Msttimings>) Studentservice.getTimings();
		ArrayList<Tbldaysofweek> daysList = (ArrayList<Tbldaysofweek>) Studentservice.getDays();

		mav.addObject("daysList", daysList);
		mav.addObject("timingList", timingList);
		mav.addObject("totalList", totalList);
		return mav;
		}
	}

	@RequestMapping(value = "ajax_par_attendance.web")
	public ModelAndView ajaxGetCurrentAttendance(HttpServletRequest req, ModelMap map) throws ParseException {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView m = new ModelAndView("ajax_par_attendance");
		int intRegId = (int) session.getAttribute("intRegId");
		String name = req.getParameter("val1");
		if (name != null) {
			List<Trnattendancerecord> trnAttendance = parentService.getStudentAttendanceByName(name);

			ArrayList<String> s = new ArrayList<>();
			java.util.Iterator<Trnattendancerecord> i2 = trnAttendance.iterator();
			while (i2.hasNext()) {
				Trnattendancerecord attendanceObject = i2.next();
				String time = attendanceObject.getDtSlotTime();
				String splitedTime[] = time.split("\\s+");
				String inTime = splitedTime[0];
				String outTime = "";
				String totalHours = "";

				if (splitedTime.length > 1) {
					outTime = splitedTime[1];
					SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
					DateTime timeValue1 = new DateTime(formatter1.parse(inTime).getTime());

					SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
					DateTime timeValue2 = new DateTime(formatter2.parse(outTime).getTime());

					String hours = String.valueOf(Hours.hoursBetween(timeValue1, timeValue2).getHours() % 24);
					String minutes = String.valueOf(Minutes.minutesBetween(timeValue1, timeValue2).getMinutes() % 60);
					System.out.println("inTime " + inTime);
					System.out.println("outTime " + outTime);
					System.out.println("hrs " + hours);
					System.out.println("min " + minutes);
					if (Integer.parseInt(hours) < 10) {
						hours = "0" + hours;
					}
					if (Integer.parseInt(minutes) < 10) {
						minutes = "0" + minutes;
					}
					totalHours = hours + ":" + minutes;
					System.out.println("total hrs " + totalHours);
					m.addObject("totalHours", totalHours);

					/*
					 * if (outTime == null) { m.addObject("msg4", outTime); } if
					 * (totalHours == null) { m.addObject("msg5", totalHours); }
					 */
				}
				s.add(time);
				s.add(inTime);
				s.add(outTime);
				m.addObject("inTime", inTime);
				m.addObject("outTime", outTime);

			}
			m.addObject("trnAttendance", trnAttendance);
			map.addAttribute("msg", "No Attendance Found For Today");

		} else {
			String name1 = req.getParameter("mstregistration1");
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
			String d1 = req.getParameter("fromDate");
			String d2 = req.getParameter("toDate");

			java.sql.Date startDate = new java.sql.Date(dateFormat1.parse(d1).getTime());
			java.sql.Date endDate = new java.sql.Date(dateFormat2.parse(d2).getTime());
			java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
			System.out.println("appliedDate " + appliedDate);
			if (endDate.after(appliedDate)) {
				// System.out.println("Not Possible");
				map.addAttribute("msg1", "Date Can't Be Greater Than Today's Date");
			} else {
				List<Trnattendancerecord> trnBetweenAttendance = parentService
						.getStudentAttendanceBetweenTwoDates(intRegId, name1, startDate, endDate);
				System.out.println("in ctrl " + trnBetweenAttendance);
				List<BetweenCal> list1 = new ArrayList();
				for (Iterator iterator = trnBetweenAttendance.iterator(); iterator.hasNext();) {

					Trnattendancerecord trn = (Trnattendancerecord) iterator.next();

					BetweenCal entityModel = new BetweenCal();

					// entityModel.setDate(trn.getDtSlotDate());
					entityModel.setDate(trn.getDtSlotDate());

					SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
					SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
					String time = trn.getDtSlotTime();
					String[] splitedTime = time.split("\\s+");
					String inTime = splitedTime[0];
					String outTime = "";
					String totalHours = "";
					entityModel.setInTime(inTime);
					if (splitedTime.length > 1) {
						outTime = splitedTime[1];
						entityModel.setOutTime(outTime);
						DateTime timeValue1 = new DateTime(formatter1.parse(inTime).getTime());
						DateTime timeValue2 = new DateTime(formatter2.parse(outTime).getTime());
						String hours = String.valueOf(Hours.hoursBetween(timeValue1, timeValue2).getHours() % 24);
						if (Integer.parseInt(hours) < 10) {
							hours = "0" + hours;
						}
						String minutes = String
								.valueOf(Minutes.minutesBetween(timeValue1, timeValue2).getMinutes() % 60);
						if (Integer.parseInt(minutes) < 10) {
							minutes = "0" + minutes;
						}

						totalHours = hours + ":" + minutes;
						entityModel.setTotalhrs(totalHours);

						list1.add(entityModel);
					} else {
						entityModel.setOutTime(outTime);
						entityModel.setTotalhrs(totalHours);
						list1.add(entityModel);
					}
				}
				m.addObject("list", list1);
			}
			map.addAttribute("msg", "No Attendance Found For Today");
		}
		return m;
		}
	}

	@RequestMapping(value = "ajax_par_project.web")
	public ModelAndView ajaxViewProjectDetail(HttpServletRequest req) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("ajax_par_project");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		String name = req.getParameter("val1");
		List<Mstteacherproject> project = parentService.getStudentProjectByName(list, name);
		if (project.isEmpty()) {
			mav.addObject("noRecordErr", "true");
		} else {
			mav.addObject("project", project);
		}

		return mav;
		}
	}

	@RequestMapping(value = "ajax_par_homework.web")
	public ModelAndView ajaxGettudentHomeWork(HttpServletRequest req) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("ajax_par_homework");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		String name = req.getParameter("val1");
		List<Tblhomework> homework = parentService.getStudentHomeWorkByName(list, name);
		if (homework.isEmpty()) {
			mav.addObject("noRecordErr", "true");
		} else {
			System.out.println("Inside controller with ajax:");
			for (Iterator iterator = homework.iterator(); iterator.hasNext();) {
				Tblhomework t = (Tblhomework) iterator.next();
				System.out.print("homework:" + t.getTxtHomeWork() + " ");
				System.out.print("Assigned date" + t.getDtAssignDate());
				System.out.println();
			}
			mav.addObject("homework", homework);
		}

		return mav;
		}
	}

	@RequestMapping("ajax_par_student_profile.web")
	public ModelAndView ajaxParStudentProfile(HttpServletRequest req) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("ajax_par_student_profile");
		int id = (int) session.getAttribute("intRegId");
		String name = req.getParameter("val1");
		List<Mstregistration> studDetail = (List<Mstregistration>) parentService.getStudentDetailByName(id,name);
		mav.addObject("studDetail", studDetail);
		return mav;
		}
	}

	@RequestMapping("ajax_par_marks.web")
	public ModelAndView ajaxStudentmarks(@ModelAttribute("parent") Mstuploadstudmarksheet parent,
			HttpServletRequest req) {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("ajax_par_marks");
		String name = req.getParameter("val1");
		List<Mstuploadstudmarksheet> mst = (List<Mstuploadstudmarksheet>) parentService.getStudentMarksListByName(name);
		mav.addObject("mst", mst);
		return mav;
		}
	}

	@RequestMapping(value = "ajax_par_notification.web")
	public ModelAndView ajaxDisplayStudentNotification(HttpServletRequest req, ModelMap map) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("ajax_par_notification");

		String name = req.getParameter("val1");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		List<Tblinvites> tblinvites = parentService.getNotificationByStudentName(name);

		List<Mstteacherinvite> mste = parentService.getNotificationToStudentNameByTeacher(list, name);
		mav.addObject("tblinvites", tblinvites);
		mav.addObject("mste", mste);
		/*if (tblinvites.isEmpty()) {
			//mav.addObject("noRecordErr", "true");
			System.out.println("tblinvites.isEmpty()");
			mav.addObject("mste", mste);
		} 
		else if(mste.isEmpty())
		{
			System.out.println("mste.isEmpty()");
			mav.addObject("tblinvites", tblinvites);
		}
		
		else if(!tblinvites.isEmpty()&&!mste.isEmpty()){
			mav.addObject("tblinvites", tblinvites);
			mav.addObject("mste", mste);
			System.out.println("Both conditions working");
		}*/
		return mav;
		}
	}

	@RequestMapping(value = "ajax_par_StudleaveInbox.web")
	public ModelAndView ajaxLeaveRequestInbox(HttpServletRequest req) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
			
		
		ModelAndView mav = new ModelAndView("ajax_par_StudleaveInbox");
		// String name = req.getParameter("val1");

		int id = Integer.parseInt(req.getParameter("val1"));
		List<Trnstudentleaveapp> studentLeaveInbox = parentService.getStudentLeaveInbox(id);

		mav.addObject("inbox", "inbox");
		mav.addObject("studentLeaveInbox", studentLeaveInbox);

		return mav;
		}
	}

	@RequestMapping("par_student profile.web")
	public ModelAndView parStudentProfile() {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
			
		
		ModelAndView mav = new ModelAndView("par_student profile");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("mst", mst);
		/*
		 * List<Mstregistration> studDetail = (List<Mstregistration>)
		 * parentService.getStudentDetailByName("Naina"); //
		 * map.addAttribute("studentName", studentName); if
		 * (studDetail.isEmpty()) {
		 * 
		 * mav.addObject("noRecordErr", "true"); } else {
		 * mav.addObject("studDetail", studDetail); }
		 */
		return mav;
	}
	}

	@RequestMapping(value = "par_student profile.web", method = RequestMethod.POST)
	public ModelAndView parStudentProfile(HttpServletRequest req, ModelMap model, ModelMap map) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_student profile");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("mst", mst);
		String studentName = req.getParameter("studentName");
		List<Mstregistration> studDetail = (List<Mstregistration>) parentService.getStudentDetailByName(id,studentName);
		map.addAttribute("studentName", studentName);
		if (studDetail.isEmpty()) {

			mav.addObject("noRecordErr", "true");
		} else {
			mav.addObject("studDetail", studDetail);
		}

		return mav;
		}
	}

	@RequestMapping(value = "par_student profile_update.web")
	public ModelAndView updateStudentProfile(ModelMap map, HttpServletRequest req, @RequestParam String name) {
		/* ModelAndView mav = new ModelAndView("updateStudentProfile"); */
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
			
		ModelAndView mav = new ModelAndView("updateStudentProfile");
		int id = (int) session.getAttribute("intRegId");
		List<Mstregistration> studDetail = (List<Mstregistration>) parentService.getStudentDetailByName(id,name);
		mav.addObject("studDetail", studDetail);
		return mav;
		}
	}

	@RequestMapping(value = "Updated_par_student profile.web")
	public ModelAndView updatedStudentProfile(ModelMap map, @ModelAttribute Mstregistration mstregistration,HttpServletRequest req,RedirectAttributes redirectAttributes) {
		// ModelAndView mav = new ModelAndView("par_student profile");
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("redirect:/par_student profile.web");
		int id = (int) session.getAttribute("intRegId");
		 //System.out.println("id:"+mstregistration.getIntRegistrationId());
		// System.out.println(mstregistration.getTxtPerAddress());
		int studentId = Integer.parseInt(req.getParameter("intRegistrationId"));
		parentService.update(mstregistration, studentId);
		List<Mstregistration> studDetail = (List<Mstregistration>) parentService
				.getStudentDetailByName(id,mstregistration.getTxtFirstName());
		// System.out.println("list:"+studDetail);
		/*mav.addObject("studDetail", studDetail);*/
		redirectAttributes.addFlashAttribute("msg", mstregistration.getTxtFirstName()+" Details has been updated successfully");
		return mav;
		}
	}

	/*
	 * @RequestMapping("par_marks.web") public ModelAndView
	 * studentmarks(@ModelAttribute("parent") Mstuploadstudmarksheet parent) {
	 * 
	 * ModelAndView mav = new ModelAndView("par_marks");
	 * List<Mstuploadstudmarksheet> mst =
	 * (List<Mstuploadstudmarksheet>)parentService.getStudentMarksByName();
	 * mav.addObject("mst",mst); return mav; }
	 */

	@RequestMapping("par_marks.web")
	public ModelAndView studentmarks(@ModelAttribute("parent") Mstuploadstudmarksheet parent, HttpServletRequest req) {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_marks");
		int id = (int) session.getAttribute("intRegId");

		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		String name = req.getParameter("studentName");
		List<Mstuploadstudmarksheet> mst1 = (List<Mstuploadstudmarksheet>) parentService
				.getStudentMarksListByName(name);
		/*
		 * for (Iterator iterator = mst.iterator(); iterator.hasNext();) {
		 * Mstuploadstudmarksheet mstuploadstudmarksheet =
		 * (Mstuploadstudmarksheet) iterator.next();
		 * System.out.println(mstuploadstudmarksheet.getMststudclasssection().
		 * getTxtSectionName());
		 * System.out.println(mstuploadstudmarksheet.getMststudclass().
		 * getTxtClassName()); }
		 */
		mav.addObject("mst", mst1);
		return mav;
		}
	}

	// marks download
	@RequestMapping(value = "downloads/{id}")
	public void Download(HttpServletRequest request,HttpServletResponse response, @PathVariable Integer id) throws IOException {

		
		Mstuploadstudmarksheet marksObj = parentService.getMarkSheet(id);
		/*res.setContentLength(file.getBlMarkSheet().length);
		res.setContentType(file.getTxtcontentType());
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getTxtfileName() + "\"");
		FileCopyUtils.copy(file.getBlMarkSheet(), out);
		res.sendRedirect("par_marks.jsp");
		out.flush();
		out.close();
		return;*/
		String dataDirectory = request.getServletContext().getRealPath("/assets/teachMarkSheets/");
		Path file = Paths.get(dataDirectory, marksObj.getTxtfileName());
		if (Files.exists(file)) {
			response.setContentType(marksObj.getTxtcontentType());
			response.addHeader("Content-Disposition", "attachment; filename=" + marksObj.getTxtfileName());
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	// correct for leave request
	@RequestMapping(value = "par_leave request.web")
	public ModelAndView addLeaveRequestDropDown(@ModelAttribute("trnLeave") Trnstudentleaveapp trnLeave) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_leave request");
		ArrayList<Mstleave> leaveal = (ArrayList<Mstleave>) parentService.getLeave();
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("leaveal", leaveal);
		mav.addObject("regal", mst);

		return mav;
		}
	}

	@RequestMapping(value = "par_leave request.web", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest req, ModelMap map) throws ParseException {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		Trnstudentleaveapp lapp = new Trnstudentleaveapp();
		ModelAndView m = new ModelAndView("par_leave request");
		String reason = req.getParameter("txtReason");
		lapp.setTxtReason(reason);
		int leaveId = Integer.parseInt(req.getParameter("mstleave"));
		int RegId = Integer.parseInt(req.getParameter("mstregistration"));
		SimpleDateFormat dateFormate1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat dateFormate2 = new SimpleDateFormat("MM/dd/yyyy");

		String d1 = req.getParameter("dtFromDate");
		String d2 = req.getParameter("dtToDate");
		java.sql.Date fromDate = new java.sql.Date(dateFormate1.parse(d1).getTime());
		java.sql.Date toDate = new java.sql.Date(dateFormate2.parse(d2).getTime());
		java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
		if (toDate.before(fromDate)) {

			map.addAttribute("msg1", "To Date must be greater than from From Date");
		} else {
			lapp.setDtFromDate(fromDate);
			lapp.setDtToDate(toDate);
			lapp.setDtAppliedDate(appliedDate);
			Mstleave ml = new Mstleave();
			ml.setIntLeaveId(leaveId);
			lapp.setMstleave(ml);

			Mstregistration mreg = new Mstregistration();
			mreg.setIntRegistrationId(RegId);
			lapp.setMstregistration(mreg);
			parentService.leaveApp(lapp);
			map.addAttribute("msg", "Leave Request has been sent successfully");
		}
		ArrayList<Mstleave> leaveal = (ArrayList<Mstleave>) parentService.getLeave();
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		m.addObject("leaveal", leaveal);
		m.addObject("regal", mst);

		return m;
		}
	}

	@RequestMapping(value = "par_StudleaveInbox.web")
	public ModelAndView leaveRequestInbox(HttpServletRequest req) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_StudleaveInbox");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("mst", mst);
		/*
		 * int id = Integer.parseInt(req.getParameter("studentName"));
		 * List<Trnstudentleaveapp> studentLeaveInbox =
		 * parentService.getStudentLeaveInbox(id);
		 * 
		 * 
		 * mav.addObject("inbox", "inbox"); mav.addObject("studentLeaveInbox",
		 * studentLeaveInbox);
		 */

		return mav;
		}
	}

	@RequestMapping(value = "par_StudleaveInboxData.web")
	public ModelAndView leaveRequestInboxData(HttpServletRequest req) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
	ModelAndView mav = new ModelAndView("par_StudleaveInbox");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("mst", mst);
		int id1 = Integer.parseInt(req.getParameter("studentName"));
		List<Trnstudentleaveapp> studentLeaveInbox = parentService.getStudentLeaveInbox(id1);

		mav.addObject("inbox", "inbox");
		mav.addObject("studentLeaveInbox", studentLeaveInbox);

		return mav;
		}
	}

	@RequestMapping(value = "par_attendance.web")
	public ModelAndView addNameInAttendanceLisrt(@ModelAttribute("attendance") Trnattendancerecord attendance) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_attendance");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_attendance.web", method = RequestMethod.POST)
	public ModelAndView getCurrentAttendance(HttpServletRequest req, ModelMap map) throws ParseException {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		
		ModelAndView m = new ModelAndView("par_attendance");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		m.addObject("regal", mst);
		String name = req.getParameter("mstregistration");
		if (name != null) {
			List<Trnattendancerecord> trnAttendance = parentService.getStudentAttendanceByName(name);

			ArrayList<String> s = new ArrayList<>();
			java.util.Iterator<Trnattendancerecord> i2 = trnAttendance.iterator();
			while (i2.hasNext()) {
				Trnattendancerecord attendanceObject = i2.next();
				String time = attendanceObject.getDtSlotTime();
				String splitedTime[] = time.split("\\s+");
				String inTime = splitedTime[0];
				String outTime = "";
				String totalHours = "";

				if (splitedTime.length > 1) {
					outTime = splitedTime[1];
					s.add(time);
					s.add(inTime);
					s.add(outTime);
					m.addObject("inTime", inTime);
					m.addObject("outTime", outTime);
					System.out.println("inTime " + inTime);
					System.out.println("outTime " + outTime);
					SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
					DateTime timeValue1 = new DateTime(formatter1.parse(inTime).getTime());

					SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
					DateTime timeValue2 = new DateTime(formatter2.parse(outTime).getTime());

					String hours = String.valueOf(Hours.hoursBetween(timeValue1, timeValue2).getHours() % 24);
					String minutes = String.valueOf(Minutes.minutesBetween(timeValue1, timeValue2).getMinutes() % 60);
					System.out.println("hrs " + hours);
					System.out.println("min " + minutes);
					if (Integer.parseInt(hours) < 10) {
						hours = "0" + hours;
					}
					if (Integer.parseInt(minutes) < 10) {
						minutes = "0" + minutes;
					}
					totalHours = hours + ":" + minutes;
					System.out.println(totalHours);
					m.addObject("totalHours", totalHours);
				}

				/*
				 * if (outTime == null) { m.addObject("msg4", outTime); } if
				 * (totalHours == null) { m.addObject("msg5", totalHours); }
				 */
			}
			m.addObject("trnAttendance", trnAttendance);
			// map.addAttribute("msg", "No Attendance Found For Today");
			m.addObject("msg", "No Attendance Found For Today");

		} else {

			/*
			 * * SimpleDateFormat dateFormat1 = new
			 * SimpleDateFormat("dd/MM/yyyy"); SimpleDateFormat dateFormat2 =
			 * new SimpleDateFormat("dd/MM/yyyy");
			 */String name1 = req.getParameter("mstregistration1");
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
			String d1 = req.getParameter("fromDate");
			String d2 = req.getParameter("toDate");

			java.sql.Date startDate = new java.sql.Date(dateFormat1.parse(d1).getTime());
			java.sql.Date endDate = new java.sql.Date(dateFormat2.parse(d2).getTime());
			java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
			System.out.println("appliedDate " + appliedDate);
			if (endDate.after(appliedDate)) {
				// System.out.println("Not Possible");
				map.addAttribute("msg1", "Date Can't Be Greater Than Today's Date");
			}

			else if (startDate.after(endDate)) {
				map.addAttribute("msg1", "From Date cannot be greater than To Date");
			} else {

				List<Trnattendancerecord> trnBetweenAttendance = parentService.getStudentAttendanceBetweenTwoDates(id,
						name1, startDate, endDate);
				System.out.println("in ctrl " + trnBetweenAttendance);
				List<BetweenCal> list1 = new ArrayList();
				for (Iterator iterator = trnBetweenAttendance.iterator(); iterator.hasNext();) {

					System.out.println("inside for loop");
					Trnattendancerecord trn = (Trnattendancerecord) iterator.next();

					BetweenCal entityModel = new BetweenCal();
					System.out.println("after creating trnatd object");

					// entityModel.setDate(trn.getDtSlotDate());
					entityModel.setDate(trn.getDtSlotDate());
					System.out.println("date...." + trn.getDtSlotDate());
					System.out.println("setting date to between entity");

					SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
					SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
					String time = trn.getDtSlotTime();
					String[] splitedTime = time.split("\\s+");
					String inTime = splitedTime[0];
					String outTime = "";
					String totalHours = "";
					entityModel.setInTime(inTime);
					if (splitedTime.length > 1) {
						 outTime = splitedTime[1];
						
						entityModel.setOutTime(outTime);

						DateTime timeValue1 = new DateTime(formatter1.parse(inTime).getTime());
						DateTime timeValue2 = new DateTime(formatter2.parse(outTime).getTime());
						String hours = String.valueOf(Hours.hoursBetween(timeValue1, timeValue2).getHours() % 24);
						if (Integer.parseInt(hours) < 10) {
							hours = "0" + hours;
						}
						String minutes = String
								.valueOf(Minutes.minutesBetween(timeValue1, timeValue2).getMinutes() % 60);
						if (Integer.parseInt(minutes) < 10) {
							minutes = "0" + minutes;
						}

						 totalHours = hours + ":" + minutes;
						entityModel.setTotalhrs(totalHours);
						list1.add(entityModel);
					}else{
						entityModel.setOutTime(outTime);
						entityModel.setTotalhrs(totalHours);
						list1.add(entityModel);
					}
				}

				m.addObject("list", list1);
			}
			map.addAttribute("msg", "No Attendance Found For Today");
		}
		return m;
		}
	}

	@RequestMapping(value = "par_timetable.web")
	public ModelAndView addNameInParTimeTable(@ModelAttribute("studTimeTable") Mststudenttimetable stuudentTimeTable) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_timetable");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	/*
	 * // a
	 * 
	 * @RequestMapping(value = "par_timetable.web", method = RequestMethod.POST)
	 * public ModelAndView partimetable(@ModelAttribute("parent")
	 * Mststudenttimetable parent) {
	 * 
	 * ModelAndView mav = new ModelAndView("par_timetable"); int id = (int)
	 * session.getAttribute("intRegId"); List<Mstlogin> list =
	 * loginService.getStudentInfoByParentId(id); List<Mstregistration> mst =
	 * (List<Mstregistration>) parentService.getStudentInformationByName(list);
	 * mav.addObject("regal", mst); List<Object[]> mst1 = (List<Object[]>)
	 * parentService.getStuTimeTablebyClass(); mav.addObject("mst", mst1);
	 * return mav; }
	 */

	// student timetable
	@RequestMapping(value = "par_timetable.web", method = RequestMethod.POST)
	public ModelAndView partimetable(@ModelAttribute("parent") Mststudenttimetable parent, HttpServletRequest request) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		
		ModelAndView mav = new ModelAndView("par_timetable");

		int intRegId = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(intRegId);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		int sid = Integer.parseInt(request.getParameter("studentName"));
		Mstregistration regObj = parentService.getClassSection(sid);
		int classId = regObj.getMststudclass().getIntClassId();
		int sectionId = regObj.getMststudclasssection().getIntSectionId();
		//ArrayList<ArrayList<Mststudenttimetable>> totalList = parentService.getStudentTT(classId, sectionId);
		ArrayList<ArrayList<Mststudenttimetable>> totalList = Studentservice.getStudentTT(classId, sectionId);
		//ArrayList<Msttimings> timingList = (ArrayList<Msttimings>) parentService.getTimings();
	//	ArrayList<Tbldaysofweek> daysList = (ArrayList<Tbldaysofweek>) parentService.getDays();
		
		ArrayList<Msttimings> timingList = Studentservice.getTimings();
		ArrayList<Tbldaysofweek> daysList = Studentservice.getDays();

		mav.addObject("daysList", daysList);
		mav.addObject("timingList", timingList);
		mav.addObject("totalList", totalList);
		return mav;
		}
	}

	/*
	 * @RequestMapping(value = "par_timetable.web", method = RequestMethod.POST)
	 * public ModelAndView displayStudentParTimeTable(HttpServletRequest req) {
	 * String name = req.getParameter("studentName"); ModelAndView mav = new
	 * ModelAndView("par_timetable"); List<Mststudenttimetable> studTimeTbl =
	 * parentService.getStudentTimeTable(name); mav.addObject("studTimeTbl",
	 * studTimeTbl); return mav; }
	 */

	@RequestMapping(value = "par_homework.web")
	public ModelAndView addNameInParHomeWork(@ModelAttribute("studHomeWork") Tblhomework stuudentHomeWork) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_homework");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	/*
	 * @RequestMapping(value = "par_homework.web", method = RequestMethod.POST)
	 * public ModelAndView gettudentHomeWork(HttpServletRequest req, ModelMap
	 * map) throws ParseException {
	 * 
	 * ModelAndView m = new ModelAndView("par_homework"); int id = (int)
	 * session.getAttribute("intRegId"); List<Mstlogin> list =
	 * loginService.getStudentInfoByParentId(id); List<Mstregistration> mst =
	 * (List<Mstregistration>) parentService.getStudentInformationByName(list);
	 * m.addObject("regal", mst); String name = req.getParameter("studentName");
	 * SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd"); String
	 * d1 = req.getParameter("date"); List<Tblhomework> homework = null; if
	 * (name != null && d1 == "") {
	 * 
	 * homework = parentService.getStudentHomeWorkByName(name);
	 * m.addObject("homework", homework);
	 * 
	 * } else {
	 * 
	 * java.sql.Date date = new java.sql.Date(dateFormate.parse(d1).getTime());
	 * System.out.println("Date=" + d1);
	 * 
	 * homework = parentService.getStudentHomeWorkByName(name, date);
	 * m.addObject("homework", homework);
	 * 
	 * } if (homework.isEmpty()) {
	 * 
	 * map.addAttribute("msg", "No Record Found"); } return m; }
	 */

	@RequestMapping(value = "par_homework.web", method = RequestMethod.POST)
	public ModelAndView gettudentHomeWork(HttpServletRequest req) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		
		ModelAndView mav = new ModelAndView("par_homework");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		String name = req.getParameter("studentName");
		List<Tblhomework> homework = parentService.getStudentHomeWorkByName(list, name);
		if (homework.isEmpty()) {
			mav.addObject("noRecordErr", "true");
		} else {
			mav.addObject("homework", homework);
			System.out.println("inside controller......");
			for (Iterator iterator = homework.iterator(); iterator.hasNext();) {
				Tblhomework t = (Tblhomework) iterator.next();
				System.out.print("homework:" + t.getTxtHomeWork() + " ");
				System.out.print("Assigned date" + t.getDtAssignDate());
				System.out.println();

			}
		}

		return mav;
		}
	}

	@RequestMapping(value = "par_notification.web")
	public ModelAndView addNameInParNotification() {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_notification");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_notification.web", method = RequestMethod.POST)
	public ModelAndView displayStudentNotification(HttpServletRequest req, ModelMap map) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_notification");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		String name = req.getParameter("studentName");

		List<Tblinvites> tblinvites = parentService.getNotificationByStudentName(name);

		List<Mstteacherinvite> mste = parentService.getNotificationToStudentNameByTeacher(list, name);

		if (tblinvites.isEmpty() && mste.isEmpty()) {
			mav.addObject("noRecordErr", "true");
		} else {
			mav.addObject("tblinvites", tblinvites);
			mav.addObject("mste", mste);
		}
		// map.addAttribute("msg", "No New Notification");
		return mav;
		}
	}

	@RequestMapping(value = "downloadsNotification/{id}")
	public void Downloade(HttpServletResponse response,HttpServletRequest request, @PathVariable int id) throws IOException {
		System.out.println("inside notification");
		System.out.println(id);
		Tblinvites invObj = parentService.getNotificationById(id);
		/*res.setContentLength(file.getBlUploadFile().length);
		res.setContentType(file.getTxtcontentType());
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getTxtfileName() + "\"");
		FileCopyUtils.copy(file.getBlUploadFile(), out);
		out.flush();
		out.close();
		return;*/
		String dataDirectory = request.getServletContext().getRealPath("/assets/invites/");
		Path file = Paths.get(dataDirectory, invObj.getTxtfileName());
		if (Files.exists(file)) {
			response.setContentType(invObj.getTxtcontentType());
			response.addHeader("Content-Disposition", "attachment; filename=" + invObj.getTxtfileName());
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "par_fee details.web")
	public ModelAndView addNameInParFeeDetail(@ModelAttribute("studentFeeDetail") Mstfeemanagement fm) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_fee details");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_gps tarcking.web")
	public ModelAndView addNameInParGpsTracking() {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_gps tarcking");
		int id = (int) session.getAttribute("intRegId");
		System.out.println("reg id in session:"+id);
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_extra cirrcular.web")
	public String parExtraCirrcular(Model model) {
		return "par_extra cirrcular";
	}

	@RequestMapping(value = "par_event.web")
	public ModelAndView addNameInParEvent() {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_event");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_virtual lib.web")
	public ModelAndView addNameInParVirtualLibrary() {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_virtual lib");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_virtual lib1.web")
	public String parVirtualLib1(Model model) {
		return "par_virtual lib1";
	}

	@RequestMapping(value = "par_projects.web")
	public ModelAndView viewNameInDropDownOfProjectPage() {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_projects");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_projects.web", method = RequestMethod.POST)
	public ModelAndView viewProjectDetail(HttpServletRequest req) {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("par_projects");
		int id = (int) session.getAttribute("intRegId");
		List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
		List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
		mav.addObject("regal", mst);
		String name = req.getParameter("studentName");
		List<Mstteacherproject> project = parentService.getStudentProjectByName(list, name);
		if (project.isEmpty()) {
			mav.addObject("noRecordErr", "true");
		} else {
			mav.addObject("project", project);
		}

		return mav;
		}
	}

	/*
	 * @RequestMapping(value = "par_question & answer.web") public String
	 * parQuestion(Model model) { return "par_question & answer"; }
	 */

	/*
	 * @RequestMapping(value = "par_question & answer.web") public String
	 * addNameInParEvent(HttpServletRequest req,ModelMap map) { Tblparentqueries
	 * tblparentqueries = new Tblparentqueries(); String txtQuestionBox =
	 * req.getParameter("writequestion");
	 * 
	 * tblparentqueries.setTxtQuestion(txtQuestionBox);
	 * parentService.addQuestion(tblparentqueries);
	 * 
	 * List<Tblparentqueries> question =
	 * parentService.getListOfQuestionsAskedByParent();
	 * map.addAttribute("question", question); map.addAttribute("msg",
	 * "Question has been sent to Admin");
	 * 
	 * return "par_question & answer"; }
	 */

	@RequestMapping("par_question&answer.web")
	public ModelAndView studentquestionandanswer(HttpServletRequest req) {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
			ModelAndView mav = new ModelAndView("par_question&answer");
		
		int id = (int) session.getAttribute("intRegId");
		String txtParentId = Integer.toString(id);
		List<Tblparentqueries> mst = (List<Tblparentqueries>) parentService
				.getListOfQuestionsAskedByParent(txtParentId);
		mav.addObject("mst", mst);
		return mav;
		}
	}

	@RequestMapping(value = "par_question&answer.web", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest req, RedirectAttributes redAtt) throws ParseException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		int id = (int) session.getAttribute("intRegId");
		String txtParentId = Integer.toString(id);
		System.out.println("parent id  +" + txtParentId);
		Tblparentqueries mq = new Tblparentqueries();
		String quetion = req.getParameter("writequestion");
		mq.setTxtQuestion(quetion);
		mq.setTxtStatus(0);
		// mq.setTxtParentId("");
		mq.setTxtParentId(txtParentId);
		parentService.addQuestion(mq);
		redAtt.addFlashAttribute("msg", "Question successfully Added");
		return new ModelAndView("redirect:/par_question&answer.web");
		}
	}
	
	
	
	
	//Syllabus
	
		@RequestMapping("par_syllabus.web")
		public ModelAndView studentsyllabus1(@ModelAttribute("parent") Mstsyllabus student)
		{
			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
			ModelAndView mav = new ModelAndView("par_syllabus");
			int id = (int) session.getAttribute("intRegId");
			List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
			List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
			mav.addObject("regal", mst);
			return mav;
			}
			}

		@RequestMapping(value="par_syllabus.web",method=RequestMethod.POST)
		public ModelAndView studentsyllabus(@ModelAttribute("parent") Mstsyllabus parent,HttpServletRequest req)
		{
			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
				ModelAndView mav = new ModelAndView("par_syllabus");
				int id = (int) session.getAttribute("intRegId");
				List<Mstlogin> list = loginService.getStudentInfoByParentId(id);
				List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
				mav.addObject("regal", mst);
			
			int sid = Integer.parseInt(req.getParameter("studentName"));
			
			Mstregistration regObj = parentService.getClassSection(sid);
			
			int classId = regObj.getMststudclass().getIntClassId();
			
			ArrayList<Mstsyllabus> parentSyllabus = Studentservice.getStudentSyllabus(classId);
			
			mav.addObject("parentSyllabus", parentSyllabus);
			return mav;
			}
		}
		@RequestMapping(value="ajax_par_syllabus.web")
		public ModelAndView ajxStudentsyllabus(@ModelAttribute("parent") Mstsyllabus parent,HttpServletRequest req)
		{
			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
				ModelAndView mav = new ModelAndView("ajax_par_syllabus");
				int intRegId = (int) session.getAttribute("intRegId");
				List<Mstlogin> list = loginService.getStudentInfoByParentId(intRegId);
				List<Mstregistration> mst = (List<Mstregistration>) parentService.getStudentInformationByName(list);
				mav.addObject("regal", mst);
				int sid = Integer.parseInt(req.getParameter("val1"));
			
			Mstregistration regObj = parentService.getClassSection(sid);
			
			int classId = regObj.getMststudclass().getIntClassId();
			
			//ArrayList<Mstsyllabus> parentSyllabus = parentService.getParentSyllabus(classId);
			ArrayList<Mstsyllabus> studentSyllabus = Studentservice.getStudentSyllabus(classId);
			mav.addObject("parentSyllabus", studentSyllabus);
			return mav;
			}
		}
		
		@RequestMapping(value = "downloadess/{id}")
		public void Downloadess(HttpServletResponse res, @PathVariable Integer id) throws IOException {
			Mstsyllabus file = Studentservice.getStudentsSyllabus(id);
			res.setContentLength(file.getBlAttachment().length);
			res.setContentType(file.getTxtContentType());
			OutputStream out = res.getOutputStream();
			res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getTxtFileName() + "\"");
			FileCopyUtils.copy(file.getBlAttachment(), out);
			
			out.flush();
			out.close();
			return;
		}
		

}
