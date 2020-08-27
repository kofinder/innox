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
					<h1 class="m-0 text-dark">Banner</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
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
					<!-- banner setup -->
					<div class="card card-default">
						<div class="card-header">
							<h3 class="card-title">Banner Setup</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>


						<div class="card-body">

							<form:form role="form" id="banner_setup_form"
								modelAttribute="bannerDTO" action="banner_setup.html"
								method="POST" enctype="multipart/form-data">
								<form:hidden path="seq" />

								<div class="form-row">
									<div class="col-sm-6">
										<div class="form-group" id="banner_name_data">
											<label for="bnnerName">Banner Name</label>
											<form:input path="name" id="banner_name"
												placeholder="Banner Name" class="form-control" />
										</div>
										<div class="form-group" id="sequence_data">
											<label for="sequence_id">Sequence</label>
											<form:input path="sequenceNo" class="form-control"
												id="sequence_id" placeholder="Banner Name" />
										</div>
									</div>

									<div class="col-sm-6">
										<div class="form-group" id="description_data">
											<label for="bannerDescription">Description</label>
											<form:input path="description" class="form-control"
												id="description_id" placeholder="Description" />
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
												<c:if test="${not empty bannerDTO.imagePath}">
													<img src="${bannerDTO.imagePath}" width="100px;"
														height="100px;" class="img-circle elevation-2"
														alt="Banner Image">
												</c:if>
											</div>
										</div>

										<div class="form-group" style="text-align: right;">
											<button type="submit" class="btn btn-primary"
												onclick="checkBannerSetup()">Save</button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- banner setup -->

					<!-- banner list -->
					<div class="card card-default">

						<div class="card-header">
							<h3 class="card-title">Banner List</h3>

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
											<td>Description</td>
											<td>Sequence</td>
											<td>Status</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${bannerList}" var="b" varStatus="status">
											<tr>
												<td><a href="banner_setup.html?bannerId=${b.seq}">
														<i class="fas fa-edit"></i>
												</a></td>
												<td>${status.count}</td>
												<td>${b.name}</td>
												<td>${b.description}</td>
												<td>${b.sequenceNo}</td>
												<td>${b.status}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- banner list -->
					</div>


				</div>
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
	function checkBannerSetup() {
		checkBannerSetupValid();
		if (errors == 0) {
			return true;
		}
		event.preventDefault();
	}

	function checkBannerSetupValid() {
		errors = 0;
		var nameErr = checkField("Name", $("#banner_name").val(), true, null,
				null, null);

		var descErr = checkField("Description", $("#description_id").val(),
				true, null, null, null);

		var sequenceErr = checkField("Sequence", $("#sequence_id").val(), true,
				null, null, null);

		if (nameErr) {
			showError("banner_name_data", "banner_name", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("banner_name_data", "banner_name")
		}

		if (descErr) {
			showError("description_data", "description_id", descErr);
			errors = 1;
		} else {
			removeErrorMsg("description_data", "description_id")
		}

		if (sequenceErr) {
			showError("sequence_data", "sequence_id", sequenceErr);
			errors = 1;
		} else {
			removeErrorMsg("sequence_data", "sequence_id")
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