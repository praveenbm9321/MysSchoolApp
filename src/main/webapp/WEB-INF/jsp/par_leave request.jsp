<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE HEADER-->
<%@include file="headerForParent.jsp"%>








<script type="text/javascript">
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin
			rules : {
				mstregistration : {
					required : true

				},
				mstleave : {
					required : true
				},
				dtFromDate : {
					required : true
				},
				dtToDate : {
					required : true
				},
				txtReason : {
					required : true
				}
			},

			messages : {
				mstregistration : {
					required : "Please select student"

				},
				mstleave : {
					required : "Select leave type"
				},
				dtFromDate : {
					required : "Select from date"
				},
				dtToDate : {
					required : "Select to date"
				},
				txtReason : {
					required : "Select reason"
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
		<h3 class="page-title">Leave Application</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="ad_index_For_Parent.web"> Parent
			</a> <i class="fa fa-angle-right"></i></li>
			<li>
				<!-- <a href="#">  -->Leave Application <!-- </a>  -->
				<!-- <i class="fa fa-angle-right"></i> -->
			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<c:if test="${!empty msg1}">
	<%-- <c:if test="${empty trnBetweenAttendance }"> --%>
	<%-- <div class="alert alert-success">
		<strong>${msg1}&nbsp;&nbsp;&nbsp;</strong>
	</div> --%>
	<div class="alert alert-warning alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
			<i class="fa fa-warning"></i>
		<strong>${msg1}</strong>
	</div>
	<%-- </c:if> --%>
</c:if>
<c:if test="${!empty msg }">
	<div class="alert alert-success">
		<i class="fa fa-check-circle"></i> <strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<%-- <c:url var = "addAction" value = "par_leave request.web"></c:url> --%>
<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>
<form action="par_leave request.web" method="POST" id="form1">

	<div class="form-group">
		<label class="col-md-2 control-label"><b>Select Student <span
				class="required" style="color: red"> * </span></b></label>
		<div class="col-md-5">
			<div class="input-group">
				<span class="input-group-addon"> <i class="fa fa-user"></i></span> <select
					style="width: 40%" class="form-control" name="mstregistration">
					<!-- <option value="">Select</option> -->
					<c:forEach items="${regal}" var="m">
						<option value="${m.intRegistrationId}">${m.txtFirstName}
							${m.txtLastName}</option>
					</c:forEach>
				</select>
			</div>
			<span style="color: red; font-weight: bolder;"> <span
				class="field-validation-valid" id="validationMessages5"></span>
			</span>
		</div>
	</div>
	<br> <br> <br>







	<div class="row">
		<div class="col-md-12">

			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption">Leave Application</div>
				</div>
				<div class="portlet-body">
					<div class="table-responsive">

						<table class="table table-bordered table-hover">
							<tbody>
								<tr>
									<td style="font-weight: bold">Leave Type <span
										class="required" style="color: red"> * </span></td>
									<td><div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span><select style="width: 49.5%" class="form-control"
										name="mstleave" id="leaveType">
											<option value="">Select</option>
											<c:forEach var="l" items="${leaveal }">
												<option value="${l.intLeaveId }">${ l. txtLeaveType }</option>
											</c:forEach>
									</select></div> <span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages1"></span>
									</span></td>

									<td style="font-weight: bold">From Date <span
										class="required" style="color: red"> * </span></td>
									<td>
									<div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
									<input name="dtFromDate" id="date1" readonly="readonly"
										class="form-control form-control-inline input-medium date-picker"  placeholder="From"/></div>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages2"></span>
									</span></td>
								</tr>


								<tr>
									<td style="font-weight: bold">To Date<span
										class="required" style="color: red"> * </span>
									</td>
									<td><div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span><input name="dtToDate" id="date2" readonly="readonly" placeholder="To"
										class="form-control form-control-inline input-medium date-picker" /></div>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages3"></span>
									</span></td>
									<!-- <td style="font-weight: bold">Please specify reason <span
										class="required" style="color: red"> * </span>
									</td>
									<td><textarea class="form-control" name="txtReason"
											id="txtReason"></textarea> <span
										style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages4"></span>
									</span></td> -->

									<td style="font-weight: bold">Please specify reason <span
										class="required" style="color: red"> * </span>
									</td>
									<td><textarea class="form-control" name="txtReason"
											style="width: 400px; resize: none;" id="txtReason" placeholder="Enter Reason"></textarea>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages4"></span>
									</span></td>


								</tr>




							</tbody>
						</table>
						<div style="padding-left: 450px">
							<input type="submit" class="btn green" value="Submit">
						</div>


					</div>
				</div>
			</div>
			<!-- END BORDERED TABLE PORTLET-->
		</div>
	</div>








</form>
<%-- </form:form> --%>
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		slider(210);
	});
</script>
<%@include file="footer.jsp"%>