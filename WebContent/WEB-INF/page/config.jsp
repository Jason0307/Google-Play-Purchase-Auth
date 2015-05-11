<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/base.css" media="all" />
<link rel="stylesheet" type="text/css"
    href="<%=request.getContextPath()%>/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
    href="<%=request.getContextPath()%>/resources/css/style.css" media="all" />
<link rel="stylesheet" type="text/css"
    href="<%=request.getContextPath()%>/resources/css/jquery.fileupload.css">
<title>Google Account Config</title>
</head>
<body>
	<div class="page">
		<header id="header">
			<hgroup class="white blank">
				<h1>Config Google Account</h1>
			</hgroup>
		</header>
		<div class="form-wrapper">
			<form class="login-form" action="#" method="POST"
				enctype="multipart/form-data">
				<span class="btn btn-success fileinput-button">
                   <i class="glyphicon glyphicon-plus"></i>
                    <span>Add / Update secret...</span>
                    <input id="upload" name="upload" type="file">
                 </span>
			</form>
		</div>
		
		<div id="progress" class="progress">
          <div class="progress-bar progress-bar-success"></div>
        </div>
		<br />
		<c:if test="${null != account}">
			<div align="center" class="account">
				<label>Client Id</label><input type="text" name="clientId"
					value="${account.clientId}" readonly="readonly" /> <br />
				<label>Client Secret</label><input type="text" name="clientSecret"
					value="${account.clientSecret}" readonly="readonly" /> <br /> 
				<label>Redirect Url</label><input type="text" name="redirectUrl"
					value="${account.redirectUrl}" readonly="readonly" /> <br /> 
				<label>Game</label><input type="text" name="game"
                    value="${account.game}" readonly="readonly" /> <br /> 
				<a href="<%=request.getContextPath()%>/admin/google/auth" class="auth">GO AUTH</a>
			</div>
		</c:if>
        <div align="center">
		<c:if test="${null != auth}">
			<c:choose>
				<c:when test="${'success' == auth }">
					<h1 style="color:green">Auth Successfully!</h1>
				</c:when>
				<c:otherwise>
					<h1 style="color:red">Auth failure!</h1>
				</c:otherwise>
			</c:choose>
		</c:if>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/prefixfree.min.js"></script>
	<script type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/jquery.ui.widget.js"></script>
	<script type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/jquery.fileupload.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#upload').fileupload({
		        url: '<%=request.getContextPath()%>/admin/google/saveAccount',
		        dataType: 'json',
		        done: function (e, data) {
		        	setTimeout(function(){
		        		$('#progress').css('display','none');
		        		/* var account = data._response.result, game = account.game, clientId = account.clientId, clientSecret = account.clientSecret, redirectUrl = account.redirectUrl;
		        		$('input[name=clientId]').val(clientId);
		        		$('input[name=clientSecret]').val(clientSecret);
		        		$('input[name=redirectUrl]').val(redirectUrl);
		        		$('input[name=game]').val(game); */
		        		
		        		window.location.reload();
		        	},500)
		        },
		        progressall: function (e, data) {
		            var progress = parseInt(data.loaded / data.total * 100, 10);
		            $('#progress .progress-bar').css(
		                'width',
		                progress + '%'
		             );
		        }
		    });
		});
	</script>
</body>
</html>