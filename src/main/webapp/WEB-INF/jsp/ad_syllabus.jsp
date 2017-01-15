
<!-- BEGIN PAGE HEADER-->

<!-- <td><input type="text" class="code" id="customFieldName" name="customFieldName[]" value="" placeholder="Input Name" /> -->


<%@include file="header.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('#syllabusForm')
								.validate(
										{ // initialize the plugin
											rules : {
												Mststudclass : {
													required : true
												},
												subs : {
													required : true
												},
												blUploadFile : {
													required : true,
													accept : "application / pdf,image / * ,application / doc,application / docx"
												}
											},

											messages : {
												Mststudclass : {
													required : "Select the Class"
												},
												subs : {
													required : "Select Subject"
												},
												blUploadFile : {
													required : "Select the File",
													accept : "please select file with extension .pdf, .jpg, .gif, .png, .doc, .docx"
												}
											},

											highlight : function(element) {
												$(element).parent().addClass(
														'error')
											}

										});

					});
</script>

<style>
#syllabusForm .error {
	color: red;
	font-size: 12px;
}
</style>
<script>
	jQuery(document).ready(function() {
		var id = 0;
		jQuery("#addrow").click(function() {
			id++;
			var row = jQuery('.samplerow tr').clone(true);
			row.find("input:text").val("");
			row.attr('id', id);
			row.appendTo('#dynamicTable1');
			return false;
		});

		$('.remove').on("click", function() {
			$(this).parents("tr").remove();
		});
	});
</script>


<div class="row">
	<div class="col-md-12">
		<h3 class="page-title">Syllabus</h3>
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<!--<h3 class="page-title">
					Teacher
					</h3>-->
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Syllabus</h5></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<c:if test="${not empty sucmsg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<i class="fa fa-check-circle"></i>${sucmsg}<strong>${sucmsg1}</strong>added
		Successfully!!!
	</div>
</c:if>
<c:if test="${not empty delMsg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<i class="fa fa-check-circle"></i>${delMsg}
	</div>
</c:if>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>Syllabus
				</div>

			</div>
			<div class="portlet-body form">

				<!-- BEGIN FORM-->
				<form action="ad_syllabus.web" method="POST" id="syllabusForm"
					class="form-horizontal" enctype="multipart/form-data">
					<div class="form-body">

						<div class="form-group">
							<label class="col-md-3 control-label" style="font-weight: bold">Class
								<span class="required"> * </span>
							</label>
							<div class="col-md-9" style="width: 360px;">
								<select name="Mststudclass" class="form-control"
									style="width: 250px;">
									<option value="">Select Class</option>
									<c:forEach var="m" items="${mststudclasses}" begin="0" end="11">
										<option value="${m.intClassId}">${m.txtClassName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" style="font-weight: bold">Subject
								<span class="required"> * </span>
							</label>
							<div class="col-md-9" style="width: 360px;">
								<table id="dynamicTable1">
									<!-- <thead>
								<th>Subject</th>
								<th>UploadFile</th>
							</thead> -->
									<tr id="0">
										<td><input type="text" name="subs" class="subjectClass"
											placeholder="Enter Subject"
											style="width: 250px; height: 35px;"></td>
										<td><input type="file" style="width: 250px;"
											class="form-control" placeholder="upload" name="blUploadFile"></td>
										<td><button class="remove">Remove</button></td>
									</tr>
								</table>
								<p>
									Only (<strong>.JPG, .GIF, .PNG, .pdf, .DOC, .DOCX</strong>) files are allowed<br> (The maximum file size
									for uploads is <strong>5 MB</strong>)
								</p>
								<br> <input type="button" id="addrow" value="Add New Row" />

								<table class="samplerow" style="display: none">
									<tr>
										<td><input type="text" name="subs"
											placeholder="Enter Subject" style="width: 250px;"></td>

										<td><input type="file" style="width: 250px;"
											class="form-control" placeholder="upload" name="blUploadFile"></td>
										<td><button class="remove">Remove</button></td>
									</tr>
								</table>
							</div>
						</div>

						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">Update</button>
								<!--<button type="button" class="btn default">Cancel</button>-->
							</div>
						</div>
					</div>
				</form>

				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->

		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Syllabus Inbox</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<%
						int i = 1;
					%>
					<table class="table table-bordered table-hover" id="sample_editable_1">
						<c:choose>
							<c:when test="${not empty syllabusList }">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th width="20%">Sl.No</th>
										<th width="20%">Class</th>
										<th width="20%">Subject</th>
										<th width="20%">Uploaded File</th>
										<th width="20%">Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cc" items="${syllabusList}">
										<tr>

											<td><%=i%></td>

											<c:forEach var="c" items="${classList }">
												<c:if test="${ c.intClassId == cc.mststudclass.intClassId}">
													<td>${c.txtClassName }</td>
												</c:if>
											</c:forEach>

											<td>${cc.txtSubjectName }</td>

											<td><a href="downloadSyllabus/${cc.intSubjectId}.web"
												class="btn green"> <i class="fa fa-download"> </i>
													Download
											</a></td>

											<td><a href="deleteSyllabus/${cc.intSubjectId }.web"
												id="alert_show">
													<button class="btn red">
														<i class="fa fa-trash-o"></i> Delete
													</button>
											</a></td>
										</tr>
										<%
											i++;
										%>
									</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="8">
										<div align="center">
											<h2>
												<i class="fa fa-warning" style="font-size: 25px;"></i> No
												Records Found
											</h2>
										</div>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>


					<!-- END FORM-->

				</div>
			</div>
		</div>
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
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		slider(14);
	});
</script>
<%@include file="footer.jsp"%>