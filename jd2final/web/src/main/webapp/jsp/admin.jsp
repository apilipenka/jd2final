<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome admin</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script src="<c:url value="/resources/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <h3>Welcome</h3>
    <hr/>
    <br></br>
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
    <ul>
        <li class="dropdown" style="list-style: none;">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="${pageContext.request.contextPath}/controller?command=USERROLELIST">Edit user roles</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=USERLIST">Edit users</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=COMMANDLIST">Edit commands</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=USERROLECOMMANDLIST">Edit user role
                    commands</a></li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=BANKLIST">Edit banks</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYLIST">Edit currencies</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=AGREEMENTLIST">Edit agreement</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=ACCOUNTLIST">Edit account</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=ACCOUNTLISTWITHPAGINATION&pg=1&rpp=1">Edit
                        account with pagination</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=CARDLIST">Edit card</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/controller?command=EXCHANGERATELIST">Edit exchange
                    rates</a></li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=CURRENCYEXTLIST">Ext Edit
                    currencies</a>
                </li>
                <li class="divider"></li>
                <li><a href=href="${logoutUrl}">Logout</a></li>
            </ul>
        </li>
    </ul>




    <br></br>
    <ul>
        <li class="dropdown" style="list-style: none;">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<b class="caret"></b></a>
            <ul class="dropdown-menu">


                <c:forEach items="${sessionScope.user.getUserRole().getCommands()}" var="command">
                    <li><a href="${pageContext.request.contextPath}/${command.getUrl()}">${command.getLabel()}</a>
                    </li>
                </c:forEach>
                <li class="divider"></li>
                <li><a href="${logoutUrl}">Logout</a></li>

            </ul>
        </li>
    </ul>

</div>
</body>
</html>