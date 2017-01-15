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

<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="headerForTeacher.jsp"%>
<script>
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin

			rules : {

				FromDate : {
					required : true,
				},

				ToDate : {
					required : true,
				},

				Status : {

					required : true,
				}

			},

			messages : {

				FromDate : {
					required : "please select from date"
				},
				ToDate : {
					required : "please select to date"
				},
				Status : {


									required : "please select status"
				}

			},

			highlight : function(element) {

				$(element).parent().addClass('error')

			}

		});

		
	});
	
	$(document).ready(function() {
		if ("${secondTab}" == "11") {
			$('#tab22').trigger('click');
		} else {
			$('#tab21').trigger('click');
		}
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
		<h3 class="page-title">Student Leave Approval</h3>
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
			<li><i class="fa fa-home"></i> 
								Teacher
						 <i class="fa fa-angle-right"></i></li>
			<li>
				<h5>Leave Approval</h5> <!-- <i class="fa fa-angle-right"></i> -->
			</li>
			<!-- <li>
							<a href="#">
							New	
							</a>
						</li> -->
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<c:if test="${not empty approve}">

	<div class="alert alert-success ">
		
		<i class="fa fa-check-circle"></i> <strong>${approve}</strong>
	</div>
</c:if>
<c:if test="${not empty reject}">

	<div class="alert alert-success ">
		
		<i class="fa fa-times"></i> <strong>${reject}</strong>
	</div>
</c:if>

<!--  <div class="alert alert-info">
								<strong>Note:</strong> 
								All (*) marked fields are mandatory.
							</div> -->



<div class="row">
	<div class="col-md-12">

		<%-- <div style = "height:300px;overflow: scroll-y;">
				<div class="portlet box green"  >
								<div class="portlet-title" >
									<div class="caption">
									Student Leave Request 
									</div>
								</div>
								<div class="portlet-body">
									<div class="table-responsive">
										<table class="table table-bordered table-hover">
										<thead>
										<tr>
										<th>
												Student Name
											</th>
 											<th>
												Applied Date
											</th>
											<th>
												 Leave Type
											</th>
											<th>
												 From Date
											</th>
											
											<th>
												To Date
											</th>
											<th>
												 Reason
											</th>
											<th>
												Action
											</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items ="${studentLeaveRequest}" var = "l">
											<c:if test="${l.intStatus==1}">
										<tr>
										<td>${l.mstregistration.txtFirstName} &nbsp;${l.mstregistration.txtLastName}</td>
										<td><fmt:formatDate value="${l.dtAppliedDate}" pattern="dd-MMM-yyyy"/></td>
										<td>${l.mstleave.txtLeaveType }</td>
										<td><fmt:formatDate value="${l.dtFromDate}" pattern="dd-MMM-yyyy"/></td>
										<td><fmt:formatDate value="${l.dtToDate}" pattern="dd-MMM-yyyy"/></td>
										<td>${l.txtReason}</td>
										<td>
										<a href = "#" class="label label-sm label-success" style = "font-weight: bolder;" >Approve</a>
										<a href = "#" class="label label-sm label-danger" style = "font-weight: bolder;">Reject</a>
										
										</td>
										</tr>
										</c:if>
										</c:forEach>
										
										
										
									</tbody>										
										</table>
										
																		
									</div>
								</div>
							</div>
							</div>
							<!-- END BORDERED TABLE PORTLET-->
							
							
							<div style = "height:300px;overflow: scroll-y;">
				<div class="portlet box green"  >
								<div class="portlet-title" >
									<div class="caption">
									Approved/Rejected Leave
									</div>
								</div>
								<div class="portlet-body">
									<div class="table-responsive">
										<table class="table table-bordered table-hover">
										<thead>
										<tr>
										<th>
												Student Name
											</th>
										
											<th>
												Applied Date
											</th>
											<th>
												 Leave Type
											</th>
											<th>
												 From Date
											</th>
											
											<th>
												To Date
											</th>
											<th>
												 Reason
											</th>
											<th>
												Status
											</th>
											<th>
												Approved/Rejected Date
											</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach items ="${studentLeaveRequest}" var = "l">
											<c:if test="${l.intStatus==2 or l.intStatus==3}">
										<tr>
										<td>${l.mstregistration.txtFirstName} &nbsp;${l.mstregistration.txtLastName}</td>
										
										
										<td><fmt:formatDate value="${l.dtAppliedDate}" pattern="dd-MMM-yyyy"/></td>
										<td>${l.mstleave.txtLeaveType }</td>
										<td><fmt:formatDate value="${l.dtFromDate}" pattern="dd-MMM-yyyy"/></td>
										<td><fmt:formatDate value="${l.dtToDate}" pattern="dd-MMM-yyyy"/></td>
										<td>${l.txtReason}</td>
										<td>
										
										<c:if test = "${l.intStatus==2}">
										<p class="label label-sm label-success" style = "font-weight: bolder;">Approved</p>
										</c:if>
										<c:if test = "${l.intStatus==3}">
										<p class="label label-sm label-danger" style = "font-weight: bolder;">Rejected</p>
										</c:if>
										
										</td>
										<td><fmt:formatDate value="${l.dtApprovedDate}" pattern="dd-MMM-yyyy"/></td>
										</tr>
										</c:if>
										</c:forEach>
										
										
										
									</tbody>										
										</table>
										
																		
									</div>
								</div>
							</div>
							</div>
							<!-- END BORDERED TABLE PORTLET-->
							
							
					 --%>

		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption" style="padding-left: 420px">Leave
					Approvals Request</div>
			</div>
			<div class="portlet-body">
				<ul class="nav nav-pills">
					<li class="active"><a href="#tab_2_1" data-toggle="tab" id="tab21">
							Pending Request </a></li>
					<li><a href="#tab_2_2" data-toggle="tab" id="tab22"> Request Status
					</a></li>

				</ul>


				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_2_1">

						<div class="portlet-body">
							<div class="table-responsive" >

<c:choose>
								<c:when test="${not empty studentLeaveRequest}">
										<table class="table table-bordered table-hover" id="sample_editable_1">
											
									
											<thead>
											
												<tr style="background-color: #E5E4E2">
													<th>Student Name</th>
													<th>Applied Date</th>
													<th>Leave Type</th>
													<th>From Date</th>

													<th>To Date</th>
													<th>Reason</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
								
												<c:forEach items="${studentLeaveRequest}" var="l">
													<c:if test="${l.intStatus==1}">
														<tr>
															<td>${l.mstregistration.txtFirstName}
																&nbsp;${l.mstregistration.txtLastName}</td>
															<td><fmt:formatDate value="${l.dtAppliedDate}"
																	pattern="dd-MMM-yyyy" /></td>
															<td>${l.mstleave.txtLeaveType }</td>
															<td><fmt:formatDate value="${l.dtFromDate}"
																	pattern="dd-MMM-yyyy" /></td>
															<td><fmt:formatDate value="${l.dtToDate}"
																	pattern="dd-MMM-yyyy" /></td>
															<td>${l.txtReason}</td>
															<td><a
																href="teac_studLeaveApprove.web?id=${l.intStudentLeaveAppId}"
																class="label label-sm label-success"
																style="font-weight: bolder;">Approve</a> <a
																href="teac_studLeaveReject.web?id=${l.intStudentLeaveAppId}"
																class="label label-sm label-danger"
																style="font-weight: bolder;">Reject</a></td>
														</tr>
													</c:if>
												</c:forEach>


											</tbody>
											
									
								
										</table>
										</c:when>
<c:otherwise>
										<h3 style="text-align: center; font-weight: bolder;"> <i class="fa fa-warning" style="font-size: 25px;"></i> No
											Pending Request</h3>
									</c:otherwise>
</c:choose>
									


							</div>


						</div>
					</div>
					<div class="tab-pane fade " id="tab_2_2">
						<div class="portlet-body">
							<form action="teac_leaveRequestSearch.web" method="post"
								id="form1">
								<table class="table table-striped table-bordered table-hover">
									<tr>
										<th><b>From Date</b></th>
										<th>
										<div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
										<input class="form-control date-picker" id="date1" readonly="readonly" placeholder="From"
											name="FromDate" type="text" value="" /><span
											style="color: red; font-weight: bolder;"> <span
												class="field-validation-valid" id="validationMessages6"></span>
										</span></div></th>
										<th><b>To Date</b></th>
										<th>
										
										<div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
										<input class="form-control date-picker" id="date2" readonly="readonly" placeholder="To"
											name="ToDate" type="text" value="" /><span
											style="color: red; font-weight: bolder;"> <span
												class="field-validation-valid" id="validationMessages7"></span>
										</span></div></th>
										<th><b>Status</b></th>
										<th><select class="form-control" id="select"
											name="Status">
												<option value="">Select</option>
												<option value="1">-- All --</option>
												<option value="2">Approved</option>
												<option value="3">Rejected</option>

										</select> <span style="color: red; font-weight: bolder;"> <span
												class="field-validation-valid" id="validationMessages8"></span>
										</span></th>
										<th>
										<input type="hidden" value="11" name="secondTab">
											<button type="submit"
												class="btn yellow filter-submit margin-bottom"
												id="btnSearch">
												<i class="fa fa-search"></i> Search
											</button>
										</th>
										<!-- id = "projsubmit2"   -->

									</tr>
								</table>
							</form>

							<div class="table-responsive" id ="sd">
								<c:if test="${! empty check}">
									<c:choose>
										<c:when test="${! empty studentLeaveRequest1}">
											<table class="table table-bordered table-hover" id="sample_editable_2">
												<thead>
													<tr style="background-color: #E5E4E2">
														<th>Student Name</th>

														<th>Applied Date</th>
														<th>Leave Type</th>
														<th>From Date</th>

														<th>To Date</th>
														<th>Reason</th>

														<th>Approved/Rejected Date</th>
														<th>Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${studentLeaveRequest1}" var="l">
														<c:if test="${l.intStatus==2 or l.intStatus==3}">
															<tr>
																<td>${l.mstregistration.txtFirstName}
																	&nbsp;${l.mstregistration.txtLastName}</td>


																<td><fmt:formatDate value="${l.dtAppliedDate}"
																		pattern="dd-MMM-yyyy" /></td>
																<td>${l.mstleave.txtLeaveType }</td>
																<td><fmt:formatDate value="${l.dtFromDate}"
																		pattern="dd-MMM-yyyy" /></td>
																<td><fmt:formatDate value="${l.dtToDate}"
																		pattern="dd-MMM-yyyy" /></td>
																<td>${l.txtReason}</td>

																<td><fmt:formatDate value="${l.dtApprovedDate}"
																		pattern="dd-MMM-yyyy" /></td>
																<td><c:if test="${l.intStatus==2}">
																		<p class="label label-sm label-success"
																			style="font-weight: bolder;">Approved</p>
																	</c:if> <c:if test="${l.intStatus==3}">
																		<p class="label label-sm label-danger"
																			style="font-weight: bolder;">Rejected</p>
																	</c:if></td>
															</tr>
														</c:if>
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







		</div>




	</div>



</div>
<!-- END CONTENT -->
<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
	type="text/javascript"></script>
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<script src="assets/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>
<script src="assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="assets/plugins/uniform/jquery.uniform.min.js"
	type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript"
	src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/DT_bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="assets/scripts/core/app.js"></script>
<script src="assets/scripts/custom/table-editable.js"></script>
<!--validation start  -->

<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/additional-methods.js"></script>
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		$('#sample_editable_2').DataTable({
			"bSort" : false
		});
		slider(111);
		
	});
</script>
<%@ include file="footer.jsp"%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
