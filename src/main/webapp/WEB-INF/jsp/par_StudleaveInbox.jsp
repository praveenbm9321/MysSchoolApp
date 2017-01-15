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
<%@include file="headerForParent.jsp"%>


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



<script>
	$(document)
			.ready(
					function() {
						$('#parent').change(function() {
							request = new XMLHttpRequest();
							var v1 = document.vinform.studentName.value;

							var url = "ajax_par_StudleaveInbox.web?val1=" + v1;
							try {
								request.onreadystatechange = getInfo;
								request.open("GET", url, true);
								request.send();
							} catch (e) {
								alert("Unable to connect to server");
							}
						});

						function getInfo() {
							if (request.readyState == 4) {
								var val = request.responseText;
								document.getElementById('studentDataForLeaveInbox').innerHTML = val;
								$('#sample_editable_1').DataTable();
							}
						}
						$('#parent').trigger('change');
					});
</script>


<script type="text/javascript">
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin
			rules : {
				studentName : {
					required : true
				}
			},
			messages : {
				studentName : {
					required : "Please select student"

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
		<h3 class="page-title">Parent</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="ad_index_For_Parent.web">
					Parent </a> <i class="fa fa-angle-right"></i></li>
			<li>
				<!-- <a href="#">  -->Leave Inbox<!--  </a> --> <!-- <i class="fa fa-angle-right"></i> -->
			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
	<%-- >"${s.intRegistrationId}" --%>
</div>
<div class="form-group">

	<label class="col-md-2 control-label"><b>Select Student <span
			class="required" style="color: red"> * </span>
	</b></label>
	<form action="par_StudleaveInboxData.web" method="POST" id="form1"
		name="vinform">
		<div class="col-md-5">
			<div class="input-group">
				<span class="input-group-addon"> <i class="fa fa-user"></i></span> <select
					id="parent" style="width: 40%" class="form-control"
					name="studentName">
					<!-- <option value="">Select</option> -->
					<c:forEach items="${mst}" var="s">
						<%-- <option><c:out value="${s.txtFirstName}">${s.intRegistrationId}</c:out></option> --%>
						<option value="${s.intRegistrationId}">${s.txtFirstName}
							${s.txtLastName}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<!-- <input type="submit" class="btn btn-primary"> -->
		<br> <br> <br>
	</form>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div id="studentDataForLeaveInbox"></div>
<%-- 
<div class="row">
	<div class="col-md-12">

		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Leave Inbox</div>
			</div>

			<div class="portlet-body">
				<div class="table-responsive">
					<c:if test="${! empty inbox }">
						<c:choose>
							<c:when test="${! empty studentLeaveInbox}">
								<table id="sample_editable_1" class="table table-bordered table-hover">
									<thead>
										<tr style="background-color: #E5E4E2">
											<th>Student Name</th>
											<th>Applied Date</th>
											<th>Leave Type</th>
											<th>From Date</th>

											<th>To Date</th>
											<th>Reason</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${studentLeaveInbox}" var="l">
											<tr>
												<td>${l.mstregistration.txtFirstName}&nbsp;${l.mstregistration.txtLastName}</td>
												<td><fmt:formatDate value="${l.dtAppliedDate}"
														pattern="dd-MMM-yyyy" /></td>
												<td>${l.mstleave.txtLeaveType }</td>
												<td><fmt:formatDate value="${l.dtFromDate}"
														pattern="dd-MMM-yyyy" /></td>
												<td><fmt:formatDate value="${l.dtToDate}"
														pattern="dd-MMM-yyyy" /></td>
												<td>${l.txtReason}</td>
												<td><c:if test="${l.intStatus==1}">
														<p class="label label-sm label-warning"
															style="font-weight: bolder;">Pending</p>
													</c:if> <c:if test="${l.intStatus==2}">
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
								<h3 style="text-align: center;font-weight: bold;">No record found!!!</h3>
							</c:otherwise>
						</c:choose>
					</c:if>

				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>
</div> --%>
</div>
</div>
<!-- END CONTENT -->
</div>






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
<!-- END CORE PLUGINS
BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript"
	src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/DT_bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS
BEGIN PAGE LEVEL SCRIPTS -->
<script src="assets/scripts/core/app.js"></script>
<script src="assets/scripts/custom/table-editable.js"></script>
<!-- validation start  -->

<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/additional-methods.js"></script>

<!-- validation end  -->










<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		App.init();
		TableEditable.init();

		slider(211);
	});
</script>
<%-- <%@ include file="footer.jsp"%> --%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
