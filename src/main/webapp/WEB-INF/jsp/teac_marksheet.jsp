
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
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<%@ include file = "headerForTeacher.jsp" %>	 
			
			<script>
	var request;
	function sendInfo() {
		request = new XMLHttpRequest();
		var v1 = document.vinform.classId.value;
		var v2 = document.vinform.sectionId.value;
		var url = "ajax_teacher_marks.web?val1=" + v1 + "&val2=" + v2 ;
		try {
			request.onreadystatechange = getInfo;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getInfo() {
		if (request.readyState == 4) {
			var val = request.response;
			/* window.onload = function(){
				document.getElementById('teacher').innerHTML = val;
				}; */
				//document.getElementsByName("studs").innerHTML = val; 
			document.getElementById('teacher').innerHTML = val; 
	// document.getElementsByTagName("c:foreach").innerHTML = val;
				
			
		}
	}
</script>
			
<script>
			$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin

			rules : {


				classId:
				{
				required:true,
				},

				sectionId:{
					required:true,
					},
				
					intTermId : {

					required : true,

					

				},
				student:
						{
						required : true,

						
						},
						file:{
							
	required : true,
					extension : "xls|xlsx"
				}

			},

			messages : {

				classId : {
					required : "please select class"
				},
				sectionId : {
					required : "please select section"
				},
				intTermId : {

					required : "please select exam term"
				},
				student : {
					required : "please select student name"
				},
				file : {
					required : "please choose file",
					extension : "only excel files (.xls or .xlsx) are allowed"
				}

			},

			highlight : function(element) {

				$(element).parent().addClass('error')

			}

		});

	});

	$(document).ready(function() {

		$('#searchForm').validate({ // initialize the plugin

			rules : {
				classId:
				{
				required:true,
				},

				sectionId:{
					required:true,
					},

					intTermId : {
					required : true,
				}

			},

			messages : {
				classId : {
					required : "please select class"
				},
				sectionId : {
					required : "please select section"
				},

				intTermId : {
					required : "please select exam term"
				}

			},

			highlight : function(element) {

				$(element).parent().addClass('error')

			}

		});

	});
</script>



<style>
#form1 .error ,#searchForm .error{
	color: red;
	font-size: 13px;
	
}
</style>

<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					Student MarkSheet
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li class="btn-group">
							
						</li>
						<li>
							<i class="fa fa-home"></i>
							<a href="ad_index_For_Teacher.web">
								Teacher
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<h5>
								Student MarkSheet
							</h5>
						</li>
						
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			 <c:if test = "${not empty msg}">
			 
			 							<div class="alert alert-success ">
		
			 							<i class="fa fa-check-circle"></i>
								<strong>${msg}</strong>
							</div>
							</c:if>
			 <div class="alert alert-info">
								<strong>Note:</strong> 
								All (*) marked fields are mandatory.
							</div>
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box green" >
						<div class="portlet-title" >
							<div class="caption" >
								<i class="fa fa-reorder"></i>Upload Student Marksheet
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div> -->
						</div>
						<div class="portlet-body form">
						
							<!-- BEGIN FORM-->
							<form:form action="marksheetupload.web" name = "vinform" class="form-horizontal" enctype="multipart/form-data" id = "form1" commandName="mstuploadstudmarksheet">
								
									

									<div class="form-group">
										<label class="col-md-3 control-label" style = "font-weight:bold">Class
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9" style = "width:360px;">
										<%-- <form:select path="mststudclass.intClassId" class="form-control" id="class">
										<form:option value="-1" label="--Select Class--"/>
										<form:options items = "${classes}" itemValue="intClassId" itemLabel="txtClassName"/>
										</form:select> --%>
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span> 
										<select name="classId" class="form-control" >
										<option value="">Select class</option>
														<c:forEach var="classes" items="${classes}">
															<option value="${classes.intClassId}">${classes.txtClassName}</option>
														</c:forEach>
										
										</select></div>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages1"></span>
										</span>
										
										</div>
									</div>
									
									
									
									<div class="form-group">
										<label class="col-md-3 control-label" style = "font-weight:bold">Section
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9" style = "width:360px;" >
										<%-- <form:select path="mststudclasssection.intSectionId" class="form-control" id="section">
										<form:option value="-1" label="--Select Section--"/>
										<form:options items = "${sections}" itemValue="intSectionId" itemLabel="txtSectionName"/>
										</form:select> --%>
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span> 
										
										<select name="sectionId" class="form-control" onchange="sendInfo()">
										<option value="">Select Section</option>
														<c:forEach var="sections" items="${sections}">
															<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
														</c:forEach>
										
										</select></div>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages2"></span>
										</span>
										</div>
									</div>
									
									
									<div class="form-group">
										<label class="col-md-3 control-label" style = "font-weight:bold">Exam
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9" style = "width:360px;">
										<%-- <form:select path="mstterms.intTermId" class="form-control" id = "examType">
										<form:option value="-1" label="--Select Exam Term--"/>
										<form:options items="${examTerms}" itemLabel="txtTermName" itemValue="intTermId"/>
										
										</form:select> --%>
										<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-book"></i>
												</span>
										<select name="intTermId" class="form-control" >
										<option value="">Select Exam Term</option>
														<c:forEach var="mstterms" items="${examTerms}">
															<option value="${mstterms.intTermId}">${mstterms.txtTermName}</option>
														</c:forEach>
										
										</select>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages3"></span>
										</span>
										</div>
									</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label" style = "font-weight:bold">Student Name
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9" style = "width:360px;">
										<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-user"></i>
												</span>
										<form:select path="mstregistration.intRegistrationId" class="form-control" id = "student">
										<form:option value="-1" label="--Select Student--"/>
										<form:options items="${students}" itemLabel="txtFirstName" itemValue="intRegistrationId"/>
										
										</form:select>
										
										</div>
										
										
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages4"></span>
										</span>
										</div>
									</div> --%>
									<div id = "teacher"></div>
									
									<div class="form-group">
										<label class="col-md-3 control-label" style = "font-weight:bold">Marksheet
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9" style = "width:360px;">
											<!-- <span class="btn green fileinput-button" style = "width:330px;background-color:grey;">
												<i class="fa fa-ban-circle"></i>
													<span>
													Browse Excel Sheet
													</span>
												<input type="file" name="file" id = "file"/>
											</span> -->
											<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-upload"></i>
											</span> <input type="file" class="form-control" placeholder="upload" enctype="multipart/form-data"
												name="file" id = "file">
											</div>
											<p>
											Only (<strong>.xls and .xlsx</strong>)
											files are allowed<br> (The maximum file size for uploads
											is <strong>5 MB</strong>)
										</p>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages5"></span>
										</span>
										</div>  
										
									</div>
									<!-- <div class="alert alert-info">
								<strong>Note:</strong> 
								Only (.xsl) (.xslt) extension files are allowed.
							</div> -->

									
								<div class="form-actions fluid">
									<div style = "padding-left:130px" class="col-md-offset-3 col-md-9">
										<input type="submit"  class="btn green" value="Submit" ><!-- id ="marksubmit1" -->
										
									</div>
								</div>
							</form:form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box green">
								<div class="portlet-title" >
									<div class="caption">
									Uploaded Marksheets
									</div>
								<%-- <form action = "searchMarksheet.web" id = "searchForm">
								<div class="col-md-4">
									<table class="table table-striped table-bordered table-hover">
									<tr>
									<td><span class="input-group-addon" style="color:black;font-weight:bold;">
													Exam
										<span class="required" style="color:red;">
											 *
										</span>
												</span></td>
									<td><div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-book"></i>
												</span><select id = "examType2" name = "exam" class="form-control" style="width:300px;">
										<option value="">Select Exam</option>
										<c:forEach items = "${examTerms}" var = "e">
										<option value="${e.intTermId}">${e.txtTermName}</option>
										
										</c:forEach>
										</select></div></td>
									
									<td><input type="submit" class="btn btn-primary" value="Search" ></td>
									</tr>
									<!-- id = "projsubmit2" -->
									</table>
																	
									</div>
									</form> --%>
									
									
									<form action = "searchMarksheet.web" id = "searchForm">
								<table class="table table-striped table-bordered table-hover">
									<tr>
										<th><b>Class</b><font color="red">
											 *
										</font></th>
										<th>
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span> 
										<select name="classId" class="form-control" >
										<option value="">Select class</option>
														<c:forEach var="classes" items="${classes}">
															<option value="${classes.intClassId}">${classes.txtClassName}</option>
														</c:forEach>
										
										</select></div></th>
										<th><b>Section</b><font color="red">
											 *
										</font></th>
										<th>
										
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span> 
										
										<select name="sectionId" class="form-control" >
										<option value="">Select Section</option>
														<c:forEach var="sections" items="${sections}">
															<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
														</c:forEach>
										
										</select></div></th>
										<th><b>Exam Type</b><font color="red">
											 *
										</font></th>
										<th><div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-book"></i>
												</span>
										<select name="intTermId" class="form-control" >
										<option value="">Select Exam Term</option>
														<c:forEach var="mstterms" items="${examTerms}">
															<option value="${mstterms.intTermId}">${mstterms.txtTermName}</option>
														</c:forEach>
										
										</select></div></th>
										<th>
										
											<button type="submit"
												class="btn btn-primary"
												id="btnSearch">
												<i class="fa fa-search"></i> Search
											</button>
										</th>
										<!-- id = "projsubmit2"   -->

									</tr>
								</table>
							</form>
					</div>
								
								

								<div class="portlet-body">
									<div class="table-responsive">
									
									
									
									<%-- <div class="col-md-7">
									<div class="form-group">
										<label class="col-md-2 control-label" style = "font-weight:bold">Exam
										<span class="required">
											 *
										</span>
										</label>
										<form action = "searchMarksheet.web" id = "searchForm">
										<div class="col-md-7" >
										<select id = "examType2" name = "exam" class="form-control" >
										<option value="">Select Exam</option>
										<c:forEach items = "${examTerms}" var = "e">
										<option value="${e.intTermId}">${e.txtTermName}</option>
										
										</c:forEach>
										</select>
										<input type="hidden"> 
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages6"></span>
										</span>
										
										</div>
										<div class="col-md-2" ><input type="submit"  class="btn green" value="Search" >
										</div>
										</form>
										<!-- id ="marksubmit2" -->
									</div>
									
									</div>
									<br/><br/> --%>
									<c:if test = "${not empty check}">
									<c:choose>
										<c:when test = "${ not empty studentMarksheet}">
					 					<table id="sample_editable_1"
									class="table table-bordered table-hover">
										<thead>
										<tr style="background-color: #E5E4E2">
											<th>
												Date
											</th>
											<th>
												 Class
											</th>
											<th>
												 Section
											</th>
											<!-- <th>
												 Student Id
											</th> -->
											<th>
												Student Name
											</th>
											<th>
												 Exam
											</th>
											<th>
												 Action
											</th>
											
											
											
										</tr>
										</thead>
										<tbody>	
										
										
										
										<c:forEach items = "${studentMarksheet}" var = "m">							
											<tr>
												<td><fmt:formatDate value="${m.dtDate}" pattern="dd-MMM-yyyy"/></td>
												<td>${m.mststudclass.txtClassName}</td>
												<td>${m.mststudclasssection.txtSectionName}</td>
												<!-- <td>SD001</td> -->
												<td>${m.mstregistration.txtFirstName}&nbsp;${m.mstregistration.txtLastName}</td>
												<td>${m.mstterms.txtTermName}</td>
												<td>
												<a
		href="marksheetViewOrDownload.web?id=${m.intUploadStudMarksheetId}">
																		<button type="button" class="btn green">
																			<i class="fa fa-upload"></i> Download
																		</button>
																	</a>
																	
												
												
												
												
												
											</tr>
										</c:forEach>	
										
										
										
										</tbody>										
										</table>
										</c:when>
										<c:otherwise><h3 style="text-align: center; font-weight: bolder;">
								<i class="fa fa-warning" style="font-size: 25px;"></i>No record found
								</h3>
										</c:otherwise>
										</c:choose>
										</c:if>
																				
									</div>
								</div>
							</div>
							<!-- END BORDERED TABLE PORTLET-->
				</div>
					
			</div>
					<!-- END VALIDATION STATES-->
				
			
			
			
			
		
	<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function() {  
	TableEditable.init();
	$('#sample_editable_1').dataTable({
	    //destroy: true,
	    
	     "bDestroy":true,
		    "bSort":false,
	   // refresh: true,
	   // aaData: response.data,
	});
	   slider(105);
	});
</script>
<%@ include file = "footer.jsp" %>	 