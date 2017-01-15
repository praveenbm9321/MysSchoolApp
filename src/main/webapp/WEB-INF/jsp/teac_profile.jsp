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
<%@include file="headerForTeacher.jsp"%>
<script>
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin

			rules : {

				txtPerAddress : {
					required : true,
				},

				txtTempAddress : {
					required : true,
				},

				txtEmailId : {

					required : true,
					email : true
				},
				txtFmobileNumber : {
					required : true,
					digits : true,
					minlength : 10,
					maxlength : 10

				},
				txtFatherName : {
					required : true
				},
				txtCity : {
					required : true
				}

			},

			messages : {

				txtPerAddress : {
					required : "permanent address cannot be empty"
				},
				txtTempAddress : {
					required : "temporary address cannot be empty"
				},
				txtEmailId : {

					required : "email Id cannot be empty"
				},
				txtFmobileNumber : {
					required : "mobile number cannot be empty"
				},
				txtFatherName : {
					required : "father/Husband Name cannot be empty"
				},
				txtCity : {
					required : "city cannot be empty"
				}

			},

			highlight : function(element) {

				$(element).parent().addClass('error')

			}

		});

	});
</script>
<style>
#form1 .error {
	color: red;
	font-size: 13px;
}
</style>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Profile</h3>
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
			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Teacher.web"> Teacher </a> <i
				class="fa fa-angle-right"></i></li>
			<li>
				<h5>Profile</h5> <!-- <i class="fa fa-angle-right"></i> -->
			</li>
			<!-- <li>
							<a href="#">
								Edit Profile
							</a>
						</li> -->
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->





<c:if test="${not empty msg1}">
	<div
		class="alert alert-success >
		<i class="fa fa-check-circle"></i> <strong>${msg1}${msg2}</strong>
	</div>
</c:if>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>Profile
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

			<!-- BEGIN FORM-->
			<!-- <form action="#" id="form_sample_1" class="form-horizontal"> -->

			<!-- <div class="portlet-body form">

				<div class="form-body"> -->
			<div class="portlet-body">
				<div class="table-responsive">

					<form action="profileEdit.web" class="form-horizontal"
						 id="form1">
						<c:forEach items="${teacherProfile}" begin="0" end="0"
							var="profile">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th><b> First Name</b><span class="required"> <font
												color="red">*</font>
										</span></th>
										<th><input name="txtFirstName" 
												value="${profile.txtFirstName}"
												class="form-control" readonly="readonly" /></th>


										<th><b> Middle Name</b></th>
										<th><input name="txtMiddleName"
												value="${profile.txtMiddleName}" 
												class="form-control" readonly="readonly" /></th>
									</tr>
								</thead>


								<thead>
									<tr>

										<th><b> Last Name</b><span class="required"> <font
												color="red">*</font>
										</span></th>
										<th><input name="txtLastName"
												value="${profile.txtLastName}" 
												class="form-control" readonly="readonly" /></th>

										<th><b> Father/Husband Name</b><span class="required">
												<font color="red">*</font>
										</span></th>
										<th><input name="txtFatherName"
												value="${profile.txtFatherName}" 
												class="form-control"  /></th>



									</tr>
								</thead>


								<thead>
									<tr>

										<th><b> Gender</b><span class="required"> <font
												color="red">*</font>
										</span></th>
										<th><input 
												value="${profile.mstgender.txtGenderName}" 
												class="form-control" readonly="readonly" />
												<input type = "hidden" name="mstgender" value="${profile.mstgender.intGenderId}"></th>


										<th><b> Religion</b></th>
										<th>
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-user"></i>
												</span> <select
													class="table-group-action-input form-control input-inline input-small input-sm"
													name="mstreligion">
													<option value="">Select</option>
													<c:forEach var="r" items="${religion}">
														<c:if
															test="${profile.mstreligion.intReligionId==r.intReligionId}">
															<option value="${r.intReligionId}"
																selected="${r.txtReligionName}">${r.txtReligionName}</option>
														</c:if>
														<c:if
															test="${profile.mstreligion.intReligionId!=r.intReligionId}">
															<option value="${r.intReligionId}">${r.txtReligionName}</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</th>



									</tr>
								</thead>


								<thead>
									<tr>
										<th><b>Martial Status</b><span class="required"> <font
												color="red">*</font>
										</span></th>
										<th><div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-user"></i>
												</span> <select
													class="table-group-action-input form-control input-inline input-small input-sm"
													name="mstmaritalstatus">
													<option value="">Select</option>
													<c:forEach var="m" items="${marital}">
														<c:if
															test="${profile.mstmaritalstatus.intMaritalStatusId==m.intMaritalStatusId}">
															<option value="${m.intMaritalStatusId}"
																selected="${m.txtMaritalStatus}">${m.txtMaritalStatus}</option>
														</c:if>
														<c:if
															test="${profile.mstmaritalstatus.intMaritalStatusId!=m.intMaritalStatusId}">
															<option value="${m.intMaritalStatusId}">${m.txtMaritalStatus}</option>
														</c:if>
													</c:forEach>

												</select>
											</div></th>


										<th><b>City</b></th>
										<th><input name="txtCity" value="${profile.txtCity}"
												data-required="1" class="form-control" /></th>

									</tr>
								</thead>


								<thead>
									<tr>
										<th><b>Mobile Number</b><span class="required"> <font
												color="red">*</font>
										</span></th>
										<th><input name="txtFmobileNumber" id="mobileNumber"
												value="${profile.txtFmobileNumber}"
												class="form-control" /></th>

										<th><b>Email ID</b><span class="required"> <font
												color="red">*</font>
										</span></th>
										<th><input name="txtEmailId" id="emailID"
												value="${profile.txtEmailId}" 
												class="form-control" /></th>

									</tr>
								</thead>


								<thead>
									<tr>
										<th><b>Joining Date</b><span class="required"> <font
												color="red">*</font>
										</span></th>
										<th><div class="input-group">
												<span class="input-group-addon"><i
													class="fa fa-calendar"></i> </span> <input
													
													type="text" autocomplete="off" name="dtJoiningDate" class="form-control"
													value="${profile.dtJoiningDate}" readonly="readonly" />
											</div></th>


										<th><b>Experience(in years only)</b><span
											class="required"> <font color="red">*</font>
										</span></th>
										<th><input class="form-control" name="txtExperience"
												value="${profile.txtExperience}" readonly="readonly" /></th>
									</tr>
								</thead>

								<thead>
									<tr>
										<th><b> Designation</b></th>
										<th><input class="form-control"
												name="txtDesignation" value="${profile.txtDesignation}"
												readonly="true" /></th>

										<th><b> Permanent Address</b><span class="required">
												<font color="red">*</font>
										</span></th>
										<th><textarea class="form-control" style="resize: none"
												rows="2" name="txtPerAddress">${profile.txtPerAddress}</textarea></th>

										<%-- <th><b> CTC</b></th>
										<th><form:input class="form-control" name="mstbank"
												value="${profile.mstbank.txtCtc}" readonly="true" /></th> --%>
									</tr>
								</thead>
								<thead>
									<tr>
										
										<th align="center"><b> Temporary Address</b><span
											class="required"> <font color="red">*</font>
										</span></th>
										<th><textarea class="form-control" style="resize: none"
												rows="2" name="txtTempAddress">${profile.txtTempAddress}</textarea></th>
												<th></th><th></th>
									</tr>
								</thead>

							</table>

							<%-- <div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold">Name</label>
								<div class="col-md-4">
									<form:input name="txtFirstName" value="${profile.txtFirstName} ${profile.txtLastName}"
										data-required="1" class="form-control" readonly="true" />
								</div>
							</div>



							<div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold">Designation</label>
								<div class="col-md-4">
									<form:input name="txtDesignation"
										value="${profile.txtDesignation}" data-required="1"
										class="form-control" readonly="true" />
								</div>
							</div>




							<div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold">Experience</label>
								<div class="col-md-4">
									<form:input name="txtExperience"
										value="${profile.txtExperience}" data-required="1"
										class="form-control" readonly="true" />
								</div>
							</div>



							<div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold">Permanent
									Address <span class="required" style="color: red"> * </span>
								</label>
								<div class="col-md-4">
									<textarea name="txtPerAddress" class="form-control"
										id="perAddr" style="resize: none;">${profile.txtPerAddress}</textarea>
									<span style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages1"></span>
									</span>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold">Temporary
									Address <span class="required" style="color: red"> * </span>
								</label>
								<div class="col-md-4">
									<textarea name="txtTempAddress" class="form-control"
										id="tempAddr" style="resize: none;">${profile.txtTempAddress}</textarea>
									<span style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages2"></span>
									</span>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold">Email
									ID <span class="required" style="color: red"> * </span>
								</label>
								<div class="col-md-4">
									<form:input name="txtEmailId" id="emailID"
										value="${profile.txtEmailId}" data-required="1"
										class="form-control" />
									<span style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages3"></span>
									</span>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold">Mobile
									Number <span class="required" style="color: red"> * </span>
								</label>
								<div class="col-md-4">
									<form:input name="txtFotherNumber" id="mobileNumber"
										value="${profile.txtFmobileNumber}" data-required="1"
										class="form-control" />
									<span style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages4"></span>
									</span>
								</div>
							</div>





							 --%>
							<div class="form-actions fluid">
								<div style="padding-left: 130px"
									class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn green">Update</button>
									<!-- id = "profileSubmit" -->
	</div>
	</div>
	</c:forEach>
	</form>
	<!-- END FORM-->
	</div>
	</div>
	<!-- END VALIDATION STATES-->
	</div>
	</div>



	</div>
	<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {

			slider(101);
		});
	</script>
	<!-- END CONTENT -->
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@include file="footer.jsp"%>