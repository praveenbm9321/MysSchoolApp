<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


 <div class="portlet box green">
	<div class="portlet-title">
		<div class="caption">Time table</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead>
					<tr style="background-color: #E5E4E2">
						<td  align="center"><strong>Day</strong></td>

						<c:forEach items="${timingList }" var="time">
							<td align="center"><strong>${time.txtClassTimings }</strong></td>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<%!int i, j, k, l = 0;%>
					<c:forEach items="${totalList}" var="totalObj">
						<tr>

							<!-- <td>Day</td> -->

							<c:forEach items="${daysList}" var="dow" begin="<%=i %>"
								end="<%=j %>">
								<td align="center" style="background-color: #E5E4E2">${dow.txtDay }</td>
							</c:forEach>
							<%
								i++;
									j++;
							%>

							<%
								k = 0;
									l = 0;
							%>
							<c:set var="count" value="0" />
							<c:forEach items="${timingList }" var="time">
								<c:choose>
									<c:when test="${fn:length(totalObj) ne 0}">
										<c:forEach items="${totalObj}" var="tt" begin="<%=k %>"
											end="<%=l %>">
											<c:choose>
												<c:when
													test="${time.intMsttimingsId eq tt.msttimings.intMsttimingsId}">

													<c:choose>
														<c:when test="${tt.intStatus ne 0 }">
															<td align="center">${tt.mstsubject.txtSubjectName }</td>
														</c:when>
														<c:otherwise>
															<td align="center">N/A</td>
														</c:otherwise>
													</c:choose>
													<%
														k++;
																					l++;
													%>
													<c:set var="count" value="${count+1 }" />
													<c:if test="${fn:length(totalObj) eq count }">
														<%
															k--;
																							l--;
														%>
													</c:if>
												</c:when>
												<c:otherwise>
													<td align="center">Null</td>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<td align="center">Not Assigned</td>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
				<%
					i = 0;
					j = 0;
					k = 0;
					l = 0;
				%>
			</table>

		</div>
	</div>
</div>
	
	