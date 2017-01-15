<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>


<script type="text/javascript">
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin
			rules : {
				answer : {
					required : true

				}
			},

			messages : {
				answer : {
					required : "answer field is mandatory"

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
	font-size: 12px;
}
</style>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Admin</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li>

				<h5>Queries</h5>

			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<div class="alert alert-info">
	<strong>Note:</strong>Maximum of 1000 characters are allowed.
</div>
<c:if test="${not empty msg}">

	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<i class="fa fa-check-circle"></i> <strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-question-circle"></i>Queries
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<%
						int i = 0;
					%>

					<table class="table table-bordered table-hover" id="form1">
						<tbody>
							<c:forEach items="${list}" var="q">
								<c:if test="${q.txtStatus==0 }">
									<%
										i++;
									%>
									<form action="ad_query/${q.intParentQueriesId }.web"
										method="post">
										<tr>
											<td><p>
													<b>Parent ID:</b> ${q.txtParentId}<br> <b>Question:</b>
													${q.txtQuestion}
												</p></td>
										</tr>
										<tr>
											<td><b>Answer:</b> <textarea class="form-control"
													style="resize: none;" name="answer" required="required"
													placeholder="write answer here"></textarea> <span
												style="color: red; font-weight: bolder;"> </span></td>
										</tr>
										<tr>
											<td align="center">
												<button type="submit" class="btn green">Send</button>
											</td>
										</tr>
									</form>
								</c:if>
							</c:forEach>
							<%
								if (i == 0) {
							%>
							<h3 style="text-align: center; font-weight: bolder;">No
								Queries</h3>
							<%
								}
							%>
						</tbody>
					</table>

				</div>
			</div>
		</div>
		<!-- END PAGE CONTENT-->
	</div>
</div>


<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-envelope"></i>Questions Inbox
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered"
					id="sample_editable_1">
					<thead>
						<tr>
							<th>Parent Id</th>
							<th>Question</th>
							<th>Answer</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="q" items="${list}">
							<c:if test="${q.txtStatus == 1}">
								<tr>
									<td>${q.txtParentId}</td>
									<td>${q.txtQuestion}</td>
									<td>${q.txtAnswer}</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
















<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2-metronic.css" />
<link rel="stylesheet"
	href="assets/plugins/data-tables/DT_bootstrap.css" />
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
<!-- END PAGE LEVEL STYLES -->

<script type="text/javascript">
	jQuery(document).ready(function() {
		slider(16);
		App.init();
		TableEditable.init();
	});
</script>
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp"%>