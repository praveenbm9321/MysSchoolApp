
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
		<form action="EnterNameNumber.web" method="post">
			<h3>Forgot Password ?</h3>
			
			<c:if test="${not empty msg1 }">
		<strong style="color: red;">${msg1 }</strong>
		
		</c:if>
		
		
			<p>Enter User Name, Mobile Number below to reset your
				password.</p>
			<div class="form-group">
				<div class="input-icon">
					<i class="fa fa-envelope"></i> <input
						class="form-control placeholder-no-fix" type="text"
						autocomplete="off" placeholder="User Name" name="userName" />
				</div>
			</div>
			<div class="form-group">
				<div class="input-icon">
					<i class="fa fa-mobile"></i> <input
						class="form-control placeholder-no-fix" type="text"
						autocomplete="off" placeholder="Mobile number" name="phno" />
				</div>
			</div>
				<div class="form-actions">
			<a href="login.web">	<button type="button" id="back-btn" class="btn">
					<i class="m-icon-swapleft"></i> Back
				</button></a>
				<button type="submit" class="btn green pull-right">
					Submit <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
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