  <!DOCTYPE html>

<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.1.1
Version: 2.0.2
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file = "headerForTeacher.jsp"%>
<script>
$(document).ready(function() {

	$('#searchForm').validate({ // initialize the plugin

		rules : {


			fromDate:
			{
			required:true,
			},

			toDate:{
				required:true,
				},
			
			

	
	},

		messages : {
		
			fromDate:
				{
				required : "please select from date"
				}	,	
				toDate:{
					required : "please select to date"
					}

		},

		highlight : function(element) {

			$(element).parent().addClass('error')

		}

	});
	

}); 
</script>



<style>
#form1 .error ,#searchForm .error{
color: red;
font-size: 13px;

}
</style> 

			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					Time Table
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li class="btn-group">
							
							
						</li>
						<li>
							<i class="fa fa-home"></i> 
							<a href="ad_index_For_Teacher.web">
								Teacher
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<h5>
								Time Table
							</h5>
							
						</li>
						
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
		
		<link href="http://chart.apis.google.com/chart?cht=p&chd=s:Uf9a&chs=200x100&chl=January|February|March|April"/>
		
					 <c:if test = "${not empty msg}">
			 
			 							<div class="alert alert-success ">
		
			 								<i class="fa fa-check-circle"></i>
								<strong>${msg}</strong> 
							</div>
							</c:if>
							<!-- BEGIN BORDERED TABLE PORTLET-->
							<div class="portlet box green">
								<div class="portlet-title">
									<div class="caption">
									Timetable
									</div>
								</div>
								<div class="portlet-body">
									<div class="table-responsive">
									<c:choose>
									<c:when test = "${! empty timetable}">
										<table id="sample_editable_1"
									class="table table-bordered table-hover">
										<thead>
										<tr style="background-color: #E5E4E2">
										
											<!-- <th>
												 S.No
											</th> -->
											<th>
												 Day
											</th>
											<th>
												 Class Timings
											</th>
											<th>
												 Class
											</th>
											<th>
												 Section
											</th>
											<th>
												 Subject
											</th>
											<th>
												 Check-In
											</th>
											
											
										</tr>
										</thead>
										<tbody>	
 											<c:forEach items = "${timetable}" var = "t">
											<tr>
												<!-- <td>1</td> -->
												<td>${t.tbldaysofweek.txtDay}</td>
												<td>${t.msttimings.txtClassTimings}</td>
												<td>${t.mststudclass.txtClassName}</td>
												<td>${t.mststudclasssection.txtSectionName}</td>
												<td>${t.mstsubject.txtSubjectName}</td>
												<td><a href = "teac_checkIn.web?id=${t.intTeacherTimeTableId}" class="btn green">Check-In</a>
											</tr>
										</c:forEach>
						
											
											
											
											
											
											
										</tbody>

											
										</table>
										</c:when>
										
										<c:otherwise>
										<h3 style="text-align: center; font-weight: bolder;"><i class="fa fa-warning" style="font-size: 25px;"></i> Timetable is not available !!</h3>
										</c:otherwise>
									</c:choose>
										
								</div>
							</div>
							
							</div>
					
							
							
							<c:if test="${not empty dateMsg}">

	<div class="alert alert-warning " style="background-color: #f7e989;color: #716252">
		
			<i class="fa fa-warning"></i>
		<strong>${dateMsg}</strong>
	</div>
</c:if>
				
							<!-- END BORDERED TABLE PORTLET-->
							<div class="portlet box green">
								<div class="portlet-title">
								<div class="caption">
									History
									</div>
								
								
								
							<form action="teachCheckInHistory.web" id = "searchForm">
								<!-- <label class="control-label col-md-2">From</label> -->
									<div class="col-md-4">
									<table class="table table-striped table-bordered table-hover">
									<tr>
									<td><span class="input-group-addon">
													 From
												</span></td>
									<td><div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
									<input name="fromDate" class="form-control form-control-inline input-medium date-picker" readonly="readonly" id = "date1">
									</div>
									<span
									style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages6"></span>
									</span>
									</td>
									<td><span class="input-group-addon">
													 to
												</span></td>
									<td><div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
									<input name="toDate" class="form-control form-control-inline input-medium date-picker" readonly="readonly" id = "date2">
									</div>
									<span
									style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages7"></span>
									</span>
									</td>
									<td><input type="submit" class="btn btn-primary" value="Search" ></td>
									</tr>
									<!-- id = "projsubmit2" -->
									</table>
																	
									</div>
								
								</form>
							</div>
						
								<div class="portlet-body">
									<div class="table-responsive">
									<c:if test="${not empty checkInHistory}">
									<c:choose>
									<c:when test="${not empty checkin}">
										<table id="sample_editable_2"
									class="table table-bordered table-hover">
										<thead>
										<tr style="background-color: #E5E4E2">
											<th>
												Date
											</th>
											<th>
												 Class Timings
											</th>
											<th>
												 Class
											</th>
											<th>
												 Section
											</th>
											<th>
												 Subject
											</th>
											<th>
												  Check-In Time
											</th>
											
											
										</tr>
										</thead>
										<tbody>		
										<c:forEach items = "${checkin}" var = "checkin">						
											<tr>
												<td><fmt:formatDate value="${checkin.dtCheckInDate}" pattern="dd-MMM-yyyy"/></td>
												<td>${checkin.mstteachertimetable.msttimings.txtClassTimings}</td>
												<td>${checkin.mstteachertimetable.mststudclass.txtClassName}</td>
												<td>${checkin.mstteachertimetable.mststudclasssection.txtSectionName}</td>
												<td>${checkin.mstteachertimetable.mstsubject.txtSubjectName}</td>
												<td>${checkin.dtCheckInTime}</td>
											
												
											</tr>
										</c:forEach>	
											
										</tbody>										
										</table>
										</c:when>
										
										<c:otherwise>
									<!-- 	<div class="alert alert-warning " style="background-color: #f7e989;color: #716252">
		
			
		<strong style="align:center"><h3><i class="fa fa-warning" style="font-size: 25px;"></i> No record found!!</h3></strong>
	</div> -->
										<h3 style="text-align:center;font-weight:bolder;"><i class="fa fa-warning" style="font-size: 25px;"></i> No record found</h3> 
										</c:otherwise>
										</c:choose>
										</c:if>										
									</div>
								</div>
								</div>
								
							
							<!-- END BORDERED TABLE PORTLET-->
					
			<!-- END PAGE CONTENT-->
	<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function() {    
	TableEditable.init();
 	$('#sample_editable_2').DataTable({
 		 "bDestroy":true,
		    "bSort":false
	});
	
	$('#sample_editable_1').DataTable({
		  
		 "bDestroy":true,
		    "bSort":false
		
	 
	});
	 
	   slider(106);
	});
</script>
<%@include file = "footer.jsp"%>