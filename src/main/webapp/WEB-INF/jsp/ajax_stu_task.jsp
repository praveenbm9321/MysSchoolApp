<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${empty noWeekRecordsss }">
		<c:choose>
			<c:when test="${not empty hwList }">
				<table id="sample_editable_1"
					class="table table-bordered table-hover">
					<thead>
						<tr style="background-color: #E5E4E2">
							<th>Date</th>
							<th>Subject</th>
							<th>Question</th>
							<th>Teacher Name</th>
							<th>Due Date</th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 0;
											int j = 0;
						%>

						<c:forEach items="${hwList}" var="m">
							<tr>

								<td>${m.dtAssignDate}</td>
								<td>${m.txtSubject}</td>
								<td>${m.txtHomeWork}</td>

								<c:forEach var="c" items="${teachNamesListHW}" begin="<%= i%>"
									end="<%=j %>">
									<td>${c}</td>
								</c:forEach>
								<%
									i++;
														j++;
								%>
								<td>${m.dtDueDate}</td>
							</tr>
						</c:forEach>


						<%
							i = 0;
											j = 0;
						%>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">
						<div align="center">
							<h3>
						<i class="fa fa-warning" style="font-size: 25px;"></i> No Records
						Found
					</h3>
						</div>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="5">
				<div align="center">
					<h3>
						<i class="fa fa-warning" style="font-size: 25px;"></i> No Records
						Found
					</h3>
				</div>
			</td>
		</tr>
	</c:otherwise>
</c:choose>






