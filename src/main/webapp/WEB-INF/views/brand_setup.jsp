<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="resources/plugins/toastr/toastr.min.css">

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
					<h1 class="m-0 text-dark">Brand</h1>
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

			<!-- banner setup -->
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Brand Setup</h3>

					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
					</div>
				</div>

				<div class="card-body">

					<form:form role="form" id="brand_setup_form"
						modelAttribute="brandDTO" action="brand_setup.html" method="POST"
						enctype="multipart/form-data">
						<form:hidden path="seq" />

						<div class="row">
							<div class="col-md-6">
								<div class="form-group" id="brand_name_data">
									<label for="brandName">Brand Name</label>
									<form:input path="name" id="brand_name"
										placeholder="Brand Name" class="form-control" />
								</div>

								<div class="form-group">
									<label for="status">Status</label>
									<form:select class="form-control" path="status" id="status_id">
										<form:option value="-1">--- Please Select One ---</form:option>
										<form:options items="${statusList}" itemValue="code"
											itemLabel="desc" />
									</form:select>
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group" id="sequence_no_data">
									<label for="sequenceNo">Sequence No</label>
									<form:input path="sequence" id="sequence_no"
										placeholder="Sequence No" class="form-control" />
								</div>

								<div class="form-group">
									<label for=exampleInputFile>File input</label>
									<div class="input-group">
										<div class="custom-file">
											<form:input path="imageFile" type="file"
												accept="image/x-png, image/jpeg" class="custom-file-input"
												id="exampleInputFile" />
											<label class="custom-file-label" for="exampleInputFile">Choose
												file</label>
										</div>
										<div class="input-group-append">
											<span class="input-group-text" id="">Upload</span>
										</div>
									</div>
									<div class="image show" style="margin-top: 20px;">
										<c:if test="${not empty brandDTO.imagePath}">
											<img src="${brandDTO.imagePath}" width="150px;"
												height="150px;" class="img-circle elevation-2"
												alt="Brand Image">
										</c:if>
									</div>
								</div>

								<div class="form-group" style="text-align: right;">
									<button type="submit" class="btn btn-primary"
										onclick="checkBrandSetup()">Save</button>
								</div>
							</div>
						</div>
					</form:form>

				</div>
				<!-- /.card-body -->

				<!-- banner setup -->
			</div>


			<!-- brand list -->
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Brand List</h3>

					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
					</div>
				</div>

				<div class="card-body">
					<div id="example1_wrapper" class="dataTables_wrapper dt-bootstrap4">
						<table id="example1"
							class="table table-bordered table-striped dataTable dtr-inline"
							role="grid" aria-describedby="example1_info">

							<thead>
								<tr role="row">
									<td width="5%">Edit</td>
									<td>No</td>
									<td>Name</td>
									<td>Sequence</td>
									<td>Status</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${brandList}" var="br" varStatus="status">
									<tr>
										<td><a href="brand_setup.html?brandId=${br.seq}"> <i
												class="fas fa-edit"></i>
										</a></td>
										<td>${status.count}</td>
										<td>${br.name}</td>
										<td>${br.sequence}</td>
										<td><c:choose>
												<c:when test="${br.status == 1}">
													<span class="badge badge-primary">${br.statusDesc}</span>
												</c:when>
												<c:otherwise>
													<span class="badge badge-danger">${br.statusDesc}</span>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- banner list -->
			</div>


			<!-- /.container-fluid -->
		</div>
		<!-- /.content -->
	</div>
</div>

<!-- jQuery -->
<script src="resources/plugins/jquery/jquery.min.js"></script>
<script src="resources/js/validation.js"></script>
<script src="resources/js/script.js"></script>

<!-- Bootstrap 4 -->
<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

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
	function checkBrandSetup() {
		checkBrandSetupValid();
		if (errors == 0) {
			return true;
		}
		event.preventDefault();
	}

	function checkBrandSetupValid() {
		errors = 0;
		var nameErr = checkField("Name", $("#brand_name").val(), true, null,
				null, null);

		var sequenceErr = checkField("Sequence", $("#sequence_no").val(), true,
				null, null, null);

		if (nameErr) {
			showError("brand_name_data", "brand_name", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("brand_name_data", "brand_name")
		}

		if (sequenceErr) {
			showError("sequence_no_data", "sequence_no", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("sequence_no_data", "sequence_no")
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