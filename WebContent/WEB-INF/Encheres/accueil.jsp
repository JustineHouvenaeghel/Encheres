<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>

	<!-- Bootstrap core CSS -->
<!-- 	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- 	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script> -->
<!--     <script src="bootstrap/js/bootstrap.min.js"></script> -->
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<!-- Custom styles for this template -->
    <link href="css/stylesheet.css" rel="stylesheet">
    
</head>
<body class="container">
	
	
	<c:choose>
		<c:when test="${empty sessionScope.utilisateur}">
			<jsp:include page="/WEB-INF/Fragments/entete_deconnecte.jsp"></jsp:include>
			<div class="row">
				<h2 class="my-5 pt-5">Liste des enchères</h2>
			</div>
	
			<jsp:include page="/WEB-INF/Fragments/messages_erreur.jsp"></jsp:include>
    
			<form method="get" action ="${pageContext.request.contextPath}/accueil" class="row justify content mb-2">
				<fieldset class="container">
					<legend>Filtres</legend>
						<div class="row">
							<img class="col-1" style="align-self:center; float: right; padding-right: 0" src="bootstrap/fonts/search.svg" alt="search" width="20" height = "20" title="Recherche">
							<input  class=" col-4 form-control" type="text" id="recherche" name="recherche" placeholder="Quel article recherchez-vous ?"/>
							<label for="categorie"  class="col-2 offset-1 col-form-label">Catégorie : </label>
							<select class=" col-4 form-control" id="categorie" name="categorie" required>
								<option value="0" selected="selected">Toutes</option>
								<c:forEach var="categorie" items="${requestScope.listeCategories}">
									<option value="${categorie.noCategorie}">${categorie.libelle}</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="row" style="margin-left: 40%">
							<input type="submit" class="btn" id="valider" value="Rechercher"/>
						</div>
				</fieldset>
			</form>
			
			
			<div class="container">
				<div class="row">
					<c:forEach var="article" items="${requestScope.listeArticles}">
						<div class="col-5 encadre">
							<div class="row">
								<div class="col- 5">
									<c:if test="${!empty article.image}">
										<img class="image" alt="${article.nomArticle}" src="${article.image}" width="100" height="100">
									
									</c:if>
									<c:if test="${empty article.image}">
										<img class="image" alt="${article.nomArticle}" src="images/image_vide.jpg" width="100" height="100">
								
									</c:if>
								</div>
								<div class="col-7">
									<h3>${article.nomArticle}</h3>
									<p>Prix : ${article.prixDeVente} crédits</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
									<p>Vendeur : ${article.vendeur.pseudo}</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			
			
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/Fragments/entete_connecte.jsp"></jsp:include>
			<div class="row">
				<h2 class="my-5 pt-5">${sessionScope.utilisateur.pseudo}, voici les enchères en cours</h2>
			</div>
	
			<jsp:include page="/WEB-INF/Fragments/messages_erreur.jsp"></jsp:include>
			
			<form method="get" action ="${pageContext.request.contextPath}/accueil" class="row justify content mb-2">
				<fieldset class="container">
					<legend>Filtres</legend>
						<div class="row">
							<img class="col-1" style="align-self:center; float: right; padding-right: 0" src="bootstrap/fonts/search.svg" alt="search" width="20" height = "20" title="Recherche">
							<input  class=" col-4 form-control" type="text" id="recherche" name="recherche" placeholder="Quel article recherchez-vous ?"/>
							<label for="categorie"  class="col-2 offset-1 col-form-label">Catégorie : </label>
							<select class=" col-4 form-control" id="categorie" name="categorie" required>
								<option value="0" selected="selected">Toutes</option>
								<c:forEach var="categorie" items="${requestScope.listeCategories}">
									<option value="${categorie.noCategorie}">${categorie.libelle}</option>
								</c:forEach>
							</select>
						</div>
						<div class="row">
							<span class="col-4 offset-2">
								<input type="radio" id="achat" name="filtre_enchere" onchange="activerDesactiver();" value="achat"/>
								<label style="text-align: left;" for="achat">Achats</label>
							</span>
							<span class="col-4 offset-2">
								<input type="radio" id="ventes" name="filtre_enchere" onchange="activerDesactiver();" value="ventes"/>
								<label for="ventes">Ventes</label>
							</span>
						</div>
						<div class="row">
							<div class="col-5 offset-1">
								<input type="checkbox" id="encheres_ouvertes" name="encheres_ouvertes" disabled="disabled"/>
								<label for="encheres_ouvertes">Enchères ouvertes</label>
								<br>

								<input type="checkbox" id="mes_encheres_en_cours" name="mes_encheres_en_cours" disabled="disabled"/>
								<label for="mes_encheres_en_cours">Mes enchères en cours</label>
								<br>

								<input type="checkbox" id="mes_encheres_en_remportees" name="mes_encheres_en_remportees" disabled="disabled"/>
								<label for="mes_encheres_en_remportees">Mes enchères remportées</label>
							</div>
							
							<div class="col-5 offset-1">
								<input type="checkbox" id="mes_ventes_en_cours" name="mes_ventes_en_cours" disabled="disabled"/>
								<label for="mes_ventes_en_cours">Mes ventes en cours</label>
								<br>

								<input type="checkbox" id="ventes_non_débutees" name="ventes_non_débutees" disabled="disabled"/>
								<label for="ventes_non_débutees">Ventes non débutées</label>
								<br>

								<input type="checkbox" id="ventes_terminees" name="ventes_terminees" disabled="disabled"/>
								<label for="ventes_terminees">Ventes terminées</label>
							</div>
							
						</div>
						
						<div class="row" style="margin-left: 40%">
							<input type="submit" class="btn" id="valider" value="Rechercher"/>
						</div>
				</fieldset>
			</form>
			
			
			<div class="container">
				<div class="row">
					<c:forEach var="article" items="${requestScope.listeArticles}">
						<div class="col-5 encadre">
							<div class="row">
								<div class="col- 5">
									<c:if test="${!empty article.image}">
										<img class="image" alt="${article.nomArticle}" src="${article.image}" width="100" height="100">
									
									</c:if>
									<c:if test="${empty article.image}">
										<img class="image" alt="${article.nomArticle}" src="images/image_vide.jpg" width="100" height="100">
								
									</c:if>
								</div>
								<div class="col-7">
									<h3><a href="${pageContext.request.contextPath}/voirDetailArticle?no_article=${article.noArticle}">${article.nomArticle}</a></h3>
									<p>Prix : ${article.prixDeVente} crédits</p>
									<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
									<p>Vendeur : <a href="${pageContext.request.contextPath}/voirProfil?pseudo=${article.vendeur.pseudo}">${article.vendeur.pseudo}</a></p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:otherwise>
	</c:choose>	
	
	<!-- Custom styles for this template -->
	<script src="js/filtrerRecherche.js"></script>
	
</body>
</html>