<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Today's Attendance</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty trnAttendance}">
							<%-- <c:if test="${! empty trnAttendance}"> --%>
							<%-- <table class="table table-striped table-bordered table-hover">
								<thead>
									<tr style="background-color: #DCDCDC">
										<th >Date</th>
										<th>In Time</th>
										<th>Out Time</th>
										<th>Total Hours(HH:MM)</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${trnAttendance}" var="atd">
										<tr>
											<td>${atd.dtSlotDate }</td>
											<td>${inTime}</td>
											<td>${outTime}</td>
											<td>${totalHours}</td>
										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${empty trnAttendance}">
									<h3 style="text-align: center; font-weight: bolder;">No
										Record Found</h3>
								</c:if>
								</tbody>
							</table> --%>
							
							
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th style="background-color: #DCDCDC">Date</th>
										<th style="background-color: #DCDCDC">In Time</th>
										<th style="background-color: #DCDCDC">Out Time</th>
										<th style="background-color: #DCDCDC">Total Hours(HH:MM)</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${trnAttendance}" var="atd">
										<tr>
											<td>${atd.dtSlotDate }</td>
											<td>${inTime}</td>
											<td><c:choose>
													<c:when test="${empty outTime }">
													--:--
												</c:when>
													<c:otherwise>
														${outTime}
													</c:otherwise>
												</c:choose></td>
												
												<td><c:choose>
													<c:when test="${  empty totalHours }">
													00:00
												</c:when>
													<c:otherwise>
														${totalHours}
													</c:otherwise>
												</c:choose></td>
											<%-- <td>${totalHours}</td> --%>
										</tr>
									</c:forEach>
									<%-- </c:if> --%>
									<%-- <c:if test="${empty trnAttendance}">
									<h3 style="text-align: center; font-weight: bolder;">No
										Record Found</h3>
								</c:if> --%>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<h3 style="text-align: center; font-weight: bold;"><i class="fa fa-warning" style="font-size: 25px;"></i> No record
								found</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>




<%-- <%@include file="par_attendance_1.jsp"%> --%>
<%-- <%@include file="footer.jsp"%> --%>
