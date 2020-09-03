<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détail de la vente</title>

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
	<c:choose>
		<c:when test="${article.etatVente == 'EC' or article.etatVente == 'ND'}">
			<div class="row">
				<h2 class="my-5 pt-5">Détail de la vente</h2>
			</div>
		</c:when>
		<c:otherwise>
			<c:if test="${enchere.acheteur.pseudo == utilisateur.pseudo}">
				<div class="row">
					<h2 class="my-5 pt-5">Vous avez remporté la vente</h2>
				</div>
			</c:if>
			
			<c:if test="${enchere.acheteur.pseudo != utilisateur.pseudo}">
				<div class="row">
					<h2 class="my-5 pt-5">${enchere.acheteur.pseudo} a remporté la vente</h2>
				</div>
			</c:if>
		</c:otherwise>
	</c:choose>
	
	
	<jsp:include page="/WEB-INF/Fragments/messages_erreur.jsp"></jsp:include>
	
	<fieldset class="container">
		<div class="row">
			<div class="col-4">
				<c:if test="${!empty article.image}">
					<img class="image" alt="${article.nomArticle}" src="${article.image}" width="300" height="300">
				
				</c:if>
				<c:if test="${empty article.image}">
					<img class="image" alt="${article.nomArticle}" src="images/image_vide.jpg" width="300" height="300">
			
				</c:if>
			</div>

			<div class="col-8">
				<div class="row">
					<h3 class="col-10">${article.nomArticle}</h3>
				</div>
				<div class="row">
					<p class="col-4">Description : </p>
					<p class="col-6">${article.description}</p>
				</div>
				<div class="row">
					<p  class="col-4">Catégorie : </p>
					<p class="col-6">${article.categorieArticle.libelle}</p>
				</div>
				<c:if test="${article.etatVente != 'ND'}">
				<div class="row">
					<p class="col-4">Meilleure offre : </p>
					<c:if test="${!empty enchere}">
						<p class="col-6">${enchere.montantEnchere} crédits par ${enchere.acheteur.pseudo}</p>
					</c:if>
					<c:if test="${empty enchere}">
						<p class="col-6">Aucune offre pour le moment</p>					
					</c:if>
				</div>
				</c:if>
				
				<div class="row">
					<p class="col-4">Mise à prix : </p>
					<p class="col-6">${article.miseAPrix} crédits</p>
				</div>
				<div class="row">
					<p class="col-4">Début de l'enchère : </p>
					<p class="col-6">${article.dateDebutEncheres}</p>
				</div>
				<div class="row">
					<p class="col-4">Fin de l'enchère : </p>
					<p class="col-6">${article.dateFinEncheres}</p>
				</div>
				<div class="row">
					<p class="col-4">Lieu de retrait : </p>
					<p class="col-6">${article.lieuRetrait.rue}
					<br>${article.lieuRetrait.codePostal} ${article.lieuRetrait.ville}
					</p>
				</div>
				<div class="row">
					<p class="col-4">Vendeur : </p>
					<p class="col-6"><a href="${pageContext.request.contextPath}/voirProfil?pseudo=${article.vendeur.pseudo}">${article.vendeur.pseudo}</a></p>
				</div>
				<c:if test="${article.vendeur.pseudo != utilisateur.pseudo and article.etatVente == 'EC'}">
					<form method="post" action ="${pageContext.request.contextPath}/encherir?no_article=${article.noArticle}">
						<div class="row">
							<p class="col-4">Ma proposition : </p>
							<c:if test="${!empty enchere}">
								<input class=" col-6 form-control" type="number" id="montant_enchere" name="montant_enchere" value = "${(enchere.montantEnchere + 1)}" min="${(enchere.montantEnchere + 1)}" required/>
							</c:if>
							<c:if test="${empty enchere}">
								<input class=" col-6 form-control" type="number" id="montant_enchere" name="montant_enchere" value = "${(article.prixDeVente + 1)}" min="${(article.prixDeVente + 1)}" required/>				
							</c:if>
							
						</div>
						<div class="row" style="margin-left: 30%">
							<input type="submit" class="btn" id="valider" value="Enchérir"/>
						</div>	
					</form>
				</c:if>	
				<c:if test="${article.vendeur.pseudo == utilisateur.pseudo and article.etatVente == 'ND'}">
					<div class="row" style="margin-left: 10%">
							<a class="btn" href="${pageContext.request.contextPath}/modifierArticle?no_article=${article.noArticle}">Modifier l'article</a>
							<a class="btn" href="${pageContext.request.contextPath}/annulerVente?no_article=${article.noArticle}">Annuler la vente</a>
					</div>
				</c:if>					
			</div>
				
		</div>
	</fieldset>
	
</body>
</html>