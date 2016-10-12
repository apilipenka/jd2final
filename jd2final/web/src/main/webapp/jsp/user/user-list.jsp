<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script src="<c:url value="/resources/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <h2><spring:message code="users.title"/></h2>

    <!--Search Form -->
    <form action="user-list" method="get" id="seachUserForm" role="form">
        <input type="hidden" id="command" name="command" value="USERLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="userName" id="userName" class="form-control"
                   placeholder="<spring:message code="placeholder.usersearch"/>"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> <spring:message code="label.search"/>
        </button>
        <br></br>
        <br></br>
    </form>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
            <spring:message code="${message}"/>
        </div>
    </c:if>
    <form action="controller" method="post" id="userForm" role="form">
        <input type="hidden" id="userID" name="userID">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty userList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td><spring:message code="label.Login"/></td>
                        <td><spring:message code="label.firstname"/></td>
                        <td><spring:message code="label.lastname"/></td>
                        <td><spring:message code="label.password"/></td>
                        <td><spring:message code="label.personalnumber"/></td>
                        <td><spring:message code="label.role"/></td>
                        <td><spring:message code="label.birthdate"/></td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="user" items="${userList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${userID == user.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="edituser?userID=${user.id}&command=userlist">${user.id}</a>
                            </td>
                            <td>${user.login}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.password}</td>
                            <td>${user.personalNumber}</td>
                            <td>${user.userRoleName}</td>
                            <td>${user.birthDate}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/deleteuser?userID=${user.id}">
                                    <span class="glyphicon glyphicon-trash"/>
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    <spring:message code="label.usernotfound"/>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="${pageContext.request.contextPath}/user/new-user.jsp?source=UserList">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md"><spring:message code="label.newuser"/></button>
    </form>
</div>
</body>
</html>