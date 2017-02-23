<%@page import="model.Draft"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DraftAudio</title>

<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- Theme CSS -->
<link href="css/grayscale.css" rel="stylesheet">
<link href="css/profile-style.css" rel="stylesheet" type="text/css">




<!-- Intro Header -->
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
</head>




<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
	<!-- Navigation -->
	<nav id="main-navbar" class="navbar navbar-custom navbar-fixed-top"
		role="navigation">
		<div class="container-fluid">
			<div class="navbar-header col-md-2">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-main-collapse">
					Menu <em class="fa fa-bars"></em>
				</button>
				<a href="#page-top" class="navbar-brand page-scroll" id="logo">
					<i class="glyphicon glyphicon-music"></i> <span class="light">Draft</span>Audio
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

					<li><a href="ShowPublicRepo" type="button" id="go-to-scores-btn"> <i
							class="glyphicon glyphicon-headphones"></i> scores
					</a></li>
					<li><a href="Editor.html" type="button" id="go-to-editor-btn"> <i
							class="glyphicon glyphicon-plus-sign"></i> New Score
					</a></li>
					<li id="user-dropdown" class="dropdown "><a style="" href="#"
						class="dropdown-toggle" data-toggle="dropdown"><strong><% out.print(session.getAttribute("NickName")); %></strong>
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
		<!-- /.container -->





	</nav>

	<div class="row" id="row0">
		<div class="col-md-6 row0-div"></div>
	</div>


	<div class="row " id="row1">



		<div class="row1-div col-lg-2 col-md-2" id="dashboard-container">

			<div class="navbar-ex1-collapse" id="nav-vert-collapse">
				<ul class="nav nav-pills nav-stacked admin-menu">
					<li class="a"><a href="#" data-target-id="home"><i class="fa fa-home fa-fw"></i>Home</a></li>
					<li><a href="showRepository" data-target-id="yourScores"><i class="fa fa-list-alt fa-fw"></i>Your Scores</a></li>
					<li><a href="http://www.jquery2dotnet.com" data-target-id="publicScores"><i class="fa fa-file-o fa-fw"></i>Your	Public Scores</a></li>
					<li><a href="http://www.jquery2dotnet.com" data-target-id="sharedScores"><i	class="fa fa-bar-chart-o fa-fw"></i>Shared Scores</a></li>
					<li><a href="http://www.jquery2dotnet.com" data-target-id="messages"><i class="fa fa-table fa-fw"></i>Messages</a></li>
					<li><a href="http://www.jquery2dotnet.com" data-target-id="settings"><i class="fa fa-cogs fa-fw"></i>Settings</a></li>
				</ul>
			</div>
			
			
		</div>

		<!-- finestra che mostra i risultati dal menu dashboard-->
		<div class="row1-div col-md-10 col-lg-10" id="dashboard-window">


			<div class="col-md-9 well admin-content" id="home">home che se
				non serve la togliamo</div>


			<div class="col-md-9 well admin-content" id="yourScores">
			<%List<Draft>list=(List<Draft>)request.getSession().getAttribute("list"); %>
			
			<%if(list!=null)for(Draft l:list){; %>
				<div class="row container-fluid center-block" id="results-section">
					<!-- un risultato -->  
					<div class="panel panel-default col-md-10" id="res-container">

						<div class=" result-panel panel panel-default">
							<div class="panel-body">
								<div class="row">
									<div class="col-md-2">
										<a><%out.print(l.getName()); %></a>
									</div>
									<div class="col-md-2">13/11/1990</div>
									<div class="col-md-3">
										<a><%out.print(l.getAuthorDraft()); %></a>
									</div>
									<div class="col-md-1 glyphicon glyphicon-heart"
										id="draft-heart">100</div>
									<div class="col-md-1 glyphicon glyphicon-eye-open"
										id="draft-views">100</div>
									<div class="col-md-1 glyphicon glyphicon-link" id="share-draft"></div>
									<div class="col-md-1 glyphicon glyphicon-remove-circle"
										id="delete-Draft"></div>


									<!-- menu dei collaboratori --> 
									<div class="col-md-1" id="collaborators">
										<a href="#" class="dropdown-toggle glyphicon glyphicon-user"
											data-toggle="dropdown"><span class="caret"></span></a>
										<ul class="dropdown-menu collaborators-menu" role="menu">
											<li>
												<div class=" col-md-12">
													<div class="row col-md-10">Collaborator1</div>
													<div class="col-md-2 ">
														<a class="glyphicon glyphicon-remove" href="#"> </a>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- un risultato -->
				</div>
	 		<%} %>
			</div>
			
			<div class="col-md-9 well admin-content" id="publicScores">
				your public Scores</div>
			<div class="col-md-9 well admin-content" id="sharedScores">
				Scores shared with you</div>

			<div class="col-md-9 well admin-content" id="messages">
				Messages</div>
			<div class="col-md-9 well admin-content" id="settings">
				Settings</div>
		</div>
	</div>
















</body>





</html>
