<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil de ${profil.pseudo}</title>

	<!-- Bootstrap core CSS -->
<!-- 	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- 	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script> -->
<!--     <script src="bootstrap/js/bootstrap.min.js"></script> -->
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


	<!-- Custom styles for this template -->
    <link href="css/stylesheet.css" rel="stylesheet">
    
</head>
<body class="container">

	<jsp:include page="/WEB-INF/Fragments/entete_connecte.jsp"></jsp:include>
	
	<div class="row">
		<h2 class="my-5 pt-5">Profil de ${profil.pseudo}</h2>
	</div>
	
	<jsp:include page="/WEB-INF/Fragments/messages_erreur.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div class="col-6 offset-3 bordure">
				<div class="row">
					<p class="col-4">Pseudo : </p>
					<p class="col-4">${profil.pseudo}</p>
				</div>
				<br>
				<div class="row">
					<p  class="col-4">Nom : </p>
					<p class="col-4">${profil.nom}</p>
				</div>
				<div class="row">
					<p class="col-4">Prénom : </p>
					<p class="col-4">${profil.prenom}</p>
				</div>
				<div class="row">
					<p class="col-4">Email : </p>
					<p class="col-4">${profil.email}</p>
				</div>
				<div class="row">
					<p class="col-4">Téléphone : </p>
					<p class="col-4">${profil.telephone}</p>
				</div>
				<div class="row">
					<p class="col-4">Adresse : </p>
					<p class="col-4">${profil.rue}</p>
				</div>
				<div class="row">
					<p class="col-4">Code postal : </p>
					<p class="col-4">${profil.codePostal}</p>
				</div>
				<div class="row">
					<p class="col-4">Ville : </p>
					<p class="col-4">${profil.ville}</p>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>