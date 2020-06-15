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
					<h1 class="m-0 text-dark">Category</h1>
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

									<form:form role="form" id="category_setup_form"
										modelAttribute="categoryDTO" action="category_setup.html"
										method="POST" enctype="multipart/form-data">
										
										<form:hidden path="seq" />
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="categoryName">Category Name</label>
													<form:input path="name" id="category_name"
														placeholder="Category Name" class="form-control" />
												</div>

												<div class="form-group">
													<label for="status">Status</label>
													<form:select class="form-control" path="status"
														id="status_id">
														<form:option value="-1">--- Please Select One ---</form:option>
														<form:options items="${statusList}" itemValue="code"
															itemLabel="desc" />
													</form:select>
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label for="sequenceNo">Sequence No</label>
													<form:input path="sequenceNo" id="sequence_no"
														placeholder="Sequence No" class="form-control" />
												</div>

												<div class="form-group">
													<label for=exampleInputFile>File input</label>
													<div class="input-group">
														<div class="custom-file">
															<form:input path="imageFile" type="file"
																accept="image/x-png, image/jpeg"
																class="custom-file-input" id="exampleInputFile1" />
															<label class="custom-file-label" for="exampleInputFile">Choose
																file</label>
														</div>
														<div class="input-group-append">
															<span class="input-group-text" id="">Upload</span>
														</div>
													</div>
													<div class="image show" style="margin-top: 20px;">
														<c:if test="${not empty categoryDTO.imagePath}">
															<img src="${images}${categoryDTO.imagePath}" width="150px;"
																height="150px;" class="img-circle elevation-2"
																alt="Category Image">
														</c:if>
													</div>
												</div>

												<div class="form-group" style="text-align: right;">
													<button id="category-save" type="submit"
														class="btn btn-primary">Save</button>
												</div>
											</div>
										</div>
									</form:form>


									<!-- category list -->
									<%-- <div class="row" style="margin-top: 15px;">
										<div class="col-12">
											<div class="card">
												<div class="card-header">
													<h3 class="card-title">Category List</h3>
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
																	<td>Sequence</td>
																	<td>Status</td>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${categoryList}" var="cat"
																	varStatus="status">
																	<tr>
																		<td><a
																			href="category_setup.html?categoryId=${cat.seq}">
																				<i class="fas fa-edit"></i>
																		</a></td>
																		<td>${status.count}</td>
																		<td>${cat.name}</td>
																		<td>${cat.sequenceNo}</td>
																		<td>${cat.status}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
										<!-- category list -->
									</div> --%>
								</div>

								<!-- sub category -->

								<div class="tab-pane fade ${ tab eq 2 ? 'active show' : '' }"
									id="sub_category_tab" role="tabpanel"
									aria-labelledby="custom-tabs-three-profile-tab">


									<div class="row">
										<div class="col-12">

											<!-- sub category setup -->
											<div class="card card-default">
												<div class="card-header" style="padding-bottom: 0px;">
													<h3 class="card-title">Sub Category Setup</h3>

													<div class="card-tools">
														<button type="button" class="btn btn-tool"
															data-card-widget="collapse">
															<i class="fas fa-minus"></i>
														</button>
													</div>
												</div>


												<div class="card-body">
													<form:form role="form" id="sub_category_setup_form"
														modelAttribute="subCategoryDTO"
														action="sub_category_setup.html" method="POST"
														enctype="multipart/form-data">
														<form:hidden id="subSeq" path="seq" />
														<input type="hidden" value="${categoryDTO.seq}" name="categorySeq">

														<div class="form-row">
															<div class="col-md-6">
																<div class="form-group">
																	<label for="categoryName"> Name</label>
																	<form:input path="name" id="sub_category_name"
																		placeholder="Category Name" class="form-control" />
																</div>

																<div class="form-group">
																	<label for="status">Status</label>
																	<form:select class="form-control" path="status"
																		id="sub_status_id">
																		<form:option value="-1">--- Please Select One ---</form:option>
																		<form:options items="${statusList}" itemValue="code"
																			itemLabel="desc" />
																	</form:select>
																</div>
															</div>

															<div class="col-md-6">
																<div class="form-group">
																	<label for="sequenceNo">Sequence No</label>
																	<form:input path="sequenceNo" id="sub_sequence_no"
																		placeholder="Sequence No" class="form-control" />
																</div>

																<div class="form-group">
																	<label for=exampleInputFile>File input</label>
																	<div class="input-group">
																		<div class="custom-file">
																			<form:input path="imageFile" type="file"
																				accept="image/x-png, image/jpeg"
																				class="custom-file-input" id="exampleInputFile2" />
																			<label class="custom-file-label"
																				for="exampleInputFile">Choose file</label>
																		</div>
																		<div class="input-group-append">
																			<span class="input-group-text" id="">Upload</span>
																		</div>
																	</div>
																	<div class="image show" style="margin-top: 20px;">
																		<c:if test="${not empty subCategoryDTO.imagePath}">
																			<img src="${images}${subCategoryDTO.imagePath}" width="150px;"
																				height="150px;" class="img-circle elevation-2"
																				alt="Sub Category Image">
																		</c:if>
																	</div>
																</div>

																<div class="form-group" style="text-align: right;">
																	<button id="sub_category_save" type="submit"
																		class="btn btn-primary">Save</button>
																</div>
															</div>

														</div>
													</form:form>
												</div>
											</div>
											<!-- sub category setup -->



											<!-- sub category list -->
											<div class="card card-default">

												<div class="card-header" style="padding-bottom: 0px;">
													<h3 class="card-title">Sub Category List</h3>

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
																	<td>Sequence</td>
																	<td>Status</td>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${subCategoryList}" var="sub"
																	varStatus="status">
																	<tr>
																		<td><a
																			href="category_setup.html?catId=${categoryDTO.seq}&subCatId=${sub.seq}"> <i
																				class="fas fa-edit"></i>
																		</a></td>
																		<td>${status.count}</td>
																		<td>${sub.name}</td>
																		<td>${sub.sequenceNo}</td>
																		<td>${sub.status}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
												<!-- sub category list -->
											</div>

										</div>
										<!-- row end -->
									</div>


								</div>

							</div>
						</div>
						<!-- /.card -->
					</div>
				</div>
			</div>

			<!-- category list -->


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
<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
	<!-- Control sidebar content goes here -->
	<div class="p-3">
		<h5>Title</h5>
		<p>Sidebar content</p>
	</div>
</aside>
<!-- /.control-sidebar -->