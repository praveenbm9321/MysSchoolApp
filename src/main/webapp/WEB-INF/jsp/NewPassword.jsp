
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" class="no-js">
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
		<form class="login-form" action="NewPassword.web" method="post" id="form1">
			<h3 class="form-title">Confirmation</h3>

<c:if test="${not empty msg2 }">
		<strong style="color: red;">${msg2 }</strong>
		
		</c:if>

			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">New Password</label>
				<div class="input-icon">
					<i class="fa fa-user"></i> <input
						class="form-control placeholder-no-fix" type="password"
						autocomplete="off" placeholder="New Password" name="newPassword" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">Confirm Password</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i> <input
						class="form-control placeholder-no-fix" type="password"
						autocomplete="off" placeholder="Re-peat Password" name="confirmPassword" />
				</div>
			</div>
			<div class="form-actions">
				
				<button type="submit" class="btn green pull-right">
					Submit <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
			<div class="login-options"></div>
		</form>

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
	<script src="assets/scripts/core/app.js" type="text/javascript"></script>
	<script src="assets/scripts/custom/login.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			App.init();
			Login.init();
		});
	</script>
</body>
</html>