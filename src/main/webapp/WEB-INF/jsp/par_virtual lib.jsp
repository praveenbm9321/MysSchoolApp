<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

			<!-- BEGIN PAGE HEADER-->
			<%@include file="headerForParent.jsp"%>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					Virtual Library <small></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						
						<li>
							<i class="fa fa-home"></i>
							<a href="ad_index_For_Parent.web"> Parent
							</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
								Virtual Library
							
						</li>
						
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<%-- <div class="form-group">
				 <label  class="col-md-2 control-label"><b>Select Student: </b></label>
				         <div class="col-md-5">
									<select style="width: 40%" class="form-control" name="mstregistration">
										<option value="">Select</option>
												<c:forEach items="${regal}" var="m">
													<option value="${m.intRegistrationId}">${m.txtFirstName}</option>
												</c:forEach>
									</select>
						</div>
		   </div> --%>
		   
		   <div class="form-group">

	<%-- <form action="par_student profile.web" method="POST" id="form1"> --%>
		<div class="form-group">
			<label class="col-md-2 control-label"><b>Select Student <span
										class="required" style="color: red"> * </span>
			</b></label>
			<div class="col-md-5">
				<div class="input-group">
					<span class="input-group-addon"> <i class="fa fa-user"></i></span>
					<select  id="parent" style="width: 40%" class="form-control" 
			name="studentName">
						<!-- <option value="">Select</option> -->
						<c:forEach items="${regal}" var="m">
							<%-- <option value="${m.intRegistrationId}">${m.txtStudentFirstName}</option> --%>
							<option value="${m.txtFirstName}">${m.txtFirstName}  ${m.txtLastName}</option>
						</c:forEach>
					</select>
				</div>

				<span style="color: red; font-weight: bolder;"> <span
					class="field-validation-valid" id="validationMessages1"></span>
				</span>

			</div>
			<input type="submit" class="btn btn-primary"> <br>
		</div>
	<%-- </form> --%>

</div>
		   
			<br><br><br>
			
			<div class="row">
				<div style="text-align:center" class="col-md-12">
				<h4>Please Subscribe to Virtual Library to access all the books</h4>
				<div style="text-align:center"><button type="button" onclick="window.location.href='par_virtual lib1.web'" type="button" class="btn btn-primary">Pay Now to get full access</button></div>
				</div>
			</div>
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function() {    
	  
	   slider(209);
	});
</script>
<%@include file="footer.jsp" %>