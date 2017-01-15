
<%-- 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Marks</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="index.html"> Student
			</a> <i class="fa fa-angle-right"></i></li>

			<li><a href="#"> Marks </a></li>
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
				<div class="caption">MarkSheet</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
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
									<td><button type="submit" class="btn green">Download</button></td>



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
</div>
</div>
<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp"%>

<!--  --> --%>

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
		$('#parent').change(function() {
			request = new XMLHttpRequest();
			var v1 = document.vinform.studentName.value;

			var url = "ajax_par_marks.web?val1=" + v1;
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
				document.getElementById('studentDataForMarks').innerHTML = val;
				$('#sample_editable_1').DataTable({
					"bSort" : false
				});
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
		<h3 class="page-title">Marks</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="ad_index.web">
					Student </a> <i class="fa fa-angle-right"></i></li>

			<li>
				<!-- <a href="#"> --> Marks <!-- </a> -->
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->


<%-- <div class="form-group">

	<label class="col-md-2 control-label"><b>Select Student * </b></label>
	<form action="par_marks.web" id="demo" method="POST" id="form1">
		<div class="col-md-5">
			<div class="input-group">
				<span class="input-group-addon"> <i class="fa fa-user"></i></span> <select
					 style="width: 40%" class="form-control"
					name="studentName">
					<option value="">Select</option>
					<c:forEach items="${regal}" var="s">
						<option><c:out value="${s.txtFirstName}">${s.txtFirstName}</c:out></option>
					</c:forEach>
				</select>
			</div>
			<span style="color: red; font-weight: bolder;"> <span
				class="field-validation-valid" id="validationMessages1"></span>
			</span>
		</div>
		<input type="submit" class="btn btn-primary"> <br>
	</form>
	<br> <br>
</div> --%>















<div class="form-group">

	<form action="par_marks.web" method="POST" id="form1" name="vinform">
		<div class="form-group">
			<label class="col-md-2 control-label"><b>Select Student <span
					class="required" style="color: red"> * </span>
			</b></label>
			<div class="col-md-5">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i></span>
					<select id="parent" style="width: 40%" class="form-control"
						name="studentName">
						<!-- <option value="">Select</option> -->
						<c:forEach items="${regal}" var="m">
							<option value="${m.txtFirstName}">${m.txtFirstName}
								${m.txtLastName}</option>
						</c:forEach>
					</select>
				</div>

				<!-- <span style="color: red; font-weight: bolder;"> <span
					class="field-validation-valid" id="validationMessages1"></span>
				</span> -->

			</div>
			<!-- <input type="submit" class="btn btn-primary"> -->
			<br>
		</div>
	</form>

</div>


<br>
<br>
<br>



<div id="studentDataForMarks"></div>
<%-- 
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

											<td><a href="download/ ${m.intUploadStudMarksheetId}.web">

											<button type="button" class="btn green">
												<i class="fa fa-download"> </i> Download
											</button>

									</a></td>

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
							<h3 style="text-align: center; font-weight: bold;">No record
								found</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

</div> --%>
<!-- END PAGE CONTENT-->
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->




<!-- <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
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
	src="assets/plugins/jquery-validation/dist/additional-methods.js"></script>

validation end  -->







<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		
		//TableEditable.init();
		slider(204);
	});
</script> 
<%@include file="footer.jsp"%>