<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="headerForStudent.jsp"%>
<!DOCTYPE html>
<html>
<head>
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
					required : "please enter old password"

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

						<form action="changePasswordForStudent.web" method="post"
							id="form1">
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
