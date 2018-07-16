<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<%
	List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
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
	</nav>	
	<div class="container">
	<a href="addReservation">Agregar reservacion</a><br>
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Usuario</th>
	      <th scope="col">Email</th>
	      <th scope="col">Lugar</th>
	      <th scope="col">Fecha</th>
	      <th scope="col">Opciones</th>
	    </tr>
	  </thead>
	  <tbody>
	  <%for(Reservation u:reservations){%>
	    <tr>
	      <th scope="row"><%=u.getId()%></th>
	      <td><%=u.getUser()%></td>
	      <td><%=u.getEmail() %></td>
	      <td><%=u.getPlace() %></td>
	      <td><%=u.getDate() %></td>
	      <td class="btn-group">
	      <form action="/viewReservation" method="get">
			<input type="hidden" name="id" value="<%=u.getId()%>">
			<input type="submit" value="Ver">
		</form>
		<form action="/editReservation" method="get">
			<input type="hidden" name="id" value="<%=u.getId()%>">
			<input type="submit" value="Editar">
		</form>
		<form action="/deleteReservation" method="get">
			<input type="hidden" name="id" value="<%=u.getId()%>">
			<input type="submit" value="Borrar">
		</form></td>
	    </tr>
	    <%} %>
	  </tbody>
	</table>	
	
	</div>
</body>
</html>