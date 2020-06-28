<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<tiles:importAttribute name="stylesheets"/>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="author" content="finderbar">
	    <meta name="description" content="topup">
  		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  		<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	    <c:forEach var="css" items="${stylesheets}">
	        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
	    </c:forEach>
	    <style type="text/css">
	    	.global-container{
				height:100%;
				min-height:100vh;
				display: flex;
				align-items: center;
				justify-content: center;
				background-color: #f5f5f5;
			}
			form{
				padding-top: 10px;
				font-size: 14px;
				margin-top: 30px;
			}
			.card-title{ font-weight:300; }
			.btn{
				font-size: 14px;
				margin-top:20px;
			}
			.login-form{ 
				width:330px;
				margin:20px;
			}
	    </style>	    
	</head>
	
	<body>
		<div class="global-container">
			<div class="card login-form">
				<div class="card-body">
					<h3 class="card-title text-center">121 INNOX</h3>
					<div class="card-text">
						
						<form:form name='f' action="/saveUser" method='POST' modelAttribute="user" accept-charset="UTF-8">
							<div class="form-group">
								<form:label path="userName">UserName</form:label>
								<form:input type="text" path="userName" class="form-control form-control-sm" id="userName" aria-describedby="userName"/>
							</div>
							<div class="form-group">
								<form:label path="email">Email</form:label>
								<form:input type="text" path="email" class="form-control form-control-sm" id="email" aria-describedby="email"/>
							</div>
							<div class="form-group">
								<form:label path="password">Password</form:label>
								<form:input type="password" path="password" class="form-control form-control-sm" id="password"/>
							</div>
							<button type="submit" class="btn btn-primary btn-block">Register</button>
						</form:form>
						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>