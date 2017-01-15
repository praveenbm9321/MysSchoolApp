<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="input-group">
			<span class="input-group-addon"> <i class="fa fa-user"></i>
			
			</span> 
			<select class="form-control select2" id="select2_sample_modal_2"
	name="select" style="width: 300px;" placeholder="Select Student"
	multiple>

	<c:forEach items="${students}" var="s">
		
		<option value="${s.intRegistrationId}">
			${s.txtFirstName}&nbsp;${s.txtLastName}</option>
	</c:forEach>
</select>
</div> 

