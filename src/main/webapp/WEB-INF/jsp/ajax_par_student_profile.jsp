<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${studDetail}" var="s">
<div class="row">
	<div class="col-md-12">
			<table id="user" class="table table-bordered table-striped">
				<tbody>
					<tr>
						<td class="bold" style="width: 15%">Student Name</td>
						<td style="width: 50%">${s.txtFirstName} ${s.txtLastName}</td>
					</tr>
					<tr>
						<td class="bold">Date of Birth</td>
						<td>${s.dtDob }</td>
					</tr>
					<tr>
						<td class="bold">Gender</td>
						<td>${s.mstgender.txtGenderName}</td>
					</tr>
					<tr>
						<td class="bold">Class</td>
						<td>${s.mststudclass.txtClassName }</td>
					</tr>
					<tr>
						<td class="bold">Section</td>
						<td>${s.mststudclasssection.txtSectionName }</td>

					</tr>
					<!-- class="editable editable-click" data-pk="1" -->
					<tr>
						<td class="bold">Blood Group</td>
						<td>${s.mstbloodgroup.txtBloodGroupName }</td>
					</tr>
					<tr>
						<td class="bold">Address</td>
						<td>${s.txtPerAddress}</td>
					</tr>

					<tr>
						<td class="bold">Contact Number (Father):</td>
						<td>${s.txtFmobileNumber}</td>
					</tr>
					<tr>
						<td class="bold">Contact Number (Mother):</td>
						<td>${s.txtMmobileNumber }</td>
					</tr>
					<tr>
						<td class="bold">Alternate Number (Father):</td>
						<td>${s.txtFotherNumber }</td>
					</tr>
					<tr>
						<td class="bold">Alternate Number (Mother):</td>
						<td>${s.txtMotherNumber }</td>
					</tr>
				</tbody>
			</table>
	</div>
	<i class="fa fa-pencil"></i> <a
		href="par_student profile_update.web?name=${s.txtFirstName}"
		style="font-size: larger; font-weight: bold;">Click here to update
		Student Information</a>
</div>

</c:forEach>
