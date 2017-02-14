<!-- BEGIN PAGE HEADER-->


<%@page import="java.util.Iterator"%>
<%@include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.io.*,java.sql.*"%>


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript">

function delete_row(no, id)
{
	if(confirm("are you sure you want to delete"))
		{
	var result=deleteInfo(id);		                        //ajax call
 document.getElementById("row"+no+"").outerHTML="";
 document.getElementById('delmsg').innerHTML = '<div class="alert alert-success"><i class="fa fa-check-circle"></i><button type="button" class="close" data-dismiss="alert"aria-hidden="true"></button><b>success!! school entry deleted</b></div>';
 
		}
	
}

function deleteInfo(id) {
	request = new XMLHttpRequest();
	var v1 = id;
	var url = "Ajax_UserstaffDelete.web?val1="+ v1;
	try {
		request.onreadystatechange = getdelInfo;
		request.open("GET", url, true);
		request.send();
		return 0;
	} catch (e) {
		alert("Unable to connect to server");
	}
}


function getdelInfo() {
	if (request.readyState == 4) {
		//var val = request.responseText;
		//document.getElementById('delmsg').innerHTML = val;
	}
}



</script>


<script>
	var request;
	function sendInfo() {
		request = new XMLHttpRequest();
		var v1 = document.vinform.ClassId.value;
		var v2 = document.vinform.SectionId.value;
		var v3 = document.vinform.categoryId.value;
		var url = "Ajax_userstaff.web?val1=" + v1 + "&val2=" + v2 + "&val3="
				+ v3;
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
			var val = request.responseText;
			document.getElementById('amit').innerHTML = val;
		}
	}
</script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#Mcategory").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "1") {
					// $("#first").not(this).hide();
					$("#Mclass").show();
					$("#Msection").show();
				}

				else {
					$("#Mclass").hide();
					$("#Msection").hide();
				}
			});
		}).change();

		$("#cls").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "") {
					$("#sec").attr("disabled", true);
				} else {
					$("#sec").attr("disabled", false);
				}
			});
		}).change();

	});
</script>


<script type="text/javascript">
	$(document).ready(function() {

		$('.btn.green.sv').hide();
		$('.btn.red.sv').hide();

		$('#form1').validate({

			// initialize the plugin
			rules : {
				categoryId : {
					required : true

				},
				SectionId : {

					required : true
				},
				ClassId : {

					required : true
				},
				StudentId : {
					alphanumeric : true
				}

			},

			messages : {
				categoryId : {
					required : " please select category"

				},
				SectionId : {
					required : " please select section"

				},
				ClassId : {
					required : " please select class"

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
		<h3 class="page-title">Staff/Student Master Record</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li>
				<h5>Staff/Student Master Record</h5>
			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->






<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>

<c:choose>
	<c:when test="${not empty msg3 }">
		<div class="alert alert-success">
			<b><i class="fa fa-check-circle"></i>${msg3}</b>
		</div>
	</c:when>
</c:choose>

<div id="delmsg"></div>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->


		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->

		<div class="tab-content">
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-search"></i> Staff/Student Master Record
						</div>

					</div>
					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="user_staff.web" class="form-horizontal"
							method="post" id="form1" name="vinform">


							<div class="form-group">
								<br> <label class="col-md-3 control-label"><b>Category</b>
									<span class="required"> * </span> </label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa  fa-list-alt"></i>
										</span> <select
											class="table-group-action-input form-control input-inline input-small input-sm"
											name="categoryId" id="Mcategory" onchange="sendInfo()">
											<option value="">Select</option>
											<c:forEach var="c" items="${category }">
												<option value="${c.intCategoryId1 }">${c.txtCategoryName }</option>
											</c:forEach>
										</select>

									</div>
								</div>
							</div>

							<div class="form-group" id="Mclass">
								<label class="col-md-3 control-label"><b>Class</b> <span
									class="required"> * </span> </label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"> <i class="fa fa-user"></i>
										</span> <select
											class="table-group-action-input form-control input-inline input-small input-sm"
											name="ClassId" id="cls">
											<option value="">Select</option>
											<c:forEach var="c" items="${classes }" begin="0" end="11">
												<option value="${c.intClassId }">${c.txtClassName }</option>
											</c:forEach>

										</select>

									</div>
								</div>
							</div>

							<div class="form-group" id="Msection">
								<label class="col-md-3 control-label"><b>Section</b> <span
									class="required"> * </span> </label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"> <i class="fa fa-user"></i>
										</span> <select
											class="table-group-action-input form-control input-inline input-small input-sm"
											name="SectionId" onchange="sendInfo()" id="sec">
											<option value="">Select</option>
											<c:forEach var="s" items="${sections }" begin="0" end="3">
												<option value="${s.intSectionId }">${s.txtSectionName }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

							<div id="amit"></div>

							<div class="clearfix" align="center">
								<button type="submit" class="btn green">Search</button>
							</div>
						</form>
						<br>
					</div>
				</div>



				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

				<!-- BEGIN CONDENSED TABLE PORTLET-->

				<c:if test="${not empty performance}">
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet box green">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-list"></i>Student Master Record
									</div>
									<div class="tools">
										<a href="javascript:;" class="collapse"> </a> <a
											href="javascript:;" class="reload"> </a>


									</div>
								</div>
								<div class="portlet-body">
									<div class="table-scrollable">

										<div class="btn-group pull-right">
											<ul class="dropdown-menu pull-right">
												<li><a href="#"> Print </a></li>
												<li><a href="#"> Save as PDF </a></li>
												<li><a href="#"> Export to Excel </a></li>
											</ul>
										</div>
									</div>

									<c:choose>

										<c:when test="${not empty Mstal }">
											<table class="table table-striped table-hover table-bordered"
												id="sample_editable_1">
												<thead>
													<tr style="background-color: #E5E4E2;">


														<th>Student ID</th>
														<th>First Name</th>
														<th>Last Name</th>
														<th>Class</th>
														<th>Section</th>
														<th>Father's Name</th>
														<th>Mobile No.</th>
														<th>DOB</th>
														<th>Gender</th>
														<!-- <th>Address</th> -->
														<!-- <th>City</th> -->
														<th colspan="2"><div align="center">Action</div></th>

													</tr>
												</thead>
												<tbody>

													<%
														int i = 0;
													%>

													<c:forEach var="mst" items="${ Mstal}">
														<form action="updateStudent/${mst.intRegistrationId}.web"
															id="form2">
															<%
																i++;
															%>

															<tr id="row<%=i%>">

																<td>${mst.intRegistrationId}</td>
																<td>${mst.txtFirstName }</td>
																<td>${mst.txtLastName }</td>
																<c:forEach var="c" items="${classes }">
																	<c:if
																		test="${ c.intClassId == mst.mststudclass.intClassId}">
																		<td>${c.txtClassName }</td>
																	</c:if>
																</c:forEach>

																<c:forEach var="s" items="${sections }">
																	<c:if
																		test="${ s.intSectionId  == mst.mststudclasssection.intSectionId}">
																		<td>${s.txtSectionName }</td>
																	</c:if>
																</c:forEach>

																<td>${mst.txtFatherName }</td>
																<td>${mst.txtFmobileNumber }</td>
																<td>${mst.dtDob}</td>

																<c:forEach var="g" items="${gal }">
																	<c:if
																		test="${ g.intGenderId  == mst.mstgender.intGenderId}">
																		<td>${g.txtGenderName }</td>
																	</c:if>
																</c:forEach>

																<%-- <td>${mst.txtPerAddress}</td> --%>

																<%-- <td><input type="text" class="form-control"
																	value="${mst.txtCity}" required="required"></td> --%>

																<td><div class="btn-group btn-group-solid">

																		<button class="btn green" type="submit">
																			edit <i class="fa fa-edit"></i>
																		</button>

																	</div></td>
														</form>

														<td><button class="btn red"
																onclick="delete_row(<%=i%>,${mst.intRegistrationId})"
																id="delete<%=i%>">
																<i class="fa fa-trash-o"></i> Delete
															</button></td>

														</tr>

													</c:forEach>
												</tbody>
											</table>
										</c:when>


										<c:otherwise>


											<div align="center">
												<h1>No Records Found!!!</h1>
											</div>


										</c:otherwise>
									</c:choose>


								</div>
							</div>
							<!-- END EXAMPLE TABLE PORTLET-->
						</div>
					</div>
				</c:if>




				<!-- END CONDENSED TABLE PORTLET-->

				<c:if test="${not empty performance1}">
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet box green">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-list"></i>Staff(Teaching/Non-Teaching) Master
										Record
									</div>
									<!-- <div class="tools">
										<a href="javascript:;" class="collapse"> </a> <a
											href="javascript:;" class="reload"> </a>


									</div> -->
								</div>
								<div class="portlet-body">
									<div class="table-scrollable">

										<!-- <div class="btn-group pull-right">

											<ul class="dropdown-menu pull-right">
												<li><a href="#"> Print </a></li>
												<li><a href="#"> Save as PDF </a></li>
												<li><a href="#"> Export to Excel </a></li>
											</ul>
										</div> -->
									</div>

									<c:choose>

										<c:when test="${not empty Mstal }">
											<table class="table table-striped table-hover table-bordered"
												id="sample_editable_2">
												<thead>
													<tr style="background-color: #E5E4E2;">
														<th>Staff ID</th>
														<th>First Name</th>
														<th>Last Name</th>
														<th>Father's Name</th>
														<th>Mobile No.</th>
														<th>Gender</th>
														<th>Joining Date</th>
														<th>Address</th>
														<!-- <th>City</th> -->
														<th colspan="2"><div align="center">Action</div></th>

													</tr>
												</thead>
												<tbody>

													<%
														int i = 0;
													%>

													<c:forEach var="mst" items="${ Mstal}">
														<form action="updateStaff/${mst.intRegistrationId}.web"
															id="form2">
															<%
																i++;
															%>

															<tr id="row<%=i%>">
																<td>${mst.intRegistrationId}</td>
																<td>${mst.txtFirstName }</td>
																<td>${mst.txtLastName }</td>
																<td>${mst.txtFatherName }</td>
																<td>${mst.txtFmobileNumber }</td>
																<c:forEach var="g" items="${gal }">
																	<c:if
																		test="${ g.intGenderId  == mst.mstgender.intGenderId}">
																		<td>${g.txtGenderName }</td>
																	</c:if>
																</c:forEach>
																<td>${mst.dtJoiningDate}</td>
																<td>${mst.txtPerAddress}</td>
																<td><div class="btn-group btn-group-solid">
																		<button class="btn green" type="submit">
																			edit <i class="fa fa-edit"></i>
																		</button>

																	</div></td>
														</form>

														<td><button class="btn red"
																onclick="delete_row(<%=i%>,${mst.intRegistrationId})"
																id="delete<%=i%>">
																<i class="fa fa-trash-o"></i> Delete
															</button></td>

														</tr>
													</c:forEach>
												</tbody>
											</table>
										</c:when>
										<c:otherwise>
											<div align="center">
												<h1>No Records Found!!!</h1>
											</div>


										</c:otherwise>
									</c:choose>

								</div>
							</div>
							<!-- END EXAMPLE TABLE PORTLET-->
						</div>
					</div>
				</c:if>



			</div>
		</div>
	</div>
	<!-- END PAGE CONTENT-->

	<!-- END PAGE CONTENT-->


	<!-- END CONTENT -->
</div>

<!-- END CONTAINER -->


<!-- BEGIN FOOTER -->
<div class="footer">
	<div class="footer-inner">2017 &copy; by Aikshika</div>
	<div class="footer-tools">
		<span class="go-top"> <i class="fa fa-angle-up"></i>
		</span>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script> 
<![endif]-->
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
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>

<script>
	jQuery(document).ready(function() {
		App.init();
		TableEditable.init();
		

		
		slider(3);
	});
</script>
</body>
<!-- END BODY -->
</html>

