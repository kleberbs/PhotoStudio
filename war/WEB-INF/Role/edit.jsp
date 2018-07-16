<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*"%>
    <% Role role = (Role)request.getAttribute("role");%>
<%@ page import="com.google.appengine.api.users.User" %>
	<%@ page import="com.google.appengine.api.users.UserService" %>
	<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
    <% UserService us = UserServiceFactory.getUserService();%>
	<% User user = us.getCurrentUser();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Roles</title>
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
	<div class="container">	
	<form action="/editRole" method="post">
		<input type="hidden" name="id" value="<%=role.getId()%>">
		<div class="form-group">
		<label>Nombre:</label><input class="form-control" name="name"value="<%=role.getName()%>">
		</div>
		<div class="form-group">
		<label>Estado:</label>
		<select name="status" class="form-control">
		<option value="true">Activo</option>
		<option value="false">Inactivo</option>
		</select>
		</div>
		<div class="form-group">
		<input type="submit" class="btn btn-primary" value="Editar">
		</div>
	</form>
	</div>
</body>
</html>