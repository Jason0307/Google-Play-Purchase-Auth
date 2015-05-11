<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<title>Google Index</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/index.css" media="all">
	<!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	<!--[if lt IE 8]><script src="http://ie7-js.googlecode.com/svn/version/2.0(beta3)/IE8.js" type="text/javascript"></script><![endif]-->
</head>
<body>
<header>
	<h1 id="logo">Fictive Company</h1>
	<nav class="clearfix"><ul>
		<li class="active fromLeft cssOnly"><a id="nHom" href="#"><span>Home</span></a></li>
		<li class="fromLeft cssOnly"><a id="nAbo" href="#"><span>About Us</span></a></li>
		<li class="fromTop cssOnly"><a id="nPro" href="#"><span>Products</span></a></li>
		<li class="fromBottom cssOnly"><a id="nNew" href="#"><span>News</span></a></li>
		<li class="fromRight cssOnly"><a id="nSer" href="#"><span>Services</span></a></li>
		<li class="inOut last cssOnly"><a id="nCon" href="#"><span>Contact Us</span></a></li>
	</ul></nav>
</header>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/index.js"></script>
</body>
</html>