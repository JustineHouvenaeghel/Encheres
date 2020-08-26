<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil de ${pseudo}</title>

	<!-- Bootstrap core CSS -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

	<!-- Custom styles for this template -->
    <link href="css/stylesheet.css" rel="stylesheet">
    
</head>
<body class="container">

	<jsp:include page="/WEB-INF/Fragments/entete_connecte.jsp"></jsp:include>
	
	<div class="row">
		<h1 class="my-5">Profil de ${pseudo}</h1>
	</div>
	
	<c:if test="${!empty listeCodesErreur}">
	   	<div class="alert alert-danger" role="alert">
	   		<strong>Erreur !</strong>
	   		<ul>
	   			<c:forEach var="code" items="${listeCodesErreur}">
	   				<li>${LecteurMessage.getMessageErreur(code)}</li>
	   			</c:forEach>
	   		</ul>
	   	</div>
    </c:if>
	
	<fieldset class="container">
		<div class="row">
			<p class="col-2">Pseudo : </p>
			<p>${pseudo}</p>
		</div>
		<br>
		<div class="row">
			<p  class="col-2">Nom : </p>
			<p>${nom}</p>
		</div>
		<div class="row">
			<p class="col-2">Prénom : </p>
			<p>${prenom}</p>
		</div>
		<div class="row">
			<p class="col-2">Email : </p>
			<p>${email}</p>
		</div>
		<div class="row">
			<p class="col-2">Téléphone : </p>
			<p>${telephone}</p>
		</div>
		<div class="row">
			<p class="col-2">Adresse : </p>
			<p>${rue}</p>
		</div>
		<div class="row">
			<p class="col-2">Code postal : </p>
			<p>${codePostal}</p>
		</div>
		<div class="row">
			<p class="col-2">Ville : </p>
			<p>${ville}</p>
		</div>
	</fieldset>
	
</body>
</html>