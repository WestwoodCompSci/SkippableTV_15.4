<!doctype html>
<html>
	<head>
		<title><?php echo $title; ?></title>
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.96.1/css/materialize.min.css" />
		<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/t4t5/sweetalert/8b62349c38c877b2616f7aff2065e7272e9c7549/dist/sweetalert.css" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
		<script async type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.96.1/js/materialize.min.js"></script>
		<script async type="text/javascript" src="https://cdn.rawgit.com/t4t5/sweetalert/8b62349c38c877b2616f7aff2065e7272e9c7549/dist/sweetalert.min.js"></script>
		<style>
			nav, nav ul a
			{
				font-weight:600;
				font-size:1.5rem !important;
			}
			.page-height
			{
				height:calc(100vh - 65px) !important;
			}
			.login
			{
				width:350px;
				margin-left:calc(50vw - 175px);
			}
			.login-background
			{
				background-size:cover;
				background:url(https://raw.githubusercontent.com/WestwoodCompSci/SkippableTV_15.4/master/bin/graphics/banner.small.png);
			}
			.font-600
			{
				font-weight:600
			}
			.font-300
			{
				font-weight:300
			}
		</style>
	</head>
	<body class="teal lighten-2 ">
		<header>
			<nav>
				<div class="nav-wrapper grey lighten-2 teal-text">
					<a href="#" class="brand-logo teal-text lighten-2"><img src="https://github.com/WestwoodCompSci/SkippableTV_15.4/blob/master/images/logo_originalSize.png?raw=true" height="65px" alt="Skippable logo" /></a>
					<a href="#" data-activates="mobile-demo" class="button-collapse"><i class="mdi-navigation-menu"></i></a>
					<ul class="right hide-on-med-and-down teal-text lighten-2">
						<li><a  class="teal-text lighten-2" href="about.php">About</a></li>
						<li><a class="teal-text lighten-2"  href="login">Login</a></li>
						<li><a  class="teal-text lighten-2" href="download.php">Download</a></li>
						<li><a  class="teal-text lighten-2" href="https://github.com/WestwoodCompSci/SkippableTV_15.4" target="_blank">Source</a></li>
					</ul>
					<ul class="side-nav teal-text lighten-2" id="mobile-demo">
						<li><a class="teal-text lighten-2"  href="about.php">About</a></li>
						<li><a  class="teal-text lighten-2" href="login">Login</a></li>
						<li><a  class="teal-text lighten-2" href="download.php">Download</a></li>
						<li><a  class="teal-text lighten-2" href="https://github.com/WestwoodCompSci/SkippableTV_15.4" target="_blank">Source</a></li>
					</ul>
				</div>
			</nav>
		</header>
		<main class="<?php if(isset($mainClass)) print $mainClass;?>">