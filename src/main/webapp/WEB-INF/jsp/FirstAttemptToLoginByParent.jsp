<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ include file="header.jsp"%> --%>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html> --%>
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
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>School Management System</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->






<script type="text/javascript">
	window.location.hash = "";
	window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
	window.onhashchange = function() {
		window.location.hash = "";
	}
</script>


<script type="text/javascript">
	function back_block() {
		window.history.foward(-1)
	}
</script> 





<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="assets/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN THEME STYLES -->
<link href="assets/css/style-metronic.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="assets/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="assets/css/themes/default.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->

<!-- multi select begin -->
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-select/bootstrap-select.min.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2-metronic.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/jquery-multi-select/css/multi-select.css" />
<!-- multi select end -->
<!-- edit begin -->
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-editable/bootstrap-editable/css/bootstrap-editable.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/bootstrap-editable/inputs-ext/address/address.css" />
<!-- edit end -->
<link rel="shortcut icon" href="favicon.ico" />
<!-- </head>

<!DOCTYPE html>
<html>
<head> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin
			rules : {
				txtOldPassword : {
					required : true

				},
				txtNewPassword : {
					required : true

				},
				txtConfirmPassword : {
					required : true
				}
			},

			messages : {
				txtOldPassword : {
					required : "Please enter old password"

				},
				txtNewPassword : {
					required : "Please enter new password"

				},
				txtConfirmPassword : {
					required : "Please enter confirm password"
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


</head>




<body class="page-header-fixed">
	<!-- BEGIN CONTENT -->

	<!-- <div class="page-content" style="margin-left: 5px; min-height: 450px;"> -->
		<!-- BEGIN PAGE HEADER-->
		
		<div class=" row">
		<div class="col-md-12">


			<div class="note note-info">
				Notes:Mandatory fields are marked with <span style="color: red">*</span>
			</div>
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption" style="padding-left: 420px">Change
						Password</div>
				</div>
				<div class="portlet-body form">
					<div class="form-horizontal">

						<form action="FirstAttemptToLoginByParent.web" method="post" id="form1">
							<c:if test="${!empty msg2 }">
								<div class="alert alert-success">
									<i class="fa fa-check-circle"></i> <strong>${msg2}</strong>
								</div>
							</c:if>
							
							<c:if test="${not empty msg3}">

	<div class="alert alert-warning ">
		
		<i class="fa fa-times"></i> <strong>${msg3}</strong>
	</div>
</c:if>
<p>This is your first attempt. Please change your password</p>
							<div class="form-body">
								<table class="table table-striped table-bordered table-hover">
									<tr>
										<th><b>Old Password<span style="color: red">*</span></b></th>
										<th><input class="form-control" name="txtOldPassword"
											style="width: 300px" type="password" value="" /></th>
									</tr>
									<tr>
										<th><b>New Password<span style="color: red">*</span></b></th>
										<th><input class="form-control" name="txtNewPassword"
											style="width: 300px" type="password" value="" /></th>
									</tr>
									<tr>
										<th><b>Confirm Password<span style="color: red">*</span></b></th>
										<th><input class="form-control" name="txtConfirmPassword"
											style="width: 300px" type="password" value="" /></th>
									</tr>
								</table>
								<br />
								<div class="col-md-offset-4 col-md-9">
									<input type="submit" class="btn green" />
								</div>
								<br /> <br />
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>

	<!-- END PAGE HEADER-->
	<!-- </div> -->
	<!-- </div> -->

	<script>
		jQuery(document).ready(function() {
			App.init();
		});
	</script>

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

	<%@include file="footer.jsp"%>

</body>
<!-- END BODY -->
</html>
