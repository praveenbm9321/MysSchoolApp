<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#firstForm').validate({ // initialize the plugin
			rules : {
				teacherId : {
					required : true
				}
			},
			messages : {
				teacherId : {
					required : "Please Select Teacher!!!"
				}
			},
			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
		$('#secondForm').validate({ // initialize the plugin
			rules : {
				ClassId : {
					required : true
				},
				SectionId : {
					required : true
				}
			},
			messages : {
				ClassId : {
					required : "Please Select Class!!!"
				},
				SectionId : {
					required : "Please Select Section!!!"
				}
			},
			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
	});
</script>
<style>
#firstForm .error {
	color: red;
	font-size: 12px;
}

#secondForm .error {
	color: red;
	font-size: 12px;
}
</style>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Assign Class Teacher</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li class="active"><i class="fa fa-home"></i> <a
				href="ad_index.web"> Home </a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Assign Class Teacher</h5></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.

</div>
<c:if test="${not empty sucMsg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<i class="fa fa-check-circle"></i><strong>${sucMsg }</strong>
	</div>
</c:if>
<c:if test="${not empty errorMsg}">
	<div class="alert alert-block alert-danger fade in">
		<button type="button" class="close" data-dismiss="alert"></button>
		<i class="fa fa-check-circle"></i> ${errorMsg }
	</div>
</c:if>
<c:if test="${not empty sucMsg2}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<i class="fa fa-check-circle"></i><strong>${sucMsg2 }</strong>
	</div>
</c:if>

<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">Assign Class Teacher</div>
	</div>
	<div class="portlet-body">
		<div class="portlet-body form">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet-body">

						<form action="ad_assignClassTeacher.web" class="form-horizontal"
							method="POST" id="firstForm">

							<div class="form-group">
								<label class="col-md-3 control-label" style="font-weight: bold">Teacher
									<span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="teacherId" class="form-control">
										<option value="">Select Teacher [ID]</option>
										<c:forEach items="${teachList}" var="s">
											<option value="${s.intRegistrationId}">${s.txtFirstName}
												${s.txtLastName} [${s.intRegistrationId}]</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<input type="hidden" name="hiddenValue" value="11">
									<button type="Submit" class="btn green">Search</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<br> <br>

			<c:if test="${empty checkPost }">
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">Class Teacher Inbox</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<div class="row">
								<div class="col-md-12">
									<div class="portlet-body">
										<div class="portlet-body">
											<div class="table-responsive">
												<table
													class="table table-striped table-bordered table-hover"
													id="sample_editable_1">
													<c:choose>
														<c:when test="${not empty classTeachListOfList }">
															<thead>
																<tr style="background-color: #E5E4E2">
																	<td align="center"><b>Teacher Name [ID]</b></td>
																	<td align="center"><b>Class & Section</b></td>
																	<td align="center"><b>Number of Classes</b></td>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="teachObjs"
																	items="${classTeachListOfList}">
																	<tr class="odd gradeX">
																		<c:forEach items="${teachObjs }" var="teachObj">
																			<c:if test="${teachObjs[0] == teachObj }">
																				<td align="center">${teachObj.mstregistration.txtFirstName }&nbsp;${teachObj.mstregistration.txtLastName }&nbsp;[${teachObj.mstregistration.intRegistrationId }]</td>
																			</c:if>
																		</c:forEach>
																		<td align="center"><c:forEach
																				items="${teachObjs }" var="teachObj">
												Class&nbsp;${teachObj.mststudclass.txtClassName }&nbsp;&nbsp;Section&nbsp;${teachObj.mststudclasssection.txtSectionName }<br>
																			</c:forEach></td>
																		<td align="center">${fn:length(teachObjs) }</td>
																	</tr>
																</c:forEach>
															</tbody>
														</c:when>
														<c:otherwise>
															<tbody>
																<tr>
																	<td align="center" colspan="8">
																		<div align="center">
																			<h2>No Teacher Records Found!!!</h2>
																		</div>
																	</td>
																</tr>
															</tbody>
														</c:otherwise>
													</c:choose>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${not empty checkPost }">
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">${teacherName }'s&nbsp;Inbox</div>
					</div>
					<div class="portlet-body">
						<div class="portlet-body form">
							<div class="row">
								<div class="col-md-12">
									<div class="portlet-body">
										<form action="ad_assignClassTeacher.web"
											class="form-horizontal" id="secondForm" method="POST">
											<table class="table table-striped table-bordered table-hover"
												border="0">
												<tr>
													<td align="center"><b>Class</b><span class="required"
														style="color: red"> * </span>&nbsp;&nbsp;<select
														class="table-group-action-input form-control input-inline input-small input-sm"
														name="ClassId" style="width: 360px;" onchange="sendInfo()">
															<option value="">Select Class</option>
															<c:forEach var="c" items="${classList }" begin="0"
																end="11">
																<option value="${c.intClassId }">${c.txtClassName }</option>
															</c:forEach>
													</select></td>
												</tr>
												<tr>
													<td align="center"><b>Section</b><span
														class="required" style="color: red"> * </span>&nbsp;&nbsp;<select
														class="table-group-action-input form-control input-inline input-small input-sm"
														name="SectionId" style="width: 360px;"
														onchange="sendInfo()">
															<option value="">Select Section</option>
															<c:forEach var="s" items="${sectionsList }" begin="0"
																end="3">
																<option value="${s.intSectionId }">${s.txtSectionName }</option>
															</c:forEach>
													</select></td>
												</tr>
											</table>
											<table class="table table-striped table-bordered table-hover">
												<c:choose>
													<c:when test="${not empty specificClassTeachList }">
														<thead>
															<tr style="background-color: #E5E4E2">
																<td align="center"><b>Teacher Name [ID]</b></td>
																<td align="center"><b>Class</b></td>
																<td align="center"><b>Section</b></td>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="teachObj"
																items="${specificClassTeachList}">
																<tr class="odd gradeX">
																	<td align="center">${teachObj.mstregistration.txtFirstName }&nbsp;${teachObj.mstregistration.txtLastName }&nbsp;[${teachObj.mstregistration.intRegistrationId }]</td>
																	<td align="center">
																		Class&nbsp;${teachObj.mststudclass.txtClassName }</td>
																	<td align="center">
																		Section&nbsp;${teachObj.mststudclasssection.txtSectionName }
																	</td>
																</tr>
																<input type="hidden" name="teachIdId"
																	value="${teachObj.mstregistration.intRegistrationId }">
															</c:forEach>
															<tr>
																<td align="center" colspan="3">
																	<div class="form-actions fluid">
																		<div class="col-md-offset-3 col-md-9">
																			<input type="hidden" name="hiddenValue" value="22">
																			<button type="Submit" class="btn green">Assign</button>
																		</div>
																	</div>
																</td>
															</tr>
														</tbody>
													</c:when>
													<c:otherwise>
														<tbody>
															<tr>
																<td align="center" colspan="8">
																	<div align="center">
																		<h2>He/She is Not a Class Teacher!!!</h2>
																	</div>
																</td>
															</tr>
															<tr>
																<td align="left" colspan="3">
																	<div class="form-actions fluid">
																		<div class="col-md-offset-3 col-md-9">
																			<input type="hidden" name="hiddenValue" value="22">
																			<input type="hidden" name="teachIdId"
																				value="${teachId }">
																			<button type="Submit" class="btn green">Assign</button>
																		</div>
																	</div>
																</td>
															</tr>
														</tbody>
													</c:otherwise>
												</c:choose>
											</table>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>
<!-- END BORDERED TABLE PORTLET-->
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
		slider(17);
	});
</script>
<%@include file="footer.jsp"%>