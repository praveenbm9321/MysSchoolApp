
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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="headerForTeacher.jsp"%>

<script>
	var request;
	function sendInfo() {
		request = new XMLHttpRequest();
		var v1 = document.vinform.classId.value;
		var v2 = document.vinform.sectionId.value;
		var url = "ajax_teacher.web?val1=" + v1 + "&val2=" + v2 ;
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
			
			document.getElementById('teacher').innerHTML = val; 
			$('#select2_sample_modal_2').select2();
			
	
				
			
		}
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".chk").hide();
		$(".check").click(function() {
			if ($(this).is(":checked")) {
				$(".chk").show();
			} else {
				$(".chk").hide();
			}

		});
	});

	$(document).ready(function() {

		$.validator.addMethod("alpha",
				function(value, element) {
					return this.optional(element)
							|| value == value
									.match(/^[a-zA-Z\s]+$/);
				});

		$('#form1').validate({ // initialize the plugin

			rules : {

				classId : {
					required : true,
				},

				sectionId : {
					required : true,
				},

				txtProjectName : {
					required : true,
					alpha:true,
					nowhitespace : false
					maxlength : 200
					/* lettersonly : true */
				},
				txtProjectDescription : {
					required : true,
					maxlength:1000

				},
				dtDueDate : {
					required : true,
					alphanumeric : false,
					date : true
				}

			},

			messages : {

				classId : {
					required : "please select class"
				},
				sectionId : {
					required : "please select section"
				},
				txtProjectName : {

					required : "please enter project name",
					alpha:"alphabets and space only"
				},
				txtProjectDescription : {
					required : "please enter project description"
				},
				dtDueDate : {
					required : "please select due date"
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

				fromDate : {
					required : true,
				},

				toDate : {
					required : true,
				},

			},

			messages : {

				fromDate : {
					required : "please select from date"
				},
				toDate : {
					required : "please select to date"
				}

			},

			highlight : function(element) {

				$(element).parent().addClass('error')

			}

		});

	});
</script>



<style>
#form1 .error, #searchForm .error {
	color: red;
	font-size: 13px;
}
</style>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Project</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group">
				<!-- <button type="button" class="btn blue dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
							<span>
								Actions
							</span>
							<i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li>
									<a href="#">
										Action
									</a>
								</li>
								<li>
									<a href="#">
										Another action
									</a>
								</li>
								<li>
									<a href="#">
										Something else here
									</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">
										Separated link
									</a>
								</li>
							</ul> -->
			</li>
			<li><i class="fa fa-home"></i> <a href="ad_index_For_Teacher.web">
								Teacher
							</a><i class="fa fa-angle-right"></i></li>
			<li>
				<h5>Project</h5>
			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->





<c:if test="${not empty msg1}">

	<div class="alert alert-success">
		
		<i class="fa fa-check-circle"></i> <strong>${msg1}${msg2}</strong>
	</div>
</c:if>


<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>

<div class="row">
	<div class="col-md-12">

		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Project</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<form:form action="teac_projectPosted.web" id="form1" name = "vinform">
						<table class="table table-bordered table-hover">
							<tbody>
								<tr>
									<td style="font-weight: bold">Class <span class="required"
										style="color: red"> * </span></td>
									<td>
									<div class="input-group">
										<span class="input-group-addon"> <i
																		class="fa fa-th-large"></i>
																	</span> 
										<%-- <form:select path="mststudclass.intClassId" class="form-control" id = "class">
										<form:option value="-1" label="--Select Class--"/>
										<form:options items = "${classes}" itemValue="intClassId" itemLabel="txtClassName"/>
										</form:select> --%> <select name="classId"
										class="form-control" style="width: 300px;">
											<option value="">Select class</option>
											<c:forEach var="classes" items="${classes}">
												<option value="${classes.intClassId}">${classes.txtClassName}</option>
											</c:forEach>

									</select></div> <span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages1"></span>
									</span>
									</td>


									<td style="font-weight: bold">Section <span
										class="required" style="color: red"> * </span></td>
									<td>
									<div class="input-group">
										<span class="input-group-addon"> <i
																		class="fa fa-th-large"></i>
																	</span> 
										<%-- <form:select path="mststudclasssection.intSectionId" class="form-control" id="section">
										<form:option value="-1" label="--Select Section--"/>
										<form:options items = "${sections}" itemValue="intSectionId" itemLabel="txtSectionName"/>
										</form:select> --%> <select name="sectionId"
										class="form-control" style="width: 300px;" onchange="sendInfo()">
											<option value="">Select Section</option>
											<c:forEach var="sections" items="${sections}">
												<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
											</c:forEach>

									</select></div> <span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages2"></span>
									</span>
									</td>
								</tr>


								<tr>
									<td style="font-weight: bold">Project Name<span
										class="required" style="color: red"> * </span>
									</td>
									<td>
									<div class="input-group">
																		<span class="input-group-addon"> <i
																			class="fa fa-list-alt"></i>
																		</span> 
									<input type="text" style="width: 300px;"
										id="projectName" name="txtProjectName" data-required="1"
										placeholder="Enter Project Name" class="form-control" /></div> <span
										style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages3"></span>
									</span></td>
									<td style="font-weight: bold">Project Description <span
										class="required" style="color: red"> * </span>
									</td>
									<td><textarea style="width: 400px; resize: none;"
											id="projDesc" class="form-control"
											placeholder="Enter Project Description"
											name="txtProjectDescription"></textarea> <span
										style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages4"></span>
									</span></td>

								</tr>
								<tr>
									<td style="font-weight: bold">Due Date <span
										class="required" style="color: red"> * </span></td>
									<td><div class="input-group">
																		<span class="input-group-addon"><i class="fa fa-calendar"></i> 
																		</span> <input name="dtDueDate"
										class="form-control form-control-inline input-medium date-picker" readonly="readonly" placeholder="Due Date"
										
										data-date-start-date="+0d" id="dtDueDate" /></div> <span
										style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages5"></span>
									</span></td>
									<td style="font-weight: bold"><a href="teac_profile.web"><input
											type="checkbox" class="check"></a>Assign To</td>
									<td>
										<!-- id="select2_sample_modal_2"  class="form-control" multiple-->
										 <div class="chk">
										 <%--<div class="input-group">
																		<span class="input-group-addon"> <i
																			class="fa fa-user"></i>
																		</span> 
											<select class="form-control select2" style="width: 300px;"
												id="select2_sample_modal_2" name="select"
												placeholder="Select Student" multiple>


												<c:forEach items="${students}" var="s">
													<option value="${s.intRegistrationId}">${s.txtFirstName}
														&nbsp;${s.txtLastName}</option>

												</c:forEach>

											</select></div> --%> 
										 <div id = "teacher"></div>
										</div>
										
									</td>
								</tr>






							</tbody>
						</table>

						<div style="padding-left: 450px">
							<input type="submit" class="btn green" value="Submit" />
						</div>
						<!-- id = "projsubmit1" -->
					</form:form>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->

		<c:if test="${not empty dateMsg}">

			<div class="alert alert-warning " style="background-color: #f7e989;color: #716252">
		
			<i class="fa fa-warning"></i>
		<strong>${dateMsg}</strong>
	</div>
		</c:if>

		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Project History</div>
				<br />

				<form action="teachProjectHistory.web" id="searchForm">
					<!-- <label class="control-label col-md-1">From</label> -->

					<div class="col-md-4">
						<table class="table table-striped table-bordered table-hover">
							<tr>
								<td><span class="input-group-addon">From</span></td>
								<td><div class="input-group">
																		<span class="input-group-addon"><i class="fa fa-calendar"></i> 
																		</span> <input name="fromDate"
										class="form-control form-control-inline input-medium date-picker"
										readonly="readonly" id="date1" placeholder="From">
								</div> <span style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages6"></span>
								</span></td>
								<td><span class="input-group-addon"> to </span></td>
								<td><div class="input-group">
																		<span class="input-group-addon"><i class="fa fa-calendar"></i> 
																		</span><input name="toDate"
										class="form-control form-control-inline input-medium date-picker" placeholder="To"
										readonly="readonly" id="date2">
								</div> <span style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages7"></span>
								</span></td>
								<td><input type="submit" class="btn btn-primary"
									value="Search"></td>
							</tr>


						</table>
						<!-- id = "projsubmit2"	 -->
					</div>
				</form>
			</div>


			<div class="portlet-body">
				<div class="table-responsive">
					<c:if test="${not empty history}">

						<c:choose>
							<c:when test="${not empty projectHistory}">
								<table id="sample_editable_1"
									class="table table-bordered table-hover">
									<thead>
										<tr style="background-color: #E5E4E2">
											<th>Date</th>
											<th>Class</th>
											<th>Section</th>

											<th>Project Name</th>
											<th>Project Description</th>
											<th>Assign To</th>
											<th>Submission Date</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${projectHistory}" var="history">
											<tr>
												<td><fmt:formatDate value="${history.dtAssignDate}"
														pattern="dd-MMM-yyyy" /></td>
												<td>${history.mststudclass.txtClassName}</td>
												<td>${history.mststudclasssection.txtSectionName}</td>
												<td>${history.txtProjectName}</td>
												<td>${history.txtProjectDescription}</td>
												<td>
													<c:choose>
													<c:when test="${ empty history.mstregistration.intRegistrationId }">
													All 
													</c:when>
													<c:otherwise>
													${history.mstregistration.txtFirstName}&nbsp;${history.mstregistration.txtLastName}
													
													</c:otherwise>
													</c:choose>
													</td>
												<td><fmt:formatDate value="${history.dtDueDate}"
														pattern="dd-MMM-yyyy" /></td>
											</tr>
										</c:forEach>



									</tbody>
								</table>
							</c:when>

							<c:otherwise>
								<h3 style="text-align: center; font-weight: bolder;">
								<i class="fa fa-warning" style="font-size: 25px;"></i>No record found
								</h3>
							</c:otherwise>
						</c:choose>
					</c:if>

				</div>
			</div>












		</div>




	</div>



</div>

<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		//App.init();
		TableEditable.init(); 
		 $('#sample_editable_1').DataTable({
			 
			 "bDestroy":true,
			"bSort" : false,
			"bRetrieve":false
		   // aaData: response.data
		});
		//
		slider(108);
	});
</script>
<%@ include file="footer.jsp"%>