<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*"%>
<%@ page import="java.util.List"%>
    <% List<Foto> fotos = (List<Foto>)request.getAttribute("fotos");%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <!-- Fonts-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Pangolin" rel="stylesheet"> 
    <style type="text/css">
    	header{
		  background-color: #000;
		}
		.logo{
		  height: 130px;
		  width: 180px;
		}
		.social-header{
		  margin-top: 40px;
		}
		.social-header a{
		  color: #9a989b;
		}
		.menu-bar{
		  background-color: #9a989b; 
		  font-family: 'Pangolin', cursive;
		}
		.menu-bar a{
		  color: #000;
		}
		.menu-bar a:hover{
		  color: #fff;
		}
		.login{
		 font-family: 'Pangolin', cursive; 
		}
		.contenido{
		  background-color: rgba(41,23,1,0.75);
		  padding-top: 300px;
		  height: 100%;
		  top: 0;
		  left: 50%;
		  width: 500px;
		}
		.dropdown-login{
		  padding: 10px;
		}
    </style>

    <title>Photo Studio</title>
  </head>
  <body>
    <header>
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <img src="photoStudio.png" alt="photo Studio" class="logo img-fluid">
          </div>
          <div class="col-md-6 ">
                    <ul class="social-header list-inline text-right">
                        <li class="list-inline-item">
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x fa-inverse"></i>
                                    <i class="fab fa-facebook fa-stack-1x "></i>
                                  </span>
                              </a>
                          </li>
                          <li class="list-inline-item">
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x fa-inverse"></i>
                                    <i class="fab fa-twitter fa-stack-1x "></i>
                                  </span>
                              </a>
                          </li>
                          <li class="list-inline-item">
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x fa-inverse"></i>
                                    <i class="fab fa-youtube fa-stack-1x "></i>
                                  </span>
                              </a>
                          </li>
                          <li class="list-inline-item">
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x fa-inverse"></i>
                                    <i class="fab fa-instagram fa-stack-1x "></i>
                                  </span>
                              </a>
                          </li>
                          <li class="list-inline-item">
                            <div class="dropdown login">
                              <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Ingresar
                              </button>
                              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <form action="/hello" method="post">
                                <div class="input-group mb-3">
                                  <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">@</span>
                                  </div>
                                  <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
                                </div>
                                <div class="input-group mb-3">
                                  <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">*</span>
                                  </div>
                                  <input type="text" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1">
                                </div>
                                <input type="submit"  value="Iniciar sesión" class="btn btn-success">
                                </form>
                              </div>
                            </div>
                          </li>
                        </ul>
         
                </div>
        </div>             
      </div>
        <div class="menu-bar text-right">
            <nav class="container" role="menu">
                <ul class="nav justify-content-end">
                  <li class="nav-item">
                    <a class="nav-link active" href="/getInto">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Sobre Nosotros</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Citas y reservas</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Contacto</a>
                  </li>
                </ul>
            </nav>
      </div>
    </header>
    <div class="main">
    	<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    		
		  <div class="carousel-inner">
		  <%for( Foto p : fotos ) {%>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="<%=p.getPhoto()%>">
		    </div>
		    <%} %>
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
    </div>
    <footer>
      
    </footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
  </body>
</html>