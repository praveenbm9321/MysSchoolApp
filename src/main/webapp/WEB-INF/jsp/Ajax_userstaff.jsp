
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${not empty Smstal }">
<div class="form-group">
	<label class="col-md-3 control-label"><b> Student Name[ID]</b></label>
	<div class="col-md-4">
		<div class="input-group">
			<span class="input-group-addon"> <i class="fa fa-user"></i>
			</span> <select class="form-control select2" id="select2_sample_modal_2"
				name="StudentId" style="width: 300px;" placeholder="Select Student">
				<option value="">select</option>
				<c:forEach items="${Smstal}" var="r">
				<option value="${r.intRegistrationId}">${r.txtFirstName} ${r.txtLastName} [${r.intRegistrationId}]</option>
				</c:forEach>
			</select>
		</div>
	</div>
</div>
</c:if>		

<c:if test="${not empty Tmstal }">
<div class="form-group">
	<label class="col-md-3 control-label"><b> Staff Name[ID]</b></label>
	<div class="col-md-4">
		<div class="input-group">
			<span class="input-group-addon"> <i class="fa fa-user"></i>
			</span> <select class="form-control select2" id="select2_sample_modal_2"
				name="StudentId" style="width: 300px;" placeholder="Select Student">
				<option value="">select</option>
				<c:forEach items="${Tmstal}" var="r">
				<option value="${r.intRegistrationId}">${r.txtFirstName} ${r.txtLastName} [${r.intRegistrationId}]</option>
				</c:forEach>
			</select>
		</div>
	</div>
</div>
</c:if>	


	
	
		