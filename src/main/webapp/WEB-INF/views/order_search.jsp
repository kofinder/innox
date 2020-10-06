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
					<h1 class="m-0 text-dark">Order Search</h1>
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
							<!-- order search-->
							<form:form role="form" id="order_search_form"
								modelAttribute="orderDTO" action="order_search.html"
								method="POST" enctype="multipart/form-data">
								<form:hidden path="seq" />
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="customerName">Customer Name</label>
											<form:input path="customerName" id="customer_name"
												placeholder="Customer Name" class="form-control" />
										</div>

										<div class="form-group">
											<label for="orderStatus">Order Status</label>
											<form:select class="form-control" path="orderStatus"
												id="order_status">
												<form:option value="-1">--- Please Select One ---</form:option>
												<form:options items="${orderStatusList}" itemValue="code"
													itemLabel="description" />
											</form:select>
										</div>

										<div class="form-group">
											<label>Date range:</label>

											<div class="input-group">
												<div class="input-group-prepend">
													<span class="input-group-text"> <i
														class="far fa-calendar-alt"></i>
													</span>
												</div>
												<input type="text" class="form-control float-right"
													name="dateRange" id="reservation" spellcheck="false"
													data-ms-editor="true">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="invoiceNumber">Invoice No</label>
											<form:input path="invoiceNumber" id="invoice_no"
												placeholder="Invoice No" class="form-control" />
										</div>

										<div class="form-group">
											<label for="paymentStatus">Payment Status</label>
											<form:select class="form-control" path="paymentStatus"
												id="payment_status">
												<form:option value="-1">--- Please Select One ---</form:option>
												<form:options items="${paymentStatusList}" itemValue="code"
													itemLabel="description" />
											</form:select>
										</div>


										<div class="form-group"
											style="text-align: right; margin-top: 55px;">
											<button id="category-save" type="submit"
												style="margin-right: 10px;" class="btn btn-primary">Search</button>

											<button id="category-search-clear" type="button"
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
							<h3 class="card-title">Order List</h3>

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
													<td>Order Invoice</td>
													<td>Customer Name</td>
													<td>Order Status</td>
													<td>Payment Status</td>
													<td>Total Cost</td>
													<td>Order Date</td>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${orderHistoryList}" var="order"
													varStatus="status">
													<tr>
														<td><a href="order_detail.html?orderId=${order.seq}">
																<i class="fas fa-edit"></i>
														</a></td>
														<td>${status.count}</td>
														<td>${order.invoiceNumber}</td>
														<td>${order.customerDTO.userName}</td>
														<td>${order.orderStatusText}</td>
														<td>${order.paymentStatusText}</td>
														<td>${order.totalCostText}</td>
														<td>${order.orderDate}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<!-- order list -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- order list -->

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