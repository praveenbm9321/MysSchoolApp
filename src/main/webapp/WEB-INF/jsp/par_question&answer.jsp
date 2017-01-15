<!-- BEGIN PAGE HEADER-->
<%@include file="headerForParent.jsp"%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(document).ready(function() {

		$('#form1').validate({ // initialize the plugin

			rules : {

				writequestion : {
					required : true,
				}

			},

			messages : {

				writequestion : {
					required : "please enter question"
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
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">Queries</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index_For_Parent.web">
					Parent</a> <i class="fa fa-angle-right"></i></li>

			<li>Queries</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
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
	<div class="col-md-12 blog-page">
		<div class="row">
			<div class="col-md-9 article-block">

				<div class="media">

					<%-- <h2>${msg }</h2> --%>
					<form action="par_question&answer.web" method="POST" id="form1">
						<div class="post-comment">

							<br /> <br /> <br />
							<div class="form-group">
								<label class="control-label col-md-3">Write Question
									Here <span class="required">*</span>
								</label>
								<div>
									<textarea class="col-md-6 form-control" rows="8"
										style="resize: none;" name="writequestion" id="question"
										placeholder="Enter Question Here"></textarea>
								</div>
								<span style="color: red; font-weight: bolder;"> <span
									class="field-validation-valid" id="validationMessages7"></span>
								</span>
							</div>
							<input type="submit" id="submit7" class="margin-top-20 btn blue"
								value="Send">
						</div>
					</form>
					<br /> <br />

					<table id="sample_editable_1">




						<thead>
							<th></th>
						</thead>

						<tbody>
							<c:forEach items="${mst}" var="cc">

								<tr>
									<!-- <th><a href="#" class="pull-left"> -->
									<td><b>Q. <%-- ${cc.intParentQueriesId}. --%>
											${cc.txtQuestion}?
									</b>
										<p>
											<b>Ans.</b> ${cc.txtAnswer}
										</p>
							</c:forEach>
							</td>
							</tr>
						</tbody>
					</table>

					<!-- </div></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div class="media">
												<a href="#" class="pull-left"> Ans. </a>
												<div class="media-body">

													
													
														<span> <a href="#"></a>
														</span>

													
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							
						
					</div>
					<hr>
				</div>
			</div>
		</div>
	</div> -->
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
<script type="text/javascript"
	src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="assets/plugins/data-tables/DT_bootstrap.js"></script>
<script src="assets/scripts/core/app.js"></script>
<script src="assets/scripts/custom/table-editable.js"></script>

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
		/* App.init();
		TableEditable.init(); */
		$('#sample_editable_1').DataTable({
			"bSort" : false
		});
		slider(213);
	});
</script>
<%@include file="footer.jsp"%>