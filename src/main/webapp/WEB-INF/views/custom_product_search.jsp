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
					<h1 class="m-0 text-dark">Custom Product Search</h1>
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

						<!-- custom product search -->
						<div class="card-body">
							<form:form role="form" id="custom_product_search"
								modelAttribute="searchCusPrdDTO"
								action="custom_product_search.html" method="POST">

								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="CategoryName">Category</label>
											<form:select class="form-control" path="categoryDTO.seq"
												id="category_id">
												<form:option value="-1">--- Please Select One ---</form:option>
												<form:options items="${categroyList}" itemValue="seq"
													itemLabel="name" />
											</form:select>
										</div>

										<div class="form-group">
											<label for="productName">Custom Product Name</label>
											<form:input path="productName" id="cus_prd_name"
												placeholder="Custom Product Name" class="form-control" />
										</div>
									</div>

									<div class="col-sm-6">
										<div class="form-group">
											<label for="subCategroy">Sub Category</label>
											<form:select class="form-control" path="subCategoryDTO.seq"
												id="sub_category_id">
												<form:option value="-1">--- Please Select One ---</form:option>
												<form:options items="${subCategroyList}" itemValue="seq"
													itemLabel="name" />
											</form:select>
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

										<div class="form-group"
											style="text-align: right; margin-top: 10px;">
											<button id="custom_product_search_btn" type="submit"
												style="margin-right: 10px;" class="btn btn-primary">Search</button>

											<button id="custom_product_search_clear" type="button"
												onclick="clearSearchData()" class="btn btn-default">Clear</button>
										</div>
									</div>
								</div>

							</form:form>
						</div>
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
							<!-- custom product list -->
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
													<td>Category Name</td>
													<td>Sub Category Name</td>
													<td>Product Name</td>
													<td>Price</td>
													<td>Status</td>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cusProductList}" var="cusPrd"
													varStatus="status">
													<tr>
														<td><a
															href="custom_product_setup.html?customPrdId=${cusPrd.seq}">
																<i class="fas fa-edit"></i>
														</a></td>
														<td>${status.count}</td>
														<td>${cusPrd.categoryDTO.name}</td>
														<td>${cusPrd.subCategoryDTO.name}</td>
														<td>${cusPrd.productName}</td>
														<td>${cusPrd.initialPrice}</td>
														<td>${prd.status}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- custom product list -->

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
</script>

<script>
	$("#category_id").on("change", function() {
		$.ajax({
			method : 'POST',
			contentType : 'application/json',
			url : 'subCategoryByCategoryAjax.html',
			dataType : 'json',
			async : true,
			data : $("#category_id").val(),
			success : function(data) {
				$('#sub_category_id').html('');
				$.each(data.data, function(i, cam) {
					$("#sub_category_id").append($("<option></option>", {
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

	function clearSearchData() {
		console.log("Clear....");
		$("#category_id").val(-1);
		$('#sub_category_id').html('');
		$("#sub_category_id").append($("<option></option>", {
			value : -1,
			text : "--- Please Select One ---"
		}))
		$("#status_id").val(-1);
		$("#cus_prd_name").val("");
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