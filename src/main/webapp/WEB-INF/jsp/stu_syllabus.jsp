
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>






<!-- BEGIN PAGE HEADER-->
<%@include file="headerForStudent.jsp"%>



<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Syllabus</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Student.web"> Student </a> <i
				class="fa fa-angle-right"></i></li>

			<li>Syllabus</li>
			
			
           <!--  <a href="#" id="notificationLink">Syllabus</a> -->
           
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Student Syllabus</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table  id="sample_editable_1"  class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #E5E4E2">
							    <th>S.No</th>
								<th>Subject Name</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
						<%int i=1; %>
							<c:forEach items="${mst}" var="m">
								<tr>
								<td><%=i %></td>
								<%i++; %>
									<td>${m.txtSubjectName}</td>
									
									<td>
												<a href="downloades/ ${m.intSubjectId}.web">
																		<button type="button" class="btn green">
																			<i class="fa fa-upload"></i> Download
																		</button>
																	</a>
												
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

</div>


<!-- <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
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
	src="assets/plugins/jquery-validation/dist/additional-methods.js"></script> -->

<!-- validation end  -->


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		
		slider(308);
		
		
	});
</script>
<%@include file="footer.jsp"%>