<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier mon article</title>

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
		<h2 class="my-5 pt-5">Modifier mon article</h2>
	</div>
	
	<jsp:include page="/WEB-INF/Fragments/messages_erreur.jsp"></jsp:include>
    
    <form method="post" action ="${pageContext.request.contextPath}/modifierArticle" class="row justify content mb-2" enctype="multipart/form-data">
		<fieldset class="container">
			<legend>Votre article</legend>
			<div class="row">
				<input class=" col-5 form-control" type="text" id="no_article" name="no_article" value="${article.noArticle}" hidden="true"/>
				
			</div>
			
			<div class="row">
				<label for="nom_article"  class="col-3 offset-1 col-form-label">Article : </label>
				<input class=" col-5 form-control" type="text" id="nom_article" name="nom_article" value="${article.nomArticle}"/>
				
			</div>
			<div class="row">
				<label for="description"  class="col-3 offset-1 col-form-label">Description : </label>
				<textarea class=" col-5 form-control" id="description" name="description">${article.description}</textarea>
				
			</div>
			<div class="row">
				<label for="categorie"  class="col-3 offset-1 col-form-label">Catégorie : </label>
				<select class=" col-5 form-control" id="categorie" name="categorie">
					<c:forEach var="categorie" items="${requestScope.listeCategories}">
						<c:choose>
							<c:when test="${categorie.noCategorie == article.categorieArticle.noCategorie}">
								<option value="${categorie.noCategorie}" SELECTED>${categorie.libelle}</option>
							</c:when>
							<c:otherwise>
								<option value="${categorie.noCategorie}">${categorie.libelle}</option>
							</c:otherwise>	
						</c:choose>
					</c:forEach>
				</select>
				
			</div>
			<div class="row">
				<label for="image"  class="col-3 offset-1 col-form-label">Photo de l'article : </label>
				<input class=" col-5 form-control" type="file" accept="image/png, image/jpeg" id="image" name="image" value="${article.image}"/>
				
			</div>
			<div class="row">
				<label for="prix"  class="col-3 offset-1 col-form-label">Mise à prix : </label>
				<input class=" col-5 form-control" type="number" id="prix" name="prix" value="${article.miseAPrix}"/>
				
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
					<input class=" col-6 form-control" type="text" id="rue" name="rue" value="${article.lieuRetrait.rue}"/>
					
				</div>
				<div class="row">
					<label for="code_postal"  class="col-2 offset-1 col-form-label">Code postal : </label>
					<input class=" col-6 form-control" type="number" id="code_postal" name="code_postal" value="${article.lieuRetrait.codePostal}"/>
					
				</div>
				<div class="row">
					<label for="ville"  class="col-2 offset-1 col-form-label">Ville : </label>
					<input class=" col-6 form-control" type="text" id="ville" name="ville" value="${article.lieuRetrait.ville}"/>
					
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