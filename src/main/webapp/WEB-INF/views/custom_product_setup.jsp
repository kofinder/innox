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
					<h1 class="m-0 text-dark">Custom Product</h1>
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

								<li class="nav-item col-sm-4"><a
									class="nav-link ${ empty tab ? 'active' : '' }"
									id="custom-tabs-three-home-tab" data-toggle="pill"
									href="#custom_product_tag" role="tab"
									aria-controls="custom-tabs-three-home" aria-selected="false">Custom
										Product Setup</a></li>

								<c:if test="${customProductDTO.seq > 0}">
									<li class="nav-item col-sm-4"><a
										class="nav-link ${ tab eq 2 ? 'active' : '' }"
										id="custom-tabs-three-profile-tab" data-toggle="pill"
										href="#custom_item_tag" role="tab"
										aria-controls="custom-tabs-three-profile" aria-selected="true">
											Custom Item Setup</a></li>
								</c:if>

								<c:if test="${customItemDTO.seq > 0}">
									<li class="nav-item col-sm-4"><a
										class="nav-link ${ tab eq 3 ? 'active' : '' }"
										id="custom-tabs-three-profile-tab" data-toggle="pill"
										href="#custom_layout_tag" role="tab"
										aria-controls="custom-tabs-three-profile" aria-selected="true">
											Custom Layout Setup</a></li>
								</c:if>

							</ul>
						</div>

						<div class="card-body">

							<div class="tab-content" id="custom-tabs-three-tabContent">

								<!-- custom product -->
								<div class="tab-pane fade ${ empty tab ? 'active show' : '' }"
									id="custom_product_tag" role="tabpanel"
									aria-labelledby="custom-tabs-three-home-tab">

									<form:form role="form" id="custom_product_setup_form"
										modelAttribute="customProductDTO"
										action="custom_product_setup.html" method="POST"
										enctype="multipart/form-data">

										<form:hidden id="prdId" path="seq" />

										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label for="category">Category</label>
													<form:select class="form-control" path="categoryDTO.seq"
														id="category_id">
														<form:options items="${categroyList}" itemValue="seq"
															itemLabel="name" />
													</form:select>
												</div>

												<div class="form-group" id="cus_product_name_data">
													<label for="productName">Custom Product Name</label>
													<form:input path="productName" id="cus_product_name"
														placeholder="Product Name" class="form-control" />
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
														<c:if test="${not empty customProductDTO.imagePath}">
															<img src="${images}${customProductDTO.imagePath}"
																width="150px;" height="150px;"
																class="img-circle elevation-2" alt="Category Image">
														</c:if>
													</div>
												</div>
											</div>

											<div class="col-sm-6">
												<div class="form-group" id="sub_category_data">
													<label for="subCategroy">Sub Category</label>
													<form:select class="form-control" path="subCategoryDTO.seq"
														id="sub_category_id">
														<form:option value="-1">--- Please Select One ---</form:option>
														<form:options items="${subCategroyList}" itemValue="seq"
															itemLabel="name" />
													</form:select>
												</div>

												<div class="form-group" id="cus_product_price_data">
													<label for="initialPrice">Initial Price</label>
													<form:input path="initialPrice" id="cus_product_price"
														placeholder="Initial Price" class="form-control" />
												</div>

												<c:if test="${customProductDTO.seq > 0}">
													<div class="form-group">
														<label for="status">Status</label>
														<form:select class="form-control" path="status"
															id="status_id">
															<form:option value="-1">--- Please Select One ---</form:option>
															<form:options items="${statusList}" itemValue="code"
																itemLabel="desc" />
														</form:select>
													</div>
												</c:if>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-12">
												<!-- custom product save button -->
												<div class="form-group" style="text-align: right;">
													<button id="custom_product_save" type="submit"
														class="btn btn-primary"
														onclick="checkCustomProductSetup()">Save</button>
												</div>
											</div>
										</div>

									</form:form>

								</div>

								<!-- custom item -->
								<div class="tab-pane fade ${ tab eq 2 ? 'active show' : '' }"
									id="custom_item_tag" role="tabpanel"
									aria-labelledby="custom-tabs-three-profile-tab">

									<form:form role="form" id="custom_item_setup_form"
										modelAttribute="customItemDTO" action="custom_item_setup.html"
										method="POST" enctype="multipart/form-data">

										<form:hidden id="itemId" path="seq" />
										<input type="hidden" value="${customProductDTO.seq}"
											name="customProductId">

										<div class="row">
											<div class="col-sm-6">
												<div class="form-group" id="cus_item_code_data">
													<label for="itemCode">Item Code</label>
													<form:input path="itemCode" id="cus_item_code"
														placeholder="Item Code" class="form-control" />
												</div>

												<div class="form-group" id="cus_itme_price_data">
													<label for="itemPrice">Item Price</label>
													<form:input path="itemPrice" id="cus_item_price"
														placeholder="Item Price" class="form-control" />
												</div>

												<div class="form-group">
													<label for="color">Color</label>
													<form:select class="form-control" path="colorDTO.seq"
														id="color_id">
														<form:options items="${colorList}" itemValue="seq"
															itemLabel="colorName" />
													</form:select>
												</div>

												<div class="form-group">
													<label for="color">Item Size : </label>
													<form:checkboxes items="${itemSizeList}" path="cusItemSizeList"
														itemValue="seq" itemLabel="sizeName"
														cssStyle="font-weight: normal;" />
												</div>
											</div>

											<div class="col-sm-6">

												<div class="form-group" id="cus_item_name_data">
													<label for="itemName">Custom Item Name</label>
													<form:input path="itemName" id="cus_item_name"
														placeholder="Item Name" class="form-control" />
												</div>

												<div class="form-group" id="cus_itme_sequence_data">
													<label for="sequenceNo">Sequence No</label>
													<form:input path="sequenceNo" id="cus_item_sequence"
														placeholder="Sequence No" class="form-control" />
												</div>

												<div class="form-group">
													<label for="sizeCategory">Size Category</label>
													<form:select class="form-control" path="sizeCategoryId"
														id="size_category_id">
														<form:options items="${sizeCategoryList}" itemValue="code"
															itemLabel="desc" />
													</form:select>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-12">
												<!-- custom item save button -->
												<div class="form-group" style="text-align: right;">
													<button id="custom_item_setup" type="submit"
														class="btn btn-primary" onclick="checkCustomItemSetup()">Save</button>
												</div>
											</div>
										</div>

									</form:form>


									<div class="card card-default" style="margin-top: 20px;">

										<div class="card-header" style="padding-bottom: 0px;">
											<h3 class="card-title">Custom Item List</h3>

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
															<td>Price</td>
															<td>Color</td>
															<td>Sequence</td>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${customItemList}" var="cusItem"
															varStatus="status">
															<tr>
																<td><a
																	href="custom_product_setup?customPrdId=${customProductDTO.seq}&customItemId=${cusItem.seq}">
																		<i class="fas fa-edit"></i>
																</a></td>
																<td>${status.count}</td>
																<td>${cusItem.itemName}</td>
																<td>${cusItem.itemCode}</td>
																<td>${cusItem.itemPrice}</td>
																<td>${cusItem.colorDTO.colorName}</td>
																<td>${cusItem.sequenceNo}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>

								</div>

								<!-- custom item layout -->
								<div class="tab-pane fade ${ tab eq 3 ? 'active show' : '' }"
									id="custom_layout_tag" role="tabpanel"
									aria-labelledby="custom-tabs-three-profile-tab">

									<form:form role="form" id="custom_layout_form"
										modelAttribute="customItemLayoutDTO"
										action="custom_layout_setup.html" method="POST"
										enctype="multipart/form-data">

										<form:hidden id="layoutId" path="seq" />

										<input type="hidden" value="${customProductDTO.seq}"
											name="customProductId">
										<input type="hidden" value="${customItemDTO.seq}"
											name="customItemId">

										<div class="row">
											<div class="col-sm-6">
												<div class="form-group" id="cus_layout_name_data">
													<label for="layoutName">Layout Name</label>
													<form:input path="layoutName" id="cus_layout_name"
														placeholder="Layout Name" class="form-control" />
												</div>

												<div class="form-group">
													<label for=exampleInputFile>File input</label>
													<div class="input-group">
														<div class="custom-file">
															<form:input path="layoutImageFile" type="file"
																accept="image/x-png, image/jpeg"
																class="custom-file-input" id="exampleInputFile2" />
															<label class="custom-file-label" for="exampleInputFile">Choose
																file</label>
														</div>
														<div class="input-group-append">
															<span class="input-group-text" id="">Upload</span>
														</div>
													</div>
													<div class="image show" style="margin-top: 20px;">
														<c:if test="${not empty CustomItemLayoutDTO.layoutImage}">
															<img src="${images}${CustomItemLayoutDTO.layoutImage}"
																width="150px;" height="150px;"
																class="img-circle elevation-2" alt="Category Image">
														</c:if>
													</div>
												</div>

												<c:if test="${customItemLayoutDTO.seq > 0}">
													<div class="form-group">
														<label for="status">Status</label>
														<form:select class="form-control" path="status"
															id="layout_status_id">
															<form:options items="${statusList}" itemValue="code"
																itemLabel="desc" />
														</form:select>
													</div>
												</c:if>
											</div>

											<div class="col-sm-6">
												<div class="form-group" id="cus_layout_price_data">
													<label for="layoutPrice">Layout Price</label>
													<form:input path="layoutPrice" id="cus_layout_price"
														placeholder="Layout Price" class="form-control" />
												</div>

												<div class="form-group" id="cus_layout_sequence_data">
													<label for="sequenceNo">Layout Sequence</label>
													<form:input path="sequenceNo" id="cus_layout_sequence"
														placeholder="Layout Sequence No" class="form-control" />
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-sm-12">
												<!-- custom item layout save button -->
												<div class="form-group" style="text-align: right;">
													<button id="custom_layout_setup" type="submit"
														class="btn btn-primary" onclick="checkCustomLayoutSetup()">Save</button>
												</div>
											</div>
										</div>

									</form:form>

									<!-- custom item layout list -->
									<div class="card card-default" style="margin-top: 20px;">

										<div class="card-header" style="padding-bottom: 0px;">
											<h3 class="card-title">Custom Item Layout List</h3>

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
															<td>Price</td>
															<td>Sequence No</td>
															<td>Status</td>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${customItemLayoutList}"
															var="cusItemLayout" varStatus="status">
															<tr>
																<td><a
																	href="custom_product_setup?customPrdId=${customProductDTO.seq}
																&customItemId=${customItemDTO.seq}&customLayoutId=${cusItemLayout.seq}">
																		<i class="fas fa-edit"></i>
																</a></td>
																<td>${status.count}</td>
																<td>${cusItemLayout.layoutName}</td>
																<td>${cusItemLayout.layoutPrice}</td>
																<td>${cusItemLayout.sequenceNo}</td>
																<td>${cusItemLayout.statusDesc}</td>
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
	// custom product setup
	function checkCustomProductSetup() {
		checkCustomProductSetupValid();
		if (errors == 0) {
			return true;
		}
		event.preventDefault();
	}

	// custom item setup
	function checkCustomItemSetup() {
		checkCustomItemSetupValid();
		if (itemErrors == 0) {
			return true;
		}
		event.preventDefault();
	}

	// custom layout setup
	function checkCustomLayoutSetup() {
		checkCustomLayoutSetupValid();
		if (layoutErrors == 0) {
			return true;
		}
		event.preventDefault();
	}

	function checkCustomProductSetupValid() {
		errors = 0;

		var cusPrdNameErr = checkField("Custom Product Name", $(
				"#cus_product_name").val(), true, null, null, null);

		var sucSubCategoryErr = checkField("Sub Category", $(
				"#sub_category_id option:selected").val(), true, null, null,
				's');

		var cusPriceErr = checkField("Custom Product Price", $(
				"#cus_product_price").val(), true, null, null, "n");

		if (cusPrdNameErr) {
			showError("cus_product_name_data", "cus_product_name",
					cusPrdNameErr);
			errors = 1;
		} else {
			removeErrorMsg("cus_product_name_data", "cus_product_name");
		}

		if (sucSubCategoryErr) {
			showError("sub_category_data", "sub_category_id", sucSubCategoryErr);
			errors = 1;
		} else {
			removeErrorMsg("sub_category_data", "sub_category_id")
		}

		if (cusPriceErr) {
			showError("cus_product_price_data", "cus_product_price",
					cusPriceErr);
			errors = 1;
		} else {
			removeErrorMsg("cus_product_price_data", "cus_product_price");
		}
	}

	function checkCustomItemSetupValid() {
		itemErrors = 0;

		var cusItemCodeErr = checkField("Custom Item Code", $("#cus_item_code")
				.val(), true, null, null, null);

		var cusItemNameErr = checkField("Custom Item Name", $("#cus_item_name")
				.val(), true, null, null, null);

		var cusItemPriceErr = checkField("Custom Item Price", $(
				"#cus_item_price").val(), true, null, null, "n");

		var cusItemSeqErr = checkField("Custom Item Sequence", $(
				"#cus_item_sequence").val(), true, null, null, "n");

		if (cusItemCodeErr) {
			showError("cus_item_code_data", "cus_item_code", cusItemCodeErr);
			itemErrors = 1;
		} else {
			removeErrorMsg("cus_item_code_data", "cus_item_code");
		}

		if (cusItemNameErr) {
			showError("cus_item_name_data", "cus_item_name", cusItemNameErr);
			itemErrors = 1;
		} else {
			removeErrorMsg("cus_item_name_data", "cus_item_name");
		}

		if (cusItemPriceErr) {
			showError("cus_itme_price_data", "cus_item_price", cusItemPriceErr);
			itemErrors = 1;
		} else {
			removeErrorMsg("cus_itme_price_data", "cus_item_price");
		}

		if (cusItemSeqErr) {
			showError("cus_itme_sequence_data", "cus_item_sequence",
					cusItemSeqErr);
			itemErrors = 1;
		} else {
			removeErrorMsg("cus_itme_sequence_data", "cus_item_sequence");
		}
	}

	function checkCustomLayoutSetupValid() {
		layoutErrors = 0;

		var layoutNameErr = checkField("Custom Layout", $("#cus_layout_price")
				.val(), true, null, null, null);

		var layoutPriceErr = checkField("Custom Layout Price", $(
				"#cus_layout_price").val(), true, null, null, "n");

		var layoutSequenceErr = checkField("Custom Layout Sequence", $(
				"#cus_layout_sequence").val(), true, null, null, "n");

		if (layoutNameErr) {
			showError("cus_layout_name_data", "cus_layout_name", layoutNameErr);
			layoutErrors = 1;
		} else {
			removeErrorMsg("cus_layout_name_data", "cus_layout_name");
		}

		if (layoutPriceErr) {
			showError("cus_layout_price_data", "cus_layout_price",
					layoutPriceErr);
			layoutErrors = 1;
		} else {
			removeErrorMsg("cus_layout_price_data", "cus_layout_price");
		}

		if (layoutSequenceErr) {
			showError("cus_layout_sequence_data", "cus_layout_sequence",
					layoutSequenceErr);
			layoutErrors = 1;
		} else {
			removeErrorMsg("cus_layout_sequence_data", "cus_layout_sequence");
		}
	}

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