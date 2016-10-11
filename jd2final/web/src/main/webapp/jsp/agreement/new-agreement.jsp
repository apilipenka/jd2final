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
            <c:set var="action" value="addAgreement"/>
        </c:if>


        <c:choose>
            <c:when test="${empty command}">
                <c:set var="command" value="ADDAGREEMENT"/>
            </c:when>
            <c:when test="${command=='NEWAGREEMENT'}">
                <c:set var="command" value="ADDAGREEMENT"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="agreementID" name="agreementID" value="${agreement.id}">
        <input type="hidden" id="source" name="source" value="${source}">
        <h2>Agreement</h2>
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
                    value="${agreement.getId()}"
                    readonly/>
            <label for="number" class="control-label col-xs-6">Number:</label>
            <input
                    type="text" name="number" id="number" class="form-control"
                    value="${agreement.number}" required="true"/>

            <label for="validFromDate" class="control-label col-xs-6">Valid from date:</label>
            <input type="text" name="validFromDate" id="validFromDate"
                   class="form-control" value="${agreement.validFromDate}"
                   required="true"/>
            <label for="validToDate" class="control-label col-xs-6">Valid to date:</label>
            <input type="text" name="validToDate" id="validToDate"
                   class="form-control" value="${agreement.validToDate}"
                   required="true"/>

            <label for="bank"
                   class="control-label col-xs-6">Bank:</label>
            <select name="bank"
                    id="bank" class="form-control">
                <c:forEach items="${banks}" var="banks">

                    <option value="${banks.getId()}" ${banks.getId() == agreement.bankID ? 'selected="selected"' : ''}>${banks.getDescription()}</option>

                </c:forEach>
            </select> <br></br>

            <label for="client"
                   class="control-label col-xs-6">Client:</label>
            <select name="client"
                    id="client" class="form-control">
                <c:forEach items="${clients}" var="clients">

                    <option value="${clients.getId()}" ${clients.getId() == agreement.userID ? 'selected="selected"' : ''}>${clients.getDescription()}</option>

                </c:forEach>
            </select> <br></br>

            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>