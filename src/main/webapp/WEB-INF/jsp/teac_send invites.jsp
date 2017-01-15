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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="headerForTeacher.jsp" %>

<script>
	var request;
	function sendInfo() {
		request = new XMLHttpRequest();
		var v1 = document.vinform.classId.value;
		var v2 = document.vinform.sectionId.value;
		var url = "ajax_teacher.web?val1=" + v1 + "&val2=" + v2 ;
		try {
			request.onreadystatechange = getInfo;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getInfo() {
		if (request.readyState == 4) {
			var val = request.response;
			
			document.getElementById('teacher').innerHTML = val; 
			$('#select2_sample_modal_2').select2();
			
	
				
			
		}
	}
</script>
<script>

$(document).ready(function () {
	$(".chk").hide();
   $(".check").click(function () {
        if ($(this).is(":checked")) {
            $(".chk").show();
        } else {
            $(".chk").hide();
        } 



        
    });
}); 
$(document).ready(function() {

	$('#form1').validate({ // initialize the plugin

		rules : {
			classId:
			{
			required:true,
			},

			sectionId:{
				required:true,
				},
			

				txtMessage:
			{
			required:true,
			maxlength:200
			}
			
			

	
	},

		messages : {
			classId:
			{
			required : "please select class"
			}	,	
			sectionId:{
				required : "please select section"
				},
				txtMessage:
				{
				required : "please enter the message"
				}	

		},

		highlight : function(element) {

			$(element).parent().addClass('error')

		}

	});
	

}); 

$(document).ready(function() {

	$('#searchForm').validate({ // initialize the plugin

		rules : {

			fromDate : {
				required : true,
			},

			toDate : {
				required : true,
			},

		},

		messages : {

			fromDate : {
				required : "please select from date"
			},
			toDate : {
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
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					Send Invites
					</h3>
					<ul class="page-breadcrumb breadcrumb">
					
						<li>
							<i class="fa fa-home"></i>
							<a href="ad_index_For_Teacher.web">
								Teacher
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
						<!-- <i class="fa fa-upload"></i> -->
							<h5>
								Send Invites
							</h5>
							
						</li>
						
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			 <c:if test = "${not empty invite}">
			 
			 							<div class="alert alert-success ">
		
			 							<i class="fa fa-check-circle"></i>
								<strong>${invite}</strong>
							</div>
							</c:if>
							
				<c:if test="${not empty Delmsg}">
	<div class="alert alert-success ">
		<i class="fa fa-check-circle"></i>
		<strong>${Delmsg} ${Delmsg1}</strong>
	</div>
</c:if>

			 <div class="alert alert-info">
								<strong>Note:</strong> 
								All (*) marked fields are mandatory.
							</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					
							
							
							<div class="portlet box green">
								<div class="portlet-title" >
									<div class="caption">
									Invite
									</div>
								</div>
								<div class="portlet-body">
								
								
								
								<div class="table-responsive">
								<form:form action="teac_InvitePost.web" commandName="mstteacherinvite" id = "form1" name = "vinform">
								<table class="table table-bordered table-hover">
								<tbody>
								
								<tr>
										<td style = "font-weight:bold">Class
										<span class="required" style ="color:red">
											 *
										</span></td>
										
										<td><%-- <form:select path="mststudclass.intClassId" class="form-control" id = "class">
										<form:option value="-1" label="--Select Class--"/>
										<form:options items = "${classes}" itemValue="intClassId" itemLabel="txtClassName"/>
										</form:select> --%>
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span>
										<select name="classId" class="form-control" style="width:300px;" >
										<option value="">Select class</option>
														<c:forEach var="classes" items="${classes}">
															<option value="${classes.intClassId}">${classes.txtClassName}</option>
														</c:forEach>
										
										</select></div>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages1"></span>
										</span>
										</td>
										
										<td style = "font-weight:bold">Section
										<span class="required" style ="color:red">
											 *
										</span></td>
										<td><%-- <form:select path="mststudclasssection.intSectionId" class="form-control" id = "section">
										<form:option value="-1" label="--Select Section--"/>
										<form:options items = "${sections}" itemValue="intSectionId" itemLabel="txtSectionName"/>
										</form:select> --%>
										<div class="input-group">
											<span class="input-group-addon"> <i
												class="fa fa-th-large"></i>
											</span>
										<select name="sectionId" class="form-control"  style="width:300px;" onchange="sendInfo()">
										<option value="">Select Section</option>
														<c:forEach var="sections" items="${sections}">
															<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
														</c:forEach>
										
										</select></div>
										<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages2"></span>
										</span>
										</td>
										</tr>
										
								
								<tr>
									
									
									<td style = "font-weight:bold">Message<span class="required" style ="color:red">
											 *
										</span></td>
									<td><textarea class="form-control" placeholder = "Enter the message" name = "txtMessage" id = "message" style = "width:400px;resize:none;"></textarea>
									<span style="color: red; font-weight: bolder;"> <span
											class="field-validation-valid" id="validationMessages3"></span>
										</span>
									</td>
								
								
								<td style="font-weight: bold"><input
											type="checkbox" class = "check">Invite To</td>
									<td style = "width: 438px;">
										<!-- id="select2_sample_modal_2"  class="form-control" multiple-->
										<div class = "chk">
										<%-- <div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-user"></i>
												</span> 
  									 <select class="form-control select2" id="select2_sample_modal_2" name = "select" style = "width:300px;"
  									 placeholder = "Select Student" multiple>

								
 
 											<c:forEach items = "${students}" var = "s">
										<option value="${s.intRegistrationId}">${s.txtFirstName}&nbsp;${s.txtLastName}</option>
										
										</c:forEach>

									</select></div> --%>
									
									<div id = "teacher"></div>
									</div>
									</td></tr>
									
									<tr>
									<td colspan = "4" align = "center"><input type="submit" value = "Send" class="btn green" ></td><!-- id = "invite" -->
									</tr>
								</tbody>
								
								</table>
								</form:form>
								</div>
								
								
									
								</div>
							</div>
		
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	
	
	<c:if test="${not empty dateMsg}">

			<div class="alert alert-warning " style="background-color: #f7e989;color: #716252">
		
			<i class="fa fa-warning"></i>
		<strong>${dateMsg}</strong>
	</div>
		</c:if>
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">Invite's Inbox</div>
					<!-- </div> -->
					
					<form action="teachInviteHistory.web" id="searchForm">
					<div class="col-md-4">
						<!-- 	<label class="control-label col-md-2">From</label> -->
						<table class="table table-striped table-bordered table-hover">
							<tr>
								<td><span class="input-group-addon"> From </span></td>
								<td><div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span> <input name="fromDate"
										class="form-control form-control-inline input-medium date-picker" placeholder="From"
										readonly="readonly" id="date1">
								</div> <span style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages6"></span>
								</span></td>

								<td><span class="input-group-addon"> to </span></td>
								<td><div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span> <input name="toDate"
										class="form-control form-control-inline input-medium date-picker" placeholder="To"
										readonly="readonly" id="date2"></div> <span
									style="color: red; font-weight: bolder;"> <span
										class="field-validation-valid" id="validationMessages7"></span>
								</span></td>
								<td><input type="submit" class="btn btn-primary"
									value="Search"></td>
							</tr>
							<!-- id="submit2" -->

						</table>

					</div>

				</form>
			</div>
					<div class="portlet-body">
						<div class="table-responsive">
							<%
								int i = 1;
							%>
							<%-- <c:if test="${not empty invites }"> --%>
							<!-- <table class="table table-striped table-bordered table-hover" id="sample_1"> -->
							<c:choose>
										<c:when test="${not empty invites }">
							<table id="sample_editable_1"
									class="table table-bordered table-hover">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th>Sl.No</th>
										<th>Date</th>
										<th>Class</th>
										<th>Section</th>
										<th>Description</th>
										<th>Invited</th>
										<th>Status</th>
										<th>Approved/Rejected Date</th>
										<th>Action</th>
										
									</tr>
								</thead>
								<tbody>
									
											<c:forEach var="i" items="${invites}">
												<tr>

													<td><%=i%></td>
													<td><fmt:formatDate value="${i.dtInvitesDate}" pattern="dd-MMM-yyyy"/></td>
													<td>${i.mststudclass.intClassId}</td>
													<%-- <td>${i.mststudclasssection.intSectionId}</td> --%>
												<td>${i.mststudclasssection.txtSectionName}</td>
													<td>${i.txtMessage}</td>
													<td>
													<c:choose>
													<c:when test="${ empty i.mstregistration.intRegistrationId }">
													All parents
													</c:when>
													<c:otherwise>
													Invited&nbsp;${i.mstregistration.txtFirstName}&nbsp;${i.mstregistration.txtLastName}&nbsp;parent's&nbsp;
													
													</c:otherwise>
													</c:choose>
													</td>
													<td>
											<c:if test = "${i.intStatus==1}">
										<p class="label label-sm label-warning"
												style="font-weight: bolder;">Pending</p>
										</c:if>		
										<c:if test = "${i.intStatus==2}">
										<p class="label label-sm label-success" style = "font-weight: bolder;">Approved</p>
										</c:if>
										<c:if test = "${i.intStatus==3}">
										<p class="label label-sm label-danger" style = "font-weight: bolder;">Rejected</p>
										</c:if>
										
										</td>
										<td>
										<c:choose>
										<c:when test="${empty i.dtApprovedDate }"><p align="center">- - - - -</p></c:when>
										<c:otherwise><p align="center"><fmt:formatDate value="${i.dtApprovedDate}" pattern="dd-MMM-yyyy"/></p></c:otherwise>
										</c:choose></td>	
													
													<td><div class="btn-group btn-group-solid">
																	<a href="deleteTeacherInvite/${i.intteacherinviteId}.web">
																		<button type="button" class="btn red">
																			<i class="fa  fa-trash-o"> </i> Delete
																		</button>
																	</a>
																</div></td>
														
												</tr>


												<%
													i++;
												%>
											</c:forEach>
										
								</tbody>
							</table>
							</c:when>
										<c:otherwise>
											<!-- <tr>
												<td> -->
													<div align="center">
														<h2><i class="fa fa-warning" style="font-size: 25px;"></i> No Records Found</h2>
													</div>
												<!-- </td>
											</tr> -->
										</c:otherwise>
									</c:choose>
<%-- </c:if> --%>

							<!-- END FORM-->

						</div>
					</div>
				</div>
				
				
<!-- <script src="assets/plugins/jquery-1.10.2.min.js"
				type="text/javascript"></script>
			<script src="assets/plugins/jquery-migrate-1.2.1.min.js"
				type="text/javascript"></script>
			<script src="assets/plugins/bootstrap/js/bootstrap.min.js"
				type="text/javascript"></script>
			<script
				src="assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
				type="text/javascript"></script>
			<script
				src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
				type="text/javascript"></script>
			<script src="assets/plugins/jquery.blockui.min.js"
				type="text/javascript"></script>
			<script src="assets/plugins/jquery.cokie.min.js"
				type="text/javascript"></script>
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

 -->



				
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function() {    
	 //App.init();
	   TableEditable.init(); 
	 $('#sample_editable_1').DataTable({
		 "bDestroy":true,
		    "bSort":false,
	    //aaData: response.data
	});
	   
	   slider(107);
	   
	  
	});
</script>

<!-- <script>
jQuery(document).ready(function() {       
   
});
</script> -->
	<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file = "footer.jsp"%>