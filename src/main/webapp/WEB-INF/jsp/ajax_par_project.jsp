<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="row">
		<div class="col-md-12">


			<!-- BEGIN BORDERED TABLE PORTLET-->
			<div class="portlet box green">
				<div class="portlet-title">
					<div class="caption">Projects</div>
				</div>
				<div class="portlet-body">
					<div class="table-responsive">
						<c:choose>
							<c:when test="${not empty project}">
								<table id="sample_editable_1"
									class="table table-bordered table-hover">
									<thead>
										<tr style="background-color: #E5E4E2">
											<th style="width: 15%">Subject</th>
											<th style="width: 50%">Project</th>
											<th style="width: 50%">Submission Date</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${project}" var="p">
											<tr>
												<td style="width: 15%">${p.txtProjectName}</td>
												<td style="width: 50%">${p.txtProjectDescription}</td>
												<td style="width: 50%">${p.dtDueDate }</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</c:when>
							<c:otherwise>
								<h3 style="text-align: center; font-weight: bold;"><i class="fa fa-warning" style="font-size: 25px;"></i> No
									record found!!!</h3>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</div>
			<!-- END BORDERED TABLE PORTLET-->

			<div style="text-align: center">
				<button type="button"
					onclick="window.location.href='par_homework.web'" type="button"
					class="btn btn-primary">Back</button>
			</div>
		</div>

	</div>