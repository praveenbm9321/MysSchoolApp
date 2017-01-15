<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

	
var name_data;
var email_data;
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
 var email=document.getElementById("email"+no);
	
 name_data=name.innerHTML;
 email_data=email.innerHTML;
	
 name.innerHTML="<input class='form-control' type='text' id='name_text"+no+"' value='"+name_data+"' required='required'>";
 email.innerHTML="<input class='form-control' type='text' id='email_text"+no+"' value='"+email_data+"' required='required'>";

}


function save_row(no,id)
{
	
	if(id!=0)
		{
	
 var name=document.getElementById("name_text"+no).value;
 var email=document.getElementById("email_text"+no).value;
 
var result=updateInfo(name,email,id);
document.getElementById("name"+no).innerHTML=name;
document.getElementById("email"+no).innerHTML=email;
document.getElementById('amit1').innerHTML = '<div class="alert alert-success"><i class="fa fa-check-circle"></i><button type="button" class="close" data-dismiss="alert"aria-hidden="true"></button><b>success!! school updated</b></div>';
		}
	else
		{
		 document.getElementById("name"+no).innerHTML=name_data;
		 document.getElementById("email"+no).innerHTML=email_data;
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
	var result=deleteInfo(id);		
 document.getElementById("row"+no+"").outerHTML="";
 document.getElementById('amit1').innerHTML = '<div class="alert alert-success"><i class="fa fa-check-circle"></i><button type="button" class="close" data-dismiss="alert"aria-hidden="true"></button><b>success!! school entry deleted</b></div>';
		}
}


var request;
function updateInfo(name,email,id) {
	request = new XMLHttpRequest();
	var v1 = name;
	var v2 = email;
	var v3 = id;
	var url = "Ajax_SchoolsconnectUpdate.web?val1=" + v1 + "&val2=" + v2 + "&val3="+ v3;
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
	var url = "Ajax_SchoolsconnectDelete.web?val1=" + v1;
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
		/* document.getElementById('amit').innerHTML =val; */
		/*  document.getElementById('sid').style.display = 'none'; */
		//document.getElementById('amit1').innerHTML = '<div class="alert alert-success"><i class="fa fa-check-circle"></i><button type="button" class="close" data-dismiss="alert"aria-hidden="true"></button><b>Hello</b></div>';
		document.getElementById('sid').innerHTML ='';
		//document.getElementById("emailid").innerHTML ='';
		document.getElementById('sid').innerHTML =val;

		if(document.getElementById('schl').value == '')
			{
			 document.getElementById("emailid").style.display="none";
			}else
				{
				 document.getElementById("emailid").style.display="block";
				}
		
	}
}
</script>


<script type="text/javascript">
	$(document).ready(function() {
		

		$('.btn.green.sv').hide();
	    $('.btn.red.sv').hide();

	
		$("#sid").change(function() {
			$(this).find("option:selected").each(function() {
				var noid = $(this).attr("value1");
				if ($(this).attr("value") == "") {
					$("#emailid").hide();
				} else {
					$("#emailid").show();
					$("#mailfield").val(noid);
				}
			});
		}).change();

		$('#form1').validate({ // initialize the plugin
			rules : {
				mstschool : {
					required : true

				},
				txtBody : {
					required : true
				},
				txtTitle : {
					required : true
				},
				blAttachment:{

accept:"application / pdf,image / * ,application / doc,application / docx"
					}
			},

			messages : {
				mstschool : {
					required : " please select school"

				},
				txtBody : {
					required : "body cannot be empty"
				},
				txtTitle : {
					required : "title cannot be empty"
				},
				blAttachment:{

					accept:"please select only file with extension .pdf, .jpg, .gif, .png, .doc, .docx"
										}

			},

			highlight : function(element) {
				$(element).parent().addClass('error')
			}

		});

		$('#form2').validate({ // initialize the plugin
			rules : {
				txtSchoolName : {
					required : true

				},

				txtEmail : {
					required : true,
					email : true,
					minlength : 5
				}
			},

			messages : {
				txtSchoolName : {
					required : "please select school"

				},
				txtEmail : {
					required : "please enter email-id"
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

#form2 .error {
	color: red;
	font-size: 13px;
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
			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>School's Connect</h5> <!-- <i class="fa fa-angle-right"></i> -->
			</li>
		</ul>
	</div>
</div>
<!-- END PAGE HEADER-->
<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>


<c:choose>
	<c:when test="${not empty msg }">
		<div class="alert alert-success">
			<i class="fa fa-check-circle"></i>
			<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true"></button>
			<b>${msg}</b>
		</div>
	</c:when>
</c:choose>

<c:if test="${not empty smsg }">
	<div class="alert alert-success">
		<i class="fa fa-check-circle"></i>
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true"></button>
		<b>${smsg}</b>
	</div>

	<script type="text/javascript">
$(document).ready(function(){
$("#t2").trigger('click');
});
</script>

</c:if>

<div id="amit1"></div>

<div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-reorder"></i>School's Connect
		</div>

	</div>
	<div class="portlet-body">

		<ul class="nav nav-pills">

			<li class="active"><a href="#tab_2_1" data-toggle="tab" id="t1">
					School's Connect </a></li>

			<li><a href="#tab_2_2" data-toggle="tab" id="t2"> Add New
					School </a></li>

		</ul>


		<div class="tab-content">
			<div class="tab-pane fade active in" id="tab_2_1">
				<div class="row">
					<div class="col-md-12">
						<div class="tab-content">
							<div class="tab-pane active" id="tab_0">
								<div class="portlet box green">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-reorder"></i>School's Connect
										</div>

									</div>
									<div class="portlet-body form">
										<!-- BEGIN FORM-->
										<form action="ad_school's_connect.web" class="form-horizontal"
											method="post" enctype="multipart/form-data" id="form1">
											<div class="form-body">

												<div class="form-group">
													<label class="col-md-3 control-label"><b>Title</b>
														<span class="required"> * </span> </label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-book"></i>
															</span> <input type="text" name="txtTitle" class="form-control"
																placeholder="Enter Title">
														</div>

													</div>
												</div>

												<div id="sid">
													<div class="form-group">
														<label class="col-md-3 control-label"><b>School</b>
															<span class="required"> * </span></label>
														<div class="col-md-4">
															<div class="input-group ">
																<span class="input-group-addon"> <i
																	class="fa fa-building-o"></i>
																</span> <select
																	class="table-group-action-input form-control input-inline input-small input-sm"
																	name="mstschool" id="schl">
																	<option value="">select</option>
																	<c:forEach var="m" items="${mstschools}">
																		<option value="${m.intSchoolId}"
																			value1="${m.txtEmail}">${m.txtSchoolName}</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>

													<div class="form-group" id="emailid">
														<label class="col-md-3 control-label"><b>Email
																id</b> </label>
														<div class="col-md-4">
															<div class="input-group">
																<span class="input-group-addon"> <i
																	class="fa fa-user"></i>
																</span> <input type="text" class="form-control" placeholder=""
																	name="txtMailId" id="mailfield" readonly>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Body</b> <span
														class="required"> * </span> </label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-bars"></i>
															</span>
															<textarea name="txtBody" class="form-control" rows="3"
																style="resize: none" placeholder="Enter Body"></textarea>
														</div>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-3 control-label"><b>Attachment</b></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-upload"></i>
															</span> <input type="file" name="blAttachment"
																class="form-control" placeholder="upload">
														</div>
														<p>
															Only (<strong>.pdf, .JPG, .GIF, .PNG, .DOC,
																.DOCX</strong>) files are allowed .<br> (The maximum file
															size for uploads is <strong>5 MB</strong>)
														</p>
													</div>
												</div>

												<div class="form-actions fluid">
													<div class="col-md-offset-3 col-md-9">
														<button type="submit" class="btn green">Send</button>

													</div>
												</div>
											</div>
										</form>
										<!-- END FORM-->
									</div>
								</div>
							</div>
							<!-- END PAGE CONTENT-->
						</div>
					</div>
					<!-- END CONTENT -->
				</div>
				<!-- END CONTAINER -->
			</div>




			<div class="tab-pane fade" id="tab_2_2">
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN BORDERED TABLE PORTLET-->
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-plus"></i> Add School
								</div>
							</div>
							<div class="portlet-body">
								<div class="table-responsive">
									<div class="portlet-body form">

										<form action="AddNewSchool.web" method="get" id="form2">
											<div class="form-wizard">
												<table class="table table-bordered table-hover">

													<thead>
														<tr>
															<th><b>School Name </b> <span class="required">
																	<font color="red">*</font>
															</span></th>
															<th><input type="text" class="form-control"
																name="txtSchoolName" placeholder="Enter School Name"></th>
															<th><b> Email</b> <span class="required"> <font
																	color="red">*</font>
															</span></th>
															<th><input type="text" class="form-control"
																name="txtEmail" placeholder="Enter Email"></th>
														</tr>
														<tr>
															<th><b>Address</b></th>
															<th><input type="text" class="form-control"
																name="txtSchoolAddress"
																placeholder="Enter School Address"></th>

															<th><b>Contact Number</b> <span
																class="required"></span></th>
															<th><input type="text" class="form-control"
																name="txtSchoolContactNumber"
																placeholder="Enter School Contact Number"></th>
														</tr>
													</thead>

													<thead>
														<tr>

														</tr>
														<tr>
															<th colspan="4">
																<div class="col-md-offset-5 col-md-9">
																	<button type="submit" class="btn green" id="#tab_2_2">
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
											<i class="fa fa-list"></i>School Inbox
										</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"> </a> <a
												href="javascript:;" class="reload"> </a>
										</div>
									</div>
									<div class="portlet-body">
										<div class="table-scrollable">

											<div class="btn-group pull-right">

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
													<th>Sl No</th>
													<th>School Name</th>
													<th>Email</th>
													<th colspan="2"><div align="center">Action</div></th>
												</tr>
											</thead>
											<tbody>
												<%
													int i = 0;
												%>
												<c:forEach var="sb" items="${mstschools }">

													<%
														i++;
													%>
													<tr id="row<%=i%>">
														<td><%=i%></td>
														<td id="name<%=i%>">${sb.txtSchoolName }</td>

														<td id="email<%=i%>">${sb.txtEmail }</td>

														<td align="center">
															<button class="btn yellow" type="submit" id="edit<%=i%>"
																onclick="edit_row(<%=i%>)">
																<i class="fa fa-edit"></i> edit
															</button>


															<button class="btn green sv" type=button id="save<%=i%>"
																onclick="save_row(<%=i%>,${ sb.intSchoolId })">
																<i class="fa fa-save"></i> save
															</button>

														</td>
														<td align="center">
															<button class="btn red"
																onclick="delete_row(<%=i%>,${ sb.intSchoolId })"
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
					</div>

				</div>
			</div>
		</div>
	</div>
</div>




<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script>
	jQuery(document).ready(function() {
		
		slider(12);
	});
</script>

<%@include file="footer.jsp"%>