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
		$("#roles").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "1") {
					// $("#first").not(this).hide();
					$("#class").show();
					$("#section").show();
					$("#teachId").hide();

				} else if ($(this).attr("value") == "") {
					$("#teachId").hide();
					$("#class").hide();
					$("#section").hide();
				} else {
					$("#teachId").show();
					$("#class").hide();
					$("#section").hide();
				}
			});
		}).change();

		if ("${secondTab}" == "11") {
			$('#tab22').trigger('click');
		} else {
			$('#tab21').trigger('click');
		}

	});

	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin
			rules : {
				mstcategory : {
					required : true
				},
				mststudclass : {
					required : true
				},
				mststudclasssection : {
					required : true
				},
				teacherId : {
					required : true
				},
				txtFile : {
					required : true,
					extension : "xlsx"
				}
			},

			messages : {
				mstcategory : {
					required : "Please Select Person"
				},
				mststudclass : {
					required : "Please Select Class"
				},
				mststudclasssection : {
					required : "Please Select Section"
				},
				teacherId : {
					required : "Please Select Teacher"
				},
				txtFile : {
					required : "Please Select .xlsx file",
					extension : "Please Select only file with extension .xlsx"
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
	font-size: 12px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$("#roles2").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "1") {
					// $("#first").not(this).hide();
					$("#class2").show();
					$("#section2").show();
					$("#teachId2").hide();

				} else if ($(this).attr("value") == "") {
					$("#teachId2").hide();
					$("#class2").hide();
					$("#section2").hide();
				} else {
					$("#teachId2").show();
					$("#class2").hide();
					$("#section2").hide();
				}
			});
		}).change();
	});

	$(document).ready(function() {

		$('#form2').validate({ // initialize the plugin
			rules : {
				mstcategory2 : {
					required : true
				},
				mststudclass2 : {
					required : true
				},
				mststudclasssection2 : {
					required : true
				},
				teacherIdForInbox2 : {
					required : true
				}
			},

			messages : {
				mstcategory2 : {
					required : "Please Select Person"
				},
				mststudclass2 : {
					required : "Please Select Class"
				},
				mststudclasssection2 : {
					required : "Please Select Section"
				},
				teacherIdForInbox2 : {
					required : "Please Select Teacher"
				}

			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}

		});

	});
</script>
<style>
#form2 .error {
	color: red;
	font-size: 12px;
}
</style>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Admin</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li class="active"><i class="fa fa-home"></i> <a
				href="ad_index.web"> Home </a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Time Table</h5></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.

</div>
<c:if test="${not empty success}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<i class="fa fa-check-circle"></i> <strong>${success}</strong>${success2 }
	</div>
</c:if>

<c:if test="${not empty failure}">
	<div class="alert alert-block alert-danger fade in">
		<button type="button" class="close" data-dismiss="alert"></button>
		<h4 class="alert-heading">Error!</h4>
		<p>${failure }</p>
	</div>
</c:if>



<table>
	<tr>
		<th width="48%">
			<div class="alert alert-info">
				<a href="assets/files/StudentTimetableTemplate.xlsx">Download Student
					TimeTable Template</a>
			</div>
		</th>
		<th width="4%"></th>
		<th width="48%">
			<div class="alert alert-info">
				<a href="assets/files/TeacherTimetableTemplate.xlsx">Download Teacher
					TimeTable Template</a>
			</div>
		</th>
	</tr>
</table>

<c:if test="${not empty updateMsg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"></button>
		<i class="fa fa-check-circle"></i> <strong>${updateMsg }</strong>'s
		TimeTable Updated Successfully!!!
	</div>
</c:if>

<c:if test="${not empty success3}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"></button>
		<i class="fa fa-check-circle"></i> TimeTable Updated for <strong>${success3 }</strong>
		Successfully!!!
	</div>
</c:if>

<!-- BEGIN BORDERED TABLE PORTLET-->
<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">Timetable</div>
	</div>
	<div class="portlet-body">
		<ul class="nav nav-pills">
			<li class="active"><a href="#tab_2_1" id="tab21"
				data-toggle="tab"> TimeTable </a></li>
			<li><a href="#tab_2_2" id="tab22" data-toggle="tab">
					Timetable Inbox </a></li>

		</ul>
		<div class="tab-content">
			<div class="tab-pane fade active in" id="tab_2_1">

				<div class="portlet-body form">
					<!-- BEGIN FORM-->
					<form action="ad_timetable.web" id="form1" method="post"
						class="form-horizontal" enctype="multipart/form-data">
						<div class="form-body">

							<div class="form-group">
								<label class="col-md-3 control-label" style="font-weight: bold">Upload
									For <span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="mstcategory" class="form-control" id="roles"
										style="width: 360px;">
										<option value="">Select Person</option>
										<c:forEach var="cat" items="${cat}" begin="0" end="1">
											<option value="${cat.intCategoryId1}">${cat.txtCategoryName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group" id="class">
								<label class="col-md-3 control-label" style="font-weight: bold">Class
									<span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="mststudclass" class="form-control"
										style="width: 360px;">
										<option value="">Select class</option>
										<c:forEach var="classes" items="${classes}" begin="0" end="11">
											<option value="${classes.intClassId}">${classes.txtClassName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group" id="section">
								<label class="col-md-3 control-label" style="font-weight: bold">Section
									<span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="mststudclasssection" class="form-control"
										style="width: 360px;">
										<option value="">Select Section</option>
										<c:forEach var="sections" items="${sections}" begin="0"
											end="3">
											<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group" id="teachId">
								<label class="col-md-3 control-label" style="font-weight: bold">Teacher
									Id <span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="teacherId" class="form-control"
										style="width: 360px;">
										<option value="">Select Teacher [ID]</option>
										<c:forEach items="${teachList}" var="s">
											<option value="${s.intRegistrationId}">${s.txtFirstName}
												${s.txtLastName} [${s.intRegistrationId}]</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3" style="font-weight: bold"><b>Upload
										File</b><span class="required"> * </span></label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-upload"></i>
										</span> <input type="file" class="form-control" placeholder="upload"
											name="txtFile">
									</div>
									<p>
										Only (<strong>.xlsx</strong>) files are allowed<br> (The
										maximum file size for uploads is <strong>5 MB</strong>)
									</p>
								</div>
							</div>

							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<button type="Submit" class="btn green">Set TimeTable</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

			<div class="tab-pane fade" id="tab_2_2">

				<div class="portlet-body form">
					<!-- BEGIN FORM-->
					<form action="ad_getTimetable.web" id="form2" method="post"
						class="form-horizontal" enctype="multipart/form-data">
						<div class="form-body">

							<div class="form-group">
								<label class="col-md-3 control-label" style="font-weight: bold">Search
									For <span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="mstcategory2" class="form-control" id="roles2"
										style="width: 360px;">
										<option value="">Select Person</option>
										<c:forEach var="cat" items="${cat}" begin="0" end="1">
											<option value="${cat.intCategoryId1}">${cat.txtCategoryName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group" id="class2">
								<label class="col-md-3 control-label" style="font-weight: bold">Class
									<span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="mststudclass2" class="form-control"
										style="width: 360px;">
										<option value="">Select class</option>
										<c:forEach var="classes" items="${classes}" begin="0" end="11">
											<option value="${classes.intClassId}">${classes.txtClassName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group" id="section2">
								<label class="col-md-3 control-label" style="font-weight: bold">Section
									<span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="mststudclasssection2" class="form-control"
										style="width: 360px;">
										<option value="">Select Section</option>
										<c:forEach var="sections" items="${sections}" begin="0"
											end="3">
											<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group" id="teachId2">
								<label class="col-md-3 control-label" style="font-weight: bold">Teacher
									Id <span class="required"> * </span>
								</label>
								<div class="col-md-9" style="width: 360px;">
									<select name="teacherIdForInbox2" class="form-control"
										style="width: 360px;">
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
									<input type="hidden" value="11" name="secondTab">
									<button type="Submit" class="btn green">Get TimeTable</button>
								</div>
							</div>

						</div>
					</form>
				</div>

				<!-- For Inbox -->

				<c:if test="${not empty teachStatus}">
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet box green">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-list"></i>Timetable For Teacher
									</div>
									<div class="tools">
										<a href="javascript:;" class="collapse"> </a> <a
											href="javascript:;" class="reload"> </a>


									</div>
								</div>
								<div class="portlet-body">

									<c:choose>
										<c:when test="${not empty teachTTList }">
											<table class="table table-striped table-hover table-bordered"
												id="sample_editable_1">
												<thead>
													<tr style="background-color: #E5E4E2">
														<th>Sl No</th>
														<th>Day</th>
														<th>Timing</th>
														<th>Class</th>
														<th>Section</th>
														<th>Subject</th>
														<th>Update</th>
														<!-- <th class="info">Delete</th> -->
													</tr>
												</thead>
												<tbody>

													<%!int i = 1;%>
													<c:forEach var="mst" items="${teachTTList}">
														<tr>
															<form
																action="updateTeacherTiming/${mst.intTeacherTimeTableId}.web"
																id="form3">
																<td><%=i%></td>

																<td><select name="day" class="form-control">
																		<option value="">Select Day</option>
																		<c:forEach var="d" items="${days}">
																			<c:if
																				test="${d.intDaysId == mst.tbldaysofweek.intDaysId}">
																				<option value="${d.intDaysId}"
																					selected="${d.txtDay}">${d.txtDay }</option>
																			</c:if>
																			<c:if
																				test="${d.intDaysId != mst.tbldaysofweek.intDaysId}">
																				<option value="${d.intDaysId }">${d.txtDay }</option>
																			</c:if>
																		</c:forEach>
																</select></td>

																<td><select name="timing" class="form-control">
																		<option value="">Select Time</option>
																		<c:forEach var="time" items="${timings}">
																			<c:if
																				test="${time.intMsttimingsId == mst.msttimings.intMsttimingsId}">
																				<option value="${time.intMsttimingsId}"
																					selected="${time.txtClassTimings}">${time.txtClassTimings }</option>
																			</c:if>
																			<c:if
																				test="${time.intMsttimingsId != mst.msttimings.intMsttimingsId}">
																				<option value="${time.intMsttimingsId }">${time.txtClassTimings }</option>
																			</c:if>
																		</c:forEach>
																</select></td>

																<td><select name="class" class="form-control">
																		<option value="">Select Class</option>
																		<c:forEach var="c" items="${classes }" begin="0"
																			end="11">
																			<c:if
																				test="${c.intClassId == mst.mststudclass.intClassId}">
																				<option value="${c.intClassId}"
																					selected="${c.txtClassName}">${c.txtClassName }</option>
																			</c:if>
																			<c:if
																				test="${c.intClassId != mst.mststudclass.intClassId}">
																				<option value="${c.intClassId }">${c.txtClassName }</option>
																			</c:if>
																		</c:forEach>
																</select></td>

																<td><select name="section" class="form-control">
																		<option value="">Select Section</option>
																		<c:forEach var="s" items="${sections }" begin="0"
																			end="3">
																			<c:if
																				test="${s.intSectionId  == mst.mststudclasssection.intSectionId}">
																				<option value="${s.intSectionId}"
																					selected="${s.txtSectionName}">${s.txtSectionName }</option>
																			</c:if>
																			<c:if
																				test="${ s.intSectionId != mst.mststudclasssection.intSectionId}">
																				<option value="${s.intSectionId }">${s.txtSectionName }</option>
																			</c:if>
																		</c:forEach>
																</select></td>
																<td><select name="subject" class="form-control">
																		<option value="">Select Subject</option>
																		<c:forEach var="sub" items="${subject }">
																			<c:if
																				test="${sub.intSubjectId == mst.mstsubject.intSubjectId}">
																				<option value="${sub.intSubjectId}"
																					selected="${sub.txtSubjectName}">${sub.txtSubjectName }</option>
																			</c:if>
																			<c:if
																				test="${sub.intSubjectId != mst.mstsubject.intSubjectId}">
																				<option value="${sub.intSubjectId }">${sub.txtSubjectName }</option>
																			</c:if>
																		</c:forEach>
																</select></td>

																<td>
																	<div class="btn-group btn-group-solid">
																		<input type="hidden" value="11" name="secondTab">
																		<button class="btn green" type="submit">
																			Update <i class="fa fa-edit"></i>
																		</button>
																	</div>
																</td>

															</form>
														</tr>

														<%
															i++;
														%>
													</c:forEach>
													<%
														i = 1;
													%>
												</tbody>
											</table>
										</c:when>
										<c:otherwise>
											<td>
												<div align="center">
													<h3 style="text-align: center; font-weight: bolder;">
														<i class="fa fa-warning" style="font-size: 25px;"></i>No
														Records Found!!!
													</h3>
												</div>
											</td>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<!-- END EXAMPLE TABLE PORTLET-->
						</div>
					</div>
				</c:if>

				<c:if test="${not empty stuStatus}">
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet box green">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-list"></i>Timetable For Student
									</div>
									<div class="tools">
										<a href="javascript:;" class="collapse"> </a> <a
											href="javascript:;" class="reload"> </a>


									</div>
								</div>
								<div class="portlet-body">

									<c:choose>
										<c:when test="${not empty stuTTList }">
											<table class="table table-striped table-hover table-bordered"
												id="sample_editable_1">
												<thead>
													<tr style="background-color: #E5E4E2">
														<th>Sl No</th>
														<th>Day</th>
														<th>Timing</th>
														<th>Subject</th>
														<th>Update</th>
													</tr>
												</thead>
												<tbody>

													<%!int j = 1;%>
													<c:forEach var="mst" items="${stuTTList}">

														<tr>
															<form
																action="updateStudentTiming/${mst.intStudentTimeTableId}.web"
																id="form4">
																<td><%=j%></td>

																<td><select name="day" class="form-control">
																		<option value="">Select Day</option>
																		<c:forEach var="d" items="${days}">
																			<c:if
																				test="${d.intDaysId == mst.tbldaysofweek.intDaysId}">
																				<option value="${d.intDaysId}"
																					selected="${d.txtDay}">${d.txtDay }</option>
																			</c:if>
																			<c:if
																				test="${d.intDaysId != mst.tbldaysofweek.intDaysId}">
																				<option value="${d.intDaysId }">${d.txtDay }</option>
																			</c:if>
																		</c:forEach>
																</select></td>
																<td><select name="timing" class="form-control">
																		<option value="">Select Time</option>
																		<c:forEach var="time" items="${timings}">
																			<c:if
																				test="${time.intMsttimingsId == mst.msttimings.intMsttimingsId}">
																				<option value="${time.intMsttimingsId}"
																					selected="${time.txtClassTimings}">${time.txtClassTimings }</option>
																			</c:if>
																			<c:if
																				test="${time.intMsttimingsId != mst.msttimings.intMsttimingsId}">
																				<option value="${time.intMsttimingsId }">${time.txtClassTimings }</option>
																			</c:if>
																		</c:forEach>
																</select></td>

																<td><select name="subject" class="form-control">
																		<option value="">Select Subject</option>
																		<c:forEach var="sub" items="${subject }">
																			<c:if
																				test="${sub.intSubjectId == mst.mstsubject.intSubjectId}">
																				<option value="${sub.intSubjectId}"
																					selected="${sub.txtSubjectName}">${sub.txtSubjectName }</option>
																			</c:if>
																			<c:if
																				test="${sub.intSubjectId != mst.mstsubject.intSubjectId}">
																				<option value="${sub.intSubjectId }">${sub.txtSubjectName }</option>
																			</c:if>
																		</c:forEach>
																</select></td>

																<td>
																	<div class="btn-group btn-group-solid">
																		<input type="hidden" value="11" name="secondTab">
																		<button class="btn green" type="submit">
																			Update <i class="fa fa-edit"></i>
																		</button>
																	</div>
																</td>

															</form>
														</tr>

														<%
															j++;
														%>
													</c:forEach>
													<%
														j = 1;
													%>
												</tbody>
											</table>
										</c:when>
										<c:otherwise>
											<td>
												<div align="center">
													<h3 style="text-align: center; font-weight: bolder;">
														<i class="fa fa-warning" style="font-size: 25px;"></i>No
														Records Found!!!
													</h3>
												</div>
											</td>
										</c:otherwise>
									</c:choose>


								</div>
							</div>
							<!-- END EXAMPLE TABLE PORTLET-->
						</div>
					</div>
				</c:if>
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

<!-- END BORDERED TABLE PORTLET-->

<!-- END PAGE CONTENT-->
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		slider(7);
	});
</script>
<%@include file="footer.jsp"%>