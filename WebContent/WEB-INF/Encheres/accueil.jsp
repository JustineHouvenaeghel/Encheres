<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>

	<!-- Bootstrap core CSS -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

	<!-- Custom styles for this template -->
    <link href="css/stylesheet.css" rel="stylesheet">
    
</head>
<body class="container">
	
	
	<c:choose>
		<c:when test="${empty sessionScope.utilisateur}">
			<jsp:include page="/WEB-INF/Fragments/entete_deconnecte.jsp"></jsp:include>
		
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/Fragments/entete_connecte.jsp"></jsp:include>
		
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty sessionScope.utilisateur}">
			<div class="row">
				<h1 class="my-5">Accueil</h1>
			</div>
		
		</c:when>
		<c:otherwise>
			<div class="row">
				<h1 class="my-5">${sessionScope.utilisateur.pseudo}, voici les enchères en cours</h1>
			</div>
			
			<a href="${pageContext.request.contextPath}/voirProfil?pseudo=juju">juju</a>
		</c:otherwise>
	</c:choose>
	
	
	
	
	
</body>
</html>