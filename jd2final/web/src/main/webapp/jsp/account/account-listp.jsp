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
    <h2><spring:message code="accounts.title"/></h2>

    <!--Search Form -->
    <form action="account-list" method="get" id="seachAccountForm" role="form">
        <input type="hidden" id="command" name="command" value="ACCOUNTLISTWITHPAGINATION">
        <input type="hidden" id="pg" name="pg" value="1">
        <input type="hidden" id="rpp" name="rpp" value=${rpp}>

        <div class="form-group col-xs-5">
            <input type="text" name="accountName" id="accountName" class="form-control"
                   placeholder="<spring:message code="placeholder.accountsearch"/>"/>
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
    <form action="account-list" method="get" id="accountForm" role="form">
        <input type="hidden" id="accountID" name="accountID">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>#</td>
                <td><spring:message code="label.number"/></td>
                <td><spring:message code="label.amount"/></td>
                <td><spring:message code="label.agreement"/></td>
                <td><spring:message code="label.currency"/></td>
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
                        <a href="editaccount?accountID=${account.id}&command=EDITACCOUNT&source=accountlist">${account.id}</a>


                    </td>
                    <td>${account.number}</td>
                    <td>${account.amount}</td>
                    <td>${account.getAgreementNumber()}</td>
                    <td>${account.getCurrencyMnemocode()}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/deleteaccount?accountID=${account.id}">
                            <span class="glyphicon glyphicon-trash"/>




                        </a>

                    </td>
                </tr>

            </c:forEach>
            <tr/>
            <tr class="thead">
                <td>
                    <c:if test="${pg > 1}">
                        <a href="${pageContext.request.contextPath}/account-list?pg=1&rpp=${rpp}<c:if test="${not empty accountName}">&accountName=${accountName}</c:if>">
                            &lt;&lt; &nbsp; First page</a>
                    </c:if>
                </td>
                <td>
                    <c:if test="${pg > 1}">
                        <a href="${pageContext.request.contextPath}/account-list?pg=${pg-1}&rpp=${rpp}<c:if test="${not empty accountName}">&accountName=${accountName}</c:if>">
                            &lt; &nbsp; Prev page</a>
                    </c:if>
                </td>
                <td>Page&nbsp;${pg}&nbsp;from&nbsp;${maxPage}</td>
                <td><c:if test="${pg < maxPage}">
                    <a href="${pageContext.request.contextPath}/account-list?pg=${pg+1}&rpp=${rpp}<c:if test="${not empty accountName}">&accountName=${accountName}</c:if>">Next
                        page &nbsp; &gt;</a>
                </c:if>
                </td>
                <c:if test="${pg < maxPage}">
                    <td><a href="${pageContext.request.contextPath}/account-list?pg=${maxPage}&rpp=${rpp}<c:if test="${not empty accountName}">&accountName=${accountName}</c:if>">Last page &nbsp; &gt;&gt;</a></td>
                </c:if>
                <td>

                    <select onchange="document.location=this.options[this.selectedIndex].value" class="form-control">
                        <option value="${pageContext.request.contextPath}/account-list?rpp=1<c:if test="${not empty accountName}">&accountName=${accountName}</c:if>" ${rpp == 1 ? 'selected="selected"' : ''}>1 record per page</option>
                        <option value="${pageContext.request.contextPath}/account-list?rpp=5<c:if test="${not empty accountName}">&accountName=${accountName}</c:if>" ${rpp == 5 ? 'selected="selected"' : ''}>5 records per page</option>
                        <option value="${pageContext.request.contextPath}/account-list?rpp=10<c:if test="${not empty accountName}">&accountName=${accountName}</c:if>" ${rpp == 10 ? 'selected="selected"' : ''}>10 records per page</option>
                    </select>
                </td>
                <td></td>
            </tr>
        </table>
        <c:choose>
            <c:when test="${not empty accountList}">
                <input type="hidden" id="action" name="action">

            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    <spring:message code="label.accountnotfound"/>

                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="${pageContext.request.contextPath}/insertAccount">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md"><spring:message code="label.newuaccount"/></button>
    </form>
</div>
</body>
</html>


