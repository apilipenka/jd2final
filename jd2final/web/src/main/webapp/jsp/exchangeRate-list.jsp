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
    <h2>Exchange rates</h2>
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
    <form action="controller" method="get" id="seachExchangeRateForm" role="form">
        <input type="hidden" id="command" name="command" value="EXCHANGERATELIST">
        <div class="form-group col-xs-5">
            <input type="text" name="exchangeRateName" id="exchangeRateName" class="form-control"
                   placeholder="Type the currency mnemocode of the exchange rate"/>
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
    <form action="controller" method="post" id="exchangerateForm" role="form">
        <input type="hidden" id="exchangeRateID" name="exchangeRateID">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty exchangeRateList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Rate date</td>
                        <td>Rate</td>
                        <td>Currency</td>
                        <td>Target currency</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="exchangeRate" items="${exchangeRateList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${exchangeRateID == exchangeRate.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="controller?exchangeRateID=${exchangeRate.id}&command=EDITEXCHANGERATE&source=exchangeRatelist">${exchangeRate.id}</a>
                            </td>
                            <td>${exchangeRate.rateDate}</td>
                            <td>${exchangeRate.rate}</td>
                            <td>${exchangeRate.currencyMnemoCode}</td>
                            <td>${exchangeRate.targetCurrencyMnemoCode}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=REMOVEEXCHANGERATE&exchangeRateID=${exchangeRate.id}">
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
                    No exchange rate found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="${pageContext.request.contextPath}/jsp/new-exchangeRate.jsp?source=exchangeRateList">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md">New exchange rate</button>
    </form>
</div>
</body>
</html>