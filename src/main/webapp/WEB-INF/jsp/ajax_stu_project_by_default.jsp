<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${empty noWeekRecords }">
		<c:choose>
			<c:when test="${not empty hwListe }">
				<table id="sample_editable_2"
					class="table table-bordered table-hover">
					<!-- <table class="table table-striped table-bordered table-hover" id="sample_2"> -->
					<thead>
						<tr style="background-color: #E5E4E2">
							<th>Date</th>
							<th>Project Name</th>
							<th>Description</th>
							<th>Teacher Name</th>
							<th>Due Date</th>
						</tr>
					</thead>
					<tbody>

						<%
							int k = 0;
											int l = 0;
						%>
						<c:forEach items="${hwListe}" var="m">
							<tr>

								<td>${m.dtAssignDate}</td>
								<td>${m.txtProjectName}</td>
								<td>${m.txtProjectDescription}</td>
								<c:forEach var="c" items="${teachNamesListProj}" begin="<%= k%>"
									end="<%=l %>">
									<td>${c}</td>
								</c:forEach>
								<%
									k++;
														l++;
								%>
								<td>${m.dtDueDate}</td>
							</tr>
						</c:forEach>

						<%
							k = 0;
											l = 0;
						%>
					</tbody>
					<%-- <%
								k = 0;
										l = 0;
							%> --%>
				</table>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">
						<div align="center">
							<h3>No Records Found!!!</h3>
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
					<h3>No Records Found</h3>
				</div>
			</td>
		</tr>
	</c:otherwise>
</c:choose>