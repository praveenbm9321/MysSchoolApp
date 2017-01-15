<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- BEGIN PLUGINS USED BY X-EDITABLE -->


<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2-metronic.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-datepicker/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-datetimepicker/css/datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-editable/bootstrap-editable/css/bootstrap-editable.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-editable/inputs-ext/address/address.css" />
<!-- END PLUGINS USED BY X-EDITABLE -->
<!-- <script type="text/javascript">
	document.getElementById("mySelect").value = "${studentName}";
</script> -->
<!-- BEGIN PAGE HEADER-->
<%@include file="headerForParent.jsp"%>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



<script>
$(document).ready(function(){
	$('#parent').change(function(){
		request = new XMLHttpRequest();
		var v1 = document.vinform.studentName.value;

		var url = "ajax_par_student_profile.web?val1="+v1;

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
			document.getElementById('studentData').innerHTML = val;
		}
	}
	$('#parent').trigger('change');
});
</script>



<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			Student's Information <small>Details of your child</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><a href="#"> Student Profile </a></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<c:if test="${!empty noRecordErr }">
	<div class="alert alert-danger">
		<strong>Alert! No Student Record Found</strong>
	</div>
</c:if>

<c:if test="${!empty msg }">
	<div class="alert alert-success">
		<strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<%-- <form  action="${pageContext.request.contextPath}/par_student profile" name="form1" id="smart-form" method="post"> --%>

<div class="form-group">

	<label class="col-md-2 control-label"><b>Select Student * </b></label>
	<form action="par_student profile.web" id="demo" method="POST"
		name="vinform">
		<div class="col-md-5">
		<div class="input-group">
				<span class="input-group-addon"> <i class="fa fa-user"></i></span>
			<select id="parent" style="width: 40%" class="form-control"
				name="studentName">
				<c:forEach items="${mst}" var="s">
					<%-- <option><c:out value="${s.txtFirstName}">${s.txtFirstName} ${s.txtLastName }</c:out></option> --%>
					<option value="${s.txtFirstName}">${s.txtFirstName}  ${s.txtLastName}
				</c:forEach>
			</select></div> <span style="color: red; font-weight: bolder;"> <span
				class="field-validation-valid" id="validationMessages1"></span>
			</span>
		</div>
	</form>
	<br> <br>
</div>
<div id="studentData"></div>



<!-- END PAGE LEVEL SCRIPTS -->
<!-- END PAGE CONTENT-->
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		slider(201);
	});
</script>
<%@include file="footer.jsp"%>

