
<!-- BEGIN PAGE HEADER-->
<%@include file="headerForStudent.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#qaForm').validate({ // initialize the plugin
			rules : {
				mstSubject : {
					required : true
				},
				writequestion : {
					required : true
				}
			},

			messages : {
				mstSubject : {
					required : "Select Subject"
				},
				writequestion : {
					required : "Please write a question"
				}
			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
	});
</script>
<style>
#qaForm .error {
	color: red;
	font-size: 12px;
}
</style>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Question & Answer</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Student.web"> Student </a> <i
				class="fa fa-angle-right"></i></li>

			<li>
				<!-- <a href="#">  -->Question & Answer<!--  </a> -->
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<c:if test="${not empty msg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<div class="row">
	<div class="col-md-12 blog-page">
		<div class="row">
			<div class="col-md-9 article-block">

				<div class="media">


					<form action="stu_question & answer.web" method="POST" id="qaForm">
						<div class="post-comment">
							<!-- <table class="table table-striped table-bordered table-hover" id="sample_1"> -->
							<div class="form-group">
								<label class="col-md-3 control-label">Subject Name <span
									class="required"> * </span></label>
								<div class="col-md-9">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-list-alt"></i>
										</span> <select
											class="table-group-action-input form-control input-inline input-small input-sm"
											name="mstSubject" required="required">
											<option value="">Select</option>
											<c:forEach items="${mst}" var="m">
												<option value="${m.intSubjectId}">${m.txtSubjectName}</option>
											</c:forEach>
										</select>
									</div>
									<!-- <input type="text" class="form-control" placeholder=""> -->
								</div>
							</div>


							<br /> <br /> <br />
							<div class="form-group">
								<label class="control-label col-md-3">Write Question
									Here:<span class=required>*</span>
								</label>
								<!-- <div class="col-md-4"> -->
								<!-- <div class="input-group"> -->
								<div>
									<textarea class="col-md-6 form-control" rows="8"
										style="resize: none;" name="writequestion"
										placeholder="Enter Question Here"></textarea>
								</div>
								<!-- </div> -->
								<!-- </div> -->
							</div>
							<div class="form-group">
								<input type="submit" class="margin-top-20 btn blue" value="Send">
							</div>

						</div>
					</form>
					<br /> <br />

					<div class="row">
						<div class="col-md-6">
							<h4><u><b>Unanswered Questions</b></u></h4>
							<table id="sample_editable_1">
								
								<thead>
									<th></th>
								</thead>

								<tbody>

									<c:forEach items="${q}" var="cc">
										<c:if test="${empty cc.txtAnswer}">
											<tr>
												<td><b>Q${cc.intQuestionAndAnswerId}.
												${cc.txtQuestion}?</b>
													<p>
														<b>Student</b> :${cc.mstregistration.txtFirstName}
														${cc.mstregistration.txtLastName}
													</p>
													<p>
														<b>Ans.</b> ${cc.txtAnswer}
													</p> <b>Teacher</b> : <c:forEach items="${teachNamesList}"
														var="teach">

														<c:if test="${teach.intRegistrationId == cc.intTeacherId}">
													
														${teach.txtFirstName} ${teach.txtLastName}
													
												</c:if>
													</c:forEach> </td>
											</tr>
											<%-- <%i++ ;%> --%>
											<%-- <%=i %>
 --%>
										</c:if>
									
									</c:forEach>


									<%-- 	</c:forEach> --%>
								</tbody>

							</table>
							
						</div>

						<div class="col-md-6">
							<h4><u><b>Answered Questions</b></u></h4>
							<table id="sample_editable_2">
								
								<thead>
									<th></th>
								</thead>

								<tbody>

									<c:forEach items="${q}" var="cc">
										<c:if test="${not empty cc.txtAnswer}">
											<tr>
												<td><b>Q${cc.intQuestionAndAnswerId}.
												${cc.txtQuestion}?</b>
													<p>
														<b>Student</b> :${cc.mstregistration.txtFirstName}
														${cc.mstregistration.txtLastName}
													</p>
													<p>
														<b>Ans.</b> ${cc.txtAnswer}


													</p> <b>Teacher</b> : <c:forEach items="${teachNamesList}"
														var="teach">

														<c:if test="${teach.intRegistrationId == cc.intTeacherId}">
													
														${teach.txtFirstName} ${teach.txtLastName}
													
												</c:if>

													</c:forEach> </td>
											</tr>
											<%-- <%i++ ;%> --%>
											<%-- <%=i %>
 --%>
										</c:if>

									</c:forEach>



									<%-- 	</c:forEach> --%>
								</tbody>

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT-->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->


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

<!-- validation end  -->


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<!-- <script type="text/javascript">
jQuery(document).ready(function() {    
	$('#sample_editable_1').DataTable({
		"bSort" : false
	});
	   slider(305);
	});
</script> -->

<script type="text/javascript">
	jQuery(document).ready(function() {
		/* App.init();
		TableEditable.init(); */
		$('#sample_editable_1').DataTable({
			"aaSorting" : [ [ 0, 'desc' ] ]
		});

		$('#sample_editable_2').DataTable({
			"aaSorting" : [ [ 0, 'desc' ] ]
		});
		slider(305);
	});
</script>
<%-- <%@include file="footer.jsp"%> --%>

<%-- 
<!-- BEGIN PAGE HEADER-->
<%@include file="headerForStudent.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#qaForm').validate({ // initialize the plugin
			rules : {
				mstSubject : {
					required : true
				},
				writequestion : {
					required : true
				}
			},

			messages : {
				mstSubject : {
					required : "Select Subject!!!"
				},
				writequestion : {
					required : "Please write a question!!!"
				}
			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
	});
</script>
<style>
#qaForm .error {
	color: red;
	font-size: 12px;
}
</style>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Question & Answer</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index_For_Student.web"> Student
			</a> <i class="fa fa-angle-right"></i></li>

			<li><!-- <a href="#">  -->Question & Answer<!--  </a> --></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<c:if test="${not empty msg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="stu_question & answer.web" method="POST" id="qaForm">
	<div class="row">
		<div class="col-md-12 blog-page">
			<div class="row">
				<div class="col-md-9 article-block">

					<div class="media">
					
						<h2>${msg }</h2>
						<form action="stu_question & answer.web" method="POST" id="qaForm">
						<div class="post-comment">
<!-- <table class="table table-striped table-bordered table-hover" id="sample_1"> -->
							<div class="form-group">
								<label class="col-md-3 control-label">Subject Name <span
									class="required"> * </span></label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"> <i class="fa fa-user"></i>
										</span> <select
											class="table-group-action-input form-control input-inline input-small input-sm"
											name="mstSubject" required="required">
											<option value="">Select</option>
											<c:forEach items="${mst}" var="m">
												<option value="${m.intSubjectId}">${m.txtSubjectName}</option>
											</c:forEach>
										</select>
									</div>
									<!-- <input type="text" class="form-control" placeholder=""> -->
								</div>
							</div>
 

							<br /> <br /> <br />
							<div class="form-group">
								<label class="control-label col-md-3">Write Question
									Here:<span class=required>*</span>
								</label>
								<!-- <div class="col-md-4"> -->
								<!-- <div class="input-group"> -->
								<div>
									<textarea class="col-md-6 form-control" rows="8"
										name="writequestion"></textarea>
								</div>
								<!-- </div> -->
								<!-- </div> -->
							</div>
							<div class="form-group">
								<input type="submit" class="margin-top-20 btn blue" value="Send">
							</div>
							
						</div>
					</form>						<br /> <br />

						<table id="sample_editable_1">
						<tbody>
							<%
							int i = 0;
							int j = 0;
								%>
							<c:forEach items="${q}" var="cc">
								<thead>
									<tr>
										<th > 
												<p style="color: #2a6496;float: left;">Q${cc.intQuestionAndAnswerId}.</p><!--  </a> -->
											<div class="media-body">

												<p>${cc.txtQuestion}?</p>
                                               ${cc.mstregistration.txtFirstName}
											</div></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="media">
												<!-- <a href="#" class="pull-left"> -->
												<p style="color: #2a6496;float: left;"> Ans.</p><!-- </a> -->
												<div class="media-body">

													<p>${cc.txtAnswer}</p>
													</c:forEach>
														</c:if>

													<c:forEach items="${teacherName}" var = "t" begin="${i}" end="${i}">
													
													<c:forEach items="${teachNamesList}" var="teach"
														begin="<%=i %>" end="<%=j %>">
														<span><!--  <a href="#"> -->${teach}<!-- </a> -->
														</span>

													</c:forEach>
													<%
							                        i++;
										            j++;
								                    %>
													<%i++ ;%>
														<%=i %>
													</c:forEach
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							</c:forEach>
                              <%
								i = 0;
								j = 0;
							  %>
							  </tbody>
						</table>
					</div>
					<hr>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- END PAGE CONTENT-->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->


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

<!-- validation end  -->

	
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function() {    
	$('#sample_editable_1').DataTable();
	   slider(305);
	});
</script> --%>
<%-- <%@include file="footer.jsp"%> --%>



<%-- <!-- BEGIN PAGE HEADER-->
<%@include file="headerForStudent.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
div.container {
	width: 100%;
	border: 1px solid gray;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$('#qaForm').validate({ // initialize the plugin
			rules : {
				mstSubject : {
					required : true
				},
				writequestion : {
					required : true
				}
			},

			messages : {
				mstSubject : {
					required : "Select Subject"
				},
				writequestion : {
					required : "Please write a question"
				}
			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
	});
</script>
<style>
#qaForm .error {
	color: red;
	font-size: 12px;
}
</style>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Question & Answer</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a
				href="ad_index_For_Student.web"> Student </a> <i
				class="fa fa-angle-right"></i></li>

			<li>
				<!-- <a href="#">  -->Question & Answer<!--  </a> -->
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<c:if test="${not empty msg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<div class="row">
	<div class="col-md-12 blog-page">
		<div class="row">
			<div class="col-md-9 article-block">

				<div class="media">


					<form action="stu_question & answer.web" method="POST" id="qaForm">
						<div class="post-comment">
							<!-- <table class="table table-striped table-bordered table-hover" id="sample_1"> -->
							<div class="form-group">
								<label class="col-md-3 control-label">Subject Name <span
									class="required"> * </span></label>
								<div class="col-md-9">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="fa fa-list-alt"></i>
										</span> <select
											class="table-group-action-input form-control input-inline input-small input-sm"
											name="mstSubject" required="required">
											<option value="">Select</option>
											<c:forEach items="${mst}" var="m">
												<option value="${m.intSubjectId}">${m.txtSubjectName}</option>
											</c:forEach>
										</select>
									</div>
									<!-- <input type="text" class="form-control" placeholder=""> -->
								</div>
							</div>


							<br /> <br /> <br />
							<div class="form-group">
								<label class="control-label col-md-3">Write Question
									Here:<span class=required>*</span>
								</label>
								<!-- <div class="col-md-4"> -->
								<!-- <div class="input-group"> -->
								<div>
									<textarea class="col-md-6 form-control" rows="8"
										style="resize: none;" name="writequestion"
										placeholder="Enter Question Here"></textarea>
								</div>
								<!-- </div> -->
								<!-- </div> -->
							</div>
							<div class="form-group">
								<input type="submit" class="margin-top-20 btn blue" value="Send">
							</div>

						</div>
					</form>
					<br /> <br />

					<table id="sample_editable_1">
							<%
							int i = 0;
							int j = 0;
								%>
							<thead>
							<th></th>
							</thead>
								
								<tbody>
								<c:forEach items="${q}" var="cc">
									<tr>
									<td><b>Q${cc.intQuestionAndAnswerId}. 
									${cc.txtQuestion}?</b>
									<p><b>Student</b> :${cc.mstregistration.txtFirstName} ${cc.mstregistration.txtLastName}</p>
									<p><b>Ans.</b> ${cc.txtAnswer}</p>
									<c:forEach items="${teachNamesList}" var="teach"
														begin="<%=i %>" end="<%=j %>">
														<p><b>Teacher</b> :${teach}</p>

													</c:forEach>
										
													<%
							                        i++;
										            j++;
								                    %>
													<%i++ ;%>
														<%=i %>
													</c:forEach>
												</td>
									</tr>
									</c:forEach>
								</tbody>
							
                              <%
								i = 0;
								j = 0;
							  %>
						</table>





					<div class="container">
						<table id="sample_editable_1">

							<%
								int i = 0;
								int j = 0;
							%>
							<thead>
								<th></th>
							</thead>
							<tbody>
								<c:forEach items="${q}" var="cc">

									<tr>
										<c:choose>
											<c:when test="${not empty cc.txtAnswer}">
												<td><b>Q${cc.intQuestionAndAnswerId}.

														${cc.txtQuestion}?</b>
													<p>
														<b>Student</b> :${cc.mstregistration.txtFirstName}
														${cc.mstregistration.txtLastName}
													</p>
													<p>
														<b>Ans.</b> ${cc.txtAnswer}
													</p> <c:forEach items="${teachNamesList}" var="teach"
														begin="<%=i %>" end="<%=j %>">
														<p>
															<b>Teacher</b> :${teach}
														</p>
													</c:forEach> <%
 	i++;
 				j++;
 %></td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when test="${ empty cc.txtAnswer}">
												<td><b>Q${cc.intQuestionAndAnswerId}.

														${cc.txtQuestion}?</b>
													<p>
														<b>Student</b> :${cc.mstregistration.txtFirstName}
														${cc.mstregistration.txtLastName}
													</p>
													<p>
														<b>Ans.</b> ${cc.txtAnswer}
													</p> <c:forEach items="${teachNamesList}" var="teach"
														begin="<%=i %>" end="<%=j %>">
														<p>
															<b>Teacher</b> :${teach}
														</p>
													</c:forEach> <%
 	i++;
 				j++;
 %></td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>

							</tbody>
							<%
								i = 0;
								j = 0;
							%>

						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT-->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->


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

<!-- validation end  -->


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<!-- <script type="text/javascript">
jQuery(document).ready(function() {    
	$('#sample_editable_1').DataTable({
		"bSort" : false
	});
	   slider(305);
	});
</script> -->

<script type="text/javascript">
	jQuery(document).ready(function() {
		/* App.init();
		TableEditable.init(); */
		$('#sample_editable_1').DataTable({
			"aaSorting" : [ [ 0, 'desc' ] ]
		});
		slider(305);
	});
</script>
<%@include file="footer.jsp"%>


<!-- BEGIN PAGE HEADER-->
<%@include file="headerForStudent.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#qaForm').validate({ // initialize the plugin
			rules : {
				mstSubject : {
					required : true
				},
				writequestion : {
					required : true
				}
			},

			messages : {
				mstSubject : {
					required : "Select Subject!!!"
				},
				writequestion : {
					required : "Please write a question!!!"
				}
			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}
		});
	});
</script>
<style>
#qaForm .error {
	color: red;
	font-size: 12px;
}
</style>


<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Question & Answer</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index_For_Student.web"> Student
			</a> <i class="fa fa-angle-right"></i></li>

			<li><!-- <a href="#">  -->Question & Answer<!--  </a> --></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<c:if test="${not empty msg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<strong>${msg}</strong>
	</div>
</c:if>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="stu_question & answer.web" method="POST" id="qaForm">
	<div class="row">
		<div class="col-md-12 blog-page">
			<div class="row">
				<div class="col-md-9 article-block">

					<div class="media">
					
						<h2>${msg }</h2>
						<form action="stu_question & answer.web" method="POST" id="qaForm">
						<div class="post-comment">
<!-- <table class="table table-striped table-bordered table-hover" id="sample_1"> -->
							<div class="form-group">
								<label class="col-md-3 control-label">Subject Name <span
									class="required"> * </span></label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"> <i class="fa fa-user"></i>
										</span> <select
											class="table-group-action-input form-control input-inline input-small input-sm"
											name="mstSubject" required="required">
											<option value="">Select</option>
											<c:forEach items="${mst}" var="m">
												<option value="${m.intSubjectId}">${m.txtSubjectName}</option>
											</c:forEach>
										</select>
									</div>
									<!-- <input type="text" class="form-control" placeholder=""> -->
								</div>
							</div>
 

							<br /> <br /> <br />
							<div class="form-group">
								<label class="control-label col-md-3">Write Question
									Here:<span class=required>*</span>
								</label>
								<!-- <div class="col-md-4"> -->
								<!-- <div class="input-group"> -->
								<div>
									<textarea class="col-md-6 form-control" rows="8"
										name="writequestion"></textarea>
								</div>
								<!-- </div> -->
								<!-- </div> -->
							</div>
							<div class="form-group">
								<input type="submit" class="margin-top-20 btn blue" value="Send">
							</div>
							
						</div>
					</form>						<br /> <br />

						<table id="sample_editable_1">
						<tbody>
							<%
							int i = 0;
							int j = 0;
								%>
							<c:forEach items="${q}" var="cc">
								<thead>
									<tr>
										<th > 
												<p style="color: #2a6496;float: left;">Q${cc.intQuestionAndAnswerId}.</p><!--  </a> -->
											<div class="media-body">

												<p>${cc.txtQuestion}?</p>
                                               ${cc.mstregistration.txtFirstName}
											</div></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="media">
												<!-- <a href="#" class="pull-left"> -->
												<p style="color: #2a6496;float: left;"> Ans.</p><!-- </a> -->
												<div class="media-body">

													<p>${cc.txtAnswer}</p>
													</c:forEach>
														</c:if>

													<c:forEach items="${teacherName}" var = "t" begin="${i}" end="${i}">
													
													<c:forEach items="${teachNamesList}" var="teach"
														begin="<%=i %>" end="<%=j %>">
														<span><!--  <a href="#"> -->${teach}<!-- </a> -->
														</span>

													</c:forEach>
													<%
							                        i++;
										            j++;
								                    %>
													<%i++ ;%>
														<%=i %>
													</c:forEach
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							</c:forEach>
                              <%
								i = 0;
								j = 0;
							  %>
							  </tbody>
						</table>
					</div>
					<hr>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- END PAGE CONTENT-->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->


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

<!-- validation end  -->

	
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function() {    
	$('#sample_editable_1').DataTable();
	   slider(305);
	});
</script>
<%@include file="footer.jsp"%> --%>