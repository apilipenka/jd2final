<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <h2>Users</h2>
    <ul>
        <li class="dropdown" style="list-style: none;">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<b class="caret"></b></a>
            <ul class="dropdown-menu">


                <c:forEach items="${sessionScope.user.getUserRole().getCommands()}" var="command">
                    <li><a href="${pageContext.request.contextPath}/${command.getUrl()}">${command.getLabel()}</a>
                    </li>
                </c:forEach>

                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a></li>
            </ul>
        </li>
    </ul>
    <!--Search Form -->
    <form action="controller" method="get" id="seachUserForm" role="form">
        <input type="hidden" id="command" name="command" value="USERLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="userName" id="userName" class="form-control"
                   placeholder="Type the Name or Last Name of the user"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br></br>
        <br></br>
    </form>

    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
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
                        <td>Login</td>
                        <td>First name</td>
                        <td>Last name</td>
                        <td>Password</td>
                        <td>Personal number</td>
                        <td>User role</td>
                        <td>Birth date</td>
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
                                <a href="controller?userID=${user.id}&command=EDITUSER&source=userlist">${user.id}</a>
                            </td>
                            <td>${user.login}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.password}</td>
                            <td>${user.personalNumber}</td>
                            <td>${user.userRoleName}</td>
                            <td>${user.birthDate}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=REMOVEUSER&userID=${user.id}">
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
                    No people found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="${pageContext.request.contextPath}/jsp/new-user.jsp?source=UserList">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New user</button>
    </form>
</div>
</body>
</html>