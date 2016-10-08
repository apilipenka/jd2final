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
        <h2>Account</h2>
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
                    value="${account.getId()}"
                    readonly/>
            <label for="number" class="control-label col-xs-6">Number:</label>
            <input
                    type="text" name="number" id="number" class="form-control"
                    value="${account.number}" required="true"/>
            <label
                    for="amount" class="control-label col-xs-6">Amount:</label>
            <input type="text" name="amount" id="amount"
                   class="form-control" value="${account.amount}" required="true"/>

            <label for="agreement"
                   class="control-label col-xs-6">Agreement:</label>
            <select name="agreement"
                    id="agreement" class="form-control">
                <c:forEach items="${agreements}" var="agreements">

                    <option value="${agreements.getId()}" ${agreements.getId() == account.agreementID ? 'selected="selected"' : ''}>${agreements.getDescription()}</option>

                </c:forEach>
            </select>

            <label for="currency"
                   class="control-label col-xs-6">Currency:</label>
            <select name="currency"
                    id="currency" class="form-control">
                <c:forEach items="${currencies}" var="currencies">

                    <option value="${currencies.getId()}" ${currencies.getId() == account.currencyID ? 'selected="selected"' : ''}>${currencies.getMnemoCode()}</option>

                </c:forEach>
            </select> <br></br>

            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>