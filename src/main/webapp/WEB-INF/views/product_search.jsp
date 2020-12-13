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
					<h1 class="m-0 text-dark">Product Search</h1>
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
					<div class="card card-default">
						<div class="card-header">
							<h3 class="card-title">Search</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="card-body">
							<!-- product search-->
							<form:form role="form" id="product_search_form"
								modelAttribute="searchProductDTO" action="product_search.html"
								method="POST" enctype="multipart/form-data">
								<form:hidden path="seq" />
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="productName">Name</label>
											<form:input path="name" id="product_name"
												placeholder="Product Name" class="form-control" />
										</div>

										<div class="form-group">
											<label for="brand">Brand</label>
											<form:select class="form-control" path="brandDTO.seq"
												id="brand_id">
												<form:option value="-1">--- Please Select One ---</form:option>
												<form:options items="${brandList}" itemValue="seq"
													itemLabel="name" />
											</form:select>
										</div>
										<div class="form-group">
											<label for="category">Category</label>
											<form:select class="form-control" path="categoryDTO.seq"
												id="category_id">
												<form:option value="-1">--- Please Select One ---</form:option>
												<form:options items="${categroyList}" itemValue="seq"
													itemLabel="name" />
											</form:select>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="codeNumber">Product Code</label>
											<form:input path="codeNumber" id="product_no"
												placeholder="Product No" class="form-control" />
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

										<div class="form-group" id="sub_category_data">
											<label for="subCategroy">Sub Category</label>
											<form:select class="form-control" path="subCategoryDTO.seq"
												id="sub_category_id">
												<%-- <form:option value="-1">--- Please Select One ---</form:option> --%>
												<form:option value="-1">--- Please Select One ---</form:option>
												<form:options items="${subCategroyList}" itemValue="seq"
													itemLabel="name" />
											</form:select>
										</div>

										<div class="form-group"
											style="text-align: right; margin-top: 55px;">
											<button id="product_s" type="submit"
												style="margin-right: 10px;" class="btn btn-primary">Search</button>

											<button id="product_search_clear" type="button"
												class="btn btn-default">Clear</button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
						<!-- /.card -->
					</div>

					<div class="card card-default">

						<div class="card-header">
							<h3 class="card-title">Product List</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="card-body">
							<!-- category list -->
							<div class="row">

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
													<td>Price</td>
													<td>Qty</td>
													<td>Brand</td>
													<td>Category</td>
													<td>Sub Catrgory</td>
													<td>Status</td>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${productList}" var="prd"
													varStatus="status">
													<tr>
														<td><a href="product_setup.html?prdId=${prd.seq}">
																<i class="fas fa-edit"></i>
														</a></td>
														<td>${status.count}</td>
														<td>${prd.name}</td>
														<td>${prd.codeNumber}</td>
														<td>${prd.priceDesc}</td>
														<td>${prd.quantity}</td>
														<td>${prd.brandDTO.name}</td>
														<td>${prd.categoryDTO.name}</td>
														<td>${prd.subCategoryDTO.name}</td>
														<td><c:choose>
																<c:when test="${prd.status == 1}">
																	<span class="badge badge-primary">${prd.statusDesc}</span>
																</c:when>
																<c:otherwise>
																	<span class="badge badge-danger">${prd.statusDesc}</span>
																</c:otherwise>
															</c:choose></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<!-- category list -->
							</div>
						</div>
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

	/* $("#category-search-clear").click(function() {
		$("#category_name").val('');
		$("#status_id").val('-1');
		$("#sequence_no").val('0');
	}); */
</script>

<script>
	$(function() {
		$("#example1").DataTable({
			"responsive" : true,
			"autoWidth" : false,
		});
	});

	$("#category_id")
			.on(
					"change",
					function() {
						$
								.ajax({
									method : 'POST',
									contentType : 'application/json',
									url : 'subCategoryByCategoryAjax.html',
									dataType : 'json',
									async : true,
									data : $("#category_id").val(),
									success : function(data) {
										$('#sub_category_id').html('');
										var selectOneBox = '<option value="-1">--- Please Select One ---</option>';
										$("#sub_category_id").append(
												selectOneBox);
										$.each(data.data, function(i, cam) {
											$("#sub_category_id").append(
													$("<option></option>", {
														value : cam.seq,
														text : cam.name
													}))
										});
									},
									error : function(e) {
										console.log("ERROR >>  ", e);
									}
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