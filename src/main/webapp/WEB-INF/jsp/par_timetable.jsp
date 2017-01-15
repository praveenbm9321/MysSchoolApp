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
	$(document).ready(function() {
		$('#parent').change(function() {
			request = new XMLHttpRequest();
			var v1 = document.vinform.studentName.value;

			var url = "ajax_par_timetable.web?val1=" + v1;
			
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
				document.getElementById('studentDataForTimeTable').innerHTML = val;
				$('#sample_editable_1').DataTable({
					"bSort" : false
				});
			}
		}
		$('#parent').trigger('change');
	});
</script>


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
			Time Table<small> of your child</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="ad_index_For_Parent.web"> Parent
			</a> <i class="fa fa-angle-right"></i></li>
			<li><!-- <a href="#">  -->Time Table<!--  </a> --></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
 
 <div class="form-group">

	<form action="par_timetable.web" method="POST" id="form1" name="vinform">
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
							<option value="${m.intRegistrationId}">${m.txtFirstName}  ${m.txtLastName}</option>
						</c:forEach>
					</select>
				</div>

				<!-- <span style="color: red; font-weight: bolder;"> <span
					class="field-validation-valid" id="validationMessages1"></span>
				</span> -->

			</div>
			<!-- <input type="submit" class="btn btn-primary">  --><br>
		</div>
	</form>

</div>
<br> 
<br>
 <br>
 
 <div id="studentDataForTimeTable"></div>
<!-- BEGIN BORDERED TABLE PORTLET-->
<%-- <c:if test="${! empty mst}">
<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">Time table</div>
	</div> --%>
	<%-- <div class="portlet-body">
		<div class="table-responsive">
		
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Time</th>
						<th>Mon</th>
						<th>Tue</th>
						<th>Wed</th>
						<th>Thu</th>
						<th>Fir</th>
						<th>Sat</th>
						<th>Sun</th>
					</tr>
				</thead>
				<tbody>
					<tr>

						<c:forEach items="${mst}" var="s">
							<c:if test="${s[2]=='Monday'}">
								<td>${s[0]}</td>
							</c:if>
							<td>${s[1]}</td>
							<c:if test="${s[2]=='Sunday'}">
								<tr>
							</c:if>

						</c:forEach>
					</tr>

				</tbody>
			</table>
			

		</div>
	</div> --%>
	
	
	
	
	
	
	
	
	
	<%-- <%-- <div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">Timetable</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
		<c:choose>
						<c:when test="${not empty mst}">
			<table class="table table-bordered table-hover">
				<thead>
					<tr style="background-color: #E5E4E2">
						<th>Time</th>
						<th>Mon</th>
						<th>Tue</th>
						<th>Wed</th>
						<th>Thu</th>
						<th>Fir</th>
						<th>Sat</th>
						<th>Sun</th>
					</tr>
				</thead>
				<tbody>
				
					<tr>

						<c:forEach items="${mst}" var="s">
							<c:if test="${s[2]=='Monday'}">
								<td>${s[0]}</td>
							</c:if>
							<td>${s[1]}</td>
							<c:if test="${s[2]=='Sunday'}">
								<tr>
							</c:if>

						</c:forEach>
					</tr>

				</tbody>
			</table>
			</c:when>
						<c:otherwise>
							<h3 style="text-align: center; font-weight: bold;"><i class="fa fa-warning" style="font-size: 25px;"></i> No record
								found</h3>
						</c:otherwise>
					</c:choose>

		</div>
	</div>

</div>
	
	 --%> 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
</div>
<%-- </c:if> --%>
<!-- END BORDERED TABLE PORTLET-->

<!-- END PAGE CONTENT-->
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		slider(203);
	});
</script>
<%@include file="footer.jsp"%>