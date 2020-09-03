<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
	<nav class="navbar navbar-expand-sm navbar-light fixed-top" role="navigation" style="background-color: #f9d73c;">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/accueil"><img src="files/encheres.png" width="30" height="30" alt="encheres"> Les objets sont nos amis</a>
			</div>
		
			<div class="navbar-collapse collapse justify-content-end">
			    <div class="nav navbar-nav">
			    
			    	<a class="nav-item nav-link" href="${pageContext.request.contextPath}/accueil">Enchères </a>
			        
			        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/vendreUnArticle">Vendre un article </a>
			        
			        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/modifierProfil">Mon profil </a>
			        
			        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/seDeconnecter">Déconnexion </a>
			        
			    </div>
			</div>
		</div>
    </nav>
</header>