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
<%@ include file="header.jsp"%>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#leaveForm')
								.submit(
										function() {
											var error = 0;
											if (!($('.input-smalls.case.ss')
													.is(':checked'))) {
												error = 1
												//alert("Please check atleast one to do this Operation");
												$('#notSelected').addClass(
														"alert alert-success");
												$('#notSelected')
														.html(
																"Please select atleast one row");
												//$(".ss").prop('checked', true);
												setTimeout(
														function() {
															$('#notSelected')
																	.removeClass(
																			"alert alert-success");
															$('#notSelected')
																	.html("");
														}, 3000);
											}
											if (error) {
												return false;
											} else {
												return true;
											}
										});
						$('#sAll').change(
								function() {
									if (false == $(this).prop("checked")) {
										$(".input-smalls.case.ss").prop(
												'checked', false);
										$('.input-smalls.case.ss').parents(
												'span').removeClass("checked");
									} else {
										$(".input-smalls.case.ss").prop(
												'checked',
												$(this).prop("checked"));
										$('.input-smalls.case.ss').parents(
												'span').addClass("checked");
									}

									//alert($('.ss').val());
									//$(".ss")[0].checked = true;
								});
						$('.input-smalls.case.ss')
								.change(
										function() {
											//uncheck "select all", if one of the listed checkbox item is unchecked
											if (false == $(this)
													.prop("checked")) { //if this item is unchecked
												$("#sAll").prop('checked',
														false); //change "select all" checked status to false
												$('#sAll').parents('span')
														.removeClass("checked");
											}
											//check "select all" if all checkbox items are checked
											if ($('.input-smalls.case.ss:checked').length == $('.input-smalls.case.ss').length) {
												$("#sAll")
														.prop('checked', true);
												$('#sAll').parents('span')
														.addClass("checked");
											}
										});
						
						if("${statusForScndtab}" == "11"){
							$('#tab22').trigger('click');
						}else{
							$('#tab21').trigger('click');
						}
						//alert(${statusForScndtab});
					});
	$(document).ready(function() {
		$('#reqStatusForm').validate({ // initialize the plugin
			rules : {
				fromDate : {
					required : true
				},
				toDate : {
					required : true
				},
				status : {
					required : true
				}
			},

			messages : {
				fromDate : {
					required : "From date required"
				},
				toDate : {
					required : "To date required"
				},
				status : {
					required : "Status required"
				}
			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
	});
</script>
<style>
#reqStatusForm .error {
	color: red;
	font-size: 12px;
}
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Admin</h3>
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
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Leave Info</h5></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<!--  <div class="alert alert-info">
								<strong>Note:</strong> 
								All (*) marked fields are mandatory.
							</div> -->



<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption" style="padding-left: 420px">Leave
					Requests</div>
			</div>
			<div class="portlet-body">
				<ul class="nav nav-pills">
					<li class="active"><a href="#tab_2_1" id="tab21"
						data-toggle="tab"> Pending Request </a></li>
					<li><a href="#tab_2_2" id="tab22" data-toggle="tab">
							Request Status </a></li>

				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_2_1">
						<div class="" id="notSelected"></div>
						<c:choose>
							<c:when test="${not empty msg1 }">
								<div class="alert alert-success">
									<b><i class="fa fa-check-circle"></i>${msg1}${msg2}</b>
								</div>

							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty msgReqList }">
										<div class="alert alert-success">
											<b><i class="fa fa-check-circle"></i>${msgReqList}</b>
										</div>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<div class="portlet-body">
							<div class="table-responsive">

								<form action="ad_leaveInfo.jsp" method="post" name="leaveForm" //edit avi *.jsp
									id="leaveForm">

									<table class="table table-striped table-bordered table-hover"
										id="sample_1">
										<thead>
											<tr style="background-color: #E5E4E2">
												<th class="table-checkbox"><input type="checkbox"
													id="sAll" class="group-checkable"
													data-set="#sample_1 .checkboxes" /> All</th>
												<th>Name</th>
												<th>Applied Date</th>
												<th>Leave Type</th>
												<th>From Date</th>
												<th>To date</th>
												<th>Reason</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${not empty teachList }">
													<c:forEach var="teaLea" items="${teachList}">
														<tr class="odd gradeX">
															<td><input type="checkbox" id="checkId"
																name="teachLeaveReqIds"
																value="${teaLea.intTeacheLeaveAppId }"
																class="input-smalls case ss"></td>

															<td>${teaLea.mstregistration.txtFirstName }&nbsp;${teaLea.mstregistration.txtLastName }</td>

															<td><fmt:formatDate value="${teaLea.dtAppliedDate }"
																	pattern="dd-MMM-yyyy" /></td>

															<c:forEach var="ccc" items="${leaveTypeList}">
																<c:if
																	test="${ccc.intLeaveId == teaLea.mstleave.intLeaveId }">
																	<td>${ccc.txtLeaveType }</td>
																</c:if>
															</c:forEach>

															<td><fmt:formatDate value="${teaLea.dtFromDate }"
																	pattern="dd-MMM-yyyy" /></td>

															<td><fmt:formatDate value="${teaLea.dtToDate }"
																	pattern="dd-MMM-yyyy" /></td>

															<td>${teaLea.txtReason }</td>

															<!-- <td><button type="submit" class="btn green">Download</button>
													</td> -->
														</tr>
													</c:forEach>
													<tr>
														<td colspan="8" align="center">
															<button type="submit" class="btn green" name="action"
																value="app" id="sss">
																<i class="fa fa-check-square-o"></i>Approve
															</button>
															<button type="submit" class="btn red" name="action"
																value="rej" id="sss">
																<i class="fa fa-times"></i>Reject
															</button>
														</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="8">
															<div align="center">
																<h3>
																	<i class="fa fa-warning" style="font-size: 25px;"></i>
																	No Records Found
																</h3>
															</div>
														</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="tab_2_2">
						<div class="portlet-body">
							<form action="ad_leave_request_check.web" method="post"
								id="reqStatusForm" name="reqStatusForm">
								<table class="table table-striped table-bordered table-hover"  >
									<tr>
										<th><b>From Date</b><span class="required"
											style="color: red"> * </span></th>
										<th>

											<div class="input-group">
												<span class="input-group-addon"><i
													class="fa fa-calendar"></i> </span> <input placeholder="From"
													class="form-control date-picker" name="fromDate"
													type="text" value="" data-date="2012-09-26"
													data-date-format="yyyy-mm-dd" readonly="readonly" /><span
													style="color: red; font-weight: bolder;"> </span>
											</div>
										</th>
										<th><b>To Date</b><span class="required"
											style="color: red"> * </span></th>
										<th>
											<div class="input-group">
												<span class="input-group-addon"><i
													class="fa fa-calendar"></i> </span> <input placeholder="To"
													class="form-control date-picker" name="toDate" type="text"
													value="" data-date="2012-09-26"
													data-date-format="yyyy-mm-dd" readonly="readonly" /><span
													style="color: red; font-weight: bolder;"> </span>
											</div>
										</th>
										<th><b>Status</b><span class="required"
											style="color: red"> * </span></th>
										<th><select class="form-control" name="status">
												<option value="">Select Status</option>
												<option value="1">-- All --</option>
												<option value="2">Approved</option>
												<option value="3">Rejected</option>
										</select></th>
										<th><input type="hidden" value="11" name="secondTab">
											<button type="Submit" id="projsubmit2"
												class="btn yellow filter-submit margin-bottom"
												id="btnSearch">
												<i class="fa fa-search"></i> Search
											</button></th>
									</tr>
								</table>
							</form>
							<c:choose>
								<c:when test="${not empty msg11 }">
									<div class="alert alert-success">
										<b><i class="fa fa-check-circle"></i>${msg11}</b>
									</div>
								</c:when>
							</c:choose>
							<div class="table-responsive" id="sd">
								<c:choose>
									<c:when test="${not empty msg22}">
										<c:choose>
											<c:when test="${! empty teachLeaveList}">
												<table class="table table-bordered table-hover" id="sample_editable_2">
													<thead>
														<tr style="background-color: #E5E4E2">
															<th>Teacher Name</th>

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
														<c:forEach items="${teachLeaveList}" var="l">
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
														</c:forEach>

													</tbody>

												</table>
											</c:when>
											<c:otherwise>
												<div align="center">
													<h3>
														<i class="fa fa-warning" style="font-size: 25px;"></i> No
														Records Found
													</h3>
												</div>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${not empty teachLeaveListGet }">
												<table class="table table-bordered table-hover" id="sample_editable_2">
													<thead>
														<tr style="background-color: #E5E4E2">
															<th>Teacher Name</th>

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
														<c:forEach items="${teachLeaveListGet}" var="l">
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
														</c:forEach>
													</tbody>
												</table>
											</c:when>
											<c:otherwise>
												<div align="center">
													<h3>
														<i class="fa fa-warning" style="font-size: 25px;"></i> No
														Records Found
													</h3>
												</div>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
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
		
		$('#sample_editable_2').DataTable({
			"bSort" : false
		});
		

		slider(6);
	});
</script>
<%@ include file="footer.jsp"%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
