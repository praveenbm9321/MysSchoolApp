<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- BEGIN PAGE HEADER-->
<%@include file="headerForStudent.jsp"%>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



<script>
	$(document)
			.ready(
					function() {


						request = new XMLHttpRequest();
						//var v1 = document.vinform.studentName.value;

						var url = "ajax_stu_task_by_default.web";
						try {
							request.onreadystatechange = getInfo;
							request.open("GET", url, true);
							request.send();
						} catch (e) {
							alert("Unable to connect to server");
						}
						
						$('#submit6').click(function() {
							request = new XMLHttpRequest();
							var v1 = document.vinform.assignDate.value;
							if(v1 != ""){
								var url = "ajax_stu_task.web?val1=" + v1;
								try {
									request.onreadystatechange = getInfo;
									request.open("GET", url, true);
									request.send();
								} catch (e) {
									alert("Unable to connect to server");
								}
								}
						});

						function getInfo() {
							if (request.readyState == 4) {
								var val = request.responseText;
								document
										.getElementById('stask').innerHTML = val;
								$('#sample_editable_1').DataTable({"bSort" : false});
							}
						}
						
					});
</script>




<script>
	$(document)
			.ready(
					
					function() {

						
						 request2 = new XMLHttpRequest();
							//var v1 = document.vinform.studentName.value;

							var url2 = "ajax_stu_project_by_default.web";
							try {
								request2.onreadystatechange = getInfo2;
								request2.open("GET", url2, true);
								request2.send();
							} catch (e) {
								alert("Unable to connect to server");
							}
						  
						
						$('#stusubmit').click(function() {
							request2 = new XMLHttpRequest();
							var v2 = document.vinformm.assignDatee.value;
							
							if(v2 != ""){
								var url2 = "ajax_stu_project.web?val1=" + v2;
								try {
									request2.onreadystatechange = getInfo2;
									request2.open("GET", url2, true);
									request2.send();
								} catch (e) {
									alert("Unable to connect to server");
								}
								}
						});

						function getInfo2() {
							if (request2.readyState == 4) {
								var val = request2.responseText;
								document
										.getElementById('stask1').innerHTML = val;
								$('#sample_editable_2').DataTable({"bSort" : false});
							}
						}
						
					});
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Homework</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Student.web"> Student </a> <i
				class="fa fa-angle-right"></i></li>

			<li>Homework</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->


<c:if test="${!empty msg1}">
	<c:if test="${empty trnBetweenAttendance }">
		<div class="alert alert-info">
			<strong>${msg1}&nbsp;&nbsp;&nbsp;Alert! No Record Found</strong>
		</div>
	</c:if>
</c:if>

<div class="row">
	<div class="col-md-12">

		<div class="col-md-2"></div>
		<div class="col-md-4"></div>

		<div class="col-md-6">
			<form action="stu_task.web" method="post" id ="enter" name = "vinform">
				<div class="form-group">
					<label class="control-label col-md-3">Select Date</label>
					<div class="col-md-4">
						<div class="input-group input-medium date-picker input-daterange"
							data-date-format="yyyy-mm-dd"
							readonly="readonly"">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-calendar"></i>
								</span> <input type="text"
									class="form-control form-control-inline input-medium date-picker" placeholder="Select Date"
									data-date-format="yyyy-mm-dd" readonly="readonly"
									name="assignDate" id="date3" style="Z-index:0">
									
							</div>
							<span style="color: red; font-weight: bolder;"> <span
								class="field-validation-valid" id="validationMessages5"></span>
							</span>
						</div>
					</div>

					<div class="col-md-2" style="margin-left: 91px;">
						<input type="button" value="Search" class="btn btn-primary"
							id="submit6" name = "searchButton" >
					</div>
				</div>


			</form>
		</div>
	</div>
	<br />
	
<div id = "studentDataForHomework">
<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">Homework</div>
	</div>
	<div class="tools">
		<a href="javascript:;" class="collapse"> </a> <a
			href="#portlet-config" data-toggle="modal" class="config"> </a> <a
			href="javascript:;" class="reload"> </a> <a href="javascript:;"
			class="remove"> </a>
	</div>
	<div class="portlet-body">
		<div class="table-responsive" id="stask">
		<!-- <div align="center">
								<h3>
												<i class="fa fa-warning" style="font-size: 25px;"></i> No
												Records Found
											</h3>
							</div> -->
		</div>
	</div>
</div>

</div>	
	

	<c:if test="${!empty msg2}">
		<c:if test="${empty trnBetweenAttendance }">
			<div class="alert alert-info">
				<strong>${msg2}&nbsp;&nbsp;&nbsp;Alert! No Record Found</strong>
			</div>
		</c:if>
	</c:if>

	<div class="col-md-2"></div>
	<div class="col-md-4"></div>
	<div class="col-md-6">
		<form action="stu_project.web" method="post" name = "vinformm">
			<div class="form-group">
				<label class="control-label col-md-3">Select Date</label>
				<div class="col-md-4">

					<div class="input-group input-medium date-picker input-daterange"
						data-date="2012-09-26" data-date-format="yyyy-mm-dd"
						readonly="readonly">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-calendar"></i>
							</span> <input type="text" id="date1" style="Z-index:0"
								class="form-control form-control-inline input-medium date-picker" placeholder="Select Date"
								data-date-format="yyyy-mm-dd" readonly="readonly"
								name="assignDatee">
						</div>
						<span style="color: red; font-weight: bolder;"> <span
							class="field-validation-valid" id="validationMessages1"></span>
						</span>
					</div>
				</div>
				<div class="col-md-2" style="margin-left: 91px;">
					<input type="button" value="Search" class="btn btn-primary"
						id="stusubmit">
				</div>
			</div>
		</form>
	</div>
	
	<div id = "studentDataForProject">
	
	<div class="portlet box green">
		<div class="portlet-title">
			<div class="caption">Assignments/Projects</div>
		</div>
		<div class="portlet-body">
			<div class="table-responsive" id="stask1">
		<!-- <div align="center">
								<h3>
						<i class="fa fa-warning" style="font-size: 25px;"></i> No Records
						Found
					</h3>
							</div> -->
		</div>
			

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
		
		/* $('#sample_editable_2').DataTable(); */
		slider(303);
	});
</script>
<!-- END PAGE CONTENT-->
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp"%>