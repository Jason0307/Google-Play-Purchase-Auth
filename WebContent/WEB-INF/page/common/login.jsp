<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/base.css" media="all" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/style.css" media="all" />
</head>
<body>
<div class="page">
    <header id="header">
        <hgroup class="white blank">
            <h2>Welcome To Google Admin</h2>
        </hgroup>
    </header>
    <section class="demo">
        <div class="content">
          <div class="form-wrapper">
              <div class="linker">
                  <span class="ring"></span>
                  <span class="ring"></span>
                  <span class="ring"></span>
                  <span class="ring"></span>
                  <span class="ring"></span>
              </div>
              <form class="login-form" action="<%=request.getContextPath() %>/admin/doLogin" method="POST">
                 <c:if test="${null != message && '' != message}">
                    <span id="message">${message}</span>
                  </c:if>
                  <input type="text" name="username" placeholder="username" />
                  <br />
                  <input type="password" name="password" placeholder="password" />
                  <button type="submit">Log in</button>
              </form>
          </div>
        </div>
    </section>
</div>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath() %>/resources/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath() %>/resources/js/prefixfree.min.js"></script>
</body>
</html>
