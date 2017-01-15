<!DOCTYPE html>
<html>
    <head>
     <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
        <meta name="author" content="Coderthemes">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <!-- App favicon -->
        <link rel="shortcut icon" href="assets1/images/aikshika.png">
        <!-- App title -->
        <title>Aikshika</title>

        <!--Morris Chart CSS -->
		<link rel="stylesheet" href="../plugins/morris/morris.css">

        <!-- App css -->
        <link href="assets1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets1/css/core.css" rel="stylesheet" type="text/css" />
        <link href="assets1/css/components.css" rel="stylesheet" type="text/css" />
        <link href="assets1/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="assets1/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="assets1/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="assets1/css/responsive.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="../plugins/switchery/switchery.min.css">

        <!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script src="assets1/js/modernizr.min.js"></script>
         <script>
        			$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin

			rules : {


				
						
				blAttachment:{
							
	required : true,
					extension : "jpg|jpeg|png"
				}

			},

			messages : {

				
				blAttachment : {
					required : "please choose file",
					extension : "only image files (.jpg, .jpeg or .png) are allowed"
				}

			},

			highlight : function(element) {

				$(element).parent().addClass('error')

			}

		});

	});
        			</script>
        			
        			<style>
#form1 .error{
	color: red;
	font-size: 13px;
	
}
</style>
        

    </head>


    <body class="fixed-left">

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Top Bar Start -->
            <div class="topbar">

                <!-- LOGO -->
                <div class="topbar-left">
                    <a href="#" class="logo"><span><img src="assets1/images/aikshika.png"></span><i class=<img src="assets1/images/aikshika.png"/>></i></i></a>
                    <!-- Image logo -->
                    <!--<a href="index.web" class="logo">-->
                        <!--<span>-->
                            <!--<img src="assets1/images/logo.png" alt="" height="30">-->
                        <!--</span>-->
                        <!--<i>-->
                            <!--<img src="assets1/images/logo_sm.png" alt="" height="28">-->
                        <!--</i>-->
                    <!--</a>-->
                </div>

                <!-- Button mobile view to collapse sidebar menu -->
                <div class="navbar navbar-default" role="navigation">
                    <div class="container">

                        <!-- Navbar-left -->
                        

                        <!-- Right(Notification) -->
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="#" class="right-menu-item dropdown-toggle" data-toggle="dropdown">
                                    <i class="mdi mdi-bell"></i>
                                    <span class="badge up bg-success">4</span>
                                </a>

                                <ul class="dropdown-menu dropdown-menu-right arrow-dropdown-menu arrow-menu-right dropdown-lg user-list notify-list">
                                    <li>
                                        <h5>Notifications</h5>
                                    </li>
                                    <li>
                                        <a href="#" class="user-list-item">
                                            <div class="icon bg-info">
                                                <i class="mdi mdi-account"></i>
                                            </div>
                                            <div class="user-desc">
                                                <span class="name">New Signup</span>
                                                <span class="time">5 hours ago</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="user-list-item">
                                            <div class="icon bg-danger">
                                                <i class="mdi mdi-comment"></i>
                                            </div>
                                            <div class="user-desc">
                                                <span class="name">New Message received</span>
                                                <span class="time">1 day ago</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#" class="user-list-item">
                                            <div class="icon bg-warning">
                                                <i class="mdi mdi-settings"></i>
                                            </div>
                                            <div class="user-desc">
                                                <span class="name">Settings</span>
                                                <span class="time">1 day ago</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="all-msgs text-center">
                                        <p class="m-0"><a href="#">See all Notification</a></p>
                                    </li>
                                </ul>
                            </li>

                            

                            <li>
                                <a href="javascript:void(0);" class="right-bar-toggle right-menu-item">
                                    <i class="mdi mdi-settings"></i>
                                </a>
                            </li>

                            <li class="dropdown user-box">
                                <!-- <a href="" class="dropdown-toggle waves-effect user-link" data-toggle="dropdown" aria-expanded="true">
                                    <img src="assets1/images/users/avatar-1.jpg" alt="user-img" class="img-circle user-img">
                                </a> -->
                                 <a href="" class="dropdown-toggle waves-effect user-link " data-toggle="dropdown" aria-expanded="true">  
                                <%
							
							Object fathername =	session.getAttribute("txtFatherName");
                                out.print("<h4>"+fathername +"</h4>");
							%></a>
                                

                                <ul class="dropdown-menu dropdown-menu-right arrow-dropdown-menu arrow-menu-right user-list notify-list">
                                    <li>
                                        <!-- <h5>Hi, John</h5> -->
                                    </li>
                                    <!-- <li><a href="javascript:void(0)"><i class="ti-user m-r-5"></i> Profile</a></li>
                                    <li><a href="javascript:void(0)"><i class="ti-settings m-r-5"></i> Settings</a></li>
                                    <li><a href="javascript:void(0)"><i class="ti-lock m-r-5"></i> Lock screen</a></li> -->
                                    <li><a href="changePasswordForParent.web"><i class="ti-lock m-r-5"></i>Change Password</a></li>
                                    <li><a href="logout.web"><i class="ti-power-off m-r-5"></i> Logout</a></li>
                                </ul>
                            </li>

                        </ul> <!-- end navbar-right -->

                    </div><!-- end container -->
                </div><!-- end navbar -->
            </div>
            <!-- Top Bar End -->


            <!-- ========== Left Sidebar Start ========== -->
            
            <!-- Left Sidebar End -->



            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container">
                        <div class="row">
							<div class="col-xs-12">
								<div class="page-title-box">
                                    <h4 class="page-title">Dashboard</h4>
                                    
                                    <div class="clearfix"></div>
                                </div>
							</div>
						</div>
                        <!-- end row -->

                        <div class="row">

                            <div class="col-lg-6 col-md-4 col-sm-6">
                                <!-- <div class="card-box widget-box-one">
                                    <center><img src="assets1/images/carre_homme.jpg"></center>
                                </div> -->
                                
                                <form action="parent_profile.web" method="post" id = "form1"
								enctype="multipart/form-data">

								<ul class="list-unstyled profile-nav">

									<li><c:choose>
												<c:when test="${! empty m }">
									
									<c:forEach items="${mst}" var="m" begin="0" end="0">
											

													<!-- <img src="assets/img/profile/images.png" class="img-responsive" alt=""/>         -->
													<div class="card-box widget-box-one">
														<center>
															<img class="img-responsive" style="width:550px;height: 330px;"
																src="getUserImageee/<c:out value="${m.intRegistrationId}"/>.web">
														</center>
													</div>
												</c:forEach>
												</c:when>

												<c:otherwise>
													<div class="card-box widget-box-one">
														<center>
															<img src="assets1/images/carre_homme.jpg" style="width:550px;height: 330px;" class="img-responsive">
														</center>
													</div>
												</c:otherwise>
											</c:choose>
										</li>
									<!-- <div align="center">
										<div class="input-group">

											</span> <input type="file" name="blAttachment" class="form-control"
												placeholder="upload">
											
										</div>
									</div> -->
									<br />
									<!-- <div class="form-actions fluid"> -->
									<!-- <div class="col-md-offset-3 col-md-9">
										<div align="center">
											<button type="submit" class="btn green"
												style="margin-right: 168px;">Submit</button>
										</div>
									</div> -->

								</ul>

							</form>
                            </div><!-- end col -->
							
							
							
							<div class="col-lg-2 col-md-4 col-sm-6">
                                <center><div class="card-box widget-box-one">
                                    
                                    <li class="dropdown hidden-xs">
                                <a data-toggle="dropdown" class="dropdown-toggle menu-item" href="#" aria-expanded="false"><img src="assets1/images/leave.png">
                                    </a>
                                <ul role="menu" class="dropdown-menu">
                                    <li><a href="par_leave request.web"><img src="assets1/images/Leave Application.png"></a></li>
                                    <li><a href="par_StudleaveInbox.web"><img src="assets1/images/Leave Inbox.png"></a></li>
                                    
                                </ul>
                            </li>
                                    
                                </div></center>
                            </div><!-- end col -->
							<div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_student profile.web"><img src="assets1/images/Student profile.png"></a></center>
                                </div>
                            </div><!-- end col -->
							
							 <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_marks.web"><img src="assets1/images/Marks.png"></a></center>
                                </div>
                            </div><!-- end col -->

                           

                           <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_attendance.web"><img src="assets1/images/attendence .png"></a></center>
                                </div>
                            </div><!-- end col -->

                            <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_timetable.web"><img src="assets1/images/timetable.png"></a></center>
                                </div>
                            </div><!-- end col -->
                            
                           <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                   
                                <center><a href="par_homework.web"><img src="assets1/images/homework.png"></a></center>
                                    
                                </div>
                            </div><!-- end col -->
                            
                            
                            
                            

                            <!--<div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="ad_student perform.web"><img src="assets1/images/studentsperformance.png"</a></center>
                                </div>
                            </div>--><!-- end col -->
                            
                            
                            

                        </div>
                        <!-- end row -->


                        <div class="row">
                            <div class="col-lg-12">
							
							
							
							
                        		<div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_notification.web"><img src="assets1/images/invitation.png"></a></center>
                                </div>
                            </div><!-- end col -->

                            <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_question&answer.web"><img src="assets1/images/Queries.png"></a></center>
                                </div>
                            </div><!-- end col -->

                            <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_fee details.web"><img src="assets1/images/fee details.png"></a></center>
                                </div>
                            </div><!-- end col -->
                            
                            <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_gps tarcking.web"><img src="assets1/images/gps tracking.png"></a></center>
                                </div>
                            </div><!-- end col -->

                            <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                    <center><a href="par_virtual lib.web"><img src="assets1/images/Virtual Library.png"></a></center>
                                </div>
                            </div><!-- end col -->
							
							 <div class="col-lg-2 col-md-4 col-sm-6">
                                <div class="card-box widget-box-one">
                                   
                                <center><a href="par_syllabus.web"><img src="assets1/images/syllabus.png"></a></center>
                                    
                                </div>
                            </div><!-- end col -->
							
							
							
							
							
							
                            </div><!-- end col -->

                            
                        <!-- end row -->


                        




                    </div> <!-- container -->

                </div> <!-- content -->

                <footer class="footer text-right">
                    2016 © Aikshika.
                    Terms & Conditions
                </footer>

            </div>


            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


            <!-- Right Sidebar -->
            <div class="side-bar right-bar">
                <a href="javascript:void(0);" class="right-bar-toggle">
                    <i class="mdi mdi-close-circle-outline"></i>
                </a>
                <h4 class="">Settings</h4>
                <div class="setting-list nicescroll">
                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">Notifications</h5>
                            <p class="text-muted m-b-0"><small>Do you need them?</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>

                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">API Access</h5>
                            <p class="m-b-0 text-muted"><small>Enable/Disable access</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>

                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">Auto Updates</h5>
                            <p class="m-b-0 text-muted"><small>Keep up to date</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>

                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">Online Status</h5>
                            <p class="m-b-0 text-muted"><small>Show your status to all</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Right-bar -->

        </div>
        <!-- END wrapper -->



        <script>
            var resizefunc = [];
        </script>

        <!-- jQuery  -->
        <script src="assets1/js/jquery.min.js"></script>
        <script src="assets1/js/bootstrap.min.js"></script>
        <script src="assets1/js/detect.js"></script>
        <script src="assets1/js/fastclick.js"></script>
        <script src="assets1/js/jquery.blockUI.js"></script>
        <script src="assets1/js/waves.js"></script>
        <script src="assets1/js/jquery.slimscroll.js"></script>
        <script src="assets1/js/jquery.scrollTo.min.js"></script>
        <script src="../plugins/switchery/switchery.min.js"></script>

        <!-- Counter js  -->
        <script src="../plugins/waypoints/jquery.waypoints.min.js"></script>
        <script src="../plugins/counterup/jquery.counterup.min.js"></script>

        <!--Morris Chart-->
		<script src="../plugins/morris/morris.min.js"></script>
		<script src="../plugins/raphael/raphael-min.js"></script>

        <!-- Dashboard init -->
        <script src="assets1/pages/jquery.dashboard.js"></script>

        <!-- App js -->
        <script src="assets1/js/jquery.core.js"></script>
        <script src="assets1/js/jquery.app.js"></script>
        
        <script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
<script type="text/javascript" src="assets/plugins/ckeditor/ckeditor.js"></script>

    </body>
</html>