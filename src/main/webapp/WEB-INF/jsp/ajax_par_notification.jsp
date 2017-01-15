<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">School Management Notification</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty tblinvites}">
							<table id="sample_editable_1"
								class="table table-bordered table-hover">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th>Date</th>
										<th>Title</th>
										<th>Description</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tblinvites}" var="m">
										<tr>
											<td>${m.dtDate}</td>
											<td>${m.txtInvitationTitle}</td>
											<td>${m.txtBody}</td>
											<td><c:choose>
													<c:when test="${empty m.txtfileName }">
													No attachment Found
												</c:when>
													<c:otherwise>
														<a href="downloadsNotification/${m.intInvitesId}.web">

															<button type="button" class="btn green">
																<i class="fa fa-download"> </i> Download
															</button>

														</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<h3 style="text-align: center; font-weight: bold;">No record
								found</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

</div>
<div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Teacher Notification</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty mste}">
							<table id="sample_editable_2"
								class="table table-bordered table-hover">
								<thead>
									<tr style="background-color: #E5E4E2">
										<th>Date</th>

										<th>Description</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${mste}" var="m">
										<tr>
											<td>${m.dtInvitesDate}</td>

											<td>${m.txtMessage}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<h3 style="text-align: center; font-weight: bold;">No record
								found</h3>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

</div>
