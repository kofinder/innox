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
	
  <!-- summernote -->
  <link rel="stylesheet" href="resources/plugins/summernote/summernote-bs4.css">

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
					<h1 class="m-0 text-dark">Product</h1>
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

			<!-- banner setup -->
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Product Setup</h3>

					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
					</div>
				</div>

				<div class="card-body">

					<form:form role="form" id="product_setup_form"
						modelAttribute="productDTO" action="product_setup.html"
						method="POST" enctype="multipart/form-data">
						<form:hidden path="seq" />

						<div class="row">
							<div class="col-md-6">
								<div class="form-group" id="prd_name_date">
									<label for="productName">Product Name</label>
									<form:input path="name" id="product_name"
										placeholder="Product Name" class="form-control" />
								</div>

								<div class="form-group">
									<label for="brand">Brand</label>
									<form:select class="form-control" path="brandDTO.seq"
										id="brand_id">
										<%-- 	<form:option value="-1">--- Please Select One ---</form:option> --%>
										<form:options items="${brandList}" itemValue="seq"
											itemLabel="name" />
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

								<div class="form-group">
									<label for="popular">Popular</label>
									<div class="custom-control custom-radio">
										<form:radiobutton class="custom-control-input"
											id="popular_radio1" path="popular" value="true" />
										<label for="popular_radio1" class="custom-control-label">Yes</label>
									</div>
									<div class="custom-control custom-radio">
										<form:radiobutton class="custom-control-input"
											id="popular_radio2" path="popular" value="false" />
										<!-- <input class="custom-control-input" type="radio"
											id="popular_radio2" name="popular"
											value="false"> -->
										<label for="popular_radio2" class="custom-control-label">No</label>
									</div>
								</div>

								<div class="form-group" id="quantity_data">
									<label for="quantity">Quantity</label>
									<form:input path="quantity" id="quantity_id"
										placeholder="Quantity" class="form-control" />
								</div>

								<div class="form-group" id="size_data">
									<label for="size">Size : </label>
									<form:checkboxes items="${sizeList}" path="prdSizeList"
										itemValue="seq" itemLabel="sizeName"
										cssStyle="font-weight: normal;" />
									<%-- <form:input path="size" id="size_id" placeholder="Prodcut Size"
										class="form-control" />
									<p class="text-sm mb-0">Example : S,M,XL...</p> --%>
								</div>

								<div class="form-group">
									<label for=product_image_1_2>Product Images</label>
									<div class="row">
										<div class="col-sm-6">
											<div class="input-group">
												<div class="custom-file">
													<form:input path="imageFile1" type="file"
														accept="image/x-png, image/jpeg" class="custom-file-input"
														id="imageFile1" />
													<label class="custom-file-label" for="imageFile1">
														Image 1</label>
												</div>
											</div>
											<%-- <div class="image show" style="margin-top: 20px;">
												<c:if test="${not empty productDTO.imagePath1}">
													<img src="${images}${productDTO.imagePath1}" width="150px;"
														height="150px;" class="img-circle elevation-2"
														alt="Product Image1">
												</c:if>
											</div> --%>
										</div>

										<div class="col-sm-6">
											<div class="input-group">
												<div class="custom-file">
													<form:input path="imageFile2" type="file"
														accept="image/x-png, image/jpeg" class="custom-file-input"
														id="imageFile2" />
													<label class="custom-file-label" for="imageFile2">
														Image 2</label>
												</div>
											</div>
											<%-- <div class="image show" style="margin-top: 20px;">
												<c:if test="${not empty productDTO.imagePath2}">
													<img src="${images}${productDTO.imagePath2}" width="150px;"
														height="150px;" class="img-circle elevation-2"
														alt="Product Image1">
												</c:if>
											</div> --%>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="overview">High Light</label>
									<form:textarea path="overview" rows="4" class="form-control textarea"
										id="overview_id" aria-required="true" wrap="hard"></form:textarea>
									<p class="text-sm mb-0">Write some high lights about your
										product !</p>
								</div>

								<!-- display if product edit -->
								<c:if test="${productDTO.seq > 0}">
									<div class="form-group">
										<label for="status">Status</label>
										<form:select class="form-control" path="status" id="status_id">
											<form:options items="${statusList}" itemValue="code"
												itemLabel="desc" />
										</form:select>
									</div>
								</c:if>

							</div>
							<!-- end of col-md-6 -->

							<div class="col-md-6">
								<div class="form-group" id="prd_code_data">
									<label for="codeNumber">Product Code</label>
									<form:input path="codeNumber" id="product_code"
										placeholder="Product Code" class="form-control" />
								</div>

								<div class="form-group">
									<label for="category">Category</label>
									<form:select class="form-control" path="categoryDTO.seq"
										id="category_id">
										<%-- <form:option value="-1">--- Please Select One ---</form:option> --%>
										<form:options items="${categroyList}" itemValue="seq"
											itemLabel="name" />
									</form:select>
								</div>

								<div class="form-group" id="price_data">
									<label for="price">Price</label>
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">$</span>
										</div>
										<form:input path="price" id="price_id" placeholder="Price"
											class="form-control" data-ms-editor="true" spellcheck="false" />
										<div class="input-group-append">
											<span class="input-group-text">.00</span>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="promotion">Promotion</label>
									<div class="custom-control custom-radio">
										<form:radiobutton class="custom-control-input"
											id="promotion_radio1" path="promotion" value="true" />
										<label for="promotion_radio1" class="custom-control-label"
											style="font-weight: normal">Yes</label>
									</div>
									<div class="custom-control custom-radio">
										<form:radiobutton class="custom-control-input"
											id="promotion_radio2" path="promotion" value="false" />
										<label for="promotion_radio2" class="custom-control-label"
											style="font-weight: normal">No</label>
									</div>
								</div>


								<div class="form-group" id="original_price_data">
									<label for="originalPrice">Original Price</label>
									<div class="row">
										<div class="col-sm-8">
											<form:input path="originalPrice" id="original_price_id"
												placeholder="Original Price" class="form-control" />
										</div>
										<div class="col-sm-4">
											<div class="input-group" id="discount_data">
												<form:input path="discountPercent" id="discount_percent_id"
													placeholder="Discount Percent" class="form-control"
													spellcheck="false" data-ms-editor="true" />
												<div class="input-group-append">
													<span class="input-group-text">%</span>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group clearfix" id="color_data"
									style="margin-top: 10px;">
									<label for="color">Color : </label>
									<div class="icheck-primary d-inline"
										style="font-weight: normal;">
										<form:checkboxes items="${colorList}" path="prdColorList"
											itemValue="seq" itemLabel="colorName"
											cssStyle="font-weight: normal;" />
									</div>
								</div>

								<div class="form-group">
									<label for=product_image_3_4>Product Images</label>
									<div class="row">
										<div class="col-sm-6">
											<div class="input-group">
												<div class="custom-file">
													<form:input path="imageFile3" type="file"
														accept="image/x-png, image/jpeg" class="custom-file-input"
														id="imageFile3" />
													<label class="custom-file-label" for="imageFile3">
														Image 3</label>
												</div>
											</div>
											<%-- <div class="image show" style="margin-top: 20px;">
												<c:if test="${not empty productDTO.imagePath3}">
													<img src="${images}${productDTO.imagePath3}" width="150px;"
														height="150px;" class="img-circle elevation-2"
														alt="Product Image3">
												</c:if>
											</div> --%>
										</div>

										<div class="col-sm-6">
											<div class="input-group">
												<div class="custom-file">
													<form:input path="imageFile4" type="file"
														accept="image/x-png, image/jpeg" class="custom-file-input"
														id="imageFile4" />
													<label class="custom-file-label" for="imageFile2">
														Image 4</label>
												</div>
											</div>
											<%-- <div class="image show" style="margin-top: 20px;">
												<c:if test="${not empty productDTO.imagePath4}">
													<img src="${images}${productDTO.imagePath4}" width="150px;"
														height="150px;" class="img-circle elevation-2"
														alt="Product Image4">
												</c:if>
											</div> --%>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="detail">Detail</label>
									<form:textarea path="detail" class="form-control textarea"
										id="detail_id" aria-required="true" wrap="hard"></form:textarea>
									<p class="text-sm mb-0">Write some detail about your
										product !</p>
								</div>

							</div>
						</div>

						<%-- <div class="row">
							<div class="col-sm-6">
								<!-- display if product edit -->
								<c:if test="${productDTO.seq > 0}">
									<div class="form-group">
										<label for="status">Status</label>
										<form:select class="form-control" path="status" id="status_id">
											<form:option value="-1">--- Please Select One ---</form:option>
											<form:options items="${statusList}" itemValue="code"
												itemLabel="desc" />
										</form:select>
									</div>
								</c:if>
							</div>
							<div class="col-sm-6"></div>
						</div> --%>

						<div class="row">
							<!-- product image list -->
							<div class="col-sm-3">
								<c:if test="${not empty productDTO.imagePath1}">
									<div class="form-group">
										<label for=exampleInputFile>Image 1</label>
										<div class="image show" style="margin-top: 20px;">
											<img src="${images}${productDTO.imagePath1}" width="150px;"
												height="150px;" class="img-circle elevation-2"
												alt="Product Image">
										</div>
									</div>
								</c:if>
							</div>

							<div class="col-sm-3">
								<c:if test="${not empty productDTO.imagePath2}">
									<div class="form-group">
										<label for=exampleInputFile>Image 2</label>
										<div class="image show" style="margin-top: 20px;">
											<img src="${images}${productDTO.imagePath2}" width="150px;"
												height="150px;" class="img-circle elevation-2"
												alt="Product Image">

										</div>
									</div>
								</c:if>
							</div>

							<div class="col-sm-3">
								<c:if test="${not empty productDTO.imagePath3}">
									<div class="form-group">
										<label for=exampleInputFile>Image 3</label>
										<div class="image show" style="margin-top: 20px;">
											<img src="${images}${productDTO.imagePath3}" width="150px;"
												height="150px;" class="img-circle elevation-2"
												alt="Product Image">
										</div>
									</div>
								</c:if>
							</div>

							<div class="col-sm-3">
								<c:if test="${not empty productDTO.imagePath4}">
									<div class="form-group">
										<label for=exampleInputFile>Image 4</label>
										<div class="image show" style="margin-top: 20px;">
											<img src="${images}${productDTO.imagePath4}" width="150px;"
												height="150px;" class="img-circle elevation-2"
												alt="Product Image">
										</div>
									</div>
								</c:if>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12">
								<!-- product save button -->
								<div class="form-group" style="text-align: right;">
									<button id="product_save" type="submit" class="btn btn-primary"
										onclick="checkProductSetup()">Save</button>
								</div>
							</div>
						</div>
					</form:form>
					<!-- /.card-body -->
				</div>
				<!-- card -->
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

<!-- Summernote -->
<script src="resources/plugins/summernote/summernote-bs4.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		bsCustomFileInput.init();
		/* $("#icheck-primary").find("label")
		css("margin-right", "10px"); */
	});
</script>

<script>
	$(function() {
		// Summernote
		$('.textarea').summernote()
	})
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
	function checkProductSetup() {
		checkProductSetupValid();
		if (errors == 0) {
			return true;
		}
		event.preventDefault();
	}

	function checkProductSetupValid() {
		errors = 0;

		var prdNameErr = checkField("Product Name", $("#product_name").val(),
				true, null, null, null);

		var prdCodeErr = checkField("Product Code", $("#product_code").val(),
				true, null, null, null);

		var priceErr = checkField("Product Price", $("#price_id").val(), true,
				null, null, "n");

		var qtyErr = checkField("Product Qty", $("#quantity_id").val(), true,
				null, null, "n");

		var originalPriceErr = checkField("Original Price", $(
				"#original_price_id").val(), true, null, null, "n");

		var subCategoryErr = checkField("Sub Category", $(
				"#sub_category_id option:selected").val(), true, null, null,
				's');

		/* var prdSizeErr = checkField("Product Size", $("#size_id").val(), true,
				null, null, null); */

		/* var prdColorErr = checkField("Product Color", $("#color_id").val(),
				true, null, null, null); */

		if (prdNameErr) {
			showError("prd_name_date", "product_name", prdNameErr);
			errors = 1;
		} else {
			removeErrorMsg("prd_name_date", "product_name");
		}

		if (prdCodeErr) {
			showError("prd_code_data", "product_code", prdCodeErr);
			errors = 1;
		} else {
			removeErrorMsg("prd_code_data", "product_code");
		}

		if (subCategoryErr) {
			showError("sub_category_data", "sub_category_id", subCategoryErr);
			errors = 1;
		} else {
			removeErrorMsg("sub_category_data", "sub_category_id")
		}

		if (priceErr) {
			showError("price_data", "price_id", priceErr);
			errors = 1;
		} else {
			removeErrorMsg("price_data", "price_id");
		}

		if (qtyErr) {
			showError("quantity_data", "quantity_id", qtyErr);
			errors = 1;
		} else {
			removeErrorMsg("quantity_data", "quantity_id");
		}

		if ($("#original_price_id").val() != ''
				&& $("#original_price_id").val() > 0) {
			if (originalPriceErr) {
				showError("original_price_data", "original_price_id",
						originalPriceErr);
				errors = 1;
			} else {
				removeErrorMsg("original_price_data", "original_price_id");
			}
		}

		/* if (prdSizeErr) {
			showError("size_data", "size_id", prdSizeErr);
			errors = 1;
		} else {
			removeErrorMsg("size_data", "size_id");
		}
		 */
		/* if (prdColorErr) {
			showError("color_data", "color_id", prdColorErr);
			errors = 1;
		} else {
			removeErrorMsg("color_data", "color_id");
		} */

		if ($.isNumeric($("#original_price_id").val())) {
			if (!$.isNumeric($("#discount_percent_id").val())) {
				showError("discount_data", "discount_percent_id",
						"Invalid discount percent");
				errors = 1;
			} else {
				removeErrorMsg("discount_data", "discount_percent_id");
			}
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