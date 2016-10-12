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
    <s:form id="exchangeRateForm" name="exchangeRateForm" action="insertExchangeRate" modelAttribute="exchangeRate"
            data-toggle="validator">
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
    <h2><spring:message code="exchangerate.title"/></h2>
    <c:if test="${not empty error}">
        <div class="alert alert-success">
            <spring:message code="${error}"/>
        </div>
    </c:if>
    <c:if test="${not empty mesage}">
        <div class="alert alert-success">
            <spring:message code="${message}"/>
        </div>
    </c:if>
    <div class="form-group col-xs-6">
        <label for="id" class="control-label col-xs-6">Id:</label>
        <s:errors path="id" cssStyle="color: red"/>
        <s:input type="text" name="id" id="id" class="form-control" readonly="true" path="id"/>

        <label for="rateDate" class="control-label col-xs-6">Rate date:</label>
        <s:errors path="rateDate" cssStyle="color: red"/>
        <s:input type="text" name="rateDate" id="rateDate" class="form-control" path="rateDate" required="true"/>


        <label for="rate" class="control-label col-xs-6">Rate:</label>
        <s:errors path="rate" cssStyle="color: red"/>
        <s:input type="text" name="rate" id="rate" class="form-control" path="rate" required="true"/>

        <label for="currencyId"
               class="control-label col-xs-6">Currency:</label>
        <s:errors path="currencyID" cssStyle="color: red"/>
        <s:select id="currencyId" path="currencyID"
        class="form-control" required="true">
        <c:forEach items="${currencies}" var="currencies">

            <option value="${currencies.getId()}" ${currencies.getId() == exchangeRate.currencyID ? 'selected="selected"' : ''}>${currencies.getName()}</option>

        </c:forEach>
        </s:select>






        <label for="targetCurrencyId"
               class="control-label col-xs-6">Target currency:</label>
        <s:errors path="targetCurrencyID" cssStyle="color: red"/>
        <s:select id="targetCurrencyId" path="targetCurrencyID"
                  class="form-control" required="true">
            <c:forEach items="${currencies}" var="currencies">

                <option value="${currencies.getId()}" ${currencies.getId() == exchangeRate.targetCurrencyID ? 'selected="selected"' : ''}>${currencies.getName()}</option>

            </c:forEach>
        </s:select>


        <br></br>
        <button type="submit" class="btn btn-primary  btn-md" name = "action1" value="Action1"><spring:message code="label.accept"/></button>
        <button type="cancel" class="btn btn-md" name = "action2" value="Action2"><spring:message code="label.cancel"/></button>
    </div>
    </s:form>
</div>
</body>
</html>