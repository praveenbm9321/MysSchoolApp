
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>School Fee Detail List</title>
    <!-- Bootstrap CSS -->
    <%-- <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
<div class="container myrow-container">
    <div class="panel panel-success">
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
                        	<th><a href="deleteFee?id=<c:out value='${fee.id}'/>">Delete</a></th>                         	
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>    
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <%-- <script src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
     --%>

</body>
</html>