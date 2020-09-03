<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<c:if test="${!empty requestScope.modificationEffectuee}">
   	<div class="alert alert-success" role="alert">
   		<strong>${requestScope.modificationEffectuee}</strong>
	</div>
</c:if>