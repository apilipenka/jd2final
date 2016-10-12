<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script src="<c:url value="/resources/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>

<body>
<div class="container">
    <h2><spring:message code="currencies.title"/></h2>

    <!--Search Form -->
    <form action="currency-list" method="get" id="seachCurrencyForm" role="form">
        <input type="hidden" id="command" name="command" value="CURRENCYLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="currencyName" id="currencyName" class="form-control"
                   placeholder="<spring:message code="placeholder.currencysearch"/>"/>
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
    <form action="currency-list" method="get" id="currencyForm" role="form">
        <input type="hidden" id="currencyID" name="currencyID">
        <input type="hidden" id="command" name="command">
        <c:choose>
            <c:when test="${not empty currencyList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td><spring:message code="label.code"/></td>
                        <td><spring:message code="label.mnemocode"/></td>
                        <td><spring:message code="label.name"/></td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="currency" items="${currencyList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${currencyID == currency.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="editcurrency?currencyID=${currency.id}">${currency.id}</a>
                            </td>
                            <td>${currency.code}</td>
                            <td>${currency.mnemoCode}</td>
                            <td>${currency.name}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/deletecurrency?currencyID=${currency.id}"
                                   id="remove">
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
                    <spring:message code="label.currencynotfound"/>

                </div>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="${pageContext.request.contextPath}/insertCurrency">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md"><spring:message code="label.newcurrency"/></button>
    </form>
</div>
</body>
</html>