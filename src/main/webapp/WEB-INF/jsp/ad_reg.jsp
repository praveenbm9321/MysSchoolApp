<!-- BEGIN PAGE HEADER-->
<%@include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('div').removeClass('radio'); //for radio button

						$("#chk").hide();
						$("input[name='child']").click(function() {
							if ($("#yes").is(":checked")) {
								$("#chk").show();
							} else {
								$("#chk").hide();
							}
						});

						//---------age and month

						$('#getDate')
								.change(
										function() {
											var dateString = $('#getDate')
													.val();
											var today = new Date();
											var birthDate = new Date(dateString);
											var age = today.getFullYear()
													- birthDate.getFullYear();
											var m = today.getMonth()
													- birthDate.getMonth();

											if (m < 0
													|| (m === 0 && today
															.getDate() < birthDate
															.getDate())) {
												age--;
												m = 11 + m;
												if (m == 1) {
													if (today.getDate() < birthDate
															.getDate()) {
														m = 0;
													}
												}
											} else if (m > 0 || m == 0) {
												if (today.getDate() < birthDate
														.getDate()) {
													m = m - 1;
												}
											}
											$('#year').val(age);
											$('#month').val(m);
										});

						//---- prevoius school checkbox
						$("#chkbox").click(function() {
							if (!($(this).is(":checked"))) {
								$("#preschl").hide();
							} else {
								$("#preschl").show();
							}
						});

						//----------for address(permanent and temporary address)
						$("#chkboxaddress").click(function() {
							var address = $("textarea#pad").val();
							if ($(this).is(":checked")) {
								$("textarea#tad").val(address);
							} else {
								$("textarea#tad").val('');
							}
						});

						//-----bpl select
						$('#bplid').change(function() {
							$(this).find("option:selected").each(function() {
								if ($(this).attr("value") == "true") {
									// $("#first").not(this).hide();
									$("#bplno").show();
								}

								else {
									$("#bplno").hide();
								}
							});
						}).change();

						//--------social category 
						$('#category')
								.change(
										function() {
											$(this)
													.find("option:selected")
													.each(
															function() {
																if (($(this)
																		.attr(
																				"value") == "1")
																		|| ($(
																				this)
																				.attr(
																						"value") == "")) {
																	// $("#first").not(this).hide();
																	$("#caste1")
																			.hide();
																	$("#caste2")
																			.hide();
																}

																else {
																	$("#caste1")
																			.show();
																	$("#caste2")
																			.show();
																}
															});
										}).change();

						//-----validation for stu reg
						$.validator.addMethod("alpha",
								function(value, element) {
									return this.optional(element)
											|| value == value
													.match(/^[a-zA-Z\s]+$/);
									// --                                    
								});

						$('#stuRegForm')
								.validate(
										{ // initialize the plugin
											rules : {
												code : {
													digits : true
												},
												blBelongToBpl : {
													required : true
												},
												bplCardNo : {
													required : true
												},

												mststudclass : {
													required : true,
												},
												/* mststudclasssection : {
													required : true,
												}, */

												/* mststream : {
													required : true,
												}, */
												mstmedium : {
													required : true,
												},
												mstmothertongue : {
													required : true,
												},
												mstaffiliation : {
													required : true,
												},
												txtPreviousSchoolName : {
													required : true,
													alpha : true
												/* lettersonly : true */
												},
												mstschooltype : {
													required : true,
												},
												txtFirstName : {
													required : true,
													lettersonly : true
												},
												txtMiddleName : {
													lettersonly : true
												},
												txtLastName : {
													required : true,
													lettersonly : true
												},
												txtFatherName : {
													required : true,
													alpha : true

												},
												txtMotherName : {
													required : true,
													alpha : true
												},
												/* txtFoccupation : {
													required : true,
													alpha : true
												},
												txtMoccupation : {
													required : true,
													alpha : true
												}, */
												txtFmobileNumber : {
													required : true,
													digits : true,
													minlength : 10,
													maxlength : 10
												},
												/* txtMmobileNumber : {
													required : true,
													digits : true,
													minlength : 10,
													maxlength : 10
												}, */

												dob : {
													required : true,
												},
												gender : {
													required : true,
												},
												nationality : {
													required : true,
													lettersonly : true
												},
												bloodgroup : {
													required : true,
												},
												religion : {
													required : true,
												},
												social : {
													required : true,
												},
												child : {
													required : true,
												},
												perAdd : {
													required : true
												},
												tempAdd : {
													required : true
												},
												city : {
													required : true,
												},
												taluk : {
													required : true,
													lettersonly : true
												},
												district : {
													required : true,
													lettersonly : true
												},
												code : {
													required : true,
													digits : true,
													minlength : 6,
													maxlength : 6,
												},
												steno : {
													required : true,
												},
												txtFemailId : {
													required : true,
													email : true
												},
												txtMemailId : {
													required : true,
													email : true
												},
												income : {
													digits : true
												},
												depend : {
													digits : true
												},
												acNum : {
													digits : true
												},
												txtFeducationalQualification : {
													nowhitespace : false
												},
												txtMeducationalQualification : {
													nowhitespace : false
												}

											},

											messages : {
												code : {
													digits : "please enter only numbers"
												},
												blBelongToBpl : {
													required : "please select belong to bpl or not"
												},
												bplCardNo : {
													required : "please enter bpl card number"
												},
												mststudclass : {
													required : "Please Select Class"
												},
												/* mststudclasssection : {
													required : "Please Select Section"
												},

												mststream : {
													required : "Please Select Stream"
												}, */
												mstmedium : {
													required : "Please Select Medium"
												},
												mstmothertongue : {
													required : "Please Select Mother Tongue"
												},
												mstaffiliation : {
													required : "Please Select Affilation"
												},
												txtPreviousSchoolName : {
													required : "Enter Previous School Name",
													alpha : "only alphabets and whitespaces"
												},
												mstschooltype : {
													required : "Please Select School Type"
												},
												txtFirstName : {
													required : "Enter First Name",
													lettersonly : "alphabets only please"
												},
												txtMiddleName : {
													lettersonly : "alphabets only please"
												},
												txtLastName : {
													required : "Enter Last Name",
													lettersonly : "alphabets only please"
												},
												txtFatherName : {
													required : "Enter Father Name",
													alpha : "only alphabets and whitespaces"
												},
												txtMotherName : {
													required : "Enter Mother Name",
													alpha : "only alphabets and whitespaces"
												},
												/* txtFoccupation : {
													required : "Enter Father Occupation",
													alpha : "only alphabets and whitespaces"
												},
												txtMoccupation : {
													required : "Enter Mother Occupation",
													alpha : "only alphabets and whitespaces"
												}, */
												txtFmobileNumber : {
													required : "Enter Father Mobile Number"
												},
												/* txtMmobileNumber : {
													required : "Enter Mother Mobile Number"
												},
												 */
												dob : {
													required : "Please Select Date Of Birth"
												},
												gender : {
													required : "Please Select Gender"
												},
												nationality : {
													required : "Enter Nationality",
													lettersonly : "alphabets only please"
												},
												bloodgroup : {
													required : "Please Select Blood Group"
												},
												religion : {
													required : "Please Select Religion"
												},
												social : {
													required : "Please Select Social Category"
												},
												child : {
													required : "Please Select Disability Child"
												},
												perAdd : {
													required : "Permanent Address is required"
												},
												tempAdd : {
													required : "Temporary Address is required"
												},
												city : {
													required : "Please Select City"
												},
												taluk : {
													required : "Enter Taluk",
													lettersonly : "alphabets only please"
												},
												district : {
													required : "Enter District",
													lettersonly : "alphabets only please"
												},
												code : {
													required : "Enter Pincode"
												},
												steno : {
													required : "Enter Enrollment Number"
												},
												txtFemailId : {
													required : "Enter email id"
												},
												txtMemailId : {
													required : "Enter email id"
												},
												income : {
													digits : "enter only numbers"
												},
												depend : {
													digits : "enter only numbers"
												},
												acNum : {
													digits : "enter only numbers"
												},
												txtFeducationalQualification : {
													nowhitespace : "no whitespaces"
												},
												txtMeducationalQualification : {
													nowhitespace : "no whitespaces"
												}

											},

											highlight : function(element) {
												$(element).parent().addClass(
														'error')
											}

										});

						//-------validation for teacher
						$('#formReg').validate({ // initialize the plugin
							rules : {
								firstName : {
									required : true,
									lettersonly : true
								},
								middleName : {
									lettersonly : true

								},
								lastName : {
									required : true,
									lettersonly : true
								},
								fatherName : {
									required : true,
									alpha : true
								},
								gender : {
									required : true
								},
								mobNum : {
									required : true,
									digits : true,
									minlength : 10,
									maxlength : 10
								},
								/* email : {
									required : true,
									email : true
								}, */
								joinDate : {
									required : true
								},
								experience : {
									required : true,
									digits : true
								},
								ctc : {
									digits : true
								},
								perAdd : {
									required : true
								},
								tempAdd : {
									required : true
								},
								marital : {

									required : true
								},
								preSchoolName : {

									alpha : true
								}
							},

							messages : {
								firstName : {
									required : "Enter First Name",
									lettersonly : "alphabets only please"
								},
								middleName : {
									lettersonly : "alphabets only please"

								},
								lastName : {
									required : "Enter Last Name",
									lettersonly : "alphabets only please"
								},
								fatherName : {
									required : "Enter Father/Husband Name",
									lettersonly : "alphabets only please"
								},
								gender : {
									required : "Select Gender"
								},
								mobNum : {
									required : "Enter Mobile Number"
								},
								joinDate : {
									required : "Joining Date is required"
								},
								experience : {
									required : "Experience is required",
									digits : "enter only numbers"
								},
								ctc : {
									digits : "Enter only numbers"
								},
								perAdd : {
									required : "Permanent Address is required"
								},
								tempAdd : {
									required : "Temporary Address is required"
								},
								marital : {

									required : "please select marital status"
								},
								preSchoolName : {

									alpha : "only alphabets and whitespaces"
								}

							},

							highlight : function(element) {
								$(element).parent().addClass('error')
							}

						});

						if ("${secondTab}" == "11") {
							$('#tab22').trigger('click');
						} else {
							$('#tab21').trigger('click');
						}
					});
</script>

<style>
#stuRegForm .error {
	color: red;
	font-size: 12px;
}

#formReg .error {
	color: red;
	font-size: 12px;
}
</style>

<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			Registration <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">

			<li><i class="fa fa-home"></i> <a href="ad_index.web"> Home
			</a> <i class="fa fa-angle-right"></i></li>
			<li><h5>Registration</h5></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->

<div class="alert alert-info">
	<strong>Note:</strong> All (*) marked fields are mandatory.
</div>

<c:choose>
	<c:when test="${not empty msg1 }">
		<div class="alert alert-success">
			<b><i class="fa fa-check-circle"></i>${msg1}</b><small>${msg2}</small>
		</div>
	</c:when>
</c:choose>


<c:if test="${not empty Treg}">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#tab22").trigger('click');
		});
	</script>

</c:if>


<div class="row">
	<div class="col-md-12">
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption" style="padding-left: 420px">Registration
					Page</div>
			</div>
			<div class="portlet-body">
				<ul class="nav nav-pills">
					<li class="active"><a href="#tab_2_1" id="tab21"
						data-toggle="tab">Student Registration </a></li>
					<li><a href="#tab_2_2" id="tab22" data-toggle="tab">Teaching/Non
							Teaching Staff Registration </a></li>
				</ul>
				<div class="tab-content">

					<!--end of menu  -->


					<div class="tab-pane fade active in" id="tab_2_1">



						<div class="row">
							<div class="col-md-12">
								<!-- BEGIN VALIDATION STATES-->
								<div class="portlet-body form">

									<!-- BEGIN FORM-->
									<form
										action='<c:choose>
										<c:when test="${not empty reg }">ad_Sreg/${reg.intRegistrationId}.web </c:when>
										<c:otherwise>user_new reg.web</c:otherwise>
										</c:choose>'
										class="form-horizontal" id="stuRegForm" method="post">
										<h4 align="center">
											<b>Admission Details</b>
										</h4>
										<div class="portlet-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th><b>Admission to Class<font color="red">*</font>
															</b></th>

															<c:choose>
																<c:when test="${not empty reg}">
																	<th><div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-th-large"></i>
																			</span> <select name="mststudclass"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="classes" items="${classes}"
																					begin="0" end="11">
																					<c:if
																						test="${classes.intClassId == reg.mststudclass.intClassId}">
																						<option value="${classes.intClassId}"
																							selected="${classes.txtClassName}">${classes.txtClassName}</option>
																					</c:if>
																					<c:if
																						test="${classes.intClassId != reg.mststudclass.intClassId}">
																						<option value="${classes.intClassId}">${classes.txtClassName}</option>
																					</c:if>
																				</c:forEach>
																			</select>
																		</div></th>
																</c:when>
																<c:otherwise>
																	<th>
																		<div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-th-large"></i>
																			</span> <select name="mststudclass"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="classes" items="${classes}"
																					begin="0" end="11">
																					<option value="${classes.intClassId}">${classes.txtClassName}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</th>
																</c:otherwise>
															</c:choose>
															<th><b>Section</b></th>

															<c:choose>
																<c:when test="${not empty reg}">
																	<th>
																		<div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-th-large"></i>
																			</span> <select name="mststudclasssection"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="sections" items="${sections}"
																					begin="0" end="11">
																					<c:if
																						test="${sections.intSectionId == reg.mststudclasssection.intSectionId}">
																						<option value="${sections.intSectionId}"
																							selected="${sections.intSectionId}">${sections.txtSectionName}</option>
																					</c:if>
																					<c:if
																						test="${sections.intSectionId != reg.mststudclasssection.intSectionId}">
																						<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
																					</c:if>
																				</c:forEach>
																			</select>
																		</div>
																	</th>
																</c:when>
																<c:otherwise>
																	<th><div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-th-large"></i>
																			</span> <select name="mststudclasssection"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="sections" items="${sections}"
																					begin="0" end="3">
																					<option value="${sections.intSectionId}">${sections.txtSectionName}</option>
																				</c:forEach>
																			</select>
																		</div></th>
																</c:otherwise>
															</c:choose>
														</tr>
													</thead>
													<thead>
														<tr>
															<th><b>Semester</b></th>



															<c:choose>
																<c:when test="${not empty reg}">
																	<th>
																		<div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-th-large"></i>
																			</span> <select name="mstsemester"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="sems" items="${sems}">
																					<c:if
																						test="${sems.intSemesterId == reg.mstsemester.intSemesterId}">
																						<option value="${sems.intSemesterId}"
																							selected="${sems.txtSemester}">${sems.txtSemester}</option>
																					</c:if>
																					<c:if
																						test="${sems.intSemesterId != reg.mstsemester.intSemesterId}">
																						<option value="${sems.intSemesterId}">${sems.txtSemester}</option>
																					</c:if>
																				</c:forEach>
																			</select>
																		</div>
																	</th>
																</c:when>
																<c:otherwise>
																	<th><div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-trello"></i>
																			</span> <select name="mstsemester"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="sems" items="${sems}">
																					<option value="${sems.intSemesterId}">${sems.txtSemester}</option>
																				</c:forEach>
																			</select>
																		</div></th>
																</c:otherwise>
															</c:choose>

															<th><b>Stream</b></th>
															<th><c:choose>
																	<c:when test="${not empty reg}">
																		<div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-minus-square"></i>
																			</span> <select name="mststream"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<!-- <option value="">Select</option> -->
																				<c:forEach var="streams" items="${streams}">
																					<c:if
																						test="${streams.intStreamId == reg.mststream.intStreamId}">
																						<option value="${streams.intStreamId}"
																							selected="${streams.txtStream}">${streams.txtStream}</option>
																					</c:if>
																					<c:if
																						test="${streams.intStreamId != reg.mststream.intStreamId}">
																						<option value="${streams.intStreamId}">${streams.txtStream}</option>
																					</c:if>
																				</c:forEach>
																			</select>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-minus-square"></i>
																			</span> <select name="mststream"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<!-- <option value="">Select</option> -->
																				<c:forEach var="streams" items="${streams}">
																					<option value="${streams.intStreamId}">${streams.txtStream}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</c:otherwise>
																</c:choose></th>
														</tr>

													</thead>
													<thead>
														<tr>
															<th><b>Medium of Instruction<font color="red">*</font></b></th>

															<c:choose>
																<c:when test="${not empty reg}">
																	<th><div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-bars"></i>
																			</span> <select name="mstmedium"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="mediums" items="${mediums}">
																					<c:if
																						test="${mediums.intMediumId == reg.mstmedium.intMediumId}">
																						<option value="${mediums.intMediumId}"
																							selected="${mediums.txtMedium}">${mediums.txtMedium}</option>
																					</c:if>
																					<c:if
																						test="${mediums.intMediumId != reg.mstmedium.intMediumId}">
																						<option value="${mediums.intMediumId}">${mediums.txtMedium}</option>
																					</c:if>
																				</c:forEach>
																			</select>
																		</div></th>
																</c:when>
																<c:otherwise>
																	<th><div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-bars"></i>
																			</span> <select name="mstmedium"
																				class="table-group-action-input form-control input-inline input-small input-sm">
																				<option value="">Select</option>
																				<c:forEach var="mediums" items="${mediums}">
																					<option value="${mediums.intMediumId}">${mediums.txtMedium}</option>
																				</c:forEach>
																			</select>
																		</div></th>
																</c:otherwise>
															</c:choose>
															<th colspan=2></th>

														</tr>
													</thead>
												</table>
											</div>
										</div>


										<br>

										<h4 align="center">
											<input type="checkbox" class="group-checkable" id="chkbox" />
											<b>Previous School Details(If Applicable)</b>
										</h4>


										<div id="preschl" style="display: none">

											<div class="portlet-body">
												<div class="table-responsive">
													<table
														class="table table-striped table-hover table-bordered">
														<thead>
															<tr>
																<th><b>Previous School Affiliation<font
																		color="red">*</font>
																</b></th>



																<c:choose>
																	<c:when test="${not empty reg}">
																		<th><div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-hospital-o"></i>
																				</span> <select name="mstaffiliation"
																					class="table-group-action-input form-control input-inline input-small input-sm">
																					<option value="">Select</option>
																					<c:forEach var="affiliations"
																						items="${affiliations}">

																						<c:if
																							test="${reg.mstaffiliation.intAffiliationId==affiliations.intAffiliationId}">
																							<option value="${affiliations.intAffiliationId}"
																								selected="${affiliations.txtAffiliation}">${affiliations.txtAffiliation}</option>
																						</c:if>
																						<c:if
																							test="${reg.mstaffiliation.intAffiliationId!=affiliations.intAffiliationId}">
																							<option value="${affiliations.intAffiliationId}">${affiliations.txtAffiliation}</option>
																						</c:if>
																					</c:forEach>
																				</select>
																			</div></th>
																	</c:when>
																	<c:otherwise>
																		<th><div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-hospital-o"></i>
																				</span> <select name="mstaffiliation"
																					class="table-group-action-input form-control input-inline input-small input-sm">
																					<option value="">Select</option>
																					<c:forEach var="affiliations"
																						items="${affiliations}">
																						<option value="${affiliations.intAffiliationId}">${affiliations.txtAffiliation}</option>
																					</c:forEach>
																				</select>
																			</div></th>
																	</c:otherwise>
																</c:choose>


																<th><b>Transfer Certificate No.</b></th>
																<th><input name="txtTransferCertificateNo"
																	placeholder="Enter Transfer Certificate No" type="text"
																	class="form-control"
																	value="${reg.txtTransferCertificateNo}" /></th>
															</tr>
														</thead>
														<thead>
															<tr>
																<th><b>Transfer Certificate Date</b></th>
																<th>
																	<%-- <input name="dtTransferCertificateDate"
																	class="form-control " type="date" autocomplete="off"
																	placeholder="Transfer Certificate Date"
																	value="${reg.dtTransferCertificateDate}" /> --%>

																	<div class="input-group">
																		<span class="input-group-addon"><i
																			class="fa fa-calendar"></i> </span> <input
																			data-date-format="yyyy-mm-dd"
																			class="form-control form-control-inline input-medium date-picker"
																			readonly="readonly"
																			class="form-control placeholder-no-fix" type="text"
																			autocomplete="off" name="dtTransferCertificateDate"
																			placeholder="Transfer Certificate Date"
																			value="${reg.dtTransferCertificateDate}" />
																	</div>
																</th>

																<th><b>Previous School Name<font color="red">*</font></b></th>
																<th><input name="txtPreviousSchoolName" type="text"
																	class="form-control"
																	value="${reg.txtPreviousSchoolName }"
																	placeholder="Enter Previous School Name" /></th>
															</tr>

														</thead>
														<thead>
															<tr>
																<th><b>Previous School type<font color="red">*</font></b></th>
																<c:choose>
																	<c:when test="${not empty reg}">
																		<th>
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-hospital-o"></i>
																				</span> <select name="mstschooltype"
																					class="table-group-action-input form-control input-inline input-small input-sm">
																					<option value="">Select</option>
																					<c:forEach var="schoolType" items="${schoolType}">

																						<c:if
																							test="${reg.mstschooltype.intSchoolTypeId==schoolType.intSchoolTypeId}">
																							<option value="${schoolType.intSchoolTypeId}"
																								selected="${schoolType.txtSchoolType}">${schoolType.txtSchoolType}</option>
																						</c:if>
																						<c:if
																							test="${reg.mstschooltype.intSchoolTypeId!=schoolType.intSchoolTypeId}">
																							<option value="${schoolType.intSchoolTypeId}">${schoolType.txtSchoolType}</option>
																						</c:if>
																					</c:forEach>
																				</select>
																			</div>
																		</th>
																	</c:when>
																	<c:otherwise>
																		<th>
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-hospital-o"></i>
																				</span> <select name="mstschooltype"
																					class="table-group-action-input form-control input-inline input-small input-sm">
																					<option value="">Select</option>
																					<c:forEach var="schoolType" items="${schoolType}">
																						<option value="${schoolType.intSchoolTypeId}">${schoolType.txtSchoolType}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</th>
																	</c:otherwise>
																</c:choose>

																<th><b>Previous School Address</b></th>
																<th><input name="txtPreviousSchoolAddress"
																	type="text" class="form-control"
																	value="${reg.txtPreviousSchoolAddress}"
																	placeholder="Enter Previous School Address " /></th>
															</tr>

														</thead>

														<thead>
															<tr>
																<th><b>Upload Transfer Certificate</b></th>
																<th><input type="file" name="" class="form-control"></th>

																<th colspan="2"></th>
															</tr>
														</thead>


													</table>
												</div>
											</div>

										</div>



										<br>
										<h4 align="center">
											<b>Student Details</b>
										</h4>

										<div class="portlet-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th><b>Student Name <font color="red">*</font></b></th>
															<th><input name="txtFirstName" type="text"
																class="form-control" placeholder="First Name"
																value="${reg.txtFirstName}" /></th>
															<th><input name="txtMiddleName" type="text"
																class="form-control" placeholder="Middle Name"
																value="${reg.txtMiddleName}" /></th>
															<th><input name="txtLastName" type="text"
																class="form-control" placeholder="Last Name"
																value="${reg.txtLastName}" /></th>
														</tr>
													</thead>

												</table>
											</div>
										</div>

										<table class="table table-striped table-hover table-bordered">
											<thead>
												<tr>
													<th width="26%"></th>
													<th width="37%">
														<div align="center">Father</div>
													</th>
													<th width="37%">
														<div align="center">Mother</div>
													</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td align="center"><b>Parent Name <font
															color="red">*</font></b></td>
													<td><input name="txtFatherName" type="text"
														placeholder="Father Name" class="form-control"
														value="${reg.txtFatherName}" /></td>
													<td><input name="txtMotherName" type="text"
														placeholder="Mother Name" class="form-control"
														value="${reg.txtMotherName}" /></td>

												</tr>

												<tr>
													<td align="center"><b>Occupation</b></td>
													<td><input type="text" class="form-control"
														name="txtFoccupation" placeholder="Father Occupation"
														value="${reg.txtFoccupation}"></td>
													<td><input type="text" class="form-control"
														name="txtMoccupation" placeholder="Mother Occupation"
														value="${reg.txtMoccupation}"></td>

												</tr>

												<tr>
													<td align="center"><b>Address<font color="red">*</font>
													</b></td>
													<td><textarea class="form-control" rows="2"
															name="perAdd" placeholder="Permanent address"
															style="resize: none">${reg.txtPerAddress}</textarea></td>
													<td><textarea class="form-control" rows="2"
															style="resize: none" name="tempAdd"
															placeholder="Temporary address">${reg.txtTempAddress}</textarea></td>

												</tr>
												<tr>
													<td align="center"><b>Mobile Number <font
															color="red">*</font></b></td>
													<td><input type="text" class="form-control"
														name="txtFmobileNumber" placeholder="Father Mobile Number"
														value="${reg.txtFmobileNumber}"></td>
													<td><input type="text" class="form-control"
														name="txtMmobileNumber" placeholder="Mother Mobile Number"
														value="${reg.txtMmobileNumber}"></td>

												</tr>
												<tr>
													<td align="center"><b>Alternate Number</b></td>
													<td><input type="text" class="form-control"
														name="txtFotherNumber"
														placeholder="Father alternate Number"
														value="${reg.txtFotherNumber}"></td>
													<td><input type="text" class="form-control"
														name="txtMotherNumber"
														placeholder="Mother alternate Number"
														value="${reg.txtMotherNumber}"></td>

												</tr>
												<tr>
													<td align="center"><b>Educational Qualification</b></td>
													<td><input type="text" class="form-control"
														name="txtFeducationalQualification"
														placeholder="Father Educational Qualifications"
														value="${reg.txtFeducationalQualification}"></td>
													<td><input type="text" class="form-control"
														name="txtMeducationalQualification"
														placeholder="Mother Educational Qualifications"
														value="${reg.txtFeducationalQualification}"></td>

												</tr>



												<tr>
													<td align="center"><b>Email Id<font color="red">*</font></b></td>
													<td><input type="text" class="form-control"
														name="txtFemailId" placeholder="Father Email"
														value="${reg.txtFemailId}"></td>
													<td><input type="text" class="form-control"
														name="txtMemailId" placeholder="Mother Email"
														value="${reg.txtMemailId}"></td>

												</tr>

												<tr>
													<td align="center"><b>Aadhar Number</b></td>
													<td><input type="text" class="form-control"
														name="txtFaadharNumber"
														placeholder="Father's Aadhar Number"
														value="${reg.txtFaadharNumber}"></td>
													<td><input type="text" class="form-control"
														name="txtMaadharNumber"
														placeholder="Mother's Aadhar Number"
														value="${reg.txtMaadharNumber}"></td>

												</tr>

											</tbody>
										</table>

										<div class="form-group">
											<label class="control-label col-md-3"><b>Date of
													Birth</b> <font color="red">*</font></label>

											<!--  <div class="input-icon">
				                <i class="fa fa-calendar"></i>-->
											<div class="col-md-9">

												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-calendar"></i> </span> <input
														data-date-format="yyyy-mm-dd"
														class="form-control form-control-inline input-medium date-picker"
														readonly="readonly"
														class="form-control placeholder-no-fix" type="text"
														autocomplete="off" placeholder="Date of Birth" name="dob"
														value="${reg.dtDob}" id="getDate" />
												</div>
											</div>
										</div>

										<table>
											<tr>
												<th>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<b> Age <font color="red">*</font></b>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</th>
												<td width="165px"><input type="text"
													class="form-control" name="" placeholder="Years" id="year"
													readonly="readonly"></td>
												<td width="165px"><input type="text"
													class="form-control" name="" placeholder="Month" id="month"
													readonly="readonly"></td>
											</tr>
										</table>

										<br>
										<c:choose>
											<c:when test="${not empty reg}">
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Gender</b>
														<span class="required"> * </span></label>
													<div class="col-md-4">
														<div class="input-group">
															<%--<span class="input-group-addon"> <i
																class="fa fa-user"></i>
															</span>  <select name="gender"
																class="table-group-action-input form-control input-inline input-small input-sm">
																<option value="">Select</option>
																<c:forEach var="g" items="${gal}">
																	<c:if
																		test="${reg.mstgender.intGenderId==g.intGenderId}">
																		<option value="${g.intGenderId}"
																			selected="${g.txtGenderName}">${g.txtGenderName}</option>
																	</c:if>
																	<c:if
																		test="${reg.mstgender.intGenderId!=g.intGenderId}">
																		<option value="${g.intGenderId}">${g.txtGenderName}</option>
																	</c:if>
																</c:forEach>
															</select> --%>


															<c:forEach var="g" items="${gal}">
																<c:if test="${reg.mstgender.intGenderId==g.intGenderId}">
																	<input type="radio" name="gender"
																		value="${g.intGenderId}" checked="${g.txtGenderName}">${g.txtGenderName}
																	</c:if>
																<c:if test="${reg.mstgender.intGenderId!=g.intGenderId}">
																	<input type="radio" name="gender"
																		value="${g.intGenderId}">${g.txtGenderName}
																	</c:if>
															</c:forEach>
														</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>


												<%-- <div class="form-group">
													<label class="col-md-3 control-label"><b>Gender</b><font
														color="red">*</font></label>
													<div class="col-md-4">
														<div class="radio-list">
															<c:forEach var="g" items="${gal}">
																<input type="radio" name="gender"
																	value="${g.intGenderId}">${g.txtGenderName}
											</c:forEach>
														</div>
													</div>
												</div> --%>




												<div class="form-group">
													<label class="col-md-3 control-label"><b>Gender</b>
														<font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<!-- <span class="input-group-addon"> <i
																class="fa fa-user"></i>
															</span> -->
															<c:forEach var="g" items="${gal }">
																<input type="radio" name="gender"
																	value="${g.intGenderId}">${g.txtGenderName}
															</c:forEach>
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>





										<c:choose>
											<c:when test="${not empty reg}">
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Mother
															Tongue</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-heart"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="mstmothertongue">
																<option value="">Select</option>
																<c:forEach var="mother" items="${motherTongues}">
																	<c:if
																		test="${reg.mstmothertongue.intmothertongueId==mother.intmothertongueId }">
																		<option value="${mother.intmothertongueId}"
																			selected="${mother.txtmothertongue}">${mother.txtmothertongue}</option>
																	</c:if>
																	<c:if
																		test="${reg.mstmothertongue.intmothertongueId!=mother.intmothertongueId }">
																		<option value="${mother.intmothertongueId}">${mother.txtmothertongue}</option>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>

											</c:when>
											<c:otherwise>
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Mother
															Tongue</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-heart"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="mstmothertongue">
																<option value="">Select</option>
																<c:forEach var="mother" items="${motherTongues}">
																	<option value="${mother.intmothertongueId}">${mother.txtmothertongue}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>

											</c:otherwise>
										</c:choose>




										<c:choose>
											<c:when test="${not empty reg}">
												<div class="form-group">
													<label class="control-label col-md-3"><b>Nationality</b>
														<font color="red">*</font></label>
													<div class="col-md-4">
														<input name="nationality" type="text" class="form-control"
															placeholder="Enter Nationality"
															value="${reg.txtNationality}" />
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="form-group">
													<label class="control-label col-md-3"><b>Nationality</b>
														<font color="red">*</font></label>
													<div class="col-md-4">
														<input name="nationality" type="text" class="form-control"
															placeholder="Enter Nationality" value="Indian" />
													</div>
												</div>
											</c:otherwise>
										</c:choose>







										<!--		<div class="form-group">
											<label class="control-label col-md-3"><b>Parent's
													Annual Income</b> </label>
											<div class="col-md-4">
												<input name="income" type="text" class="form-control" />
											</div>
										</div>


										 <div>
											<label class="col-md-3 control-label"><b>Belong
													to BPL<span class="required"> * </span>
											</b></label>
											<div class="col-md-4">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-gear (alias)"></i>
													</span> <select
														class="table-group-action-input form-control input-inline input-small input-sm"
														name="blBelongToBpl" id="bplid">
														<option value="">Select</option>
														<option value="true">Yes</option>
														<option value="false">No</option>
													</select>
													<input type="text" class="form-control" placeholder="">
												</div>
											</div>
										</div>

										<div id="bplno">
											<label class="control-label col-md-3"><b>BPL Card
													No.<span class="required"> * </span>
											</b> </label>
											<div class="col-md-4">
												<input name="bplCardNo" type="text" class="form-control" />
											</div>
										</div>
 -->





										<div class="portlet-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th><b>Parent's Annual Income </b></th>
															<th><input name="income" type="text"
																class="form-control" placeholder="Enter Annual Income"
																value="${reg.nmParentsAnnualIncome}" /></th>




															<th><b>Belong to BPL<font color="red">*</font></b></th>

															<c:choose>
																<c:when test="${not empty reg}">
																	<th><div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-hospital-o"></i>
																			</span> <select
																				class="table-group-action-input form-control input-inline input-small input-sm"
																				name="blBelongToBpl" id="bplid">
																				<c:if test="${reg.blBelongToBpl == true}">
																					<option value="">Select</option>
																					<option value="true" selected="selected">Yes</option>
																					<option value="false">No</option>
																				</c:if>

																				<c:if test="${reg.blBelongToBpl == false}">
																					<option value="">Select</option>
																					<option value="true">Yes</option>
																					<option value="false" selected="selected">No</option>
																				</c:if>

																				<c:if test="${reg.blBelongToBpl == null}">
																					<option value="" selected="selected">Select</option>
																					<option value="true">Yes</option>
																					<option value="false">No</option>
																				</c:if>
																			</select>
																		</div></th>
																</c:when>
																<c:otherwise>
																	<th><div class="input-group">
																			<span class="input-group-addon"> <i
																				class="fa fa-hospital-o"></i>
																			</span> <select
																				class="table-group-action-input form-control input-inline input-small input-sm"
																				name="blBelongToBpl" id="bplid">
																				<option value="">Select</option>
																				<option value="true">Yes</option>
																				<option value="false">No</option>
																			</select>
																		</div></th>
																</c:otherwise>
															</c:choose>




														</tr>
													</thead>

													<thead id="bplno">
														<tr>
															<th><b>Upload BPL Certificate</b></th>
															<th><input type="file" name="" class="form-control"></th>
															<th><b>BPL Card No.<font color="red">*</font>
															</b></th>
															<th><input name="bplCardNo" type="text"
																class="form-control" value="${reg.txtBplcardNo}"
																placeholder="Enter BPL Card No" /></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>



										<c:choose>
											<c:when test="${not empty reg}">
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Blood
															Group</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-heart"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="bloodgroup">
																<option value="">Select</option>
																<c:forEach var="b" items="${bal }">
																	<c:if
																		test="${reg.mstbloodgroup.intBloodGroupId==b.intBloodGroupId}">
																		<option value="${b.intBloodGroupId}"
																			selected="${b.txtBloodGroupName}">${b.txtBloodGroupName}</option>
																	</c:if>
																	<c:if
																		test="${reg.mstbloodgroup.intBloodGroupId!=b.intBloodGroupId}">
																		<option value="${ b.intBloodGroupId}">${b. txtBloodGroupName}</option>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>

											</c:when>
											<c:otherwise>
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Blood
															Group</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-heart"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="bloodgroup">
																<option value="">Select</option>
																<c:forEach var="b" items="${bal }">
																	<option value="${ b.intBloodGroupId}">${b. txtBloodGroupName}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>

											</c:otherwise>
										</c:choose>


										<c:choose>
											<c:when test="${not empty reg}">
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Religion</b>
														<font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-flag"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="religion">
																<option value="">Select</option>
																<c:forEach var="r" items="${ral }">
																	<c:if
																		test="${reg.mstreligion.intReligionId==r.intReligionId}">
																		<option value="${r.intReligionId}"
																			selected="${r.txtReligionName}">${r. txtReligionName}</option>
																	</c:if>
																	<c:if
																		test="${reg.mstreligion.intReligionId!=r.intReligionId}">
																		<option value="${ r.intReligionId }">${r. txtReligionName}</option>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Religion</b>
														<font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-flag"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="religion">
																<option value="">Select</option>
																<c:forEach var="r" items="${ral }">
																	<option value="${ r.intReligionId }">${r. txtReligionName}</option>
																</c:forEach>
																<!-- <option value="Cancel">Others(Please Specify)</option> -->
															</select>
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>


										<c:choose>
											<c:when test="${not empty reg}">
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Social
															Category</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-flag"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="social" id="category">
																<option value="">Select</option>
																<c:forEach var="s" items="${sal }">
																	<c:if
																		test="${reg.mstsocialcategory.intSocialCategoryId==s.intSocialCategoryId}">
																		<option value="${s.intSocialCategoryId}"
																			selected="${s.txtSocialCategory}">${s. txtSocialCategory }</option>
																	</c:if>
																	<c:if
																		test="${reg.mstsocialcategory.intSocialCategoryId!=s.intSocialCategoryId}">
																		<option value="${ s.intSocialCategoryId }">${s. txtSocialCategory }</option>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="form-group">
													<label class="col-md-3 control-label"><b>Social
															Category</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa fa-flag"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="social" id="category">
																<option value="">Select</option>
																<c:forEach var="s" items="${sal }">
																	<option value="${ s.intSocialCategoryId }">${s. txtSocialCategory }</option>
																</c:forEach>
															</select>
															<!-- <input type="text" class="form-control" placeholder=""> -->
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>

										<div class="form-group" id="caste1">
											<label class="col-md-3 control-label"><b>Student
													Caste</b></label>
											<div class="col-md-4">
												<div class="input-group">
													<input type="text" class="form-control" name="scasteno"
														value="${reg.txtStudentCaste}"
														placeholder="Enter Student Caste ">
												</div>
											</div>
										</div>
										<div class="form-group" id="caste2">
											<label class="col-md-3 control-label"><b>Student
													Caste Certificate No.</b></label>
											<div class="col-md-4">
												<div class="input-group">
													<input type="text" class="form-control" name="scaste"
														placeholder="Enter caste certificateNo "
														value="${reg.txtStudentCasteCertificateNo}">
												</div>
											</div>
										</div>



										<div class="form-group">
											<label class="col-md-3 control-label"><b>Specially Abled</b>
												<font color="red">*</font></label>
											<div class="col-md-4">
												<div class="input-group">
													<c:choose>
														<c:when test="${not empty reg}">
															<c:if
																test="${reg.mstdisabilitychild.intDisabilityChildId ==1 }">
																<input type="radio" name="child"
																	value="${reg.mstdisabilitychild.intDisabilityChildId}"
																	id="yes">yes
														<input type="radio" name="child" value="1"
																	checked="checked">no
														</c:if>
															<c:if
																test="${reg.mstdisabilitychild.intDisabilityChildId !=1 }">
																<input type="radio" name="child"
																	value="${reg.mstdisabilitychild.intDisabilityChildId}"
																	id="yes" checked="checked">yes
														<input type="radio" name="child" value="1">no
														</c:if>
														</c:when>
														<c:otherwise>
															<input type="radio" name="child" value="0" id="yes">yes
														    <input type="radio" name="child" value="1"
																checked="checked">no
                                                        </c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>






										<c:choose>
											<c:when test="${not empty reg}">
												<div class="form-group" id="chk">
													<label class="col-md-3 control-label"><b>
															Reason</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa  fa-minus-circle"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="disChild">
																<option value="">Select</option>
																<c:forEach var="d" items="${dal}">
																	<c:if
																		test="${reg.mstdisabilitychild.intDisabilityChildId==d.intDisabilityChildId}">
																		<option value="${d.intDisabilityChildId}"
																			selected="${d.txtDisability}">${d.txtDisability}</option>
																	</c:if>
																	<c:if
																		test="${reg.mstdisabilitychild.intDisabilityChildId!=d.intDisabilityChildId}">
																		<option value="${ d.intDisabilityChildId}">${d.txtDisability}</option>
																	</c:if>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="form-group" id="chk">
													<label class="col-md-3 control-label"><b>Reason
															</b><font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
															<span class="input-group-addon"> <i
																class="fa  fa-minus-circle"></i>
															</span> <select
																class="table-group-action-input form-control input-inline input-small input-sm"
																name="disChild">
																<option value="">Select</option>
																<c:forEach var="d" items="${dal }" begin="1">
																	<option value="${ d.intDisabilityChildId  }">${d.txtDisability  }</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
											</c:otherwise>
										</c:choose>



										<br>
										<h4 align="center">
											<b>Student Contact Details</b>
										</h4>

										<div class="portlet-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover">

													<thead>
														<tr>
															<th><b>Locality</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter locality" name="local"
																value="${reg.txtLocality }"></th>
															<th><b>City/Town/Village<font color="red">*</font></b>
															</th>
															<th><input type="text" class="form-control"
																placeholder="Enter City" name="city"
																value="${reg.txtCity }"></th>
														</tr>

													</thead>
													<thead>
														<tr>
															<th><b>Taluk<font color="red">*</font></b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Taluk" name="taluk"
																value="${reg.txtTaluk }"></th>
															<th><b> District<font color="red">*</font></b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter District" name="district"
																value="${reg.txtDistrict }"></th>


														</tr>

													</thead>
													<thead>
														<tr>
															<th><b> Pincode<font color="red">*</font></b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Pincode" name="code"
																value="${reg.txtPincode }"></th>
															<th colspan="2"></th>
														</tr>
													</thead>
												</table>

											</div>
										</div>
										<br>
										<h4 align="center">
											<b>(For Office Use Only)</b>
										</h4>




										<div class="portlet-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th><b>Student Enrolment Number<font color="red">*</font>
															</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Enrollment No" name="steno"
																value="${reg.txtStudentEnrollmentNumber }"></th>
															<th><b>Admission Date</b></th>
															<th>
																<%-- <input type="date" class="form-control"
																name="Adate" value="${reg.dtAdmissionDate}"> --%>

																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="fa fa-calendar"></i> </span> <input
																		data-date-format="yyyy-mm-dd"
																		class="form-control form-control-inline input-medium date-picker"
																		readonly="readonly"
																		class="form-control placeholder-no-fix" type="text"
																		autocomplete="off" name="Adate"
																		placeholder="Admission Date"
																		value="${reg.dtAdmissionDate}" />
																</div>
															</th>

														</tr>
													</thead>
													<thead>
														<tr>
															<th><b>Student/Parent's Bank Name</b></th>




															<c:choose>
																<c:when test="${not empty bnk}">
																	<th><select
																		class="table-group-action-input form-control input-inline input-small input-sm"
																		name="bank">
																			<option value="">Select</option>
																			<c:forEach var="c" items="${banks}">
																				<c:if
																					test="${bnk.tblbankname.intBankNameId==c.intBankNameId}">
																					<option value="${c.intBankNameId}"
																						selected="${c.txtBankName}">${c.txtBankName  }</option>
																				</c:if>
																				<c:if
																					test="${bnk.tblbankname.intBankNameId!=c.intBankNameId}">
																					<option value="${ c.intBankNameId  }">${c.txtBankName  }</option>
																				</c:if>
																			</c:forEach>
																	</select></th>
																</c:when>
																<c:otherwise>
																	<th><select
																		class="table-group-action-input form-control input-inline input-small input-sm"
																		name="bank">
																			<option value="">Select</option>
																			<c:forEach var="c" items="${banks}">
																				<option value="${ c.intBankNameId  }">${c.txtBankName  }</option>
																			</c:forEach>
																	</select></th>
																</c:otherwise>
															</c:choose>
															<th><b>Student/Parent's Bank A/c No</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Parent's Bank A/c No" name="acNum"
																value="${bnk.txtBankAccountNo }"></th>
														</tr>
													</thead>
													<thead>
														<tr>
															<th><b> Bank IFSC Code</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enetr Bank IFSC Code " name="ifsc"
																value="${bnk.txtIfsccode}"></th>
															<th colspan=2></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
										<div class="form-actions fluid">
											<div class="col-md-offset-3 col-md-9">
												<c:if test="${empty reg }">
													<button type="Submit" class="btn green">Submit</button>
												</c:if>
												<c:if test="${not empty reg }">
													<button type="Submit" class="btn green">Update</button>
												</c:if>
												<button type="reset" class="btn green">Reset</button>
											</div>
										</div>
									</form>
									<!-- END FORM-->
								</div>
							</div>
							<!-- END VALIDATION STATES-->
						</div>

					</div>





					<div class="tab-pane fade" id="tab_2_2">
						<form
							action='<c:choose>
							<c:when test="${not empty Treg }">ad_Treg/${Treg.intRegistrationId}.web</c:when>
							<c:otherwise>ad_reg22.web</c:otherwise>
							</c:choose>'
							id="formReg" method="post">
							<div class="row">
								<div class="col-md-12">
									<!-- BEGIN BORDERED TABLE PORTLET-->
									<div class="portlet box green">
										<div class="portlet-title">
											<div class="caption">General Information</div>
										</div>

										<div class="portlet-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th><b>First Name</b> <span class="required">
																	<font color="red">*</font>
															</span>
															<th><input type="text" class="form-control"
																placeholder="Enter First Name" name="firstName"
																value="${Treg.txtFirstName }"></th>

															<th><b>Middle Name</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Middle Name" name="middleName"
																id="mName" value="${Treg.txtMiddleName  }"></th>
														</tr>
													</thead>
													<thead>
														<tr>
															<th><b>Last Name</b><span class="required"> <font
																	color="red">*</font>
															</span></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Last Name" name="lastName"
																value="${Treg.txtLastName }"></th>

															<th><b>Father/Husband Name</b><span class="required">
																	<font color="red">*</font>
															</span></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Father/Husband Name"
																name="fatherName" value="${Treg.txtFatherName }"></th>
														</tr>
													</thead>
													<thead>
														<tr>
															<th><b> Gender <font color="red">*</font></b></th>
															<c:choose>
																<c:when test="${not empty Treg}">
																	<th>
																		<div class="col-md-12">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="gender">
																					<option value="">Select</option>
																					<c:forEach var="g" items="${gender}">
																						<c:if
																							test="${Treg.mstgender.intGenderId==g.intGenderId}">
																							<option value="${g.intGenderId}"
																								selected="${g.txtGenderName}">${g.txtGenderName}</option>
																						</c:if>
																						<c:if
																							test="${Treg.mstgender.intGenderId!=g.intGenderId}">
																							<option value="${g.intGenderId}">${g.txtGenderName}</option>
																						</c:if>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</th>
																</c:when>
																<c:otherwise>
																	<th>


																		<div class="form-group">
																			<div class="col-md-4">
																				<div class="radio-list">
																					<c:forEach var="g" items="${gal}">
																						<input type="radio" name="gender"
																							value="${g.intGenderId}">${g.txtGenderName}
															                               	</c:forEach>
																				</div>
																			</div>
																		</div> <%-- <div class="col-md-12">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="gender">
																					<option value="">Select</option>
																					<c:forEach var="g" items="${gender}">
																						<option value="${g.intGenderId}">${g.txtGenderName}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div> --%>
																	</th>
																</c:otherwise>
															</c:choose>

															<th><b> Religion</b></th>

															<c:choose>
																<c:when test="${not empty Treg}">
																	<th>
																		<div class="col-md-4">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="religion">
																					<option value="">Select</option>
																					<c:forEach var="r" items="${religion}">
																						<c:if
																							test="${Treg.mstreligion.intReligionId==r.intReligionId}">
																							<option value="${r.intReligionId}"
																								selected="${r.txtReligionName}">${r.txtReligionName}</option>
																						</c:if>
																						<c:if
																							test="${Treg.mstreligion.intReligionId!=r.intReligionId}">
																							<option value="${r.intReligionId}">${r.txtReligionName}</option>
																						</c:if>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</th>
																</c:when>
																<c:otherwise>
																	<th>
																		<div class="col-md-4">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="religion">
																					<option value="">Select</option>
																					<c:forEach var="g" items="${religion}">
																						<option value="${g.intReligionId}">${g.txtReligionName}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</th>
																</c:otherwise>
															</c:choose>

														</tr>

													</thead>
													<thead>
														<tr>
															<th><b> Marital Status <font color="red">*</font></b></th>
															<c:choose>
																<c:when test="${not empty Treg}">
																	<th>
																		<div class="col-md-12">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="marital">
																					<option value="">Select</option>
																					<c:forEach var="m" items="${marital}">
																						<c:if
																							test="${Treg.mstmaritalstatus.intMaritalStatusId==m.intMaritalStatusId}">
																							<option value="${m.intMaritalStatusId}"
																								selected="${m.txtMaritalStatus}">${m.txtMaritalStatus}</option>
																						</c:if>
																						<c:if
																							test="${Treg.mstmaritalstatus.intMaritalStatusId!=m.intMaritalStatusId}">
																							<option value="${m.intMaritalStatusId}">${m.txtMaritalStatus}</option>
																						</c:if>
																					</c:forEach>

																				</select>
																			</div>
																		</div>
																	</th>
																</c:when>
																<c:otherwise>
																	<th>
																		<div class="col-md-12">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-user"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="marital">
																					<option value="">Select</option>
																					<c:forEach var="g" items="${marital}">
																						<option value="${g.intMaritalStatusId}">${g.txtMaritalStatus}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</th>
																</c:otherwise>
															</c:choose>

															<th><b> City</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter City" name="city"
																value="${Treg.txtCity }"></th>
														</tr>
													</thead>
													<thead>
														<tr>
															<th><b> Mobile No.(Only 10 digits)</b><span
																class="required"> <font color="red">*</font>
															</span></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Mobile No" name="mobNum"
																value="${Treg.txtFmobileNumber}"></th>

															<th><b> Email</b><span class="required"> </span></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Email" name="email"
																value="${Treg.txtEmailId}"></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- BEGIN BORDERED TABLE PORTLET-->
							<div class="row">
								<div class="col-md-12">
									<div class="portlet box green">
										<div class="portlet-title">
											<div class="caption">Official Information</div>
										</div>

										<div class="portlet-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover">
													<thead>
														<tr>
															<th><b>Joining Date</b><span class="required">
																	<font color="red">*</font>
															</span></th>
															<th>
																<%-- <input type="date" class="form-control"
																name="joinDate" value="${Treg.dtJoiningDate}"> --%>

																<div class="input-group">
																	<span class="input-group-addon"><i
																		class="fa fa-calendar"></i> </span> <input
																		data-date-format="yyyy-mm-dd"
																		class="form-control form-control-inline input-medium date-picker"
																		readonly="readonly"
																		class="form-control placeholder-no-fix" type="text"
																		autocomplete="off" name="joinDate"
																		placeholder="Joining Date"
																		value="${Treg.dtJoiningDate}" />
																</div>
															</th>
															<th><b>Previous School name</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Previous School name"
																name="preSchoolName"
																value="${Treg.txtPreviousSchoolName}"></th>
														</tr>
													</thead>
													<thead>
														<tr>
															<th><b>Subject</b></th>
															<c:choose>
																<c:when test="${not empty Treg}">
																	<th>
																		<div class="col-md-4">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-list-alt"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="subject">
																					<option value="">Select</option>
																					<c:forEach var="g" items="${subject}">
																						<c:if
																							test="${Treg.mstsubject.intSubjectId==g.intSubjectId}">
																							<option value="${g.intSubjectId}"
																								selected="${g.txtSubjectName}">${g.txtSubjectName}</option>
																						</c:if>
																						<c:if
																							test="${Treg.mstsubject.intSubjectId!=g.intSubjectId}">
																							<option value="${g.intSubjectId}">${g.txtSubjectName}</option>
																						</c:if>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</th>
																</c:when>
																<c:otherwise>
																	<th>
																		<div class="col-md-4">
																			<div class="input-group">
																				<span class="input-group-addon"> <i
																					class="fa fa-list-alt"></i>
																				</span> <select
																					class="table-group-action-input form-control input-inline input-small input-sm"
																					name="subject">
																					<option value="">Select</option>
																					<c:forEach var="g" items="${subject}">
																						<option value="${g.intSubjectId}">${g.txtSubjectName}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</th>
																</c:otherwise>
															</c:choose>


															<th><b>Experience(mention in years only)</b><span
																class="required"> <font color="red">*</font>
															</span></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Experience" name="experience"
																value="${Treg.txtExperience}"></th>
														</tr>

													</thead>
													<thead>
														<tr>
															<th><b> Designation</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter Designation" name="designation"
																value="${Treg.txtDesignation}"></th>

															<th><b> CTC</b></th>
															<th><input type="text" class="form-control"
																placeholder="Enter CTC" name="ctc" value="${bnk.txtCtc}"></th>
														</tr>
													</thead>
													<thead>
														<tr>
															<th align="center"><b> Permanent Address</b><span
																class="required"> <font color="red">*</font>
															</span></th>
															<th><textarea class="form-control"
																	style="resize: none" rows="2" name="perAdd"
																	placeholder="Permanent Address" id="pad">${Treg.txtPerAddress}</textarea></th>




															<th align="center"><input type="checkbox"
																class="group-checkable" id="chkboxaddress"
																title="temporary address same as permanent address" />
																<b> Temporary Address</b><span class="required">
																	<font color="red">*</font>
															</span></th>
															<th><textarea class="form-control"
																	style="resize: none" rows="2" name="tempAdd"
																	placeholder="Enter Temporary Address" id="tad">${Treg.txtTempAddress}</textarea></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<input type="hidden" value="11" name="secondTab">
											<c:if test="${empty Treg}">
												<button type="Submit" class="btn green">Submit</button>
											</c:if>

											<c:if test="${not empty Treg}">
												<button type="Submit" class="btn green">Update</button>
											</c:if>

											<button type="reset" class="btn green">Reset</button>
										</div>
									</div>
								</div>
								<!-- END PAGE CONTENT -->
							</div>
						</form>
					</div>
					<!-- END PAGE CONTENT-->
				</div>
			</div>
		</div>
		<!-- END CONTENT -->
	</div>
</div>


<script src="assets/scripts/custom/slider.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		slider(2);
	});
</script>
<%@include file="footer.jsp"%>