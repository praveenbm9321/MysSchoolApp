<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12">


		<!-- BEGIN BORDERED TABLE PORTLET-->
		<div class="portlet box green">
			<div class="portlet-title">
				<div class="caption">Student Syllabus</div>
			</div>
			<div class="portlet-body">
				<div class="table-responsive">
					<table  id="sample_editable_1"  class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #E5E4E2">
							    <th>S.No</th>
								<th>Subject Name</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
						<%
							int i = 1;
						%>
							<c:forEach items="${parentSyllabus}" var="m">
								<tr>
								<td><%=i%></td>
								<%
									i++;
								%>
									<td>${m.txtSubjectName}</td>
									
									<td>
												<a href="downloadess/${m.intSubjectId}.web">
																		<button type="button" class="btn green">
																			<i class="fa fa-upload"></i> Download
																		</button>
																	</a>
												
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
		<!-- END BORDERED TABLE PORTLET-->
	</div>

</div>

