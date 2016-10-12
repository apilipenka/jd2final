<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script src="<c:url value="/resources/js/jquery-1.12.4.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">


    <s:form id="accountForm" name="accountForm" action="insertAccount" modelAttribute="account" method="post"
            data-toggle="validator">

    <c:if test="${empty action}">
        <c:set var="action" value="addAccount"/>
    </c:if>


    <c:choose>
        <c:when test="${empty command}">
            <c:set var="command" value="ADDACCOUNT"/>
        </c:when>
        <c:when test="${command=='NEWACCOUNT'}">
            <c:set var="command" value="ADDACCOUNT"/>
        </c:when>
    </c:choose>


    <input type="hidden" id="command" name="command" value="${command}">
    <input type="hidden" id="accountID" name="accountID" value="${account.id}">
    <input type="hidden" id="source" name="source" value="${source}">
    <h2><spring:message code="account.title"/></h2>
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


        <label for="number" class="control-label col-xs-6"><spring:message code="label.number"/></label>
        <s:errors path="number" cssStyle="color: red"/>
        <s:input type="text" name="number" id="number" class="form-control" path="number" required="true"/>

        <label
                for="amount" class="control-label col-xs-6"><spring:message code="label.amount"/></label>
        <s:errors path="amount" cssStyle="color: red"/>
        <s:input type="text" name="amount" id="amount" class="form-control" path="amount" required="true"/>

        <label for="agreement"
               class="control-label col-xs-6"><spring:message code="label.agreement"/></label>
        <s:errors path="agreementID" cssStyle="color: red"/>
        <s:select id="agreement" path="agreementID"
                  class="form-control" required="true">
            <c:forEach items="${agreements}" var="agreements">

                <option value="${agreements.getId()}" ${agreements.getId() == account.agreementID ? 'selected="selected"' : ''}>${agreements.getDescription()}</option>

            </c:forEach>
        </s:select>


        <label for="currency"
               class="control-label col-xs-6"><spring:message code="label.currency"/></label>
        <s:errors path="currencyID" cssStyle="color: red"/>

        <s:select id="currency" path="currencyID"
                  class="form-control" required="true">
            <c:forEach items="${currencies}" var="currencies">

                <option value="${currencies.getId()}" ${currencies.getId() == account.currencyID ? 'selected="selected"' : ''}>${currencies.getMnemoCode()}</option>

            </c:forEach>
        </s:select>


        <br></br>

        <button type="submit" class="btn btn-primary  btn-md" name="action1" value="Action1"><spring:message
                code="label.accept"/></button>
        <button type="cancel" class="btn btn-md" name="action2" value="Action2"><spring:message
                code="label.cancel"/></button>

    </div>
    </s:form>
</div>
</body>
</html>