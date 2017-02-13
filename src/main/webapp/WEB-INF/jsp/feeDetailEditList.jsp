
<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                        <!-- BEGIN FORM-->
                        <form action="#" class="form-horizontal" method="post">
                            <br>


                            
    
                        </form>
                        <!-- END FORM-->
                        
           <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">
                    School Fee Details
                </h3>
            </div>
            <div class="panel-body">
                <form:form id="employeeRegisterForm" cssClass="form-horizontal" modelAttribute="feemanagement" method="post" action="saveFee">
    
                    <div class="form-group">
                        <div class="control-label col-xs-3"> <form:label path="classname" >ClassName</form:label> </div>
                        <div class="col-xs-6">
                            <form:hidden path="id" value="${feeObject.id}"/>
                            <form:input readonly="true" cssClass="form-control" path="classname"  value="${feeObject.classname}"/>
                           <!--   <form:select cssClass="form-control" path="classname" value="${feeObject.classname}" >
                            <option value="class1">class1</option>
                            <option value="class2">class2</option>
                            <option value="class3">class3</option>
                            <option value="class4">class4</option>
                            <option value="class5">class5</option>
                            <option value="class6">class6</option>
                            <option value="class7">class7</option>
                            <option value="class8">class8</option>
                            <option value="class9">class9</option>
                            <option value="class10">class10</option>
                                </form:select>-->
                        </div>
                        <form:errors path="classname"/>
                    </div>
  
    
                    <div class="form-group">
                        <div class="control-label col-xs-3"><form:label path="feeamount">FeeAmount</form:label></div>
                        <div class="col-xs-6">
                            <form:input cssClass="form-control" path="feeamount" value="${feeObject.feeamount}"/>
                        </div>
                    </div>
    
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-4">
                            </div>
                            <div class="col-xs-4">
                                <input type="submit" id="saveEmployee" class="btn btn-primary" value="Save" onclick="return submitEmployeeForm();"/>
                            </div>
                            <div class="col-xs-4">
                            </div>
                        </div>
                    </div>
    
                </form:form>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
        function submitEmployeeForm() {             
        
            // getting the employee form values
            var classname = $('#classname').val().trim();
            var feeamount = $('#feeamount').val();
            if(classname.length ==0) {
                alert('Please enter Classname');
                $('#classname').focus();
                return false;
            }
    
            if(feeamount <= 0) {
                alert('Please enter proper FeeAmount');
                $('#salary').focus();
                return false;
            }
            return true;
        };  
    </script>

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
            <%@include file="footer.jsp"%>