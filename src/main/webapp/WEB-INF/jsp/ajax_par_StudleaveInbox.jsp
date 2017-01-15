<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div class="col-md-12">

		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Leave Inbox</div>
			</div>

			<div class="portlet-body">
				<div class="table-responsive">
					<%-- <c:if test="${! empty inbox }"> --%>
						<c:choose>
							<c:when test="${! empty studentLeaveInbox}">
								<table id="sample_editable_1" class="table table-bordered table-hover">
									<thead>
										<tr style="background-color: #E5E4E2">
											<th>Student Name</th>
											<th>Applied Date</th>
											<th>Leave Type</th>
											<th>From Date</th>

											<th>To Date</th>
											<th>Reason</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<%-- <c:forEach items="${studentLeaveInbox}" var="l">
											<tr>
												<td>${l.mstregistration.txtFirstName}&nbsp;${l.mstregistration.txtLastName}</td>
												<td><fmt:formatDate value="${l.dtAppliedDate}"
														pattern="dd-MMM-yyyy" /></td>
												<td>${l.mstleave.txtLeaveType }</td>
												<td><fmt:formatDate value="${l.dtFromDate}"
														pattern="dd-MMM-yyyy" /></td>
												<td><fmt:formatDate value="${l.dtToDate}"
														pattern="dd-MMM-yyyy" /></td>
												<td>${l.txtReason}</td>
												<td><c:if test="${l.intStatus==1}">
														<p class="label label-sm label-warning"
															style="font-weight: bolder;">Pending</p>
													</c:if> <c:if test="${l.intStatus==2}">
														<p class="label label-sm label-success"
															style="font-weight: bolder;">Approved</p>
													</c:if> <c:if test="${l.intStatus==3}">
														<p class="label label-sm label-danger"
															style="font-weight: bolder;">Rejected</p>
													</c:if></td>
											</tr>
										</c:forEach> --%>
										
										
										
										<c:forEach items ="${studentLeaveInbox}" var = "l">
										<tr>
										<td>${l.mstregistration.txtFirstName}&nbsp;${l.mstregistration.txtLastName}</td>
										<td><fmt:formatDate value="${l.dtAppliedDate}" pattern="dd-MMM-yyyy"/></td>
										<td>${l.mstleave.txtLeaveType }</td>
										<td><fmt:formatDate value="${l.dtFromDate}" pattern="dd-MMM-yyyy"/></td>
										<td><fmt:formatDate value="${l.dtToDate}" pattern="dd-MMM-yyyy"/></td>
										<td>${l.txtReason}</td>
										<td>
										<c:if test = "${l.intStatus==1}">
										<p class="label label-sm label-warning" style = "font-weight: bolder;">Pending</p>
										</c:if>
										<c:if test = "${l.intStatus==2}">
										<p class="label label-sm label-success" style = "font-weight: bolder;">Approved</p>
										</c:if>
										<c:if test = "${l.intStatus==3}">
										<p class="label label-sm label-danger" style = "font-weight: bolder;">Rejected</p>
										</c:if>
										
										</td>
										</tr>
										</c:forEach>
										
										
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<h3 style="text-align: center;font-weight: bold;"><i class="fa fa-warning" style="font-size: 25px;"></i> No record found!!!</h3>
							</c:otherwise>
						</c:choose>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>
</div>