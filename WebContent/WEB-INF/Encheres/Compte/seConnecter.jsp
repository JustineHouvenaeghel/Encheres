<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Se connecter</title>

	<!-- Bootstrap core CSS -->
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

	<!-- Custom styles for this template -->
    <link href="css/stylesheet.css" rel="stylesheet">
    
</head>
<body class="container">

	<jsp:include page="/WEB-INF/Fragments/entete_deconnecte.jsp"></jsp:include>
	
	<div class="row">
		<h1 class="my-5">Se connecter</h1>
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
	
	<form method="get" action ="${pageContext.request.contextPath}/creerUnCompte">
		<div class="row">
			<span class="text col-2 offset-8">Pas encore inscrit ? </span>
			<input type="submit" class="btn" value="Créer un compte"/>
		</div>
	</form>
	
	<form method="post" action ="${pageContext.request.contextPath}/seConnecter">
		<fieldset class="container">
			<legend>Connexion</legend>
			
			<div class="row">
				<label for="login" class="col-2 offset-2 col-form-label">Login : </label>
				<input class="col-6 form-control" type="text" id="login" name="login" autofocus required/>
			</div>
			<div class="row">
				<label for="mot_de_passe" class="col-2 offset-2 col-form-label">Mot de passe : </label>
				<input class="col-6 form-control" type="password" id="mot_de_passe" name="mot_de_passe" required/>
			</div>
			<div class="row" style="margin-left: 45%">
				<input type="submit" class="btn" value="Se connecter"/>
			</div>
		</fieldset>
		
			
	</form>
	
	
	
</body>
</html>