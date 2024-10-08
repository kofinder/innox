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
					<h1 class="m-0 text-dark">Size</h1>
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
					<!-- banner setup -->
					<div class="card card-default">
						<div class="card-header">
							<h3 class="card-title">Size Setup</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>


						<div class="card-body">

							<form:form role="form" id="size_setup_form"
								modelAttribute="sizeDTO" action="size_setup.html" method="POST"
								enctype="multipart/form-data">
								<form:hidden path="seq" />

								<div class="form-row">
									<div class="col-sm-6">
										<div class="form-group" id="size_name_data">
											<label for="sizeName">Size Name</label>
											<form:input path="sizeName" id="size_name"
												placeholder="Size Name" class="form-control" />
										</div>
										<div class="form-group">
											<label for="status">Category</label>
											<form:select class="form-control" path="sizeCategory"
												id="size_category">
												<form:options items="${itemSizeCategoryList}"
													itemValue="code" itemLabel="desc" />
											</form:select>
										</div>
									</div>

									<div class="col-sm-6">
										<div class="form-group" id="size_code_data">
											<label for="sizeCode">Size Code</label>
											<form:input path="sizeCode" class="form-control"
												id="size_code" placeholder="Size Code" />
										</div>
										<c:if test="${sizeDTO.seq > 0}">
											<div class="form-group">
												<label for="status">Status</label>
												<form:select class="form-control" path="status"
													id="status_id">
													<form:options items="${statusList}" itemValue="code"
														itemLabel="desc" />
												</form:select>
											</div>
										</c:if>

										<div class="form-group" style="text-align: right;">
											<button type="submit" class="btn btn-primary"
												onclick="checkSizeSetup()">Save</button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- size setup -->

					<!-- size list -->
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
											<td>Code</td>
											<td>Category</td>
											<td>Status</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${sizeList}" var="size" varStatus="status">
											<tr>
												<td><a href="size_setup.html?sizeId=${size.seq}"> <i
														class="fas fa-edit"></i>
												</a></td>
												<td>${status.count}</td>
												<td>${size.sizeName}</td>
												<td>${size.sizeCode}</td>
												<td>${size.sizeCategoryDesc}</td>
												<td><c:choose>
														<c:when test="${size.status == 1}">
															<span class="badge badge-primary">${size.statusDesc}</span>
														</c:when>
														<c:otherwise>
															<span class="badge badge-danger">${size.statusDesc}</span>
														</c:otherwise>
													</c:choose></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- size list -->
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
	function checkSizeSetup() {
		checkSizeSetupValid();
		if (errors == 0) {
			return true;
		}
		event.preventDefault();
	}

	function checkSizeSetupValid() {
		errors = 0;
		var nameErr = checkField("Name", $("#size_name").val(), true, null,
				null, null);

		var sizeCodeErr = checkField("Size Code", $("#size_code").val(), true,
				null, null, null);

		if (nameErr) {
			showError("size_name_data", "size_name", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("size_name_data", "size_name")
		}

		if (sizeCodeErr) {
			showError("size_code_data", "size_code", sizeCodeErr);
			errors = 1;
		} else {
			removeErrorMsg("size_code_data", "size_code")
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