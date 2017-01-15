<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript">
	$(document).ready(function() {
		$("#schl").change(function() {

			$("#emailid").hide();

		}).change();
	});
</script> -->

<div class="form-group">
	<label class="col-md-3 control-label"><b>School</b> <span
		class="required"> * </span></label>
	<div class="col-md-4">
		<div class="input-group ">
			<span class="input-group-addon"> <i class="fa fa-building-o"></i>
			</span> <select
				class="table-group-action-input form-control input-inline input-small input-sm"
				name="mstschool" id="schl">
				<option value="">select</option>
				<c:forEach var="m" items="${mstschools}">
					<option value="${m.intSchoolId}" value1="${m.txtEmail}">${m.txtSchoolName}</option>
				</c:forEach>
			</select>
		</div>
	</div>
</div>

<div class="form-group" id="emailid">
	<label class="col-md-3 control-label"><b>Email id</b> </label>
	<div class="col-md-4">
		<div class="input-group">
			<span class="input-group-addon"> <i class="fa fa-user"></i>
			</span> <input type="text" class="form-control" placeholder=""
				name="txtMailId" id="mailfield" readonly>
		</div>
	</div>
</div>