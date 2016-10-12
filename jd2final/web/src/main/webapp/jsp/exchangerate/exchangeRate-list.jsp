<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <h2><spring:message code="exchangerates.title"/></h2>
    <!--Search Form -->
    <form action="controller" method="get" id="seachExchangeRateForm" role="form">
        <input type="hidden" id="command" name="command" value="EXCHANGERATELIST">
        <div class="form-group col-xs-5">
            <input type="text" name="exchangeRateName" id="exchangeRateName" class="form-control"
                   placeholder="<spring:message code="placeholder.exchangeratesearch"/>"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> <spring:message code="label.search"/>
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
                        <td><spring:message code="label.ratedate"/></td>
                        <td><spring:message code="label.rate"/></td>
                        <td><spring:message code="label.currency"/></td>
                        <td><spring:message code="label.targetcurrency"/></td>
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
                                <a href="editexchangeRate?exchangeRateID=${exchangeRate.id}">${exchangeRate.id}</a>
                            </td>
                            <td>${exchangeRate.rateDate}</td>
                            <td>${exchangeRate.rate}</td>
                            <td>${exchangeRate.currencyMnemoCode}</td>
                            <td>${exchangeRate.targetCurrencyMnemoCode}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/deleteexchangerate?exchangeRateID=${exchangeRate.id}">
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
                    <spring:message code="label.exchangeratenotfound"/>

                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="${pageContext.request.contextPath}/insertExchangeRate">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md"><spring:message code="label.newexchangerate"/></button>
    </form>
</div>
</body>
</html>