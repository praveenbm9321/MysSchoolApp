
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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="assets/plugins/select2/select2-metronic.css" />
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="assets/css/style-metronic.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="assets/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="assets/css/themes/default.css" rel="stylesheet"
	type="text/css" id="style_color" />
<link href="assets/css/pages/login.css" rel="stylesheet" type="text/css" />
<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>




<!-- BEGIN BODY -->
<body class="login">
	

	<!-- BEGIN LOGO -->
	<div class="logo">
		<a href="index.html"> <img src="assets/img/logo.png" alt="" />
		</a>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" action="OTPToEnter1.web"  method="post" id="form1">
			<h3 class="form-title">OTP Sent</h3>
			<h4>Your OTP has been sent to your Mobile Number.</h4>

			<%-- <c:if test="${not empty msg}">
				<strong style="color: blue;">${msg}</strong>
			</c:if>
			--%>
			<c:if test="${not empty msg}">
		
			<strong style="color: blue;">${msg}</strong>
	</c:if>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Enter OTP</label>
				<div class="input-icon">
					<i class="fa fa-user"></i> <input
						class="form-control placeholder-no-fix" type="text"
						autocomplete="off" placeholder="Enter OTP" name="otp" />
				</div>
			</div>
			
			<div class="form-actions">
			<a href="EnterNameNumber.web">	<button type="button" id="back-btn" class="btn">
					<i class="m-icon-swapleft"></i> Back
				</button></a>
				<button type="submit" class="btn green pull-right">
					Verify <i class="m-icon-swapright m-icon-white"></i>
				</button>
				
			</div>
			<div class="login-options">
				
			</div>
			<p>If you don't have receive your OTP within five minutes, click the Resend OTP button to resend OTP</p>
			<!-- <div class="form-actions">
				<button type="submit" class="btn green pull-right">
					Resend OTP <i class="m-icon-swapright m-icon-white"></i>
				</button> -->
			<a href="resendOTP.web?">Resend OTP</a>	
			</div>
		</form>
		<!-- END LOGIN FORM -->
		
				<!-- END REGISTRATION FORM -->
				
				
				
				
			<!-- <form class="login-form" action="OTPToEnter2.web"  >
			<div class="login-options">
				
			</div>
			<p>If you don't have receive your OTP within five minutes, click the Resend OTP button to resend OTP</p>
			<div class="form-actions">
				<button type="submit" class="btn green pull-right">
					Resend OTP <i class="m-icon-swapright m-icon-white"></i>
				</button>
				
			</div>
		</form> -->
	</div>
	
	<div class="copyright">2016 &copy; by SeekDigital.</div>
	<script src="assets/plugins/jquery-1.10.2.min.js"
		type="text/javascript"></script>
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
	<script
		src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="assets/plugins/select2/select2.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/scripts/core/app.js" type="text/javascript"></script>
	<script src="assets/scripts/custom/login.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			App.init();
			Login.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>