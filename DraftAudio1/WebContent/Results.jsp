<%@page import="java.util.List"%>
<%@page import="model.Draft"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">



<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- Theme CSS -->
<link href="css/grayscale.css" rel="stylesheet">


<!-- jQuery -->
<script src="vendor/jquery/jquery.js"></script>

<script src="js/profile.js" type="text/jscript"></script>
<!-- Bootstrap Core JavaScript -->
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Plugin JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<!-- Theme JavaScript -->
<script src="js/grayscale.min.js"></script>
<link href="css/Results.css" rel="stylesheet">



<body>
	<nav id="main-navbar" class="navbar navbar-custom navbar-fixed-top"
		role="navigation">
	<div class="container-fluid">
		<div class="navbar-header col-md-2">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-main-collapse">
				Menu <em class="fa fa-bars"></em>
			</button>
			<a href="#page-top" class="navbar-brand page-scroll" id="logo"> <i
				class="glyphicon glyphicon-music"></i> <span class="light">Draft</span>Audio
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div
			class="collapse navbar-collapse navbar-right navbar-main-collapse">
			<ul class="nav navbar-nav">
				<!-- Hidden li included to remove active class from about link when scrolled up past about section -->
				<li class="hidden"><a href="#page-top"></a></li>
				<li>
					<form class="navbar-form" role="search">
						<div class="input-group">
							<input name="q" type="text" class="form-control" id="search-bar"
								placeholder="Search">
							<div class="input-group-btn">
								<button type="submit" class="btn btn-info" id="search-btn">
									<i class="glyphicon glyphicon-search" id="search-glyph"></i>
								</button>
							</div>
						</div>
					</form>
				</li>


				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Other <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>

				<li><a type="button" id="go-to-scores-btn"> <i
						class="glyphicon glyphicon-headphones"></i> scores
				</a></li>
				<li><a type="button" id="go-to-editor-btn"> <i
						class="glyphicon glyphicon-plus-sign"></i> New Score
				</a></li>
				<li id="user-dropdown" class="dropdown "><a style="" href="#"
					class="dropdown-toggle" data-toggle="dropdown"><strong>account</strong>
						<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/user/preferences"><i
								class="glyphicon glyphicon-question-sign"></i> help</a></li>
						<li><a href="/user/preferences"><i
								class="glyphicon glyphicon-envelope"></i> contact us</a></li>
						<li><a href="/user/preferences"><i
								class="glyphicon glyphicon-cog"></i> settings</a></li>
						<li class="divider"></li>
						<li><a href="/auth/logout"><i
								class="glyphicon glyphicon-off"></i> Logout</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>


	<div class="row" id="separator"></div>



	<div class="row container-fluid center-block" id="results-section">



		<div class="panel panel-default col-md-10" id="res-container">
			<%List<Draft>list=(List<Draft>)request.getSession().getAttribute("result"); %>
			
			<%for(Draft l:list){; %>
			<div class=" result-panel panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-4">
							<a href="www.google.it"><%out.print(l.getName()); %></a>
						</div>
						<div class="col-md-3">1/9/1939</div>
						<div class="col-md-3">
							<a href="www.youtube.it"><% out.print(l.getAuthorDraft());%> </a>
						</div>
						<div class="col-md-1 glyphicon glyphicon-heart" id="draft-heart">
							539</div>
						<div class="col-md-1 glyphicon glyphicon-eye-open"
							id="draft-views">800</div>
					</div>
				</div>
			</div>
			<%} %>


		</div>







	</div>





</body>



</html>
