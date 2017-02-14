<%@page import="org.hibernate.type.AdaptedImmutableType"%>
<%@include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script>
	window.onload = function() {
		$("#charts1").CanvasJSChart({
			title : {
				text : "Class 1",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p1[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p1[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts2").CanvasJSChart({
			title : {
				text : "Class 2",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p2[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p2[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts3").CanvasJSChart({
			title : {
				text : "Class 3",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p3[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p3[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts4").CanvasJSChart({
			title : {
				text : "Class 4",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p4[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p4[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts5").CanvasJSChart({
			title : {
				text : "Class 5",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p5[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p5[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts6").CanvasJSChart({
			title : {
				text : "Class 6",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p6[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p6[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts7").CanvasJSChart({
			title : {
				text : "Class 7",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p7[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p7[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts8").CanvasJSChart({
			title : {
				text : "Class 8",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p8[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p8[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts9").CanvasJSChart({
			title : {
				text : "Class 9",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p9[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p9[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts10").CanvasJSChart({
			title : {
				text : "Class 10",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p10[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p10[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts11").CanvasJSChart({
			title : {
				text : "Class 11",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p11[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p11[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

		$("#charts12").CanvasJSChart({
			title : {
				text : "Class 12",
				fontSize : 15
			},

			legend : {
				verticalAlign : "center",
				horizontalAlign : "right"
			},
			data : [ {
				type : "pie",
				showInLegend : false,
				toolTipContent : "{label} <br/> {y} %",
				indexLabel : "{y} %",
				dataPoints : [ {
					label : "Present",
					y : "${p12[0]}",
					legendText : "Present"
				}, {
					label : "Absent",
					y : "${p12[1]}",
					legendText : "Absent"
				} ]
			} ]
		});

	}

	var request;
	function sendInfo() {
		request = new XMLHttpRequest();
		var v1 = document.vinform.ClassId.value;
		var v2 = document.vinform.SectionId.value;

		var url = "Ajax_userstaff.web?val1=" + v1 + "&val2=" + v2;
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

		$('#attendForm').validate({ // initialize the plugin
			rules : {
				ClassId : {
					required : true
				},
				SectionId : {
					required : true
				}
			},

			messages : {
				ClassId : {
					required : "Select the Class"
				},
				SectionId : {
					required : "Select the Section"
				}
			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}

		});

	});
</script>

<style>
#attendForm .error {
	color: red;
	font-size: 12px;
}
</style>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Attendance</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Attendance Info</h5></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->


<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">


			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>Attendance Track for student
				</div>
			</div>

			<div class="portlet-body">

				<form action="ad_attendance.web" class="form-horizontal"
					method="post" id="attendForm" name="vinform">

					<div class="form-group">
						<label class="col-md-3 control-label"><b>Class</b> <span
							class="required"> * </span></label>
						<div class="col-md-4">
							<div class="input-group" style="width: 360px;">
								<span class="input-group-addon"> <i class="fa fa-user"></i>
								</span> <select
									class="table-group-action-input form-control input-inline input-small input-sm"
									name="ClassId" style="width: 360px;" onchange="sendInfo()">
									<option value="">Select</option>
									<c:forEach var="c" items="${classes }">
										<option value="${c.intClassId }">${c.txtClassName }</option>
									</c:forEach>

								</select>
								<!-- <input type="text" class="form-control" placeholder=""> -->
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label"><b>Section</b> <span
							class="required"> * </span></label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"> <i class="fa fa-user"></i>
								</span> <select
									class="table-group-action-input form-control input-inline input-small input-sm"
									name="SectionId" style="width: 360px;" onchange="sendInfo()">
									<option value="">Select</option>
									<c:forEach var="s" items="${sections }">
										<option value="${s.intSectionId }">${s.txtSectionName }</option>
									</c:forEach>

								</select>
							</div>
							<!-- <input type="text" class="form-control" placeholder=""> -->
						</div>
					</div>

					<div id="amit"></div>

					<!-- <div class="form-group">
						<label class="col-md-3 control-label"><b>Student ID</b></label>
						<div class="col-md-4">
							<div class="input-group" style="width: 320px;">
								<span class="input-group-addon"> <i class="fa fa-user"></i>
								</span> <input type="text" name="stuId" class="form-control"
									placeholder="enter id" style="width: 360px;">

							</div>
						</div>
					</div> -->

					<div class="portlet-title" style="padding: 2px 4px 4px 11px;"></div>
					<label class="col-md-3 control-label"><b>Select
							Date&nbsp&nbsp&nbsp&nbsp</b></label>
					<div class="input-group input-large date-picker input-daterange"
						data-date="2012-09-26" data-date-format="yyyy-mm-dd"
						style="width: 360px;">

						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-calendar"></i>
							</span> <input type="text" class="form-control" name="from"
								placeholder="From" readonly="readonly">
							<!-- </div> -->
							<!-- <span class="input-group-addon"> -->
							<!-- <i
								class="fa fa-angle-double-right"></i>  </span> -->
							<!-- <div class="input-group"> -->
							<span class="input-group-addon"><i class="fa fa-calendar"></i>
							</span> <input type="text" class="form-control" name="to"
								readonly="readonly" placeholder="To">
						</div>
					</div>

					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button type="Submit" class="btn green">Search</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>

</div>

<c:if test="${empty attendance }">
	<div class="row">
		<div class="col-md-4">
			<div id="charts1" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts2" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts3" style="width: 100%; height: 300px"></div>
		</div>
	</div>


	<div class="row">
		<div class="col-md-4">
			<div id="charts4" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts5" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts6" style="width: 100%; height: 300px"></div>
		</div>
	</div>


	<div class="row">
		<div class="col-md-4">
			<div id="charts7" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts8" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts9" style="width: 100%; height: 300px"></div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-4">
			<div id="charts10" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts11" style="width: 100%; height: 300px"></div>
		</div>

		<div class="col-md-4">
			<div id="charts12" style="width: 100%; height: 300px"></div>
		</div>
	</div>
</c:if>
<!-- 
pie chart
<div class="col-md-8">
	<div class="col-md-4">
		<img class="img-responsive" src="pieChart.web" />
	</div>
	<div class="col-md-4">
		<img class="img-responsive" src="pieChart.web" />
	</div>
</div> -->
<c:if test="${not empty attendance}">
	<div class="row">
		<div class="col-md-12">


			<!-- BEGIN BORDERED TABLE PORTLET-->
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption">Attendance Records</div>
				</div>
				<%!int k = 0;
	int l = 2;
	int e = 0;
	int f = 0;
	int p = 0;
	int q = 0;%>
				<div class="portlet-body">
					<div class="table-responsive">



						<table class="table table-bordered table-hover">
							<thead>
								<tr style="background-color: #E5E4E2">
									<th>Student Id</th>
									<th>Student Name [ID]</th>
									<th>Date</th>
									<th>In-Time</th>
									<th>Out-Time</th>
									<th>Total-Hours</th>
								</tr>
							</thead>
							<tbody>

								<c:choose>

									<c:when test="${not empty attndList }">
										<c:forEach var="ad" items="${attndList}">
											<tr>
												<c:forEach var="id" items="${stuIdList}" begin="<%= p %>"
													end="<%= q %>">

													<td>${id}</td>
												</c:forEach>
												<c:forEach var="id" items="${stuIdList}" begin="<%= p %>"
													end="<%= q %>">
													<c:forEach var="name" items="${stuNameList}"
														begin="<%= e %>" end="<%= f %>">
														<td>${name}[${id}]</td>
													</c:forEach>
												</c:forEach>
												<td><fmt:formatDate value="${ad.dtSlotDate}"
														pattern="dd-MMM-yyyy" /></td>

												<c:forEach var="out" items="${output}" begin="<%=k %>"
													end="<%=l %>">
													<td>${out}</td>
												</c:forEach>
											</tr>
											<%
												k += 3;
																l += 3;
											%>
											<%
												e += 1;
																f += 1;
																p += 1;
																q += 1;
											%>

										</c:forEach>
										<%
											k = 0;
														l = 2;
														e = 0;
														f = 0;
														p = 0;
														q = 0;
										%>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="6">
												<div align="center">
													<h1>No Records Found!!!</h1>
												</div>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>



<!-- END CONTENT -->


<!-- <div class="footer">
	<div class="footer-inner">2014 &copy; by SeekDigital</div>
	<div class="footer-tools">
		<span class="go-top"> <i class="fa fa-angle-up"></i>
		</span>
	</div>
</div> -->
<!-- 
END FOOTER
BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time)
BEGIN CORE PLUGINS
[if lt IE 9]> -->
<!-- <script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script> 
<![endif]
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


<script src="assets/chart/canvasjs.min.js" type="text/javascript"></script>
<script src="assets/chart/jquery.canvasjs.min.js" type="text/javascript"></script>
 -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		slider(5);
	});
</script>
<%@include file="footer.jsp"%>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
