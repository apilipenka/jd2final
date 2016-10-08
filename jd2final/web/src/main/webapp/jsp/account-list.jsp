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
    <h2>Accounts</h2>
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
    <form action="controller" method="get" id="seachAccountForm" role="form">
        <input type="hidden" id="command" name="command" value="ACCOUNTLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="accountName" id="accountName" class="form-control"
                   placeholder="Type the Number of the account"/>
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
    <form action="controller" method="post" id="accountForm" role="form">
        <input type="hidden" id="accountID" name="accountID">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty accountList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Number</td>
                        <td>Amount</td>
                        <td>Agreement</td>
                        <td>Currency</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="account" items="${accountList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${accountID == account.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="controller?accountID=${account.id}&command=EDITACCOUNT&source=accountlist">${account.id}</a>
                            </td>
                            <td>${account.number}</td>
                            <td>${account.amount}</td>
                            <td>${account.getAgreementNumber()}</td>
                            <td>${account.getCurrencyMnemocode()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=REMOVEACCOUNT&accountID=${account.id}">
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
                    No agreement found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="${pageContext.request.contextPath}/jsp/new-account.jsp?source=AccountListList">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New aaccount</button>
    </form>
</div>
</body>
</html>