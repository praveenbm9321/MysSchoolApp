
<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#roles").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "1") {
					// $("#first").not(this).hide();
					$("#class").show();
					$("#section").show();

					$("#class").change(function() {
						$(this).find("option:selected").each(function() {
							if ($(this).attr("value") == "") {
								// $("#first").not(this).hide();
								$("#section").hide();
							} else {
								$("#section").show();
							}
						});
					}).change();

				}

				else {
					$("#class").hide();
					$("#section").hide();
				}
			});
		}).change();
	});
</script>

<script type="text/javascript">
<!--
	//-->
	$(document)
			.ready(
					function() {

						$('#form1')
								.validate(
										{ // initialize the plugin
											rules : {
												tblrolename : {
													required : true
												},
												mststudclass : {
													required : true
												},
												mststudclasssection : {
													required : true
												},
												blUploadFile : {
													accept : "application / *, image/ *"
												}
											},

											messages : {
												tblrolename : {
													required : " Please Select Whom to Send"
												},
												mststudclass : {
													required : "Please select Class"
												},
												mststudclasssection : {
													required : "Please select Section"
												},
												blUploadFile : {
													accept : "please select only file with extension .pdf, .jpg, .gif, .png, .doc, .docx, .xlsx, .xls"
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
#form1 .error {
	color: red;
	font-size: 12px;
}
</style>



<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title"></h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group">
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Invites</h5></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->

<!-- BEGIN PAGE HEADER-->
<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>

<c:choose>
	<c:when test="${not empty sucmsg }">
		<div class="alert alert-success">
			<b><i class="fa fa-check-circle"></i>${sucmsg}</b>
		</div>
	</c:when>
</c:choose>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->


		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->

		<div class="tab-content">
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-reorder"></i>Invites
						</div>

					</div>

					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="ad_invites.web" class="form-horizontal"
							method="post" enctype="multipart/form-data" id="form1">
							<!-- enctype="multipart/form-data" -->
							<div class="form-body">

								<div class="form-group">
									<label class="col-md-3 control-label" style="font-weight: bold">Invite
										To <span class="required"> * </span>
									</label>
									<div class="col-md-9" style="width: 360px;">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-user"></i>
											</span> <select name="tblrolename" class="form-control" id="roles" style="width: 360px;">
												<option value="">Select Person</option>
												<c:forEach var="roles" items="${roles}" begin="0" end="2">
													<option value="${roles.intRoleId}">${roles.txtRoleName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label"><b>Invitation
											Title</b></label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-book"></i>
											</span> <input type="text" class="form-control" style="width: 360px;"
												placeholder="enter title " name="txtInvitationTitle">

										</div>
									</div>
								</div>

								<div class="form-group" id="class">
									<label class="col-md-3 control-label" style="font-weight: bold">Class
										<span class="required"> * </span>
									</label>
									<div class="col-md-9" style="width: 360px;">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span> <select name="mststudclass" class="form-control" style="width: 360px;">
												<option value="">Select class</option>
												<c:forEach var="classes" items="${classes}">
													<option value="${classes.intClassId}">${classes.txtClassName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="form-group" id="section">
									<label class="col-md-3 control-label" style="font-weight: bold">Section
										<span class="required"> * </span>
									</label>
									<div class="col-md-9" style="width: 360px;">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span> <select name="mststudclasssection" class="form-control" style="width: 360px;">
												<option value="">Select Section</option>
											<c:forEach var="sections" items="${sections}">
												<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
											</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label"><b>Body</b></label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"> <i class="fa fa-bars"></i>
											</span>

											<textarea class="form-control" rows="3" name="txtBody" placeholder="Enter Body" style="width: 360px;resize: none"></textarea>

											<!-- <input type="textarea" class="form-control" placeholder="Massage"> -->
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label"><b>Upload
											File</b></label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-upload"></i>
											</span> <input type="file" class="form-control" placeholder="upload" style="width: 360px;"
												name="blUploadFile">
											<!-- -->

										</div>
										<p>
											Only (<strong>.pdf, .JPG, .GIF, .PNG, .DOC, .DOCX,
												.XLSX, .XLS</strong>) files are allowed<br> (The maximum file
											size for uploads is <strong>5 MB</strong>)
										</p>
									</div>
								</div>

								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green">Submit</button>
										<!--<button type="button" class="btn default">Cancel</button>-->
									</div>
								</div>
							</div>
						</form>
						<!-- END FORM-->
					</div>

				</div>



				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">Invite's Inbox</div>
					</div>
					<div class="portlet-body">
						<div class="table-responsive">
							<%
								int i = 1;
							%>
							<table class="table table-bordered table-hover" id="sample_editable_1">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th>Sl.No</th>
										<th>Invitation title</th>
										<th>Invites to</th>
										<th>Class</th>
										<th>Section</th>
										<th>Description</th>
										<th>Uploaded File</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${not empty invDetails }">
											<c:forEach var="cc" items="${invDetails}">
												<tr>

													<td><%=i%></td>
													<c:choose>
														<c:when test="${not empty cc.txtInvitationTitle }">
															<td>${cc.txtInvitationTitle}</td>
														</c:when>
														<c:otherwise>
															<td>No Title</td>
														</c:otherwise>
													</c:choose>

													<c:forEach var="c" items="${roles }">
														<c:if test="${ c.intRoleId == cc.tblrolename.intRoleId}">
															<td>${c.txtRoleName }</td>
														</c:if>
													</c:forEach>

													<c:set var="id" scope="session" value="${roleId}" />

													<c:choose>
														<c:when test="${cc.tblrolename.intRoleId == id}">
															<c:choose>
																<c:when test="${not empty cc.mststudclass.intClassId}">
																	<c:forEach var="c" items="${classes }">
																		<c:if
																			test="${ c.intClassId == cc.mststudclass.intClassId}">
																			<td>${c.txtClassName }</td>
																		</c:if>
																	</c:forEach>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															<td>N/A</td>
														</c:otherwise>
													</c:choose>

													<c:choose>
														<c:when test="${cc.tblrolename.intRoleId == id}">
															<c:choose>
																<c:when
																	test="${not empty cc.mststudclasssection.intSectionId}">
																	<c:forEach var="c" items="${sections }">
																		<c:if
																			test="${ c.intSectionId == cc.mststudclasssection.intSectionId}">
																			<td>${c.txtSectionName }</td>
																		</c:if>
																	</c:forEach>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															<td>N/A</td>
														</c:otherwise>
													</c:choose>

													<c:choose>
														<c:when test="${not empty cc.txtBody }">
															<td>${cc.txtBody}</td>
														</c:when>
														<c:otherwise>
															<td>No Description</td>
														</c:otherwise>
													</c:choose>

													<!-- <td><button type="button" class="btn green" onclick="download/{cc.intInvitesId}.web">Download</button> </td> -->
													<td align="center"><c:choose>
													<c:when test="${empty cc.txtfileName }">
													N/A
												</c:when>
													<c:otherwise><a href="downloadInvite/${cc.intInvitesId}.web"
														class="btn green"> <i class="fa fa-download"> </i>
															Download
													</a></c:otherwise></c:choose></td>

													<td><a href="deleteInvites/${ cc.intInvitesId }.web"
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
														<h2><i class="fa fa-warning" style="font-size: 25px;"></i> No Records Found</h2>
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


				<!-- END PAGE CONTENT-->

			</div>
		</div>
	</div>
	<!-- END CONTENT -->
</div>
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
/* TableEditable.init(); */
$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		slider(11);
	});
</script>
<%@include file="footer.jsp"%>