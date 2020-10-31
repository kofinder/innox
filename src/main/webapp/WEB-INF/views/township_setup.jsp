<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- DataTables -->
<link rel="stylesheet"
	href="resources/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">

<link rel="stylesheet"
	href="resources/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">

<div class="content-wrapper">

	<div class="content-header">
		<div class="container-fluid">

			<c:if test="${ not empty message }">
				<div class="alert alert-info alert-dismissible mr-3"
					style="margin-top: 7px;">
					<button class="close" aria-hidden="true" data-dismiss="alert"
						type="button">x</button>${message}
				</div>
			</c:if>

			<c:if test="${ not empty errorMsg }">
				<div class="alert alert-danger alert-dismissible mr-3"
					style="margin-top: 7px;">
					<button class="close" aria-hidden="true" data-dismiss="alert"
						type="button">x</button>${errorMsg}
				</div>
			</c:if>

			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">Township</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="dashboard.html">Home</a></li>
						<li class="breadcrumb-item active">${pageTitle}</li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>

	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">


			<div class="row">
				<div class="col-12">
					<!-- township setup -->
					<div class="card card-default">
						<div class="card-header">
							<h3 class="card-title">Township Setup</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="card-body">

							<form:form role="form" id="township_setup_form"
								modelAttribute="townshipDTO" action="township_setup.html"
								method="POST" enctype="multipart/form-data">
								<form:hidden path="seq" />

								<div class="row">
									<div class="col-md-6">
										<div class="form-group" id="state_data">
											<label for="status">State</label>
											<form:select class="form-control" path="stateDTO.seq"
												id="state_id">
												<form:options items="${stateList}" itemValue="seq"
													itemLabel="name" />
											</form:select>
										</div>
										<div class="form-group" id="township_name_data">
											<label for="townshipName">Name</label>
											<form:input path="townshipName" id="township_name"
												placeholder="Township Name" class="form-control" />
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group" id="zone_data">
											<label for="status">Zone</label>
											<form:select class="form-control" path="zoneDTO.seq"
												id="zone_id">
												<form:options items="${zoneList}" itemValue="seq"
													itemLabel="zoneName" />
											</form:select>
										</div>

										<div class="form-group" id="nrc_name_data">
											<label for="nrcName">NRC Name</label>
											<form:input path="nrcName" id="nrc_name"
												placeholder="NRC Name" class="form-control" />
										</div>

										<div class="form-group" style="text-align: right;">
											<button type="submit" class="btn btn-primary"
												onclick="checkTownshipeSetup()">Save</button>
										</div>
									</div>
								</div>
							</form:form>

						</div>
						<!-- end township setup -->

						<!-- township list -->
						<div class="card card-default">

							<div class="card-header">
								<h3 class="card-title">Size List</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool"
										data-card-widget="collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>

							<div class="card-body">
								<div id="example1_wrapper"
									class="dataTables_wrapper dt-bootstrap4">
									<table id="example1"
										class="table table-bordered table-striped dataTable dtr-inline"
										role="grid" aria-describedby="example1_info">

										<thead>
											<tr role="row">
												<td width="5%">Edit</td>
												<td>No</td>
												<td>Name</td>
												<td>NRC</td>
												<td>State</td>
												<td>Zone</td>
												<td>Delivery Fee</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${townshipList}" var="t" varStatus="status">
												<tr>
													<td><a href="township_setup.html?townshipId=${t.seq}">
															<i class="fas fa-edit"></i>
													</a></td>
													<td>${status.count}</td>
													<td>${t.townshipName}</td>
													<td>${t.nrcName}</td>
													<td>${t.stateDTO.name}</td>
													<td>${t.zoneDTO.zoneName}</td>
													<td>${t.zoneDTO.deliveryFee}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- end township list -->
						</div>
					</div>
				</div>

				<!-- /.container-fluid -->
			</div>
			<!-- /.content -->
		</div>
	</div>
</div>

<!-- jQuery -->
<script src="resources/plugins/jquery/jquery.min.js"></script>
<script src="resources/js/validation.js"></script>
<script src="resources/js/script.js"></script>

<!-- DataTables -->
<script src="resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script
	src="resources/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script
	src="resources/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script
	src="resources/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<!-- bs-custom-file-input -->
<script
	src="resources/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		bsCustomFileInput.init();
	});
</script>

<script>
	$(function() {
		$("#example1").DataTable({
			"responsive" : true,
			"autoWidth" : false,
		});
	});
</script>
<script>
	function checkTownshipeSetup() {
		checkValidTownshipSetup();
		if (errors == 0) {
			return true;
		}

		event.preventDefault();
	}

	function checkValidTownshipSetup() {
		errors = 0;

		var nameErr = checkField("Name", $("#township_name").val(), true, null,
				null, null);

		var nrcNameErr = checkField("NRC Name", $("#nrc_name").val(), true,
				null, null, null);

		if (nameErr) {
			showError("township_name_data", "township_name", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("township_name_data", "township_name");
		}

		if (nrcNameErr) {
			showError("nrc_name_data", "nrc_name", nrcNameErr);
			errors = 1;
		} else {
			removeErrorMsg("nrc_name_data", "nrc_name");
		}

	}
</script>
<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
	<!-- Control sidebar content goes here -->
	<div class="p-3">
		<h5>Title</h5>
		<p>Sidebar content</p>
	</div>
</aside>
<!-- /.control-sidebar -->