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
    <h2><spring:message code="banks.title"/></h2>
        <!--Search Form -->
    <form action="bank-list" method="get" id="seachBankForm" role="form">
        <input type="hidden" id="command" name="command" value="BANKLIST">
        <div class="form-group col-xs-5">
            <input type="text" name="bankName" id="bankName" class="form-control"
                   placeholder="<spring:message code="placeholder.banksearch"/>"/>
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
    <form action="controller" method="post" id="bankForm" role="form">
        <input type="hidden" id="bankID" name="bankID">
        <input type="hidden" id="command" name="command">
        <c:choose>
            <c:when test="${not empty bankList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td><spring:message code="label.name"/></td>
                        <td><spring:message code="label.unn"/></td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="bank" items="${bankList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test="${bankID == bank.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="new-bank?bankID=${bank.id}">${bank.id}</a>
                            </td>
                            <td>${bank.name}</td>
                            <td>${bank.UNN}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/deletebank?bankID=${bank.id}"
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
                    <spring:message code="label.banknotfound"/>
                </div>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="${pageContext.request.contextPath}/insertBank">
        <br></br>
        <button type="submit" class="btn btn-primary  btn-md"><spring:message code="label.newbank"/></button>
    </form>
</div>
</body>
</html>