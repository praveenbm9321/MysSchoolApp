<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="form-group">
										<label class="col-md-3 control-label" style = "font-weight:bold">Student Name
										<span class="required">
											 *
										</span>
										</label>
										<div class="col-md-9" style = "width:360px;">
										<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-user"></i>
												</span>
										<%-- <form:select path="mstregistration.intRegistrationId" class="form-control" id = "student">
										<form:option value="-1" label="--Select Student--"/>
										<form:options items="${students}" itemLabel="txtFirstName" itemValue="intRegistrationId"/>
										
										</form:select> --%>
										<select name="student" class="form-control" id="select2_sample_modal_2">
	<option value="">Select Student Name</option>
	<c:forEach var="students" items="${students}">
		<option value="${students.intRegistrationId}">${students.txtFirstName}&nbsp;${students.txtLastName}</option>
	</c:forEach>

</select>
										</div>
										
										
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages4"></span>
										</span>
										</div>
									</div>


