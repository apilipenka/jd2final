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
    <h2>Cards</h2>
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
    <form action="controller" method="get" id="seachCardForm" role="form">
        <input type="hidden" id="command" name="command" value="CARDLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="cardName" id="cardName" class="form-control"
                   placeholder="Type the Name or Number of the card"/>
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
    <form action="controller" method="post" id="cardForm" role="form">
        <input type="hidden" id="cardID" name="cardID">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty cardList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Number</td>
                        <td>Name</td>
                        <td>Valid to date</td>
                        <td>Account</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="card" items="${cardList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${cardID == card.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="controller?cardID=${card.id}&command=EDITCARD&source=cardlist">${card.id}</a>
                            </td>
                            <td>${card.number}</td>
                            <td>${card.name}</td>
                            <td>${card.validToDate}</td>
                            <td>${card.accountAgreementBankName}&nbsp;${card.accountNumber}&nbsp;${card.accountCurrencyMnemoCode}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=REMOVECARD&cardID=${card.id}">
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
                    No card found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="${pageContext.request.contextPath}/jsp/card/new-card.jspd.jsp?source=CardList">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New card</button>
    </form>
</div>
</body>
</html>