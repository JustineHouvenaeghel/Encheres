<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Créer un compte</title>

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

	<jsp:include page="/WEB-INF/Fragments/entete_deconnecte.jsp"></jsp:include>
	
	<div class="row">
		<h2 class="my-5 pt-5">Créer un compte</h2>
	</div>
	
	<jsp:include page="/WEB-INF/Fragments/messages_erreur.jsp"></jsp:include>
	
	<form method="post" action ="${pageContext.request.contextPath}/creerUnCompte" class="row justify content mb-2">
		<fieldset class="container">
			<legend>Vos informations personnelles</legend>
			<div class="row">
				<label for="pseudo"  class="col-2 offset-2 col-form-label">Pseudo : </label>
				<input class=" col-4 form-control" type="text" id="pseudo" name="pseudo" autofocus required/>
			</div>
			<div class="row">
				<label for="nom"  class="col-2 col-form-label">Nom : </label>
				<input class=" col-4 form-control" type="text" id="nom" name="nom" required/>
				<label for="prenom"  class="col-2 col-form-label">Prénom : </label>
				<input class=" col-4 form-control" type="text" id="prenom" name="prenom" required/>
			</div>
			<div class="row">
				<label for="email"  class="col-2 col-form-label">Email : </label>
				<input class=" col-4 form-control" type="text" id="email" name="email" required/>
				<label for="telephone"  class="col-2 col-form-label">Téléphone : </label>
				<input class=" col-4 form-control" type="tel" id="telephone" name="telephone"/>
			</div>
			<div class="row">
				<label for="rue"  class="col-2 col-form-label">Adresse : </label>
				<input class=" col-4 form-control" type="text" id="rue" name="rue" required/>
				<label for="code_postal"  class="col-2 col-form-label">Code postal : </label>
				<input class=" col-4 form-control" type="number" id="code_postal" name="code_postal" required/>
			</div>
			<div class="row">
				<label for="ville"  class="col-2 col-form-label">Ville : </label>
				<input class=" col-4 form-control" type="text" id="ville" name="ville" required/>
			</div>
		</fieldset>
		<fieldset class="container">
			<legend>Sécurité</legend>
			<div class="row">
				<label for="mot_de_passe"  class="col-2 col-form-label">Mot de passe : </label>
				<input class=" col-3 form-control" type="password" id="mot_de_passe" name="mot_de_passe" required/>
				<label for="mot_de_passe_2"  class="col-4 col-form-label">Confirmation mot de passe : </label>
				<input class=" col-3 form-control" type="password" id="mot_de_passe_2" name="mot_de_passe_2" onchange="passverif()" required/>
				<span id="msg" class="offset-3"></span>
			</div>
		</fieldset>
			<div class="row" style="margin-left: 30%">
				<input type="submit" class="btn" id="valider" value="Créer un compte"/>
				<a class="btn" href="${pageContext.request.contextPath}/accueil">Annuler</a>
			</div>
			
	</form>
	
	
	
	<!-- Custom styles for this template -->
	<script src="js/creerUnCompte.js"></script>
</body>
</html>