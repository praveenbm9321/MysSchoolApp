
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- BEGIN PAGE HEADER-->
<%@include file="headerForParent.jsp"%>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



<script>
	$(document)
			.ready(
					function() {
						$('#parent').change(function() {
							request = new XMLHttpRequest();
							var v1 = document.vinform.studentName.value;

							var url = "ajax_par_syllabus.web?val1=" + v1;
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
										.getElementById('studentsyllabus').innerHTML = val;
								$('#sample_editable_1').DataTable({
									"bSort" : false
								});
							}
							
						}
						$('#parent').trigger('change');
					});
</script>



<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Syllabus</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Student.web"> parent </a> <i
				class="fa fa-angle-right"></i></li>

			<li>Syllabus</li>



		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="form-group">

	<form action="par_syllabus.web" method="POST" id="form1" name="vinform">
		<div class="form-group">
			<label class="col-md-2 control-label"><b>Select Student <span
					class="required" style="color: red"> * </span>
			</b></label>
			<div class="col-md-5">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i></span>
					<select id="parent" style="width: 40%" class="form-control"
						name="studentName">
						<!-- <option value="">Select</option> -->
						<c:forEach items="${regal}" var="m">
							<option value="${m.intRegistrationId}">${m.txtFirstName}
								${m.txtLastName}</option>
						</c:forEach>
					</select>
				</div>

			</div>
		</div>
	</form>

</div>
<br>
<br>
<br>
<br>
<div id="studentsyllabus"></div>

<!-- 
	<script src="assets/plugins/jquery-1.10.2.min.js"
		type="text/javascript"></script>
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
	END CORE PLUGINS
	BEGIN PAGE LEVEL PLUGINS
	<script type="text/javascript"
		src="assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="assets/plugins/data-tables/DT_bootstrap.js"></script>
	END PAGE LEVEL PLUGINS
	BEGIN PAGE LEVEL SCRIPTS
	<script src="assets/scripts/core/app.js"></script>
	<script src="assets/scripts/custom/table-editable.js"></script>
	validation start 

	<script type="text/javascript"
		src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-validation/dist/jquery.validate.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-validation/dist/additional-methods.js"></script>

	validation end 
 -->

	<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			/* $('#sample_editable_1').DataTable({
				"bSort" : false
			}); */
			slider(212);
		});
	</script>
	<%@include file="footer.jsp"%>