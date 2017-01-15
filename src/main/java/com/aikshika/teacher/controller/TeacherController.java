package com.aikshika.teacher.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aikshika.admin.service.AdminService;
import com.aikshika.entity.Mstleave;
import com.aikshika.entity.Mstmaritalstatus;
import com.aikshika.entity.Mstquestionandanswer;
import com.aikshika.entity.Mstregistration;
import com.aikshika.entity.Mstreligion;
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
import com.aikshika.student.service.StudentService;
import com.aikshika.teacher.service.TeacherService;

@Controller
public class TeacherController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public TeacherService teacherService;

	@Autowired
	private HttpSession session;

	@Autowired
	public AdminService adminService;

	@Autowired
	StudentService Studentservice;

	private Logger logger = Logger.getLogger(TeacherController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}

	@RequestMapping(value = "ad_index_For_Teacher.web")
	public ModelAndView adIndex(Model model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			// return "teacherDashboard";

			ModelAndView mav = new ModelAndView("teacherDashboard");
			int intRegId = (int) session.getAttribute("intRegId");

			List<Mstregistration> mst = (List<Mstregistration>) Studentservice.getParentInformationByName(intRegId);
			mav.addObject("mst", mst);

			return mav;
		}

	}

	@RequestMapping(value = "teacher_profile.web", method = RequestMethod.POST)
	public String saveImage(@RequestParam CommonsMultipartFile blAttachment) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			Mstregistration reg = new Mstregistration();
			int intRegId = (int) session.getAttribute("intRegId");
			byte[] byteArr = blAttachment.getBytes();

			Studentservice.saveImage(byteArr, intRegId);
			return "redirect:/ad_index_For_Teacher.web";
		}
	}

	@RequestMapping(value = "/getUserImagee/{id}.web")
	public void getUserImage(HttpServletResponse response, @PathVariable("id") int tweetID) throws IOException {

		response.setContentType("image/jpeg");
		int id = (int) session.getAttribute("intRegId");
		List<Mstregistration> list = Studentservice.getParentInformationByName(id);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Mstregistration mstregistration = (Mstregistration) iterator.next();
			if (mstregistration.getIntRegistrationId() == tweetID) {
				byte[] buffer = mstregistration.getBlImage();
				InputStream in1 = new ByteArrayInputStream(buffer);
				IOUtils.copy(in1, response.getOutputStream());
			}
		}

	}

	@RequestMapping(value = "ajax_teacher.web")
	public ModelAndView ajaxCall(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ajax_for_teacher");
			String cid = req.getParameter("val1");
			String sid = req.getParameter("val2");
			System.out.println("class id " + cid);
			System.out.println("section id " + sid);
			int ClassId = Integer.parseInt(cid);
			int SectionId = Integer.parseInt(sid);
			ArrayList<Mstregistration> mstal = (ArrayList<Mstregistration>) adminService.getAllStudentLists(ClassId,
					SectionId, "");
			m.addObject("students", mstal);
			System.out.println("inside teacher");
			return m;
		}
	}

	@RequestMapping(value = "ajax_teacher_marks.web")
	public ModelAndView ajaxCallMarks(HttpServletRequest req) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			ModelAndView m = new ModelAndView("ajax_for_teacherMarks");
			String cid = req.getParameter("val1");
			String sid = req.getParameter("val2");
			System.out.println("class id " + cid);
			System.out.println("section id " + sid);
			int ClassId = Integer.parseInt(cid);
			int SectionId = Integer.parseInt(sid);
			ArrayList<Mstregistration> mstal = (ArrayList<Mstregistration>) adminService.getAllStudentLists(ClassId,
					SectionId, "");
			m.addObject("students", mstal);
			System.out.println("inside teacher");
			return m;
		}
	}
	/*
	 * private PieDataset createDataSet() { DefaultPieDataset dpd = new
	 * DefaultPieDataset(); dpd.setValue("Mac", 21); dpd.setValue("Linux", 30);
	 * dpd.setValue("Window", 40); dpd.setValue("Others", 9); return dpd; }
	 * 
	 * private JFreeChart createChart(PieDataset pdSet, String chartTitle) {
	 * 
	 * JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, pdSet, true,
	 * true, false); PiePlot3D plot = (PiePlot3D) chart.getPlot();
	 * plot.setStartAngle(290); plot.setDirection(Rotation.CLOCKWISE);
	 * plot.setForegroundAlpha(0.5f); return chart; }
	 */

	public JFreeChart getChart() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Present", 65);
		dataset.setValue("Absent", 35);
		JFreeChart chart = ChartFactory.createPieChart("Attendance", dataset, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSectionPaint("Present", Color.GREEN);
		plot.setSectionPaint("Absent", Color.RED);

		Font font3 = new Font("Present", Font.PLAIN, 20);
		plot.setLabelFont(font3);
		plot.setLabelFont(font3);

		// dataset.setValue("Yugo", 44.2);

		/*
		 * boolean legend = true; boolean tooltips = false; boolean urls =
		 * false;
		 */

		// JFreeChart chart = ChartFactory.createPieChart("Attendance", dataset,
		// legend, tooltips, urls);

		// chart.setBorderPaint(Color.BLACK);
		// chart.setBorderStroke(new BasicStroke(5.0f));
		// chart.setBorderVisible(true);

		return chart;
	}

	@RequestMapping(value = "pieChart.web")
	public void getPieChart(HttpServletResponse response) {
		// response.setContentType("image/png");
		// PieDataset pdSet = createDataSet();

		/*
		 * JFreeChart chart = createChart(pdSet, "My Pie Chart");
		 * 
		 * try { ChartUtilities.writeChartAsPNG(response.getOutputStream(),
		 * chart, 750, 400); response.getOutputStream().close(); } catch
		 * (IOException ex) { ex.printStackTrace(); }
		 */
		response.setContentType("image/png");
		try {
			// OutputStream outputStream = response.getOutputStream();

			JFreeChart chart = getChart();
			int width = 350;
			int height = 350;
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, width, height);
			// outputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// teacher profile page display
	@RequestMapping(value = "teac_profile.web")
	public String teacProfile(Model model, Mstregistration mstregistration) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {

			int id = (int) session.getAttribute("intRegId");
			System.out.println("inside teacher profile method");
			List<Mstregistration> profile = teacherService.Profile(id);
			System.out.println("joining date:" + profile.get(0).getDtJoiningDate());
			List<Mstreligion> religion = adminService.religion();
			ArrayList<Mstmaritalstatus> marital = (ArrayList<Mstmaritalstatus>) adminService.getMaritalStatus();
			model.addAttribute("religion", religion);
			model.addAttribute("marital", marital);
			model.addAttribute("teacherProfile", profile);
			return "teac_profile";
		}

	}

	// teacher profile page gets redisplayed with updated details
	@RequestMapping(value = "profileEdit.web")
	public String teacProfileEdit(ModelMap model, HttpServletRequest req, RedirectAttributes redirectAttributes)
			throws ParseException {
		System.out.println("inside teacher profile edit method");
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");

			Mstregistration mstregistration = new Mstregistration();
			mstregistration.setTxtFatherName(req.getParameter("txtFatherName"));
			mstregistration.setTxtFmobileNumber(req.getParameter("txtFmobileNumber"));
			mstregistration.setTxtEmailId(req.getParameter("txtEmailId"));
			mstregistration.setTxtCity(req.getParameter("txtCity"));

			mstregistration.setTxtPerAddress(req.getParameter("txtPerAddress"));
			mstregistration.setTxtTempAddress(req.getParameter("txtTempAddress"));
			mstregistration.setTxtDesignation(req.getParameter("txtDesignation"));
			mstregistration.setTxtExperience(req.getParameter("txtExperience"));
			Mstreligion mstreligion = new Mstreligion();
			mstreligion.setIntReligionId(Integer.parseInt(req.getParameter("mstreligion")));

			Mstmaritalstatus mstmaritalstatus = new Mstmaritalstatus();
			mstmaritalstatus.setIntMaritalStatusId(Integer.parseInt(req.getParameter("mstmaritalstatus")));

			mstregistration.setMstmaritalstatus(mstmaritalstatus);
			mstregistration.setMstreligion(mstreligion);
			mstregistration.setIntRegistrationId(id);
			teacherService.update(mstregistration);
			redirectAttributes.addFlashAttribute("msg1", "Success!");
			redirectAttributes.addFlashAttribute("msg2", " Details have been updated successfully");

			return "redirect:/teac_profile.web";
		}
	}

	// displays leave request page for the teacher
	@RequestMapping(value = "teac_leave request.web")
	public String teacLeaveRequest(ModelMap model) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			// int id = (int) session.getAttribute("intRegId");
			List<Mstleave> leaveTypes = teacherService.getAllLeaveTypes();
			model.addAttribute("leaveTypes", leaveTypes);
			return "teac_leave request";
		}
	}

	// displays leave request page after the leave is applied
	@RequestMapping(value = "teachPostLeaveRequest.web")
	public String applyLeave(ModelMap map, Trnteacherleaveapp trnteacherleaveapp, BindingResult result,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		System.out.println("errors; " + result.getFieldError());

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Mstleave mstleave = new Mstleave();
				mstleave.setIntLeaveId(Integer.parseInt(request.getParameter("leaveID")));
				java.sql.Date fromDate = new java.sql.Date(
						dateFormat.parse(request.getParameter("dtFromDate")).getTime());
				java.sql.Date toDate = new java.sql.Date(dateFormat.parse(request.getParameter("dtToDate")).getTime());
				if (fromDate.after(toDate)) {
					System.out.println(".......check");
					redirectAttributes.addFlashAttribute("dateMsg", "From date cannot be greater than To date");

					return "redirect:/teac_leave request.web";
				}
				trnteacherleaveapp.setMstleave(mstleave);
				trnteacherleaveapp.setDtFromDate(fromDate);
				trnteacherleaveapp.setDtToDate(toDate);
				Mstregistration mstreg = new Mstregistration();
				mstreg.setIntRegistrationId(id);
				trnteacherleaveapp.setMstregistration(mstreg);
				trnteacherleaveapp.setDtAppliedDate(new java.sql.Date(new Date().getTime()));
				trnteacherleaveapp.setIntStatus(1);
				teacherService.applyLeave(trnteacherleaveapp);
				redirectAttributes.addFlashAttribute("trnteacherleaveapp", new Trnteacherleaveapp());

				redirectAttributes.addFlashAttribute("msg1", "Your request for leave has been sent successfully");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "redirect:/teac_leave request.web";
		}
	}

	// displays leave inbox page
	@RequestMapping(value = "teac_leaveInbox.web")
	public String teacLeaveInbox(ModelMap model) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			List<Trnteacherleaveapp> leaveInbox = teacherService.getTeacherLeaveInbox(id);
			model.addAttribute("inbox", leaveInbox);
			return "teac_leaveInbox";
		}
	}

	// displays student leave inbox page
	@RequestMapping(value = "teac_StudentLeaveInbox.web")
	public String teacStudentLeaveInbox(ModelMap model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			List<Trnstudentleaveapp> studentLeaveRequest = teacherService.getStudentLeaveRequest(id);
			model.addAttribute("studentLeaveRequest", studentLeaveRequest);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -14);
			System.out.println("Date = " + cal.getTime());
			List<Trnstudentleaveapp> searchedLeaveReq = teacherService.getAllSearchedLeaveReq(id,
					new java.sql.Date(cal.getTime().getTime()), new java.sql.Date(new Date().getTime()));
			model.addAttribute("studentLeaveRequest1", searchedLeaveReq);
			model.addAttribute("check", "check");
			return "teac_StudentLeaveInbox";
		}
	}

	// for student leave approval
	@RequestMapping(value = "teac_studLeaveApprove.web")
	public String teacStudentLeaveApproval(ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id1 = (int) session.getAttribute("intRegId");
			int id = Integer.parseInt(request.getParameter("id"));
			teacherService.approveOrRejectStudentLeave(id, 2, new java.sql.Date(new Date().getTime()));
			redirectAttributes.addFlashAttribute("approve", "Leave request is approved");
			return "redirect:/teac_StudentLeaveInbox.web";
		}
	}

	// for student leave rejection
	@RequestMapping(value = "teac_studLeaveReject.web")
	public String teacStudentLeaveReject(ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id1 = (int) session.getAttribute("intRegId");
			int id = Integer.parseInt(request.getParameter("id"));
			teacherService.approveOrRejectStudentLeave(id, 3, new java.sql.Date(new Date().getTime()));
			return "redirect:/teac_StudentLeaveInbox.web";
		}
	}

	// leave request search
	@RequestMapping(value = "teac_leaveRequestSearch.web", method = RequestMethod.POST)
	public String teacleaveRequestSearch(ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			int status = Integer.parseInt(request.getParameter("Status"));

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				java.sql.Date fromDate = new java.sql.Date(
						dateFormat.parse(request.getParameter("FromDate")).getTime());
				java.sql.Date toDate = new java.sql.Date(dateFormat.parse(request.getParameter("ToDate")).getTime());
				if (status == 1) {
					List<Trnstudentleaveapp> searchedLeaveReq = teacherService.getAllSearchedLeaveReq(id, fromDate,
							toDate);
					model.addAttribute("studentLeaveRequest1", searchedLeaveReq);

					List<Trnstudentleaveapp> studentLeaveRequest = teacherService.getStudentLeaveRequest(id);
					model.addAttribute("studentLeaveRequest", studentLeaveRequest);
					model.addAttribute("check", "check");
				}
				if (status == 2 || status == 3) {
					List<Trnstudentleaveapp> searchedLeaveReq = teacherService.getSearchedLeaveReq(id, status, fromDate,
							toDate);
					model.addAttribute("studentLeaveRequest1", searchedLeaveReq);
					List<Trnstudentleaveapp> studentLeaveRequest = teacherService.getStudentLeaveRequest(id);
					model.addAttribute("studentLeaveRequest", studentLeaveRequest);
					model.addAttribute("check", "check");
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			String secondTab = request.getParameter("secondTab");
			model.addAttribute("secondTab", secondTab);
			return "teac_StudentLeaveInbox";
		}
	}

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * binder.registerCustomEditor(byte[].class, new
	 * ByteArrayMultipartFileEditor()); }
	 */

	// displays the page to upload marksheet of students
	@RequestMapping(value = "teac_marksheet.web")
	public String teacMarkSheet(ModelMap model, Mstuploadstudmarksheet mstuploadstudmarksheet) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			// int id = (int) session.getAttribute("intRegId");
			System.out.println("inside marksheet");
			List<Mststudclass> classes = teacherService.getAllClasses();
			List<Mststudclasssection> sections = teacherService.getAllSections();
			List<Mstterms> examTerms = teacherService.getExamTerms();
			List<Mstregistration> students = teacherService.getStudents();
			sections.remove(4);
			classes.remove(12);
			model.addAttribute("classes", classes);
			model.addAttribute("sections", sections);
			model.addAttribute("examTerms", examTerms);
			model.addAttribute("students", students);
			return "teac_marksheet";
		}
	}

	// displays the marksheet page after uploading the marksheet file
	@RequestMapping(value = "marksheetupload.web")
	public String teacMarkSheetUpload(Mstuploadstudmarksheet mstuploadstudmarksheet, BindingResult result,
			HttpServletRequest request, ModelMap model, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			String saveDirectory = "/teachMarkSheets";

			int id = (int) session.getAttribute("intRegId");
			System.out.println("after uploading............");
			System.out.println("filename:" + file.getOriginalFilename());
			System.out.println("size:" + file.getSize());
			System.out.println("file:" + file);
			System.out.println("file content type = " + file.getContentType());
			byte[] bytes = file.getBytes();
			Mststudclass mststudclass = new Mststudclass();
			mststudclass.setIntClassId(Integer.parseInt(request.getParameter("classId")));

			Mststudclasssection mststudclasssection = new Mststudclasssection();
			mststudclasssection.setIntSectionId(Integer.parseInt(request.getParameter("sectionId")));

			Mstterms mstterms = new Mstterms();
			mstterms.setIntTermId(Integer.parseInt(request.getParameter("intTermId")));

			Mstregistration mstregistration = new Mstregistration();
			mstregistration.setIntRegistrationId(Integer.parseInt(request.getParameter("student")));

			mstuploadstudmarksheet.setMststudclass(mststudclass);
			mstuploadstudmarksheet.setMststudclasssection(mststudclasssection);
			mstuploadstudmarksheet.setMstterms(mstterms);
			mstuploadstudmarksheet.setMstregistration(mstregistration);

			CommonsMultipartFile aFile = (CommonsMultipartFile) file;
			String oName = aFile.getOriginalFilename();
			String fileWithPath = null;
			if (!oName.equals("")) {
				fileWithPath = saveDirectory + aFile.getOriginalFilename();
				aFile.transferTo(new File(fileWithPath));
			}

			mstuploadstudmarksheet.setTxtfileName(file.getOriginalFilename());
			mstuploadstudmarksheet.setTxtcontentType(file.getContentType());
			mstuploadstudmarksheet.setTxtPath(fileWithPath);
			mstuploadstudmarksheet.setDtDate(new Date());
			mstuploadstudmarksheet.setIntTeacherId(id);
			teacherService.uploadStudentMarksheet(mstuploadstudmarksheet);
			redirectAttributes.addFlashAttribute("msg", "Marksheet has been uploaded successfully....");
			return "redirect:/teac_marksheet.web";
		}
	}

	// this method is used to search the marksheet according to exam
	@RequestMapping(value = "searchMarksheet.web")
	public String searchMarksheet(ModelMap model, HttpServletRequest request,
			Mstuploadstudmarksheet mstuploadstudmarksheet, RedirectAttributes redirectAttributes) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			System.out.println("Id:" + Integer.parseInt(request.getParameter("intTermId")));
			int classId = Integer.parseInt(request.getParameter("classId"));
			int sectionId = Integer.parseInt(request.getParameter("sectionId"));
			int examId = Integer.parseInt(request.getParameter("intTermId"));
			List<Mstuploadstudmarksheet> studentMarksheet = teacherService.getStudentMarksheet(classId, sectionId,
					examId, id);
			redirectAttributes.addFlashAttribute("studentMarksheet", studentMarksheet);
			redirectAttributes.addFlashAttribute("check", "check");
			return "redirect:/teac_marksheet.web";
		}
	}

	// marksheet download
	@RequestMapping(value = "marksheetViewOrDownload.web")
	public void marksheetDownload(HttpServletRequest req, HttpServletResponse res, @RequestParam Integer id)
			throws IOException {
		Mstuploadstudmarksheet marksObj = teacherService.getMarksheet(id);
		String dataDirectory = req.getServletContext().getRealPath("/assets/teachMarkSheets/");
		Path file = Paths.get(dataDirectory, marksObj.getTxtfileName());
		if (Files.exists(file)) {
			res.setContentType(marksObj.getTxtcontentType());
			res.addHeader("Content-Disposition", "attachment; filename=" + marksObj.getTxtfileName());
			try {
				Files.copy(file, res.getOutputStream());
				res.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	// notification file download
	@RequestMapping(value = "notificationFileDownload.web")
	public void notificationFileDownload(HttpServletResponse response, HttpServletRequest request, @RequestParam int id)
			throws IOException {

		Tblinvites invObj = teacherService.getNotificationFile(id);

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

	@RequestMapping(value = "teac_notification.web")
	public String teacNotification(ModelMap model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			// int id = (int) session.getAttribute("intRegId");
			List<Tblinvites> teacherNotification = teacherService.getTeacherNotification(3);
			model.addAttribute("notification", teacherNotification);
			return "teac_notification";
		}
	}

	// display project page to post new project
	@RequestMapping(value = "teac_project.web")
	public String teacProject(ModelMap model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			List<Mststudclass> classes = teacherService.getAllClasses();
			List<Mststudclasssection> sections = teacherService.getAllSections();
			model.addAttribute("mstteacherproject", new Mstteacherproject());
			List<Mstregistration> students = teacherService.getStudents();
			sections.remove(4);
			classes.remove(12);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -14);
			System.out.println("Date = " + cal.getTime());
			model.addAttribute("classes", classes);
			model.addAttribute("sections", sections);
			model.addAttribute("students", students);

			List<Mstteacherproject> projectHistory = teacherService.getProjectHistory(id,
					new java.sql.Date(cal.getTime().getTime()), new java.sql.Date(new Date().getTime()));
			model.addAttribute("projectHistory", projectHistory);
			model.addAttribute("history", "history display");

			return "teac_project";
		}
	}

	// project page display after the project is posted
	@RequestMapping(value = "teac_projectPosted.web")
	public String teachProjectPost(ModelMap map, Mstteacherproject mstteacherproject, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			Mststudclass mststudclass = new Mststudclass();
			Mststudclasssection mststudclasssection = new Mststudclasssection();
			mststudclasssection.setIntSectionId(Integer.parseInt(request.getParameter("sectionId")));
			mststudclass.setIntClassId(Integer.parseInt(request.getParameter("classId")));

			java.sql.Date dueDate = new java.sql.Date(mstteacherproject.getDtDueDate().getTime());
			java.sql.Date assignDate = new java.sql.Date(new Date().getTime());
			mstteacherproject.setDtAssignDate(assignDate);
			mstteacherproject.setDtDueDate(dueDate);
			mstteacherproject.setIntTeacherId(id);
			mstteacherproject.setMststudclass(mststudclass);
			mstteacherproject.setMststudclasssection(mststudclasssection);
			if (request.getParameterValues("select") != null) {
				String[] values = request.getParameterValues("select");
				System.out.println("select length:" + values.length);
				System.out.println("inside if..........");
				int[] array = Stream.of(values).mapToInt(Integer::parseInt).toArray();
				for (Integer i : array) {
					Mstregistration mstregistration = new Mstregistration();
					mstregistration.setIntRegistrationId(i);
					mstteacherproject.setMstregistration(mstregistration);
					teacherService.projectPost(mstteacherproject);
					System.out.println("project for particular students......");
				}

			} else {
				teacherService.projectPost(mstteacherproject);
			}

			redirectAttributes.addFlashAttribute("msg1", "Success!");
			redirectAttributes.addFlashAttribute("msg2", "  project is posted successfully");
			return "redirect:/teac_project.web";
		}
	}

	// display the posted project's for the searched date
	@RequestMapping(value = "teachProjectHistory.web")
	public String teachProjectHistory(ModelMap map, HttpServletRequest request, Mstteacherproject mstteacherproject,
			RedirectAttributes redirectAttributes) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {

				java.sql.Date fromDate = new java.sql.Date(
						dateFormat.parse(request.getParameter("fromDate")).getTime());
				java.sql.Date toDate = new java.sql.Date(dateFormat.parse(request.getParameter("toDate")).getTime());

				System.out.println("after conversion");
				System.out.println("from date:" + fromDate);
				System.out.println("To date:" + toDate);
				if (fromDate.after(toDate)) {
					System.out.println(".......check");
					map.addAttribute("dateMsg", "From date cannot be greater than To date");
					return "teac_project";
				}
				List<Mstteacherproject> projectHistory = teacherService.getProjectHistory(id, fromDate, toDate);
				map.addAttribute("projectHistory", projectHistory);

				map.addAttribute("history", "history display");
				List<Mststudclass> classes = teacherService.getAllClasses();
				List<Mststudclasssection> sections = teacherService.getAllSections();
				List<Mstregistration> students = teacherService.getStudents();
				sections.remove(4);
				classes.remove(12);
				map.addAttribute("classes", classes);
				map.addAttribute("sections", sections);
				map.addAttribute("students", students);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "teac_project";
		}
	}

	// display the question & answer page
	@RequestMapping(value = "teac_question & answer.web")
	public String teacQuestionAnswer(ModelMap model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			List<Mstquestionandanswer> questions = teacherService.getStudentQuestions();
			model.addAttribute("questions", questions);
			List<Mstquestionandanswer> answeredQuestion = teacherService.getAnsweredQuestion(id);
			model.addAttribute("answeredQuestion", answeredQuestion);
			return "teac_question & answer";
		}
	}

	// method to post the answer for the question
	@RequestMapping(value = "teac_answer.web")
	public String teacAnswer(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			int questionId = Integer.parseInt(request.getParameter("id"));
			String answer = request.getParameter("answer");
			System.out.println("question Id:" + questionId);
			System.out.println("answer:" + answer);
			teacherService.postAnswer(questionId, answer, id);

			redirectAttributes.addFlashAttribute("msg", "Your answer is posted successfully");
			return "redirect:/teac_question & answer.web";
		}
	}

	// displays the page to invite the class
	@RequestMapping(value = "teac_send invites.web")
	public String teacSendInvites(ModelMap model) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			List<Mststudclass> classes = teacherService.getAllClasses();
			List<Mststudclasssection> sections = teacherService.getAllSections();
			List<Mstregistration> students = teacherService.getStudents();
			model.addAttribute("students", students);
			sections.remove(4);
			classes.remove(12);
			model.addAttribute("mstteacherinvite", new Mstteacherinvite());
			model.addAttribute("classes", classes);
			model.addAttribute("sections", sections);
			List<Mstteacherinvite> invites = teacherService.getPostedInvites(id);
			model.addAttribute("invites", invites);
			return "teac_send invites";
		}
	}

	// displays the page after the invite is sent
	@RequestMapping(value = "teac_InvitePost.web")
	public String teacInvitesPosted(ModelMap model, Mstteacherinvite mstteacherinvite, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			Mststudclass mststudclass = new Mststudclass();
			Mststudclasssection mststudclasssection = new Mststudclasssection();
			mststudclass.setIntClassId(Integer.parseInt(request.getParameter("classId")));
			mststudclasssection.setIntSectionId(Integer.parseInt(request.getParameter("sectionId")));
			mstteacherinvite.setIntTeacherId(id);
			mstteacherinvite.setMststudclass(mststudclass);
			mstteacherinvite.setMststudclasssection(mststudclasssection);
			mstteacherinvite.setDtInvitesDate(new Date());
			mstteacherinvite.setIntStatus(1);

			if (request.getParameterValues("select") != null) {
				String[] values = request.getParameterValues("select");
				System.out.println("select length:" + values.length);
				System.out.println("inside if..........");
				int[] array = Stream.of(values).mapToInt(Integer::parseInt).toArray();
				for (Integer i : array) {
					Mstregistration mstregistration = new Mstregistration();
					mstregistration.setIntRegistrationId(i);
					mstteacherinvite.setMstregistration(mstregistration);
					teacherService.postTeacherInvite(mstteacherinvite);
				}

			} else {
				teacherService.postTeacherInvite(mstteacherinvite);
			}
			redirectAttributes.addFlashAttribute("invite", "Your Invite request has been sent successfully");
			return "redirect:/teac_send invites.web";
		}
	}

	// display the posted invite's for the searched dates
	@RequestMapping(value = "teachInviteHistory.web")
	public String teacherInviteHistory(ModelMap map, HttpServletRequest request, Mstteacherinvite mstteacherinvite,
			RedirectAttributes redirectAttributes) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				java.sql.Date fromDate = new java.sql.Date(
						dateFormat.parse(request.getParameter("fromDate")).getTime());
				java.sql.Date toDate = new java.sql.Date(dateFormat.parse(request.getParameter("toDate")).getTime());
				System.out.println("after conversion");
				System.out.println("from date:" + fromDate);
				System.out.println("To date:" + toDate);
				if (fromDate.after(toDate)) {
					System.out.println(".......check");
					map.addAttribute("dateMsg", "From date cannot be greater than To date");
					return "teac_send invites";
				}
				List<Mstteacherinvite> inviteHistory = teacherService.getInviteHistory(id, fromDate, toDate);
				redirectAttributes.addFlashAttribute("invites", inviteHistory);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return "redirect:/teac_send invites.web";
		}
	}

	// delete the invite sent by teacher
	@RequestMapping(value = "deleteTeacherInvite/{id}.web", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Integer id, RedirectAttributes re) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			teacherService.deleteTeacherInvite(id);
			re.addFlashAttribute("Delmsg", "Invitation");
			re.addFlashAttribute("Delmsg1", "has been deleted successfully");
			return "redirect:/teac_send invites.web";
		}
	}

	// display the timetable page of teacher
	@RequestMapping(value = "teac_timetable.web")
	public String teacTimetable(ModelMap map) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			List<Mstteachertimetable> timetable = teacherService.getTimetable(id);
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			System.out.println(new java.sql.Date(new Date().getTime()));

			List<Mstteachercheckin> classCheckInTimeHistory = teacherService.getClassCheckInTimeHistory(id, date, date);
			map.addAttribute("timetable", timetable);
			map.addAttribute("checkin", classCheckInTimeHistory);
			map.addAttribute("checkInHistory", "Yes");

			return "teac_timetable";
		}
	}

	// displays the timetable page after teacher has checked-in into particular
	// class
	@RequestMapping(value = "teac_checkIn.web")
	public ModelAndView teacCheckIn(ModelMap map, @RequestParam("id") int intTeacherTimeTableId,
			RedirectAttributes redAtt) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return new ModelAndView("redirect:/login.web");
		} else {
			int id = (int) session.getAttribute("intRegId");
			System.out.println("id......." + intTeacherTimeTableId);
			Mstteachertimetable mstteachertimetable = new Mstteachertimetable();
			mstteachertimetable.setIntTeacherTimeTableId(intTeacherTimeTableId);
			Mstteachercheckin mstteachercheckin = new Mstteachercheckin();
			mstteachercheckin.setMstteachertimetable(mstteachertimetable);
			mstteachercheckin.setDtCheckInDate(new Date());
			LocalTime localTime = LocalTime.now();
			String time = localTime.toString();
			String t = time.substring(0, 5);
			// SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			System.out.println("time:" + time);
			mstteachercheckin.setDtCheckInTime(t);
			teacherService.recordCheckInTime(mstteachercheckin);

			redAtt.addFlashAttribute("msg", "Your request for class Check-In time has been marked");

			return new ModelAndView("redirect:/teac_timetable.web");
		}
	}

	// displays the class check-in history of teacher
	@RequestMapping(value = "teachCheckInHistory.web")
	public String teacCheckInHistory(ModelMap map, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				java.sql.Date fromDate = new java.sql.Date(
						dateFormat.parse(request.getParameter("fromDate")).getTime());
				java.sql.Date toDate = new java.sql.Date(dateFormat.parse(request.getParameter("toDate")).getTime());
				if (fromDate.after(toDate)) {
					System.out.println(".......check");
					redirectAttributes.addFlashAttribute("dateMsg", "From date cannot be greater than To date");
					return "redirect:/teac_timetable.web";
				}
				List<Mstteachercheckin> classCheckInTimeHistory = teacherService.getClassCheckInTimeHistory(1, fromDate,
						toDate);
				map.addAttribute("checkin", classCheckInTimeHistory);
				
				 List<Mstteachertimetable> timetable =
				 teacherService.getTimetable(id);
				 map.addAttribute("timetable", timetable);
				 map.addAttribute("checkInHistory", "Yes");
				 
			} catch (ParseException e) {
				e.printStackTrace();
			}

			return "teac_timetable";
		}
	}

	// homework page display to post the homework
	@RequestMapping(value = "teac_homework.web")
	public String teacHomework(ModelMap map) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			List<Mststudclass> classes = teacherService.getAllClasses();
			List<Mststudclasssection> sections = teacherService.getAllSections();
			List<Mstregistration> students = teacherService.getStudents();
			map.addAttribute("tblhomework", new Tblhomework());
			sections.remove(4);
			classes.remove(12);

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			System.out.println("Date = " + cal.getTime());

			List<Tblhomework> homeworkHistory = teacherService.getHomeworkHistory(id,
					new java.sql.Date(cal.getTime().getTime()), new java.sql.Date(new Date().getTime()));
			map.addAttribute("homeworkHistory", homeworkHistory);
			map.addAttribute("history", "history display");

			map.addAttribute("classes", classes);
			map.addAttribute("sections", sections);
			map.addAttribute("students", students);
			return "teac_homework";
		}
	}

	// homework page display after the homework is posted
	@RequestMapping(value = "teac_homeworkPosted.web")
	public String teachHomeworkPost(ModelMap map,  Tblhomework tblhomework, BindingResult result,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			System.out.println("inside..................homework");
			if (result.hasErrors()) {
				System.out.println(result.hasFieldErrors());
				System.out.println(result.getFieldErrorCount());
				System.out.println(result.getFieldError());

			}
			Mststudclass mststudclass = new Mststudclass();
			Mststudclasssection mststudclasssection = new Mststudclasssection();
			mststudclasssection.setIntSectionId(Integer.parseInt(request.getParameter("sectionId")));
			mststudclass.setIntClassId(Integer.parseInt(request.getParameter("classId")));

			java.sql.Date dueDate = new java.sql.Date(tblhomework.getDtDueDate().getTime());
			java.sql.Date assignDate = new java.sql.Date(new Date().getTime());
			tblhomework.setDtAssignDate(assignDate);
			tblhomework.setDtDueDate(dueDate);
			tblhomework.setIntTeacherId(id);
			tblhomework.setMststudclass(mststudclass);
			tblhomework.setMststudclasssection(mststudclasssection);
			if (request.getParameterValues("select") != null) {
				String[] values = request.getParameterValues("select");
				System.out.println("select length:" + values.length);
				System.out.println("inside if..........");
				int[] array = Stream.of(values).mapToInt(Integer::parseInt).toArray();
				for (Integer i : array) {
					Mstregistration mstregistration = new Mstregistration();
					mstregistration.setIntRegistrationId(i);
					tblhomework.setMstregistration(mstregistration);
					teacherService.homeworkPost(tblhomework);
					System.out.println("homework for particular students......");
				}

			} else {
				teacherService.homeworkPost(tblhomework);
			}
			redirectAttributes.addFlashAttribute("msg1", "Success!");
			redirectAttributes.addFlashAttribute("msg2", " Homework is posted successfully");
			return "redirect:/teac_homework.web";
		}
	}

	// display the posted homework's for the searched dates
	@RequestMapping(value = "teachHomeworkHistory.web")
	public String teachHomeworkHistory(ModelMap map, HttpServletRequest request, Tblhomework tblhomework,
			RedirectAttributes redirectAttributes) {
		if (session.getAttribute("intRegId") == null || session.getAttribute("intRegId") == "") {
			return "redirect:/login.web";
		} else {
			int id = (int) session.getAttribute("intRegId");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				java.sql.Date fromDate = new java.sql.Date(
						dateFormat.parse(request.getParameter("fromDate")).getTime());
				java.sql.Date toDate = new java.sql.Date(dateFormat.parse(request.getParameter("toDate")).getTime());
				System.out.println("after conversion");
				System.out.println("from date:" + fromDate);
				System.out.println("To date:" + toDate);
				if (fromDate.after(toDate)) {
					System.out.println(".......check");
					map.addAttribute("dateMsg", "From date cannot be greater than To date");
					return "teac_homework";
				}
				List<Tblhomework> homeworkHistory = teacherService.getHomeworkHistory(id, fromDate, toDate);
				map.addAttribute("homeworkHistory", homeworkHistory);
				map.addAttribute("history", "history display");
				List<Mststudclass> classes = teacherService.getAllClasses();
				List<Mststudclasssection> sections = teacherService.getAllSections();
				List<Mstregistration> students = teacherService.getStudents();
				sections.remove(4);
				map.addAttribute("classes", classes);
				map.addAttribute("sections", sections);
				map.addAttribute("students", students);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			return "teac_homework";
		}
	}
}
