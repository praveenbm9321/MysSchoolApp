<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE HEADER-->
<%@include file="headerForParent.jsp"%>



<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



<script>
	$(document).ready(function() {
		App.init();
		TableEditable.init();
		//$('#sample_editable_2').DataTable();
		slider(206);
		$('#parent').change(function() {
			request = new XMLHttpRequest();
			var v1 = document.vinform.studentName.value;

			var url = "ajax_par_notification.web?val1=" + v1;
			try {
				request.onreadystatechange = getInfo;
				request.open("GET", url, true);
				request.send();
			} catch (e) {
				alert("Unable to connect to server");
			}
			//$('#sample_editable_2').DataTable();
		});

		function getInfo() {
			if (request.readyState == 4) {
				var val = request.responseText;
				document.getElementById('studentDataForNotification').innerHTML = val;
				$('#sample_editable_2').DataTable({
					"bSort" : false
				});
				$('#sample_editable_1').DataTable({
					"bSort" : false
				});
			}
		}
		$('#parent').trigger('change');
		//$('#sample_editable_2').DataTable();
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
		<h3 class="page-title">
			Notification <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index_For_Parent.web"> Parent
			</a> <i class="fa fa-angle-right"></i></li>
			<li>
				<!-- <a href="#"> --> Notification <!-- </a> -->
			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<%-- <c:if test="${!empty noRecordErr }">
	<div class="alert alert-danger">
		<strong>Alert! No New Notification Found</strong>
	</div>
</c:if> --%>
<div class="form-group">

	<form action="par_notification.web" method="POST" id="form1" name="vinform">
		<div class="form-group">
			<label class="col-md-2 control-label"><b>Select Student <span
										class="required" style="color: red"> * </span>
			</b></label>
			<div class="col-md-5">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i></span>
					<select  id="parent" style="width: 40%" class="form-control" 
			name="studentName">
						<!-- <option value="">Select</option> -->
						<c:forEach items="${regal}" var="m">
							<%-- <option value="${m.intRegistrationId}">${m.txtStudentFirstName}</option> --%>
							<option value="${m.txtFirstName}">${m.txtFirstName}  ${m.txtLastName}</option>
						</c:forEach>
					</select>
				</div>

				<!-- <span style="color: red; font-weight: bolder;"> <span
					class="field-validation-valid" id="validationMessages1"></span>
				</span> -->

			</div>
			<!-- <input type="submit" class="btn btn-primary">  --><br>
		</div>
	</form>

</div><br><br><br>
<div id = "studentDataForNotification"> </div>
<%-- <div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">School Management Notification</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty tblinvites}">
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
									<c:forEach items="${tblinvites}" var="m">
										<tr>
											<td>${m.dtDate}</td>
											<td>${m.txtInvitationTitle}</td>
											<td>${m.txtBody}</td>
											<td><c:choose>
													<c:when test="${empty m.txtfileName }">
													No attachment Found
												</c:when>
													<c:otherwise>
														<a href="downloadsNotification/${m.intInvitesId}.web">

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
							<h3 style="text-align: center; font-weight: bold;">No record
								found</h3>
						</c:otherwise>
					</c:choose>
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
					<c:choose>
						<c:when test="${not empty tblinvites}">
							<table id="sample_editable_2"
								class="table table-bordered table-hover">
								<thead>
									<tr>
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
						</c:when>
						<c:otherwise>
							<h3 style="text-align: center; font-weight: bold;">No record
								found</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

</div>
 --%>
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

<%-- <%@include file="footer.jsp"%> --%>