<%@page import="org.hibernate.type.AdaptedImmutableType"%>
<%@include file="headerForStudent.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
	$(document).ready(function() {

		$('#searhForm').validate({ // initialize the plugin

			rules : {

				fromDate : {
					required : true,
				},

				toDate : {
					required : true,
				}

			},

			messages : {

				fromDate : {
					required : "please select from date"
				},
				toDate : {
					required : "please select to date"
				}

			},

			highlight : function(element) {

				$(element).parent().addClass('error');

			}

		});

	});
</script>

<style>
#searhForm .error {
	color: red;
	font-size: 13px;
}
</style>




<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<!-- <h3 class="page-title">
			Attendance <small>Your child's daily report</small>
		</h3> -->
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Student.web"> Student </a> <i
				class="fa fa-angle-right"></i></li>
			<li>
				<!-- <a href="#">  -->Attendance <!-- </a> -->
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="tab-content">
	<div class="tab-pane active" id="tab_0">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN BORDERED TABLE PORTLET-->
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">Current Attendance</div>
					</div>
					<%-- <%!int k = 0;
	int l = 2;%> --%>
					<div class="portlet-body">
						<div class="table-responsive">

							<c:if test="${not empty attendance}">
								<c:choose>

									<c:when test="${not empty attndList }">

										<table class="table table-bordered table-hover">
											<thead>
												<tr style="background-color: #E5E4E2">
													<th>Date</th>
													<th>In-Time</th>
													<th>Out-Time</th>
													<th>Total-Hours</th>

												</tr>
											</thead>
											<tbody>

												<%!int k = 0;
	int l = 2;%>
												<c:forEach var="ad" items="${attndList}">
													<tr>

														<td>${ad.dtSlotDate}</td>
														<c:forEach var="out" items="${output1}" begin="<%=k %>"
															end="<%=l %>">
															<td>${out}</td>
														</c:forEach>
														<%
															k += 3;
																			l += 3;
														%>
													</tr>
												</c:forEach>
											</tbody>
											<%
												k = 0;
															l = 2;
											%>
										</table>
									</c:when>
									<c:otherwise>
										<div align="center">
											<h3>
												<i class="fa fa-warning" style="font-size: 25px;"></i> No
												Records Found
											</h3>
										</div>

									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
					</div>
				</div>
				<!-- </div> -->


				<c:if test="${!empty msg1}">
					<c:if test="${empty trnBetweenAttendance }">
						<div class="alert alert-warning "
							style="background-color: #f7e989; color: #716252">

							<i class="fa fa-warning"></i> <strong>${msg1}</strong>
						</div>
					</c:if>
				</c:if>
				<!-- END BORDERED TABLE PORTLET-->
				<!-- BEGIN BORDERED TABLE PORTLET-->
				<!-- <div class="portlet box green"> -->
				<!-- <div class="portlet-title"> -->
				<!--<label class="control-label col-md-1"><b>From</b></label>-->
				<form action="stu_attedance.web" id="searhForm" class="form-horizontal" method="post">
					<div class="portlet box green">
						<div class="portlet-title">
							<label class="control-label col-md-1">From</label>
							<!-- <div class="col-md-4">
										<div
											class="input-group input-large date-picker input-daterange"
											data-date="2012-09-26" data-date-format="yyyy-mm-dd">

											<input type="text" class="form-control" name="from"
												id="date1"> <span
												style="color: red; font-weight: bolder;"> <span
												class="field-validation-valid" id="validationMessages6"></span>
											</span> <span class="input-group-addon"> to </span> <input
												type="text" class="form-control" name="to" id="date2">

											<span style="color: red; font-weight: bolder;"> <span
												class="field-validation-valid" id="validationMessages7"></span>
											</span>

										</div>
									</div> -->










							<div class="col-md-4">
								<table class="table table-striped table-bordered table-hover">
									<tr>
										<td><span class="input-group-addon">From</span></td>
										<td>

											<div class="input-group">
												<span class="input-group-addon"><i
													class="fa fa-calendar"></i> </span> <input name="fromDate"
													class="form-control form-control-inline input-medium date-picker"
													size="50" placeholder="From" id="date1" readonly="readonly">
												<span style="color: red; font-weight: bolder;"> <span
													class="field-validation-valid" id="validationMessages6"></span>
												</span>
											</div>
										</td>
										<td><span class="input-group-addon"> To </span></td>
										<td>
											<div class="input-group">
												<span class="input-group-addon"><i
													class="fa fa-calendar"></i> </span> <input name="toDate"
													class="form-control form-control-inline input-medium date-picker"
													placeholder="To" id="date2" readonly="readonly"> <span
													style="color: red; font-weight: bolder;"> <span
													class="field-validation-valid" id="validationMessages7"></span>
												</span>
											</div>
										</td>
										<td><input type="submit" class="btn btn-primary" value="Search"></td>
									</tr>
								</table>

							</div>
							<!-- <input type="Submit" value="Search" class="btn btn-primary"
										id="submit2"> -->
						</div>

						<%-- <%!int i = 0;
	int j = 2;%> --%>
						<!--table begin-->
						<div class="portlet-body">
							<div class="table-responsive">
								<c:if test="${not empty attendance}">
									<c:choose>

										<c:when test="${not empty trnBetweenAttendance }">

											<table id="sample_editable_1"
												class="table table-bordered table-hover">
												<thead>
													<tr style="background-color: #E5E4E2">

														<th>Date</th>
														<th>In-Time</th>
														<th>Out-Time</th>
														<th>Total-Hours</th>
													</tr>
												</thead>
												<tbody>

													<%!int i = 0;
	int j = 2;%>

													<c:forEach var="ad" items="${trnBetweenAttendance}">
														<tr>

															<td>${ad.dtSlotDate}</td>

															<c:forEach var="out" items="${out}" begin="<%=i %>"
																end="<%=j %>">
																<td>${out}</td>
															</c:forEach>
															<%
																i += 3;
																				j += 3;
															%>
														</tr>
													</c:forEach>
													<%
														i = 0;
																	j = 2;
													%>
												</tbody>
											</table>
										</c:when>


										<c:otherwise>
											<div align="center">
												<h3>
													<i class="fa fa-warning" style="font-size: 25px;"></i> No
													Records Found
												</h3>
											</div>
										</c:otherwise>
									</c:choose>
								</c:if>
							</div>
						</div>
					</div>
				</form>
				<!-- END BORDERED TABLE PORTLET-->
				<!-- </div> -->

				<!-- END PAGE CONTENT-->
			</div>
		</div>
		<!-- END CONTENT -->
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

<!-- validation end  -->


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		/* TableEditable.init(); */
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		slider(302);
	});
</script>
 <%@include file="footer.jsp"%>  
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->