<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
    <script src="<c:url value="/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <form action="controller" method="post" role="form"
          data-toggle="validator">


        <c:if test="${empty action}">
            <c:set var="action" value="addExchangeRate"/>
        </c:if>


        <c:choose>
            <c:when test="${empty command}">
                <c:set var="command" value="ADDEXCHANGERATE"/>
            </c:when>
            <c:when test="${command=='NEWEXCHANGERATE'}">
                <c:set var="command" value="ADDEXCHANGERATE"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="exchangeRateID" name="exchangeRateID" value="${exchangeRate.id}">
        <input type="hidden" id="source" name="source" value="${source}">
        <h2>Exchange rate</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-success">
                    ${error}
            </div>
        </c:if>
        <c:if test="${not empty mesage}">
            <div class="alert alert-success">
                    ${mesage}
            </div>
        </c:if>
        <div class="form-group col-xs-6">
            <label for="id" class="control-label col-xs-6">Id:</label>
            <input
                    type="text" name="id" id="id" class="form-control"
                    value="${exchangeRate.getId()}"
                    readonly/>
            <label for="rateDate" class="control-label col-xs-6">Rate date:</label>
            <input type="text" name="ratehDate" id="rateDate" placeholder="dd.MM.yyyy"
                   class="form-control" value="${exchangeRate.rateDate}"
                   required="true"/>
            <label for="rate" class="control-label col-xs-6">Rate:</label>
            <input
                    type="text" name="rate" id="rate" class="form-control"
                    value="${exchangeRate.rate}" required="true"/>
            <label for="currencyId"
                   class="control-label col-xs-6">Currency:</label>
            <select name="currencyId"
                    id="currencyId" class="form-control">
                <c:forEach items="${currencies}" var="currencies">
                    <option value="${currencies.getId()}" ${currencies.getId() == exchangeRate.currencyID ? 'selected="selected"' : ''}>${currencies.getName()}</option>
                </c:forEach>
            </select>
            <label for="targetCurrencyId"
                   class="control-label col-xs-6">Target currency:</label>
            <select name="targetCurrencyId"
                    id="targetCurrencyId" class="form-control">
                <c:forEach items="${currencies}" var="currencies">
                    <option value="${currencies.getId()}" ${currencies.getId() == exchangeRate.targetCurrencyID ? 'selected="selected"' : ''}>${currencies.getName()}</option>
                </c:forEach>
            </select>
            <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>