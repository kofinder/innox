<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Toastr -->
<link rel="stylesheet" href="resources/plugins/toastr/toastr.min.css">

<!-- DataTables -->
<link rel="stylesheet"
	href="resources/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">

<link rel="stylesheet"
	href="resources/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">

<div class="content-wrapper">

	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">Dash	board</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="dashboard.html">Home</a></li>
						<li class="breadcrumb-item active">Dashboard</li>
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

<!-- Toastr -->
<script src="resources/plugins/toastr/toastr.min.js"></script>

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
	toastr.info('Have a good day!', '121 INNOX');
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