<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script src="<c:url value="/resources/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <title>Login</title>
</head>
<body>
<div class="container col-md-4" style="float: none">
    <h2>Login</h2>
    <br/>
    <c:set var="userType" value="GUEST" scope="session"/>
    <c:if test="${not empty error}">
        <div class="alert alert-success">
                ${error}
        </div>
    </c:if>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <c:if test="${not empty wrongAction}">
        <div class="alert alert-success">
                ${wrongAction}
        </div>
    </c:if>
    <c:if test="${not empty nullPage}">
        <div class="alert alert-success">
                ${nullPage}
        </div>
    </c:if>
    <form name="loginForm" method="POST" action="<c:url value='login'/>">
        <input type="hidden" name="command" value="login"/>
        <input class="control col-xs-6"
               type="text" name="login" value="" placeholder="Login"/> <br/>
        <br/>
        <input class="control col-xs-6"
               type="password" name="password" value="" placeholder="Password"/> <br/>
        <br/>
        <input type="submit" value="Log in" class="btn btn-info col-xs-6"/>
        <br/>
        <br/>
        <!--<a href="${pageContext.request.contextPath}/jsp/new-user.jsp?source=Login">Register</a>-->
        <a href="${pageContext.request.contextPath}/register">Register</a>
    </form>
    <hr/>
    Links for guest...
    <br/> Debug info - session = ${sessionScope}


</div>


</body>
</html>