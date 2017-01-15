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
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="headerForTeacher.jsp"%>
<script>
	/* $(document).ready(function() {

		$('#form1').validate({ // initialize the plugin

			rules : {
				answer : {
					required : true,
				}

			},

			messages : {

				answer : {
					required : "please enter the answer"
				}
			},

			highlight : function(element) {

				$(element).parent().addClass('error')

			}

		});

	}); */

	$(document).ready(function() {
	 $('#form1 textarea[name^="answer"]').each(function() {
	    $(this).validate( {
	       // minlength: 2,
	        rules : {
				answer : {
					required : true,
				}
				},

		messages : {

			answer : {
				required : "please enter the answer"
			}
		},
		highlight : function(element) {

			$(element).parent().addClass('error')

		}

	    });

		
	        }); 

	        /* $('#form1 textarea[name^="answer"]').each(function() {
		     
	            $(this).rules("add", {
	            	 required: true
	              
	               
	            }

	            );

	            
	            
	        }); */
	    
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
		<h3 class="page-title">Question & Answer</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index_For_Teacher.web">
								Teacher
							</a><i class="fa fa-angle-right"></i></li>
			<li>

				<h5>Question & Answer</h5>

			</li>

		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<c:if test="${not empty msg}">

	<div class="alert alert-success ">
		
		<i class="fa fa-check-circle"></i> <strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">



		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Question Answer</div>
			</div>
			<div class="portlet-body">



				<div class="table-responsive">
								
						<table class="table table-bordered table-hover" >
						<thead></thead>
							<tbody>
								<c:if test="${! empty questions}">
									<c:forEach items="${questions}" var="q">
											<form action="teac_answer.web" class="form-horizontal" id = "form1" >

										<tr class="odd gradeX">
											<td><p>
													<b>Class</b> :
													${q.mstregistration.mststudclass.txtClassName}<br />
													<!-- <b>Section:A</b><br/> -->
													<b>StudentName:</b>${q.mstregistration.txtFirstName}&nbsp;${q.mstregistration.txtLastName}<br />
													<b>Question:</b>${q.txtQuestion}</p><!-- </td> -->
										<!-- </tr> -->
										
<!-- id="form1" -->
					
					<!-- <tr>
											<td> --><b>Answer</b> :<textarea class="form-control" rows = "2" required="required"
													name="answer" style = "resize:none;"  placeholder="Enter Answer Here" ></textarea> <span
												style="color: red;"> <span
													class="field-validation-valid" id="validationMessages1"></span>
											</span><!-- </td>
										</tr>
										<tr> -->
											<!-- <td align="center"> -->
											
											<input type="hidden" name="id"
												value="${q.intQuestionAndAnswerId}">
												<button type="submit" class="btn green">Send</button><!-- </td> -->
											<!-- id = "ansSubmit" -->
											
										</tr>
										<!--  -->
															</form>
									</c:forEach>
								</c:if>
								<c:if test="${empty questions}">
									<h3 style="text-align: center; font-weight: bolder;">
									<i class="fa fa-warning" style="font-size: 25px;"></i> No
										Questions are available to answer</h3>
								</c:if>


							</tbody>

						</table>
					
				</div>



			</div>
		</div>
		<!-- END PAGE CONTENT-->
		
		
		
		
		
		
		
		
				<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Answered Question</div>
			</div>
			<div class="portlet-body">



				<div class="table-responsive">
								
						<table class="table table-bordered table-hover" id="sample_editable_1">
						<thead></thead>
							<tbody>
								<c:if test="${! empty answeredQuestion}">
									<c:forEach items="${answeredQuestion}" var="q">
											

										<tr class="odd gradeX">
											<td><p>
													<b>Class</b> :
													${q.mstregistration.mststudclass.txtClassName}<br />
													<!-- <b>Section:A</b><br/> -->
													<b>StudentName:</b>${q.mstregistration.txtFirstName}&nbsp;${q.mstregistration.txtLastName}<br />
													<b>Question:</b>${q.txtQuestion}</p><!-- </td> -->
									<b>Answer</b> :<textarea class="form-control" rows = "2" 
													name="answer" style = "resize:none;" readonly="readonly">${q.txtAnswer}</textarea>
															
									</c:forEach>
								</c:if>
								<c:if test="${empty answeredQuestion}">
									<h3 style="text-align: center; font-weight: bolder;">
									<i class="fa fa-warning" style="font-size: 25px;"></i> No
										Questions are answered</h3>
								</c:if>


							</tbody>

						</table>
					
				</div>



			</div>
		</div>
		
		
		
		
	</div>
</div>

<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		TableEditable.init(); 
		/*  $('#sample_editable_1').DataTable({
			    "bSort":false,
		    //aaData: response.data
		}); */
		slider(103);
	});
</script>
<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp"%>