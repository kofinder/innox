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
					<h1 class="m-0 text-dark">Announcement</h1>
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
					<div class="card card-primary card-outline card-outline-tabs">

						<div class="card-header p-0 pt-1 border-bottom-0">
							<ul class="nav nav-tabs" id="custom-tabs-two-tab" role="tablist">

								<c:choose>
									<c:when test="${categoryDTO.seq > 0}">
										<li class="nav-item col-sm-6"><a
											class="nav-link ${ empty tab ? 'active' : '' }"
											id="custom-tabs-three-home-tab" data-toggle="pill"
											href="#category-tab" role="tab"
											aria-controls="custom-tabs-three-home" aria-selected="false">Category
												Setup</a></li>

										<li class="nav-item col-sm-6"><a
											class="nav-link ${ tab eq 2 ? 'active' : '' }"
											id="custom-tabs-three-profile-tab" data-toggle="pill"
											href="#sub_category_tab" role="tab"
											aria-controls="custom-tabs-three-profile"
											aria-selected="true">Sub Category Setup</a></li>
									</c:when>

									<c:otherwise>
										<li class="nav-item col-sm-12"><a
											class="nav-link ${ empty tab ? 'active' : '' }"
											id="custom-tabs-three-home-tab" data-toggle="pill"
											href="#category-tab" role="tab"
											aria-controls="custom-tabs-three-home" aria-selected="false">Category
												Setup</a></li>
									</c:otherwise>
								</c:choose>

							</ul>
						</div>

						<div class="card-body">

							<div class="tab-content" id="custom-tabs-three-tabContent">

								<!-- category -->
								<div class="tab-pane fade ${ empty tab ? 'active show' : '' }"
									id="category-tab" role="tabpanel"
									aria-labelledby="custom-tabs-three-home-tab">

									<form:form role="form" id="announcement_setup_form"
										modelAttribute="announcementDTO"
										action="announcement_setup.html" method="POST"
										enctype="multipart/form-data">

										<form:hidden path="seq" />
										<div class="row">
											<div class="col-md-6">
												<div class="form-group" id="title_data">
													<label for="categoryName">Title</label>
													<form:input path="title" id="title" placeholder="Title"
														class="form-control" />
												</div>

												<div class="form-group">
													<label for="status">Status</label>
													<form:select class="form-control" path="status"
														id="status_id">
														<form:options items="${statusList}" itemValue="code"
															itemLabel="desc" />
													</form:select>
												</div>

												<div class="form-group">
													<label for=exampleInputFile>Summary Image</label>
													<div class="input-group">
														<div class="custom-file">
															<form:input path="summaryImageFile" type="file"
																accept="image/x-png, image/jpeg"
																class="custom-file-input" id="summaryImageFile" />
															<label class="custom-file-label" for="summaryImageFile">Choose
																file</label>
														</div>
														<div class="input-group-append">
															<span class="input-group-text" id="">Upload</span>
														</div>
													</div>
													<div class="image show" style="margin-top: 20px;">
														<c:if test="${not empty announcementDTO.summaryImage}">
															<img src="${images}${announcementDTO.summaryImage}"
																width="150px;" height="150px;"
																class="img-circle elevation-2" alt="Category Image">
														</c:if>
													</div>
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group" id="description_data">
													<label for="sequenceNo">Description</label>
													<form:input path="description" id="description"
														placeholder="Description" class="form-control" />
												</div>

												<div class="form-group">
													<label for="status">Noti Type</label>
													<form:select class="form-control" path="notiType"
														id="status_id">
														<form:options items="${notiTypeList}" itemValue="code"
															itemLabel="description" />
													</form:select>
												</div>

												<div class="form-group">
													<label for=exampleInputFile>Detail Image</label>
													<div class="input-group">
														<div class="custom-file">
															<form:input path="detailImageFile" type="file"
																accept="image/x-png, image/jpeg"
																class="custom-file-input" id="detailImageFile" />
															<label class="custom-file-label" for="detailImageFile">Choose
																file</label>
														</div>
														<div class="input-group-append">
															<span class="input-group-text" id="">Upload</span>
														</div>
													</div>
													<div class="image show" style="margin-top: 20px;">
														<c:if test="${not empty announcementDTO.detailImage}">
															<img src="${images}${announcementDTO.detailImage}"
																width="150px;" height="150px;"
																class="img-circle elevation-2" alt="Category Image">
														</c:if>
													</div>
												</div>

												<div class="form-group" style="text-align: right;">
													<button id="announcement_save" type="submit"
														class="btn btn-primary" onclick="checkAnnouncementSetup()">Save</button>
												</div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
						<!-- /.card -->
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
	function checkAnnouncementSetup() {
		checkAnnouncementValid();
		if (errors == 0) {
			return true;
		}
		event.preventDefault();
	}

	function checkAnnouncementValid() {
		errors = 0;
		var nameErr = checkField("Name", $("#title").val(), true, null, null,
				null);

		var descriptionErr = checkField("Description", $("#description").val(),
				true, null, null, null);

		if (nameErr) {
			showError("title_data", "title", nameErr);
			errors = 1;
		} else {
			removeErrorMsg("title_data", "title");
		}

		if (descriptionErr) {
			showError("description_data", "description", descriptionErr);
			errors = 1;
		} else {
			removeErrorMsg("description_data", "description");
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