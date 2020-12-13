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
					<h1 class="m-0 text-dark">Font</h1>
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
							<h3 class="card-title">Font Setup</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="card-body">

							<form:form role="form" id="font_setup_form"
								modelAttribute="fontDTO" action="font_setup.html" method="POST"
								enctype="multipart/form-data">
								<form:hidden path="seq" />

								<div class="row">
									<div class="col-md-6">
										<div class="form-group" id="font_name_data">
											<label for="name">Name</label>
											<form:input path="name" id="font_name"
												placeholder="Font Name" class="form-control" />
										</div>
										<div class="form-group" id="font_url_data">
											<label for="fontURL">Font URL</label>
											<form:input path="fontURL" id="font_url"
												placeholder="Font URL" class="form-control" />
										</div>
										<div class="form-group">
											<label for=exampleInputFile>File input</label>
											<div class="input-group">
												<div class="custom-file">
													<form:input path="fontImageFile" type="file"
														accept="image/x-png, image/jpeg" class="custom-file-input"
														id="exampleInputFile1" />
													<label class="custom-file-label" for="exampleInputFile">Choose
														file</label>
												</div>
												<div class="input-group-append">
													<span class="input-group-text" id="">Upload</span>
												</div>
											</div>
											<div class="image show" style="margin-top: 20px;">
												<c:if test="${not empty fontDTO.fontImage}">
													<img src="${images}${fontDTO.fontImage}" width="150px;"
														height="150px;" class="img-circle elevation-2"
														alt="Font Image">
												</c:if>
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group" id="font_sample_data">
											<label for="fontSample">Font Sample</label>
											<form:input path="fontSample" id="font_sample"
												placeholder="Font Sample" class="form-control" />
										</div>

										<c:if test="${fontDTO.seq > 0}">
											<div class="form-group">
												<label for="status">Status</label>
												<form:select class="form-control" path="status"
													id="status_id">
													<form:options items="${statusList}" itemValue="code"
														itemLabel="desc" />
												</form:select>
											</div>
										</c:if>

										<div class="form-group" id="font_description_data">
											<label for="fontDescription">Description</label>
											<form:textarea id="font_description" path="fontDescription"
												cols="20" rows="5" cssClass="form-control"
												placeholder="Description" />
										</div>

										<div class="form-group" style="text-align: right;">
											<button type="submit" class="btn btn-primary"
												onclick="checkFontSetup()">Save</button>
										</div>
									</div>
								</div>
							</form:form>

						</div>
						<!-- font setup -->

						<!-- font list -->
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
												<td>URL</td>
												<td>Sample</td>
												<td>Description</td>
												<td>Status</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${fontList}" var="font" varStatus="status">
												<tr>
													<td><a href="font_setup.html?fontId=${font.seq}">
															<i class="fas fa-edit"></i>
													</a></td>
													<td>${status.count}</td>
													<td>${font.name}</td>
													<td>${font.fontURL}</td>
													<td>${font.fontSample}</td>
													<td>${font.fontDescription}</td>
													<td><c:choose>
															<c:when test="${font.status == 1}">
																<span class="badge badge-primary">${font.statusDesc}</span>
															</c:when>
															<c:otherwise>
																<span class="badge badge-danger">${font.statusDesc}</span>
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<!-- font list -->
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
	function checkFontSetup() {
		checkValidFontSetup();
		if (errors == 0) {
			return true;
		}

		event.preventDefault();
	}

	function checkValidFontSetup() {
		errors = 0;

		var nameErr = checkField("Font Name", $("#font_name").val(), true,
				null, null, null);

		var urlErr = checkField("Font URL", $("#font_url").val(), true, null,
				null, null);

		var sampleErr = checkField("Font Sample", $("#font_sample").val(),
				true, null, null, null);

		if (nameErr) {
			showError("font_name_data", "font_name", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("font_name_data", "font_name");
		}

		if (urlErr) {
			showError("font_url_data", "font_url", urlErr);
			errors = 1;
		} else {
			removeErrorMsg("font_url_data", "font_url");
		}

		if (sampleErr) {
			showError("font_sample_data", "font_sample", sampleErr);
			errors = 1;
		} else {
			removeErrorMsg("font_sample_data", "font_sample");
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