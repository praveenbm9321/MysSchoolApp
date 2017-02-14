<%@include file="header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

var name_data;
var email_data;
var person_data;
var address_data;
var website_data;
var tno=0;
var temp=0;
var i=0;
function edit_row(no)
{
	
	temp=tno;
	tno=no;
 document.getElementById("edit"+no).style.display="none";
 document.getElementById("save"+no).style.display="block";
 document.getElementById("delete"+no).style.display="none";
 document.getElementById("cancel"+no).style.display="block";
 if(i>0 && temp!=no){
	    save_row(temp,0);
		       }
			i++;
	
 var name=document.getElementById("name"+no);
 var person=document.getElementById("rperson"+no);
 var address=document.getElementById("address"+no);
 var email=document.getElementById("email"+no);
 var website=document.getElementById("website"+no);
	
  name_data=name.innerHTML;
  person_data=person.innerHTML;
  address_data=address.innerHTML;
  email_data=email.innerHTML;
  website_data=website.innerHTML;
 
	
 name.innerHTML="<input class='form-control' type='text' id='name_text"+no+"' value='"+name_data+"'>";
 person.innerHTML="<input class='form-control' type='text' id='person_text"+no+"' value='"+person_data+"'>";
 address.innerHTML="<input class='form-control' type='text' id='address_text"+no+"' value='"+address_data+"'>";
 email.innerHTML="<input class='form-control' type='text' id='email_text"+no+"' value='"+email_data+"'>";
 website.innerHTML="<input class='form-control' type='text' id='website_text"+no+"' value='"+website_data+"'>";
}


function save_row(no,id)
{
	if(id!=0)
		{
	request=new XMLHttpRequest();
 var name=document.getElementById("name_text"+no).value;
 var person=document.getElementById("person_text"+no).value;
 var address=document.getElementById("address_text"+no).value;
 var email=document.getElementById("email_text"+no).value;
 var website=document.getElementById("website_text"+no).value;
 
var result=updateInfo(name,person,address,email,website,id);  //ajax  call
document.getElementById("name"+no).innerHTML=name;
document.getElementById("rperson"+no).innerHTML=person;
document.getElementById("address"+no).innerHTML=address;
document.getElementById("email"+no).innerHTML=email;
document.getElementById("website"+no).innerHTML=website;
document.getElementById('amit1').innerHTML = '<div class="alert alert-success"><i class="fa fa-check-circle"></i><button type="button" class="close" data-dismiss="alert"aria-hidden="true"></button><b>success!! school details updated</b></div>';
		}
	else
		{
		document.getElementById("name"+no).innerHTML=name_data;
		 document.getElementById("rperson"+no).innerHTML=person_data;
		 document.getElementById("address"+no).innerHTML=address_data;
		 document.getElementById("email"+no).innerHTML=email_data;
		 document.getElementById("website"+no).innerHTML=website_data;
		}

 
 

 document.getElementById("edit"+no).style.display="block";
 document.getElementById("save"+no).style.display="none";
 document.getElementById("cancel"+no).style.display="none";
 document.getElementById("delete"+no).style.display="block";
}


function delete_row(no, id)
{
	if(confirm("are you sure you want to delete"))
		{
	var result=deleteInfo(id);		                        //ajax call
 document.getElementById("row"+no+"").outerHTML="";
 document.getElementById('amit1').innerHTML = '<div class="alert alert-success"><i class="fa fa-check-circle"></i><button type="button" class="close" data-dismiss="alert"aria-hidden="true"></button><b>success!! school entry deleted</b></div>';
		}
	
	
}


var request;
function updateInfo(name,person,address,email,website,id) {
	request = new XMLHttpRequest();
	var v1 = name;
	var v2 = person;
	var v3 = address;
	var v4 = email;
	var v5 = website;
	var v6 = id;
	var url = "Ajax_School_BranchUpdate.web?val1="+v1+"&val2="+v2+"&val3="+v3+"&val4="+v4+"&val5="+v5+"&val6="+v6;
	try {
		request.onreadystatechange = getInfo;
		request.open("GET", url, true);
		request.send();
		return 0;
	} catch (e) {
		alert("Unable to connect to server");
	}
}

function deleteInfo(id) {
	request = new XMLHttpRequest();
	var v1 = id;
	var url = "Ajax_School_BranchDelete.web?val1=" + v1;
	try {
		request.onreadystatechange = getInfo;
		request.open("GET", url, true);
		request.send();
		return 0;
	} catch (e) {
		alert("Unable to connect to server");
	}
}


function getInfo() {
	if (request.readyState == 4) {
		var val = request.responseText;
		document.getElementById('amit').innerHTML = val;
	}
}

</script>


<script type="text/javascript">
<!--
	//-->
	$(document).ready(function() {


		$('.btn.green.sv').hide();
	    $('.btn.red.sv').hide();
	    $.validator.addMethod("alpha",
				function(value, element) {
					return this.optional(element)
							|| value == value
									.match(/^[a-zA-Z\s]+$/);
				});
		
		$('#form1').validate({ // initialize the plugin
			rules : {
				txtSchoolName : {
					required : true,
					alpha : true
				},
				txtEmail : {

					required : true,
					email : true,
					minlength : 5
				},
				txtSchoolContactNumber : {
					required : true,
					digits:true,
					minlength:10,
					maxlength : 10
				},
				txtWebsite : {
					required : true
					
				
				}
			},

			messages : {
				txtSchoolName : {
					required : " please enter school name"

				},
				txtEmail : {
					required : " please enter email"

				},
				txtSchoolContactNumber : {
					required : " please enter contact number"

				},
				txtWebsite : {
					required : " please enter url"

				}

			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}

		});

		/* $('#form2').validate({ // initialize the plugin
			rules : {
				txtSchoolName : {
					required : true,
					lettersonly : true
				},
				txtEmail : {

					email : true,
					minlength : 5
				}
			},

			messages : {
				txtSchoolName : {
					required : " school name cannot be empty"

				},
				txtEmail : {
					required : " email cannot be empty"

				}

			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			} 

		});*/

	});
</script>

<style>
#form1 .error {
	color: red;
	font-size: 12px;
}

#form2 .error {
	color: red;
	font-size: 12px;
}
</style>




<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			School And Branch Details <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>School And Branch Details</h5></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->

<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>

<c:if test="${not empty sucmsg}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<strong>${sucmsg}</strong>${sucmsg1}
	</div>
</c:if>


<div id="amit1"></div>



<div class="row">
	<div class="col-md-12">
		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-plus"></i> Add School Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<div class="portlet-body form">
						<form action="ad_school & branch.web" method="post" id="form1">
							<div class="form-wizard">
								<table class="table table-bordered table-hover">

									<thead>
										<tr>
											<th><b>School Name </b> <span class="required"> <font
													color="red">*</font>
											</span></th>
											<th><input type="text" class="form-control"
												name="txtSchoolName" placeholder="Enter School Name"></th>
											<th><b>Responsible Person</b></th>
											<th><input type="text" class="form-control"
												name="txtResponsiblePerson" placeholder="Enter Responsible Person"></th>

										</tr>
									</thead>
									<thead>
										<tr>
											<th><b>School Address</b></th>
											<th><input type="text" class="form-control"
												name="txtSchoolAddress" placeholder="Enter School Address"></th>

											<th><b>School Contact Number</b> <span class="required">
													<font color="red">*</font>
											</span></th>
											<th><input type="text" class="form-control"
												name="txtSchoolContactNumber" placeholder="Enter School Contact Number"></th>
										</tr>

									</thead>
									<thead>
										<tr>
											<th><b> Email</b> <span class="required"> <font
													color="red">*</font>
											</span></th>
											<th><input type="text" class="form-control"
												name="txtEmail" placeholder="Enter Email"></th>

											<th><b>Website</b> <span class="required"> <font
													color="red">*</font>
											</span></th>
											<th><input type="text" class="form-control"
												name="txtWebsite" placeholder="Enter Website"></th>
										</tr>
										<tr>
											<th colspan="4">
												<div class="col-md-offset-5 col-md-9">
													<button type="submit" class="btn green" id="btnSubmit">
														<i class="fa fa-check"></i> Submit
													</button>
												</div>
											</th>
										</tr>
									</thead>
								</table>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>




		<!-- BEGIN PAGE CONTENT-->
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-list"></i>School Details Inbox
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"> </a> <a
								href="javascript:;" class="reload"> </a>


						</div>
					</div>
					<div class="portlet-body">
						<div class="table-scrollable">
							<!-- <div class="btn-group">
									<button id="sample_editable_1_new" class="btn green">
									Add New <i class="fa fa-plus"></i>
									</button>
								</div> -->
							<div class="btn-group pull-right">
								<!-- <button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="fa fa-angle-down"></i>
									</button>   -->
								<ul class="dropdown-menu pull-right">
									<li><a href="#"> Print </a></li>
									<li><a href="#"> Save as PDF </a></li>
									<li><a href="#"> Export to Excel </a></li>
								</ul>
							</div>
						</div>
						<table class="table table-striped table-hover table-bordered"
							id="sample_editable_1">
							<thead>
								<tr style="background-color: #E5E4E2">
									<th><div align="center">Sl No</div></th>
									<th><div align="center">School Name</div></th>
									<th><div align="center">Responsible Person</div></th>
									<th><div align="center">School Address</div></th>
									<th><div align="center">School Contact Number</div></th>
									<th><div align="center">Email</div></th>
									<th><div align="center">Website</div></th>
									<th colspan="2"><div align="center">Action</div></th>
								</tr>
							</thead>
							<tbody>
								<%
									int i = 0;
								%>
								<c:forEach var="sb" items="${sandbal }">
									<%
										i++;
									%>
									<tr id="row<%=i%>">
										<td><%=i%></td>
										<td id="name<%=i%>">${sb.txtSchoolName }</td>


										<c:choose>
											<c:when test="${empty sb.txtResponsiblePerson}">
												<td id="rperson<%=i%>">N/A</td>
											</c:when>
											<c:otherwise>
												<td id="rperson<%=i%>">${sb.txtResponsiblePerson}</td>
											</c:otherwise>
										</c:choose>



										<c:choose>
											<c:when test="${empty sb.txtSchoolAddress }">
												<td id="address<%=i%>">N/A</td>
											</c:when>
											<c:otherwise>
												<td id="address<%=i%>">${sb.txtSchoolAddress }</td>
											</c:otherwise>
										</c:choose>

<td id="email<%=i%>">${sb.txtSchoolContactNumber}</td>
										<td id="email<%=i%>">${sb.txtEmail}</td>
										<td id="website<%=i%>">${sb.txtWebsite}</td>

										<td align="center">
											<button class="btn yellow" type="submit" id="edit<%=i%>"
												onclick="edit_row(<%=i%>)">
												<i class="fa fa-edit"></i> edit
											</button>


											<button class="btn green sv" type=button id="save<%=i%>"
												onclick="save_row(<%=i%> , ${sb.intSchoolId})">
												<i class="fa fa-save"></i> save
											</button>

										</td>
										<td align="center">
											<button class="btn red"
												onclick="delete_row(<%=i%>,${sb.intSchoolId})"
												id="delete<%=i%>">
												<i class="fa fa-trash-o"></i> Delete
											</button>
											<button class="btn red sv" onclick="save_row(<%=i%>,0)"
												id="cancel<%=i%>">
												<i class="fa fa-times"></i> Cancel
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
</div>
<!-- END CONTENT -->

<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="footer">
	<div class="footer-inner">2017 &copy; by Aikshika</div>
	<div class="footer-tools">
		<span class="go-top"> <i class="fa fa-angle-up"></i>
		</span>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
<script src="assets/plugins/excanvas.min.js"></script> 
<![endif]-->
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


<!-- BEGIN PAGE LEVEL PLUGINS -->


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


<script type="text/javascript"
	src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>

<script type="text/javascript"
	src="assets/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="assets/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript"
	src="assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
<script type="text/javascript" src="assets/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script type="text/javascript"
	src="assets/plugins/bootstrap-markdown/lib/markdown.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL STYLES -->
<script src="assets/scripts/core/app.js"></script>
<script src="assets/scripts/custom/form-validation.js"></script>
<!-- END PAGE LEVEL STYLES -->


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<!-- END PAGE LEVEL STYLES -->


<script>
	jQuery(document).ready(function() {
		App.init();
		slider(13);
		FormWizard.init();

	});
</script>
</body>
<!-- END BODY -->
</html>
