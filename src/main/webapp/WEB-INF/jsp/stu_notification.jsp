
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- BEGIN PAGE HEADER-->
<%@include file="headerForStudent.jsp"%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Notification</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Student.web"> Student </a> <i
				class="fa fa-angle-right"></i></li>

			<li>Notification</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">School Management Notification</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table id="sample_editable_1"
						class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #E5E4E2">
								<th>Date</th>
								<th>Title</th>
								<th>Description</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${mst}" var="m">
								<tr>
									<td>${m.dtDate}</td>
									<td>${m.txtInvitationTitle}</td>
									<td>${m.txtBody}</td>
									<td><%-- <a href="downloadss/ ${m.intInvitesId}.web">

											<button type="button" class="btn green">
												<i class="fa fa-download"> </i> Download
											</button>

									</a> --%>
									
									<c:choose>
												<c:when test="${empty m.txtfileName}">
												No attachment found
												</c:when>
												<c:otherwise>
												<!-- <button type="submit"   class="btn green">Download</button> -->
												<a href="downloadss/ ${m.intInvitesId}.web">
																		<button type="button" class="btn green">
																			<i class="fa fa-upload"></i> Download
																		</button>
																	</a>
												</c:otherwise>
												</c:choose>
									</td>
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

<div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Teacher Notification</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table id="sample_editable_2"
						class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #E5E4E2">
								<th>Date</th>

								<th>Description</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${mste}" var="m">
								<tr>
									<td>${m.dtInvitesDate}</td>

									<td>${m.txtMessage}</td>
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
<!-- END PAGE CONTENT-->
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->










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

<!-- validation end  -->


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		$('#sample_editable_2').DataTable({
			"bSort" : false
		});
		
		slider(304);
	});
</script>
<%-- <%@include file="footer.jsp"%> --%>