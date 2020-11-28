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

<!-- daterange picker -->
<link rel="stylesheet"
	href="resources/plugins/daterangepicker/daterangepicker.css">

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
					<h1 class="m-0 text-dark">Order Manage</h1>
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
							<h3 class="card-title">Order Item List</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="card-body">
							<!-- order detail-->
							<form:form role="form" id="order_detail_form"
								modelAttribute="orderDTO" action="order_detail.html"
								method="POST" enctype="multipart/form-data">
								<form:hidden path="seq" />
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="invoiceNumber">invoice Number</label>
											<form:input path="invoiceNumber" id="invoice_number"
												disabled="true" class="form-control" />
										</div>

										<div class="form-group">
											<label for="orderStatus">Order Status</label>
											<form:select class="form-control" path="orderStatus"
												id="order_status">
												<form:options items="${orderStatusList}" itemValue="code"
													itemLabel="description" />
											</form:select>
										</div>

										<div class="form-group">
											<label for="deliveryFee">Delivery Fee</label>
											<form:input path="deliveryFee" id="delivery_fee"
												disabled="true" class="form-control" />
										</div>

										<div class="form-group">
											<label for="userName">Customer Name</label>
											<form:input path="customerDTO.userName" id="customer_name"
												disabled="true" class="form-control" />
										</div>

										<div class="form-group">
											<label for="townshipName">Township Name</label>
											<form:input path="townshipDTO.townshipName"
												id="township_name" disabled="true" class="form-control" />
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="paymentType">Payment Type</label>
											<form:input path="paymentTypeDTO.name" id="paymentType"
												disabled="true" class="form-control" />
										</div>

										<div class="form-group">
											<label for="paymentStatus">Payment Status</label>
											<form:select class="form-control" path="paymentStatus"
												id="payment_status">
												<form:options items="${paymentStatusList}" itemValue="code"
													itemLabel="description" />
											</form:select>
										</div>

										<div class="form-group">
											<label for="totalCost">Total Cost</label>
											<form:input path="totalCost" id="total_cost" disabled="true"
												class="form-control" />
										</div>

										<div class="form-group">
											<label for="stateName">State Name</label>
											<form:input path="stateDTO.name" id="state_name"
												disabled="true" class="form-control" />
										</div>

										<div class="form-group">
											<label for="deliveryAddress">Detail Address</label>
											<form:input path="deliveryAddress" id="detail_address"
												disabled="true" class="form-control" />
										</div>

										<div class="form-group">
											<label for="remark">Remark</label>
											<form:input path="remark" id="remark" disabled="true"
												class="form-control" />
										</div>


										<div class="form-group"
											style="text-align: right; margin-top: 55px;">
											<button id="order_update" type="button"
												style="margin-right: 10px;" class="btn btn-primary"
												data-toggle="modal" data-target="#modal-default">Update</button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
						<!-- /.card -->
					</div>

					<div class="card card-default">

						<div class="card-header">
							<h3 class="card-title">Order List</h3>

							<div class="card-tools">
								<button type="button" class="btn btn-tool"
									data-card-widget="collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="card-body">
							<!-- order item list -->
							<div class="row">

								<div class="card-body">
									<div id="example1_wrapper"
										class="dataTables_wrapper dt-bootstrap4">
										<table id="example1"
											class="table table-bordered table-striped dataTable dtr-inline"
											role="grid" aria-describedby="example1_info">

											<thead>
												<tr role="row">
													<td>No</td>
													<td>Product Name</td>
													<td>Color</td>
													<td>Size</td>
													<td>Unit Price</td>
													<td>Quantity</td>
													<td>Sub Total</td>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${orderItemList}" var="orderItem"
													varStatus="status">
													<tr>
														<td>${status.count}</td>
														<td>${orderItem.productDTO.name}</td>
														<td>${orderItem.colorDTO.colorName}</td>
														<td>${orderItem.sizeDTO.sizeName}</td>
														<td>${orderItem.unitPrice}</td>
														<td>${orderItem.quantity}</td>
														<td>${orderItem.subTotal}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<!-- order item list -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- order item list -->

			<!-- /.container-fluid -->
		</div>
		<!-- /.content -->
	</div>
</div>


<div class="modal" id="modal-default">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Confirm</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Are you sure want to update ?</p>
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<button id="order_update_modal" type="button"
					class="btn btn-primary">Update</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
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

<script src="resources/plugins/moment/moment.min.js"></script>
<!-- date-range-picker -->
<script src="resources/plugins/daterangepicker/daterangepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		bsCustomFileInput.init();
	});

	$("#category-search-clear").click(function() {
		$("#category_name").val('');
		$("#status_id").val('-1');
		$("#sequence_no").val('0');
	});

	$("#order_update_modal").click(function() {
		$("#order_detail_form").submit();
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
	$(function() {
		//Date range picker
		$('#reservation').daterangepicker();
	})
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