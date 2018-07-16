<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<% Foto foto = (Foto)request.getAttribute("foto");%>
<%@ page import="com.google.appengine.api.users.User" %>
	<%@ page import="com.google.appengine.api.users.UserService" %>
	<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
    <% UserService us = UserServiceFactory.getUserService();%>
	<% User user = us.getCurrentUser();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ingresar</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">Photo Studio</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link" href="/listRole">Roles</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/listUser">Usuarios</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/listResource">Recursos</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/listAcces">Acceso</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/listPhoto">Fotos</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/listReservation">Reservas</a>
	      </li>
	      <%if(user==null){ %>
	      <li class="nav-item">
	        <a class="nav-link" href="/login">Login</a>
	      </li>
	      <%} else{%>
	      <li class="nav-item">
	        <a class="nav-link"><%=user.getEmail() %></a>
	      </li>	      
	      
	      <li class="nav-item">
	        <a class="nav-link" href="/logout">LogOut</a>
	      </li>
	      <%} %>
	    </ul>
	  </div>
	</nav>
		<img class="card-img-top" src="<%= foto.getPhoto()%>" alt="<%= foto.getName() %>">
	
	<form action="editPhoto" method="post">
	<input type="hidden" name="photo" value="<%= foto.getPhoto()%>">
	<div class="form-group">
		<label> Nombre: </label><input class="form-control" name="name" value="<%= foto.getName() %>">
	</div>
	<div class="form-group">
		<label>Descripcion: </label><input class="form-control" name="description" value="<%= foto.getDescription() %>">
	</div>	
	<div class="form-group">
		<input class="btn btn-secondary" type="submit" value="Actualizar">
	</div>		
	</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>