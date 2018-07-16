<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*"%>
    <%@ page import="java.util.List"%>
    <%
    	UserX user = (UserX)request.getAttribute("user");
    %>
    <%
    	List<Role> roles= (List<Role>)request.getAttribute("roles");
    %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
    <% UserService us = UserServiceFactory.getUserService();%>
	<% User usr = us.getCurrentUser();%>
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
	      <%if(usr==null){ %>
	      <li class="nav-item">
	        <a class="nav-link" href="/login">Login</a>
	      </li>
	      <%} else{%>
	      <li class="nav-item">
	        <a class="nav-link"><%=usr.getEmail() %></a>
	      </li>	      
	      
	      <li class="nav-item">
	        <a class="nav-link" href="/logout">LogOut</a>
	      </li>
	      <%} %>
	    </ul>
	  </div>
	</nav><div class="container">
	<form action="/editUser" method="post">	
		<input type="hidden" name="id" value="<%=user.getId()%>">
		<div class="form-group">
		<label>Nombre:</label>
		<input class="form-control" name="name"value="<%=user.getName()%>">
		</div>
		<div class="form-group">
		<label>Apellidos:</label>
		<input name="lastName" class="form-control" value="<%=user.getLastName()%>">
		</div>
		<div class="form-group">
		<label>Correo Electronico:</label>
		<input class="form-control" name="email" value="<%= user.getEmail()%>">
		</div>
		<div class="form-group">
		<label>Fecha de nacimiento:</label>
		<input class="form-control" name="birth"value="<%=user.getBirth()%>">
		</div>
		<div class="form-group">
		<select name="role" class="form-control">
			<%for(Role r: roles){ %>
			<option value="<%=r.getId()%>"><%=r.getName()%></option>
			<%} %>
		</select>
		</div>
		<div class="form-group">
		<input type="submit" class="btn btn-primary" value="Editar">
		</div>
	</form>
	</div>
</body>
</html>