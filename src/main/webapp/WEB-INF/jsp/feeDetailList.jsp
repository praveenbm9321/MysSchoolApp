
<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			<!-- <small>basic datatable samples</small> -->
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li class="btn-group">
				
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home </a> <i
				class="fa fa-angle-right"></i></li>
			<li><h5> Fee Management </h5> 
			</li>
			<!-- <li>
							<a href="#">
								Basic Datatables
							</a>
						</li> -->
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->

<!-- BEGIN PAGE HEADER-->
<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->


		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->

		<div class="tab-content">
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-reorder"></i>Fee management
						</div>
						<!--<div class="tools">
											<a href="javascript:;" class="collapse">
											</a>
											<a href="#portlet-config" data-toggle="modal" class="config">
											</a>
											<a href="javascript:;" class="reload">
											</a>
											<a href="javascript:;" class="remove">
											</a>
										</div>-->
					</div>
					<div class="portlet-body form">
			<div class="panel-heading">
            <h3 class="panel-title">
                <div align="left"><b>Fee Detail List</b> </div>
                <div align="right"><a href="createFee">Add New FeeAmount</a></div>
            </h3>
        </div>
        
        <div class="panel-body">
            <c:if test="${empty feeList}">
                There are no Fee Detail Here
            </c:if>
            <c:if test="${not empty feeList}">   
            	<form action="searchFee">
            		<div class="row">
					  <div class="col-md-4">Search Fee Detail List: <input type='text' name='searchName' id='searchName'/> </div>
					  <div id="status"></div>
					  <div class="col-md-4"><input class="btn btn-success" type='submit' value='Search'/></div>
					</div>
            	</form>         	
            	            	
                <table class="table table-hover table-bordered">
                    <thead style="background-color: #bce8f1;">
                    <tr>
                        <!--  <th>FeeId</th>-->
                        <th>ClassName</th>
                        <th>FeeAmount</th>
                        <th>Upadate</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${feeList}" var="fee">
                        <tr>
                        	<!--  <th><c:out value="${fee.id}"/></th>-->
                        	<th><c:out value="${fee.classname}"/></th>
                        	<th><c:out value="${fee.feeamount}"/></th> 
                        	<th><a href="editFee?id=<c:out value='${fee.id}'/>">Update</a></th>
                        	<th><a href="deleteFee?id=<c:out value='${fee.id}'/>" onClick="confirmDelete();">Delete</a></th> 
                        	                     	
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>    
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
						<!-- END PAGE CONTENT-->
					</div>
					</div>
					</div>
					</div>
				</div>
				<!-- END CONTENT -->
			<!-- END CONTAINER -->
			<!-- BEGIN FOOTER -->
<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function() {    
	  
	   slider(9);
	});
</script>
<script type="text/JavaScript">
 
function confirmDelete(){
var agree=confirm("Are you sure you want to delete this file?");
if (agree)
      return true ;
else
     return false ;
}
</script>
			<%@include file="footer.jsp"%>