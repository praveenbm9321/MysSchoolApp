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
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Leave Inbox</h3>
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
				<h5>Leave Inbox</h5> <!-- <i class="fa fa-angle-right"></i> -->
			</li>

		</ul>
	</div>
</div>




<div class="row">
	<div class="col-md-12">

		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Leave Inbox</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table id="sample_editable_1"
						class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #E5E4E2">
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
							<c:forEach items="${inbox}" var="l">
								<tr>
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


				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
















	</div>




</div>



<!-- END CONTENT -->

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
<script src="assets/scripts/custom/table-managed.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->



<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable({
			"bSort" : false
		//aaData: response.data
		});
		slider(110);
	});
</script>

<script>
	
</script>
<%@ include file="footer.jsp"%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
