<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE HEADER-->
<%@include file="headerForParent.jsp"%>

<!-- <script>
	function myFunction() {
		var x = document.getElementById("form1").elements[0].value;

		document.getElementById("form2").elements[0].value = x;
	}
</script> -->


<script type="text/javascript">
	$(document).ready(function() {

		$('#form2').validate({ // initialize the plugin
			rules : {
				mstregistration1 : {
					required : true

				},
				fromDate : {
					required : true
				},
				toDate : {
					required : true
				}
			},

			messages : {
				mstregistration1 : {
					required : "Please select student"

				},
				fromDate : {
					required : "Select leave type"
				},
				toDate : {
					required : "Select from date"
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




<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



<script>
	$(document)
			.ready(
					function() {
						$('#parent').change(function() {
							request = new XMLHttpRequest();
							var v1 = document.vinform.mstregistration.value;

							var url = "ajax_par_attendance.web?val1=" + v1;
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
								document
										.getElementById('studentDataForAttendance').innerHTML = val;
							}
						}
						$('#parent').trigger('change');
					});
</script>




<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			Attendance <small>Your child's daily report</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li>
				<!-- <a href="#"> --> Attendance <!-- </a> -->
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<%-- <c:if test="${!empty msg}">

	<c:if test="${empty trnAttendance }">
		<div class="alert alert-info">
			<strong>No Record Found!!!</strong>
		</div>
	</c:if>
</c:if> --%>

<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<form action="par_attendance.web" method="POST" id="form1"
	name="vinform">
	<div class="form-group">
		<label class="col-md-2 control-label"><b>Select Student <font
				color="red"> *</font>
		</b></label>
		<div class="col-md-5">
			<div class="input-group">
				<span class="input-group-addon"> <i class="fa fa-user"></i></span> <select
					id="parent" style="width: 40%" class="form-control"
					name="mstregistration">
					<c:forEach items="${regal}" var="m">
						<option value="${m.txtFirstName}">${m.txtFirstName} ${m.txtLastName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
</form>
<br>
<br>
<br>
<div id="studentDataForAttendance"></div>

<%-- <c:if test="${!empty msg1}">
	<c:if test="${empty trnBetweenAttendance }">
		<div class="alert alert-info">
			<strong>${msg1}&nbsp;&nbsp;&nbsp;Alert! No Record Found</strong>
		</div>
	</c:if>
</c:if> --%>

<%-- 
<div class="row">
	<div class="col-md-12">
		<div class="portlet-body">
			<div class="table-responsive">
				<c:if test="${! empty list}">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>Date</th>
								<th>In Time</th>
								<th>Out Time</th>
								<th>Total Hours(HH:MM)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="a">
								<tr>
									<td>${a.date }</td>
									<td>${a.inTime }</td>
									<td>${a.outTime }</td>
									<td>${a.totalhrs }</td>
								</tr>
							</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<h3 style="text-align: center; font-weight: bolder;">No
									Record Found</h3>
							</c:if>
						</tbody>
					</table>
			</div>
		</div>
	</div>
</div>


 --%>




<div class="row">
	<div class="col-md-12">

	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">Attendance History</div>
			<br />
		</div>	
<div class="portlet-body">
			<div class="table-responsive">
			<form action="par_attendance.web" method="post" id = "form2">
				<table class="table table-striped table-bordered table-hover">
					<tr>
						<th><b>Select Student<font color="red"> *</font></b></th>
						<th><div class="input-group">
								<span class="input-group-addon"> <i class="fa fa-user"></i></span>
								<select id="parent1" style="width: 150px;" class="form-control"
									name="mstregistration1">

									<c:forEach items="${regal}" var="m">
										<option value="${m.txtFirstName}">${m.txtFirstName}
											${m.txtLastName}</option>
									</c:forEach>
								</select>
							</div> <span style="color: red; font-weight: bolder;"> <span
								class="field-validation-valid" id="validationMessages8"></span>
						</span></th>
						<!-- data-date="10/11/2012" data-date-format="mm/dd/yyyy" -->
						<td><span class="input-group-addon">From</span></td>
						<td>

							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-calendar"></i>
								</span> <input name="fromDate"
									class="form-control form-control-inline input-medium date-picker" size="50" placeholder="From"
									id="date1" readonly="readonly"> <span
									style="color: red; font-weight: bolder;"> <span
									class="field-validation-valid" id="validationMessages6"></span>
								</span>
							</div>
						</td>
						<td><span class="input-group-addon"> To </span></td>
						<td>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-calendar"></i>
								</span> <input name="toDate"
									class="form-control form-control-inline input-medium date-picker" placeholder="To"
									id="date2" readonly="readonly"> <span
									style="color: red; font-weight: bolder;"> <span
									class="field-validation-valid" id="validationMessages7"></span>
								</span>
							</div>
						</td>
						<th>
							<button type="submit" id="projsubmit2"
								class="btn yellow filter-submit margin-bottom" id="btnSearch">
								<i class="fa fa-search"></i> Search
							</button>
						</th>


					</tr>
				</table>
			</form>




		
<!-- 	</div>
</div>
<div class="row">
	<div class="col-md-12"> -->
		
				<c:if test="${! empty list}">
					<table id="sample_editable_1"
									class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #E5E4E2">
								<th>Date</th>
								<th>In Time</th>
								<th>Out Time</th>
								<th>Total Hours(HH:MM)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="a">
								<tr>
									<td>${a.date }</td>
									<td>${a.inTime }</td>
									<%-- <td>${a.outTime }</td>
									<td>${a.totalhrs }</td> --%>
									<td><c:choose>
													<c:when test="${empty a.outTime }">
													--:--
												</c:when>
													<c:otherwise>
														${a.outTime}
													</c:otherwise>
												</c:choose></td>
												
												<td><c:choose>
													<c:when test="${  empty a.totalhrs }">
													00:00
												</c:when>
													<c:otherwise>
														${a.totalhrs}
													</c:otherwise>
												</c:choose></td>
								</tr>
							</c:forEach>
							</tbody>
					</table>
							</c:if>
							
							<c:if test="${empty list}">
							
								<h3 style="text-align: center; font-weight: bolder;"><i class="fa fa-warning" style="font-size: 25px;"></i> No
									Record Found</h3>
							</c:if>
						
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

<!-- validation end  -->


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		 /* TableEditable.init(); */
		slider(202);
	});
</script>
<%-- <%@include file="par_attendance_1.jsp"%> --%>
 <%@include file="footer.jsp"%> 













