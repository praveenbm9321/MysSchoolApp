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
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Notification</h3>
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
				<h5>Notification</h5> <!-- <i class="fa fa-angle-right"></i> -->
			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->









<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Notification</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">

					<c:choose>
						<c:when test="${not empty notification}">
							<table id="sample_editable_1"
								class="table table-bordered table-hover">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th>Date</th>
										<th>Title</th>
										<th>Description</th>
										<th>Attachment</th>


									</tr>
								</thead>
								<tbody>
									<c:forEach items="${notification}" var="n">
										<tr>
											<td><fmt:formatDate value="${n.dtDate}"
													pattern="dd-MMM-yyyy" /></td>
											<td>${n.txtInvitationTitle}</td>
											<td>${n.txtBody}</td>
											<td><c:choose>
													<c:when test="${empty n.txtfileName }">
												No attachment found
												</c:when>
													<c:otherwise>

														<!-- ${n.txtfileName}<button type="submit"   class="btn green">Download</button> -->
														<a
															href="notificationFileDownload.web?id=${n.intInvitesId}">
															<button type="button" class="btn green">
																<i class="fa fa-upload"></i> Download
															</button>
														</a>
													</c:otherwise>
												</c:choose></td>


										</tr>
									</c:forEach>


								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<h3 style="text-align: center; font-weight: bolder;">
								<i class="fa fa-warning" style="font-size: 25px;"></i>
								Notification are not available
							</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

</div>
<!-- END VALIDATION STATES-->


<!-- 	
	END CONTENT
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
END CORE PLUGINS
BEGIN PAGE LEVEL PLUGINS
<script type="text/javascript"
	src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/DT_bootstrap.js"></script>
END PAGE LEVEL PLUGINS
BEGIN PAGE LEVEL SCRIPTS
<script src="assets/scripts/core/app.js"></script>
<script src="assets/scripts/custom/table-editable.js"></script>
validation start 

<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript"
	src="assets/plugins/jquery-validation/dist/additional-methods.js"></script> -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>

<script type="text/javascript">
	jQuery(document).ready(function() {
		//App.init();
		TableEditable.init();
		$('#sample_editable_1').DataTable({
			"bDestroy" : true,
			"bSort" : false,
		// aaData: response.data
		});
		slider(104);
	});
</script>


<%@include file="footer.jsp"%>