<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">MarkSheet</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty mst}">
							<table id="sample_editable_1"
									class="table table-bordered table-hover">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th>Date</th>
										<th>Class</th>
										<th>Section</th>
										<th>Exam</th>
										<th>Action</th>

</tr>

									
								</thead>
								<tbody>
									<c:forEach items="${mst}" var="m">
										<tr>
											<td>${m.dtDate}</td>

											<td>${m.mststudclass.intClassId}</td>

											<td>${m.mststudclasssection.txtSectionName}</td>

											<td>${m.mstterms.txtTermName}</td>

											<%-- <td><a href="download/ ${m.intUploadStudMarksheetId}.web">

											<button type="button" class="btn green">
												<i class="fa fa-download"> </i> Download
											</button>

									</a></td> --%>

											<td><c:choose>
													<c:when test="${empty m.txtfileName }">
													No attachment Found
												</c:when>
													<c:otherwise>
														<a href="download/${m.intUploadStudMarksheetId}.web">

															<button type="button" class="btn green">
																<i class="fa fa-download"> </i> Download
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
							<h3 style="text-align: center; font-weight: bold;"><i class="fa fa-warning" style="font-size: 25px;"></i> No record
								found</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

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

<!-- validation end --> 



<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable();
		slider(202);
	});
</script>
<%-- <%@include file="par_attendance_1.jsp"%> --%>
<%-- <%@include file="footer.jsp"%> --%>