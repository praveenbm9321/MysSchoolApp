<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="header.jsp"%>

<script>

var msg;

var tno=0;
var temp=0;
var i=0;
function edit_row(no)
{
	
	temp=tno;
	tno=no;
 document.getElementById("edit"+no).style.display="none";
 document.getElementById("save"+no).style.display="block";
 document.getElementById("approve"+no).style.display="none";
 document.getElementById("reject"+no).style.display="none";
 document.getElementById("cancel"+no).style.display="block";

	if(i>0 && temp!=no){
    save_row(temp,0);
	       }
		i++;
 var Lmsg=document.getElementById("msg"+no);

	
 msg=Lmsg.innerHTML;

	
 Lmsg.innerHTML="<input class='form-control' type='text' id='msg_text"+no+"' value='"+msg+"' required='required'>";


}


function save_row(no,id)
{
	
	if(id!=0)
		{
 var Lmsg=document.getElementById("msg_text"+no).value;
 
 var result=updateInfo(Lmsg,id);
document.getElementById("msg"+no).innerHTML=Lmsg;
document.getElementById('smsg').innerHTML = '<div class="alert alert-success"><i class="fa fa-check-circle"></i><button type="button" class="close" data-dismiss="alert"aria-hidden="true"></button><b>success description updated</b></div>';
		}
	else
		{
		 document.getElementById("msg"+no).innerHTML=msg;
		
		}

	document.getElementById("edit"+no).style.display="block";
	 document.getElementById("save"+no).style.display="none";
	 document.getElementById("approve"+no).style.display="block";
	 document.getElementById("reject"+no).style.display="block";
	 document.getElementById("cancel"+no).style.display="none";
}


var request;
function updateInfo(message,id) {
	request = new XMLHttpRequest();
	var v1 = message;
	var v2= id;
	
	var url = "Ajax_TeacherLeaveUpdate.web?val1="+v1+"&val2="+v2;
	try {
		//request.onreadystatechange = getInfo;
		request.open("GET", url, true);
		request.send();
		return 0;
	} catch (e) {
		alert("Unable to connect to server");
	}
}



</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$(".btn.green.sv").hide();
						$(".btn.red.sv").hide();

						
						$('#leaveForm')
								.submit(
										function() {
											var error = 0;
											if (!($('.input-smalls.case.ss')
													.is(':checked'))) {
												error = 1
												//alert("Please check atleast one to do this Operation");
												$('#notSelected').addClass(
														"alert alert-success");
												$('#notSelected')
														.html(
																"Please select atleast one row");
												//$(".ss").prop('checked', true);
												setTimeout(
														function() {
															$('#notSelected')
																	.removeClass(
																			"alert alert-success");
															$('#notSelected')
																	.html("");
														}, 3000);
											}
											if (error) {
												return false;
											} else {
												return true;
											}
										});

						
						$('#sAll').change(
								function() {
									if (false == $(this).prop("checked")) {
										$(".input-smalls.case.ss").prop(
												'checked', false);
										$('.input-smalls.case.ss').parents(
												'span').removeClass("checked");
									} else {
										$(".input-smalls.case.ss").prop(
												'checked',
												$(this).prop("checked"));
										$('.input-smalls.case.ss').parents(
												'span').addClass("checked");
									}

									//alert($('.ss').val());
									//$(".ss")[0].checked = true;
								});

						
						$('.input-smalls.case.ss')
								.change(
										function() {
											//uncheck "select all", if one of the listed checkbox item is unchecked
											if (false == $(this)
													.prop("checked")) { //if this item is unchecked
												$("#sAll").prop('checked',
														false); //change "select all" checked status to false
												$('#sAll').parents('span')
														.removeClass("checked");
											}
											//check "select all" if all checkbox items are checked
											if ($('.input-smalls.case.ss:checked').length == $('.input-smalls.case.ss').length) {
												$("#sAll")
														.prop('checked', true);
												$('#sAll').parents('span')
														.addClass("checked");
											}
										});

						

						if ("${statusForScndtab}" == "11") {
							$('#tab22').trigger('click');
						} else {
							$('#tab21').trigger('click');
						}
						//alert(${statusForScndtab});
					});
	$(document).ready(function() {
		$('#reqStatusForm').validate({ // initialize the plugin
			rules : {
				fromDate : {
					required : true
				},
				toDate : {
					required : true
				},
				status : {
					required : true
				}
			},

			messages : {
				fromDate : {
					required : "From date required"
				},
				toDate : {
					required : "To date required"
				},
				status : {
					required : "Status required"
				}
			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
	});
</script>
<style>
#reqStatusForm .error {
	color: red;
	font-size: 12px;
}
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Admin</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group"></li>
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Teacher Invitations Info</h5></li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption" style="padding-left: 420px">Teacher
					Invitations Management</div>
			</div>
			<div class="portlet-body">
				<ul class="nav nav-pills">
					<li class="active"><a href="#tab_2_1" id="tab21"
						data-toggle="tab"> Pending Request </a></li>
					<li><a href="#tab_2_2" id="tab22" data-toggle="tab">
							Request Status </a></li>

				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="tab_2_1">
						<div class="" id="notSelected"></div>
						<c:choose>
							<c:when test="${not empty msg1 }">
								<div class="alert alert-success">
									<b><i class="fa fa-check-circle"></i>${msg1}${msg2}</b>
								</div>

							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty msgReqList }">
										<div class="alert alert-success">
											<b><i class="fa fa-check-circle"></i>${msgReqList}</b>
										</div>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>

						<div id="smsg"></div>
						<div class="portlet-body">
							<div class="table-responsive">

								<%-- <form action="ad_teach_invites.web" name="leaveForm" method="get">  --%>
									<table class="table table-striped table-bordered table-hover"
										id="sample_editable_1">
										<thead>
											<tr style="background-color: #E5E4E2">
												<th>Invitation Date</th>
												<th>Class</th>
												<th>Section</th>
												<th>Student</th>
												<th>Description</th>
												<th>Teacher</th>
												<th colspan="3"><div align="center">Action</div></th>
											</tr>
										</thead>
										<tbody>
											<%
												int i = 0;
												int j = 0;
												int t = 0;
											%>
											<c:choose>
												<c:when test="${not empty teachInvList }">
													<c:forEach var="teaInv" items="${teachInvList}">
														<%
															t++;
														%>
														<tr class="odd gradeX" id="row<%=t%>">

															<td><fmt:formatDate value="${teaInv.dtInvitesDate }"
																	pattern="dd-MMM-yyyy" /></td>

															<td>${teaInv.mststudclass.txtClassName }</td>

															<td>${teaInv.mststudclasssection.txtSectionName }</td>

															<c:choose>
																<c:when
																	test="${not empty teaInv.mstregistration.txtFirstName }">
																	<td>${teaInv.mstregistration.txtFirstName }&nbsp;${teaInv.mstregistration.txtLastName }[${teaInv.mstregistration.intRegistrationId }]</td>
																</c:when>
																<c:otherwise>
																	<td>All Students</td>
																</c:otherwise>
															</c:choose>

															<td id="msg<%=t%>">${teaInv.txtMessage }</td>

															<c:forEach items="${regTeachList }" var="regObj"
																begin="<%=i %>" end="<%=j %>">
																<td>${regObj.txtFirstName }&nbsp;${regObj.txtLastName }[${regObj.intRegistrationId }]</td>
															</c:forEach>

															<td><button type="button" class="btn yellow"
																	id="edit<%=t%>" onclick="edit_row(<%=t%>)">
																	<i class="fa fa-edit"></i>Edit
																</button></td>

															<td><a href="ApproveOrReject/${teaInv.intteacherinviteId}/2.web"><button type="submit" class="btn green"
																	name="action" value="app" id="approve<%=t%>">
																	<i class="fa fa-check-square-o"></i>Approve
																</button></a>

																<button type="button" class="btn green sv"
																	id="save<%=t%>"
																	onclick="save_row(<%=t%>,${teaInv.intteacherinviteId})">
																	<i class="fa fa-check-square-o"></i>Save
																</button></td>


															<td><a href="ApproveOrReject/${teaInv.intteacherinviteId}/3.web"><button type="submit" class="btn red"
																	name="action" value="rej" id="reject<%=t%>">
																	<i class="fa fa-times"></i>Reject
																</button></a>

																<button type="button" class="btn red sv"
																	id="cancel<%=t%>" onclick="save_row(<%=t%>,0)">
																	<i class="fa fa-check-square-o"></i>Cancel
																</button></td>

															<!-- <td><button type="submit" class="btn green">Download</button>
													</td> -->
															<%
																i++;
																			j++;
															%>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="8">
															<div align="center">
																<h3>
																	<i class="fa fa-warning" style="font-size: 25px;"></i>
																	No Records Found
																</h3>
															</div>
														</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</tbody>
										<%
											i = 0;
											j = 0;
										%>
									</table>
								<%-- </form> --%>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="tab_2_2">
						<div class="portlet-body">
							<form action="ad_teach_invites_check.web" method="post"
								id="reqStatusForm" name="reqStatusForm">
								<table class="table table-striped table-bordered table-hover">
									<tr>
										<th><b>From Date</b><span class="required"
											style="color: red"> * </span></th>
										<th>

											<div class="input-group">
												<span class="input-group-addon"><i
													class="fa fa-calendar"></i> </span> <input placeholder="From"
													class="form-control date-picker" name="fromDate"
													type="text" value="" data-date="2012-09-26"
													data-date-format="yyyy-mm-dd" readonly="readonly" /><span
													style="color: red; font-weight: bolder;"> </span>
											</div>
										</th>
										<th><b>To Date</b><span class="required"
											style="color: red"> * </span></th>
										<th>
											<div class="input-group">
												<span class="input-group-addon"><i
													class="fa fa-calendar"></i> </span> <input placeholder="To"
													class="form-control date-picker" name="toDate" type="text"
													value="" data-date="2012-09-26"
													data-date-format="yyyy-mm-dd" readonly="readonly" /><span
													style="color: red; font-weight: bolder;"> </span>
											</div>
										</th>
										<th><b>Status</b><span class="required"
											style="color: red"> * </span></th>
										<th><select class="form-control" name="status">
												<option value="">Select Status</option>
												<option value="1">-- All --</option>
												<option value="2">Approved</option>
												<option value="3">Rejected</option>
										</select></th>
										<th><input type="hidden" value="11" name="secondTab">
											<button type="Submit" id="projsubmit2"
												class="btn yellow filter-submit margin-bottom"
												id="btnSearch">
												<i class="fa fa-search"></i> Search
											</button></th>
									</tr>
								</table>
							</form>
							<c:choose>
								<c:when test="${not empty msg11 }">
									<div class="alert alert-success">
										<b><i class="fa fa-check-circle"></i>${msg11}</b>
									</div>
								</c:when>
							</c:choose>
							<div class="table-responsive" id="sd">
								<c:choose>
									<c:when test="${not empty msg22}">
										<c:choose>
											<c:when test="${! empty teachInvList}">
												<table class="table table-bordered table-hover">
													<thead>
														<tr style="background-color: #E5E4E2">
															<th>Invitation Date</th>
															<th>Class</th>
															<th>Section</th>
															<th>Student</th>
															<th>Description</th>
															<th>Teacher</th>
															<th>Approved/Rejected Date</th>
															<th>Status</th>
														</tr>
													</thead>
													<tbody>
														<%
															int k = 0;
																			int l = 0;
														%>
														<c:forEach items="${teachInvListPost }" var="l">
															<tr>
																<td><fmt:formatDate value="${l.dtInvitesDate}"
																		pattern="dd-MMM-yyyy" /></td>

																<td>${l.mststudclass.txtClassName }</td>

																<td>${l.mststudclasssection.txtSectionName }</td>

																<c:choose>
																	<c:when
																		test="${not empty l.mstregistration.txtFirstName }">
																		<td>${l.mstregistration.txtFirstName }&nbsp;${l.mstregistration.txtLastName }[${l.mstregistration.intRegistrationId }]</td>
																	</c:when>
																	<c:otherwise>
																		<td>All Students</td>
																	</c:otherwise>
																</c:choose>

																<td>${l.txtMessage }</td>

																<c:forEach items="${teachList }" var="regObj"
																	begin="<%=k %>" end="<%=l %>">
																	<td>${regObj.txtFirstName }&nbsp;${regObj.txtLastName }[${regObj.intRegistrationId }]</td>
																</c:forEach>

																<td><fmt:formatDate value="${l.dtApprovedDate}"
																		pattern="dd-MMM-yyyy" /></td>

																<td><c:if test="${l.intStatus==2}">
																		<p class="label label-sm label-success"
																			style="font-weight: bolder;">Approved</p>
																	</c:if> <c:if test="${l.intStatus==3}">
																		<p class="label label-sm label-danger"
																			style="font-weight: bolder;">Rejected</p>
																	</c:if></td>
																<%
																	k++;
																						l++;
																%>
															</tr>
														</c:forEach>
													</tbody>
													<%
														k = 0;
																		l = 0;
													%>
												</table>
											</c:when>
											<c:otherwise>
												<div align="center">
													<h3>
														<i class="fa fa-warning" style="font-size: 25px;"></i> No
														Records Found
													</h3>
												</div>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${not empty teachInvListGet }">
												<table class="table table-bordered table-hover"
													id="sample_editable_2">
													<thead>
														<tr style="background-color: #E5E4E2">
															<th>Invitation Date</th>
															<th>Class</th>
															<th>Section</th>
															<th>Student</th>
															<th>Description</th>
															<th>Teacher</th>
															<th>Approved/Rejected Date</th>
															<th>Status</th>
														</tr>
													</thead>
													<tbody>
														<%
															int m = 0;
																			int n = 0;
														%>
														<c:forEach items="${teachInvListGet}" var="l">
															<tr>
																<td><fmt:formatDate value="${l.dtInvitesDate}"
																		pattern="dd-MMM-yyyy" /></td>

																<td>${l.mststudclass.txtClassName }</td>

																<td>${l.mststudclasssection.txtSectionName }</td>

																<c:choose>
																	<c:when
																		test="${not empty l.mstregistration.txtFirstName }">
																		<td>${l.mstregistration.txtFirstName }&nbsp;${l.mstregistration.txtLastName }[${l.mstregistration.intRegistrationId }]</td>
																	</c:when>
																	<c:otherwise>
																		<td>All Students</td>
																	</c:otherwise>
																</c:choose>

																<td>${l.txtMessage }</td>

																<c:forEach items="${teachListGet }" var="regObj"
																	begin="<%=m %>" end="<%=n %>">
																	<td>${regObj.txtFirstName }&nbsp;${regObj.txtLastName }[${regObj.intRegistrationId }]</td>
																</c:forEach>

																<td><fmt:formatDate value="${l.dtApprovedDate}"
																		pattern="dd-MMM-yyyy" /></td>

																<td><c:if test="${l.intStatus==2}">
																		<p class="label label-sm label-success"
																			style="font-weight: bolder;">Approved</p>
																	</c:if> <c:if test="${l.intStatus==3}">
																		<p class="label label-sm label-danger"
																			style="font-weight: bolder;">Rejected</p>
																	</c:if></td>
																<%
																	m++;
																						n++;
																%>
															</tr>
														</c:forEach>
													</tbody>
													<%
														m = 0;
																		n = 0;
													%>
												</table>
											</c:when>
											<c:otherwise>
												<div align="center">
													<h3>
														<i class="fa fa-warning" style="font-size: 25px;"></i> No
														Records Found
													</h3>
												</div>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END CONTENT -->
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

<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		$('#sample_editable_2').DataTable({
			"bSort" : false
		});

		slider(18);
	});
</script>
<%@ include file="footer.jsp"%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
