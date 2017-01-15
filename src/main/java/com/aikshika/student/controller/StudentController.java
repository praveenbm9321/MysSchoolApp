package com.aikshika.student.controller;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
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
import com.aikshika.entity.Trnattendancerecord;
import com.aikshika.student.service.StudentService;



@Controller
public class StudentController {
	
	private Logger logger = Logger.getLogger(StudentController.class);
	
	
	@Autowired
	private StudentService Studentservice;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("ad_index_For_Student.web")
	public ModelAndView studentprofileIndex() 
	{
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("studentDashboard");
int intRegId =  (int) session.getAttribute("intRegId");
		
		List<Mstregistration> mst = (List<Mstregistration>)Studentservice.getParentInformationByName(intRegId);
		mav.addObject("mst",mst);
		

		return mav;
		}
	}
	@RequestMapping(value = "ajax_stu_project_by_default.web")
	public ModelAndView studentproject2(HttpServletRequest req, ModelMap map) throws ParseException {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		System.out.println("INSIDE PROJECT DEFAULT");
		//ArrayList<String> teachNamesListHW = new ArrayList<String>();
		ArrayList<String> teachNamesListProj = new ArrayList<String>();

		ModelAndView mav = new ModelAndView("ajax_stu_project");
		//ArrayList<Tblhomework> hwList = new ArrayList<Tblhomework>();
		ArrayList<Mstteacherproject> hwListe = new ArrayList<Mstteacherproject>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*String aDate = req.getParameter("val1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date aDt = sdf.parse(aDate);
		
		java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
		if (aDt.after(appliedDate)) {
			map.addAttribute("msg2", "Date Can't Be Greater Than Today's Date");
		}

		hwListe = (ArrayList<Mstteacherproject>) Studentservice.getProject(aDt);

		if (hwListe.size() > 0) {
			Iterator<Mstteacherproject> itr2 = hwListe.iterator();
			while (itr2.hasNext()) {
				Mstteacherproject qq = (Mstteacherproject) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListProj.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecords", "noWeekRecords");
		}
*/
		String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SimpleDateFormat sdfwww = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date curDt = sdfwww.parse(sdfw);

		Calendar now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, -1);
		System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
				+ "-" + now.get(Calendar.DATE));
		String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
				+ now.get(Calendar.DATE);
		java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

		// int id = 1;
		int intRegId = (int) session.getAttribute("intRegId");
		Mstregistration reg=Studentservice.getClass(intRegId);
		int classId=reg.getMststudclass().getIntClassId();
		int sectionId = reg.getMststudclasssection().getIntSectionId();
  hwListe = (ArrayList<Mstteacherproject>) Studentservice
				.getProFromTo(new java.sql.Date(oneWeekBefore.getTime()), new java.sql.Date(curDt.getTime()), intRegId,classId,sectionId);

		if (hwListe.size() > 0) {
			Iterator<Mstteacherproject> itr2 = hwListe.iterator();
			while (itr2.hasNext()) {
				Mstteacherproject qq = (Mstteacherproject) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListProj.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecords", "noWeekRecords");
		}

		mav.addObject("hwListe", hwListe);
		//mav.addObject("teachNamesListHW", teachNamesListHW);
		mav.addObject("teachNamesListProj", teachNamesListProj);
		//mav.addObject("hwList", hwList);
		mav.addObject("hwListe", hwListe);
		//mav.addObject("teachNamesListHW", teachNamesListHW);
		mav.addObject("teachNamesListProj", teachNamesListProj);
		return mav;
		}

	}
	
	
	@RequestMapping(value = "ajax_stu_task_by_default.web")
	public ModelAndView studenthomework(HttpServletRequest req, ModelMap map) throws ParseException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		
		System.out.println("INSIDE HOMEWORK DEFAULT");
		ArrayList<String> teachNamesListHW = new ArrayList<String>();
		//ArrayList<String> teachNamesListProj = new ArrayList<String>();

		ModelAndView mav = new ModelAndView("ajax_stu_task");
		ArrayList<Tblhomework> hwList = new ArrayList<Tblhomework>();
		//ArrayList<Mstteacherproject> hwListe = new ArrayList<Mstteacherproject>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*String aDate = req.getParameter("val1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date aDt = sdf.parse(aDate);
		
		java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
		if (aDt.after(appliedDate)) {
			map.addAttribute("msg2", "Date Can't Be Greater Than Today's Date");
		}

		hwListe = (ArrayList<Mstteacherproject>) Studentservice.getProject(aDt);

		if (hwListe.size() > 0) {
			Iterator<Mstteacherproject> itr2 = hwListe.iterator();
			while (itr2.hasNext()) {
				Mstteacherproject qq = (Mstteacherproject) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListProj.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecords", "noWeekRecords");
		}
*/
		String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SimpleDateFormat sdfwww = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date curDt = sdfwww.parse(sdfw);

		Calendar now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, -1);
		System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
				+ "-" + now.get(Calendar.DATE));
		String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
				+ now.get(Calendar.DATE);
		java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

		// int id = 1;
		int id = (int) session.getAttribute("intRegId");
		Mstregistration reg=Studentservice.getClass(id);
		int classId=reg.getMststudclass().getIntClassId();
		int sectionId = reg.getMststudclasssection().getIntSectionId();
		hwList = (ArrayList<Tblhomework>) Studentservice.getHWFromTo(new java.sql.Date(oneWeekBefore.getTime()),
				new java.sql.Date(curDt.getTime()), id,classId,sectionId);

		if (hwList.size() > 0) {
			Iterator<Tblhomework> itr2 = hwList.iterator();
			while (itr2.hasNext()) {
				Tblhomework qq = (Tblhomework) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListHW.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecordsss", "noWeekRecordsss");
		}

		mav.addObject("hwList", hwList);
		//mav.addObject("hwListe", hwListe);
		mav.addObject("teachNamesListHW", teachNamesListHW);
		//mav.addObject("teachNamesListProj", teachNamesListProj);
		return mav;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "ajax_stu_project.web")
	public ModelAndView studentproject1(HttpServletRequest req, ModelMap map) throws ParseException {

		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ArrayList<String> teachNamesListHW = new ArrayList<String>();
		ArrayList<String> teachNamesListProj = new ArrayList<String>();

		ModelAndView mav = new ModelAndView("ajax_stu_project");
		String aDate = req.getParameter("val1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date aDt = sdf.parse(aDate);
		ArrayList<Tblhomework> hwList = new ArrayList<Tblhomework>();
		ArrayList<Mstteacherproject> hwListe = new ArrayList<Mstteacherproject>();
		java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
		if (aDt.after(appliedDate)) {
			map.addAttribute("msg2", "Date Can't Be Greater Than Today's Date");
		}

		int id = (int) session.getAttribute("intRegId");
		Mstregistration reg=Studentservice.getClass(id);
		int classId=reg.getMststudclass().getIntClassId();
		int sectionId = reg.getMststudclasssection().getIntSectionId();
		hwListe = (ArrayList<Mstteacherproject>) Studentservice.getProject(aDt,id,classId,sectionId);

		if (hwListe.size() > 0) {
			Iterator<Mstteacherproject> itr2 = hwListe.iterator();
			while (itr2.hasNext()) {
				Mstteacherproject qq = (Mstteacherproject) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListProj.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecords", "noWeekRecords");
		}

		String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SimpleDateFormat sdfwww = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date curDt = sdfwww.parse(sdfw);

		Calendar now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, -1);
		System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
				+ "-" + now.get(Calendar.DATE));
		String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
				+ now.get(Calendar.DATE);
		java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

		// int id = 1;
		/*int id = (int) session.getAttribute("intRegId");
		Mstregistration reg=Studentservice.getClass(id);
		int classId=reg.getMststudclass().getIntClassId();
		int sectionId = reg.getMststudclasssection().getIntSectionId();*/
		hwList = (ArrayList<Tblhomework>) Studentservice.getHWFromTo(new java.sql.Date(oneWeekBefore.getTime()),
				new java.sql.Date(curDt.getTime()), id,classId,sectionId);

		if (hwList.size() > 0) {
			Iterator<Tblhomework> itr2 = hwList.iterator();
			while (itr2.hasNext()) {
				Tblhomework qq = (Tblhomework) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListHW.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecordsss", "noWeekRecordsss");
		}

		mav.addObject("hwList", hwList);
		mav.addObject("hwListe", hwListe);
		mav.addObject("teachNamesListHW", teachNamesListHW);
		mav.addObject("teachNamesListProj", teachNamesListProj);
		return mav;
		}

	}
	
	@RequestMapping(value = "ajax_stu_task.web")
	public ModelAndView studenttask2(HttpServletRequest req, ModelMap map) throws ParseException {

		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ArrayList<String> teachNamesListHW = new ArrayList<String>();
		ArrayList<String> teachNamesListProj = new ArrayList<String>();

		ModelAndView mav = new ModelAndView("ajax_stu_task");

		String aDateStr = req.getParameter("val1");
		System.out.println(aDateStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date aDate = sdf.parse(aDateStr);
		
		ArrayList<Tblhomework> hwList = new ArrayList<Tblhomework>();
		ArrayList<Mstteacherproject> hwListe = new ArrayList<Mstteacherproject>();

		java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
		if (aDate.after(appliedDate)) {
			map.addAttribute("msg1", "Date Can't Be Greater Than Today's Date");
		}

		int intRegId = (int) session.getAttribute("intRegId");
		Mstregistration reg=Studentservice.getClass(intRegId);
		int classId=reg.getMststudclass().getIntClassId();
		int sectionId = reg.getMststudclasssection().getIntSectionId();
		hwList = (ArrayList<Tblhomework>) Studentservice.getHW(aDate,intRegId,classId,sectionId);

		if (hwList.size() > 0) {
			Iterator<Tblhomework> itr2 = hwList.iterator();
			while (itr2.hasNext()) {
				Tblhomework qq = (Tblhomework) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListHW.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecordsss", "noWeekRecordsss");
		}

		String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SimpleDateFormat sdfwww = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date curDt = sdfwww.parse(sdfw);

		Calendar now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, -1);
		System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
				+ "-" + now.get(Calendar.DATE));
		String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
				+ now.get(Calendar.DATE);
		java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

		// int id = 1;
		//int intRegId = (int) session.getAttribute("intRegId");
		hwListe = (ArrayList<Mstteacherproject>) Studentservice.getProFromTo(new java.sql.Date(oneWeekBefore.getTime()),
				new java.sql.Date(curDt.getTime()), intRegId,classId,sectionId);
		

		if (hwListe.size() > 0) {
			Iterator<Mstteacherproject> itr2 = hwListe.iterator();
			while (itr2.hasNext()) {
				Mstteacherproject qq = (Mstteacherproject) itr2.next();
				int teachId = qq.getIntTeacherId();
				System.out.println(teachId);
				String teachName = Studentservice.getTeacherName(teachId);
				teachNamesListProj.add(teachName);
			}
		} else {
			mav.addObject("noWeekRecords", "noWeekRecords");
		}

		mav.addObject("hwList", hwList);
		mav.addObject("hwListe", hwListe);
		mav.addObject("teachNamesListHW", teachNamesListHW);
		mav.addObject("teachNamesListProj", teachNamesListProj);
		return mav;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="stu_profile.web", method=RequestMethod.POST)
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
		return "redirect:/ad_index_For_Student.web";
		}
	}
	
	
	@RequestMapping("stu_profile.web")
	public ModelAndView studentprofile(@ModelAttribute("student") Mstregistration student) 
	{
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("stu_profile");
		int intRegId =  (int) session.getAttribute("intRegId");
		
		List<Mstregistration> mst = (List<Mstregistration>)Studentservice.getParentInformationByName(intRegId);
		mav.addObject("mst",mst);
		

		return mav;
		}
	}
	
	
	
	/*@RequestMapping(value="stu_profile.web", method=RequestMethod.POST)
	public String saveImage( @RequestParam CommonsMultipartFile blAttachment)
	{
		Mstregistration reg=new Mstregistration();
		int intRegId =  (int) session.getAttribute("RegId");
		byte [] byteArr=blAttachment.getBytes();
				Studentservice.saveImage(byteArr,intRegId);
		return "redirect:/stu_profile.web";
	}*/
	
	@RequestMapping(value="/getUserImage/{id}.web")
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
	//student question and answer
	
	@RequestMapping("stu_question & answer.web")
	public ModelAndView studentquestionandanswer(HttpServletRequest req)
	{
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("stu_question & answer");
		
		List<Mstsubject> mst = (List<Mstsubject>)Studentservice.getStudentQuestionByName();
		mav.addObject("mst",mst);
		int intRegId= (int) session.getAttribute("intRegId");
		/*int stuId=4;*/
		   Mstregistration regObj=Studentservice.getClass(intRegId);
		   String classStr=regObj.getMststudclass().getTxtClassName();
		   int classjj=Integer.parseInt(classStr);
	
		
		 List<Mstquestionandanswer> q = Studentservice.getTeacherNameAll(classjj);
		 int teachId=0;
		/*ArrayList<String> stuNamesList=new ArrayList<String>();*/
		 /*ArrayList<String> teachNamesList=new ArrayList<String>();
		 Iterator<Mstquestionandanswer> itr=q.iterator();
		 while (itr.hasNext()) {
			Mstquestionandanswer qq = (Mstquestionandanswer) itr.next();
			int teachId=qq.getIntTeacherId();
			System.out.println(teachId);
			String teachName=Studentservice.getTeachName(teachId);
			teachNamesList.add(teachName);
		}*/
		 ArrayList<Mstregistration> teachNamesList = (ArrayList<Mstregistration>) Studentservice.getTeachName(teachId);
		 mav.addObject("teachNamesList", teachNamesList);
		   mav.addObject("q", q);
		return mav;
		}
	}
	
	
	@RequestMapping(value="stu_question & answer.web",method = RequestMethod.POST)  
	   public ModelAndView save(HttpServletRequest req, RedirectAttributes redAtt)throws ParseException { 
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		Mstquestionandanswer mq = new Mstquestionandanswer();
	   ModelAndView m=new ModelAndView();
	   int intRegId= (int) session.getAttribute("intRegId");
	   String quetion = req.getParameter("writequestion");
	   mq.setTxtQuestion(quetion);
	   int subjectId = Integer.parseInt(req.getParameter("mstSubject"));
	   Mstsubject msbject = new Mstsubject();
	   Mstregistration reg=new Mstregistration();
	  /* int stuId=4;*/
	   reg.setIntRegistrationId(intRegId);
	   Mstregistration regObj=Studentservice.getClass(intRegId);
	   String classStr=regObj.getMststudclass().getTxtClassName();
	   int classjj=Integer.parseInt(classStr);
	   
	   
	   
	   mq.setMstregistration(reg);
	   msbject.setIntSubjectId(subjectId);
	   mq.setMstsubject(msbject);
	  // mq.setTxtAnswer("This Question has not been answered till now!!!!");
	   mq.setIntTeacherId(0);
	   Mststudclass classObj=new Mststudclass();
	   classObj.setIntClassId(regObj.getMststudclass().getIntClassId());
	   
	   mq.setMststudclass(classObj);
	   Studentservice.addQuestion(mq);
	   redAtt.addFlashAttribute("msg", "Successfully Added");
	   
	   
	   List<Mstsubject> mst = (List<Mstsubject>)Studentservice.getStudentQuestionByName();
		m.addObject("mst",mst);
		
	
		 List<Mstquestionandanswer> q = Studentservice.getTeacherNameAll(classjj);
		 int teachId=0;
		/*ArrayList<String> stuNamesList=new ArrayList<String>();*/
		/* ArrayList<String> teachNamesList=new ArrayList<String>();
		 Iterator<Mstquestionandanswer> itr=q.iterator();
		 while (itr.hasNext()) {
			Mstquestionandanswer qq = (Mstquestionandanswer) itr.next();
			int teachId=qq.getIntTeacherId();
			System.out.println(teachId);
			String teachName=Studentservice.getTeachName(teachId);
			teachNamesList.add(teachName);
			
			
		}*/
		 ArrayList<Mstregistration> teachNamesList = (ArrayList<Mstregistration>) Studentservice.getTeachName(teachId);
	   
	   
		 m.addObject("teachNamesList", teachNamesList);
		 m.addObject("q", q);
	
	   
		  
	   return new ModelAndView("redirect:/stu_question & answer.web");
		}
	   } 
	
	
	
	
 //student task/assignment
	// student task/assignment
		@RequestMapping("stu_task.web")
		public ModelAndView studenttask() throws ParseException {

			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
			ModelAndView mav = new ModelAndView("stu_task");

			String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date curDt = sdf.parse(sdfw);

			Calendar now = Calendar.getInstance();
			now.add(Calendar.WEEK_OF_YEAR, -1);
			System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
					+ "-" + now.get(Calendar.DATE));
			String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
					+ now.get(Calendar.DATE);
			java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

			java.sql.Date date = new java.sql.Date(oneWeekBefore.getTime());
			// int id = 1;
			int intRegId = (int) session.getAttribute("intRegId");
			Mstregistration reg=Studentservice.getClass(intRegId);
			int classId=reg.getMststudclass().getIntClassId();
			int sectionId = reg.getMststudclasssection().getIntSectionId();
		List<Tblhomework>	hwList = (ArrayList<Tblhomework>) Studentservice.getHWFromTo(new java.sql.Date(oneWeekBefore.getTime()),
					new java.sql.Date(curDt.getTime()), intRegId,classId,sectionId);

			ArrayList<String> teachNamesListHW = new ArrayList<String>();
			ArrayList<String> teachNamesListProj = new ArrayList<String>();

			if (hwList.size() > 0) {
				Iterator<Tblhomework> itr2 = hwList.iterator();
				while (itr2.hasNext()) {
					Tblhomework qq = (Tblhomework) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListHW.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecordsss", "noWeekRecordsss");
			}

			mav.addObject("hwList", hwList);

			ArrayList<Mstteacherproject> hwListe = (ArrayList<Mstteacherproject>) Studentservice
					.getProFromTo(new java.sql.Date(oneWeekBefore.getTime()), new java.sql.Date(curDt.getTime()), intRegId,classId,sectionId);

			if (hwListe.size() > 0) {
				Iterator<Mstteacherproject> itr2 = hwListe.iterator();
				while (itr2.hasNext()) {
					Mstteacherproject qq = (Mstteacherproject) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListProj.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecords", "noWeekRecords");
			}

			mav.addObject("hwListe", hwListe);
			mav.addObject("teachNamesListHW", teachNamesListHW);
			mav.addObject("teachNamesListProj", teachNamesListProj);
			return mav;
			}
		}

		@RequestMapping(value = "stu_task.web", method = RequestMethod.POST)
		public ModelAndView studenttask1(HttpServletRequest req, ModelMap map) throws ParseException {

			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
			ArrayList<String> teachNamesListHW = new ArrayList<String>();
			ArrayList<String> teachNamesListProj = new ArrayList<String>();

			ModelAndView mav = new ModelAndView("stu_task");
			int intRegId = (int) session.getAttribute("intRegId");
			Mstregistration reg=Studentservice.getClass(intRegId);
			int classId=reg.getMststudclass().getIntClassId();
			int sectionId = reg.getMststudclasssection().getIntSectionId();
			String aDateStr = req.getParameter("assignDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date aDate = sdf.parse(aDateStr);
			ArrayList<Tblhomework> hwList = new ArrayList<Tblhomework>();
			ArrayList<Mstteacherproject> hwListe = new ArrayList<Mstteacherproject>();

			java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
			if (aDate.after(appliedDate)) {
				map.addAttribute("msg1", "Date Can't Be Greater Than Today's Date");
			}

			
			hwList = (ArrayList<Tblhomework>) Studentservice.getHW(aDate,intRegId,classId,sectionId);

			if (hwList.size() > 0) {
				Iterator<Tblhomework> itr2 = hwList.iterator();
				while (itr2.hasNext()) {
					Tblhomework qq = (Tblhomework) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListHW.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecordsss", "noWeekRecordsss");
			}

			String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdfwww = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date curDt = sdfwww.parse(sdfw);

			Calendar now = Calendar.getInstance();
			now.add(Calendar.WEEK_OF_YEAR, -1);
			System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
					+ "-" + now.get(Calendar.DATE));
			String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
					+ now.get(Calendar.DATE);
			java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

			// int id = 1;
			 intRegId = (int) session.getAttribute("intRegId");
			hwListe = (ArrayList<Mstteacherproject>) Studentservice.getProFromTo(new java.sql.Date(oneWeekBefore.getTime()),
					new java.sql.Date(curDt.getTime()), intRegId,classId,sectionId);

			if (hwListe.size() > 0) {
				Iterator<Mstteacherproject> itr2 = hwListe.iterator();
				while (itr2.hasNext()) {
					Mstteacherproject qq = (Mstteacherproject) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListProj.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecords", "noWeekRecords");
			}

			mav.addObject("hwList", hwList);
			mav.addObject("hwListe", hwListe);
			mav.addObject("teachNamesListHW", teachNamesListHW);
			mav.addObject("teachNamesListProj", teachNamesListProj);
			return mav;
			}
		}

		@RequestMapping("stu_project.web")
		public ModelAndView studentprojec() throws ParseException {

			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
			ModelAndView mav = new ModelAndView("stu_task");

			String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date curDt = sdf.parse(sdfw);

			Calendar now = Calendar.getInstance();
			now.add(Calendar.WEEK_OF_YEAR, -1);
			System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
					+ "-" + now.get(Calendar.DATE));
			String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
					+ now.get(Calendar.DATE);
			java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

			java.sql.Date date = new java.sql.Date(oneWeekBefore.getTime());
			// int id = 1;
			int id = (int) session.getAttribute("intRegId");
			Mstregistration reg=Studentservice.getClass(id);
			int classId=reg.getMststudclass().getIntClassId();
			int sectionId = reg.getMststudclasssection().getIntSectionId();
		List<Tblhomework>	hwList = (ArrayList<Tblhomework>) Studentservice.getHWFromTo(new java.sql.Date(oneWeekBefore.getTime()),
					new java.sql.Date(curDt.getTime()), id,classId,sectionId);

			ArrayList<String> teachNamesListHW = new ArrayList<String>();
			ArrayList<String> teachNamesListProj = new ArrayList<String>();

			if (hwList.size() > 0) {
				Iterator<Tblhomework> itr2 = hwList.iterator();
				while (itr2.hasNext()) {
					Tblhomework qq = (Tblhomework) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListHW.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecordsss", "noWeekRecordsss");
			}

			mav.addObject("hwList", hwList);

			ArrayList<Mstteacherproject> hwListe = (ArrayList<Mstteacherproject>) Studentservice
					.getProFromTo(new java.sql.Date(oneWeekBefore.getTime()), new java.sql.Date(curDt.getTime()), id,classId,sectionId);
			

			if (hwListe.size() > 0) {
				Iterator<Mstteacherproject> itr2 = hwListe.iterator();
				while (itr2.hasNext()) {
					Mstteacherproject qq = (Mstteacherproject) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListProj.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecords", "noWeekRecords");
			}

			mav.addObject("hwListe", hwListe);
			mav.addObject("teachNamesListHW", teachNamesListHW);
			mav.addObject("teachNamesListProj", teachNamesListProj);
			return mav;
			}
		}

		@RequestMapping(value = "stu_project.web", method = RequestMethod.POST)
		public ModelAndView studentproject(HttpServletRequest req, ModelMap map) throws ParseException {

			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
			ArrayList<String> teachNamesListHW = new ArrayList<String>();
			ArrayList<String> teachNamesListProj = new ArrayList<String>();

			int id = (int) session.getAttribute("intRegId");
			Mstregistration reg=Studentservice.getClass(id);
			int classId=reg.getMststudclass().getIntClassId();
			int sectionId = reg.getMststudclasssection().getIntSectionId();
			ModelAndView mav = new ModelAndView("stu_task");
			String aDate = req.getParameter("assignDatee");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date aDt = sdf.parse(aDate);
			ArrayList<Tblhomework> hwList = new ArrayList<Tblhomework>();
			ArrayList<Mstteacherproject> hwListe = new ArrayList<Mstteacherproject>();
			java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
			if (aDt.after(appliedDate)) {
				map.addAttribute("msg2", "Date Can't Be Greater Than Today's Date");
			}

			hwListe = (ArrayList<Mstteacherproject>) Studentservice.getProject(aDt,id,classId,sectionId);

			if (hwListe.size() > 0) {
				Iterator<Mstteacherproject> itr2 = hwListe.iterator();
				while (itr2.hasNext()) {
					Mstteacherproject qq = (Mstteacherproject) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListProj.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecords", "noWeekRecords");
			}

			String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdfwww = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date curDt = sdfwww.parse(sdfw);

			Calendar now = Calendar.getInstance();
			now.add(Calendar.WEEK_OF_YEAR, -1);
			System.out.println("date before 1 week : " + (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1)
					+ "-" + now.get(Calendar.DATE));
			String oneWeekBeforeStr = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
					+ now.get(Calendar.DATE);
			java.util.Date oneWeekBefore = sdf.parse(oneWeekBeforeStr);

			// int id = 1;
			
			hwList = (ArrayList<Tblhomework>) Studentservice.getHWFromTo(new java.sql.Date(oneWeekBefore.getTime()),
					new java.sql.Date(curDt.getTime()), id,classId,sectionId);

			if (hwList.size() > 0) {
				Iterator<Tblhomework> itr2 = hwList.iterator();
				while (itr2.hasNext()) {
					Tblhomework qq = (Tblhomework) itr2.next();
					int teachId = qq.getIntTeacherId();
					System.out.println(teachId);
					String teachName = Studentservice.getTeacherName(teachId);
					teachNamesListHW.add(teachName);
				}
			} else {
				mav.addObject("noWeekRecordsss", "noWeekRecordsss");
			}

			mav.addObject("hwList", hwList);
			mav.addObject("hwListe", hwListe);
			mav.addObject("teachNamesListHW", teachNamesListHW);
			mav.addObject("teachNamesListProj", teachNamesListProj);
			return mav;
			}

		}
	
/*@RequestMapping(value="stu_project.web", method = RequestMethod.POST)
public ModelAndView studentproject(HttpServletRequest req) throws ParseException
{

ModelAndView mav = new ModelAndView("stu_task");
String aDate=req.getParameter("assignDatee");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date curDt = sdf.parse(aDate);
ArrayList<Mstteacherproject> hwListe = (ArrayList<Mstteacherproject>) Studentservice.getProject(curDt);
mav.addObject("hwListe", hwListe);
mav.addObject("performance", "performance");
return mav;


}
*/	
//student attendance
	@RequestMapping(value = "stu_attedance.web")
	public ModelAndView attendancePost(HttpServletRequest req, @RequestParam Map<String, String> params)
			throws ParseException {
		
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("stu_attedance");

		
		// start initialization
		 int studId = (int) session.getAttribute("intRegId");
		/*String stuName = null;*/
		ArrayList<Trnattendancerecord> attndList = null;
		
		String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date curDt = sdf.parse(sdfw);
		
		//end initialization
		
		ArrayList<String> output1 = new ArrayList<String>();

			attndList = (ArrayList<Trnattendancerecord>) Studentservice.getStudAttendance(studId, curDt);
			
		mav.addObject("attndList", attndList);
		Iterator<Trnattendancerecord> i2 = attndList.iterator();
		while (i2.hasNext()) {
			Trnattendancerecord attdObj = (Trnattendancerecord) i2.next();

			String inOutTime = attdObj.getDtSlotTime();

			ArrayList<String> inOutTotalList = (ArrayList<String>) getTotalTime(inOutTime);
			String inTime = inOutTotalList.get(0);
			String outTime = inOutTotalList.get(1);
			String totalTime = inOutTotalList.get(2);

			output1.add(inTime);
			output1.add(outTime);
			output1.add(totalTime);
		} // while
		
		/*mav.addObject("output", output);
		mav.addObject("studId", studId);
		mav.addObject("stuName", stuName);*/
		mav.addObject("output1", output1);
		mav.addObject("attendance", "attendance");

		return mav;
		}
	}

	

	@RequestMapping(value = "stu_attedance.web", method = RequestMethod.POST)
	public ModelAndView attendanceByDatesPost(@RequestParam Map<String, String> params, ModelMap map) throws ParseException {
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("stu_attedance");

		
		 int studId = (int) session.getAttribute("intRegId");
		/*String stuName = null;*/
		ArrayList<Trnattendancerecord> attndList = null;
		
		String sdfw = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date curDt = sdf.parse(sdfw);
		
		//end initialization
		
		ArrayList<String> output1 = new ArrayList<String>();

			attndList = (ArrayList<Trnattendancerecord>) Studentservice.getStudAttendance(studId, curDt);
			
		mav.addObject("attndList", attndList);
		Iterator<Trnattendancerecord> i2 = attndList.iterator();
		while (i2.hasNext()) {
			Trnattendancerecord attdObj = (Trnattendancerecord) i2.next();

			String inOutTime = attdObj.getDtSlotTime();

			ArrayList<String> inOutTotalList = (ArrayList<String>) getTotalTime(inOutTime);
			String inTime = inOutTotalList.get(0);
			String outTime = inOutTotalList.get(1);
			String totalTime = inOutTotalList.get(2);

			output1.add(inTime);
			output1.add(outTime);
			output1.add(totalTime);
		} // while
		
		/*mav.addObject("output", output);
		mav.addObject("studId", studId);
		mav.addObject("stuName", stuName);*/
		mav.addObject("output1", output1);
		mav.addObject("attendance", "attendance");
		
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
		String d1 = params.get("fromDate");
		String d2 = params.get("toDate");
		System.out.println(d1+"  "+d2);
		System.out.println("first");
		java.util.Date startDate = dateFormat.parse(d1);
		System.out.println("second");
		java.util.Date endDate = dateFormat1.parse(d2);
		java.sql.Date appliedDate = new java.sql.Date(new java.util.Date().getTime());
		   if(endDate.after(appliedDate)){
			   map.addAttribute("msg1", "Date Can't Be Greater Than Today's Date");  
		   }
		   else if(startDate.after(endDate))
		   {
			   map.addAttribute("msg1", "FromDate can not be greater than ToDate");  
		   }
		//int studId = 4;
		String stuName = Studentservice.getStuNames(studId);

		ArrayList<Trnattendancerecord> trnBetweenAttendance = (ArrayList<Trnattendancerecord>) Studentservice
				.getStudentAttendFromToDate(startDate, endDate, studId);
		mav.addObject("trnBetweenAttendance", trnBetweenAttendance);
		Iterator<Trnattendancerecord> i = trnBetweenAttendance.iterator();
		ArrayList<String> output = new ArrayList<String>();
		while (i.hasNext()) {
			Trnattendancerecord trnattndObj = (Trnattendancerecord) i.next();
			String inOutTime = trnattndObj.getDtSlotTime();
			ArrayList<String> inOutTotalList = (ArrayList<String>) getTotalTime(inOutTime);
			String inTime = inOutTotalList.get(0);
			String outTime = inOutTotalList.get(1);
			String totalTime = inOutTotalList.get(2);

			/*
			 * mav.addObject("inTime", inTime); mav.addObject("outTime",
			 * outTime); mav.addObject("totalTime", totalTime);
			 */
			output.add(inTime);
			output.add(outTime);
			output.add(totalTime);

		}
		
		mav.addObject("out", output);
		mav.addObject("studId", studId);
		mav.addObject("stuName", stuName);

		mav.addObject("attendance", "attendance");

		return mav;
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
	

	/*public List<String> getTotalTime(String inOutTime) {

		String inTime = inOutTime.substring(0, 5);
		String outTime = inOutTime.substring(6, 11);

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
		String totalTime = null;
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

		ArrayList<String> list = new ArrayList<>();
		list.add(inTime);
		list.add(outTime);
		list.add(totalTime);
		return list;
	}*/

	//student timetable
	/*@RequestMapping("stu_timetable.web")
	public ModelAndView partimetable(@ModelAttribute("parent") Mststudenttimetable parent) {
				
		ModelAndView mav = new ModelAndView("stu_timetable");
		List<Object[]> mst = (List<Object[]>) Studentservice.getStuTimeTablebyClass();
		mav.addObject("mst",mst);
		return mav;
			} */
	
	
	// student timetable
		@RequestMapping("stu_timetable.web")
		public ModelAndView partimetable(@ModelAttribute("parent") Mststudenttimetable parent) {
			if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
			{
				return new ModelAndView("redirect:/login.web");
			}
			else
			{
			ModelAndView mav = new ModelAndView("stu_timetable");
			int intRegId = (int) session.getAttribute("intRegId");
			Mstregistration regObj=Studentservice.getClassSection(intRegId);
			int classId=regObj.getMststudclass().getIntClassId();
			int sectionId=regObj.getMststudclasssection().getIntSectionId();
			ArrayList<ArrayList<Mststudenttimetable>> totalList=Studentservice.getStudentTT(classId, sectionId);
			
			ArrayList<Msttimings> timingList=(ArrayList<Msttimings>) Studentservice.getTimings();
			ArrayList<Tbldaysofweek> daysList=(ArrayList<Tbldaysofweek>) Studentservice.getDays();
			
			mav.addObject("daysList", daysList);
			mav.addObject("timingList", timingList);
			mav.addObject("totalList", totalList);
			return mav;
			}
		}
		
	
	
	


	//student notification
	
	@RequestMapping("stu_notification.web")
	public ModelAndView studentnotification(@ModelAttribute("student") Tblinvites student)
	{
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("stu_notification");
		int intRegId =  (int) session.getAttribute("intRegId");
		List<Tblinvites> mst = (List<Tblinvites>)Studentservice.getStudentNotificationByName(intRegId);
		Mstregistration reg=Studentservice.getClass(intRegId);
		int classId=reg.getMststudclass().getIntClassId();
		int sectionId = reg.getMststudclasssection().getIntSectionId();
		List<Mstteacherinvite> mste = (List<Mstteacherinvite>)Studentservice.getTeacherNotificationByName(intRegId,classId,sectionId);
		mav.addObject("mste",mste);
		mav.addObject("mst", mst);
		return mav;
		}
	}
	
	@RequestMapping(value = "downloadss/{id}")
	public void Downloade(HttpServletResponse response,HttpServletRequest request, @PathVariable Integer id) throws IOException {
		Tblinvites invObj = Studentservice.getAdminNotification(id);
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
		/*res.setContentLength(file.getBlUploadFile().length);
		res.setContentType(file.getTxtcontentType());
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getTxtfileName() + "\"");
		FileCopyUtils.copy(file.getBlUploadFile(), out);
		
		out.flush();
		out.close();
		return;*/
	}

	
	//student marks
	
	@RequestMapping("perf_marks.web")
	public ModelAndView studentmarks(@ModelAttribute("student") Mstuploadstudmarksheet student)
	{
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		
		ModelAndView mav = new ModelAndView("perf_marks");
		int intRegId =  (int) session.getAttribute("intRegId");
		ArrayList<Mstuploadstudmarksheet> mst = (ArrayList<Mstuploadstudmarksheet>) Studentservice.getStudentMarksByName(intRegId);
		mav.addObject("mst",mst);
		return mav;
		}
	}
	
	@RequestMapping(value = "download/{id}")
	public void Download(HttpServletRequest request,HttpServletResponse response, @PathVariable Integer id) throws IOException {
		
		Mstuploadstudmarksheet marksObj = Studentservice.getMarkSheet(id);
		/*res.setContentLength(file.getBlMarkSheet().length);
		res.setContentType(file.getTxtcontentType());
		OutputStream out = res.getOutputStream();
		res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getTxtfileName() + "\"");
		FileCopyUtils.copy(file.getBlMarkSheet(), out);
		res.sendRedirect("perf_marks.jsp");
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
	
	

	//Syllabus
	
	
	
	@RequestMapping("stu_syllabus.web")
	public ModelAndView studentsyllabus(@ModelAttribute("student") Mstsyllabus student)
	{
		if(session.getAttribute("intRegId")==null||session.getAttribute("intRegId")=="")
		{
			return new ModelAndView("redirect:/login.web");
		}
		else
		{
		ModelAndView mav = new ModelAndView("stu_syllabus");
		int intRegId =  (int) session.getAttribute("intRegId");
		Mstregistration reg=Studentservice.getClass(intRegId);
		int classId=reg.getMststudclass().getIntClassId();
		ArrayList<Mstsyllabus> mst = (ArrayList<Mstsyllabus>)Studentservice.getStudentSyllabus(classId);
		
		
		mav.addObject("mst", mst);
		return mav;
		}
	}
	
	@RequestMapping(value = "downloades/{id}")
	public void Downloades(HttpServletResponse res, @PathVariable Integer id) throws IOException {
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
