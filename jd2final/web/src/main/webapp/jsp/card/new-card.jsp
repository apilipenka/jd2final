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
            <c:set var="action" value="addCard"/>
        </c:if>


        <c:choose>
            <c:when test="${empty command}">
                <c:set var="command" value="ADDCARD"/>
            </c:when>
            <c:when test="${command=='NEWCARD'}">
                <c:set var="command" value="ADDCARD"/>
            </c:when>
        </c:choose>


        <input type="hidden" id="command" name="command" value="${command}">
        <input type="hidden" id="cardID" name="cardID" value="${card.id}">
        <input type="hidden" id="source" name="source" value="${source}">
        <h2>Card</h2>
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
                    value="${card.getId()}"
                    readonly/>
            <label for="number" class="control-label col-xs-6">Number:</label>
            <input
                    type="text" name="number" id="number" class="form-control"
                    value="${card.number}" required="true"/>
            <label
                    for="name" class="control-label col-xs-6">Name:</label>
            <input type="text" name="name" id="name"
                   class="form-control" value="${card.name}" required="true"/>

            <label for="validToDate" class="control-label col-xs-6">Valid to date:</label>
            <input type="text" name="validToDate" id="validToDate"
                   class="form-control" value="${card.validToDate}"
                   required="true"/>

            <label for="account"
                   class="control-label col-xs-6">Account:</label>
            <select name="account"
                    id="account" class="form-control">
                <c:forEach items="${accounts}" var="accounts">

                    <option value="${accounts.getId()}" ${accounts.getId() == card.accountID ? 'selected="selected"' : ''}>${accounts.getNumber()}</option>

                </c:forEach>

            </select> <br></br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>