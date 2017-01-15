<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin
			rules : {
				teacherId : {
					required : true
				}
			},

			messages : {
				teacherId : {
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
#form1 .error {
	color: red;
	font-size: 12px;
}
</style>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Teacher Attendance Track</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Time Table</h5></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">


			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>Attendance Track for Teacher
				</div>
			</div>

			<div class="portlet-body">

				<form action="ad_teach_attend.web" class="form-horizontal"
					id="form1" method="POST">

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

					<div class="portlet-title" style="padding: 2px 4px 4px 11px;">
						<label class="col-md-3 control-label"><b>Select
								Date&nbsp&nbsp&nbsp&nbsp</b></label>
						<!-- <div class="input-group input-large date-picker input-daterange"
							data-date="2012-09-26" data-date-format="yyyy-mm-dd">
							<input type="text" class="form-control" name="teachFromDate"
								placeholder="From"> <span class="input-group-addon"><i
								class="fa fa-angle-double-right"></i> </span> <input type="text"
								class="form-control" name="teachToDate" placeholder="To">
						</div> -->

						<div class="input-group input-large date-picker input-daterange"
							data-date="2012-09-26" data-date-format="yyyy-mm-dd"
							style="width: 360px;">

							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-calendar"></i>
								</span> <input type="text" class="form-control" name="from" placeholder="From"
									readonly="readonly">
								<!-- </div> -->
								<!-- <span class="input-group-addon"> -->
								<!-- <i
								class="fa fa-angle-double-right"></i>  </span> -->
								<!-- <div class="input-group"> -->
								<span class="input-group-addon"><i class="fa fa-calendar"></i>
								</span> <input type="text" class="form-control" name="to" placeholder="To"
									readonly="readonly">
							</div>
						</div>

					</div>

					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button type="Submit" class="btn green">Search</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>

</div>

<!-- BEGIN BORDERED TABLE PORTLET-->
<c:if test="${not empty check }">
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">Time table</div>
		</div>
		<div class="portlet-body">
			<div class="table-responsive">
				<table class="table table-bordered table-hover" id="sample_editable_1">
					<thead>
						<tr style="background-color: #E5E4E2">
							<td  align="center"><b>Date</b></td>
							<td  align="center"><b>Day</b></td>
							<c:forEach items="${timingList }" var="time">
								<td  align="center"><b>${time.txtClassTimings }</b></td>
							</c:forEach>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${empty cc }">
							<c:choose>
								<c:when test="${empty danger }">
									<tbody>
										<%!int i, j, y, z, kk, ll, mm, nn = 0;
	int m = 0;
	int n = 0;
	int mqqq = 0;
	int nqqq = 6;%>
										<c:forEach items="${totalDays}" var="w">
											<tr style="background-color: #E5E4E2">
												<%
													i = 0;
																			j = 0;
																			mqqq = 0;
																			nqqq = 6;
												%>
												<c:set var="count" value="1" />
												<c:forEach items="${dateList}" var="dateObj"
													begin="<%=kk %>" end="<%=ll %>">
													<td align="center" ><fmt:formatDate
															value="${dateObj}" pattern="dd-MMM-yyyy" /></td>
												</c:forEach>
												<%
													kk++;
																			ll++;
												%>
												<c:forEach items="${tblDOWList}" var="dow" begin="<%=mm %>"
													end="<%=nn %>">
													<td align="center" >${dow.txtDay }</td>
												</c:forEach>
												<%
													mm++;
																			nn++;
												%>
												<c:forEach items="${allList}" var="allListObj"
													begin="<%=m %>" end="<%=n %>">
													<c:forEach items="${allListObj}" var="all"
														begin="<%=mqqq %>" end="<%=nqqq %>">
														<c:choose>
															<c:when test="${all.intStatus eq 1 }">

																<c:forEach items="${listOfListForCheckIn}" var="listObj"
																	begin="<%=y %>" end="<%=z %>">
																	<c:choose>
																		<c:when test="${not empty listObj}">
																			<c:forEach items="${listObj}" var="s" begin="<%=i %>"
																				end="<%=j %>">
																				<c:choose>
																					<c:when
																						test="${ all.intTeacherTimeTableId == s.mstteachertimetable.intTeacherTimeTableId }">
																						<td style="background-color: #339966"
																							align="center">
																							<table>
																								<tr>
																									<td align="center" colspan="2">${s.mstteachertimetable.mstsubject.txtSubjectName }</td>
																								</tr>
																								<tr>
																									<td align="center" colspan="2">${s.dtCheckInTime }</td>
																								</tr>
																								<tr>
																									<td align="center">Class
																										${s.mstteachertimetable.mststudclass.txtClassName }</td>
																									<td align="center">Section
																										${s.mstteachertimetable.mststudclasssection.txtSectionName }</td>
																								</tr>

																							</table>
																						</td>
																						<c:if test="${fn:length(listObj) eq count }">
																							<%
																								i -= 1;
																																									j -= 1;
																							%>
																						</c:if>
																					</c:when>
																					<c:otherwise>
																						<td style="background-color: #ff8566"
																							align="center">${all.mstsubject.txtSubjectName }</td>
																						<%
																							i--;
																																							j--;
																						%>
																						<c:set var="count" value="${count-1 }" />
																					</c:otherwise>
																				</c:choose>

																				<%
																					i++;
																																			j++;
																				%>
																				<c:set var="count" value="${count+1 }" />
																			</c:forEach>
																		</c:when>
																		<c:otherwise>
																			<td style="background-color: #ff8566" align="center">${all.mstsubject.txtSubjectName }</td>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<td align="center">N/A</td>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:forEach>
												<%
													m++;
																			n++;
												%>
												<%
													y++;
																			z++;
												%>

											</tr>
										</c:forEach>
										<%
											i = 0;
																j = 0;
																m = 0;
																n = 0;
																y = 0;
																z = 0;
																kk = 0;
																ll = 0;
																mm = 0;
																nn = 0;
																mqqq = 0;
																nqqq = 6;
										%>
										<c:set var="count" value="1" />
									</tbody>
								</c:when>
								<c:otherwise>
									<td colspan="9">
										<h3 style="text-align: center; font-weight: bolder;">
											<i class="fa fa-warning" style="font-size: 25px;"></i> No
											Records Found!!!
										</h3>
									</td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<td colspan="9">
								<h4 style="text-align: center; font-weight: bolder;">
									<i class="fa fa-warning" style="font-size: 25px;"></i> There is
									no Timetable for <strong>'${fName }&nbsp;${lName }'</strong>!!!
								</h4>
							</td>
						</c:otherwise>
					</c:choose>
				</table>

			</div>
		</div>
	</div>

</c:if>
<!-- END BORDERED TABLE PORTLET-->
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
		slider(15);
	});
</script>
<%@include file="footer.jsp"%>