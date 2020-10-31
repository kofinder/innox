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
					<h1 class="m-0 text-dark">Zone</h1>
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
					<!-- font setup -->
					<div class="card card-default">
						<div class="card-header">
							<h3 class="card-title">State Setup</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="card-body">

							<form:form role="form" id="zone_setup_form"
								modelAttribute="zoneDTO" action="zone_setup.html" method="POST"
								enctype="multipart/form-data">
								<form:hidden path="seq" />

								<div class="row">
									<div class="col-md-6">
										<div class="form-group" id="zone_name_data">
											<label for="zoneName">Zone Name</label>
											<form:input path="zoneName" id="zone_name"
												placeholder="Zone Name" class="form-control" />
										</div>
										<div class="form-group" id="delivery_fee_data">
											<label for="deliveryFee">Delivery Fee</label>
											<form:input path="deliveryFee" id="delivery_fee"
												placeholder="Delivery Fee" class="form-control" />
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group" id="zone_code_data">
											<label for="zoneCode">Zone Code</label>
											<form:input path="zoneCode" id="zone_code"
												placeholder="Zone Code" class="form-control" />
										</div>

										<div class="form-group" id="description">
											<label for="zoneCode">Description</label>
											<form:textarea path="description" rows="3"
												cssClass="form-control" />
										</div>

										<div class="form-group" style="text-align: right;">
											<button type="submit" class="btn btn-primary"
												onclick="checkZoneSetup()">Save</button>
										</div>
									</div>
								</div>
							</form:form>

						</div>
						<!-- end zone setup -->

						<!-- zone list -->
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
												<td>Zone Code</td>
												<td>Delivery Fee</td>
												<td>Description</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${zoneList}" var="zone" varStatus="status">
												<tr>
													<td><a href="zone_setup.html?zoneId=${zone.seq}"> <i
															class="fas fa-edit"></i>
													</a></td>
													<td>${status.count}</td>
													<td>${zone.zoneName}</td>
													<td>${zone.zoneCode}</td>
													<td>${zone.deliveryFee}</td>
													<td>${zone.description}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- end zone list -->
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
	function checkZoneSetup() {
		checkValidZoneSetup();
		if (errors == 0) {
			return true;
		}

		event.preventDefault();
	}

	function checkValidZoneSetup() {
		errors = 0;

		var nameErr = checkField("Name", $("#zone_name").val(), true, null,
				null, null);

		var zoneCodeErr = checkField("Zone Code", $("#zone_code").val(), true,
				null, null, null);

		if (nameErr) {
			showError("zone_name_data", "zone_name", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("zone_name_data", "zone_name");
		}

		if (zoneCodeErr) {
			showError("zone_code_data", "zone_code", zoneCodeErr);
			errors = 1;
		} else {
			removeErrorMsg("zone_code_data", "zone_code");
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