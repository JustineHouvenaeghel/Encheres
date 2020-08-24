<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

	<div class="col-12">
		<h1 class="my-5">Se connecter</h1>
	</div>
	
	<form method="post" action ="${pageContext.request.contextPath}/connexion" class="row justify content mb-2">
			<label for="user_id" class="col-2 col-form-label">Identifiant :</label>
			<input class="form-control" type="text" id="user_id" name="user_id"/>
			<input type="submit" class="btn" value="Connexion"/>
	</form>
</body>
</html>