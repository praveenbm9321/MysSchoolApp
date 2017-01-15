
<%@include file="headerForParent.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



<script>
	$(document).ready(function() {
		$('#parent').change(function() {
			request = new XMLHttpRequest();
			var v1 = document.vinform.studentName.value;

			var url = "ajax_par_homework.web?val1=" + v1;
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
				document.getElementById('studentDataForHomework').innerHTML = val;
				$('#sample_editable_1').DataTable({
					"bSort" : false
					});
			}
		}
		$('#parent').trigger('change');
	});
</script>


<!-- BEGIN PAGE HEADER-->
<script type="text/javascript">
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin
			rules : {
				studentName : {
					required : true

				}
			},

			messages : {
				studentName : {
					required : "Please select student"

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
		<h3 class="page-title">
			Homework <small>(and Project)</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index_For_Parent.web"> Parent
			</a> <i class="fa fa-angle-right"></i></li>
			<li>
				<!-- <a href="#">  -->Homework <!-- </a> -->
			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<c:if test="${!empty msg}">
	<c:if test="${empty homework}">
		<div class="alert alert-info">
			<strong>No Record Found!!!</strong>
		</div>
	</c:if>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="form-group">
	<form action="par_homework.web" method="POST" id="form1" name="vinform">
		<div class="form-group">
			<label class="col-md-2 control-label"><b>Select Student <span
										class="required" style="color: red"> * </span>
			</b></label>
			<div class="col-md-5">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i></span>
					<select  id="parent" style="width: 40%" class="form-control" 
			name="studentName">
						<!-- <option value="">Select</option> -->
						<c:forEach items="${regal}" var="m">
							<%-- <option value="${m.intRegistrationId}">${m.txtStudentFirstName}</option> --%>
							<option value="${m.txtFirstName}">${m.txtFirstName}  ${m.txtLastName}</option>
						</c:forEach>
					</select>
				</div>

				<span style="color: red; font-weight: bolder;"> <span
					class="field-validation-valid" id="validationMessages1"></span>
				</span>

			</div>
			<!-- <input type="submit" class="btn btn-primary"> --> <br><br><br>
		</div>
		<!-- <label>Or Select Date </label>&nbsp; 
		
		
		<!-- <input type="text" name="date"class="form-control form-control-inline input-medium date-picker"
			data-date="2012-10-11" data-date-format="yyyy-mm-dd" size="16"
			type="date" value="">&nbsp; <input type="submit"
			value="Submit" class="btn btn-primary" id="parSubmit"> --> 
			
	</form>
	<br> <br> <br>
</div>





<div id = "studentDataForHomework"></div>








<%-- 
<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Today's Homework</div>
			</div>
		</div>
		<!-- <h2></h2> -->
			<c:choose>
						<c:when test="${not empty homework}">
		<table id="sample_editable_1"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th style="width: 15%">Subject</th>
					<th style="width: 50%">Homework</th>
					<th style="width: 50%">Submission Date</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${homework}" var="t">
					<tr>
						<td>${t.txtSubject}</td>
						<td>${t.txtHomeWork}</td>
						<td>${t.dtDueDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:when>
						<c:otherwise>
							<h3 style="font-weight: bold;">No record found!!!</h3>
						</c:otherwise>
					</c:choose>

		<div style="text-align: center">
			<button type="button"
				onclick="window.location.href='par_projects.web'" type="button"
				class="btn btn-primary">View Projects</button>
		</div>
	</div>
</div>



 --%>


<!-- ============================ -->


<!--=================  -->


<!-- BEGIN FOOTER -->
<div class="footer">
	<div class="footer-inner">2014 &copy; by SeekDigital</div>
	<div class="footer-tools">
		<span class="go-top"> <i class="fa fa-angle-up"></i>
		</span>
	</div>
</div>
<!-- END FOOTER
BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time)
BEGIN CORE PLUGINS
[if lt IE 9]> -->
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script> 
<!-- <![endif] -->
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
<!-- END CORE PLUGINS
BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript"
	src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/DT_bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS
BEGIN PAGE LEVEL SCRIPTS -->
<script src="assets/scripts/core/app.js"></script>
<script src="assets/scripts/custom/table-editable.js"></script>






<!-- validation start  -->

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

<!-- validation end  -->
<script>
	jQuery(document).ready(function() {
		 App.init();
		TableEditable.init(); 

		slider(205);
	});
</script>
<!-- </body>
END BODY
</html> -->
<%-- <%@include file="footer.jsp"%>   --%>