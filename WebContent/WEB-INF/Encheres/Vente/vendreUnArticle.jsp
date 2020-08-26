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
	

	<jsp:include page="/WEB-INF/Fragments/entete_connecte.jsp"></jsp:include>


	<div class="row">
		<h1 class="my-5">Nouvelle vente</h1>
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
    
    <form method="post" action ="${pageContext.request.contextPath}/vendreUnArticle" class="row justify content mb-2">
		<fieldset class="container">
			<legend>Votre article</legend>
			
			<div class="row">
				<label for="nom_article"  class="col-3 offset-1 col-form-label">Article : </label>
				<input class=" col-5 form-control" type="text" id="nom_article" name="nom_article" autofocus required/>
				
			</div>
			<div class="row">
				<label for="description"  class="col-3 offset-1 col-form-label">Description : </label>
				<textarea class=" col-5 form-control" id="description" name="description" required></textarea>
				
			</div>
			<div class="row">
				<label for="categorie"  class="col-3 offset-1 col-form-label">Catégorie : </label>
				<select class=" col-5 form-control" type="text" id="categorie" name="categorie" required>
					<option value="Toutes">Toutes</option>
					<option value="Informatique">Informatique</option>
					<option value="Ameublement">Ameublement</option>
					<option value="Sport & loisirs">Sport & Loisirs</option>
				</select>
				
			</div>
			<div class="row">
				<label for="image"  class="col-3 offset-1 col-form-label">Photo de l'article : </label>
				<input class=" col-5 form-control" type="file" accept="image/png, image/jpeg" id="image" name="image" required/>
				
			</div>
			<div class="row">
				<label for="prix"  class="col-3 offset-1 col-form-label">Mise à prix : </label>
				<input class=" col-5 form-control" type="number" id="prix" name="prix" value="150" required/>
				
			</div>
			<div class="row">
				<label for="debut_enchere"  class="col-3 offset-1 col-form-label">Début de l'enchère : </label>
				<input class=" col-5 form-control" type="datetime-local" id="debut_enchere" name="debut_enchere" required/>
				
			</div>
			<div class="row">
				<label for="fin_enchere"  class="col-3 offset-1 col-form-label">Fin de l'enchère : </label>
				<input class=" col-5 form-control" type="datetime-local" id="fin_enchere" name="fin_enchere" required/>
				
			</div>
			<fieldset>
			<legend>Adresse de retrait</legend>
				<div class="row">
					<label for="rue"  class="col-2 offset-1 col-form-label">Adresse : </label>
					<input class=" col-6 form-control" type="text" id="rue" name="rue" value="${sessionScope.utilisateur.rue}" required/>
					
				</div>
				<div class="row">
					<label for="code_postal"  class="col-2 offset-1 col-form-label">Code postal : </label>
					<input class=" col-6 form-control" type="number" id="code_postal" name="code_postal" value="${sessionScope.utilisateur.codePostal}" required/>
					
				</div>
				<div class="row">
					<label for="ville"  class="col-2 offset-1 col-form-label">Ville : </label>
					<input class=" col-6 form-control" type="text" id="ville" name="ville" value="${sessionScope.utilisateur.ville}" required/>
					
				</div>
			</fieldset>
		</fieldset>
		<div class="row" style="margin-left: 35%">
			<input type="submit" class="btn" id="valider" value="Enregistrer"/>
			<a class="btn" href="${pageContext.request.contextPath}/accueil">Annuler</a>
			
		</div>
		
	</form>
	
	
</body>
</html>