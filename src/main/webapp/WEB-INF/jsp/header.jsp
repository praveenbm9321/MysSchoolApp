<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.1.1
Version: 2.0.2
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>School Management System</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->






<script type="text/javascript">
	window.location.hash = "";
	window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
	window.onhashchange = function() {
		window.location.hash = "";
	}
</script>


<script type="text/javascript">
	function back_block() {
		window.history.foward(-1)
	}
</script>





<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN THEME STYLES -->
<link href="assets/css/style-metronic.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="assets/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="assets/css/themes/default.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->

<!-- multi select begin -->
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-select/bootstrap-select.min.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2-metronic.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/jquery-multi-select/css/multi-select.css" />
<!-- multi select end -->
<!-- edit begin -->
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-editable/bootstrap-editable/css/bootstrap-editable.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-editable/inputs-ext/address/address.css" />
<!-- edit end -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed" onload="javascript:back_block();">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="header-inner">
			<!-- BEGIN LOGO -->
			<a class="navbar-brand" href="index.html"> <img
				src="assets/img/logo.png" alt="logo" class="img-responsive" />
			</a>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<!-- <a href="javascript:;" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse"> <img
				src="assets/img/menu-toggler.png" alt="" />
			</a> -->
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN NOTIFICATION DROPDOWN -->
				<!--<li class="dropdown" id="header_notification_bar">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="fa fa-warning"></i>
					<span class="badge">
						 6
					</span>
				</a>
				<ul class="dropdown-menu extended notification">
					<li>
						<p>
							 You have 14 new notifications
						</p>
					</li>
					<li>
						<ul class="dropdown-menu-list scroller" style="height: 250px;">
							<li>
								<a href="#">
									<span class="label label-icon label-success">
										<i class="fa fa-plus"></i>
									</span>
									 New user registered.
									<span class="time">
										 Just now
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 Server #12 overloaded.
									<span class="time">
										 15 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-warning">
										<i class="fa fa-bell-o"></i>
									</span>
									 Server #2 not responding.
									<span class="time">
										 22 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-info">
										<i class="fa fa-bullhorn"></i>
									</span>
									 Application error.
									<span class="time">
										 40 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 Database overloaded 68%.
									<span class="time">
										 2 hrs
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 2 user IP blocked.
									<span class="time">
										 5 hrs
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-warning">
										<i class="fa fa-bell-o"></i>
									</span>
									 Storage Server #4 not responding.
									<span class="time">
										 45 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-info">
										<i class="fa fa-bullhorn"></i>
									</span>
									 System Error.
									<span class="time">
										 55 mins
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="label label-icon label-danger">
										<i class="fa fa-bolt"></i>
									</span>
									 Database overloaded 68%.
									<span class="time">
										 2 hrs
									</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="external">
						<a href="#">
							 See all notifications <i class="m-icon-swapright"></i>
						</a>
					</li>
				</ul>
			</li>-->
				<!-- END NOTIFICATION DROPDOWN -->
				<!-- BEGIN INBOX DROPDOWN -->
				<!--<li class="dropdown" id="header_inbox_bar">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="fa fa-envelope"></i>
					<span class="badge">
						 5
					</span>
				</a>
				<ul class="dropdown-menu extended inbox">
					<li>
						<p>
							 You have 12 new messages
						</p>
					</li>
					<li>
						<ul class="dropdown-menu-list scroller" style="height: 250px;">
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar2.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Lisa Wong
										</span>
										<span class="time">
											 Just Now
										</span>
									</span>
									<span class="message">
										 Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar3.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Richard Doe
										</span>
										<span class="time">
											 16 mins
										</span>
									</span>
									<span class="message">
										 Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar1.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Bob Nilson
										</span>
										<span class="time">
											 2 hrs
										</span>
									</span>
									<span class="message">
										 Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar2.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Lisa Wong
										</span>
										<span class="time">
											 40 mins
										</span>
									</span>
									<span class="message">
										 Vivamus sed auctor 40% nibh congue nibh...
									</span>
								</a>
							</li>
							<li>
								<a href="inbox.html?a=view">
									<span class="photo">
										<img src="./assets/img/avatar3.jpg" alt=""/>
									</span>
									<span class="subject">
										<span class="from">
											 Richard Doe
										</span>
										<span class="time">
											 46 mins
										</span>
									</span>
									<span class="message">
										 Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh...
									</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="external">
						<a href="inbox.html">
							 See all messages <i class="m-icon-swapright"></i>
						</a>
					</li>
				</ul>
			</li>-->
				<!-- END INBOX DROPDOWN -->
				<!-- BEGIN TODO DROPDOWN -->
				<!--<li class="dropdown" id="header_task_bar">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="fa fa-tasks"></i>
					<span class="badge">
						 5
					</span>
				</a>
				<ul class="dropdown-menu extended tasks">
					<li>
						<p>
							 You have 12 pending tasks
						</p>
					</li>
					<li>
						<ul class="dropdown-menu-list scroller" style="height: 250px;">
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 New release v1.2
										</span>
										<span class="percent">
											 30%
										</span>
									</span>
									<span class="progress">
										<span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 40% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Application deployment
										</span>
										<span class="percent">
											 65%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 65%;" class="progress-bar progress-bar-danger" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 65% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Mobile app release
										</span>
										<span class="percent">
											 98%
										</span>
									</span>
									<span class="progress">
										<span style="width: 98%;" class="progress-bar progress-bar-success" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 98% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Database migration
										</span>
										<span class="percent">
											 10%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 10%;" class="progress-bar progress-bar-warning" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 10% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Web server upgrade
										</span>
										<span class="percent">
											 58%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 58%;" class="progress-bar progress-bar-info" aria-valuenow="58" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 58% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 Mobile development
										</span>
										<span class="percent">
											 85%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 85%;" class="progress-bar progress-bar-success" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 85% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
							<li>
								<a href="#">
									<span class="task">
										<span class="desc">
											 New UI release
										</span>
										<span class="percent">
											 18%
										</span>
									</span>
									<span class="progress progress-striped">
										<span style="width: 18%;" class="progress-bar progress-bar-important" aria-valuenow="18" aria-valuemin="0" aria-valuemax="100">
											<span class="sr-only">
												 18% Complete
											</span>
										</span>
									</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="external">
						<a href="#">
							 See all tasks <i class="m-icon-swapright"></i>
						</a>
					</li>
				</ul>
			</li>-->
				<!-- END TODO DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" data-hover="dropdown"
					data-close-others="true"> <!-- <img alt=""
						src="assets/img/avatar1_small.jpg" />  --> <span class="username">
							<%
								Object firstName = session.getAttribute("txtFirstName");
								Object lastName = session.getAttribute("txtLastName");
								out.print(firstName + " " + lastName);
							%>
					</span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="changePasswordForAdmin.web"> <i
								class="fa fa-lock"></i> Change password

						</a></li>
						<li><a href="logout.web"> <i class="fa fa-user"></i> Log
								Out
						</a></li>

					</ul></li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU -->
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>

	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu" data-auto-scroll="true"
					data-slide-speed="200">
					<li class="sidebar-toggler-wrapper">
						<!-- BEGIN SIDEBAR TOGGLER BUTTON --> <!-- <!-- <div class="sidebar-toggler hidden-phone">
					</div> --> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					</li>
					<li class="sidebar-search-wrapper">
						<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
						<form class="sidebar-search" action="extra_search.html"
							method="POST">
							<div class="form-container">
								<!--<div class="input-box">
								<a href="javascript:;" class="remove">
								</a>
								 <input type="text" placeholder="Search..."/> 
								<input type="button" class="submit" value=" "/>
							</div>-->
							</div>
						</form> <!-- END RESPONSIVE QUICK SEARCH FORM -->
					</li>


					<!-- <li class="start " id="Dashboard"><a href="ad_index.web">
							<i class="fa fa-home"></i> <span class="title"> Dashboard
						</span>
					</a></li>
					<li id="Admin"><a href="javascript:;"> <i
							class="fa fa-shopping-cart"></i> <span class="title">
								Admin </span> <span class="arrow "> </span>
					</a>
						<ul class="sub-menu"> -->
					<li class="start" id="Dashboard"><a href="ad_index.web"> <i
							class="fa fa-bullhorn"></i> Dashboard
					</a></li>
					<li id="usermanagement"><a href="#"> <i class="fa fa-user"></i>
							User Management <span class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li id="newreg"><a href="ad_reg.web"> <i
									class="fa fa-edit "></i> New Registration
							</a></li>

							<li id="studentStaff"><a href="user_staff.web"> <i
									class="fa  fa-building-o"></i> Staff/Student Master Record
							</a></li>

							<li id="assignCT"><a href="ad_assignClassTeacher.web"> <i
									class="fa  fa-building-o"></i> Assign Class Teacher
							</a></li>

							<!-- <li id="credentials"><a href="user_credentials.web"> <i
											class="fa fa-dedent"></i> View Credentials
									</a></li> -->
						</ul></li>

					<li id="attendanceLeave"><a href="#"> <i
							class="fa  fa-clock-o"></i> Attendance & Leave <span
							class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li id="attendanceInfo"><a href="ad_attendance.web"> <i
									class="fa fa-tags "></i> Attendance Info
							</a></li>
							<li id="teachAttendInfo"><a href="ad_teach_attend.web">
									<i class="fa fa-tags "></i> Teacher Info
							</a></li>
							<li id="leaveInfo"><a href="ad_leaveInfo.web"> <i
									class="fa  fa-clock-o"></i> Leave Info
							</a></li>
						</ul></li>

					<li id="adminTimetable"><a href="ad_timetable.web"> <i
							class="fa fa-table"></i> Time Table
					</a></li>
					<li id="adminLibrary"><a href="ad_library.web"> <i
							class="fa fa-align-justify"></i> Library
					</a></li>



					<li id="adminFeeMgt"><a href="/SMS/getAllFees"> <i
							class="fa  fa-money"></i> Fee Management

					</a></li>

					<li id="adminStuPerf"><a href="ad_student perform.web"> <i
							class="fa fa-bar-chart-o"></i> Students Performance
					</a></li>
					<li id="invites"><a href="#"> <i class="fa  fa-clock-o"></i>
							Invites <span class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li id="adminInvites"><a href="ad_invites.web"> <i
									class="fa fa-tags "></i> Admin Invites
							</a></li>
							<li id="teacherInvites"><a href="ad_teach_invites.web">
									<i class="fa fa-tags "></i> Teacher Invites
							</a></li>
						</ul></li>
					<li id="adminSchoolConnect"><a href="ad_school's_connect.web">
							<i class="fa fa-chain"></i> School's Connect
					</a></li>
					<!-- <li>
							<a href="ad_activity log.web">
								<i class="fa  fa-tags"></i>
								Activity Log
							</a>
						</li> -->
					<li id="adminSchoolBranch"><a href="ad_school & branch.web">
							<i class="fa fa-archive"></i> School & Branch Details
					</a></li>
					<li id="adminSyllabus"><a href="ad_syllabus.web"> <i
							class="fa  fa-folder-open"></i> Syllabus
					</a></li>
					<li id="adminQuery"><a href="ad_query.web"> <i
							class="fa fa-question-circle"></i> Queries
					</a></li>
					<!-- </ul></li> -->
					<!-- 					<li id="Teacher"><a href="javascript:;"> <i
							class="fa fa-users"></i> <span class="title"> Teacher </span> <span
							class="arrow"> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="ad_index.web"> <i class="fa fa-home"></i>
									Dashboard
							</a></li>
							<li id="teacherProfile"><a href="teac_profile.web"> <i
									class="fa fa-edit "></i> Profile

							</a></li>

							<li id="teacherHomework"><a href="teac_homework.web"> <i
									class="fa fa-edit "></i> Homework

							</a></li>

							<li id="teacherQuesAns"><a href="teac_question & answer.web">
									<i class="fa fa-question-circle"></i> Question & Answer
							</a></li>
							<li id="teacherNotification"><a href="teac_notification.web">
									<i class="fa fa-upload"></i> Notification
							</a></li>
							<li id="teachMarksheet"><a href="teac_marksheet.web"> <i
									class="fa fa-edit "></i> Student MarkSheet

							</a></li>

							<li id="teachTimetable"><a href="teac_timetable.web"> <i
									class="fa fa-table"></i> Time Table
							</a></li>
							<li id="teachInvites"><a href="teac_send invites.web"> <i
									class="fa fa-inbox"></i> Send Invites
							</a></li>
							<li id="teachProject"><a href="teac_project.web"> <i
									class="fa fa-edit "></i> Project

							</a></li>


							<li id="teachLeave"><a href="ad_user management.html"> <i
									class="fa  fa-folder-open"></i> Leave <span class="arrow ">
								</span>
							</a>

								<ul class="sub-menu">
									<li id="teachLeaveReq"><a href="teac_leave request.web">
											<i class="fa fa-edit "></i> Leave Application
									</a></li>

									<li id="teachLeaveInbox"><a href="teac_leaveInbox.web">
											<i class="fa  fa-folder-open"></i> Leave Inbox
									</a></li>
									<li id="teachStudentLeave"><a
										href="teac_StudentLeaveInbox.web"> <i
											class="fa  fa-folder-open"></i> Student Leave Approval Inbox
									</a></li>
								</ul></li>
						</ul></li>
					<li id="Parent"><a href="javascript:;"> <i
							class="fa  fa-user"></i> <span class="title"> Parent </span> <span
							class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="#"> <i class="fa fa-home"></i> Dashboard
							</a></li>
							<li id="parStuProfile"><a href="par_student profile.web">
									<i class="fa fa-folder"></i> Student Profile
							</a></li>
							<li id="parAttendance"><a href="par_attendance.web"> <i
									class="fa fa-users"></i> Attendance
							</a></li>
							<li id="parTimetable"><a href="par_timetable.web"> <i
									class="fa fa-table"></i> Time Table
							</a></li>
							<li id="parMarks"><a href="par_marks.web"> <i
									class="fa fa-columns"></i> Marks
							</a></li>
							<li id="parHmwrk"><a href="par_homework.web"> <i
									class="fa fa-home"></i> Homework
							</a></li>
							<li id="parNotification"><a href="par_notification.web">
									<i class="fa fa-home"></i> Notification<span
									class="badge badge-success"> 1 </span>

							</a></li>

							<li id="parFee"><a href="par_fee details.web"> <i
									class="fa fa-tasks"></i> Fee Details
							</a></li>
							<li id="parGPS"><a href="par_gps tarcking.web"> <i
									class="fa fa-rss"></i> GPS Tracking
							</a></li>
													<li>
							<a href="par_event.web">
							<i class="fa fa-tasks"></i>
								 Event
							</a>
						</li>
							<li id="parLib"><a href="par_virtual lib.web"> <i
									class="fa fa-align-justify"></i> Virtual Library
							</a></li>
							<li id="parLeave"><a href="#"> <i
									class="fa  fa-folder-open"></i> Leave <span class="arrow ">
								</span>
							</a>

								<ul class="sub-menu">
									<li id="parLeaveRequest"><a href="par_leave request.web">
											<i class="fa fa-edit "></i> Leave Application
									</a></li>

									<li id="parLeaveInbox"><a href="par_StudleaveInbox.web">
											<i class="fa  fa-folder-open"></i> Leave Inbox
									</a></li>

								</ul></li>
						</ul></li>
					<li id="Student"><a href="javascript:;"> <i
							class="fa fa-users"></i> <span class="title"> Student </span> <span
							class="arrow "> </span>
					</a>
						<ul class="sub-menu">
							<li><a href="#"> <i class="fa fa-home"></i> Dashboard
							</a></li>
							<li id="stuProfile"><a href="stu_profile.web"> <i
									class="fa fa-folder"></i> Profile
							</a></li>
							<li id="stuAttendance"><a href="stu_attedance.web"> <i
									class="fa fa-users"></i> Attendance
							</a></li>
							<li id="stuTask"><a href="stu_task.web"> <i
									class="fa  fa-tasks"></i> Homework
							</a></li>
							<li id="stuNotification"><a href="stu_notification.web">
									<i class="fa  fa-dropbox"></i> Notification

							</a></li>
							<li id="stuQuesAns"><a href="stu_question & answer.web">
									<i class="fa fa-question-circle"></i> Question & Answer
							</a></li>
							<li id="stuMarks"><a href="perf_marks.web"> <i
									class="fa fa-edit "></i> Marks
							</a></li>
							<li id="stuTimetable"><a href="stu_timetable.web"> <i
									class="fa fa-table"></i> Time Table
							</a></li>

						</ul></li>

					<li><a href="javascript:;"> <i class="fa fa-puzzle-piece"></i>
							<span class="title"> Reports Gallery </span>

					</a></li> -->
				</ul>

				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">Modal title</h4>
							</div>
							<div class="modal-body">Widget settings form goes here</div>
							<div class="modal-footer">
								<button type="button" class="btn blue">Save changes</button>
								<button type="button" class="btn default" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN STYLE CUSTOMIZER -->
				<!--<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
							 THEME COLOR
						</span>
						<ul>
							<li class="color-black current color-default" data-style="default">
							</li>
							<li class="color-blue" data-style="blue">
							</li>
							<li class="color-brown" data-style="brown">
							</li>
							<li class="color-purple" data-style="purple">
							</li>
							<li class="color-grey" data-style="grey">
							</li>
							<li class="color-white color-light" data-style="light">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
							 Layout
						</span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Header
						</span>
						<select class="header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Sidebar
						</span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Sidebar Position
						</span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
							 Footer
						</span>
						<select class="footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>-->
				<!-- END STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->